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

import java.io.IOException;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

/**
 * This class will contain code to allow subclasses in performing aynchronous calls
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.FlexUIPreserveInServer",
        family  =   "javax.faces.FlexUIPreserveInServer",
        desc    =   "Base component for FlexPreserveInServer components"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIPreserveInServer")
public abstract class AbstractFlexUIPreserveInServer extends AbstractFlexUISimpleBase {
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        
        /*
         * adding the component to the session for future asynchronous requests
         */
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.put(getId(), this);
        
    }
    
    @Override
    public void decode(FacesContext context) {
        super.decode(context);
        
        /*
         * No longer needed, so remove the content.
         */
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.remove(getId());
        
    }
    
}
