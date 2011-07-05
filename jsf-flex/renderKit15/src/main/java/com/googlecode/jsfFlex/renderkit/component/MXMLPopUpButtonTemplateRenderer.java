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
				@JsfFlexAttribute(attribute="openAlways"),
				@JsfFlexAttribute(attribute="popUp"),
				@JsfFlexAttribute(attribute="arrowButtonWidth"),
				@JsfFlexAttribute(attribute="closeDuration"),
				@JsfFlexAttribute(attribute="closeEasingFunction"),
                @JsfFlexAttribute(attribute="disabledIconColor"),
                @JsfFlexAttribute(attribute="iconColor"),
                @JsfFlexAttribute(attribute="disabledIconColor"),
                @JsfFlexAttribute(attribute="iconColor"),
				@JsfFlexAttribute(attribute="openDuration"),
				@JsfFlexAttribute(attribute="openEasingFunction"),
				@JsfFlexAttribute(attribute="popUpDownSkin"),
				@JsfFlexAttribute(attribute="popUpGap"),
				@JsfFlexAttribute(attribute="popUpIcon"),
				@JsfFlexAttribute(attribute="popUpOverSkin"),
				@JsfFlexAttribute(attribute="close"),
				@JsfFlexAttribute(attribute="open")
		}
)
public abstract class MXMLPopUpButtonTemplateRenderer extends MXMLButtonTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLPopUpButtonTemplateRenderer.class, componentObj, null);
		
	}
	
}
