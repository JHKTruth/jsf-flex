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
package com.googlecode.jsfFlex.renderkit.validator;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;

/**
 * @author Ji Hoon Kim
 */
@IJsfFlexAttributeProperties(
		componentNodeAttributes={},

		jsfFlexAttributes={
				@IJsfFlexAttribute(attribute="enabled"),
				@IJsfFlexAttribute(attribute="listener"),
				@IJsfFlexAttribute(attribute="property"),
				@IJsfFlexAttribute(attribute="required"),
				@IJsfFlexAttribute(attribute="requiredFieldError"),
				@IJsfFlexAttribute(attribute="source"),
				@IJsfFlexAttribute(attribute="trigger"),
				@IJsfFlexAttribute(attribute="triggerEvent")
		}
)
public abstract class AbstractFlexValidatorTemplateRenderer extends AbstractFlexComponentRenderer {
	
	private static final String VALIDATION_MANAGER_IMPORT = "com.googlecode.jsfFlex.communication.validator.ValidationManager";
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(AbstractFlexValidatorTemplateRenderer.class, componentObj, null);
		
		AbstractFlexContext mxmlContext = AbstractFlexContext.getCurrentInstance();
		AdditionalApplicationScriptContent additionalAppScriptContent = mxmlContext.getAdditionalAppScriptContent();
		additionalAppScriptContent.addValidationManagerValidatorId(componentObj.getId());
		
		additionalAppScriptContent.addActionScriptImport(VALIDATION_MANAGER_IMPORT);
		
	}
	
}