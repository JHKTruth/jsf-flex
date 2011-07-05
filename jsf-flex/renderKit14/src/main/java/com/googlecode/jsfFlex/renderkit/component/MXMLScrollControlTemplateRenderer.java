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
package com.googlecode.jsfFlex.renderkit.component;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;

/**
 * @JsfFlexAttributes
 * 	border=true
 * 	horizontalScrollPolicy=true
 * 	horizontalScrollPosition=true
 * 	liveScrolling=true
 * 	maxHorizontalScrollPosition=true
 * 	maxVerticalScrollPosition=true
 * 	scrollTipFunction=true
 * 	showScrollTips=true
 * 	verticalScrollPolicy=true
 * 	verticalScrollPosition=true
 * 	backgroundAlpha=true
 * 	backgroundColor=true
 * 	backgroundImage=true
 * 	backgroundSize=true
 * 	borderColor=true
 * 	borderSides=true
 * 	borderSkin=true
 * 	borderStyle=true
 * 	borderThickness=true
 * 	color=true
 * 	cornerRadius=true
 * 	disabledColor=true
 * 	dropShadowColor=true
 * 	dropShadowEnabled=true
 * 	fontFamily=true
 * 	fontSize=true
 * 	fontStyle=true
 * 	fontWeight=true
 * 	horizontalScrollBarStyleName=true
 * 	leading=true
 * 	repeatDelay=true
 * 	repeatInterval=true
 * 	shadowDirection=true
 * 	shadowDistance=true
 * 	textAlign=true
 * 	textDecoration=true
 * 	textIndent=true
 * 	verticalScrollBarStyleName=true
 * 	scroll=true
 * 
 * @author Ji Hoon Kim
 */
public abstract class MXMLScrollControlTemplateRenderer extends MXMLComponentRenderer {
	
	private static final String MXML_SCROLL_CONTROL_TEMPLATE_REPLACE_MAPPING;
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLScrollControlTemplateRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_SCROLL_CONTROL_TEMPLATE_REPLACE_MAPPING = packageName + 
													"/replaceMapping/MXMLScrollControlTemplateRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLScrollControlTemplateRenderer.class, componentObj, MXML_SCROLL_CONTROL_TEMPLATE_REPLACE_MAPPING);
		
	}
	
}
