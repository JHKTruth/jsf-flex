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
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLInput",
		type="com.googlecode.jsfFlex.MXMLNumericStepper"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="NumericStepper",
		mxmlComponentPackage="mx.controls",
		mxmlComponentNodeAttributes={
				@FlexComponentNodeAttribute(
						htmlType="INPUT",
						typeAttributeValue="HIDDEN",
						valueAttributeValue="value",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_value")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="imeMode", byMethod=false),
				@JsfFlexAttribute(attribute="maxChars", byMethod=false),
				@JsfFlexAttribute(attribute="maximum", byMethod=false),
				@JsfFlexAttribute(attribute="minimum", byMethod=false),
				@JsfFlexAttribute(attribute="stepSize", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundAlpha", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundColor", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundImage", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundSize", byMethod=false),
				@JsfFlexAttribute(attribute="borderCapColor", byMethod=false),
				@JsfFlexAttribute(attribute="borderColor", byMethod=false),
				@JsfFlexAttribute(attribute="borderSides", byMethod=false),
				@JsfFlexAttribute(attribute="borderSkin", byMethod=false),
				@JsfFlexAttribute(attribute="borderStyle", byMethod=false),
				@JsfFlexAttribute(attribute="borderThickness", byMethod=false),
				@JsfFlexAttribute(attribute="color", byMethod=false),
				@JsfFlexAttribute(attribute="cornerRadius", byMethod=false),
				@JsfFlexAttribute(attribute="disabledColor", byMethod=false),
				@JsfFlexAttribute(attribute="downArrowDisabledSkin", byMethod=false),
				@JsfFlexAttribute(attribute="downArrowDownSkin", byMethod=false),
				@JsfFlexAttribute(attribute="downArrowOverSkin", byMethod=false),
				@JsfFlexAttribute(attribute="downArrowUpSkin", byMethod=false),
				@JsfFlexAttribute(attribute="dropShadowEnabled", byMethod=false),
				@JsfFlexAttribute(attribute="dropShadowColor", byMethod=false),
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
				@JsfFlexAttribute(attribute="leading", byMethod=false),
				@JsfFlexAttribute(attribute="paddingLeft", byMethod=false),
				@JsfFlexAttribute(attribute="paddingRight", byMethod=false),
				@JsfFlexAttribute(attribute="shadowDirection", byMethod=false),
				@JsfFlexAttribute(attribute="shadowDistance", byMethod=false),
				@JsfFlexAttribute(attribute="textAlign", byMethod=false),
				@JsfFlexAttribute(attribute="textDecoration", byMethod=false),
				@JsfFlexAttribute(attribute="textIndent", byMethod=false),
				@JsfFlexAttribute(attribute="upArrowDisabledSkin", byMethod=false),
				@JsfFlexAttribute(attribute="upArrowDownSkin", byMethod=false),
				@JsfFlexAttribute(attribute="upArrowOverSkin", byMethod=false),
				@JsfFlexAttribute(attribute="upArrowUpSkin", byMethod=false),
				@JsfFlexAttribute(attribute="change", byMethod=false),
				@JsfFlexAttribute(attribute="dataChange", byMethod=false)
		}
)
public final class MXMLNumericStepperRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLNumericStepperRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLNumericStepperRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
