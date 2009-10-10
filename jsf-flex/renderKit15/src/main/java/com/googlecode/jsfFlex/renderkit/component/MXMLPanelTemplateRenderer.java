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
				@JsfFlexAttribute(attribute="layout"),
				@JsfFlexAttribute(attribute="status"),
				@JsfFlexAttribute(attribute="title", byMethod=true),
				@JsfFlexAttribute(attribute="titleIcon"),
				@JsfFlexAttribute(attribute="borderAlpha"),
				@JsfFlexAttribute(attribute="borderThicknessBottom"),
				@JsfFlexAttribute(attribute="borderThicknessLeft"),
				@JsfFlexAttribute(attribute="borderThicknessRight"),
				@JsfFlexAttribute(attribute="borderThicknessTop"),
                @JsfFlexAttribute(attribute="closeButtonDisabledSkin"),
                @JsfFlexAttribute(attribute="closeButtonDownSkin"),
                @JsfFlexAttribute(attribute="closeButtonOverSkin"),
                @JsfFlexAttribute(attribute="closeButtonUpSkin"),
                @JsfFlexAttribute(attribute="controlBarStyleName"),
				@JsfFlexAttribute(attribute="footerColors"),
				@JsfFlexAttribute(attribute="headerColors"),
				@JsfFlexAttribute(attribute="headerHeight"),
				@JsfFlexAttribute(attribute="highlightAlphas"),
				@JsfFlexAttribute(attribute="horizontalAlign"),
				@JsfFlexAttribute(attribute="horizontalGap"),
				@JsfFlexAttribute(attribute="modalTransparency"),
				@JsfFlexAttribute(attribute="modalTransparencyBlur"),
				@JsfFlexAttribute(attribute="modalTransparencyColor"),
				@JsfFlexAttribute(attribute="modalTransparencyDuration"),
				@JsfFlexAttribute(attribute="roundedBottomCorners"),
				@JsfFlexAttribute(attribute="statusStyleName"),
				@JsfFlexAttribute(attribute="titleBackgroundSkin"),
				@JsfFlexAttribute(attribute="titleStyleName"),
				@JsfFlexAttribute(attribute="verticalAlign"),
				@JsfFlexAttribute(attribute="verticalGap"),
				@JsfFlexAttribute(attribute="resizeEndEffect"),
				@JsfFlexAttribute(attribute="resizeStartEffect"),
                @JsfFlexAttribute(attribute="close")
		}
)
public abstract class MXMLPanelTemplateRenderer extends MXMLContainerTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLPanelTemplateRenderer.class, componentObj, null);
		
	}
	
}
