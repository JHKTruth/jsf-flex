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
package com.googlecode.jsfFlex.renderkit.component.ext;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.component.MXMLComponentRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLSimple"
 *  type        = "com.googlecode.jsfFlex.MXMLMenuBar"
 * 
 * @JsfFlexAttributes
 * 	dataDescriptor=false
 * 	dataProvider=true
 * 	iconField=false
 * 	labelField=false
 * 	labelFunction=false
 * 	menubarItems=false
 * 	menus=false
 * 	selectedIndex=true
 * 	showRoot=false
 * 	backgroundAlpha=false
 * 	backgroundColor=false
 * 	backgroundSkin=false
 * 	borderColor=false
 * 	color=false
 * 	cornerRadius=false
 * 	disabledColor=false
 * 	fillAlphas=false
 * 	fillColors=false
 * 	focusAlpha=false
 * 	focusRoundedCorners=false
 * 	fontAntiAliasType=false
 * 	fontFamily=false
 * 	fontGridFitType=false
 * 	fontSharpness=false
 * 	fontSize=false
 * 	fontStyle=false
 * 	fontThickness=false
 * 	fontWeight=false
 * 	highlightAlphas=false
 * 	itemDownSkin=false
 * 	itemOverSkin=false
 * 	itemUpSkin=false
 * 	leading=false
 * 	rollOverColor=false
 * 	selectionColor=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	itemClick=false
 * 	itemRollOut=false
 * 	itemRollOver=false
 * 	menuHide=false
 * 	menuShow=false
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLMenuBarRenderer extends MXMLComponentRenderer {
	
	private static final String MXML_MENU_BAR_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "MenuBar";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLMenuBarRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_MENU_BAR_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLMenuBarRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLMenuBarRenderer.class, componentObj, MXML_MENU_BAR_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}
	
}
