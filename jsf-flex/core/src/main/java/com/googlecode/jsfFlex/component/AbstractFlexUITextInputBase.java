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

import com.googlecode.jsfFlex.attributes.IFlexUITextAttribute;

/**
 * This class will process the needed actions of setting and retrieving of "text" attribute<br>
 * within the Flex components.<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        clazz       =   "com.googlecode.jsfFlex.component.FlexUITextInputBase",
        type        =   "com.googlecode.jsfFlex.FlexUITextInputBase",
        family      =   "javax.faces.FlexUITextInputBase",
        desc        =   "Base component for FlexInput components that contain text attribute",
        template    =   true
)
@FacesComponent("com.googlecode.jsfFlex.FlexUITextInputBase")
public abstract class AbstractFlexUITextInputBase 
							extends AbstractFlexUIInputBase 
							implements IFlexUITextAttribute {
	
	private final static org.apache.commons.logging.Log _log = org.apache.commons.logging.LogFactory.getLog(FlexUITextInputBase.class);
	
	private static final String TEXT_ATTR = "text";
	private static final String TEXT_ID_APPENDED = "_text";
	
	private org.json.JSONObject initValue;
	
	protected void populateComponentInitValues(){
		try{
			if(getText() != null){
				getInitValue().put(VALUE, com.googlecode.jsfFlex.shared.util.FlexJsfUtil.escapeCharacters( getText() ));
			}
		}catch(org.json.JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
            throw new com.googlecode.jsfFlex.shared.exception.ComponentBuildException(jsonException);
		}
	}
    
    private synchronized org.json.JSONObject getInitValue(){
        if(initValue == null){
            try{
                initValue = new org.json.JSONObject();
                initValue.put(ATTRIBUTE, TEXT_ATTR);
                
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
    	
    	String textId = getId() + TEXT_ID_APPENDED;
    	String textUpdateVal = requestMap.get(textId);
    	
    	if(textUpdateVal != null){
    		setText(textUpdateVal);
    		setSubmittedValue(textUpdateVal);
    	}
    }
    
    @Override
    public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	javax.el.ValueExpression ve = getValueExpression(TEXT_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getText());
    		setText(null);
    	}
    	
    }
    
}
