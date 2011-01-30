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
package com.googlecode.jsfFlex.renderkit.flex;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.faces.context.ResponseWriterWrapper;

import com.googlecode.jsfFlex.renderkit.FlexRendererBase;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks.ICommonTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.AbstractFileManipulatorTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.IFlexTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.ITaskRunner;
import com.googlecode.jsfFlex.shared.tasks.ITaskRunner.QUEUE_TASK_ID;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractFlexResponseWriter extends ResponseWriterWrapper {
    
    private final static String EXTERNAL_LIBRARY_PATH_COLLECTION_ERROR = "Paths for External Libary SWC files must be absolute.";
    
    private final static String JSF_FLEX_FLASH_APPLICATION_CONFIG_TEMPLATE = "jsf-flex-flash-application-config.vm";
    private final static String TO_CREATE_JSF_FLEX_FLASH_APPLICATION_CONFIG_FILE_NAME = "jsfFlexFlashApplicationConfig.xml";
    private final static String JSF_FLEX_FLASH_APPLICATION_CONFIG_TOKEN = "jsfFlexFlashApplicationConfig";
    
    private final static Object LOCK = new Object();
    private final static FilenameFilter SWF_LIBRARY_FILENAME_FILTER = new FilenameFilter() {
        public boolean accept(File currDir, String currFileName) {
            /*
             * Since indexOf uses regular expression for String argument, must escape period.
             */
            return currFileName.indexOf("\\.swf") > -1;
        }
    };
    
    protected AbstractFlexResponseWriter(){
        super();
    }
    
    /**
     * Method to shutdown all the FutureTasks for various runners.
     */
    public void shutDownFutureTasks(){
        getCommonTaskRunner().clearAllFutureTasks();
        getFileManipulatorTaskRunner().clearAllFutureTasks();
        getFlexTaskRunner().clearAllFutureTasks();
    }
    
    /**
     * This method will map the fields of javaDoc/annotation [depends on which JRE version was specified during<br>
     * build time] from the FlexUIComponent to a HashSet.<br>
     * 
     * @param mapClass
     * @param componentObj
     * @param mappingFile
     */
    public final void mapFields(Class mapClass, Object componentObj, String mappingFile) {
        IFlexContract comp = IFlexContract.class.cast( componentObj );
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
    public final String childPreMxmlComponentIdentifier(IFlexContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(FlexRendererBase.MAJOR_MINOR_DELIM);
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
    public final String siblingPreMxmlComponentIdentifier(IFlexContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(currInstance.getParentPreMxmlIdentifier());
        toReturn.append(FlexRendererBase.MAJOR_MINOR_DELIM);
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
    public final String childReplaceTokenWithPreMxmlIdentifier(IFlexContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(FlexConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(FlexConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
        
        return toReturn.toString();
    }
    
    /**
     * This method will return the preMxml identifier of the component as a sibling component. Meaning this component does NOT have a minor level of 0,<br>
     * so it is NOT the first component of the parent component and should be NOT considered as a child but as a sibling.<br>
     * 
     * @param currInstance
     * @return
     */
    public final String siblingReplaceTokenWithPreMxmlIdentifier(IFlexContract currInstance){
        StringBuilder toReturn = new StringBuilder();
        
        toReturn.append(FlexConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
        toReturn.append(currInstance.getPreMxmlIdentifier());
        toReturn.append(FlexConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
        
        return toReturn.toString();
    }
    
    /**
     * This method will extract the flexSDk
     * 
     * @param componentFlex
     */
    public final void unZipFlexSDK(IFlexApplicationContract componentFlex) {
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        synchronized(LOCK){
            if(!new File(flexContext.getFlexSDKPath()).exists()){
                makeDirectory(flexContext.getFlexSDKPath());
                String queueTaskId = componentFlex.getId();
                unZipArchiveRelative(FlexConstants.FLEX_SDK_PART_1_ZIP, flexContext.getFlexSDKPath(), queueTaskId + "_1");
                unZipArchiveRelative(FlexConstants.FLEX_SDK_PART_2_ZIP, flexContext.getFlexSDKPath(), queueTaskId + "_2");
                unZipArchiveRelative(FlexConstants.FLEX_SDK_PART_3_ZIP, flexContext.getFlexSDKPath(), queueTaskId + "_3");
            }
        }
    }
    
    /**
     * A method that should be invoked to ensure that FutureTasks of unzipping a flexSDK has been completed. This is due to<br>
     * flexSDK being broken up into multiple zip files for multiple FutureTasks.
     * 
     * @param queueTaskId
     */
    public void waitForFlexUnzip(String queueTaskId){
        String unZipArchiveRelativeQueueTaskPart1Id = QUEUE_TASK_ID.UNZIP_ARCHIVE_RELATIVE.getQueueTaskId(queueTaskId + "_1");
        String unZipArchiveRelativeQueueTaskPart2Id = QUEUE_TASK_ID.UNZIP_ARCHIVE_RELATIVE.getQueueTaskId(queueTaskId + "_2");
        String unZipArchiveRelativeQueueTaskPart3Id = QUEUE_TASK_ID.UNZIP_ARCHIVE_RELATIVE.getQueueTaskId(queueTaskId + "_3");
        
        waitForFutureTask(getCommonTaskRunner(), unZipArchiveRelativeQueueTaskPart1Id);
        waitForFutureTask(getCommonTaskRunner(), unZipArchiveRelativeQueueTaskPart2Id);
        waitForFutureTask(getCommonTaskRunner(), unZipArchiveRelativeQueueTaskPart3Id);
    }
    
    /**
     * This method should be invoked when one wishes to wait for FutureTask's end
     * 
     * @param taskRunner
     * @param queueTaskId
     */
    public final void waitForFutureTask(ITaskRunner taskRunner, String queueTaskId){
        
        taskRunner.waitForFutureTask(queueTaskId);
    }
    
    /**
     * Will modify java.home's jvm.config parameter with the Java SDK path specified by the user 
     * Used if the user is using a Windows 64 bit applications
     * 
     * @param flexContext
     */
    public final void checkFlexJavaSDKPath(AbstractFlexContext flexContext) {
    	
        if(flexContext.getFlexJavaSDKPath() != null){
        	/*
        	 * The path must be of '/' and not '\'
        	 */
        	String actualPath = flexContext.getFlexJavaSDKPath().replaceAll("\\\\", "/");
        	replaceTokenWithValue(flexContext.getFlexSDKPath() + FlexConstants.FLEX_JVM_CONFIG_PATH,
        			FlexConstants.FLEX_JVM_CONFIG_JAVA_HOME + actualPath, FlexConstants.FLEX_JVM_CONFIG_JAVA_HOME);
        }
    }
    
    /**
     * One can consider this method to be somewhat of a facade in creating application SWF file.<br>
     * 
     * @param flexFile
     * @param componentFlex
     * @param multiLingualSupportMap
     */
    public final void processCreateSwf(String flexFile, IFlexApplicationContract componentFlex, Map<String, String> multiLingualSupportMap) {
        
        final AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        final IFlexTaskRunner flexTaskRunner = getFlexTaskRunner();
        final ICommonTaskRunner commonTaskRunner = getCommonTaskRunner();
        final String queueTaskId = componentFlex.getId();
        final String flexSDKPath = flexContext.getFlexSDKPath();
        
        //now create the MXML file
        createMXML(componentFlex.getAbsolutePathToPreMxmlFile(), flexFile);
        
        waitForFlexUnzip(queueTaskId);
        
        synchronized(LOCK){
            
        	if(!new File(flexContext.getJsfFlexSwcPath()).exists()){
        		checkFlexJavaSDKPath(flexContext);
        		
                //copy the necessary ActionScript files over for SWF generation 
                createSwcSourceFiles(flexContext.getJsfFlexSwcPath(), FlexConstants.getSwcSourceFiles(), 
                                            FlexConstants.JSF_FLEX_MAIN_SWC_CONFIG_FILE, flexContext.getWebContextPath());
                
                //create the SWC file
                String loadConfigAbsolutePath = flexContext.getJsfFlexSwcPath() + FlexConstants.JSF_FLEX_MAIN_SWC_CONFIGURATIONFILE;
                String swcFileLocationPath = flexContext.getJsfFlexSwcPath() + FlexConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + FlexConstants.SWC_FILE_EXT;
                createSystemSWCFile(flexContext.getJsfFlexSwcPath(), swcFileLocationPath, flexSDKPath, loadConfigAbsolutePath, componentFlex, null);
                
                /*
                 *  copy the necessary swf source files to swfBasePath
                 *  these are files such as xml[s] which are used by the system's/above ActionScripts
                 */
                createSwfSourceFiles(flexContext.getSwfPath(), FlexConstants.getSwfSourceFiles());
                
                /*
                 * unzip the swc's library.swf file and copy it to the swf file for linking with the swf file
                 */
                unZipArchiveAbsolute(new File(swcFileLocationPath), flexContext.getJsfFlexSwcPath(), null);
                
                //copy the library.swf file to swc directory
                copyFileSet(flexContext.getJsfFlexSwcPath(), "**/*.swf", null, flexContext.getSwfPath(), null);
                
                //rename the file from library.swf to jsfFlexMainSwc.swf file
                String sourceFile = flexContext.getSwfPath() + FlexConstants.DEFAULT_SWC_LIBRARY_SWF_NAME;
                String destFile = flexContext.getSwfPath() + FlexConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + FlexConstants.SWF_FILE_EXT;
                
                renameFile(sourceFile, destFile, true);
                deleteResources(sourceFile, false, null);
                
                /*
                 * Now need to place additional SWC content to the correct library path for dynamic linking to the 
                 * to be created SWF file
                 */
                if(componentFlex.getProvidedAdditionalExternalLibaryPath() != null){
                    final CountDownLatch additionalSWCLatch = new CountDownLatch(componentFlex.getProvidedAdditionalExternalLibaryPath().size());
                    final Collection<String> runtimeSharedLibrary = componentFlex.getRuntimeSharedLibraries();
                    final Collection<String> externalLibraryPath = componentFlex.getExternalLibraryPath();
                    
                    final String swcPath = flexContext.getSwcPath();
                    
                    for(final String currSWC : componentFlex.getProvidedAdditionalExternalLibaryPath()){
                        String[] splitted = currSWC.split(String.valueOf(File.separatorChar));
                        if(splitted == null || splitted.length < 1){
                            throw new ComponentBuildException(EXTERNAL_LIBRARY_PATH_COLLECTION_ERROR);
                        }
                        
                        externalLibraryPath.add(currSWC);
                        String tempCurrSwcFileName = splitted[splitted.length - 1];
                        
                        final String currSWCFileName = tempCurrSwcFileName.substring(0, tempCurrSwcFileName.indexOf('.'));
                        final String swcDirectory = swcPath + currSWCFileName + File.separatorChar; 
                        final String waitForQueueTaskIdUnzip = QUEUE_TASK_ID.UNZIP_ARCHIVE_ABSOLUTE_FI.getQueueTaskId(queueTaskId + "_" + currSWCFileName);
                        final String waitForQueueTaskIdCopy = QUEUE_TASK_ID.COPY_FILE_SET.getQueueTaskId(queueTaskId + "_" + currSWCFileName);
                        
                        new Thread(new Runnable(){
                            
                            public void run() {
                                
                                flexTaskRunner.makeDirectory(swcDirectory);
                                commonTaskRunner.unZipArchiveAbsolute(new File(currSWC), swcDirectory, waitForQueueTaskIdUnzip);
                                waitForFutureTask(commonTaskRunner, waitForQueueTaskIdUnzip);
                                /*
                                 * Usually the SWF file within the SWC file is defaulted to library.swf name, but do 
                                 * not assume. Use the file with a SWF extension.
                                 */
                                
                                File fetchLibrarySwfFileName = new File(swcDirectory);
                                List<File> swfLibraryContents = java.util.Arrays.asList(fetchLibrarySwfFileName.listFiles(SWF_LIBRARY_FILENAME_FILTER));
                                /*
                                 * For sanity in case there exists multiple swf library content [should be not, but for future]
                                 */
                                int swfLibraryFileCount = 0;
                                for(File currSwfLibrary : swfLibraryContents){
                                    String originalFileName = currSwfLibrary.getAbsolutePath();
                                    String[] nameSplitted = originalFileName.split(String.valueOf(File.separatorChar));
                                    
                                    /*
                                     * Take the last fileName and rename it with the swc fileName.
                                     * Note that this means that each SWC fileName should be unique, which is an 
                                     * expected requirement.
                                     */
                                    
                                    StringBuilder renameFileName = new StringBuilder();
                                    for(int i=0; i < (nameSplitted.length - 1); i++){
                                        renameFileName.append(nameSplitted[i]);
                                        renameFileName.append(File.separatorChar);
                                    }
                                    
                                    renameFileName.append(currSWCFileName);
                                    renameFileName.append(swfLibraryFileCount++);
                                    renameFileName.append(FlexConstants.SWF_FILE_EXT);
                                    
                                    flexTaskRunner.renameFile(originalFileName, renameFileName.toString(), true);
                                    runtimeSharedLibrary.add(flexContext.getSwfWebPath() + renameFileName.toString());
                                }
                                
                                flexTaskRunner.copyFileSet(swcDirectory, "**/*.swf", null, flexContext.getSwfPath(), waitForQueueTaskIdCopy);
                                waitForFutureTask(flexTaskRunner, waitForQueueTaskIdCopy);
                                flexTaskRunner.deleteResources(swcDirectory, true, null);
                                
                            }
                            
                        }).start();
                        
                    }
                    
                    try{
                        additionalSWCLatch.await();
                    }catch(InterruptedException interruptedExcept){
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            createJsfFlexFlashApplicationConfigurationFile();
            
            final CountDownLatch localeLatch = new CountDownLatch(multiLingualSupportMap.keySet().size());
            /*
             * Additional step of executing copyLocale script for Flex 3.0+.
             * Skip en_US as it is the original source language
             * TODO: Is the below CountDownLatch truly needed [might be more harmful due to context switch and etcetera, oonsider later]
             */
            for(final String currLocale : multiLingualSupportMap.keySet()){
                if(currLocale.equalsIgnoreCase(FlexConstants.EN_US)){
                    localeLatch.countDown();
                    continue;
                }
                
                final String waitForQueueTaskId = QUEUE_TASK_ID.COPY_LOCALE.getQueueTaskId(queueTaskId + "_" + currLocale);
                new Thread(new Runnable(){
                    
                    public void run() {
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
        createSWF(flexFile, componentFlex, flexContext.getFlexSDKPath(), multiLingualSupportMap, flexContext.getLocaleWebContextPath(), queueTaskId);
        
    }
    
    /**
     * Returns the multiLingualSupport Map for this web application.<br>
     * 
     * @return
     */
    public final Map<String, String> getMultiLingualSupportMap(){
        Map<String, String> multiLingualSupportMap = new LinkedHashMap<String, String>();
        
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        String localeWebContextPath = flexContext.getLocaleWebContextPath();
        
        if(localeWebContextPath == null){
            multiLingualSupportMap.put(FlexConstants.DEFAULT_LOCALE_SWF_PATH_KEY, flexContext.getApplicationSwfPath());
        }else{
            String swfBaseName = flexContext.getCurrMxml();
            String swfFileNameBasePath = flexContext.getSwfPath() + swfBaseName + File.separatorChar;
            
            File localeWebContextDirectory = new File(localeWebContextPath);
            if(localeWebContextDirectory.isDirectory()){
                String[] directoryChildren = localeWebContextDirectory.list();
                
                for(String currDirectoryChild : directoryChildren){
                    File currentChild = new File(localeWebContextPath + currDirectoryChild);
                    if(currentChild.isDirectory()){
                        //a locale
                        String locale = currentChild.getName();
                        multiLingualSupportMap.put(locale, swfFileNameBasePath + swfBaseName + FlexConstants.SWF_FILE_NAME_LOCALE_SEPARATOR 
                                                                + locale + FlexConstants.SWF_FILE_EXT);
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
     */
    public final String copyFileSet(String copyDir, String copyInclude, String copyExclude, String copyTo, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.COPY_FILE_SET.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().copyFileSet(copyDir, copyInclude, copyExclude, copyTo, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will flatten the FlexApplicationRenderer preMxml file and copy it as a MXML file to its correct directory,<br>
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
     * @param flexFile
     * @param componentFlex
     * @param flexSDKRootPath
     * @param multiLingualSupportMap
     * @param localeWebContextPath
     * @param queueTaskId
     */
    public final void createSWF(final String flexFile, final IFlexApplicationContract componentFlex, final String flexSDKRootPath, Map<String, String> multiLingualSupportMap, 
                                    String localeWebContextPath, String queueTaskId) {
        
        String defaultLocale = multiLingualSupportMap.get(FlexConstants.DEFAULT_LOCALE_SWF_PATH_KEY);
        
        if(defaultLocale != null){
            
            getFlexTaskRunner().createSWF(flexFile, defaultLocale, componentFlex, flexSDKRootPath, null, null, queueTaskId == null ? null : QUEUE_TASK_ID.CREATE_SWF.getQueueTaskId(queueTaskId));
        }else{
            
            if(queueTaskId == null){
                
                for(String currLocale : multiLingualSupportMap.keySet()){
                    String currLocaleFileName = multiLingualSupportMap.get(currLocale);
                    String currLocaleSourcePath = localeWebContextPath + currLocale + File.separatorChar;
                    
                    getFlexTaskRunner().createSWF(flexFile, currLocaleFileName, componentFlex, flexSDKRootPath, currLocale, currLocaleSourcePath, null);
                }
                
            }else{
                
                /*
                 * TODO: Is the below CountDownLatch truly needed [might be more harmful due to context switch and etcetera, oonsider later]
                 */
                final IFlexTaskRunner flexTaskRunner = getFlexTaskRunner();
                final CountDownLatch createSWFLatch = new CountDownLatch(multiLingualSupportMap.keySet().size());
                for(final String currLocale : multiLingualSupportMap.keySet()){
                    final String currLocaleFileName = multiLingualSupportMap.get(currLocale);
                    final String currLocaleSourcePath = localeWebContextPath + currLocale + File.separatorChar;
                    
                    final String currQueueTaskId = QUEUE_TASK_ID.CREATE_SWF.getQueueTaskId(queueTaskId + "_" + currLocale);
                    
                    new Thread(new Runnable(){
                        
                        public void run(){
                            flexTaskRunner.createSWF(flexFile, currLocaleFileName, componentFlex, flexSDKRootPath, currLocale, currLocaleSourcePath, currQueueTaskId);
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
     */
    public final String copyLocale(String locale, String flexSDKRootPath, String queueTaskId){
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.COPY_LOCALE.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().copyLocale(locale, flexSDKRootPath, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will create the necessary SWC source files. Please refer to flexConstants.xml for the file listings.<br>
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
     * This method will create the necessary source files for the application SWF. Please refer to flexConstants.xml for the file listings.<br>
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
        
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        JsfFlexFlashApplicationConfiguration jsfFlexFlashApplicationConfiguration = flexContext.getJsfFlexFlashApplicationConfiguration();
        
        String filePath = flexContext.getSwfPath() + TO_CREATE_JSF_FLEX_FLASH_APPLICATION_CONFIG_FILE_NAME;
        
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
     */
    public final String createSystemSWCFile(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath, IFlexApplicationContract componentFlex, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.CREATE_SYSTEM_SWC_FILE.getQueueTaskId(queueTaskId);
        getFlexTaskRunner().createSystemSWCFile(sourcePath, outPut, flexSDKRootPath, loadConfigFilePath, componentFlex, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * This method will delete the resource, which should be specified in absolute path.<br>
     * 
     * @param deleteResource
     * @param isDirectory
     * @param queueTaskId
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
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
     * @return generated queueTaskId to be used when invoking ITaskRunner.waitForFutureTask
     */
    public final String unZipArchiveAbsolute(InputStream unZipFile, String unZipDest, String queueTaskId) {
        queueTaskId = queueTaskId == null ? null : QUEUE_TASK_ID.UNZIP_ARCHIVE_ABSOLUTE_IS.getQueueTaskId(queueTaskId);
        getCommonTaskRunner().unZipArchiveAbsolute(unZipFile, unZipDest, queueTaskId);
        return queueTaskId;
    }
    
    /**
     * Generates a file based on the provided template and various properties within the Map.
     * 
     * @param filePath
     * @param templateFile
     * @param initProperties
     * @param tokenMap
     */
    public final void createFileContent(String filePath, String templateFile, Properties initProperties, Map<String, ? extends Object> tokenMap){
        
        getFileManipulatorTaskRunner().createFileContent(filePath, templateFile, initProperties, tokenMap);
    }
    
    /**
     * This method will create the preMxml file of the component.<br>
     * 
     * @param comp
     * @param jsfFlexAttributeProperties
     * @param bodyContent
     */
    public final void createPreMxml(IFlexContract comp, IJsfFlexAttributeProperties jsfFlexAttributeProperties, String bodyContent) {
        
        String fileDirectory = comp.getAbsolutePathToPreMxmlFile().substring(0, comp.getAbsolutePathToPreMxmlFile().lastIndexOf(File.separatorChar));
        getFlexTaskRunner().makeDirectory(fileDirectory);
        
        String componentNameSpace = jsfFlexAttributeProperties.componentNameSpace();
        String providedComponentNameSpaceValue = comp.getNameSpaceOverride();
        if(providedComponentNameSpaceValue != null && providedComponentNameSpaceValue.trim().length() > 0){
            componentNameSpace = providedComponentNameSpaceValue;
        }
        getFileManipulatorTaskRunner().createPreMxmlFile(comp.getAbsolutePathToPreMxmlFile(), null, comp.getAnnotationDocletParserInstance().getTokenValueSet(), jsfFlexAttributeProperties.componentName(), 
                                                            componentNameSpace, bodyContent, childPreMxmlComponentIdentifier(comp), siblingPreMxmlComponentIdentifier(comp));
        
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
     * This method will return ICommonTaskRunner interface from AbstractFlexContext.<br>
     * 
     * @return
     */
    public final ICommonTaskRunner getCommonTaskRunner(){
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        return flexContext.getCommonRunner();
    }
        
    /**
     * This method will return AbstractFileManipulatorTaskRunner interface from AbstractFlexContext.<br>
     * 
     * @return
     */
    public final AbstractFileManipulatorTaskRunner getFileManipulatorTaskRunner(){
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        return flexContext.getFileManipulatorRunner();
    }
    
    /**
     * This method will return IFlexTaskRunner interface from AbstractFlexContext.<br>
     * 
     * @return
     */
    public final IFlexTaskRunner getFlexTaskRunner(){
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        return flexContext.getFlexRunner();
    }
    
}
