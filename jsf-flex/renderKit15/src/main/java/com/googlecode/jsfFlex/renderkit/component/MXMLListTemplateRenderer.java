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
import com.googlecode.jsfFlex.renderkit.mxml.MXMLResponseWriterImpl;

/**
 * @author Ji Hoon Kim
 */
@JsfFlexAttributeProperties(
		componentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="editable", byMethod=false),
				@JsfFlexAttribute(attribute="editedItemPosition", byMethod=false),
				@JsfFlexAttribute(attribute="editorDataField", byMethod=false),
				@JsfFlexAttribute(attribute="editorHeightOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorUsesEnterKey", byMethod=false),
				@JsfFlexAttribute(attribute="editorWidthOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorXOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorYOffset", byMethod=false),
				@JsfFlexAttribute(attribute="imeMode", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditor", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditorInstance", byMethod=false),
				@JsfFlexAttribute(attribute="rendererIsEditor", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundDisabledColor", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditBegin", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditEnd", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditBeginning", byMethod=false),
				@JsfFlexAttribute(attribute="itemFocusIn", byMethod=false),
				@JsfFlexAttribute(attribute="itemFocusOut", byMethod=false)
		}
)
public class MXMLListTemplateRenderer extends MXMLListBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		MXMLResponseWriterImpl writer = (MXMLResponseWriterImpl) context.getResponseWriter();
		writer.mapFields(MXMLListTemplateRenderer.class, componentObj, null);
		
	}
	
}