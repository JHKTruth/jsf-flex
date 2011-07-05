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
package com.googlecode.jsfFlex.renderkit.component.ext;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.component.MXMLComboBaseTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLInput"
 *  type        = "com.googlecode.jsfFlex.MXMLComboBox"
 * 
 * @JsfFlexAttributes
 * 	dropdownFactory=false
 * 	dropdownWidth=false
 * 	itemRenderer=false
 * 	labelField=false
 * 	labelFunction=false
 * 	prompt=false
 * 	rowCount=false
 * 	alternatingItemColors=false
 * 	arrowButtonWidth=false
 * 	borderColor=false
 * 	borderThickness=false
 * 	closeDuration=false
 * 	closeEasingFunction=false
 * 	color=false
 * 	cornerRadius=false
 * 	disabledColor=false
 * 	dropDownBorderColor=false
 * 	dropDownStyleName=false
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
 * 	highlightAlphas=false
 * 	leading=false
 * 	openDuration=false
 * 	openEasingFunction=false
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	rollOverColor=false
 * 	selectionColor=false
 * 	selectionDuration=false
 * 	selectionEasingFunction=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	textRollOverColor=false
 * 	textSelectedColor=false
 * 	change=false
 * 	close=false
 * 	dataChange=false
 * 	enter=false
 * 	itemRollOut=false
 * 	itemRollOver=false
 * 	open=false
 * 	scroll=false
 * 
 * @FlexComponentValueClassInfo
 *  mxmlComponentPackage=mx.controls
 *  mxmlComponentName=ComboBox
 *  
 * @FlexComponentNodeAttribute
 *  htmlType=input
 *  typeAttributeValue=hidden
 *  valueAttributeValue=text
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_text
 *  
 * @FlexComponentNodeAttribute
 *  htmlType=input
 *  typeAttributeValue=hidden
 *  valueAttributeValue=selectedIndex
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_selectedIndex
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLComboBoxRenderer extends MXMLComboBaseTemplateRenderer {
	
	private static final String MXML_COMBO_BOX_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "ComboBox";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLComboBoxRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_COMBO_BOX_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLComboBoxRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLComboBoxRenderer.class, componentObj, MXML_COMBO_BOX_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}

}
