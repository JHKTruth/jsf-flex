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
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline.Argument;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class MXMLCTask extends AbstractAntBaseTask {
	
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
    private static final String OPTIMIZE = " -compiler.optimize ";
	
	private final ExecTask _mxmlcTask;
	private final Target _mxmlcTarget;
	
	private final String _file;
	private final String _outputPath;
	private final IFlexApplicationContract _componentFlex;
	private final String _flexSDKRootPath;
	
	private String _locale;
	private String _localePath;
	
	public MXMLCTask(String file, String outputPath, IFlexApplicationContract componentFlex, String flexSDKRootPath){
		_file = file;
		_outputPath = outputPath;
		_componentFlex = componentFlex;
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
		if(FlexConstants.WINDOWS_SYSTEM){
			_mxmlcTask.setExecutable(_flexSDKRootPath + WINDOWS_EXEC);
		}else{
			_mxmlcTask.setExecutable(_flexSDKRootPath + NON_WINDOWS_SHELL);
		}
		
		arg = _mxmlcTask.createArg();
		arg.setLine(FILE_PROPERTY + FlexConstants.STRING_QUOTE + _file + FlexConstants.STRING_QUOTE);
		
        arg = _mxmlcTask.createArg();
        arg.setLine(OPTIMIZE);
        
		if(_outputPath != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(OUTPUT_ARG_SYNTAX + FlexConstants.STRING_QUOTE + _outputPath + FlexConstants.STRING_QUOTE);
		}
		
		if(_componentFlex.isAccessible()){
			arg = _mxmlcTask.createArg();
			arg.setLine(ACCESSIBLE + "true");
		}
		
		if(_locale != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(LOCALE + _locale);
		}
		
		if(_componentFlex.getSourcePath() != null || _localePath != null){
			StringBuilder sourcePathVal = new StringBuilder();
			
			if(_componentFlex.getSourcePath() != null){
                
				for(String currPath : _componentFlex.getSourcePath()){
					sourcePathVal.append(FlexConstants.STRING_QUOTE);
					sourcePathVal.append(currPath);
					sourcePathVal.append(FlexConstants.STRING_QUOTE);
					sourcePathVal.append(" ");
				}
			}
			
			if(_localePath != null){
				sourcePathVal.append(FlexConstants.STRING_QUOTE);
				sourcePathVal.append(_localePath);
				sourcePathVal.append(FlexConstants.STRING_QUOTE);
			}
			
			arg = _mxmlcTask.createArg();
			arg.setLine(SOURCE_PATH_ARG_SYNTAX + sourcePathVal.toString());
		}
		
		if(_componentFlex.getExternalLibraryPath() != null){
			arg = _mxmlcTask.createArg();
            
            StringBuilder externalLibraryPath = new StringBuilder();
            for(String currExternalLibraryPath : _componentFlex.getExternalLibraryPath()){
                externalLibraryPath.append(FlexConstants.STRING_QUOTE);
                externalLibraryPath.append(currExternalLibraryPath);
                externalLibraryPath.append(FlexConstants.STRING_QUOTE);
                externalLibraryPath.append(" ");
            }
			arg.setLine(EXTERNAL_LIBRARY_PATH + externalLibraryPath.toString());
		}
		
		if(_componentFlex.getRuntimeSharedLibraries() != null){
			arg = _mxmlcTask.createArg();
            
            StringBuilder runtimeSharedLibrary = new StringBuilder();
            for(String currRuntimeSharedLibrary : _componentFlex.getRuntimeSharedLibraries()){
                runtimeSharedLibrary.append(FlexConstants.STRING_QUOTE);
                runtimeSharedLibrary.append(currRuntimeSharedLibrary);
                runtimeSharedLibrary.append(FlexConstants.STRING_QUOTE);
                runtimeSharedLibrary.append(" ");
            }
			arg.setLine(RUNTIME_SHARED_LIBRARIES + runtimeSharedLibrary.toString());
		}
		
		if(_componentFlex.getDefaultBgColor() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(DEFAULT_BG_COLOR_ARG_SYNTAX + _componentFlex.getDefaultBgColor());
		}
		
		if((_componentFlex.getMaxLvRecursion() != null && _componentFlex.getMaxLvRecursion().intValue() > 0) || 
						(_componentFlex.getMaxScriptExecTime() != null && _componentFlex.getMaxScriptExecTime().intValue() > 0)){
			StringBuilder limitVal = new StringBuilder();
			limitVal.append((_componentFlex.getMaxLvRecursion() != null && _componentFlex.getMaxLvRecursion().intValue() <= 0) ? 1000 : 
                                _componentFlex.getMaxLvRecursion().intValue());
			limitVal.append(" ");
			limitVal.append(((_componentFlex.getMaxScriptExecTime() == null || _componentFlex.getMaxScriptExecTime().intValue() <= 0) || 
									(_componentFlex.getMaxScriptExecTime() == null || _componentFlex.getMaxScriptExecTime().intValue() > 60)) ? 60 : 
                                        _componentFlex.getMaxScriptExecTime().intValue());
			arg = _mxmlcTask.createArg();
			arg.setLine(DEFAULT_SCRIPT_LIMIT_ARG_SYNTAX + limitVal.toString());
		}
		
		if(_componentFlex.isIncremental()){
			arg = _mxmlcTask.createArg();
			arg.setValue(INCREMENTAL_ARG_SYNTAX + "true");
		}
		
		if(_componentFlex.getLoadConfig() != null){
			arg = _mxmlcTask.createArg();
			arg.setValue(LOAD_CONFIG_ARG_SYNTAX + FlexConstants.STRING_QUOTE + _componentFlex.getLoadConfig() + FlexConstants.STRING_QUOTE);
		}
		
		if(_componentFlex.getTitle() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(TITLE_ARG_SYNTAX + _componentFlex.getTitle());
		}
		
		if(_componentFlex.getDescription() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(DESCRIPTION_ARG_SYNTAX + _componentFlex.getDescription());
		}
		
		if(_componentFlex.getCreator() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(CREATOR_ARG_SYNTAX + _componentFlex.getCreator());
		}
		
		if(_componentFlex.getPublisher() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(PUBLISHER_ARG_SYNTAX + _componentFlex.getPublisher());
		}
		
		if(_componentFlex.getLanguage() != null){
			arg = _mxmlcTask.createArg();
			arg.setLine(LANGUAGE_ARG_SYNTAX + _componentFlex.getLanguage());
		}
		
		if(_componentFlex.getDate() != null){
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
			String dateFormatted = format.format(_componentFlex.getDate());
			arg = _mxmlcTask.createArg();
			arg.setLine(DATE_ARG_SYNTAX + dateFormatted);
		}
        
        Map<String, String> additionalMxmlcCommandArgs = _componentFlex.getAdditionalMxmlcCommandArguments();
        if(additionalMxmlcCommandArgs != null){
            for(String currKey : additionalMxmlcCommandArgs.keySet()){
                arg = _mxmlcTask.createArg();
                arg.setLine(currKey + additionalMxmlcCommandArgs.get(currKey));
            }
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
		content.append(_componentFlex.isAccessible());
		content.append(" ] ");
		content.append("externalLibraryPath [ ");
		content.append(_componentFlex.getExternalLibraryPath());
		content.append(" ] ");
		content.append("runtimeSharedLibraries [ ");
		content.append(_componentFlex.getRuntimeSharedLibraries());
		content.append(" ] ");
		content.append("source_path [");
		if(_componentFlex.getSourcePath() != null){
			
            for(String currSourcePath : _componentFlex.getSourcePath()){
				content.append(" ");
				content.append(currSourcePath);
			}
		}
		content.append(" ] ");
		content.append("default_bg_color [ ");
		content.append(_componentFlex.getDefaultBgColor());
		content.append(" ] ");
		content.append("max_lv_recursion [ ");
		content.append(_componentFlex.getMaxLvRecursion());
		content.append(" ] ");
		content.append("max_script_exec_time [ ");
		content.append(_componentFlex.getMaxScriptExecTime());
		content.append(" ] ");
		content.append("incremental [ ");
		content.append(_componentFlex.isIncremental());
		content.append(" ] ");
		content.append("load_config [ ");
		content.append(_componentFlex.getLoadConfig());
		content.append(" ] ");
		content.append("title [ ");
		content.append(_componentFlex.getTitle());
		content.append(" ] ");
		content.append("description [ ");
		content.append(_componentFlex.getDescription());
		content.append(" ] ");
		content.append("creator [ ");
		content.append(_componentFlex.getCreator());
		content.append(" ] ");
		content.append("publisher [ ");
		content.append(_componentFlex.getPublisher());
		content.append(" ] ");
		content.append("language [ ");
		content.append(_componentFlex.getLanguage());
		content.append(" ] ");
		content.append("date [ ");
		content.append(_componentFlex.getDate());
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
