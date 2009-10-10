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
				@JsfFlexAttribute(attribute="allowThumbOverlap"),
				@JsfFlexAttribute(attribute="allowTrackClick"),
				@JsfFlexAttribute(attribute="dataTipFormatFunction"),
				@JsfFlexAttribute(attribute="direction"),
				@JsfFlexAttribute(attribute="labels"),
				@JsfFlexAttribute(attribute="liveDragging"),
				@JsfFlexAttribute(attribute="maximum"),
				@JsfFlexAttribute(attribute="minimum"),
				@JsfFlexAttribute(attribute="showDataTip"),
				@JsfFlexAttribute(attribute="sliderDataTipClass"),
				@JsfFlexAttribute(attribute="sliderThumbClass"),
				@JsfFlexAttribute(attribute="snapInterval"),
				@JsfFlexAttribute(attribute="thumbCount"),
				@JsfFlexAttribute(attribute="tickInterval"),
                @JsfFlexAttribute(attribute="tickValues"),
                @JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="dataTipOffset"),
				@JsfFlexAttribute(attribute="dataTipPrecision"),
				@JsfFlexAttribute(attribute="dataTipStyleName"),
				@JsfFlexAttribute(attribute="fillAlphas"),
				@JsfFlexAttribute(attribute="fillColors"),
				@JsfFlexAttribute(attribute="labelOffset"),
				@JsfFlexAttribute(attribute="labelStyleName"),
				@JsfFlexAttribute(attribute="showTrackHighlight"),
				@JsfFlexAttribute(attribute="slideDuration"),
				@JsfFlexAttribute(attribute="slideEasingFunction"),
				@JsfFlexAttribute(attribute="thumbDisabledSkin"),
				@JsfFlexAttribute(attribute="thumbDownSkin"),
				@JsfFlexAttribute(attribute="thumbOffset"),
				@JsfFlexAttribute(attribute="thumbOverSkin"),
				@JsfFlexAttribute(attribute="thumbUpSkin"),
				@JsfFlexAttribute(attribute="tickColor"),
				@JsfFlexAttribute(attribute="tickLength"),
				@JsfFlexAttribute(attribute="tickOffset"),
				@JsfFlexAttribute(attribute="tickThickness"),
				@JsfFlexAttribute(attribute="trackColors"),
				@JsfFlexAttribute(attribute="trackHighlightSkin"),
				@JsfFlexAttribute(attribute="trackMargin"),
				@JsfFlexAttribute(attribute="trackSkin"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="thumbDrag"),
				@JsfFlexAttribute(attribute="thumbPress"),
				@JsfFlexAttribute(attribute="thumbRelease")
		}
)
public abstract class MXMLSliderTemplateRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLSliderTemplateRenderer.class, componentObj, null);
		
	}
	
}
