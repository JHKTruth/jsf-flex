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
package com.googlecode.jsfFlex.component.ext.data.ext.properties;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.properties.FlexUIDynamicPropertyBase",
        type                =   "com.googlecode.jsfFlex.FlexUIDynamicPropertyBase",
        family              =   "javax.faces.FlexProperty",
        desc                =   "Base component for dynamic/reflected FlexProperty component",
        template            =   true
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIDynamicPropertyBase")
public abstract class AbstractFlexUIDynamicPropertyBase 
						extends UIComponentBase {
	
	/**
	 * Property of the object. This will allow fetching of the property name and property value dynamically [property provided as a static string representing the property name and its value being the reflected value of this static string].
	 */
    @JSFProperty(
            required    =   true,
            desc        =   "Property of the object. This will allow fetching of the property name and property value dynamically [property provided as a static string representing the property name and its value being the reflected value of this static string]."
    )
	public abstract String getProperty();
	
	public synchronized String getPropertyMethodName(){
		final String GET_PROPERTY_METHOD_NAME = "get" + getProperty().substring(0, 1).toUpperCase() + getProperty().substring(1);
		
		return GET_PROPERTY_METHOD_NAME;
	}
	
}
