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
				@JsfFlexAttribute(attribute="border"),
				@JsfFlexAttribute(attribute="horizontalScrollPolicy"),
				@JsfFlexAttribute(attribute="horizontalScrollPosition"),
				@JsfFlexAttribute(attribute="liveScrolling"),
				@JsfFlexAttribute(attribute="maxHorizontalScrollPosition"),
				@JsfFlexAttribute(attribute="maxVerticalScrollPosition"),
				@JsfFlexAttribute(attribute="scrollTipFunction"),
				@JsfFlexAttribute(attribute="showScrollTips"),
				@JsfFlexAttribute(attribute="verticalScrollPolicy"),
				@JsfFlexAttribute(attribute="verticalScrollPosition"),
				@JsfFlexAttribute(attribute="backgroundAlpha"),
				@JsfFlexAttribute(attribute="backgroundColor"),
				@JsfFlexAttribute(attribute="backgroundImage"),
				@JsfFlexAttribute(attribute="backgroundSize"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="borderSides"),
				@JsfFlexAttribute(attribute="borderSkin"),
				@JsfFlexAttribute(attribute="borderStyle"),
				@JsfFlexAttribute(attribute="borderThickness"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
				@JsfFlexAttribute(attribute="dropShadowColor"),
				@JsfFlexAttribute(attribute="dropShadowEnabled"),
				@JsfFlexAttribute(attribute="fontFamily"),
				@JsfFlexAttribute(attribute="fontSize"),
				@JsfFlexAttribute(attribute="fontStyle"),
				@JsfFlexAttribute(attribute="fontWeight"),
				@JsfFlexAttribute(attribute="horizontalScrollBarStyleName"),
				@JsfFlexAttribute(attribute="leading"),
                @JsfFlexAttribute(attribute="repeatDelay"),
                @JsfFlexAttribute(attribute="repeatInterval"),
				@JsfFlexAttribute(attribute="shadowDirection"),
				@JsfFlexAttribute(attribute="shadowDistance"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="verticalScrollBarStyleName"),
				@JsfFlexAttribute(attribute="scroll")
		}
)
public abstract class MXMLScrollControlTemplateRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLScrollControlTemplateRenderer.class, componentObj, null);
		
	}
	
}
