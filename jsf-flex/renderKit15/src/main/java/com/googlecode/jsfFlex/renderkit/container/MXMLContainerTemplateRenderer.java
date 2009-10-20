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
package com.googlecode.jsfFlex.renderkit.container;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;

/**
 * @author Ji Hoon Kim
 */
@JsfFlexAttributeProperties(
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="autoLayout"),
				@JsfFlexAttribute(attribute="clipContent"),
				@JsfFlexAttribute(attribute="creationIndex"),
				@JsfFlexAttribute(attribute="creationPolicy"),
				@JsfFlexAttribute(attribute="defaultButton"),
				@JsfFlexAttribute(attribute="horizontalLineScrollSize"),
				@JsfFlexAttribute(attribute="horizontalPageScrollSize"),
				@JsfFlexAttribute(attribute="horizontalScrollBar"),
				@JsfFlexAttribute(attribute="horizontalScrollPolicy"),
				@JsfFlexAttribute(attribute="horizontalScrollPosition"),
				@JsfFlexAttribute(attribute="icon"),
				@JsfFlexAttribute(attribute="label"),
				@JsfFlexAttribute(attribute="verticalLineScrollSize"),
				@JsfFlexAttribute(attribute="verticalPageScrollSize"),
				@JsfFlexAttribute(attribute="verticalScrollBar"),
				@JsfFlexAttribute(attribute="verticalScrollPolicy"),
				@JsfFlexAttribute(attribute="verticalScrollPosition"),
				@JsfFlexAttribute(attribute="backgroundAlpha"),
				@JsfFlexAttribute(attribute="backgroundAttachment"),
				@JsfFlexAttribute(attribute="backgroundColor"),
				@JsfFlexAttribute(attribute="backgroundDisabledColor"),
				@JsfFlexAttribute(attribute="backgroundImage"),
				@JsfFlexAttribute(attribute="backgroundSize"),
				@JsfFlexAttribute(attribute="barColor"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="borderSides"),
				@JsfFlexAttribute(attribute="borderSkin"),
				@JsfFlexAttribute(attribute="borderStyle"),
				@JsfFlexAttribute(attribute="borderThickness"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
				@JsfFlexAttribute(attribute="disabledOverlayAlpha"),
				@JsfFlexAttribute(attribute="dropShadowColor"),
				@JsfFlexAttribute(attribute="dropShadowEnabled"),
				@JsfFlexAttribute(attribute="fontAntiAliasType"),
				@JsfFlexAttribute(attribute="fontFamily"),
				@JsfFlexAttribute(attribute="fontGridFitType"),
				@JsfFlexAttribute(attribute="fontSharpness"),
				@JsfFlexAttribute(attribute="fontSize"),
				@JsfFlexAttribute(attribute="fontStyle"),
				@JsfFlexAttribute(attribute="fontThickness"),
				@JsfFlexAttribute(attribute="fontWeight"),
				@JsfFlexAttribute(attribute="horizontalScrollBarStyleName"),
				@JsfFlexAttribute(attribute="paddingBottom"),
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="paddingTop"),
				@JsfFlexAttribute(attribute="shadowDirection"),
				@JsfFlexAttribute(attribute="shadowDistance"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="verticalScrollBarStyleName"),
				@JsfFlexAttribute(attribute="childAdd"),
				@JsfFlexAttribute(attribute="childIndexChange"),
				@JsfFlexAttribute(attribute="childRemove"),
				@JsfFlexAttribute(attribute="dataChange"),
				@JsfFlexAttribute(attribute="scroll")
		}
)
public abstract class MXMLContainerTemplateRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLContainerTemplateRenderer.class, componentObj, null);
		
	}
	
}
