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
 * 	 class    = "com.googlecode.jsfFlex.component.ext.data.ext.properties.MXMLUIStaticPropertyBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUIStaticPropertyBase"
 *   family   = "javax.faces.MXMLProperty"
 *   desc	  = "Base component for static MXMLProperty component"
 *   template = "true"
 *   
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUIStaticPropertyBase 
						extends UIComponentBase {
	
	/**
	 * Static name of the property.
	 * 
	 *@JSFProperty
	 *    required        = true
	 *    rtexprvalue     = false
	 *    desc            = "Static name of the property."
	 */
	public abstract String getStaticPropertyName();
	
	/**
	 * Static value of the property.
	 * 
	 *@JSFProperty
	 *    required        = true
	 *    rtexprvalue     = false
	 *    desc            = "Static value of the property."
	 */
	public abstract String getStaticPropertyValue();
	
}
