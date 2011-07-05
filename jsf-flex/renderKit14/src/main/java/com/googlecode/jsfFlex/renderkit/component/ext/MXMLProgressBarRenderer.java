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
 *  type        = "com.googlecode.jsfFlex.MXMLProgressBar"
 * 
 * @JsfFlexAttributes
 * 	conversion=false
 * 	direction=false
 * 	indeterminate=false
 * 	label=false
 * 	labelPlacement=false
 * 	maximum=false
 * 	minimum=false
 * 	mode=false
 * 	source=false
 * 	barColor=false
 * 	barSkin=false
 * 	borderColor=false
 * 	color=false
 * 	disabledColor=false
 * 	fontAntiAliasType=false
 * 	fontFamily=false
 * 	fontGridFitType=false
 * 	fontSharpness=false
 * 	fontSize=false
 * 	fontThickness=false
 * 	fontStyle=false
 * 	fontWeight=false
 * 	horizontalGap=false
 * 	indeterminateSkin=false
 * 	labelWidth=false
 * 	leading=false
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	themeColor=false
 * 	trackColors=false
 * 	trackHeight=false
 * 	trackSkin=false
 * 	verticalGap=false
 * 	complete=false
 * 	hide=false
 * 	progress=false
 * 	show=false
 * 	completeEffect=false
 * 
 * @FlexComponentValueClassInfo
 *  mxmlComponentPackage=mx.controls
 *  mxmlComponentName=ProgressBar
 *  
 * @FlexComponentNodeAttribute
 *  htmlType=input
 *  typeAttributeValue=hidden
 *  valueAttributeValue=value
 *  valueDynamic=true
 *  valueNested=false
 *  nameAttributeValue=id
 *  nameDynamic=true
 *  nameAppend=_value
 *  
 * @author Ji Hoon Kim
 */
public final class MXMLProgressBarRenderer extends MXMLComponentRenderer {
	
	private static final String MXML_PROGRESS_BAR_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "ProgressBar";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLProgressBarRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_PROGRESS_BAR_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLProgressBarRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLProgressBarRenderer.class, componentObj, MXML_PROGRESS_BAR_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}

}
