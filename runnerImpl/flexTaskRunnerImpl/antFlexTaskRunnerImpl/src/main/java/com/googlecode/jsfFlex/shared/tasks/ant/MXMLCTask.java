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
package com.googlecode.jsfFlex.shared.tasks.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline.Argument;

import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public final class MXMLCTask extends AntBaseTask {
	
	private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "mxmlc.exe";
	private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "mxmlc.sh";
	
	private static final String MXMLC_TARGET = "mxmlc_compile";
	
	private static final String ACCESSIBLE = " -accessible=";
	private static final String LOCALE = " -locale ";
	private static final String RUNTIME_SHARED_LIBRARIES = " -runtime-shared-libraries=";
	private static final String EXTERNAL_LIBRARY_PATH = " -external-library-path=";
	private static final String FILE_PROPERTY = "-file-specs ";
	private static final String OUTPUT_ARG_SYNTAX = " -output ";
	private static final String SOURCE_PATH_ARG_SYNTAX = " -source-path ";
	private static final String DEFAULT_BG_COLOR_ARG_SYNTAX = " -default-background-color ";
	private static final String DEFAULT_SCRIPT_LIMIT_ARG_SYNTAX = " -default-script-limits ";
	private static final String INCREMENTAL_ARG_SYNTAX = " -incremental=";
	private static final String LOAD_CONFIG_ARG_SYNTAX = " -load-config+=";
	private static final String TITLE_ARG_SYNTAX = " -title ";
	private static final String DESCRIPTION_ARG_SYNTAX = " -description ";
	private static final String CREATOR_ARG_SYNTAX = " -creator ";
	private static final String PUBLISHER_ARG_SYNTAX = " -publisher ";
	private static final String LANGUAGE_ARG_SYNTAX = " -language ";
	private static final String DATE_ARG_SYNTAX = " -date ";
	
	private final ExecTask _mxmlcTask;
	private final Target _mxmlcTarget;
	
	private final String _file;
	private final String _outputPath;
	private final _MXMLApplicationContract _componentMXML;
	private final String _flexSDKRootPath;
	
	private String _locale;
	private String _localePath;
	
	public MXMLCTask(String file, String outputPath, _MXMLApplicationContract componentMXML, String flexSDKRootPath){
		_file = file;
		_outputPath = outputPath;
		_componentMXML = componentMXML;
		_flexSDKRootPath = flexSDKRootPath;
		
		_mxmlcTarget = new Target();
		_mxmlcTarget.setName(MXMLC_TARGET);
		_mxmlcTarget.setProject(_taskProject);
		_taskProject.addTarget(_mxmlcTarget);
		
		_mxmlcTask = new ExecTask();
		_mxmlcTask.setOwningTarget(_mxmlcTarget);
		_mxmlcTask.setProject(_taskProject);
		_mxmlcTask.setFailonerror(true);
		
		_mxmlcTarget.addTask(_mxmlcTask);
	}
	
	private void setArguments(){
		
		//TODO : Implement it better later
		Argument arg;
		if(MXMLConstants.WINDOWS_SYSTEM){
			_mxmlcTask.setExecutable(_flexSDKRootPath + WINDOWS_EXEC);
		}else{
			_mxmlcTask.setExecutable(_flexSDKRootPath + NON_WINDOWS_SHELL);
		}
		
		arg = _mxmlcTask.createArg();
		arg.setLine(FILE_PROPERTY + MXMLConstants.STRING_QUOTE + _file + MXMLConstants.STRING_QUOTE);
		
		if(_outputPath != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(OUTPUT_ARG_SYNTAX + MXMLConstants.STRING_QUOTE + _outputPath + MXMLConstants.STRING_QUOTE);
		}
		
		if(_componentMXML.isAccessible()){
			arg = _mxmlcTask.createArg();
			arg.setLine(ACCESSIBLE + "true");
		}
		
		if(_locale != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(LOCALE + _locale);
		}
		
		if(_componentMXML.getSourcePath() != null || _localePath != null){
			StringBuilder sourcePathVal = new StringBuilder();
			
			if(_componentMXML.getSourcePath() != null){
				String[] sourcePath = _componentMXML.getSourcePath().split(" ");
				
                for(String currPath : sourcePath){
					sourcePathVal.append(MXMLConstants.STRING_QUOTE);
					sourcePathVal.append(currPath);
					sourcePathVal.append(MXMLConstants.STRING_QUOTE);
					sourcePathVal.append(" ");
				}
			}
			
			if(_localePath != null){
				sourcePathVal.append(MXMLConstants.STRING_QUOTE);
				sourcePathVal.append(_localePath);
				sourcePathVal.append(MXMLConstants.STRING_QUOTE);
			}
			
			arg = _mxmlcTask.createArg();
			arg.setLine(SOURCE_PATH_ARG_SYNTAX + sourcePathVal.toString());
		}
		
		if(_componentMXML.getExternalLibraryPath() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(EXTERNAL_LIBRARY_PATH + MXMLConstants.STRING_QUOTE + _componentMXML.getExternalLibraryPath() + MXMLConstants.STRING_QUOTE);
		}
		
		if(_componentMXML.getRuntimeSharedLibraries() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(RUNTIME_SHARED_LIBRARIES + MXMLConstants.STRING_QUOTE + _componentMXML.getRuntimeSharedLibraries() + MXMLConstants.STRING_QUOTE);
		}
		
		if(_componentMXML.getDefaultBgColor() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(DEFAULT_BG_COLOR_ARG_SYNTAX + _componentMXML.getDefaultBgColor());
		}
		
		if((_componentMXML.getMaxLvRecursion() != null && _componentMXML.getMaxLvRecursion().intValue() > 0) || 
						(_componentMXML.getMaxScriptExecTime() != null && _componentMXML.getMaxScriptExecTime().intValue() > 0)){
			StringBuilder limitVal = new StringBuilder();
			limitVal.append((_componentMXML.getMaxLvRecursion() != null && _componentMXML.getMaxLvRecursion().intValue() <= 0) ? 1000 : 
											_componentMXML.getMaxLvRecursion().intValue());
			limitVal.append(" ");
			limitVal.append(((_componentMXML.getMaxScriptExecTime() == null || _componentMXML.getMaxScriptExecTime().intValue() <= 0) || 
									(_componentMXML.getMaxScriptExecTime() == null || _componentMXML.getMaxScriptExecTime().intValue() > 60)) ? 60 : 
											_componentMXML.getMaxScriptExecTime().intValue());
			arg = _mxmlcTask.createArg();
			arg.setLine(DEFAULT_SCRIPT_LIMIT_ARG_SYNTAX + limitVal.toString());
		}
		
		if(_componentMXML.isIncremental()){
			arg = _mxmlcTask.createArg();
			arg.setValue(INCREMENTAL_ARG_SYNTAX + "true");
		}
		
		if(_componentMXML.getLoadConfig() != null){
			arg = _mxmlcTask.createArg();
			arg.setValue(LOAD_CONFIG_ARG_SYNTAX + MXMLConstants.STRING_QUOTE + _componentMXML.getLoadConfig() + MXMLConstants.STRING_QUOTE);
		}
		
		if(_componentMXML.getTitle() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(TITLE_ARG_SYNTAX + _componentMXML.getTitle());
		}
		
		if(_componentMXML.getDescription() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(DESCRIPTION_ARG_SYNTAX + _componentMXML.getDescription());
		}
		
		if(_componentMXML.getCreator() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(CREATOR_ARG_SYNTAX + _componentMXML.getCreator());
		}
		
		if(_componentMXML.getPublisher() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(PUBLISHER_ARG_SYNTAX + _componentMXML.getPublisher());
		}
		
		if(_componentMXML.getLanguage() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(LANGUAGE_ARG_SYNTAX + _componentMXML.getLanguage());
		}
		
		if(_componentMXML.getDate() != null){
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
			String dateFormatted = format.format(_componentMXML.getDate());
			arg = _mxmlcTask.createArg();
			arg.setLine(DATE_ARG_SYNTAX + dateFormatted);
		}
		
		_mxmlcTask.maybeConfigure();
	}
	
	protected void performTask() {
		
		try {
			
			setArguments();
			buildProject(MXMLC_TARGET);
			
		} catch (BuildException buildException) {
			_taskProject.fireBuildFinished(buildException);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Error in MXMLC's performTask with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), buildException);
		}

	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("file [ ");
		content.append(_file);
		content.append(" ] ");
		content.append("outputPath [ ");
		content.append(_outputPath);
		content.append(" ] ");
		content.append("flexSDKRootPath [ ");
		content.append(_flexSDKRootPath);
		content.append(" ] ");
		content.append("locale [ ");
		content.append(_locale);
		content.append(" ] ");
		content.append("localePath [ ");
		content.append(_localePath);
		content.append(" ] ");
		content.append("accessible [ ");
		content.append(_componentMXML.isAccessible());
		content.append(" ] ");
		content.append("externalLibraryPath [ ");
		content.append(_componentMXML.getExternalLibraryPath());
		content.append(" ] ");
		content.append("runtimeSharedLibraries [ ");
		content.append(_componentMXML.getRuntimeSharedLibraries());
		content.append(" ] ");
		content.append("source_path [");
		if(_componentMXML.getSourcePath() != null){
			String[] sourcePath = _componentMXML.getSourcePath().split(" ");
            for(String currSourcePath : sourcePath){
				content.append(" ");
				content.append(currSourcePath);
			}
		}
		content.append(" ] ");
		content.append("default_bg_color [ ");
		content.append(_componentMXML.getDefaultBgColor());
		content.append(" ] ");
		content.append("max_lv_recursion [ ");
		content.append(_componentMXML.getMaxLvRecursion());
		content.append(" ] ");
		content.append("max_script_exec_time [ ");
		content.append(_componentMXML.getMaxScriptExecTime());
		content.append(" ] ");
		content.append("incremental [ ");
		content.append(_componentMXML.isIncremental());
		content.append(" ] ");
		content.append("load_config [ ");
		content.append(_componentMXML.getLoadConfig());
		content.append(" ] ");
		content.append("title [ ");
		content.append(_componentMXML.getTitle());
		content.append(" ] ");
		content.append("description [ ");
		content.append(_componentMXML.getDescription());
		content.append(" ] ");
		content.append("creator [ ");
		content.append(_componentMXML.getCreator());
		content.append(" ] ");
		content.append("publisher [ ");
		content.append(_componentMXML.getPublisher());
		content.append(" ] ");
		content.append("language [ ");
		content.append(_componentMXML.getLanguage());
		content.append(" ] ");
		content.append("date [ ");
		content.append(_componentMXML.getDate());
		content.append(" ] ");
		return content.toString();
	}

	public MXMLCTask locale(String locale){
		_locale = locale;
		return this;
	}
	public MXMLCTask localePath(String localePath){
		_localePath = localePath;
		return this;
	}
	
}
