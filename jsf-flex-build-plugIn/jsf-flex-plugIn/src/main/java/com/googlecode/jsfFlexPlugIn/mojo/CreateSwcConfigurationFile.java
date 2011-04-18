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
package com.googlecode.jsfFlexPlugIn.mojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.googlecode.jsfFlexPlugIn.inspector.IJsfFlexInspectListener;
import com.googlecode.jsfFlexPlugIn.inspector.AbstractJsfFlexInspectorBase;
import com.googlecode.jsfFlexPlugIn.parser.IJsfFlexParserListener;
import com.googlecode.jsfFlexPlugIn.parser.velocity.JsfFlexVelocityParser;
import com.googlecode.jsfFlexPlugIn.utils.tasks.ReplaceText;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.annotation.AnnotationValue;

import static com.googlecode.jsfFlexPlugIn.utils.JsfFlexBuildPluginConstant.*;
import static com.googlecode.jsfFlexPlugIn.utils.JsfFlexBuildPluginUtil.*;

/**
 * @goal    createSwcConfigurationFile
 * @phase   generate-resources
 * @author Ji Hoon Kim
 */
public final class CreateSwcConfigurationFile extends AbstractMojo 
                                                implements IJsfFlexInspectListener, IJsfFlexParserListener {
    
    /**
     * @parameter expression="${project}"
     */
    private MavenProject _project;
    
    /**
     * @parameter expression="${basedir}/target/classes/com/googlecode/jsfFlex/shared/util"
     */
    private File _toCreateFlexConstantsXMLPath;
    
    /**
     * @parameter expression="src/main/resources/META-INF"
     */
    private File _templateSourceDirectory;
    
    private final Set<SwcActionScriptFile> _swcActionScriptFileSet;
    private final CountDownLatch _mergeCollectionTemplateLatch;
    
    private AbstractJsfFlexInspectorBase _jsfFlexInspector;
    
    public CreateSwcConfigurationFile() {
        super();
        _swcActionScriptFileSet = new LinkedHashSet<SwcActionScriptFile>();
        _mergeCollectionTemplateLatch = new CountDownLatch(3);
    }
    
    public void execute() throws MojoExecutionException, MojoFailureException {
        if(_project.getParent() == null){
        	return;
        }
        
        String currDirPath = String.class.cast( _project.getCompileSourceRoots().get(0) );
        
        _jsfFlexInspector = new AbstractJsfFlexInspectorBase(currDirPath){
            
            private static final String SWC_ACTION_SCRIPT_FILES_ANNOTATION_NOT_FOUND = "com.googlecode.jsfFlex.shared.util.annotation.ISwcActionScriptFiles annotation could not be located";
            private static final String SWC_ACTION_SCRIPT_FILES_ANNOTATION_NAME = "com.googlecode.jsfFlex.shared.util.annotation.ISwcActionScriptFiles";
            
            private static final String ACTION_SCRIPT_FILES_KEY = "actionScriptFiles";
            private static final String ACTION_SCRIPT_FILE_KEY = "actionScriptFile";
            
            @Override
            public void inspectFiles() {
                JavaDocBuilder builder = new JavaDocBuilder();
                builder.addSourceTree(new File(getDirPath()));
                JavaClass flexConstant = builder.getClassByName("com.googlecode.jsfFlex.shared.util.FlexConstants");
                
                Annotation[] qdoxAnnotations = flexConstant.getAnnotations();
                Annotation swcActionScriptFilesAnnotation = null;
                
                if(qdoxAnnotations == null){
                    throw new RuntimeException(SWC_ACTION_SCRIPT_FILES_ANNOTATION_NOT_FOUND);
                }
                
                for(Annotation currAnnotation : qdoxAnnotations){
                    if(currAnnotation.getType().getValue().equals(SWC_ACTION_SCRIPT_FILES_ANNOTATION_NAME)){
                        swcActionScriptFilesAnnotation = currAnnotation;
                        break;
                    }
                }
                
                if(swcActionScriptFilesAnnotation == null){
                    throw new RuntimeException(SWC_ACTION_SCRIPT_FILES_ANNOTATION_NOT_FOUND);
                }
                
                AnnotationValue actionScriptFiles = swcActionScriptFilesAnnotation.getProperty(ACTION_SCRIPT_FILES_KEY);
                
                @SuppressWarnings("unchecked")
                List<Annotation> swcActionScriptFileList = (List<Annotation>) actionScriptFiles.getParameterValue();
                for(Annotation currActionScriptFile : swcActionScriptFileList){
                    _swcActionScriptFileSet.add(new SwcActionScriptFile(removeQuotes( currActionScriptFile.getProperty(ACTION_SCRIPT_FILE_KEY).getParameterValue().toString() )));
                }
                
                inspectionCompleted();
                
            }
            
        };
        
        _jsfFlexInspector.addInspectListener(this);
        
        _jsfFlexInspector.inspectFiles();
        
    }
    
    public void mergeCollectionToTemplateFinished(String fileMerged) {
        ReplaceText removeEmptySpace = new ReplaceText(fileMerged);
        removeEmptySpace.replaceRegExp(true);
        removeEmptySpace.regMatch(ReplaceText.CLEAN_REG_EXP_MATCH);
        removeEmptySpace.regReplace(ReplaceText.CLEAN_REG_EXP_REPLACE_WITH);
        
        removeEmptySpace.performTask();
        
        _mergeCollectionTemplateLatch.countDown();
    }
    
    public void inspectFileFinished(List<Map<String, ? extends Object>> inspectedList, String sourceInspected, String packageName) {
        
    }
    
    public void inspectionCompleted() {
        
        final String SWC_CONFIG_FILE_CLASS_DIRECTORY = "target" + File.separatorChar + "classes" + File.separatorChar + "com" + File.separatorChar 
                                                            + "googlecode" + File.separatorChar + "jsfFlex" + File.separatorChar + "shared" + 
                                                            File.separatorChar + "tasks" + File.separatorChar;
        
        final String ANT_FLEX_TASK_RUNNER_IMPL = File.separatorChar + "runnerImpl" + File.separatorChar + "flexTaskRunnerImpl" + File.separatorChar + 
                                                    "antFlexTaskRunnerImpl" + File.separatorChar + SWC_CONFIG_FILE_CLASS_DIRECTORY;

        final String JYTHON_FLEX_TASK_RUNNER_IMPL = File.separatorChar + "runnerImpl" + File.separatorChar + "flexTaskRunnerImpl" + File.separatorChar + 
                                                        "jythonFlexTaskRunnerImpl" + File.separatorChar + SWC_CONFIG_FILE_CLASS_DIRECTORY;
        
        final String FLEX_CONSTANTS_XML_TEMPLATE_FILE_NAME = "jsf-flex-flexConstants.vm";
        final String JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_TEMPLATE_FILE_NAME = "jsf-flex-jsfFlexMainSwcConfigurationFile.vm";
        
        final String FLEX_CONSTANTS_XML_FILE_NAME = "flexConstants.xml";
        final String JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_FILE_NAME = "jsfFlexMainSwcConfigurationFile.xml";
        
        MavenProject topProject = _project.getParent().getParent();
        String basePath = topProject.getBasedir().getAbsolutePath();
        String antFlexTaskRunnerSwcConfigFilePath = basePath + ANT_FLEX_TASK_RUNNER_IMPL;
        String jythonFlexTaskRunnerSwcConfigFilePath = basePath + JYTHON_FLEX_TASK_RUNNER_IMPL;
        String flexConstantFilePath = _toCreateFlexConstantsXMLPath.getPath();
        
        ExecutorService mergeCollectionTemplatePool = Executors.newFixedThreadPool(3);
        
        try{
            
            Properties velocityParserProperties = new Properties();
            velocityParserProperties.put(FILE_RESOURCE_LOADER_PATH_KEY, _templateSourceDirectory.getPath());
            
            submitMergeContent(mergeCollectionTemplatePool, flexConstantFilePath, FLEX_CONSTANTS_XML_FILE_NAME, 
                                    velocityParserProperties, FLEX_CONSTANTS_XML_TEMPLATE_FILE_NAME);
            submitMergeContent(mergeCollectionTemplatePool, antFlexTaskRunnerSwcConfigFilePath, JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_FILE_NAME, 
                                    velocityParserProperties, JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_TEMPLATE_FILE_NAME);
            submitMergeContent(mergeCollectionTemplatePool, jythonFlexTaskRunnerSwcConfigFilePath, JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_FILE_NAME, 
                                    velocityParserProperties, JSF_FLEX_MAIN_SWC_CONFIGURATION_FILE_XML_TEMPLATE_FILE_NAME);
            try{
                _mergeCollectionTemplateLatch.await();
            }catch(InterruptedException interruptedExcept){
                Thread.currentThread().interrupt();
            }
        }finally {
            if(mergeCollectionTemplatePool != null){
                mergeCollectionTemplatePool.shutdownNow();
            }
        }
        
    }
    
    private void submitMergeContent(ExecutorService mergeCollectionTemplatePool, String fileDirectory, String fileName, 
                                        final Properties velocityParserProperties, final String templateFileName) {
        final String SWC_ACTION_SCRIPT_FILE_SET_ATTRIBUTE = "swcActionScriptFileSet";
        
        checkForDirectoryExistenceOrCreate(fileDirectory);
        final String filePath = fileDirectory + File.separatorChar + fileName;
        mergeCollectionTemplatePool.submit(new FutureTask<Void>(new Runnable(){
                                                public void run() {
                                                    FileWriter writer = null;
                                                    try{
                                                        writer = new FileWriter(new File(filePath));
                                                    }catch(IOException ioException){
                                                        throw new RuntimeException("Error while opening FileWriter for : " + filePath);
                                                    }
                                                    Map<String, Object> contextInfoMap = new HashMap<String, Object>();
                                                    contextInfoMap.put(SWC_ACTION_SCRIPT_FILE_SET_ATTRIBUTE, _swcActionScriptFileSet);
                                                    
                                                    JsfFlexVelocityParser jsfFlexVelocityParser = new JsfFlexVelocityParser(velocityParserProperties);
                                                    jsfFlexVelocityParser.init();
                                                    jsfFlexVelocityParser.addParserListener(CreateSwcConfigurationFile.this);
                                                    
                                                    jsfFlexVelocityParser.mergeCollectionToTemplate(templateFileName, contextInfoMap, 
                                                            writer, filePath);
                                                }
                                            }, null));
    }
    
    public static final class SwcActionScriptFile {
        
        private static final String ACTIONSCRIPT_DIR_NAME = "com/googlecode/jsfFlex/shared/actionScript/";
        
        private final String _actionScriptFile;
        
        private SwcActionScriptFile(String actionScriptFile){
            super();
            _actionScriptFile = actionScriptFile;
        }
        
        public String getActionScriptFile() {
            return _actionScriptFile;
        }
        public String getActionScriptFileForJarAccess() {
            return ACTIONSCRIPT_DIR_NAME + _actionScriptFile.replace('.', '/') + AS_FILE_EXT;
        }
        
        @Override
        public boolean equals(Object instance) {
            if(!(instance instanceof SwcActionScriptFile)){
                return false;
            }
            
            SwcActionScriptFile swcActionScriptFileInstance = SwcActionScriptFile.class.cast( instance );
            return _actionScriptFile.equals( swcActionScriptFileInstance._actionScriptFile );
        }
        
        @Override
        public int hashCode() {
            return _actionScriptFile.hashCode();
        }
        
    }
    
}
