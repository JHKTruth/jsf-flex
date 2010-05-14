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

import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;

/**
 * @author Ji Hoon Kim
 */
@IJsfFlexAttributeProperties(
		componentNodeAttributes={},

		jsfFlexAttributes={
				@IJsfFlexAttribute(attribute="autoLayout"),
				@IJsfFlexAttribute(attribute="clipContent"),
				@IJsfFlexAttribute(attribute="creationIndex"),
				@IJsfFlexAttribute(attribute="creationPolicy"),
				@IJsfFlexAttribute(attribute="defaultButton"),
				@IJsfFlexAttribute(attribute="horizontalLineScrollSize"),
				@IJsfFlexAttribute(attribute="horizontalPageScrollSize"),
				@IJsfFlexAttribute(attribute="horizontalScrollBar"),
				@IJsfFlexAttribute(attribute="horizontalScrollPolicy"),
				@IJsfFlexAttribute(attribute="horizontalScrollPosition"),
				@IJsfFlexAttribute(attribute="icon"),
				@IJsfFlexAttribute(attribute="label"),
				@IJsfFlexAttribute(attribute="verticalLineScrollSize"),
				@IJsfFlexAttribute(attribute="verticalPageScrollSize"),
				@IJsfFlexAttribute(attribute="verticalScrollBar"),
				@IJsfFlexAttribute(attribute="verticalScrollPolicy"),
				@IJsfFlexAttribute(attribute="verticalScrollPosition"),
				@IJsfFlexAttribute(attribute="backgroundAlpha"),
				@IJsfFlexAttribute(attribute="backgroundAttachment"),
				@IJsfFlexAttribute(attribute="backgroundColor"),
				@IJsfFlexAttribute(attribute="backgroundDisabledColor"),
				@IJsfFlexAttribute(attribute="backgroundImage"),
				@IJsfFlexAttribute(attribute="backgroundSize"),
				@IJsfFlexAttribute(attribute="barColor"),
				@IJsfFlexAttribute(attribute="borderColor"),
				@IJsfFlexAttribute(attribute="borderSides"),
				@IJsfFlexAttribute(attribute="borderSkin"),
				@IJsfFlexAttribute(attribute="borderStyle"),
				@IJsfFlexAttribute(attribute="borderThickness"),
				@IJsfFlexAttribute(attribute="color"),
				@IJsfFlexAttribute(attribute="cornerRadius"),
				@IJsfFlexAttribute(attribute="disabledColor"),
				@IJsfFlexAttribute(attribute="disabledOverlayAlpha"),
				@IJsfFlexAttribute(attribute="dropShadowColor"),
				@IJsfFlexAttribute(attribute="dropShadowEnabled"),
				@IJsfFlexAttribute(attribute="fontAntiAliasType"),
				@IJsfFlexAttribute(attribute="fontFamily"),
				@IJsfFlexAttribute(attribute="fontGridFitType"),
				@IJsfFlexAttribute(attribute="fontSharpness"),
				@IJsfFlexAttribute(attribute="fontSize"),
				@IJsfFlexAttribute(attribute="fontStyle"),
				@IJsfFlexAttribute(attribute="fontThickness"),
				@IJsfFlexAttribute(attribute="fontWeight"),
				@IJsfFlexAttribute(attribute="horizontalScrollBarStyleName"),
				@IJsfFlexAttribute(attribute="paddingBottom"),
				@IJsfFlexAttribute(attribute="paddingLeft"),
				@IJsfFlexAttribute(attribute="paddingRight"),
				@IJsfFlexAttribute(attribute="paddingTop"),
				@IJsfFlexAttribute(attribute="shadowDirection"),
				@IJsfFlexAttribute(attribute="shadowDistance"),
				@IJsfFlexAttribute(attribute="textAlign"),
				@IJsfFlexAttribute(attribute="textDecoration"),
				@IJsfFlexAttribute(attribute="textIndent"),
				@IJsfFlexAttribute(attribute="verticalScrollBarStyleName"),
				@IJsfFlexAttribute(attribute="childAdd"),
				@IJsfFlexAttribute(attribute="childIndexChange"),
				@IJsfFlexAttribute(attribute="childRemove"),
				@IJsfFlexAttribute(attribute="dataChange"),
				@IJsfFlexAttribute(attribute="scroll")
		}
)
public abstract class AbstractFlexContainerTemplateRenderer extends AbstractFlexComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(AbstractFlexContainerTemplateRenderer.class, componentObj, null);
		
	}
	
}