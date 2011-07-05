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
				@JsfFlexAttribute(attribute="id", byMethod=true),
				@JsfFlexAttribute(attribute="cachePolicy", byMethod=false),
				@JsfFlexAttribute(attribute="currentState", byMethod=false),
				@JsfFlexAttribute(attribute="doubleClickEnabled", byMethod=false),
				@JsfFlexAttribute(attribute="enabled", byMethod=false),
				@JsfFlexAttribute(attribute="explicitHeight", byMethod=false),
				@JsfFlexAttribute(attribute="explicitMaxHeight", byMethod=false),
				@JsfFlexAttribute(attribute="explicitMaxWidth", byMethod=false),
				@JsfFlexAttribute(attribute="explicitMinHeight", byMethod=false),
				@JsfFlexAttribute(attribute="explicitMinWidth", byMethod=false),
				@JsfFlexAttribute(attribute="explicitWidth", byMethod=false),
				@JsfFlexAttribute(attribute="focusEnabled", byMethod=false),
				@JsfFlexAttribute(attribute="height", byMethod=true),
				@JsfFlexAttribute(attribute="includeInLayout", byMethod=false),
				@JsfFlexAttribute(attribute="maxHeight", byMethod=false),
				@JsfFlexAttribute(attribute="maxWidth", byMethod=false),
				@JsfFlexAttribute(attribute="measuredHeight", byMethod=false),
				@JsfFlexAttribute(attribute="measuredMinHeight", byMethod=false),
				@JsfFlexAttribute(attribute="measuredMinWidth", byMethod=false),
				@JsfFlexAttribute(attribute="measuredWidth", byMethod=false),
				@JsfFlexAttribute(attribute="minHeight", byMethod=false),
				@JsfFlexAttribute(attribute="minWidth", byMethod=false),
				@JsfFlexAttribute(attribute="mouseFocusEnabled", byMethod=false),
				@JsfFlexAttribute(attribute="percentHeight", byMethod=false),
				@JsfFlexAttribute(attribute="percentWidth", byMethod=false),
				@JsfFlexAttribute(attribute="scaleX", byMethod=false),
				@JsfFlexAttribute(attribute="scaleY", byMethod=false),
				@JsfFlexAttribute(attribute="states", byMethod=false),
				@JsfFlexAttribute(attribute="styleName", byMethod=true),
				@JsfFlexAttribute(attribute="toolTip", byMethod=false),
				@JsfFlexAttribute(attribute="transitions", byMethod=false),
				@JsfFlexAttribute(attribute="validationSubField", byMethod=false),
				@JsfFlexAttribute(attribute="width", byMethod=true),
				@JsfFlexAttribute(attribute="x", byMethod=true),
				@JsfFlexAttribute(attribute="y", byMethod=true),
				@JsfFlexAttribute(attribute="bottom", byMethod=false),
				@JsfFlexAttribute(attribute="errorColor", byMethod=false),
				@JsfFlexAttribute(attribute="focusBlendMode", byMethod=false),
				@JsfFlexAttribute(attribute="focusSkin", byMethod=false),
				@JsfFlexAttribute(attribute="focusThickness", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalCenter", byMethod=false),
				@JsfFlexAttribute(attribute="left", byMethod=false),
				@JsfFlexAttribute(attribute="right", byMethod=false),
				@JsfFlexAttribute(attribute="themeColor", byMethod=false),
				@JsfFlexAttribute(attribute="top", byMethod=false),
				@JsfFlexAttribute(attribute="verticalCenter", byMethod=false),
				@JsfFlexAttribute(attribute="addedEffect", byMethod=false),
				@JsfFlexAttribute(attribute="creationCompleteEffect", byMethod=false),
				@JsfFlexAttribute(attribute="focusInEffect", byMethod=false),
				@JsfFlexAttribute(attribute="focusOutEffect", byMethod=false),
				@JsfFlexAttribute(attribute="hideEffect", byMethod=false),
				@JsfFlexAttribute(attribute="mouseDownEffect", byMethod=false),
				@JsfFlexAttribute(attribute="mouseUpEffect", byMethod=false),
				@JsfFlexAttribute(attribute="moveEffect", byMethod=false),
				@JsfFlexAttribute(attribute="removedEffect", byMethod=false),
				@JsfFlexAttribute(attribute="resizeEffect", byMethod=false),
				@JsfFlexAttribute(attribute="rollOutEffect", byMethod=false),
				@JsfFlexAttribute(attribute="rollOverEffect", byMethod=false),
				@JsfFlexAttribute(attribute="showEffect", byMethod=false),
				@JsfFlexAttribute(attribute="add", byMethod=false),
				@JsfFlexAttribute(attribute="creationComplete", byMethod=true),
				@JsfFlexAttribute(attribute="currentStateChange", byMethod=false),
				@JsfFlexAttribute(attribute="currentStateChanging", byMethod=false),
				@JsfFlexAttribute(attribute="dragComplete", byMethod=false),
				@JsfFlexAttribute(attribute="dragDrop", byMethod=false),
				@JsfFlexAttribute(attribute="dragEnter", byMethod=false),
				@JsfFlexAttribute(attribute="dragExit", byMethod=false),
				@JsfFlexAttribute(attribute="dragOver", byMethod=false),
				@JsfFlexAttribute(attribute="effectEnd", byMethod=false),
				@JsfFlexAttribute(attribute="effectStart", byMethod=false),
				@JsfFlexAttribute(attribute="enterState", byMethod=false),
				@JsfFlexAttribute(attribute="exitState", byMethod=false),
				@JsfFlexAttribute(attribute="hide", byMethod=false),
				@JsfFlexAttribute(attribute="initialize", byMethod=false),
				@JsfFlexAttribute(attribute="invalid", byMethod=false),
				@JsfFlexAttribute(attribute="mouseDownOutside", byMethod=false),
				@JsfFlexAttribute(attribute="mouseWheelOutside", byMethod=false),
				@JsfFlexAttribute(attribute="move", byMethod=false),
				@JsfFlexAttribute(attribute="preinitialize", byMethod=false),
				@JsfFlexAttribute(attribute="record", byMethod=false),
				@JsfFlexAttribute(attribute="remove", byMethod=false),
				@JsfFlexAttribute(attribute="resize", byMethod=false),
				@JsfFlexAttribute(attribute="show", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipCreate", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipEnd", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipHide", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipShow", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipShown", byMethod=false),
				@JsfFlexAttribute(attribute="toolTipStart", byMethod=false),
				@JsfFlexAttribute(attribute="updateComplete", byMethod=false),
				@JsfFlexAttribute(attribute="valid", byMethod=false),
				@JsfFlexAttribute(attribute="valueCommit", byMethod=false)
		}
)
public abstract class MXMLComponentRenderer extends MXMLComponentBaseRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLComponentRenderer.class, componentObj, null);
		
	}
	
}
