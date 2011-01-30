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
package com.googlecode.jsfFlex.eventGlue.ext;

import java.util.Arrays;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIAsynchronousEventGlueHandlerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIAsynchronousPropertyUpdateDelimAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUIAsynchronousPropertyUpdateListAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUIEventHandlerSrcIdAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIEventHandlerTgtIdAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIEventListenerAttribute;
import com.googlecode.jsfFlex.eventGlue.AbstractFlexUIAsynchronousEventGlueBase;
import com.googlecode.jsfFlex.shared.adapter.IFlexEvent;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.model.beans.AsynchronousPropertyUpdateEventBean;
import com.googlecode.jsfFlex.shared.model.event.AsynchronousPropertyUpdateEvent;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexAsynchronousPropertyUpdateEventListener",
        clazz               =   "com.googlecode.jsfFlex.eventGlue.ext.FlexUIAsynchronousPropertyUpdateEventListener",
        type                =   "com.googlecode.jsfFlex.FlexUIAsynchronousPropertyUpdateEventListener",
        tagClass            =   "com.googlecode.jsfFlex.taglib.eventGlue.ext.FlexUIAsynchronousPropertyUpdateEventListenerTag",
        family              =   "javax.faces.FlexEventListener",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexAsynchronousPropertyUpdateEventListener"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIAsynchronousPropertyUpdateEventListener")
public abstract class AbstractFlexUIAsynchronousPropertyUpdateEventListener 
                                    extends AbstractFlexUIAsynchronousEventGlueBase
                                    implements IFlexUIAsynchronousEventGlueHandlerAttribute, IFlexUIEventListenerAttribute, IFlexUIEventHandlerSrcIdAttribute,  
                                    IFlexUIEventHandlerTgtIdAttribute, IFlexUIAsynchronousPropertyUpdateListAttributes, 
                                    IFlexUIAsynchronousPropertyUpdateDelimAttributes {

    private final static Log _log = LogFactory.getLog(AbstractFlexUIAsynchronousPropertyUpdateEventListener.class);
    
    private static final String SOURCE_PROPERTY_CURRENT_VALUE = "SOURCE_PROPERTY_CURRENT_VALUE";
    
    @Override
    public JSONObject ayncProcessRequest() throws JSONException {
        
        FacesContext currContext = FacesContext.getCurrentInstance();
        javax.el.ELContext elContext = currContext.getELContext();
        
        String sourceCurrentValue = currContext.getExternalContext().getRequestParameterMap().get(SOURCE_PROPERTY_CURRENT_VALUE);
        
        StringBuilder logMessage = new StringBuilder(getClass().getSimpleName());
        logMessage.append(" => ayncProcessRequest :  sourceCurrentValue [ ");
        logMessage.append(sourceCurrentValue);
        logMessage.append(" ] ");
        _log.info(logMessage.toString());
        
        Object[] arguments = new Object[]{ new AsynchronousPropertyUpdateEvent(sourceCurrentValue, getEventHandlerSrcId(), getEventHandlerTgtId()) };
        Object methodResult = getAsynchronousEventGlueHandler().invoke(elContext, arguments);
        AsynchronousPropertyUpdateEventBean result = null;
        if(!(methodResult instanceof AsynchronousPropertyUpdateEventBean)){
            result = new AsynchronousPropertyUpdateEventBean(methodResult.toString(), getEventHandlerSrcId(), getEventHandlerTgtId());
        }else{
            result = AsynchronousPropertyUpdateEventBean.class.cast( methodResult );
        }
        
        return result.formatResponseToJSON();
    }
    
    @Override
    public JSONObject getAddtionalArguments(){
        JSONObject additionalArguments = new JSONObject();
        
        List<String> sourcePropertyList = getSourcePropertyList();
        List<String> targetPropertyList = getTargetPropertyList();
        
        if(sourcePropertyList == null){
            
            String sourceProperty = getSourcePropertyDelim();
            String targetProperty = getTargetPropertyDelim();
            
            if(sourceProperty == null){
                throw new IllegalArgumentException("Either sourcePropertyList and targetPropertyList or sourcePropertyDelim and targetPropertyDelim must be provided " 
                                                       + "[note for convention sourcePropertyList + targetPropertyDelim and vice versa too is not allowed.");
                
            }
            
            sourcePropertyList = Arrays.asList(sourceProperty.split(","));
            targetPropertyList = Arrays.asList(targetProperty.split(","));
            
        }
        
        try{
            additionalArguments.put(IFlexEvent.ACTION_SCRIPT_EVENT_FIELDS.SOURCE_PROPERTY.name(), new JSONArray(sourcePropertyList));
            additionalArguments.put(IFlexEvent.ACTION_SCRIPT_EVENT_FIELDS.TARGET_PROPERTY.name(), new JSONArray(targetPropertyList));
        
        }catch(JSONException jsonException){
            _log.info("Error while formatting to JSON content", jsonException);
            throw new ComponentBuildException(jsonException);
        }
        
        return additionalArguments;
    }
    
    public IFlexEvent.EVENT_HANDLER_TYPE getEventHandlerType() {
        return IFlexEvent.EVENT_HANDLER_TYPE.PROPERTY_UPDATE_EVENT_HANDER;
    }
    
    public String getEventHandlerEventName() {
        return getEventListener();
    }
    
}
