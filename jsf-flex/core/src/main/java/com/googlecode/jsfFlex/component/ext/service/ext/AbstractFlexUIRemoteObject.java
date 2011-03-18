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
package com.googlecode.jsfFlex.component.ext.service.ext;

import javax.faces.component.FacesComponent;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexRemoteObject",
        clazz               =   "com.googlecode.jsfFlex.component.ext.service.ext.FlexUIRemoteObject",
        type                =   "com.googlecode.jsfFlex.FlexUIRemoteObject",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.service.ext.FlexUIRemoteObjectTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexRemoteObject"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIRemoteObject")
public abstract class AbstractFlexUIRemoteObject 
						extends AbstractFlexUISimpleBase 
                        implements IFlexUIBaseAttributes {
    
    /**
     * Id of the component.
     */
    @JSFProperty(
    		inheritedTag  =   true,
            rtexprvalue =   true,
            literalOnly =   true,
            desc        =   "Id of the component."
    )
    @Override
    public String getId(){
        return super.getId();
    }
    
}
