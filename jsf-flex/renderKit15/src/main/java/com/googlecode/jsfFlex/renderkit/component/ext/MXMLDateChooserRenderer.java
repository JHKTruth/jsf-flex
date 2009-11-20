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
        type="com.googlecode.jsfFlex.MXMLDateChooser"
)
@JsfFlexAttributeProperties(
        mxmlComponentPackage="mx.controls",
        mxmlComponentName="DateChooser",
        mxmlComponentNodeAttributes={
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
                @JsfFlexAttribute(attribute="allowDisjointSelection"),
                @JsfFlexAttribute(attribute="allowMultipleSelection"),
                @JsfFlexAttribute(attribute="dayNames"),
                @JsfFlexAttribute(attribute="disabledDays"),
                @JsfFlexAttribute(attribute="disabledRanges"),
                @JsfFlexAttribute(attribute="displayedMonth"),
                @JsfFlexAttribute(attribute="displayedYear"),
                @JsfFlexAttribute(attribute="firstDayOfWeek"),
                @JsfFlexAttribute(attribute="maxYear"),
                @JsfFlexAttribute(attribute="minYear"),
                @JsfFlexAttribute(attribute="monthNames"),
                @JsfFlexAttribute(attribute="monthSymbol"),
                @JsfFlexAttribute(attribute="selectableRange"),
                @JsfFlexAttribute(attribute="selectedRanges"),
                @JsfFlexAttribute(attribute="showToday"),
                @JsfFlexAttribute(attribute="yearNavigationEnabled"),
                @JsfFlexAttribute(attribute="yearSymbol"),
                @JsfFlexAttribute(attribute="backgroundColor"),
                @JsfFlexAttribute(attribute="backgroundAlpha"),
                @JsfFlexAttribute(attribute="borderColor"),
                @JsfFlexAttribute(attribute="borderThickness"),
                @JsfFlexAttribute(attribute="color"),
                @JsfFlexAttribute(attribute="cornerRadius"),
                @JsfFlexAttribute(attribute="disabledColor"),
                @JsfFlexAttribute(attribute="disabledIconColor"),
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
                @JsfFlexAttribute(attribute="headerColors"),
                @JsfFlexAttribute(attribute="headerStyleName"),
                @JsfFlexAttribute(attribute="highlightAlphas"),
                @JsfFlexAttribute(attribute="horizontalGap"),
                @JsfFlexAttribute(attribute="iconColor"),
                @JsfFlexAttribute(attribute="leading"),
                @JsfFlexAttribute(attribute="nextMonthDisabledSkin"),
                @JsfFlexAttribute(attribute="nextMonthDownSkin"),
                @JsfFlexAttribute(attribute="nextMonthOverSkin"),
                @JsfFlexAttribute(attribute="nextMonthSkin"),
                @JsfFlexAttribute(attribute="nextMonthUpSkin"),
                @JsfFlexAttribute(attribute="nextYearDisabledSkin"),
                @JsfFlexAttribute(attribute="nextYearDownSkin"),
                @JsfFlexAttribute(attribute="nextYearOverSkin"),
                @JsfFlexAttribute(attribute="nextYearSkin"),
                @JsfFlexAttribute(attribute="nextYearUpSkin"),
                @JsfFlexAttribute(attribute="prevMonthDisabledSkin"),
                @JsfFlexAttribute(attribute="prevMonthDownSkin"),
                @JsfFlexAttribute(attribute="prevMonthOverSkin"),
                @JsfFlexAttribute(attribute="prevMonthSkin"),
                @JsfFlexAttribute(attribute="prevMonthUpSkin"),
                @JsfFlexAttribute(attribute="prevYearDisabledSkin"),
                @JsfFlexAttribute(attribute="prevYearDownSkin"),
                @JsfFlexAttribute(attribute="prevYearOverSkin"),
                @JsfFlexAttribute(attribute="prevYearSkin"),
                @JsfFlexAttribute(attribute="prevYearUpSkin"),
                @JsfFlexAttribute(attribute="rollOverColor"),
                @JsfFlexAttribute(attribute="rollOverIndicatorSkin"),
                @JsfFlexAttribute(attribute="selectionColor"),
                @JsfFlexAttribute(attribute="selectionIndicatorSkin"),
                @JsfFlexAttribute(attribute="textAlign"),
                @JsfFlexAttribute(attribute="textDecoration"),
                @JsfFlexAttribute(attribute="textIndent"),
                @JsfFlexAttribute(attribute="todayColor"),
                @JsfFlexAttribute(attribute="todayIndicatorSkin"),
                @JsfFlexAttribute(attribute="todayStyleName"),
                @JsfFlexAttribute(attribute="verticalGap"),
                @JsfFlexAttribute(attribute="weekDayStyleName"),
                @JsfFlexAttribute(attribute="change"),
                @JsfFlexAttribute(attribute="scroll")
        }
)
public final class MXMLDateChooserRenderer extends MXMLComponentRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeBegin(context, componentObj);
        
        _MXMLContract componentMXML = _MXMLContract.class.cast( componentObj );
        
        AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
        writer.mapFields(MXMLDateChooserRenderer.class, componentObj, null);
        writer.createPreMxml(componentMXML, MXMLDateChooserRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
                null);
        
    }
    
}
