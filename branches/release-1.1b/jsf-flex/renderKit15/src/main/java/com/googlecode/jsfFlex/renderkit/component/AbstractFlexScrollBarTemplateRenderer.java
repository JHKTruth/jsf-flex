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

import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;

/**
 * @author Ji Hoon Kim
 */
@IJsfFlexAttributeProperties(
		componentNodeAttributes={},

		jsfFlexAttributes={
				@IJsfFlexAttribute(attribute="direction"),
				@IJsfFlexAttribute(attribute="lineScrollSize"),
				@IJsfFlexAttribute(attribute="maxScrollPosition"),
				@IJsfFlexAttribute(attribute="minScrollPosition"),
				@IJsfFlexAttribute(attribute="pageScrollSize"),
				@IJsfFlexAttribute(attribute="pageSize"),
				@IJsfFlexAttribute(attribute="scrollPosition"),
				@IJsfFlexAttribute(attribute="borderColor"),
				@IJsfFlexAttribute(attribute="cornerRadius"),
				@IJsfFlexAttribute(attribute="downArrowDisabledSkin"),
				@IJsfFlexAttribute(attribute="downArrowDownSkin"),
				@IJsfFlexAttribute(attribute="downArrowOverSkin"),
				@IJsfFlexAttribute(attribute="downArrowUpSkin"),
				@IJsfFlexAttribute(attribute="fillAlphas"),
				@IJsfFlexAttribute(attribute="fillColors"),
				@IJsfFlexAttribute(attribute="highlightAlphas"),
                @IJsfFlexAttribute(attribute="iconColor"),
                @IJsfFlexAttribute(attribute="thumbDownSkin"),
				@IJsfFlexAttribute(attribute="thumbIcon"),
                @IJsfFlexAttribute(attribute="thumbOffset"),
				@IJsfFlexAttribute(attribute="thumbOverSkin"),
				@IJsfFlexAttribute(attribute="thumbUpSkin"),
				@IJsfFlexAttribute(attribute="trackColors"),
				@IJsfFlexAttribute(attribute="trackSkin"),
				@IJsfFlexAttribute(attribute="upArrowDisabledSkin"),
				@IJsfFlexAttribute(attribute="upArrowDownSkin"),
				@IJsfFlexAttribute(attribute="upArrowOverSkin"),
				@IJsfFlexAttribute(attribute="upArrowUpSkin")
		}
)
public abstract class AbstractFlexScrollBarTemplateRenderer extends AbstractFlexComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(AbstractFlexScrollBarTemplateRenderer.class, componentObj, null);
		
	}
	
}