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
 *  type        = "com.googlecode.jsfFlex.MXMLColorPicker"
 * 
 * @JsfFlexAttributes
 * 	colorField=false
 * 	labelField=false
 * 	showTextField=false
 * 	backgroundColor=false
 * 	borderColor=false
 * 	closeDuration=false
 * 	closeEasingFunction=false
 * 	color=false
 * 	columnCount=false
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
 * 	horizontalGap=false
 * 	leading=false
 * 	openDuration=false
 * 	openEasingFunction=false
 * 	paddingBottom=false
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	paddingTop=false
 * 	previewHeight=false
 * 	previewWidth=false
 * 	swatchBorderColor=false
 * 	swatchBorderSize=false
 * 	swatchGridBackgroundColor=false
 * 	swatchGridBorderSize=false
 * 	swatchHeight=false
 * 	swatchHighlightColor=false
 * 	swatchHighlightSize=false
 * 	swatchPanelStyleName=false
 * 	swatchWidth=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textFieldWidth=false
 * 	textIndent=false
 * 	verticalGap=false
 * 	change=false
 * 	close=false
 * 	enter=false
 * 	itemRollOut=false
 * 	itemRollOver=false
 * 	open=false
 * 
 * @FlexComponentValueClassInfo
 *  mxmlComponentPackage=mx.controls
 *  mxmlComponentName=ColorPicker
 *  
 * @FlexComponentNodeAttribute
 *  htmlType=input
 *  typeAttributeValue=hidden
 *  valueAttributeValue=selectedColor
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_selectedColor
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLColorPickerRenderer extends MXMLComboBaseTemplateRenderer {
	
	private static final String MXML_COLOR_PICKER_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "ColorPicker";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLColorPickerRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_COLOR_PICKER_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLColorPickerRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLColorPickerRenderer.class, componentObj, MXML_COLOR_PICKER_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}

}
