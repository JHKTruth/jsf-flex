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

import com.googlecode.jsfFlex.component.attributes._MXMLUIHtmlTextAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextBindingAttribute;

/**
 * This class will process the needed actions of setting and retrieving of "htmlText" attribute<br>
 * within the Flex components. Note that since this class invokes the super method of populateComponentInitValues<br>
 * it will also set and retrieve values of "text" attribute of the component [if it exists].<br>
 * 
 * @JSFComponent
 * 	 class    = "com.googlecode.jsfFlex.component.MXMLUIHtmlTextInputBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUIHtmlTextInputBase"
 *   family   = "javax.faces.MXMLUIHtmlTextInputBase"
 *   desc	  = "Base component for MXMLInput components that contain htmlText attribute"
 *   template = "true"
 * 
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUIHtmlTextInputBase 
							extends com.googlecode.jsfFlex.component.MXMLUITextInputBase 
							implements _MXMLUIHtmlTextAttribute, _MXMLUITextBindingAttribute {

	private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(MXMLUIHtmlTextInputBase.class);
	
	private static final String HTML_TEXT_ATTR = "htmlText";
	private static final String HTML_TEXT_ID_APPENDED = "_htmlText";
	
	private org.json.JSONObject initValue;
	
	{
		try{
			initValue = new org.json.JSONObject();
			initValue.put(ATTRIBUTE, HTML_TEXT_ATTR);
			
			_initValues.put(initValue);
			
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		
		try{
			if(getTextBinding().equals(HTML_TEXT_ATTR) && getHtmlText() != null){
				initValue.put(VALUE, com.googlecode.jsfFlex.util.MXMLJsfUtil.escapeCharacters( getHtmlText() ));
			}else{
				super.populateComponentInitValues();
			}
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
    
    public void decode(FacesContext context) {
    	super.decode(context);
    	
    	java.util.Map requestMap = context.getExternalContext().getRequestParameterMap();
    	/*
    	 * since there exists two possible returned values [text and htmlText],
    	 * the attribute will be appended to the id [i.e. id_text and id_htmlText]
    	 */
    	
    	String htmlTextId = getId() + HTML_TEXT_ID_APPENDED;
    	String htmlTextUpdateVal = (String) requestMap.get(htmlTextId);
    	
    	if(htmlTextUpdateVal != null){
    		setHtmlText(htmlTextUpdateVal);
    	}
    	
    	if(getTextBinding().equals(HTML_TEXT_ATTR)){
    		setSubmittedValue(getHtmlText());
    	}
    	
    }
    
    public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	javax.el.ValueExpression ve = getValueExpression(HTML_TEXT_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getHtmlText());
    		setHtmlText(null);
    	}
    	
    }
}
