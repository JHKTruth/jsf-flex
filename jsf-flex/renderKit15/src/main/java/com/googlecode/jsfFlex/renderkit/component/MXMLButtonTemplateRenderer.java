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
				@JsfFlexAttribute(attribute="autoRepeat"),
				@JsfFlexAttribute(attribute="emphasized"),
                @JsfFlexAttribute(attribute="fontContext"),
				@JsfFlexAttribute(attribute="label"),
				@JsfFlexAttribute(attribute="labelPlacement"),
				@JsfFlexAttribute(attribute="selectedField"),
				@JsfFlexAttribute(attribute="stickyHighlighting"),
				@JsfFlexAttribute(attribute="toggle"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
				@JsfFlexAttribute(attribute="disabledIcon"),
				@JsfFlexAttribute(attribute="disabledSkin"),
				@JsfFlexAttribute(attribute="downIcon"),
				@JsfFlexAttribute(attribute="downSkin"),
				@JsfFlexAttribute(attribute="fillAlphas"),
				@JsfFlexAttribute(attribute="fillColors"),
				@JsfFlexAttribute(attribute="focusAlpha"),
				@JsfFlexAttribute(attribute="focusRoundedCorners"),
				@JsfFlexAttribute(attribute="fontAntiAliasType"),
				@JsfFlexAttribute(attribute="fontFamily"),
				@JsfFlexAttribute(attribute="fontGridFitType"),
				@JsfFlexAttribute(attribute="fontSharpness"),
				@JsfFlexAttribute(attribute="fontSize"),
				@JsfFlexAttribute(attribute="fontStyle"),
				@JsfFlexAttribute(attribute="fontThickness"),
				@JsfFlexAttribute(attribute="fontWeight"),
				@JsfFlexAttribute(attribute="highlightAlphas"),
				@JsfFlexAttribute(attribute="horizontalGap"),
				@JsfFlexAttribute(attribute="icon"),
                @JsfFlexAttribute(attribute="kerning"),
				@JsfFlexAttribute(attribute="leading"),
                @JsfFlexAttribute(attribute="letterSpacing"),
				@JsfFlexAttribute(attribute="overIcon"),
				@JsfFlexAttribute(attribute="overSkin"),
				@JsfFlexAttribute(attribute="paddingBottom"),
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="paddingTop"),
				@JsfFlexAttribute(attribute="repeatDelay"),
				@JsfFlexAttribute(attribute="repeatInterval"),
				@JsfFlexAttribute(attribute="selectedDisabledIcon"),
				@JsfFlexAttribute(attribute="selectedDisabledSkin"),
				@JsfFlexAttribute(attribute="selectedDownIcon"),
				@JsfFlexAttribute(attribute="selectedDownSkin"),
				@JsfFlexAttribute(attribute="selectedOverIcon"),
				@JsfFlexAttribute(attribute="selectedOverSkin"),
				@JsfFlexAttribute(attribute="selectedUpIcon"),
				@JsfFlexAttribute(attribute="selectedUpSkin"),
                @JsfFlexAttribute(attribute="skin"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="textRollOverColor"),
				@JsfFlexAttribute(attribute="textSelectedColor"),
				@JsfFlexAttribute(attribute="upIcon"),
				@JsfFlexAttribute(attribute="upSkin"),
				@JsfFlexAttribute(attribute="verticalGap"),
				@JsfFlexAttribute(attribute="buttonDown"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="dataChange")
		}
)
public abstract class MXMLButtonTemplateRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLButtonTemplateRenderer.class, componentObj, null);
		
	}
	
}
