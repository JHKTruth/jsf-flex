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
package com.googlecode.jsfFlex.shared.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.python.util.PythonInterpreter;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.tasks.jython.ChmodTask;
import com.googlecode.jsfFlex.shared.tasks.jython.CopyLocaleTask;
import com.googlecode.jsfFlex.shared.tasks.jython.DeleteTask;
import com.googlecode.jsfFlex.shared.tasks.jython.EchoTask;
import com.googlecode.jsfFlex.shared.tasks.jython.FileCopyTask;
import com.googlecode.jsfFlex.shared.tasks.jython.MXMLCTask;
import com.googlecode.jsfFlex.shared.tasks.jython.MkdirTask;
import com.googlecode.jsfFlex.shared.tasks.jython.RenameTask;
import com.googlecode.jsfFlex.shared.tasks.jython.ReplaceTextTask;
import com.googlecode.jsfFlex.shared.tasks.jython.SWCTask;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * An implementation of IFlexTaskRunner using Jython.<br>
 * 
 * PYTHON_HOME must be set to the correct path and can be set within the web.xml<br>
 * as python.home init parameter or can be passed as JVM python.home parameter<br>
 * 
 * @author Ji Hoon Kim
 */
final class JythonFlexTaskRunnerImpl extends TaskRunnerImpl implements IFlexTaskRunner {
	
	private final static Log _log = LogFactory.getLog(JythonFlexTaskRunnerImpl.class);
	
	private static final String ACTION_SCRIPT_DIR_NAME = "actionScript";
	private static final String WEB_CONSTANTS_AS_FILE_NAME = File.separatorChar + "WebConstants.as";
	private static final String WEB_CONTEXT_PATH_TOKEN = "{webContextPath}";
	private static final String PYTHON_HOME = "python.home";
	
	static{
		
		String pythonHome = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		pythonHome = context.getExternalContext().getInitParameter(PYTHON_HOME);
		
		if(pythonHome == null){
			pythonHome = System.getProperty(PYTHON_HOME);
		}
		
		try{
			
			if(pythonHome != null){
				Properties initProperties = new Properties();
				initProperties.put(PYTHON_HOME, pythonHome);
				PythonInterpreter.initialize(System.getProperties(), initProperties, null);
				
				_log.debug("Successfully set python.home to " + pythonHome);
			}
			
		}catch(Exception jythonInitializationException){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Error encountered when trying to set python.home to");
			errorMessage.append(pythonHome);
			throw new RuntimeException(jythonInitializationException);
		}
		
	}
	
	JythonFlexTaskRunnerImpl(){
		super();
	}
	
	public void chmod(File directory, String permission, String fileInclusionRegExp, String queueTaskId) {
		ChmodTask chmodDirectory = new ChmodTask(directory, permission, fileInclusionRegExp);
		if(queueTaskId != null){
            queueFutureTask(queueTaskId, chmodDirectory);
        }else{
            addTask(chmodDirectory);
        }
	}
	
	public void copyFile(String fileToCopy, String fileToCopyTo, String queueTaskId) {
		FileCopyTask fileCopier = new FileCopyTask(fileToCopy, fileToCopyTo);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, fileCopier);
        }else{
            addTask(fileCopier);
        }
	}
	
	public void copyFileSet(String copyDir, String copyInclude, String copyExclude, String copyTo, String queueTaskId) {
		List<String> copyIncludeList = copyInclude == null ? new LinkedList<String>() : Arrays.asList(copyInclude.split(" "));
		List<String> copyExcludeList = copyExclude == null ? new LinkedList<String>() : Arrays.asList(copyExclude.split(" "));
		FileCopyTask fileCopier = new FileCopyTask(copyDir, copyIncludeList, copyExcludeList, copyTo);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, fileCopier);
        }else{
            addTask(fileCopier);
        }
	}
	
	public void createMXML(String targetAbsolutePath, String copyTo) {
		//TODO : Implement this better later
		ReplaceTextTask removeEmptySpace = new ReplaceTextTask(targetAbsolutePath);
		removeEmptySpace.addTokenValue(ReplaceTextTask.CLEAN_REG_EXP_MATCH, ReplaceTextTask.CLEAN_REG_EXP_REPLACE_WITH);
		
		addTask(removeEmptySpace);
		
		copyFile(targetAbsolutePath, copyTo, null);
	}
	
	public void createSWF(String flexFile, String swfPath, IFlexApplicationContract componentFlex, String flexSDKRootPath, String locale, String localePath, String queueTaskId) {
		MXMLCTask swfCreator = new MXMLCTask(flexFile, swfPath, componentFlex, flexSDKRootPath).locale(locale).localePath(localePath);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, swfCreator);
        }else{
            addTask(swfCreator);
        }
	}
    
    public void copyLocale(String locale, String flexSDKRootPath, String queueTaskId){
        CopyLocaleTask copyLocale = new CopyLocaleTask(locale, flexSDKRootPath);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, copyLocale);
        }else{
            addTask(copyLocale);
        }
    }
	
	public void createSwcSourceFiles(String swcPath, List<String> systemSourceFiles, String jsfFlexMainSwcConfigFile, String webContextPath) {
		//Echo the sourceFiles to the SWC path
		
		/*
		 * TODO : implement it better later
		 */
		EchoTask toEcho = new EchoTask(null, null);
		for(String currSystemSource : systemSourceFiles){
			String[] currSplit = currSystemSource.split("/");
			StringBuilder path = new StringBuilder();
			
			/*
			 * This is a pure HACK, implement it better later
			 * The path of ActionScript files must be of com/googlecode/jsfFlex/util/shared/actionScript
			 */
			String pathToFile = currSystemSource.substring(currSystemSource.indexOf(ACTION_SCRIPT_DIR_NAME) + 13);
			if(pathToFile == null || pathToFile.length() == 0){
				_log.debug("The source file [" + currSystemSource + "] is null or the length is zero");
				continue;
			}
			//remove the last element [name of file]
			pathToFile = pathToFile.substring(0, pathToFile.lastIndexOf("/"));
			
			for(String fileValue : Arrays.asList(pathToFile.split("/"))){
				path.append(fileValue);
				path.append(File.separatorChar);
			}
			makeDirectory(swcPath + path.toString());
			String fileName = swcPath + path.toString() + currSplit[currSplit.length-1];
			
			toEcho.file(fileName);
			toEcho.message(getFileManipulatorTaskRunner().getComponentTemplate(getClass().getClassLoader(), currSystemSource));
			addTask(toEcho);
			
			/*
			 * Need to replace WEB_CONTEXT_PATH_TOKEN with the correct value.
			 * TODO : implement this better later
			 */
			if(fileName.indexOf(WEB_CONSTANTS_AS_FILE_NAME) > 0){
				ReplaceTextTask replaceWebContextPath = new ReplaceTextTask(fileName);
				replaceWebContextPath.addTokenValue(WEB_CONTEXT_PATH_TOKEN, webContextPath);
				addTask(replaceWebContextPath);
			}
			
		}
		
		//now flush out the swc config file
		String jsfFlexMainSwcConfigFileName = swcPath + jsfFlexMainSwcConfigFile.substring(jsfFlexMainSwcConfigFile.lastIndexOf("/") + 1);
		
		toEcho.file(jsfFlexMainSwcConfigFileName);
		toEcho.message(getFileManipulatorTaskRunner().getComponentTemplate(getClass().getClassLoader(), jsfFlexMainSwcConfigFile));
		addTask(toEcho);
		
	}
	
	public void createSwfSourceFiles(String swfBasePath, List<String> systemSwfSourceFiles) {
		
		MkdirTask swfBasePathDirCreator = new MkdirTask(swfBasePath);
		addTask(swfBasePathDirCreator);
		
		//Echo the swf sourceFiles to the swfBasepath
		
		/*
		 * TODO : implement it better later
		 */
		EchoTask toEcho = new EchoTask(null, null);
		for(String currSystemSwfSourceFile : systemSwfSourceFiles){
			String[] currSplit = currSystemSwfSourceFile.split("/");
			String fileName = swfBasePath + currSplit[currSplit.length-1];
			
			toEcho.file(fileName);
			toEcho.message(getFileManipulatorTaskRunner().getComponentTemplate(getClass().getClassLoader(), currSystemSwfSourceFile));
			addTask(toEcho);
		}
	}
	
	public void createSystemSWCFile(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath, IFlexApplicationContract componentFlex, String queueTaskId) {
		SWCTask swcCreate = new SWCTask(sourcePath, outPut, flexSDKRootPath, loadConfigFilePath, componentFlex);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, swcCreate);
        }else{
            addTask(swcCreate);
        }
	}
	
	public void deleteResources(String resourceToDelete, boolean isDirectory, String queueTaskId) {
		DeleteTask deleteResourceTask = new DeleteTask(resourceToDelete, isDirectory);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, deleteResourceTask);
        }else{
            addTask(deleteResourceTask);
        }
	}
	
	public void makeDirectory(String directoryToCreate) {
		MkdirTask preMxmlDirCreator = new MkdirTask(directoryToCreate);
        addTask(preMxmlDirCreator);
	}
	
	public void renameFile(String sourceFile, String destFile, boolean overWrite) {
		RenameTask rename = new RenameTask(sourceFile, destFile, overWrite);
        addTask(rename);
	}
	
	public void replaceTokenWithValue(String targetAbsolutePath, String valueToReplaceWith, String tokenReplace) {
		
		ReplaceTextTask addUIComponentTemplate = new ReplaceTextTask(targetAbsolutePath);
		addUIComponentTemplate.addTokenValue(tokenReplace, valueToReplaceWith);
        addTask(addUIComponentTemplate);
	}
	
	public void writeBodyContent(IFlexContract componentFlex) {
		
		Object stringBodyContent = componentFlex.getAttributes().get(FlexConstants.TAG_BODY_CONTENT_ATTR);
		String stringBodyContentToReplace = stringBodyContent == null ? "" : (String) stringBodyContent;
		ReplaceTextTask writeBodyContent = new ReplaceTextTask(componentFlex.getAbsolutePathToPreMxmlFile());
		writeBodyContent.addTokenValue(FlexConstants.TAG_BODY_CONTENT_TOKEN, stringBodyContentToReplace);
        addTask(writeBodyContent);
	}
	
	public final AbstractFileManipulatorTaskRunner getFileManipulatorTaskRunner(){
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
		return flexContext.getFileManipulatorRunner();
	}
	
}
