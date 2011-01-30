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
package com.googlecode.jsfFlex.shared.tasks.jython;

import java.io.File;
import java.util.Map;
import java.util.Vector;

import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class MXMLCTask extends AbstractJythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "commandExecuteTask.py";
	
	private static final PyObject _commandExecuteTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(MXMLCTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_commandExecuteTaskClass = interpreter.get("CommandExecuteTask");
	}
	
	private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "mxmlc.exe";
	private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "mxmlc.sh";
	
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
	
	private String _file;
	private String _outputPath;
	private IFlexApplicationContract _componentFlex;
	private String _flexSDKRootPath;
	
	private String _locale;
	private String _localePath;
	
	public MXMLCTask(){
		super();
	}
	
	public MXMLCTask(String file, String outputpath, IFlexApplicationContract componentFlex, String flexSDKRootPath){
		_file = file;
		_outputPath = outputpath;
        _componentFlex = componentFlex;
		_flexSDKRootPath = flexSDKRootPath;
	}
	
	void build() {
		
		String commandToExecute = FlexConstants.WINDOWS_SYSTEM ? _flexSDKRootPath + WINDOWS_EXEC : _flexSDKRootPath + NON_WINDOWS_SHELL;
		Vector<String> commandArguments = getCommandArguments();
		
		PyObject commandExecuteTaskObject = _commandExecuteTaskClass.__call__(new PyString(commandToExecute), 
																		new PyList(commandArguments));
		_jythonTask = IJythonTaskPerformer.class.cast( commandExecuteTaskObject.__tojava__(IJythonTaskPerformer.class) );
	}
	
	private Vector<String> getCommandArguments(){
		
		Vector<String> commandArguments = new Vector<String>();
		
		commandArguments.add(FILE_PROPERTY + FlexConstants.STRING_QUOTE + _file + FlexConstants.STRING_QUOTE);
		commandArguments.add(OPTIMIZE);
        
		if(_outputPath != null){
			commandArguments.add(OUTPUT_ARG_SYNTAX + FlexConstants.STRING_QUOTE + _outputPath + FlexConstants.STRING_QUOTE);
		}
		
		if(_componentFlex.isAccessible()){
			commandArguments.add(ACCESSIBLE + "true");
		}
		
		if(_locale != null){
			commandArguments.add(LOCALE + _locale);
		}
		
		if(_componentFlex.getSourcePath() != null || _localePath != null){
			StringBuilder sourcePathVal = new StringBuilder();
			
			if(_componentFlex.getSourcePath() != null){
				
                for(String currSourcePath : _componentFlex.getSourcePath()){
					sourcePathVal.append(FlexConstants.STRING_QUOTE);
					sourcePathVal.append(currSourcePath);
					sourcePathVal.append(FlexConstants.STRING_QUOTE);
					sourcePathVal.append(" ");
				}
			}
			
			if(_localePath != null){
				sourcePathVal.append(FlexConstants.STRING_QUOTE);
				sourcePathVal.append(_localePath);
				sourcePathVal.append(FlexConstants.STRING_QUOTE);
			}
			
			commandArguments.add(SOURCE_PATH_ARG_SYNTAX + sourcePathVal.toString());
		}
		
		if(_componentFlex.getExternalLibraryPath() != null){
            
            StringBuilder externalLibraryPath = new StringBuilder();
            for(String currExternalLibraryPath : _componentFlex.getExternalLibraryPath()){
                externalLibraryPath.append(FlexConstants.STRING_QUOTE);
                externalLibraryPath.append(currExternalLibraryPath);
                externalLibraryPath.append(FlexConstants.STRING_QUOTE);
                externalLibraryPath.append(" ");
            }
			commandArguments.add(EXTERNAL_LIBRARY_PATH + externalLibraryPath.toString());
		}
		
		if(_componentFlex.getRuntimeSharedLibraries() != null){
            
            StringBuilder runtimeSharedLibrary = new StringBuilder();
            for(String currRuntimeSharedLibrary : _componentFlex.getRuntimeSharedLibraries()){
                runtimeSharedLibrary.append(FlexConstants.STRING_QUOTE);
                runtimeSharedLibrary.append(currRuntimeSharedLibrary);
                runtimeSharedLibrary.append(FlexConstants.STRING_QUOTE);
                runtimeSharedLibrary.append(" ");
            }
			commandArguments.add(RUNTIME_SHARED_LIBRARIES + runtimeSharedLibrary.toString());
		}
		
		if(_componentFlex.getDefaultBgColor() != null){
			commandArguments.add(DEFAULT_BG_COLOR_ARG_SYNTAX + _componentFlex.getDefaultBgColor());
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
			commandArguments.add(DEFAULT_SCRIPT_LIMIT_ARG_SYNTAX + limitVal.toString());
		}
		
		if(_componentFlex.isIncremental()){
			commandArguments.add(INCREMENTAL_ARG_SYNTAX + "true");
		}
		
		if(_componentFlex.getLoadConfig() != null){
			commandArguments.add(LOAD_CONFIG_ARG_SYNTAX + FlexConstants.STRING_QUOTE + _componentFlex.getLoadConfig() + FlexConstants.STRING_QUOTE);
		}
		
		if(_componentFlex.getTitle() != null){
			commandArguments.add(TITLE_ARG_SYNTAX + _componentFlex.getTitle());
		}
		
		if(_componentFlex.getDescription() != null){
			commandArguments.add(DESCRIPTION_ARG_SYNTAX + _componentFlex.getDescription());
		}
		
		if(_componentFlex.getCreator() != null){
			commandArguments.add(CREATOR_ARG_SYNTAX + _componentFlex.getCreator());
		}
		
		if(_componentFlex.getPublisher() != null){
			commandArguments.add(PUBLISHER_ARG_SYNTAX + _componentFlex.getPublisher());
		}
		
		if(_componentFlex.getLanguage() != null){
			commandArguments.add(LANGUAGE_ARG_SYNTAX + _componentFlex.getLanguage());
		}
		
		if(_componentFlex.getDate() != null){
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
			String dateFormatted = format.format(_componentFlex.getDate());
			commandArguments.add(DATE_ARG_SYNTAX + dateFormatted);
		}
        
        Map<String, String> additionalMxmlcCommandArgs = _componentFlex.getAdditionalMxmlcCommandArguments();
        if(additionalMxmlcCommandArgs != null){
            for(String currKey : additionalMxmlcCommandArgs.keySet()){
                commandArguments.add(currKey + additionalMxmlcCommandArgs.get(currKey));
            }
        }
		
		return commandArguments;
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
