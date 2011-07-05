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

import com.googlecode.jsfFlex.renderkit.component.MXMLComponentRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLInput"
 *  type        = "com.googlecode.jsfFlex.MXMLTextInput"
 * 
 * @JsfFlexAttributes
 * 	condenseWhite=false
 * 	data=false
 * 	displayAsPassword=false
 * 	editable=false
 * 	horizontalScrollPosition=false
 * 	imeMode=false
 * 	listData=false
 * 	maxChars=false
 * 	restrict=false
 * 	selectionBeginIndex=false
 * 	selectionEndIndex=false
 * 	backgroundAlpha=false
 * 	backgroundColor=false
 * 	backgroundImage=false
 * 	backgroundSize=false
 * 	borderColor=false
 * 	borderSides=false
 * 	borderSkin=false
 * 	borderStyle=false
 * 	borderThickness=false
 * 	color=false
 * 	cornerRadius=false
 * 	disabledColor=false
 * 	dropShadowColor=false
 * 	dropShadowEnabled=false
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
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	shadowDirection=false
 * 	shadowDistance=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	change=false
 * 	dataChange=false
 * 	enter=false
 * 	textInput=false
 * 
 * @FlexComponentValueClassInfo
 *  mxmlComponentPackage=mx.controls
 *  mxmlComponentName=TextInput
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
 *  valueAttributeValue=htmlText
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_htmlText
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLTextInputRenderer extends MXMLComponentRenderer {
	
	private static final String MXML_TEXT_INPUT_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "TextInput";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLTextInputRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_TEXT_INPUT_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLTextInputRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLTextInputRenderer.class, componentObj, MXML_TEXT_INPUT_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}
	
}
