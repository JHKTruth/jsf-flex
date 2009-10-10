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
		type="com.googlecode.jsfFlex.MXMLTextInput"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="TextInput",
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
						valueAttributeValue="htmlText",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_htmlText")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="condenseWhite"),
				@JsfFlexAttribute(attribute="data"),
				@JsfFlexAttribute(attribute="displayAsPassword"),
				@JsfFlexAttribute(attribute="editable", byMethod=true),
				@JsfFlexAttribute(attribute="horizontalScrollPosition"),
				@JsfFlexAttribute(attribute="imeMode"),
				@JsfFlexAttribute(attribute="listData"),
				@JsfFlexAttribute(attribute="maxChars"),
				@JsfFlexAttribute(attribute="restrict"),
				@JsfFlexAttribute(attribute="selectionBeginIndex"),
				@JsfFlexAttribute(attribute="selectionEndIndex"),
				@JsfFlexAttribute(attribute="backgroundAlpha"),
				@JsfFlexAttribute(attribute="backgroundColor"),
				@JsfFlexAttribute(attribute="backgroundImage"),
				@JsfFlexAttribute(attribute="backgroundSize"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="borderSides"),
				@JsfFlexAttribute(attribute="borderSkin"),
				@JsfFlexAttribute(attribute="borderStyle"),
				@JsfFlexAttribute(attribute="borderThickness"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
				@JsfFlexAttribute(attribute="dropShadowColor"),
				@JsfFlexAttribute(attribute="dropShadowEnabled"),
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
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="shadowDirection"),
				@JsfFlexAttribute(attribute="shadowDistance"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="dataChange"),
				@JsfFlexAttribute(attribute="enter"),
				@JsfFlexAttribute(attribute="textInput")
		}
)
public final class MXMLTextInputRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLTextInputRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLTextInputRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}
	
}
