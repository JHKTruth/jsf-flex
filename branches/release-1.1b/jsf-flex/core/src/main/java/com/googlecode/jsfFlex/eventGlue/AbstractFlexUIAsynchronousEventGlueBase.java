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
package com.googlecode.jsfFlex.eventGlue;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIAsynchronousEventGlueHandlerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIEventListenerAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;
import com.googlecode.jsfFlex.renderkit.html.util.AbstractJsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter.IFlexEvent;
import com.googlecode.jsfFlex.shared.adapter.IFlexEvent.EVENT_HANDLER_TYPE.JAVA_SCRIPT_IMPORT;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.FlexUIAsynchronousEventGlueBase",
        family  =   "javax.faces.FlexUIAsynchronousEventGlueBase",
        desc    =   "Base component for FlexAsynchronousEventGlue components."
)
public abstract class AbstractFlexUIAsynchronousEventGlueBase 
                            extends AbstractFlexUISimpleBase 
                            implements IFlexEvent, IFlexUIAsynchronousEventGlueHandlerAttribute, IFlexUIEventListenerAttribute {
    
    public abstract JSONObject ayncProcessRequest() throws JSONException;
    
    public void encodeBegin(FacesContext context) throws IOException {
        
        AbstractFlexContext mxmlContext = AbstractFlexContext.getCurrentInstance();
        
        if(mxmlContext.isProductionEnv()){
            //means no need to create preMxml files
            setRendered(false);
        }
        
        if(getAsynchronousEventGlueHandler() != null){
            EVENT_HANDLER_TYPE eventHandlerType = getEventHandlerType();
            AbstractJsfFlexResource jsfFlexResource = AbstractJsfFlexResource.getInstance();
            EnumSet<JAVA_SCRIPT_IMPORT> javaScriptImports = eventHandlerType.getJavaScriptImports();
            
            for(JAVA_SCRIPT_IMPORT currJSImport : javaScriptImports){
                jsfFlexResource.addResource(getClass(), currJSImport.getJavaScriptImport());
            }
        }
        
        super.encodeBegin(context);
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        
        /*
         * adding the component to the map for future asynchronous request such as 
         * DataUpdateEventHandler.as 
         * 
         */
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.put(getId(), this);
        
    }
    
    public String getEventHandlerId(){
        return getId();
    }
    
}
