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

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author Ji Hoon Kim
 */
public final class MkdirTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "mkdirTask.py";
	
	private static final PyObject _mkdirTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(MkdirTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_mkdirTaskClass = interpreter.get("MkdirTask");
	}
	
	private String _directory;
	
	public MkdirTask(){
		super();
	}
	
	public MkdirTask(String directory){
		_directory = directory;
	}
	
	void build() {
		
		PyObject mkdirTaskObject = _mkdirTaskClass.__call__(new PyString(_directory));
		_jythonTask = _JythonTaskPerformer.class.cast( mkdirTaskObject.__tojava__(_JythonTaskPerformer.class) );
	}
	
	public String toString() {
        StringBuilder content = new StringBuilder();
		content.append("directory [ ");
		content.append(_directory);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public MkdirTask directory(String directory) {
		_directory = directory;
		return this;
	}
	
}
