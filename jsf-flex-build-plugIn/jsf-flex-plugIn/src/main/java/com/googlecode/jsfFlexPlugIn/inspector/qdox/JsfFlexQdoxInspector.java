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
package com.googlecode.jsfFlexPlugIn.inspector.qdox;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.googlecode.jsfFlexPlugIn.inspector._JsfFlexInspectorBase;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;

/**
 * @author Ji Hoon Kim
 */
public final class JsfFlexQdoxInspector extends _JsfFlexInspectorBase {
	
	private final String[] _patternList;
	
	public JsfFlexQdoxInspector(){
		super();
		_patternList = null;
	}
	
	public JsfFlexQdoxInspector(String dirPath, String... patternList){
		super(dirPath);
		_patternList = patternList;
	}
	
	@Override
	public synchronized void inspectFiles(){
		
		JavaDocBuilder builder = new JavaDocBuilder();
		builder.addSourceTree(new File(getDirPath()));
		JavaClass[] inspectableFiles = builder.getClasses();
		
		for(JavaClass currClass : inspectableFiles){
			//TODO implement it better later, but since it's a plug-in does it have to be???
			List<Map<String, ? extends Object>> inspectedList = new LinkedList<Map<String, ? extends Object>>();
			
			for(String _currPattern : _patternList){
				DocletTag[] inspectedDocletTag = currClass.getTagsByName(_currPattern);
				
				for(DocletTag currDocletTag : inspectedDocletTag){
					Map<String, Object> inspectedMap = new LinkedHashMap<String, Object>();
					inspectedMap.putAll(currDocletTag.getNamedParameterMap());
					inspectedList.add(inspectedMap);
				}
				
			}
			
			inspectFileFinished(inspectedList, currClass.getName(), currClass.getPackage().toString());
		}
		
		inspectionCompleted();
		
	}
	
}
