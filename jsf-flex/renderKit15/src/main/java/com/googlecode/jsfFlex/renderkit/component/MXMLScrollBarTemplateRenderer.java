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
				@JsfFlexAttribute(attribute="direction", byMethod=true),
				@JsfFlexAttribute(attribute="lineScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="maxScrollPosition", byMethod=true),
				@JsfFlexAttribute(attribute="minScrollPosition", byMethod=true),
				@JsfFlexAttribute(attribute="pageScrollSize", byMethod=true),
				@JsfFlexAttribute(attribute="pageSize", byMethod=true),
				@JsfFlexAttribute(attribute="scrollPosition", byMethod=true),
				@JsfFlexAttribute(attribute="borderColor", byMethod=true),
				@JsfFlexAttribute(attribute="cornerRadius", byMethod=true),
				@JsfFlexAttribute(attribute="downArrowDisabledSkin", byMethod=true),
				@JsfFlexAttribute(attribute="downArrowDownSkin", byMethod=true),
				@JsfFlexAttribute(attribute="downArrowOverSkin", byMethod=true),
				@JsfFlexAttribute(attribute="downArrowUpSkin", byMethod=true),
				@JsfFlexAttribute(attribute="fillAlphas", byMethod=true),
				@JsfFlexAttribute(attribute="fillColors", byMethod=true),
				@JsfFlexAttribute(attribute="highlightAlphas", byMethod=true),
				@JsfFlexAttribute(attribute="thumbDownSkin", byMethod=true),
				@JsfFlexAttribute(attribute="thumbIcon", byMethod=true),
				@JsfFlexAttribute(attribute="thumbOverSkin", byMethod=true),
				@JsfFlexAttribute(attribute="thumbUpSkin", byMethod=true),
				@JsfFlexAttribute(attribute="trackColors", byMethod=true),
				@JsfFlexAttribute(attribute="trackSkin", byMethod=true),
				@JsfFlexAttribute(attribute="upArrowDisabledSkin", byMethod=true),
				@JsfFlexAttribute(attribute="upArrowDownSkin", byMethod=true),
				@JsfFlexAttribute(attribute="upArrowOverSkin", byMethod=true),
				@JsfFlexAttribute(attribute="upArrowUpSkin", byMethod=true)
		}
)
public class MXMLScrollBarTemplateRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLScrollBarTemplateRenderer.class, componentObj, null);
		
	}
	
}
