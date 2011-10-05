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

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIEventListenerAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUIPreserveInServer;
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
@FacesComponent("com.googlecode.jsfFlex.FlexUIAsynchronousEventGlueBase")
public abstract class AbstractFlexUIAsynchronousEventGlueBase 
                            extends AbstractFlexUIPreserveInServer 
                            implements IFlexEvent, IFlexUIEventListenerAttribute {
    
    public abstract JSONObject ayncProcessRequest() throws JSONException;
    
    protected abstract boolean isAsynchronousEventGlueEnabled();
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        
        if(flexContext.isProductionEnv()){
            //means no need to create preMxml files
            setRendered(false);
        }
        
        if(isAsynchronousEventGlueEnabled()){
            EVENT_HANDLER_TYPE eventHandlerType = getEventHandlerType();
            AbstractJsfFlexResource jsfFlexResource = AbstractJsfFlexResource.getInstance();
            EnumSet<JAVA_SCRIPT_IMPORT> javaScriptImports = eventHandlerType.getJavaScriptImports();
            
            for(JAVA_SCRIPT_IMPORT currJSImport : javaScriptImports){
                jsfFlexResource.addResource(getClass(), currJSImport.getJavaScriptImport());
            }
        }
        
        super.encodeBegin(context);
    }
    
    /**
     * Usually one does not provide overriding of this method; however there are certain cases where one desires to 
     * provide additional parameters [i.e. AbstractFlexUIAsynchronousPropertyUpdateEventListener]
     * 
     * @return
     */
    public JSONObject getAddtionalArguments(){
        return null;
    }
    
    public String getEventHandlerId(){
        return getId();
    }
    
}
