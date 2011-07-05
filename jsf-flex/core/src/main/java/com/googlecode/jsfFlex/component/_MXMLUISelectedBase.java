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
package com.googlecode.jsfFlex.component;

import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedAttribute;

/**
 * This class will process the needed actions of setting and retrieving of "selected" attribute<br>
 * within the Flex components.<br>
 * 
 * @JSFComponent
 * 	 class    = "com.googlecode.jsfFlex.component.MXMLUISelectedBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUISelectedBase"
 *   family   = "javax.faces.MXMLUISelectedBase"
 *   desc	  = "Base component for MXMLInput components that contain selected attribute"
 *   template = "true"
 * 
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUISelectedBase 
							extends MXMLUIInputBase 
							implements _MXMLUISelectedAttribute {
	
	private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(MXMLUISelectedBase.class);
	
	private static final String SELECTED_ID_APPENDED = "_selected";
	private static final String SELECTED_ATTR = "selected";
	
	private org.json.JSONObject initValue;
	
	{
		try{
			initValue = new org.json.JSONObject();
			initValue.put(ATTRIBUTE, SELECTED_ATTR);
			
			_initValues.put(initValue);
			
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		try{
			if(getSelected() != null){
				initValue.put(VALUE, getSelected());
			}
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	java.util.Map requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String selectedId = getId() + SELECTED_ID_APPENDED;
    	String selectedUpdateVal = (String) requestMap.get(selectedId);
    	
    	if(selectedUpdateVal != null){
    		setSelected(Boolean.valueOf(selectedUpdateVal));
    		setSubmittedValue(selectedUpdateVal);
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	javax.el.ValueExpression ve = getValueExpression(SELECTED_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelected());
    		setSelected(null);
    	}
    	
    }
	
}
