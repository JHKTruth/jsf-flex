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
		type="com.googlecode.jsfFlex.MXMLDateField"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="DateField",
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
                        valueAttributeValue="selectedDate",
                        isValueDynamic=true,
                        isValueNested=false,
                        valueNestedValues={},
                        nameAttributeValue="id",
                        isNameDynamic=true,
                        nameAppend="_selectedDate")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="dayNames"),
				@JsfFlexAttribute(attribute="disabledDays"),
				@JsfFlexAttribute(attribute="disabledRanges"),
				@JsfFlexAttribute(attribute="displayedMonth"),
				@JsfFlexAttribute(attribute="displayedYear"),
                @JsfFlexAttribute(attribute="dropdownFactory"),
                @JsfFlexAttribute(attribute="firstDayOfWeek"),
				@JsfFlexAttribute(attribute="formatString"),
				@JsfFlexAttribute(attribute="labelFunction"),
				@JsfFlexAttribute(attribute="maxYear"),
				@JsfFlexAttribute(attribute="minYear"),
				@JsfFlexAttribute(attribute="monthNames"),
				@JsfFlexAttribute(attribute="monthSymbol"),
				@JsfFlexAttribute(attribute="parseFunction"),
				@JsfFlexAttribute(attribute="selectableRange"),
				@JsfFlexAttribute(attribute="showToday"),
				@JsfFlexAttribute(attribute="yearNavigationEnabled"),
				@JsfFlexAttribute(attribute="yearSymbol"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="borderThickness"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="dateChooserStyleName"),
				@JsfFlexAttribute(attribute="disabledColor"),
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
                @JsfFlexAttribute(attribute="iconColor"),
				@JsfFlexAttribute(attribute="headerColors"),
				@JsfFlexAttribute(attribute="headerStyleName"),
				@JsfFlexAttribute(attribute="highlightAlphas"),
				@JsfFlexAttribute(attribute="leading"),
				@JsfFlexAttribute(attribute="paddingLeft"),
				@JsfFlexAttribute(attribute="paddingRight"),
				@JsfFlexAttribute(attribute="rollOverColor"),
				@JsfFlexAttribute(attribute="selectionColor"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="todayColor"),
				@JsfFlexAttribute(attribute="todayStyleName"),
				@JsfFlexAttribute(attribute="weekDayStyleName"),
				@JsfFlexAttribute(attribute="change"),
				@JsfFlexAttribute(attribute="close"),
				@JsfFlexAttribute(attribute="dataChange"),
				@JsfFlexAttribute(attribute="open"),
				@JsfFlexAttribute(attribute="scroll")
		}
)
public final class MXMLDateFieldRenderer extends MXMLComboBaseTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLDateFieldRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLDateFieldRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
