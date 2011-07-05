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
				@JsfFlexAttribute(attribute="columns", byMethod=false),
				@JsfFlexAttribute(attribute="draggableColumns", byMethod=false),
				@JsfFlexAttribute(attribute="editable", byMethod=false),
				@JsfFlexAttribute(attribute="editedItemPosition", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalScrollPosition", byMethod=false),
				@JsfFlexAttribute(attribute="imeMode", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditorInstance", byMethod=false),
				@JsfFlexAttribute(attribute="minColumnWidth", byMethod=false),
				@JsfFlexAttribute(attribute="resizableColumns", byMethod=false),
				@JsfFlexAttribute(attribute="sortableColumns", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundDisabledColor", byMethod=false),
				@JsfFlexAttribute(attribute="columnDropIndicatorSkin", byMethod=false),
				@JsfFlexAttribute(attribute="columnResizeSkin", byMethod=false),
				@JsfFlexAttribute(attribute="headerColors", byMethod=false),
				@JsfFlexAttribute(attribute="headerDragProxyStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="headerSeparatorSkin", byMethod=false),
				@JsfFlexAttribute(attribute="headerStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalGridLineColor", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalGridLines", byMethod=false),
				@JsfFlexAttribute(attribute="rollOverColor", byMethod=false),
				@JsfFlexAttribute(attribute="selectionColor", byMethod=false),
				@JsfFlexAttribute(attribute="sortArrowSkin", byMethod=false),
				@JsfFlexAttribute(attribute="stretchCursor", byMethod=false),
				@JsfFlexAttribute(attribute="verticalGridLineColor", byMethod=false),
				@JsfFlexAttribute(attribute="verticalGridLines", byMethod=false),
				@JsfFlexAttribute(attribute="columnStretch", byMethod=false),
				@JsfFlexAttribute(attribute="headerRelease", byMethod=false),
				@JsfFlexAttribute(attribute="headerShift", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditBegin", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditEnd", byMethod=false),
				@JsfFlexAttribute(attribute="itemFocusIn", byMethod=false),
				@JsfFlexAttribute(attribute="itemFocusOut", byMethod=false)
		}
)
public abstract class MXMLDataGridTemplateRenderer extends MXMLDataGridBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLDataGridTemplateRenderer.class, componentObj, null);
		
	}
	
}
