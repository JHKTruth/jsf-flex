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
package com.googlecode.jsfFlex.attributes;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.shared.adapter.IFlexAttributeNode;

/**
 * In order to simplify development and focus in bridging of JSF and Flex, all attributes 
 * of a component that is not specific to the JSF Flex project and not boundable to a bean will be 
 * provided as a sub-tag of the component. Meaning one can consider the component to be an XML Element  
 * node and this tag/component to be an Attribute node.
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name        =   "jf:flexAttributeNode",
        clazz       =   "com.googlecode.jsfFlex.attributes.FlexUIAttributeNode",
        type        =   "com.googlecode.jsfFlex.FlexAttributeNode",
        tagClass    =   "com.googlecode.jsfFlex.taglib.attributes.FlexUIAttributeNodeTag",
        family      =   "javax.faces.FlexUIAttributeNode",
        desc        =   "Attribute Node component"
)
@FacesComponent("com.googlecode.jsfFlex.FlexAttributeNode")
public abstract class AbstractFlexUIAttributeNode 
                        extends UIComponentBase 
                        implements IFlexAttributeNode {
    
    /**
     * Attribute name.
     */
    @JSFProperty(
            desc        =   "Attribute name.",
            required    =   true
    )
    public abstract String getName();
    
    /**
     * Attribute value.
     */
    @JSFProperty(
            desc        =   "Attribute value.",
            required    =   true
    )
    public abstract String getValue();
    
}
