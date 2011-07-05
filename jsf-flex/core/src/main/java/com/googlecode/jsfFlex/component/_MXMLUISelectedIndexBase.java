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

import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedIndexAttribute;

/**
 * This class will process the needed actions of setting and retrieving of "selectedIndex" attribute<br>
 * within the Flex components.<br>
 * 
 * @JSFComponent
 * 	 class    = "com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUISelectedIndexBase"
 *   family   = "javax.faces.MXMLUISelectedIndexBase"
 *   desc	  = "Base component for MXMLInput components that contain selectedIndex attribute"
 *   template = "true"
 * 
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUISelectedIndexBase 
						extends MXMLUIInputBase 
						implements _MXMLUISelectedIndexAttribute {

	private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(MXMLUISelectedIndexBase.class);
	
	private static final String SELECTED_INDEX_ID_APPENDED = "_selectedIndex";
	private static final String SELECTED_INDEX_ATTR = "selectedIndex";
	
	private org.json.JSONObject initValue;
	
	{
		try{
			initValue = new org.json.JSONObject();
			initValue.put(ATTRIBUTE, SELECTED_INDEX_ATTR);
			
			_initValues.put(initValue);
			
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		try{
			if(getSelectedIndex() != null){
				initValue.put(VALUE, getSelectedIndex());
			}
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	java.util.Map requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String selectedIndexId = getId() + SELECTED_INDEX_ID_APPENDED;
    	String selectedIndexUpdateVal = (String) requestMap.get(selectedIndexId);
    	
    	if(selectedIndexUpdateVal != null){
    		setSelectedIndex(Integer.valueOf(selectedIndexUpdateVal));
    		setSubmittedValue(selectedIndexUpdateVal);
    	}

    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	javax.el.ValueExpression ve = getValueExpression(SELECTED_INDEX_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelectedIndex());
    		setSelectedIndex(null);
    	}
    	
    }
	
}
