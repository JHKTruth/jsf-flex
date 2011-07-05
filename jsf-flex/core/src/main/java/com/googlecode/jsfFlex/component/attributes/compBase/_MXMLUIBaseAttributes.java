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
package com.googlecode.jsfFlex.component.attributes.compBase;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLUIBaseAttributes {
	
	/**
	 * Id of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Id of the component."
	 *     inheritedTag	= true
	 */
	String getId();
	
	/**
	 * Number that specifies the height of the component, in pixels, in the parent's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the height of the component, in pixels, in the parent's coordinates."
	 */
	String getHeight();
	
	/**
	 * The class style used by this component.
	 * 
	 * @JSFProperty
	 *     desc			= "The class style used by this component."
	 */
	String getStyleName();
	
	/**
	 * Number that specifies the width of the component, in pixels, in the parent's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the width of the component, in pixels, in the parent's coordinates."
	 */
	String getWidth();
	
	/**
	 * Number that specifies the component's horizontal position, in pixels, within its parent container.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the component's horizontal position, in pixels, within its parent container."
	 */
	String getX();
	
	/**
	 * Number that specifies the component's vertical position, in pixels, within its parent container.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the component's vertical position, in pixels, within its parent container."
	 */
	String getY();
	
	/**
	 * Dispatched when the component has finished its construction, property processing, measuring, layout, and drawing.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the component has finished its construction, property processing, measuring, layout, and drawing."
	 */
	String getCreationComplete();
	
}
