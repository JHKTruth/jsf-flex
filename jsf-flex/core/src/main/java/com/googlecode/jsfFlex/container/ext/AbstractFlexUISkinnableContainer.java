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
package com.googlecode.jsfFlex.container.ext;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUICreationPolicyAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexSkinnableContainer",
        clazz               =   "com.googlecode.jsfFlex.container.ext.FlexUISkinnableContainer",
        type                =   "com.googlecode.jsfFlex.FlexUISkinnableContainer",
        tagClass            =   "com.googlecode.jsfFlex.taglib.container.ext.FlexUISkinnableContainerTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexSkinnableContainer"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUISkinnableContainer")
public abstract class AbstractFlexUISkinnableContainer 
                            extends AbstractFlexUISimpleBase
                            implements IFlexUIBaseAttributes, IFlexUICreationPolicyAttribute {
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        /*
         * HACK :
         *  Setting creationPolicy to "all", so the components which are not
         *  shown by the non-selected Container would be created and can be referred 
         *  during the initialization/value preserving process
         * 
         *  I think this is the most prudent choice
         */
        setCreationPolicy("all");
        super.encodeBegin(context);
    }
    
}
