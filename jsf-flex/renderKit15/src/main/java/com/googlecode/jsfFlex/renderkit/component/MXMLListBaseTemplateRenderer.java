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
				@JsfFlexAttribute(attribute="allowDragSelection"),
				@JsfFlexAttribute(attribute="allowMultipleSelection"),
				@JsfFlexAttribute(attribute="columnCount"),
				@JsfFlexAttribute(attribute="columnWidth"),
				@JsfFlexAttribute(attribute="dataProvider", byMethod=true),
				@JsfFlexAttribute(attribute="dataTipField"),
				@JsfFlexAttribute(attribute="dataTipFunction"),
				@JsfFlexAttribute(attribute="dragEnabled"),
				@JsfFlexAttribute(attribute="dragMoveEnabled"),
				@JsfFlexAttribute(attribute="dropEnabled"),
				@JsfFlexAttribute(attribute="iconField"),
				@JsfFlexAttribute(attribute="iconFunction"),
				@JsfFlexAttribute(attribute="itemRenderer"),
				@JsfFlexAttribute(attribute="labelField"),
				@JsfFlexAttribute(attribute="labelFunction"),
                @JsfFlexAttribute(attribute="lockedColumnCount"),
                @JsfFlexAttribute(attribute="lockedRowCount"),
				@JsfFlexAttribute(attribute="menuSelectionMode"),
                @JsfFlexAttribute(attribute="offscreenExtraRowsOrColumns"),
				@JsfFlexAttribute(attribute="rowCount", byMethod=true),
				@JsfFlexAttribute(attribute="rowHeight"),
				@JsfFlexAttribute(attribute="selectable"),
				@JsfFlexAttribute(attribute="selectedIndex", byMethod=true),
				@JsfFlexAttribute(attribute="selectedIndices"),
				@JsfFlexAttribute(attribute="selectedItem"),
				@JsfFlexAttribute(attribute="selectedItems"),
				@JsfFlexAttribute(attribute="showDataTips"),
				@JsfFlexAttribute(attribute="variableRowHeight"),
				@JsfFlexAttribute(attribute="wordWrap"),
				@JsfFlexAttribute(attribute="alternatingItemColors"),
				@JsfFlexAttribute(attribute="dropIndicatorSkin"),
				@JsfFlexAttribute(attribute="focusAlpha"),
				@JsfFlexAttribute(attribute="focusRoundedCorners"),
				@JsfFlexAttribute(attribute="paddingBottom"),
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="paddingTop"),
				@JsfFlexAttribute(attribute="rollOverColor"),
				@JsfFlexAttribute(attribute="selectionColor"),
				@JsfFlexAttribute(attribute="selectionDisabledColor"),
				@JsfFlexAttribute(attribute="selectionDuration"),
				@JsfFlexAttribute(attribute="selectionEasingFunction"),
				@JsfFlexAttribute(attribute="textRollOverColor"),
				@JsfFlexAttribute(attribute="textSelectedColor"),
				@JsfFlexAttribute(attribute="useRollOver"),
				@JsfFlexAttribute(attribute="verticalAlign"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="dataChange"),
				@JsfFlexAttribute(attribute="itemDoubleClick"),
				@JsfFlexAttribute(attribute="itemRollOut"),
				@JsfFlexAttribute(attribute="itemRollOver"),
				@JsfFlexAttribute(attribute="itemClick")
		}
)
public abstract class MXMLListBaseTemplateRenderer extends MXMLScrollControlTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLListBaseTemplateRenderer.class, componentObj, null);
		
	}
	
}
