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

import java.util.List;
import java.util.Vector;

import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author Ji Hoon Kim
 */
public final class FileCopyTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "fileCopyTask.py";
	
	private static final PyObject _fileCopyTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(FileCopyTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_fileCopyTaskClass = interpreter.get("FileCopyTask");
	}
	
	private String _copyFile;
	
	private String _copyDir;
	private List _copyInclude;
	private List _copyExclude;
	
	private String _copyTo;
	
	public FileCopyTask(){
		super();
	}
	
	public FileCopyTask(String copyFile, String copyTo){
		super();
		_copyFile = copyFile;
		_copyTo = copyTo;
	}
	
	public FileCopyTask(String copyDir, List copyInclude, List copyExclude,
						String copyTo){
		super();
		_copyDir = copyDir;
		_copyInclude = copyInclude;
		_copyExclude = copyExclude;
		_copyTo = copyTo;
	}
	
	void build() {
		
		Vector copyDirParameters = new Vector();
		if(_copyDir != null){
			copyDirParameters.add(_copyDir);
			copyDirParameters.add(_copyInclude);
			copyDirParameters.add(_copyExclude);
		}
		
		PyObject fileCopyTaskObject = _fileCopyTaskClass.__call__(new PyString(_copyFile), 
												new PyList(copyDirParameters), new PyString(_copyTo));
		_jythonTask = (_JythonTaskPerformer) fileCopyTaskObject.__tojava__(_JythonTaskPerformer.class);
		
	}
	
	public String toString() {
		StringBuffer content = new StringBuffer();
		content.append("copyFile [ ");
		content.append(_copyFile);
		content.append(" ] ");
		content.append("copyDir [ ");
		content.append(_copyDir);
		content.append(" ] ");
		content.append("copyInclude [ ");
		content.append(_copyInclude);
		content.append(" ] ");
		content.append("copyExclude [ ");
		content.append(_copyExclude);
		content.append(" ] ");
		content.append("copyTo [ ");
		content.append(_copyTo);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public FileCopyTask copyFile(String copyFile) {
		_copyFile = copyFile;
		return this;
	}
	public FileCopyTask copyDir(String copyDir) {
		_copyDir = copyDir;
		return this;
	}
	public FileCopyTask copyExclude(List copyExclude) {
		_copyExclude = copyExclude;
		return this;
	}
	public FileCopyTask copyInclude(List copyInclude) {
		_copyInclude = copyInclude;
		return this;
	}
	public FileCopyTask copyTo(String copyTo) {
		_copyTo = copyTo;
		return this;
	}
	
}
