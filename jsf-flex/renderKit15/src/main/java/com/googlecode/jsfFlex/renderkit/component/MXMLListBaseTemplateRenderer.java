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
				@JsfFlexAttribute(attribute="allowDragSelection", byMethod=true),
				@JsfFlexAttribute(attribute="allowMultipleSelection", byMethod=true),
				@JsfFlexAttribute(attribute="columnCount", byMethod=true),
				@JsfFlexAttribute(attribute="columnWidth", byMethod=true),
				@JsfFlexAttribute(attribute="dataProvider", byMethod=true),
				@JsfFlexAttribute(attribute="dataTipField", byMethod=true),
				@JsfFlexAttribute(attribute="dataTipFunction", byMethod=true),
				@JsfFlexAttribute(attribute="dragEnabled", byMethod=true),
				@JsfFlexAttribute(attribute="dragMoveEnabled", byMethod=true),
				@JsfFlexAttribute(attribute="dropEnabled", byMethod=true),
				@JsfFlexAttribute(attribute="iconField", byMethod=true),
				@JsfFlexAttribute(attribute="iconFunction", byMethod=true),
				@JsfFlexAttribute(attribute="itemRenderer", byMethod=true),
				@JsfFlexAttribute(attribute="labelField", byMethod=true),
				@JsfFlexAttribute(attribute="labelFunction", byMethod=true),
				@JsfFlexAttribute(attribute="lockedColumnCount", byMethod=true),
				@JsfFlexAttribute(attribute="lockedRowCount", byMethod=true),
				@JsfFlexAttribute(attribute="menuSelectionMode", byMethod=true),
				@JsfFlexAttribute(attribute="rowCount", byMethod=true),
				@JsfFlexAttribute(attribute="rowHeight", byMethod=true),
				@JsfFlexAttribute(attribute="selectable", byMethod=true),
				@JsfFlexAttribute(attribute="selectedIndex", byMethod=true),
				@JsfFlexAttribute(attribute="selectedIndices", byMethod=true),
				@JsfFlexAttribute(attribute="selectedItem", byMethod=true),
				@JsfFlexAttribute(attribute="selectedItems", byMethod=true),
				@JsfFlexAttribute(attribute="showDataTips", byMethod=true),
				@JsfFlexAttribute(attribute="variableRowHeight", byMethod=true),
				@JsfFlexAttribute(attribute="wordWrap", byMethod=true),
				@JsfFlexAttribute(attribute="alternatingItemColors", byMethod=true),
				@JsfFlexAttribute(attribute="dropIndicatorSkin", byMethod=true),
				@JsfFlexAttribute(attribute="focusAlpha", byMethod=true),
				@JsfFlexAttribute(attribute="focusRoundedCorners", byMethod=true),
				@JsfFlexAttribute(attribute="paddingBottom", byMethod=true),
				@JsfFlexAttribute(attribute="paddingLeft", byMethod=true),
				@JsfFlexAttribute(attribute="paddingRight", byMethod=true),
				@JsfFlexAttribute(attribute="paddingTop", byMethod=true),
				@JsfFlexAttribute(attribute="rollOverColor", byMethod=true),
				@JsfFlexAttribute(attribute="selectionColor", byMethod=true),
				@JsfFlexAttribute(attribute="selectionDisabledColor", byMethod=true),
				@JsfFlexAttribute(attribute="selectionDuration", byMethod=true),
				@JsfFlexAttribute(attribute="selectionEasingFunction", byMethod=true),
				@JsfFlexAttribute(attribute="textRollOverColor", byMethod=true),
				@JsfFlexAttribute(attribute="textSelectedColor", byMethod=true),
				@JsfFlexAttribute(attribute="useRollOver", byMethod=true),
				@JsfFlexAttribute(attribute="verticalAlign", byMethod=true),
				@JsfFlexAttribute(attribute="change", byMethod=true),
				@JsfFlexAttribute(attribute="dataChange", byMethod=true),
				@JsfFlexAttribute(attribute="itemDoubleClick", byMethod=true),
				@JsfFlexAttribute(attribute="itemRollOut", byMethod=true),
				@JsfFlexAttribute(attribute="itemRollOver", byMethod=true),
				@JsfFlexAttribute(attribute="itemClick", byMethod=true)
		}
)
public class MXMLListBaseTemplateRenderer extends MXMLScrollControlTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLListBaseTemplateRenderer.class, componentObj, null);
		
	}
	
}
