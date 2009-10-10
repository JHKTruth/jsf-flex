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
public final class RenameTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "renameTask.py";
	
	private static final PyObject _renameTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(RenameTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_renameTaskClass = interpreter.get("RenameTask");
	}
	
	private String _sourceFile;
	private String _destFile;
	private boolean _overwrite;
	
	public RenameTask(){
		super();
	}
	
	public RenameTask(String sourceFile, String destFile, boolean overwrite){
		super();
		_sourceFile = sourceFile;
		_destFile = destFile;
		_overwrite = overwrite;
	}
	
	void build() {
		
		PyObject renameTaskObject = _renameTaskClass.__call__(new PyString(_sourceFile), new PyString(_destFile), 
																	new PyInteger(_overwrite ? 1 : 0));
		_jythonTask = (_JythonTaskPerformer) renameTaskObject.__tojava__(_JythonTaskPerformer.class);
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("sourceFile [ ");
		content.append(_sourceFile);
		content.append(" ] ");
		content.append("destFile [ ");
		content.append(_destFile);
		content.append(" ] ");
		content.append("overwrite [ ");
		content.append(_overwrite);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public RenameTask destFile(String destFile) {
		_destFile = destFile;
		return this;
	}
	public RenameTask overwrite(boolean overwrite) {
		_overwrite = overwrite;
		return this;
	}
	public RenameTask sourceFile(String sourceFile) {
		_sourceFile = sourceFile;
		return this;
	}
	
}
