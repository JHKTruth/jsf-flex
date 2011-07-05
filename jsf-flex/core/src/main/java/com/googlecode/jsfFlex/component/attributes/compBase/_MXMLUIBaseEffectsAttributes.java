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
public interface _MXMLUIBaseEffectsAttributes {
	
	/**
	 * Played when the component is added as a child to a Container.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component is added as a child to a Container."
	 */
	String getAddedEffect();
	
	/**
	 * Played when the component is created.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component is created."
	 */
	String getCreationCompleteEffect();
	
	/**
	 * Played when the component gains keyboard focus.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component gains keyboard focus."
	 */
	String getFocusInEffect();
	
	/**
	 * Played when the component loses keyboard focus.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component loses keyboard focus."
	 */
	String getFocusOutEffect();
	
	/**
	 * Played when the component becomes invisible.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component becomes invisible."
	 */
	String getHideEffect();
	
	/**
	 * Played when the user presses the mouse button while over the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the user presses the mouse button while over the component."
	 */
	String getMouseDownEffect();
	
	/**
	 * Played when the user releases the mouse button while over the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the user releases the mouse button while over the component."
	 */
	String getMouseUpEffect();
	
	/**
	 * Played when the component is moved.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component is moved."
	 */
	String getMoveEffect();
	
	/**
	 * Played when the component is removed from a Container.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component is removed from a Container."
	 */
	String getRemovedEffect();
	
	/**
	 * Played when the component is resized.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component is resized."
	 */
	String getResizeEffect();
	
	/**
	 * Played when the user rolls the mouse so it is no longer over the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the user rolls the mouse so it is no longer over the component."
	 */
	String getRollOutEffect();
	
	/**
	 * Played when the user rolls the mouse over the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the user rolls the mouse over the component."
	 */
	String getRollOverEffect();
	
	/**
	 * Played when the component becomes visible.
	 * 
	 * @JSFProperty
	 *     desc			= "Played when the component becomes visible."
	 */
	String getShowEffect();
	
}
