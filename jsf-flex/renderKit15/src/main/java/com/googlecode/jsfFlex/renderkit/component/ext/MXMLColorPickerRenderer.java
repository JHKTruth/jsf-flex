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
		type="com.googlecode.jsfFlex.MXMLColorPicker"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="ColorPicker",
		mxmlComponentPackage="mx.controls",
		mxmlComponentNodeAttributes={
				@FlexComponentNodeAttribute(
						htmlType="input",
						typeAttributeValue="hidden",
						valueAttributeValue="selectedColor",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_selectedColor")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="colorField", byMethod=false),
				@JsfFlexAttribute(attribute="labelField", byMethod=false),
				@JsfFlexAttribute(attribute="showTextField", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundColor", byMethod=false),
				@JsfFlexAttribute(attribute="borderColor", byMethod=false),
				@JsfFlexAttribute(attribute="closeDuration", byMethod=false),
				@JsfFlexAttribute(attribute="closeEasingFunction", byMethod=false),
				@JsfFlexAttribute(attribute="color", byMethod=false),
				@JsfFlexAttribute(attribute="columnCount", byMethod=false),
				@JsfFlexAttribute(attribute="fillAlphas", byMethod=false),
				@JsfFlexAttribute(attribute="fillColors", byMethod=false),
				@JsfFlexAttribute(attribute="focusAlpha", byMethod=false),
				@JsfFlexAttribute(attribute="focusRoundedCorners", byMethod=false),
				@JsfFlexAttribute(attribute="fontAntiAliasType", byMethod=false),
				@JsfFlexAttribute(attribute="fontFamily", byMethod=false),
				@JsfFlexAttribute(attribute="fontGridFitType", byMethod=false),
				@JsfFlexAttribute(attribute="fontSharpness", byMethod=false),
				@JsfFlexAttribute(attribute="fontSize", byMethod=false),
				@JsfFlexAttribute(attribute="fontStyle", byMethod=false),
				@JsfFlexAttribute(attribute="fontThickness", byMethod=false),
				@JsfFlexAttribute(attribute="fontWeight", byMethod=false),
				@JsfFlexAttribute(attribute="highlightAlphas", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalGap", byMethod=false),
				@JsfFlexAttribute(attribute="leading", byMethod=false),
				@JsfFlexAttribute(attribute="openDuration", byMethod=false),
				@JsfFlexAttribute(attribute="openEasingFunction", byMethod=false),
				@JsfFlexAttribute(attribute="paddingBottom", byMethod=false),
				@JsfFlexAttribute(attribute="paddingLeft", byMethod=false),
				@JsfFlexAttribute(attribute="paddingRight", byMethod=false),
				@JsfFlexAttribute(attribute="paddingTop", byMethod=false),
				@JsfFlexAttribute(attribute="previewHeight", byMethod=false),
				@JsfFlexAttribute(attribute="previewWidth", byMethod=false),
				@JsfFlexAttribute(attribute="swatchBorderColor", byMethod=false),
				@JsfFlexAttribute(attribute="swatchBorderSize", byMethod=false),
				@JsfFlexAttribute(attribute="swatchGridBackgroundColor", byMethod=false),
				@JsfFlexAttribute(attribute="swatchGridBorderSize", byMethod=false),
				@JsfFlexAttribute(attribute="swatchHeight", byMethod=false),
				@JsfFlexAttribute(attribute="swatchHighlightColor", byMethod=false),
				@JsfFlexAttribute(attribute="swatchHighlightSize", byMethod=false),
				@JsfFlexAttribute(attribute="swatchPanelStyleName", byMethod=false),
				@JsfFlexAttribute(attribute="swatchWidth", byMethod=false),
				@JsfFlexAttribute(attribute="textAlign", byMethod=false),
				@JsfFlexAttribute(attribute="textDecoration", byMethod=false),
				@JsfFlexAttribute(attribute="textFieldWidth", byMethod=false),
				@JsfFlexAttribute(attribute="textIndent", byMethod=false),
				@JsfFlexAttribute(attribute="verticalGap", byMethod=false),
				@JsfFlexAttribute(attribute="change", byMethod=false),
				@JsfFlexAttribute(attribute="close", byMethod=false),
				@JsfFlexAttribute(attribute="enter", byMethod=false),
				@JsfFlexAttribute(attribute="itemRollOut", byMethod=false),
				@JsfFlexAttribute(attribute="itemRollOver", byMethod=false),
				@JsfFlexAttribute(attribute="open", byMethod=false)
		}
)
public final class MXMLColorPickerRenderer extends MXMLComboBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLColorPickerRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLColorPickerRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
