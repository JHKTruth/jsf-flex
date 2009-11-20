/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.googlecode.jsfFlex.renderkit.mxml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.faces.context.ResponseWriter;

import com.googlecode.jsfFlex.renderkit.MXMLRendererBase;
import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks._CommonTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FileManipulatorTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FlexTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._TaskRunner;
import com.googlecode.jsfFlex.shared.tasks._TaskRunner.QUEUE_TASK_ID;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLResponseWriter extends ResponseWriter {
    
    private final static String JSF_FLEX_FLASH_APPLICATION_CONFIG_TEMPLATE = "jsf-flex-flash-application-config.vm";
    private final static String TO_CREATE_JSF_FLEX_FLASH_APPLICATION_CONFIG_FILE_NAME = "jsfFlexFlashApplicationConfig.xml";
    private final static String JSF_FLEX_FLASH_APPLICATION_CONFIG_TOKEN = "jsfFlexFlashApplicationConfig";
    
    private final static Object lock = new Object();
    
    protected AbstractMXMLResponseWriter(){
        super();
    }
    
    public void execute() {
        getCommonTaskRunner().execute();
        getFlexTaskRunner().execute();
    }
    
    /**
     * This method will map the fields of javaDoc/annotation [depends on which JRE version was specified during<br>
     * build time] from the MXMLUIComponent to a HashSet.<br>
     * 
     * @param mapClass
     * @param componentObj
     * @param mappingFile
     */
    public final void mapFields(Class mapClass, Object componentObj, String mappingFile) {
        _MXMLContract comp = _MXMLContract.class.cast( componentObj );
        comp.getAnnotationDocletParserInstance().mapComponentFields(mapClass, componentObj, mappingFile);
    }
    
    /**
     * This method will return the child preMxml component identifier. In another words, when the preMxml file is created<br>
     * the file requires this identifier to be placed within the file so that possible child can be added to the correct<br>
     * component and have correct relationship.<br>
     * 
     * @param currInstance
     * @return
     */
    public final String childPreMxmlComponentIdentifier(_MXMLContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(MXMLRendererBase.MAJOR_MINOR_DELIM);
        toReturn.append(currInstance.getMajorLevel()+1);
        toReturn.append(0);
        
        return toReturn.toString();
    }
    
    /**
     * This method will return the sibling preMxml component identifier. In another words, when the preMxml file is created<br>
     * the file requires this identifier to be placed within the file so that possible sibling can be added to the correct<br>
     * component and have correct relationship.<br>
     * 
     * @param currInstance
     * @return
     */
    public final String siblingPreMxmlComponentIdentifier(_MXMLContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(currInstance.getParentPreMxmlIdentifier());
        toReturn.append(MXMLRendererBase.MAJOR_MINOR_DELIM);
        toReturn.append(currInstance.getMajorLevel());
        toReturn.append(currInstance.getMinorLevel()+1);
        
        return toReturn.toString();
    }
    
    /**
     * This method will return the preMxml identifier of the component as a child component. Meaning this component has a minor level of 0,<br>
     * so it is the first component of the parent component and should be considered as a child and NOT a sibling.<br>
     * 
     * @param currInstance
     * @return
     */
    public final String childReplaceTokenWithPreMxmlIdentifier(_MXMLContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(MXMLConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(MXMLConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
        
        return toReturn.toString();
    }
    
    /**
     * This method will return the preMxml identifier of the component as a sibling component. Meaning this component does NOT have a minor level of 0,<br>
     * so it is NOT the first component of the parent component and should be NOT considered as a child but as a sibling.<br>
     * 
     * @param currInstance
     * @return
     */
    public final String siblingReplaceTokenWithPreMxmlIdentifier(_MXMLContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(MXMLConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(MXMLConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
        
        return toReturn.toString();
    }
    
    /**
     * This method will extract the flexSDk
     * 
     * @param componentMXML
     */
    public final void unZipFlexSDK(_MXMLApplicationContract componentMXML) {
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        synchronized(lock){
            if(!new File(mxmlContext.getFlexSDKPath()).exists()){
                makeDirectory(mxmlContext.getFlexSDKPath());
                String queueTaskId = componentMXML.getId();
                unZipArchiveRelative(MXMLConstants.FLEX_SDK_ZIP, mxmlContext.getFlexSDKPath(), queueTaskId);
            }
        }
    }
    
    /**
     * This method should be invoked when one wishes to wait for FutureTask's end
     * 
     * @param taskRunner
     * @param queueTaskId
     */
    public final void waitForFutureTask(_TaskRunner taskRunner, String queueTaskId){
        
        taskRunner.waitForFutureTask(queueTaskId);
    }
    
    /**
     * One can consider this method to be somewhat of a facade in creating application SWF file.<br>
     * 
     * @param mxmlFile
     * @param componentMXML
     * @param multiLingualSupportMap
     */
    public final void processCreateSwf(String mxmlFile, _MXMLApplicationContract componentMXML, Map<String, String> multiLingualSupportMap) {
        
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        //now create the MXML file
        createMXML(componentMXML.getAbsolutePathToPreMxmlFile(), mxmlFile);
        
        final String queueTaskId = componentMXML.getId();
        final String flexSDKPath = mxmlContext.getFlexSDKPath();
        
        String unZipArchiveRelativeQueueTaskId = QUEUE_TASK_ID.UNZIP_ARCHIVE_RELATIVE.getQueueTaskId(queueTaskId);
        waitForFutureTask(getCommonTaskRunner(), unZipArchiveRelativeQueueTaskId);
        
        synchronized(lock){
            
            if(!new File(mxmlContext.getSwcPath()).exists()){
                //copy the necessary ActionScript files over for SWF generation 
                createSwcSourceFiles(mxmlContext.getSwcPath(), MXMLConstants.getSwcSourceFiles(), 
                                            MXMLConstants.JSF_FLEX_MAIN_SWC_CONFIG_FILE, mxmlContext.getWebContextPath());
                
                //create the SWC file
                String loadConfigAbsolutePath = mxmlContext.getSwcPath() + MXMLConstants.JSF_FLEX_MAIN_SWC_CONFIGURATIONFILE;
                String swcFileLocationPath = mxmlContext.getSwcPath() + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWC_FILE_EXT;
                createSystemSWCFile(mxmlContext.getSwcPath(), swcFileLocationPath, flexSDKPath, loadConfigAbsolutePath, null);
                
                /*
                 *  copy the necessary swf source files to swfBasePath
                 *  these are files such as xml[s] which are used by the system's/above ActionScripts
                 */
                createSwfSourceFiles(mxmlContext.getSwfBasePath(), MXMLConstants.getSwfSourceFiles());
                
                /*
                 * unzip the swc's library.swf file and copy it to the swf file for linking with the swf file
                 */
                unZipArchiveAbsolute(new File(swcFileLocationPath), mxmlContext.getSwcPath(), null);
                
                //copy the library.swf file to swc directory
                copyFileSet(mxmlContext.getSwcPath(), "**/*.swf", null, mxmlContext.getSwfBasePath(), null);
                
                //rename the file from library.swf to jsfFlexMainSwc.swf file
                String sourceFile = mxmlContext.getSwcPath() + MXMLConstants.DEFAULT_SWC_LIBRARY_SWF_NAME;
                String destFile = mxmlContext.getSwfBasePath() + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWF_FILE_EXT;
                
                renameFile(sourceFile, destFile, true);
                
                deleteResources(sourceFile, false, null);
            }
            
            createJsfFlexFlashApplicationConfigurationFile();
            
            final _FlexTaskRunner flexTaskRunner = getFlexTaskRunner();
            final CountDownLatch localeLatch = new CountDownLatch(multiLingualSupportMap.keySet().size());
            /*
             * Additional step of executing copyLocale script for Flex 3.0+.
             * Skip en_US as it is the original source language
             * TODO: Is the below CountDownLatch truly needed [might be more harmful due to context switch and etcetera, oonsider later]
             */
            for(final String currLocale : multiLingualSupportMap.keySet()){
                if(currLocale.equalsIgnoreCase(MXMLConstants.EN_US)){
                    localeLatch.countDown();
                    continue;
                }
                
                new Thread(new Runnable(){
                    
                    public void run() {
                        String waitForQueueTaskId = QUEUE_TASK_ID.COPY_LOCALE.getQueueTaskId(queueTaskId);
                        flexTaskRunner.copyLocale(currLocale, flexSDKPath, waitForQueueTaskId);
                        waitForFutureTask(flexTaskRunner, waitForQueueTaskId);
                        localeLatch.countDown();
                    }
                    
                }).start();
                
            }
            try{
                localeLatch.await();
            }catch(InterruptedException interruptedExcept){
                Thread.currentThread().interrupt();
            }
        }
        
        //finally the SWF file
        createSWF(mxmlFile, componentMXML, mxmlContext.getFlexSDKPath(), multiLingualSupportMap, mxmlContext.getLocaleWebContextPath(), queueTaskId);
        
    }
    
    /**
     * Returns the multiLingualSupport Map for this web application.<br>
     * 
     * @return
     */
    public final Map<String, String> getMultiLingualSupportMap(){
        Map<String, String> multiLingualSupportMap = new LinkedHashMap<String, String>();
        
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        String localeWebContextPath = mxmlContext.getLocaleWebContextPath();
        
        if(localeWebContextPath == null){
            multiLingualSupportMap.put(MXMLConstants.DEFAULT_LOCALE_SWF_PATH_KEY, mxmlContext.getSwfPath());
        }else{
            String swfBaseName = mxmlContext.getCurrMxml();
            String swfFileNameBasePath = mxmlContext.getSwfBasePath() + swfBaseName + File.separatorChar;
            
            File localeWebContextDirectory = new File(localeWebContextPath);
            if(localeWebContextDirectory.isDirectory()){
                String[] directoryChildren = localeWebContextDirectory.list();
                
                for(String currDirectoryChild : directoryChildren){
                    File currentChild = new File(localeWebContextPath + currDirectoryChild);
                    if(currentChild.isDirectory()){
                        //a locale
                        String locale = currentChild.getName();
                        multiLingualSupportMap.put(locale, swfFileNameBasePath + swfBaseName + MXMLConstants.SWF_FILE_NAME_LOCALE_SEPARATOR 
                                                                + locale + MXMLConstants.SWF_FILE_EXT);
                    }
                }
            }
            
        }
        
        return multiLingualSupportMap;
    }
    
    /**
     * This method will copy one file to an another file. Note that these should be specified in absolute path.<br>
     * 
     * @param fileToCopy
     * @param fileToCopyTo
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String copyFile(String fileToCopy, String fileToCopyTo, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.COPY_FILE.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().copyFile(fileToCopy, fileToCopyTo, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will copy certain fileSet to the destination directory [i.e. if you wish to exclude or include only a specific set of<br>
     * file extensions this method should be used]. Note that the copy source and copy target should be specified in absolute path.<br>
     * 
     * @param copyDir
     * @param copyInclude
     * @param copyExclude
     * @param copyTo
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String copyFileSet(String copyDir, String copyInclude, String copyExclude, String copyTo, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.COPY_FILE_SET.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().copyFileSet(copyDir, copyInclude, copyExclude, copyTo, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will flatten the MXMLApplicationRenderer preMxml file and copy it as a MXML file to its correct directory,<br>
     * which should be specified in absolute path.<br>
     * 
     * @param targetAbsolutePath
     * @param copyTo
     */
    public final void createMXML(String targetAbsolutePath, String copyTo) {
        
        getFlexTaskRunner().createMXML(targetAbsolutePath, copyTo);
    }
    
    /**
     * This method will create the application SWF file from its MXML file.<br>
     * 
     * @param mxmlFile
     * @param componentMXML
     * @param flexSDKRootPath
     * @param multiLingualSupportMap
     * @param localeWebContextPath
     * @param queueTaskId
     */
    public final void createSWF(final String mxmlFile, final _MXMLApplicationContract componentMXML, final String flexSDKRootPath, Map<String, String> multiLingualSupportMap, 
                                    String localeWebContextPath, String queueTaskId) {
        
        String defaultLocale = multiLingualSupportMap.get(MXMLConstants.DEFAULT_LOCALE_SWF_PATH_KEY);
        
        if(defaultLocale != null){
            
            getFlexTaskRunner().createSWF(mxmlFile, defaultLocale, componentMXML, flexSDKRootPath, null, null, queueTaskId == null ? null : QUEUE_TASK_ID.CREATE_SWF.getQueueTaskId(queueTaskId));
        }else{
            
            if(queueTaskId == null){
                
                for(String currLocale : multiLingualSupportMap.keySet()){
                    String currLocaleFileName = multiLingualSupportMap.get(currLocale);
                    String currLocaleSourcePath = localeWebContextPath + currLocale + File.separatorChar;
                    
                    getFlexTaskRunner().createSWF(mxmlFile, currLocaleFileName, componentMXML, flexSDKRootPath, currLocale, currLocaleSourcePath, null);
                }
                
            }else{
                
                /*
                 * TODO: Is the below CountDownLatch truly needed [might be more harmful due to context switch and etcetera, oonsider later]
                 */
                int swfCount = 0;
                final _FlexTaskRunner flexTaskRunner = getFlexTaskRunner();
                final CountDownLatch createSWFLatch = new CountDownLatch(multiLingualSupportMap.keySet().size());
                for(final String currLocale : multiLingualSupportMap.keySet()){
                    final String currLocaleFileName = multiLingualSupportMap.get(currLocale);
                    final String currLocaleSourcePath = localeWebContextPath + currLocale + File.separatorChar;
                    
                    final String currQueueTaskId = QUEUE_TASK_ID.CREATE_SWF.getQueueTaskId(queueTaskId + "_" + swfCount);
                    
                    new Thread(new Runnable(){
                        
                        public void run(){
                            flexTaskRunner.createSWF(mxmlFile, currLocaleFileName, componentMXML, flexSDKRootPath, currLocale, currLocaleSourcePath, currQueueTaskId);
                            waitForFutureTask(flexTaskRunner, currQueueTaskId);
                            createSWFLatch.countDown();
                        }
                        
                    }).start();
                    
                }
                
                try{
                    createSWFLatch.await();
                }catch(InterruptedException interruptedExcept){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    /**
     * This method will execute a copyLocale script of en_US to the specified locale. This is an add on task for Flex 3.0+.<br>
     * 
     * @param locale
     * @param flexSDKRootPath
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String copyLocale(String locale, String flexSDKRootPath, String queueTaskId){
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.COPY_LOCALE.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().copyLocale(locale, flexSDKRootPath, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will create the necessary SWC source files. Please refer to mxmlConstants.xml for the file listings.<br>
     * 
     * @param swcPath
     * @param systemSourceFiles
     * @param jsfFlexMainSwcConfigFile
     * @param webContextPath
     */
    public final void createSwcSourceFiles(String swcPath, List<String> systemSourceFiles, String jsfFlexMainSwcConfigFile, String webContextPath) {
        
        getFlexTaskRunner().createSwcSourceFiles(swcPath, systemSourceFiles, jsfFlexMainSwcConfigFile, webContextPath);
    }
    
    /**
     * This method will create the necessary source files for the application SWF. Please refer to mxmlConstants.xml for the file listings.<br>
     * 
     * @param swfBasePath
     * @param systemSwfSourceFiles
     */
    public final void createSwfSourceFiles(String swfBasePath, List<String> systemSwfSourceFiles) {
        
        getFlexTaskRunner().createSwfSourceFiles(swfBasePath, systemSwfSourceFiles);
    }
    
    /**
     * This method will create jsf-flex-flash-config.xml file that will be loaded by resources such as AbstractLogger.as
     */
    public final void createJsfFlexFlashApplicationConfigurationFile(){
        
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        JsfFlexFlashApplicationConfiguration jsfFlexFlashApplicationConfiguration = mxmlContext.getJsfFlexFlashApplicationConfiguration();
        
        String filePath = mxmlContext.getSwfBasePath() + TO_CREATE_JSF_FLEX_FLASH_APPLICATION_CONFIG_FILE_NAME;
        
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put(JSF_FLEX_FLASH_APPLICATION_CONFIG_TOKEN, jsfFlexFlashApplicationConfiguration);
        
        createFileContent(filePath, JSF_FLEX_FLASH_APPLICATION_CONFIG_TEMPLATE, null, tokenMap);
        
    }
    
    /**
     * This method will create the SWC file, which will contain a library SWF file to be used by application SWF files.<br>
     * 
     * @param sourcePath
     * @param outPut
     * @param flexSDKRootPath
     * @param loadConfigFilePath
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String createSystemSWCFile(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.CREATE_SYSTEM_SWC_FILE.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().createSystemSWCFile(sourcePath, outPut, flexSDKRootPath, loadConfigFilePath, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will delete the resource, which should be specified in absolute path.<br>
     * 
     * @param deleteResource
     * @param isDirectory
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String deleteResources(String deleteResource, boolean isDirectory, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.DELETE_RESOURCES.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().deleteResources(deleteResource, isDirectory, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will create a directory, which should be specified in absolute path.<br>
     * 
     * @param directoryToCreate
     */
    public final void makeDirectory(String directoryToCreate) {
        
        getFlexTaskRunner().makeDirectory(directoryToCreate);
    }
    
    /**
     * This method will enable renaming of a file to an another file name. Note that the copy source and copy target should be specified<br>
     * in absolute path.<br>
     * 
     * @param sourceFile
     * @param destFile
     * @param overWrite
     */
    public final void renameFile(String sourceFile, String destFile, boolean overWrite) {
        
        getFlexTaskRunner().renameFile(sourceFile, destFile, overWrite);
    }
    
    /**
     * This method will replace a token with a value within a preMxml file.<br>
     * 
     * @param targetAbsolutePath
     * @param valueToReplaceWith
     * @param tokenReplace
     */
    public final void replaceTokenWithValue(String targetAbsolutePath, String valueToReplaceWith, String tokenReplace) {
        
        getFlexTaskRunner().replaceTokenWithValue(targetAbsolutePath, valueToReplaceWith, tokenReplace);
    }
    
    /**
     * This method should be used for files that are relative to the UnzipTask.<br>
     * 
     * @param unZipFile
     * @param unZipDest
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String unZipArchiveRelative(String unZipFile, String unZipDest, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.UNZIP_ARCHIVE_RELATIVE.getQueueTaskId(queueTaskId);
        getCommonTaskRunner().unZipArchiveRelative(unZipFile, unZipDest, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method should be used for files that are absolute.<br>
     * 
     * @param unZipFile
     * @param unZipDest
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String unZipArchiveAbsolute(File unZipFile, String unZipDest, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.UNZIP_ARCHIVE_ABSOLUTE_FI.getQueueTaskId(queueTaskId);
        getCommonTaskRunner().unZipArchiveAbsolute(unZipFile, unZipDest, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method should be used for files that are absolute.<br>
     * 
     * @param unZipFile
     * @param unZipDest
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking _TaskRunner.waitForFutureTask
     */
    public final String unZipArchiveAbsolute(InputStream unZipFile, String unZipDest, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.UNZIP_ARCHIVE_ABSOLUTE_IS.getQueueTaskId(queueTaskId);
        getCommonTaskRunner().unZipArchiveAbsolute(unZipFile, unZipDest, queueTaskId);
        return queueTaskId;
    }
    
    public final void createFileContent(String filePath, String templateFile, Properties initProperties, Map<String, ? extends Object> tokenMap){
        
        getFileManipulatorTaskRunner().createFileContent(filePath, templateFile, initProperties, tokenMap);
    }
    
    /**
     * This method will create the preMxml file of the component.<br>
     * 
     * @param comp
     * @param mxmlComponentName
     * @param bodyContent
     */
    public final void createPreMxml(_MXMLContract comp, String mxmlComponentName, String bodyContent) {
        
        String fileDirectory = comp.getAbsolutePathToPreMxmlFile().substring(0, comp.getAbsolutePathToPreMxmlFile().lastIndexOf(File.separatorChar));
        getFlexTaskRunner().makeDirectory(fileDirectory);
        
        getFileManipulatorTaskRunner().createPreMxmlFile(comp.getAbsolutePathToPreMxmlFile(), null, comp.getAnnotationDocletParserInstance().getTokenValueSet(), mxmlComponentName, 
                                                                bodyContent, childPreMxmlComponentIdentifier(comp), siblingPreMxmlComponentIdentifier(comp));
        
    }
    
    /**
     * This method will load and read the template specified and return it as a String.<br>
     * 
     * @param loader
     * @param template
     * @return
     */
    public final String getComponentTemplate(ClassLoader loader, String template) {
        
        return getFileManipulatorTaskRunner().getComponentTemplate(loader, template);
    }
    
    /**
     * This method will read the file specified and return it as a String. Note the fileName should be specified in absolute path.<br>
     * 
     * @param fileName
     * @return
     */
    public final String readFileContent(String fileName) {
        
        return getFileManipulatorTaskRunner().readFileContent(fileName);
    }
    
    /**
     * Convenient method to write content to the passed in Writer instance. Convenient meaning it will throw a ComponentBuildException<br>
     * which extends RuntimeException when an exception was triggered.<br>
     * 
     * @param writer
     * @param toWrite
     */
    public final void writeToWriter(Writer writer, String toWrite){
        
        try{
            writer.write(toWrite);
        }catch(IOException exceptionWriting){
            throw new ComponentBuildException("Exception while writing " + toWrite, exceptionWriting);
        }
    }
    
    /**
     * This method will return _CommonTaskRunner interface from MxmlContext.<br>
     * 
     * @return
     */
    public final _CommonTaskRunner getCommonTaskRunner(){
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        return mxmlContext.getCommonRunner();
    }
        
    /**
     * This method will return _FileManipulatorTaskRunner interface from MxmlContext.<br>
     * 
     * @return
     */
    public final _FileManipulatorTaskRunner getFileManipulatorTaskRunner(){
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        return mxmlContext.getFileManipulatorRunner();
    }
    
    /**
     * This method will return _FlexTaskRunner interface from MxmlContext.<br>
     * 
     * @return
     */
    public final _FlexTaskRunner getFlexTaskRunner(){
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        return mxmlContext.getFlexRunner();
    }
    
}
