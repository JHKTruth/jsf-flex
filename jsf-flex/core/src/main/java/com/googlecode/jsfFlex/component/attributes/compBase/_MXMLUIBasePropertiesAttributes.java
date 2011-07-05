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
public interface _MXMLUIBasePropertiesAttributes {
	
	/**
	 * Specifies the bitmap caching policy for this object.
	 * 
	 * @JSFProperty
	 *     desc			= "Specifies the bitmap caching policy for this object.'
	 */
	String getCachePolicy();
	
	/**
	 * The current view state of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "The current view state of the component."
	 */
	String getCurrentState();
	
	/**
	 * Specifies whether the UIComponent object receives doubleClick events.
	 * 
	 * @JSFProperty
	 *     desc			= "Specifies whether the UIComponent object receives doubleClick events."
	 */
	String getDoubleClickEnabled();
	
	/**
	 * Whether the component can accept user interaction.
	 * 
	 * @JSFProperty
	 *     desc			= "Whether the component can accept user interaction."
	 */
	String getEnabled();
	
	/**
	 * Number that specifies the explicit height of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the explicit height of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitHeight();
	
	/**
	 * Number that specifies the maximum height of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the maximum height of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitMaxHeight();
	
	/**
	 * Number that specifies the maximum width of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the maximum width of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitMaxWidth();
	
	/**
	 * Number that specifies the minimum height of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the minimum height of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitMinHeight();
	
	/**
	 * Number that specifies the minimum width of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the minimum width of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitMinWidth();
	
	/**
	 * Number that specifies the explicit width of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the explicit width of the component, in pixels, in the component's coordinates."
	 */
	String getExplicitWidth();
	
	/**
	 * Indicates whether the component can receive focus when clicked on.
	 * 
	 * @JSFProperty
	 *     desc			= "Indicates whether the component can receive focus when clicked on."
	 */
	String getFocusEnabled();
	
	/**
	 * Specifies whether this component is included in the layout of the parent container.
	 * 
	 * @JSFProperty
	 *     desc			= "Specifies whether this component is included in the layout of the parent container."
	 */
	String getIncludeInLayout();
	
	/**
	 * Number that specifies the maximum height of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the maximum height of the component, in pixels, in the component's coordinates."
	 */
	String getMaxHeight();
	
	/**
	 * Number that specifies the maximum width of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the maximum width of the component, in pixels, in the component's coordinates."
	 */
	String getMaxWidth();
	
	/**
	 * The default height of the component, in pixels.
	 * 
	 * @JSFProperty
	 *     desc			= "The default height of the component, in pixels."
	 */
	String getMeasuredHeight();
	
	/**
	 * The default minimum height of the component, in pixels.
	 * 
	 * @JSFProperty
	 *     desc			= "The default minimum height of the component, in pixels."
	 */
	String getMeasuredMinHeight();
	
	/**
	 * The default minimum width of the component, in pixels.
	 * 
	 * @JSFProperty
	 *     desc			= "The default minimum width of the component, in pixels."
	 */
	String getMeasuredMinWidth();
	
	/**
	 * The default width of the component, in pixels.
	 * 
	 * @JSFProperty
	 *     desc			= "The default width of the component, in pixels."
	 */
	String getMeasuredWidth();
	
	/**
	 * Number that specifies the minimum height of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the minimum height of the component, in pixels, in the component's coordinates."
	 */
	String getMinHeight();
	
	/**
	 * Number that specifies the minimum width of the component, in pixels, in the component's coordinates.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the minimum width of the component, in pixels, in the component's coordinates."
	 */
	String getMinWidth();
	
	/**
	 * Whether you can receive focus when clicked on.
	 * 
	 * @JSFProperty
	 *     desc			= "Whether you can receive focus when clicked on."
	 */
	String getMouseFocusEnabled();
	
	/**
	 * Number that specifies the height of a component as a percentage of its parent's size.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the height of a component as a percentage of its parent's size."
	 */
	String getPercentHeight();
	
	/**
	 * Number that specifies the width of a component as a percentage of its parent's size.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the width of a component as a percentage of its parent's size."
	 */
	String getPercentWidth();
	
	/**
	 * Number that specifies the horizontal scaling factor.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the horizontal scaling factor."
	 */
	String getScaleX();
	
	/**
	 * Number that specifies the vertical scaling percentage.
	 * 
	 * @JSFProperty
	 *     desc			= "Number that specifies the vertical scaling percentage."
	 */
	String getScaleY();
	
	/**
	 * The view states that are defined for this component.
	 * 
	 * @JSFProperty
	 *     desc			= "The view states that are defined for this component."
	 */
	String getStates();
	
	/**
	 * Text to display in the ToolTip.
	 * 
	 * @JSFProperty
	 *     desc			= "Text to display in the ToolTip."
	 */
	String getToolTip();
	
	/**
	 * An Array of Transition objects, where each Transition object defines a set of effects to play when a view state change occurs.
	 * 
	 * @JSFProperty
	 *     desc			= "An Array of Transition objects, where each Transition object defines a set of effects to play when a view state change occurs."
	 */
	String getTransitions();
	
	/**
	 * Used by a validator to associate a subfield with this component.
	 * 
	 * @JSFProperty
	 *     desc			= "Used by a validator to associate a subfield with this component."
	 */
	String getValidationSubField();
	
}
