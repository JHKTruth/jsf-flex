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

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class ChmodTask extends AbstractJythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "commandExecuteTask.py";
	private static final String CHMOD_COMMAND = "chmod";
	
	private static final PyObject _commandExecuteTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(ChmodTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_commandExecuteTaskClass = interpreter.get("CommandExecuteTask");
	}
	
	private File _dir;
	private String _permission;
	private String _fileInclusionRegExp;
	
	public ChmodTask(){
		super();
	}
	
	public ChmodTask(File dir, String permission, String fileInclusionRegExp){
		super();
		
		_dir = dir;
		_permission = permission;
		_fileInclusionRegExp = fileInclusionRegExp;
	}
	
	@Override
	void build() {
		
		Vector<String> commandArguments = getCommandArguments();
		
		PyObject commandExecuteTaskObject = _commandExecuteTaskClass.__call__(new PyString(CHMOD_COMMAND), 
																		new PyList(commandArguments));
		_jythonTask = IJythonTaskPerformer.class.cast( commandExecuteTaskObject.__tojava__(IJythonTaskPerformer.class) );
		
	}
	
	private Vector<String> getCommandArguments(){
		
		Vector<String> commandArguments = new Vector<String>();
		
		commandArguments.add(_permission);
		
		commandArguments.add(_dir.getAbsolutePath());
		
		commandArguments.add(_fileInclusionRegExp);
		
		return commandArguments;
	}
	
	public ChmodTask dir(File dir) {
		_dir = dir;
		return this;
	}
	
	public ChmodTask permission(String permission) {
		_permission = permission;
		return this;
	}
	
	public ChmodTask fileInclusionRegExp(String fileInclusionRegExp) {
		_fileInclusionRegExp = fileInclusionRegExp;
		return this;
	}
	
}
