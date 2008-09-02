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

import com.googlecode.jsfFlex.framework.component.MXMLComboBaseTemplate;
import com.googlecode.jsfFlex.framework.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JsfFlexAttributes
 * 	dayNames=false
 * 	disabledDays=false
 * 	disabledRanges=false
 * 	displayedMonth=false
 * 	displayedYear=false
 * 	firstDayOfWeek=false
 * 	formatString=false
 * 	labelFunction=false
 * 	maxYear=false
 * 	minYear=false
 * 	monthNames=false
 * 	monthSymbol=false
 * 	parseFunction=false
 * 	selectableRange=false
 * 	selectedDate=false
 * 	showToday=false
 * 	yearNavigationEnabled=false
 * 	yearSymbol=false
 * 	borderColor=false
 * 	borderThickness=false
 * 	color=false
 * 	cornerRadius=false
 * 	dateChooserStyleName=false
 * 	disabledColor=false
 * 	fillAlphas=false
 * 	fillColors=false
 * 	focusAlpha=false
 * 	focusRoundedCorners=false
 * 	fontAntiAliasType=false
 * 	fontFamily=false
 * 	fontGridFitType=false
 * 	fontSharpness=false
 * 	fontSize=false
 * 	fontStyle=false
 * 	fontThickness=false
 * 	fontWeight=false
 * 	headerColors=false
 * 	headerStyleName=false
 * 	highlightAlphas=false
 * 	leading=false
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	rollOverColor=false
 * 	selectionColor=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	todayColor=false
 * 	todayStyleName=false
 * 	weekDayStyleName=false
 * 	change=false
 * 	close=false
 * 	dataChange=false
 * 	open=false
 * 	scroll=false
 * 
 * @JsfFlexRenderKitAttribute
 *  componentFamily=javax.faces.MXMLInput
 *  rendererName=com.googlecode.jsfFlex.MXMLDateField
 *  rendererClass=com.googlecode.jsfFlex.framework.component.ext.MXMLDateField
 * 
 * @JsfFlexComponentValueClassInfo
 *  classPackage=mx.controls
 *  className=DateField
 *  
 * @JsfFlexComponentNodeInfo
 *  htmlType=INPUT
 *  typeAttributeValue=HIDDEN
 *  valueAttributeValue=text
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_text
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLDateField extends MXMLComboBaseTemplate {
	
	private static final String MXML_DATE_FIELD_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "DateField";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLDateField.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_DATE_FIELD_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLDateFieldReplaceMapping.xml";
	}
	
	public MXMLDateField(){
		super();
	}
	
	public void buildComponentBegin(Object componentObj) throws ComponentBuildException{
		super.buildComponentBegin(componentObj);
		
		mapFields(MXMLDateField.class, componentObj, MXML_DATE_FIELD_REPLACE_MAPPING);
		
	}
	
	public void buildComponentInterlude(Object componentObj) throws ComponentBuildException {
		super.buildComponentInterlude(componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		addCreatePreMxmlTask(componentMXML, MXML_COMPONENT_NAME, null);

	}

}
