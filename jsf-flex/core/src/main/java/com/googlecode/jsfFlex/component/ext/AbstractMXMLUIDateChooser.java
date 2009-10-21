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
package com.googlecode.jsfFlex.component.ext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIAllowDisjointSelectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAllowMultipleSelectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDayNamesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledDaysAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledRangesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisplayedMonthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisplayedYearAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFirstDayOfWeekAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHighlightAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaxYearAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMinYearAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMonthNamesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMonthSymbolAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextMonthDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextMonthDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextMonthOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextMonthSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextMonthUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextYearDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextYearDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextYearOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextYearSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINextYearUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevMonthDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevMonthDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevMonthOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevMonthSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevMonthUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevYearDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevYearDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevYearOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevYearSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPrevYearUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverIndicatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectableRangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedDateAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedRangesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionIndicatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowTodayAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITodayColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITodayIndicatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITodayStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWeekDayStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIYearNavigationEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIYearSymbolAttribute;
import com.googlecode.jsfFlex.component.MXMLUIInputBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlDateChooser",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIDateChooser",
        type                =   "com.googlecode.jsfFlex.MXMLUIDateChooser",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIDateChooserTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLDateChooser",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIDateChooser 
                            extends MXMLUIInputBase 
                            implements _MXMLUIBaseAttributes, _MXMLUIAllowDisjointSelectionAttribute, _MXMLUIAllowMultipleSelectionAttribute, 
                            _MXMLUIDayNamesAttribute, _MXMLUIDisabledDaysAttribute, _MXMLUIDisabledRangesAttribute, 
                            _MXMLUIDisplayedMonthAttribute, _MXMLUIDisplayedYearAttribute, _MXMLUIFirstDayOfWeekAttribute, 
                            _MXMLUIMaxYearAttribute, _MXMLUIMinYearAttribute, _MXMLUIMonthNamesAttribute, _MXMLUIMonthSymbolAttribute, 
                            _MXMLUISelectableRangeAttribute, _MXMLUISelectedDateAttribute, _MXMLUISelectedRangesAttribute, 
                            _MXMLUIShowTodayAttribute, _MXMLUIYearNavigationEnabledAttribute, _MXMLUIYearSymbolAttribute, 
                            _MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBorderColorAttribute, 
                            _MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, 
                            _MXMLUIDisabledColorAttribute, _MXMLUIDisabledIconColorAttribute, _MXMLUIFillAlphasAttribute, 
                            _MXMLUIFillColorsAttribute, _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, 
                            _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, 
                            _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, _MXMLUIFontStyleAttribute, 
                            _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, _MXMLUIHeaderColorsAttribute, 
                            _MXMLUIHeaderStyleNameAttribute, _MXMLUIHighlightAlphasAttribute, _MXMLUIHorizontalGapAttribute, 
                            _MXMLUIIconColorAttribute, _MXMLUILeadingAttribute, _MXMLUINextMonthDisabledSkinAttribute, 
                            _MXMLUINextMonthDownSkinAttribute, _MXMLUINextMonthOverSkinAttribute, _MXMLUINextMonthSkinAttribute, 
                            _MXMLUINextMonthUpSkinAttribute, _MXMLUINextYearDisabledSkinAttribute, _MXMLUINextYearDownSkinAttribute, 
                            _MXMLUINextYearOverSkinAttribute, _MXMLUINextYearSkinAttribute, _MXMLUINextYearUpSkinAttribute, 
                            _MXMLUIPrevMonthDisabledSkinAttribute, _MXMLUIPrevMonthDownSkinAttribute, _MXMLUIPrevMonthOverSkinAttribute, 
                            _MXMLUIPrevMonthSkinAttribute, _MXMLUIPrevMonthUpSkinAttribute, _MXMLUIPrevYearDisabledSkinAttribute, 
                            _MXMLUIPrevYearDownSkinAttribute, _MXMLUIPrevYearOverSkinAttribute, _MXMLUIPrevYearSkinAttribute, 
                            _MXMLUIPrevYearUpSkinAttribute, _MXMLUIRollOverColorAttribute, _MXMLUIRollOverIndicatorSkinAttribute, 
                            _MXMLUISelectionColorAttribute, _MXMLUISelectionIndicatorSkinAttribute, _MXMLUITextAlignAttribute, 
                            _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, _MXMLUITodayColorAttribute, 
                            _MXMLUITodayIndicatorSkinAttribute, _MXMLUITodayStyleNameAttribute, _MXMLUIVerticalGapAttribute, 
                            _MXMLUIWeekDayStyleNameAttribute, _MXMLUIChangeAttribute, _MXMLUIScrollAttribute {
    
    private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(AbstractMXMLUIDateChooser.class);
    
    private static final String SELECTED_DATE_ATTR = "selectedDate";
    private static final String SELECTED_DATE_ID_APPENDED = "_selectedDate";
    
    private static final String DATE_TYPE_INIT_VALUE = "Date";
    private static final String DATE_FORMAT_DEFAULT = "EEE MMM dd HH:mm:ss z Z yyyy";
    
    private org.json.JSONObject initValue;
    
    protected void populateComponentInitValues(){
        try{
            if(getSelectedDate() != null){
                getInitValue().put(VALUE, com.googlecode.jsfFlex.util.MXMLJsfUtil.convertJavaDateToASDateConstructorArguments( getSelectedDate() ));
            }
        }catch(org.json.JSONException jsonException){
            _log.info("Error while formatting to JSON content", jsonException);
        }
    }
    
    private synchronized org.json.JSONObject getInitValue(){
        if(initValue == null){
            try{
                initValue = new org.json.JSONObject();
                initValue.put(ATTRIBUTE, SELECTED_DATE_ATTR);
                initValue.put(SPECIFIC_OBJECT_TYPE_INIT, DATE_TYPE_INIT_VALUE);
                
                _initValues.put(initValue);
                
            }catch(org.json.JSONException jsonException){
                _log.info("Error while formatting to JSON content", jsonException);
            }
        }
        return initValue;
    }
    
    public void decode(FacesContext context) {
        super.decode(context);
        
        java.util.Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String selectedDateId = getId() + SELECTED_DATE_ID_APPENDED;
        String selectedDateUpdateVal = requestMap.get(selectedDateId);
        
        if(selectedDateUpdateVal != null && selectedDateUpdateVal.length() > 0){
            /*
             * HACK: Since ActionScript returns date in format of "Thu Aug 23 00:00:00 GMT-0700 2009"
             * and "EEE MMM dd HH:mm:ss zZ yyyy" pattern doesn't seem to match it within SimpleDateFormat,
             * place a space between z + Z
             */
            int dashIndex = selectedDateUpdateVal.indexOf("-");
            if(dashIndex != -1){
                selectedDateUpdateVal = selectedDateUpdateVal.substring(0, dashIndex) + " " + selectedDateUpdateVal.substring(dashIndex);
            }
            Calendar instance = Calendar.getInstance();
            try{
                DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DEFAULT);
                dateFormat.setLenient(true);
                instance.setTime( dateFormat.parse(selectedDateUpdateVal) );
                setSelectedDate(instance);
                setSubmittedValue(selectedDateUpdateVal);
            }catch(ParseException parsingException){
                setValid(false);
                context.addMessage(getId(), new FacesMessage("Parsing exception for value : " + selectedDateUpdateVal));
                _log.error("Parsing exception for value : " + selectedDateUpdateVal, parsingException);
            }
        }
    }
    
    public void processUpdates(FacesContext context) {
        super.processUpdates(context);
        
        if (!isRendered() || !isValid()){
            return;
        }
        
        javax.el.ValueExpression ve = getValueExpression(SELECTED_DATE_ATTR);
        
        if(ve != null && !ve.isReadOnly(context.getELContext())){
            ve.setValue(context.getELContext(), getSelectedDate());
            setSelectedDate(null);
        }
        
    }
    
}
