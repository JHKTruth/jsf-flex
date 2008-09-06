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

import com.googlecode.jsfFlex.framework.component.MXMLButtonTemplate;
import com.googlecode.jsfFlex.framework.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JsfFlexAttributes
 * 	groupName=true
 * 	value=true
 * 
 * @JsfFlexRenderKitAttribute
 *  componentFamily=javax.faces.MXMLInput
 *  rendererName=com.googlecode.jsfFlex.MXMLRadioButton
 *  rendererClass=com.googlecode.jsfFlex.framework.component.ext.MXMLRadioButton
 * 
 * @JsfFlexComponentValueClassInfo
 *  classPackage=mx.controls
 *  className=RadioButton
 *  
 * @JsfFlexComponentNodeAttribute
 *  htmlType=INPUT
 *  typeAttributeValue=HIDDEN
 *  valueDynamic=true
 *  valueNested=true
 *  valueNestedValues=group_selectedValue
 *  nameAttributeValue=groupName
 *  nameDynamic=true
 *  nameAppend=_selectedValue
 * 
 * @JsfFlexComponentNodeAttribute
 *  htmlType=INPUT
 *  typeAttributeValue=HIDDEN
 *  valueAttributeValue=selected
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_selected
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLRadioButton extends MXMLButtonTemplate {
	
	private static final String MXML_RADIO_BUTTON_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "RadioButton";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLRadioButton.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_RADIO_BUTTON_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLRadioButtonReplaceMapping.xml";
	}
	
	public MXMLRadioButton(){
		super();
	}
	
	public void buildComponentBegin(Object componentObj) throws ComponentBuildException{
		super.buildComponentBegin(componentObj);
		
		mapFields(MXMLRadioButton.class, componentObj, MXML_RADIO_BUTTON_REPLACE_MAPPING);
		
	}
	
	public void buildComponentInterlude(Object componentObj) throws ComponentBuildException {
		super.buildComponentInterlude(componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		addCreatePreMxmlTask(componentMXML, MXML_COMPONENT_NAME, null);

	}

}