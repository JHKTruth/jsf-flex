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
package com.googlecode.jsfFlex.framework.component;

import com.googlecode.jsfFlex.framework.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.framework.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.framework.exception.ComponentBuildException;

/**
 * @author Ji Hoon Kim
 */
@JsfFlexAttributeProperties(
		componentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="layout", byMethod=false),
				@JsfFlexAttribute(attribute="status", byMethod=false),
				@JsfFlexAttribute(attribute="title", byMethod=false),
				@JsfFlexAttribute(attribute="titleIcon", byMethod=false),
				@JsfFlexAttribute(attribute="borderAlpha", byMethod=false),
				@JsfFlexAttribute(attribute="borderThicknessBottom", byMethod=false),
				@JsfFlexAttribute(attribute="borderThicknessLeft", byMethod=false),
				@JsfFlexAttribute(attribute="borderThicknessRight", byMethod=false),
				@JsfFlexAttribute(attribute="borderThicknessTop", byMethod=false),
				@JsfFlexAttribute(attribute="closeButtonDisabledSkin", byMethod=false),
				@JsfFlexAttribute(attribute="closeButtonDownSkin", byMethod=false),
				@JsfFlexAttribute(attribute="closeButtonOverSkin", byMethod=false),
				@JsfFlexAttribute(attribute="closeButtonUpSkin", byMethod=false),
				@JsfFlexAttribute(attribute="controlBarStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="footerColors", byMethod=false),
				@JsfFlexAttribute(attribute="headerColors", byMethod=false),
				@JsfFlexAttribute(attribute="headerHeight", byMethod=false),
				@JsfFlexAttribute(attribute="highlightAlphas", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalAlign", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalGap", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparency", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyBlur", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyColor", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyDuration", byMethod=false),
				@JsfFlexAttribute(attribute="roundedBottomCorners", byMethod=false),
				@JsfFlexAttribute(attribute="statusStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="titleBackgroundSkin", byMethod=false),
				@JsfFlexAttribute(attribute="titleStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="verticalAlign", byMethod=false),
				@JsfFlexAttribute(attribute="verticalGap", byMethod=false),
				@JsfFlexAttribute(attribute="resizeEndEffect", byMethod=false),
				@JsfFlexAttribute(attribute="resizeStartEffect", byMethod=false),
				@JsfFlexAttribute(attribute="close", byMethod=false)
		}
)
public abstract class MXMLPanelTemplate extends MXMLContainerTemplate {
	
	public MXMLPanelTemplate(){
		super();
	}
	
	public void buildComponentBegin(Object componentObj) throws ComponentBuildException {
		super.buildComponentBegin(componentObj);
		
		mapFields(MXMLPanelTemplate.class, componentObj, null);
		
	}
	
}