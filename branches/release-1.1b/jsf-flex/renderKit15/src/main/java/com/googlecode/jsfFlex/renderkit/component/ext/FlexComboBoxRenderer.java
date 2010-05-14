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

import com.googlecode.jsfFlex.renderkit.annotation.IFlexComponentNodeAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComboBaseTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="FLEX_BASIC",
		family="javax.faces.FlexInput",
		type="com.googlecode.jsfFlex.FlexComboBox"
)
@IJsfFlexAttributeProperties(
		componentName="ComboBox",
		componentPackage="mx.controls",
		componentNodeAttributes={
				@IFlexComponentNodeAttribute(
						htmlType="input",
						typeAttributeValue="hidden",
						valueAttributeValue="text",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_text"),
				@IFlexComponentNodeAttribute(
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
				@IJsfFlexAttribute(attribute="dropdownFactory"),
				@IJsfFlexAttribute(attribute="dropdownWidth"),
				@IJsfFlexAttribute(attribute="itemRenderer"),
				@IJsfFlexAttribute(attribute="labelField"),
				@IJsfFlexAttribute(attribute="labelFunction"),
				@IJsfFlexAttribute(attribute="prompt"),
				@IJsfFlexAttribute(attribute="rowCount", byMethod=true),
				@IJsfFlexAttribute(attribute="alternatingItemColors"),
				@IJsfFlexAttribute(attribute="arrowButtonWidth"),
				@IJsfFlexAttribute(attribute="borderColor"),
				@IJsfFlexAttribute(attribute="borderThickness"),
				@IJsfFlexAttribute(attribute="closeDuration"),
				@IJsfFlexAttribute(attribute="closeEasingFunction"),
				@IJsfFlexAttribute(attribute="color"),
				@IJsfFlexAttribute(attribute="cornerRadius"),
				@IJsfFlexAttribute(attribute="disabledColor"),
                @IJsfFlexAttribute(attribute="disabledIconColor"),
				@IJsfFlexAttribute(attribute="dropDownBorderColor"),
				@IJsfFlexAttribute(attribute="dropDownStyleName"),
				@IJsfFlexAttribute(attribute="fillAlphas"),
				@IJsfFlexAttribute(attribute="fillColors"),
				@IJsfFlexAttribute(attribute="focusAlpha"),
				@IJsfFlexAttribute(attribute="focusRoundedCorners"),
				@IJsfFlexAttribute(attribute="fontAntiAliasType"),
				@IJsfFlexAttribute(attribute="fontFamily"),
				@IJsfFlexAttribute(attribute="fontGridFitType"),
				@IJsfFlexAttribute(attribute="fontSharpness"),
				@IJsfFlexAttribute(attribute="fontSize"),
				@IJsfFlexAttribute(attribute="fontStyle"),
				@IJsfFlexAttribute(attribute="fontThickness"),
				@IJsfFlexAttribute(attribute="fontWeight"),
				@IJsfFlexAttribute(attribute="highlightAlphas"),
                @IJsfFlexAttribute(attribute="iconColor"),
				@IJsfFlexAttribute(attribute="leading"),
				@IJsfFlexAttribute(attribute="openDuration"),
				@IJsfFlexAttribute(attribute="openEasingFunction"),
                @IJsfFlexAttribute(attribute="paddingTop"),
                @IJsfFlexAttribute(attribute="paddingBottom"),
				@IJsfFlexAttribute(attribute="paddingLeft"),
				@IJsfFlexAttribute(attribute="paddingRight"),
				@IJsfFlexAttribute(attribute="rollOverColor"),
				@IJsfFlexAttribute(attribute="selectionColor"),
				@IJsfFlexAttribute(attribute="selectionDuration"),
				@IJsfFlexAttribute(attribute="selectionEasingFunction"),
				@IJsfFlexAttribute(attribute="textAlign"),
				@IJsfFlexAttribute(attribute="textDecoration"),
				@IJsfFlexAttribute(attribute="textIndent"),
				@IJsfFlexAttribute(attribute="textRollOverColor"),
				@IJsfFlexAttribute(attribute="textSelectedColor"),
				@IJsfFlexAttribute(attribute="change"),
				@IJsfFlexAttribute(attribute="close"),
				@IJsfFlexAttribute(attribute="dataChange"),
				@IJsfFlexAttribute(attribute="enter"),
				@IJsfFlexAttribute(attribute="itemRollOut"),
				@IJsfFlexAttribute(attribute="itemRollOver"),
				@IJsfFlexAttribute(attribute="open"),
				@IJsfFlexAttribute(attribute="scroll")
		}
)
public final class FlexComboBoxRenderer extends AbstractFlexComboBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
		
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(FlexComboBoxRenderer.class, componentObj, null);
		writer.createPreMxml(componentFlex, FlexComboBoxRenderer.class.getAnnotation(IJsfFlexAttributeProperties.class).componentName(), 
				null);
		
	}

}
