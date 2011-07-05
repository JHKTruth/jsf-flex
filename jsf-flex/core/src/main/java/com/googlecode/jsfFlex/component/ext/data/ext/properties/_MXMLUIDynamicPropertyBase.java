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

import javax.faces.component.UIComponentBase;

/**
 * @JSFComponent
 * 	 class    = "com.googlecode.jsfFlex.component.ext.data.ext.properties.MXMLUIDynamicPropertyBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDynamicPropertyBase"
 *   family   = "javax.faces.MXMLProperty"
 *   desc	  = "Base component for dynamic/reflected MXMLProperty component"
 *   template = "true"
 *   
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUIDynamicPropertyBase 
						extends UIComponentBase {
	
	/**
	 * Property of the object. This will allow fetching of the property name and property value dynamically [property provided as a static string representing the property name and its value being the reflected value of this static string].
	 * 
	 *@JSFProperty
	 *    required        = true
	 *    rtexprvalue     = false
	 *    desc            = "Property of the object. This will allow fetching of the property name and property value dynamically [property provided as a static string representing the property name and its value being the reflected value of this static string]."
	 */
	public abstract String getProperty();
	
	public synchronized String getPropertyMethodName(){
		final String GET_PROPERTY_METHOD_NAME = "get" + getProperty().substring(0, 1).toUpperCase() + getProperty().substring(1);
		
		return GET_PROPERTY_METHOD_NAME;
	}
	
}
