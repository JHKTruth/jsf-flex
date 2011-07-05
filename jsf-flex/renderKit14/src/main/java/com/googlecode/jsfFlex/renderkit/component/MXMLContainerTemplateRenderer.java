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
 * 	autoLayout=true
 * 	clipContent=true
 * 	creationIndex=true
 * 	creationPolicy=true
 * 	defaultButton=true
 * 	horizontalLineScrollSize=true
 * 	horizontalPageScrollSize=true
 * 	horizontalScrollBar=true
 * 	horizontalScrollPolicy=true
 * 	horizontalScrollPosition=true
 * 	icon=true
 * 	label=true
 * 	verticalLineScrollSize=true
 * 	verticalPageScrollSize=true
 * 	verticalScrollBar=true
 * 	verticalScrollPolicy=true
 * 	verticalScrollPosition=true
 * 	backgroundAlpha=true
 * 	backgroundAttachment=true
 * 	backgroundColor=true
 * 	backgroundDisabledColor=true
 * 	backgroundImage=true
 * 	backgroundSize=true
 * 	barColor=true
 * 	borderColor=true
 * 	borderSides=true
 * 	borderSkin=true
 * 	borderStyle=true
 * 	borderThickness=true
 * 	color=true
 * 	cornerRadius=true
 * 	disabledColor=true
 * 	disabledOverlayAlpha=true
 * 	dropShadowColor=true
 * 	dropShadowEnabled=true
 * 	fontAntiAliasType=true
 * 	fontFamily=true
 * 	fontGridFitType=true
 * 	fontSharpness=true
 * 	fontSize=true
 * 	fontStyle=true
 * 	fontThickness=true
 * 	fontWeight=true
 * 	horizontalScrollBarStyleName=true
 * 	paddingBottom=true
 * 	paddingLeft=true
 * 	paddingRight=true
 * 	paddingTop=true
 * 	shadowDirection=true
 * 	shadowDistance=true
 * 	textAlign=true
 * 	textDecoration=true
 * 	textIndent=true
 * 	verticalScrollBarStyleName=true
 * 	childAdd=true
 * 	childIndexChange=true
 * 	childRemove=true
 * 	dataChange=true
 * 	scroll=true
 * 
 * @author Ji Hoon Kim
 */
public abstract class MXMLContainerTemplateRenderer extends MXMLComponentRenderer {
	
	private static final String MXML_CONTAINER_TEMPLATE_REPLACE_MAPPING;
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLContainerTemplateRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_CONTAINER_TEMPLATE_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLContainerTemplateRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLContainerTemplateRenderer.class, componentObj, MXML_CONTAINER_TEMPLATE_REPLACE_MAPPING);
		
	}
	
}
