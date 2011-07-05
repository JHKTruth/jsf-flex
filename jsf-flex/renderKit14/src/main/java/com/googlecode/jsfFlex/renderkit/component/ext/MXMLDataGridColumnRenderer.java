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

import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLInput"
 *  type        = "com.googlecode.jsfFlex.MXMLDataGridColumn"
 * 
 * @JsfFlexAttributes
 * 	dataField=true
 * 	dataTipField=false
 * 	dataTipFunction=false
 * 	editable=true
 * 	editorDataField=false
 * 	editorHeightOffset=false
 * 	editorUsesEnterKey=false
 * 	editorWidthOffset=false
 * 	editorXOffset=false
 * 	editorYOffset=false
 * 	headerRenderer=false
 * 	headerText=false
 * 	headerWordWrap=false
 * 	imeMode=false
 * 	itemEditor=false
 * 	itemRenderer=false
 * 	labelFunction=false
 * 	minWidth=false
 * 	rendererIsEditor=false
 * 	resizable=false
 * 	showDataTips=false
 * 	sortable=false
 * 	sortCompareFunction=false
 * 	sortDescending=false
 * 	visible=false
 * 	wordWrap=false
 * 	backgroundColor=false
 * 	color=false
 * 	disabledColor=false
 * 	fontAntiAliasType=false
 * 	fontFamily=false
 * 	fontGridFitType=false
 * 	fontSharpness=false
 * 	fontSize=false
 * 	fontStyle=false
 * 	fontThickness=false
 * 	fontWeight=false
 * 	headerStyleName=false
 * 	paddingLeft=false
 * 	paddingRight=false
 * 	textAlign=false
 * 	textDecoration=false
 * 	textIndent=false
 * 	
 * @author Ji Hoon Kim
 */
public final class MXMLDataGridColumnRenderer extends MXMLComponentBaseRenderer {
	
	private static final String MXML_DATA_GRID_COLUMN_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "DataGridColumn";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLDataGridColumnRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_DATA_GRID_COLUMN_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLDataGridColumnRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLDataGridColumnRenderer.class, componentObj, MXML_DATA_GRID_COLUMN_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}
	
}
