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

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author Ji Hoon Kim
 */
public final class DeleteTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "deleteTask.py";
	
	private static final PyObject _deleteTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(DeleteTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_deleteTaskClass = interpreter.get("DeleteTask");
	}
	
	private String _deleteResource;
	private boolean _isDirectory;
	
	public DeleteTask(){
		super();
	}
	
	public DeleteTask(String deleteResource, boolean isDirectory){
		super();
		_deleteResource = deleteResource;
		_isDirectory = isDirectory;
	}
	
	void build(){
		
		PyObject deleteTaskObject = _deleteTaskClass.__call__(new PyString(_deleteResource), new PyInteger(_isDirectory ? 1 : 0));
		_jythonTask = (_JythonTaskPerformer) deleteTaskObject.__tojava__(_JythonTaskPerformer.class);
	}
	
	public String toString() {
		StringBuffer content = new StringBuffer();
		content.append("deleteResource [ ");
		content.append(_deleteResource);
		content.append(" ] ");
		content.append("isDirectory [ ");
		content.append(_isDirectory);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public DeleteTask deleteResource(String deleteResource) {
		_deleteResource = deleteResource;
		return this;
	}
	public DeleteTask directory(boolean isDirectory) {
		_isDirectory = isDirectory;
		return this;
	}
	
}
