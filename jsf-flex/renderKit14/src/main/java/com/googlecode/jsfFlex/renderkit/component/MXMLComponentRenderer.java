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

import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;

/**
 * @JsfFlexAttributes
 * 	id=true
 * 	cachePolicy=false
 * 	currentState=false
 * 	doubleClickEnabled=false
 * 	enabled=false
 * 	explicitHeight=false
 * 	explicitMaxHeight=false
 * 	explicitMaxWidth=false
 * 	explicitMinHeight=false
 * 	explicitMinWidth=false
 * 	explicitWidth=false
 * 	focusEnabled=false
 * 	height=true
 * 	includeInLayout=false
 * 	maxHeight=false
 * 	maxWidth=false
 * 	measuredHeight=false
 * 	measuredMinHeight=false
 * 	measuredMinWidth=false
 * 	measuredWidth=false
 * 	minHeight=false
 * 	minWidth=false
 * 	mouseFocusEnabled=false
 * 	percentHeight=false
 * 	percentWidth=false
 * 	scaleX=false
 * 	scaleY=false
 * 	states=false
 * 	styleName=true
 * 	toolTip=false
 * 	transitions=false
 * 	validationSubField=false
 * 	width=true
 * 	x=true
 * 	y=true
 * 	bottom=false
 * 	errorColor=false
 * 	focusBlendMode=false
 * 	focusSkin=false
 * 	focusThickness=false
 * 	horizontalCenter=false
 * 	left=false
 * 	right=false
 * 	themeColor=false
 * 	top=false
 * 	verticalCenter=false
 * 	addedEffect=false
 * 	creationCompleteEffect=false
 * 	focusInEffect=false
 * 	focusOutEffect=false
 * 	hideEffect=false
 * 	mouseDownEffect=false
 * 	mouseUpEffect=false
 * 	moveEffect=false
 * 	removedEffect=false
 * 	resizeEffect=false
 * 	rollOutEffect=false
 * 	rollOverEffect=false
 * 	showEffect=false
 * 	add=false
 * 	creationComplete=true
 * 	currentStateChange=false
 * 	currentStateChanging=false
 * 	dragComplete=false
 * 	dragDrop=false
 * 	dragEnter=false
 * 	dragExit=false
 * 	dragOver=false
 * 	effectEnd=false
 * 	effectStart=false
 * 	enterState=false
 * 	exitState=false
 * 	hide=false
 * 	initialize=false
 * 	invalid=false
 * 	mouseDownOutside=false
 * 	mouseWheelOutside=false
 * 	move=false
 * 	preinitialize=false
 * 	record=false
 * 	remove=false
 * 	resize=false
 * 	show=false
 * 	toolTipCreate=false
 * 	toolTipEnd=false
 * 	toolTipHide=false
 * 	toolTipShow=false
 * 	toolTipShown=false
 * 	toolTipStart=false
 * 	updateComplete=false
 * 	valid=false
 * 	valueCommit=false
 * 
 * @author Ji Hoon Kim
 */
public abstract class MXMLComponentRenderer extends MXMLComponentBaseRenderer {
	
	private static final String MXML_COMPONENT_REPLACE_MAPPING;
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLComponentRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_COMPONENT_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLComponentRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLComponentRenderer.class, componentObj, MXML_COMPONENT_REPLACE_MAPPING);
		
	}
	
}
