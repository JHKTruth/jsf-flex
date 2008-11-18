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

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLSimpleBase",
		type="com.googlecode.jsfFlex.MXMLDataGridColumn"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="DataGridColumn",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="dataField", byMethod=false),
				@JsfFlexAttribute(attribute="dataTipField", byMethod=false),
				@JsfFlexAttribute(attribute="dataTipFunction", byMethod=false),
				@JsfFlexAttribute(attribute="editable", byMethod=false),
				@JsfFlexAttribute(attribute="editorDataField", byMethod=false),
				@JsfFlexAttribute(attribute="editorHeightOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorUsesEnterKey", byMethod=false),
				@JsfFlexAttribute(attribute="editorWidthOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorXOffset", byMethod=false),
				@JsfFlexAttribute(attribute="editorYOffset", byMethod=false),
				@JsfFlexAttribute(attribute="headerRenderer", byMethod=false),
				@JsfFlexAttribute(attribute="headerText", byMethod=false),
				@JsfFlexAttribute(attribute="headerWordWrap", byMethod=false),
				@JsfFlexAttribute(attribute="imeMode", byMethod=false),
				@JsfFlexAttribute(attribute="itemEditor", byMethod=false),
				@JsfFlexAttribute(attribute="itemRenderer", byMethod=false),
				@JsfFlexAttribute(attribute="labelFunction", byMethod=false),
				@JsfFlexAttribute(attribute="minWidth", byMethod=false),
				@JsfFlexAttribute(attribute="rendererIsEditor", byMethod=false),
				@JsfFlexAttribute(attribute="resizable", byMethod=false),
				@JsfFlexAttribute(attribute="showDataTips", byMethod=false),
				@JsfFlexAttribute(attribute="sortable", byMethod=false),
				@JsfFlexAttribute(attribute="sortCompareFunction", byMethod=false),
				@JsfFlexAttribute(attribute="sortDescending", byMethod=false),
				@JsfFlexAttribute(attribute="visible", byMethod=false),
				@JsfFlexAttribute(attribute="wordWrap", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundColor", byMethod=false),
				@JsfFlexAttribute(attribute="color", byMethod=false),
				@JsfFlexAttribute(attribute="disabledColor", byMethod=false),
				@JsfFlexAttribute(attribute="fontAntiAliasType", byMethod=false),
				@JsfFlexAttribute(attribute="fontFamily", byMethod=false),
				@JsfFlexAttribute(attribute="fontGridFitType", byMethod=false),
				@JsfFlexAttribute(attribute="fontSharpness", byMethod=false),
				@JsfFlexAttribute(attribute="fontSize", byMethod=false),
				@JsfFlexAttribute(attribute="fontStyle", byMethod=false),
				@JsfFlexAttribute(attribute="fontThickness", byMethod=false),
				@JsfFlexAttribute(attribute="fontWeight", byMethod=false),
				@JsfFlexAttribute(attribute="headerStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="paddingLeft", byMethod=false),
				@JsfFlexAttribute(attribute="paddingRight", byMethod=false),
				@JsfFlexAttribute(attribute="textAlign", byMethod=false),
				@JsfFlexAttribute(attribute="textDecoration", byMethod=false),
				@JsfFlexAttribute(attribute="textIndent", byMethod=false)
		}
)
public class MXMLDataGridColumnRenderer extends MXMLComponentBaseRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLDataGridColumnRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLDataGridColumnRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}
	
}
