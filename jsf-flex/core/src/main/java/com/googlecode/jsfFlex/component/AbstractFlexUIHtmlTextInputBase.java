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

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIHtmlTextAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITextBindingAttribute;

/**
 * This class will process the needed actions of setting and retrieving of "htmlText" attribute<br>
 * within the Flex components. Note that since this class invokes the super method of populateComponentInitValues<br>
 * it will also set and retrieve values of "text" attribute of the component [if it exists].<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        clazz       =   "com.googlecode.jsfFlex.component.FlexUIHtmlTextInputBase",
        type        =   "com.googlecode.jsfFlex.FlexUIHtmlTextInputBase",
        family      =   "javax.faces.FlexUIHtmlTextInputBase",
        desc        =   "Base component for FlexInput components that contain htmlText attribute",
        template    =   true
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIHtmlTextInputBase")
public abstract class AbstractFlexUIHtmlTextInputBase 
							extends com.googlecode.jsfFlex.component.FlexUITextInputBase 
							implements IFlexUIHtmlTextAttribute, IFlexUITextBindingAttribute {

	private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(FlexUIHtmlTextInputBase.class);
	
	private static final String HTML_TEXT_ATTR = "htmlText";
	private static final String HTML_TEXT_ID_APPENDED = "_htmlText";
	
	private org.json.JSONObject initValue;
    
    @Override
    protected void populateComponentInitValues(){
		try{
			if(getTextBinding().equals(HTML_TEXT_ATTR) && getHtmlText() != null){
				getInitValue().put(VALUE, com.googlecode.jsfFlex.shared.util.FlexJsfUtil.escapeCharacters( getHtmlText() ));
			}else{
				super.populateComponentInitValues();
			}
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
    
    private synchronized org.json.JSONObject getInitValue(){
        if(initValue == null){
            try{
                initValue = new org.json.JSONObject();
                initValue.put(ATTRIBUTE, HTML_TEXT_ATTR);
                
                _initValues.put(initValue);
                
            }catch(org.json.JSONException jsonException){
                _log.info("Error while formatting to JSON content", jsonException);
            }
        }
        return initValue;
    }
    
    @Override
    public void decode(FacesContext context) {
    	super.decode(context);
    	
    	java.util.Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	/*
    	 * since there exists two possible returned values [text and htmlText],
    	 * the attribute will be appended to the id [i.e. id_text and id_htmlText]
    	 */
    	
    	String htmlTextId = getId() + HTML_TEXT_ID_APPENDED;
    	String htmlTextUpdateVal = requestMap.get(htmlTextId);
    	
    	if(htmlTextUpdateVal != null){
    		setHtmlText(htmlTextUpdateVal);
    	}
    	
    	if(getTextBinding().equals(HTML_TEXT_ATTR)){
    		setSubmittedValue(getHtmlText());
    	}
    	
    }
    
    @Override
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
