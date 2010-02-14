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
package com.googlecode.jsfFlex.taglib;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionValueChangeListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.MethodExpressionValidator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of setting UIInput attributes has been taken from MyFaces. Much Thanks!
 * 
 * @author Ji Hoon Kim
 */
public abstract class MXMLUIInputTagBase extends MXMLUIComponentTagBase {
	
	private final static Log _log = LogFactory.getLog(MXMLUIInputTagBase.class);
	
	private static final Class[] VALIDATOR_ARGS = {FacesContext.class, UIComponent.class, Object.class};
	private static final Class[] VALUE_LISTENER_ARGS = {ValueChangeEvent.class};
	
	private static final String IMMEDIATE_ATTR = "immediate";
	private static final String REQUIRED_ATTR = "required";
	
	//UIInput attributes
	private String _immediate;
    private String _required;
    private String _validator;
    private String _valueChangeListener;
    
    public void release() {
        super.release();
        
        _immediate=null;
        _required=null;
        _validator=null;
        _valueChangeListener=null;
    }
    
    protected void setProperties(UIComponent component){
        super.setProperties(component);
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
        
        setBooleanProperty(context, component, IMMEDIATE_ATTR, _immediate);
        setBooleanProperty(context, component, REQUIRED_ATTR, _required);
        
        if(_validator != null){
        	if(!(component instanceof EditableValueHolder)){
        		throw new IllegalArgumentException("Component " + component.getClientId(context) + " is no EditableValueHolder");
        	}
        	
            MethodExpression me = expressionFactory.createMethodExpression(context.getELContext(), _validator, Object.class, VALIDATOR_ARGS);
            if(me != null){
                ((EditableValueHolder)component).addValidator(new MethodExpressionValidator(me));
        	}else{
        		_log.error("Component " + component.getClientId(context) + " has invalid validation expression " + _validator);
        	}
        }
        
        if(_valueChangeListener != null){
        	if(!(component instanceof EditableValueHolder)){
        		throw new IllegalArgumentException("Component " + component.getClientId(context) + " is no EditableValueHolder");
        	}
        	
            MethodExpression me = expressionFactory.createMethodExpression(context.getELContext(), _valueChangeListener, Object.class, VALUE_LISTENER_ARGS);
        	if(me != null){
                ((EditableValueHolder)component).addValueChangeListener(new MethodExpressionValueChangeListener(me));
        	}else{
        		_log.error("Component " + component.getClientId(context) + " has invalid valueChangedListener expression " + _valueChangeListener);
        	}
        }
        
    }
    
    public void setImmediate(String immediate){
        _immediate = immediate;
    }
    public void setRequired(String required){
        _required = required;
    }
    public void setValidator(String validator){
        _validator = validator;
    }
    public void setValueChangeListener(String valueChangeListener){
        _valueChangeListener = valueChangeListener;
    }
    
}
