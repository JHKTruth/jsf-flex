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

import com.googlecode.jsfFlex.renderkit.annotation.FlexComponentNodeAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComboBaseTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLInput",
		type="com.googlecode.jsfFlex.MXMLComboBox"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="ComboBox",
		mxmlComponentPackage="mx.controls",
		mxmlComponentNodeAttributes={
				@FlexComponentNodeAttribute(
						htmlType="input",
						typeAttributeValue="hidden",
						valueAttributeValue="text",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_text"),
				@FlexComponentNodeAttribute(
						htmlType="input",
						typeAttributeValue="hidden",
						valueAttributeValue="selectedIndex",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_selectedIndex")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="dropdownFactory"),
				@JsfFlexAttribute(attribute="dropdownWidth"),
				@JsfFlexAttribute(attribute="itemRenderer"),
				@JsfFlexAttribute(attribute="labelField"),
				@JsfFlexAttribute(attribute="labelFunction"),
				@JsfFlexAttribute(attribute="prompt"),
				@JsfFlexAttribute(attribute="rowCount", byMethod=true),
				@JsfFlexAttribute(attribute="alternatingItemColors"),
				@JsfFlexAttribute(attribute="arrowButtonWidth"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="borderThickness"),
				@JsfFlexAttribute(attribute="closeDuration"),
				@JsfFlexAttribute(attribute="closeEasingFunction"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
                @JsfFlexAttribute(attribute="disabledIconColor"),
				@JsfFlexAttribute(attribute="dropDownBorderColor"),
				@JsfFlexAttribute(attribute="dropDownStyleName"),
				@JsfFlexAttribute(attribute="fillAlphas"),
				@JsfFlexAttribute(attribute="fillColors"),
				@JsfFlexAttribute(attribute="focusAlpha"),
				@JsfFlexAttribute(attribute="focusRoundedCorners"),
				@JsfFlexAttribute(attribute="fontAntiAliasType"),
				@JsfFlexAttribute(attribute="fontFamily"),
				@JsfFlexAttribute(attribute="fontGridFitType"),
				@JsfFlexAttribute(attribute="fontSharpness"),
				@JsfFlexAttribute(attribute="fontSize"),
				@JsfFlexAttribute(attribute="fontStyle"),
				@JsfFlexAttribute(attribute="fontThickness"),
				@JsfFlexAttribute(attribute="fontWeight"),
				@JsfFlexAttribute(attribute="highlightAlphas"),
                @JsfFlexAttribute(attribute="iconColor"),
				@JsfFlexAttribute(attribute="leading"),
				@JsfFlexAttribute(attribute="openDuration"),
				@JsfFlexAttribute(attribute="openEasingFunction"),
                @JsfFlexAttribute(attribute="paddingTop"),
                @JsfFlexAttribute(attribute="paddingBottom"),
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="rollOverColor"),
				@JsfFlexAttribute(attribute="selectionColor"),
				@JsfFlexAttribute(attribute="selectionDuration"),
				@JsfFlexAttribute(attribute="selectionEasingFunction"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="textRollOverColor"),
				@JsfFlexAttribute(attribute="textSelectedColor"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="close"),
				@JsfFlexAttribute(attribute="dataChange"),
				@JsfFlexAttribute(attribute="enter"),
				@JsfFlexAttribute(attribute="itemRollOut"),
				@JsfFlexAttribute(attribute="itemRollOver"),
				@JsfFlexAttribute(attribute="open"),
				@JsfFlexAttribute(attribute="scroll")
		}
)
public final class MXMLComboBoxRenderer extends MXMLComboBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = _MXMLContract.class.cast( componentObj );
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLComboBoxRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLComboBoxRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
