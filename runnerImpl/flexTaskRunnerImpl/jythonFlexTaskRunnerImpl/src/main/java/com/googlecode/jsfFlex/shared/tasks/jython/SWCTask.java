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
import java.util.Vector;

import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public final class SWCTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "commandExecuteTask.py";
	
	private static final PyObject _commandExecuteTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(SWCTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_commandExecuteTaskClass = interpreter.get("CommandExecuteTask");
	}
	
	private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "compc.exe";
	private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "compc.sh";
	
	private static final String SOURCE_PATH = " -source-path ";
	private static final String OUTPUT = " -output ";
	private static final String LOAD_CONFIG_ARG_SYNTAX = " -load-config+=";
	
	private String _sourcePath;
	private String _outPut;
	private String _loadConfig;
	private String _flexSDKRootPath;
	
	public SWCTask(){
		super();
	}
	
	public SWCTask(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath){
		super();
		_sourcePath = sourcePath;
		_outPut = outPut;
		_loadConfig = loadConfigFilePath;
		_flexSDKRootPath = flexSDKRootPath;
	}
	
	void build() {
		
		String commandToExecute = MXMLConstants.WINDOWS_SYSTEM ? _flexSDKRootPath + WINDOWS_EXEC : _flexSDKRootPath + NON_WINDOWS_SHELL;
		Vector<String> commandArguments = getCommandArguments();
		
		PyObject commandExecuteTaskObject = _commandExecuteTaskClass.__call__(new PyString(commandToExecute), 
																		new PyList(commandArguments));
		_jythonTask = _JythonTaskPerformer.class.cast( commandExecuteTaskObject.__tojava__(_JythonTaskPerformer.class) );
	}
	
	private Vector<String> getCommandArguments(){
		
		Vector<String> commandArguments = new Vector<String>();
		
		commandArguments.add(SOURCE_PATH + MXMLConstants.STRING_QUOTE + _sourcePath + MXMLConstants.STRING_QUOTE);
		commandArguments.add(OUTPUT + MXMLConstants.STRING_QUOTE + _outPut + MXMLConstants.STRING_QUOTE);
		
		if(_loadConfig != null){
			commandArguments.add(LOAD_CONFIG_ARG_SYNTAX + MXMLConstants.STRING_QUOTE + _loadConfig + MXMLConstants.STRING_QUOTE);
		}
		
		return commandArguments;
	}
	
	public String toString(){
		StringBuilder content = new StringBuilder();
		content.append("source [ ");
		content.append(_sourcePath);
		content.append(" ] ");
		content.append("outPut [ ");
		content.append(_outPut);
		content.append(" ] ");
		content.append("loadConfig [ ");
		content.append(_loadConfig);
		content.append(" ] ");
		content.append(" flexSDKRootPath [ ");
		content.append(_flexSDKRootPath);
		content.append(" ] ");
		return content.toString();
	}
	
	public SWCTask loadConfig(String loadConfig) {
		_loadConfig = loadConfig;
		return this;
	}
	public SWCTask outPut(String outPut) {
		_outPut = outPut;
		return this;
	}
	public SWCTask sourcePath(String sourcePath) {
		_sourcePath = sourcePath;
		return this;
	}
	public SWCTask flexSDKRootPath(String flexSDKRootPath) {
		_flexSDKRootPath = flexSDKRootPath;
		return this;
	}
	
}
