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
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUISelectedDateAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexDateField",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUIDateField",
        type                =   "com.googlecode.jsfFlex.FlexUIDateField",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUIDateFieldTag",
        family              =   "javax.faces.FlexInput",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexDateField",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.AbstractFlexUIInputTagBase"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIDateField")
public abstract class AbstractFlexUIDateField 
						extends com.googlecode.jsfFlex.component.FlexUITextInputBase 
						implements IFlexUIBaseAttributes, IFlexUISelectedDateAttribute {
	
    private final static Log _log = LogFactory.getLog(AbstractFlexUIDateField.class);
    
    private static final String SELECTED_DATE_ATTR = "selectedDate";
    private static final String SELECTED_DATE_ID_APPENDED = "_selectedDate";
    
    private static final String DATE_FORMAT_DEFAULT = "EEE MMM dd HH:mm:ss z Z yyyy";
    
    @Override
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
            }catch(ParseException parsingException){
                setValid(false);
                context.addMessage(getId(), new FacesMessage("Parsing exception for value : " + selectedDateUpdateVal));
                _log.error("Parsing exception for value : " + selectedDateUpdateVal, parsingException);
            }
        }
    }
    
    @Override
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
