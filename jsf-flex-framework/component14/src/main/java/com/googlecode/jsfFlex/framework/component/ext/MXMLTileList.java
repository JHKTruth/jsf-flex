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
package com.googlecode.jsfFlex.framework.component.ext;

import com.googlecode.jsfFlex.framework.component.MXMLListBaseTemplate;
import com.googlecode.jsfFlex.framework.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JsfFlexRenderKitAttribute
 *  componentFamily=javax.faces.MXMLInput
 *  rendererName=com.googlecode.jsfFlex.MXMLTileList
 *  rendererClass=com.googlecode.jsfFlex.framework.component.ext.MXMLTileList
 * 
 * @JsfFlexComponentValueClassInfo
 *  classPackage=mx.controls
 *  className=TileList
 *  
 * @JsfFlexComponentNodeInfo
 *  htmlType=INPUT
 *  typeAttributeValue=HIDDEN
 *  valueAttributeValue=selectedIndex
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_selectedIndex
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLTileList extends MXMLListBaseTemplate {
	
	private static final String MXML_COMPONENT_NAME = "TileList";
	
	public MXMLTileList(){
		super();
	}
	
	public void buildComponentBegin(Object componentObj) throws ComponentBuildException{
		super.buildComponentBegin(componentObj);
		
	}
	
	public void buildComponentInterlude(Object componentObj) throws ComponentBuildException {
		super.buildComponentInterlude(componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		addCreatePreMxmlTask(componentMXML, MXML_COMPONENT_NAME, null);
		
	}

}