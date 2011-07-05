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

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;

/**
 * @author Ji Hoon Kim
 */
@JsfFlexAttributeProperties(
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="autoLayout", byMethod=true),
				@JsfFlexAttribute(attribute="clipContent", byMethod=true),
				@JsfFlexAttribute(attribute="creationIndex", byMethod=true),
				@JsfFlexAttribute(attribute="creationPolicy", byMethod=true),
				@JsfFlexAttribute(attribute="defaultButton", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalLineScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalPageScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalScrollBar", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalScrollPolicy", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalScrollPosition", byMethod=true),
				@JsfFlexAttribute(attribute="icon", byMethod=true),
				@JsfFlexAttribute(attribute="label", byMethod=true),
				@JsfFlexAttribute(attribute="verticalLineScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="verticalPageScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="verticalScrollBar", byMethod=true),
				@JsfFlexAttribute(attribute="verticalScrollPolicy", byMethod=true),
				@JsfFlexAttribute(attribute="verticalScrollPosition", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundAlpha", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundAttachment", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundColor", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundDisabledColor", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundImage", byMethod=true),
				@JsfFlexAttribute(attribute="backgroundSize", byMethod=true),
				@JsfFlexAttribute(attribute="barColor", byMethod=true),
				@JsfFlexAttribute(attribute="borderColor", byMethod=true),
				@JsfFlexAttribute(attribute="borderSides", byMethod=true),
				@JsfFlexAttribute(attribute="borderSkin", byMethod=true),
				@JsfFlexAttribute(attribute="borderStyle", byMethod=true),
				@JsfFlexAttribute(attribute="borderThickness", byMethod=true),
				@JsfFlexAttribute(attribute="color", byMethod=true),
				@JsfFlexAttribute(attribute="cornerRadius", byMethod=true),
				@JsfFlexAttribute(attribute="disabledColor", byMethod=true),
				@JsfFlexAttribute(attribute="disabledOverlayAlpha", byMethod=true),
				@JsfFlexAttribute(attribute="dropShadowColor", byMethod=true),
				@JsfFlexAttribute(attribute="dropShadowEnabled", byMethod=true),
				@JsfFlexAttribute(attribute="fontAntiAliasType", byMethod=true),
				@JsfFlexAttribute(attribute="fontFamily", byMethod=true),
				@JsfFlexAttribute(attribute="fontGridFitType", byMethod=true),
				@JsfFlexAttribute(attribute="fontSharpness", byMethod=true),
				@JsfFlexAttribute(attribute="fontSize", byMethod=true),
				@JsfFlexAttribute(attribute="fontStyle", byMethod=true),
				@JsfFlexAttribute(attribute="fontThickness", byMethod=true),
				@JsfFlexAttribute(attribute="fontWeight", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalScrollBarStyleName", byMethod=true),
				@JsfFlexAttribute(attribute="paddingBottom", byMethod=true),
				@JsfFlexAttribute(attribute="paddingLeft", byMethod=true),
				@JsfFlexAttribute(attribute="paddingRight", byMethod=true),
				@JsfFlexAttribute(attribute="paddingTop", byMethod=true),
				@JsfFlexAttribute(attribute="shadowDirection", byMethod=true),
				@JsfFlexAttribute(attribute="shadowDistance", byMethod=true),
				@JsfFlexAttribute(attribute="textAlign", byMethod=true),
				@JsfFlexAttribute(attribute="textDecoration", byMethod=true),
				@JsfFlexAttribute(attribute="textIndent", byMethod=true),
				@JsfFlexAttribute(attribute="verticalScrollBarStyleName", byMethod=true),
				@JsfFlexAttribute(attribute="childAdd", byMethod=true),
				@JsfFlexAttribute(attribute="childIndexChange", byMethod=true),
				@JsfFlexAttribute(attribute="childRemove", byMethod=true),
				@JsfFlexAttribute(attribute="dataChange", byMethod=true),
				@JsfFlexAttribute(attribute="scroll", byMethod=true)
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
