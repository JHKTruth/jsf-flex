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
                @JsfFlexAttribute(attribute="automationName"),
				@JsfFlexAttribute(attribute="cachePolicy"),
				@JsfFlexAttribute(attribute="currentState"),
				@JsfFlexAttribute(attribute="doubleClickEnabled"),
				@JsfFlexAttribute(attribute="enabled"),
				@JsfFlexAttribute(attribute="explicitHeight"),
				@JsfFlexAttribute(attribute="explicitMaxHeight"),
				@JsfFlexAttribute(attribute="explicitMaxWidth"),
				@JsfFlexAttribute(attribute="explicitMinHeight"),
				@JsfFlexAttribute(attribute="explicitMinWidth"),
				@JsfFlexAttribute(attribute="explicitWidth"),
				@JsfFlexAttribute(attribute="focusEnabled"),
				@JsfFlexAttribute(attribute="height"),
				@JsfFlexAttribute(attribute="includeInLayout"),
				@JsfFlexAttribute(attribute="maxHeight"),
				@JsfFlexAttribute(attribute="maxWidth"),
				@JsfFlexAttribute(attribute="measuredHeight"),
				@JsfFlexAttribute(attribute="measuredMinHeight"),
				@JsfFlexAttribute(attribute="measuredMinWidth"),
				@JsfFlexAttribute(attribute="measuredWidth"),
				@JsfFlexAttribute(attribute="minHeight"),
				@JsfFlexAttribute(attribute="minWidth"),
				@JsfFlexAttribute(attribute="mouseFocusEnabled"),
				@JsfFlexAttribute(attribute="percentHeight"),
				@JsfFlexAttribute(attribute="percentWidth"),
				@JsfFlexAttribute(attribute="scaleX"),
				@JsfFlexAttribute(attribute="scaleY"),
				@JsfFlexAttribute(attribute="states"),
				@JsfFlexAttribute(attribute="styleName"),
				@JsfFlexAttribute(attribute="toolTip"),
				@JsfFlexAttribute(attribute="transitions"),
				@JsfFlexAttribute(attribute="validationSubField"),
				@JsfFlexAttribute(attribute="width"),
				@JsfFlexAttribute(attribute="x"),
				@JsfFlexAttribute(attribute="y"),
				@JsfFlexAttribute(attribute="bottom"),
				@JsfFlexAttribute(attribute="errorColor"),
				@JsfFlexAttribute(attribute="focusBlendMode"),
				@JsfFlexAttribute(attribute="focusSkin"),
				@JsfFlexAttribute(attribute="focusThickness"),
				@JsfFlexAttribute(attribute="horizontalCenter"),
				@JsfFlexAttribute(attribute="left"),
				@JsfFlexAttribute(attribute="right"),
				@JsfFlexAttribute(attribute="themeColor"),
				@JsfFlexAttribute(attribute="top"),
				@JsfFlexAttribute(attribute="verticalCenter"),
				@JsfFlexAttribute(attribute="addedEffect"),
				@JsfFlexAttribute(attribute="creationCompleteEffect"),
				@JsfFlexAttribute(attribute="focusInEffect"),
				@JsfFlexAttribute(attribute="focusOutEffect"),
				@JsfFlexAttribute(attribute="hideEffect"),
				@JsfFlexAttribute(attribute="mouseDownEffect"),
				@JsfFlexAttribute(attribute="mouseUpEffect"),
				@JsfFlexAttribute(attribute="moveEffect"),
				@JsfFlexAttribute(attribute="removedEffect"),
				@JsfFlexAttribute(attribute="resizeEffect"),
				@JsfFlexAttribute(attribute="rollOutEffect"),
				@JsfFlexAttribute(attribute="rollOverEffect"),
				@JsfFlexAttribute(attribute="showEffect"),
				@JsfFlexAttribute(attribute="add"),
				@JsfFlexAttribute(attribute="creationComplete"),
				@JsfFlexAttribute(attribute="currentStateChange"),
				@JsfFlexAttribute(attribute="currentStateChanging"),
				@JsfFlexAttribute(attribute="dragComplete"),
				@JsfFlexAttribute(attribute="dragDrop"),
				@JsfFlexAttribute(attribute="dragEnter"),
				@JsfFlexAttribute(attribute="dragExit"),
				@JsfFlexAttribute(attribute="dragOver"),
				@JsfFlexAttribute(attribute="effectEnd"),
				@JsfFlexAttribute(attribute="effectStart"),
				@JsfFlexAttribute(attribute="enterState"),
				@JsfFlexAttribute(attribute="exitState"),
				@JsfFlexAttribute(attribute="hide"),
				@JsfFlexAttribute(attribute="initialize"),
				@JsfFlexAttribute(attribute="invalid"),
				@JsfFlexAttribute(attribute="mouseDownOutside"),
				@JsfFlexAttribute(attribute="mouseWheelOutside"),
				@JsfFlexAttribute(attribute="move"),
				@JsfFlexAttribute(attribute="preinitialize"),
				@JsfFlexAttribute(attribute="record"),
				@JsfFlexAttribute(attribute="remove"),
				@JsfFlexAttribute(attribute="resize"),
				@JsfFlexAttribute(attribute="show"),
				@JsfFlexAttribute(attribute="toolTipCreate"),
				@JsfFlexAttribute(attribute="toolTipEnd"),
				@JsfFlexAttribute(attribute="toolTipHide"),
				@JsfFlexAttribute(attribute="toolTipShow"),
				@JsfFlexAttribute(attribute="toolTipShown"),
				@JsfFlexAttribute(attribute="toolTipStart"),
				@JsfFlexAttribute(attribute="updateComplete"),
				@JsfFlexAttribute(attribute="valid"),
				@JsfFlexAttribute(attribute="valueCommit")
		}
)
public abstract class MXMLComponentRenderer extends MXMLComponentBaseRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLComponentRenderer.class, componentObj, null);
		
	}
	
}
