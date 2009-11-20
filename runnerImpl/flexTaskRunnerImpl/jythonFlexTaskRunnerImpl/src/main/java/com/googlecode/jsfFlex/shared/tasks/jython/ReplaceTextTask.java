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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.python.core.PyDictionary;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author Ji Hoon Kim
 */
public final class ReplaceTextTask extends _JythonBaseTask {
	
	private static final String PYTHON_EXECUTION_FILE = "replaceTextTask.py";
	
	private static final PyObject _replaceTextTaskClass;
	
	static{
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(ReplaceTextTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
		_replaceTextTaskClass = interpreter.get("ReplaceTextTask");
	}
	
	//make the below reg exp better later
	public static final String CLEAN_REG_EXP_MATCH = "\\s{2,}";
	public static final String CLEAN_REG_EXP_REPLACE_WITH = System.getProperty("line.separator");
	
	private final Map<String, String> _replaceDictionary;
	
	private String _file;
	private boolean _replaceAllOccurrence;
	
	public ReplaceTextTask(){
		super();
	}
	
	public ReplaceTextTask(String file){
		_file = file;
	}
	
	{
		_replaceDictionary = new HashMap<String, String>();
		_replaceAllOccurrence = true;
	}
	
	public void addTokenValue(String token, String value){
		_replaceDictionary.put(token, value);
	}
	
	void build() {
		
		PyDictionary pyDictionary = new PyDictionary(new Hashtable<String, String>(_replaceDictionary));
		
		PyObject replaceTextTaskObject = _replaceTextTaskClass.__call__(new PyString(_file), pyDictionary, new PyInteger(_replaceAllOccurrence ? 0 : 1));
		_jythonTask = _JythonTaskPerformer.class.cast( replaceTextTaskObject.__tojava__(_JythonTaskPerformer.class) );
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("file [ ");
		content.append(_file);
		content.append(" ] ");
		content.append("replaceAllOccurrence [ ");
		content.append(_replaceAllOccurrence);
		content.append(" ] ");
		content.append("replaceDictionary [");
		for(String currVal : _replaceDictionary.keySet()){
			content.append(" ");
			content.append("key/value");
			content.append(currVal);
			content.append("/");
			content.append(_replaceDictionary.get(currVal));
		}
		content.append(" ] ");
		return content.toString();
	}
	
	public ReplaceTextTask file(String file) {
		_file = file;
		return this;
	}
	public ReplaceTextTask replaceAllOccurrence(boolean replaceAllOccurrence) {
		_replaceAllOccurrence = replaceAllOccurrence;
		return this;
	}
	
}
