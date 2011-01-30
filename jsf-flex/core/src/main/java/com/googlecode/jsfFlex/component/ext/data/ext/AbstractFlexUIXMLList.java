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
package com.googlecode.jsfFlex.component.ext.data.ext;

import javax.faces.component.FacesComponent;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.component.ext.data.AbstractFlexUIXMLContainerBase;

/**
 * <ul>
 * This component can have as its direct children components of :
 * 		<li> AbstractFlexUIXMLListEntries </li>
 * 		<li> AbstractFlexUIXMLStaticElement </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexXMLList",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.FlexUIXMLList",
        type                =   "com.googlecode.jsfFlex.FlexUIXMLList",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.data.ext.FlexUIXMLListTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexXMLList"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIXMLList")
public abstract class AbstractFlexUIXMLList 
						extends AbstractFlexUIXMLContainerBase 
                        implements IFlexUIBaseAttributes {
	
}
