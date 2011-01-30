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
package com.googlecode.jsfFlexPlugIn.inspector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractJsfFlexInspectorBase {
	
	private final String _dirPath;
	private final List<IJsfFlexInspectListener> _jsfFlexInspectListeners;
	
	public AbstractJsfFlexInspectorBase(){
		super();
		_dirPath = null;
	}
	
	public AbstractJsfFlexInspectorBase(String dirPath){
		super();
		_dirPath = dirPath;
	}
	
	{
		_jsfFlexInspectListeners = new LinkedList<IJsfFlexInspectListener>();
	}
	
	protected String getDirPath(){
		return _dirPath;
	}
	
	public synchronized void addInspectListener(IJsfFlexInspectListener callBack){
		_jsfFlexInspectListeners.add(callBack);
	}
	
	protected synchronized void inspectFileFinished(List<Map<String, ? extends Object>> inspectedList, String inspectedFileName, String inspectedPackage){
		for(IJsfFlexInspectListener inspectedCallBack : _jsfFlexInspectListeners){
			inspectedCallBack.inspectFileFinished(inspectedList, inspectedFileName, inspectedPackage);
		}
	}
	
	protected synchronized void inspectionCompleted(){
		for(IJsfFlexInspectListener inspectedCallBack : _jsfFlexInspectListeners){
			inspectedCallBack.inspectionCompleted();
		}
	}
	
	public abstract void inspectFiles();
	
}
