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
public final class EchoTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "echoTask.py";
	
	private static final PyObject _echoTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(EchoTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_echoTaskClass = interpreter.get("EchoTask");
	}
	
	private String _message;
	private String _file;
	
	public EchoTask(){
		super();
	}
	
	public EchoTask(String message, String file){
		_message = message;
		_file = file;
	}
	
	void build() {
		
		PyObject echoTaskObject = _echoTaskClass.__call__(new PyString(_message), new PyString(_file));
		_jythonTask = (_JythonTaskPerformer) echoTaskObject.__tojava__(_JythonTaskPerformer.class);
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("message [ ");
		content.append(_message);
		content.append(" ] ");
		content.append("file [ ");
		content.append(_file);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public EchoTask file(String file) {
		_file = file;
		return this;
	}
	public EchoTask message(String message) {
		_message = message;
		return this;
	}
	
}
