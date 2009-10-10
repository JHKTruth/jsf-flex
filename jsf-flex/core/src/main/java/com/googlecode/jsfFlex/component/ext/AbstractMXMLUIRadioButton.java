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

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIGroupNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedValueAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlRadioButton",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIRadioButton",
        type                =   "com.googlecode.jsfFlex.MXMLUIRadioButton",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIRadioButtonTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLRadioButton",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIRadioButton 
						extends com.googlecode.jsfFlex.component.MXMLUISelectedBase
						implements _MXMLUIButtonAttributes, _MXMLUIGroupNameAttribute, _MXMLUISelectedValueAttribute,
                        _MXMLUIDisabledIconColorAttribute, _MXMLUIIconColorAttribute {
	
	private static final String SELECTED_VALUE_ATTR = "selectedValue";
	private static final String SELECTED_VALUE_ID_APPENDED = "_selectedValue";
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	/*
    	 *	So to make it easy for databinding, will utilize groupName + _selectedValue
    	 *	as the search parameter when setting the selectedValue variable.
    	 *	This way the developer has to simply dataBind on selectedValue per groupName entry
    	 *	to check which value was selected by user.
    	 */
    	
    	String selectedValueId = getGroupName() + SELECTED_VALUE_ID_APPENDED;
    	String selectedValueUpdateVal = requestMap.get(selectedValueId);
    	if(selectedValueUpdateVal != null){
    		setSelectedValue(selectedValueUpdateVal);
    		setSubmittedValue(selectedValueUpdateVal);
    	}
    	
    	if(getValue() == null){
    		setSelected(false);
    	}else if(getSelectedValue() != null){
    		setSelected(Boolean.valueOf(getSelectedValue().equals(getValue())));
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueExpression ve = getValueExpression(SELECTED_VALUE_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelectedValue());
    		setSelectedValue(null);
    	}
    	
    }
	
	/**
	 * Current value.
	 */
    @JSFProperty(
            inheritTag  =   true,
            desc        =   "Current value."
    )
	public Object getValue(){
		return super.getValue();
	}
	
}
