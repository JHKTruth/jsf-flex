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

import javax.faces.component.FacesComponent;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexGroup",
        clazz               =   "com.googlecode.jsfFlex.container.ext.FlexUIGroup",
        type                =   "com.googlecode.jsfFlex.FlexUIGroup",
        tagClass            =   "com.googlecode.jsfFlex.taglib.container.ext.FlexUIGroupTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexGroup"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIGroup")
public abstract class AbstractFlexUIGroup 
                            extends AbstractFlexUISimpleBase 
                            implements IFlexUIBaseAttributes {
    
}
