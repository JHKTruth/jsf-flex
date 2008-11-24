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
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class will process the needed actions of setting and retrieving of "value" attribute<br>
 * within the Flex components.<br>
 * 
 * @author Ji Hoon Kim
 */ 
public abstract class MXMLUIValueBase extends MXMLUIInputBase {
	
	private final static Log _log = LogFactory.getLog(MXMLUIValueBase.class);
	
	private static final String VALUE_ATTR = "value";
	private static final String VALUE_ID_APPENDED = "_value";
	
	private JSONObject initValue;
	
	{
		try{
			initValue = new JSONObject();
			initValue.put(ATTRIBUTE, VALUE_ATTR);
			
			_initValues.put(initValue);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		try{
			if(getValue() != null){
				initValue.put(VALUE, (String) getValue());
			}
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	HttpServletRequest httpRequest = (HttpServletRequest) context.getExternalContext().getRequest();
    	
    	String valueId = getId() + VALUE_ID_APPENDED;
    	String valueUpdateVal = httpRequest.getParameter(valueId);
    	
    	if(valueUpdateVal != null){
    		setValue(valueUpdateVal);
    		setSubmittedValue(valueUpdateVal);
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
		super.processUpdates(context);
		
		if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueBinding vb = getValueBinding(VALUE_ATTR);
		if(vb != null && !vb.isReadOnly(getFacesContext())){
			vb.setValue(getFacesContext(), getValue());
			setValue(null);
		}
		
	}
	
}