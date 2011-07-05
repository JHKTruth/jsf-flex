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
public interface _MXMLUIBaseStyleAttributes {
	
	/**
	 * The vertical distance in pixels from the lower edge of the component to the lower edge of its parent container.
	 * 
	 * @JSFProperty
	 *     desc			= "The vertical distance in pixels from the lower edge of the component to the lower edge of its parent container."
	 */
	String getBottom();
	
	/**
	 * Color of the component highlight when validation fails.
	 * 
	 * @JSFProperty
	 *     desc			= "Color of the component highlight when validation fails."
	 */
	String getErrorColor();
	
	/**
	 * Blend mode used by the focus rectangle.
	 * 
	 * @JSFProperty
	 *     desc			= "Blend mode used by the focus rectangle."
	 */
	String getFocusBlendMode();
	
	/**
	 * Skin used to draw the focus rectangle.
	 * 
	 * @JSFProperty
	 *     desc			= "Skin used to draw the focus rectangle.'
	 */
	String getFocusSkin();
	
	/**
	 * Thickness, in pixels, of the focus rectangle outline.
	 * 
	 * @JSFProperty
	 *     desc			= "Thickness, in pixels, of the focus rectangle outline."
	 */
	String getFocusThickness();
	
	/**
	 * The horizontal distance in pixels from the center of the component's parent container to the center of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "The horizontal distance in pixels from the center of the component's parent container to the center of the component."
	 */
	String getHorizontalCenter();
	
	/**
	 * The horizontal distance in pixels from the left edge of the component's parent container to the left edge of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "The horizontal distance in pixels from the left edge of the component's parent container to the left edge of the component."
	 */
	String getLeft();
	
	/**
	 * The horizontal distance in pixels from the right edge of the component to the right edge of its parent container.
	 * 
	 * @JSFProperty
	 *     desc			= "The horizontal distance in pixels from the right edge of the component to the right edge of its parent container."
	 */
	String getRight();
	
	/**
	 * Theme color of a component.
	 * 
	 * @JSFProperty
	 *     desc			= "Theme color of a component."
	 */
	String getThemeColor();
	
	/**
	 * The vertical distance in pixels from the top edge of the component's parent container to the top edge of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "The vertical distance in pixels from the top edge of the component's parent container to the top edge of the component."
	 */
	String getTop();
	
	/**
	 * The vertical distance in pixels from the center of the component's parent container to the center of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "The vertical distance in pixels from the center of the component's parent container to the center of the component."
	 */
	String getVerticalCenter();
	
}
