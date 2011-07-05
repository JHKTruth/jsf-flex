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
public interface _MXMLUIContainerAttributes {
	
	/**
	 * If true, measurement and layout are done when the position or size of a child is changed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "If true, measurement and layout are done when the position or size of a child is changed."
	 */
	String getAutoLayout();

	/**
	 * Whether to apply a clip mask if the positions and/or sizes of this container's children extend outside the borders of this container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Whether to apply a clip mask if the positions and/or sizes of this container's children extend outside the borders of this container."
	 */
	String getClipContent();

	/**
	 * Specifies the order to instantiate and draw the children of the container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the order to instantiate and draw the children of the container."
	 */
	String getCreationIndex();
	
	/**
	 * The child creation policy for this Container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The child creation policy for this Container."
	 */
	String getCreationPolicy();
	
	void setCreationPolicy(String creationPolicy);
	
	/**
	 * The Button control designated as the default button for the container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The Button control designated as the default button for the container."
	 */
	String getDefaultButton();

	/**
	 * Number of pixels to move when the left- or right-arrow button in the horizontalscroll bar is pressed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels to move when the left- or right-arrow button in the horizontalscroll bar is pressed."
	 */
	String getHorizontalLineScrollSize();

	/**
	 * Number of pixels to move when the track in the horizontal scroll bar is pressed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels to move when the track in the horizontal scroll bar is pressed."
	 */
	String getHorizontalPageScrollSize();

	/**
	 * The horizontal scrollbar used in this container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The horizontal scrollbar used in this container."
	 */
	String getHorizontalScrollBar();

	/**
	 * Number of pixels to scroll when the up- or down-arrow button in the verticalscroll bar is pressed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels to scroll when the up- or down-arrow button in the verticalscroll bar is pressed."
	 */
	String getVerticalLineScrollSize();

	/**
	 * Number of pixels to scroll when the track in the vertical scroll bar is pressed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels to scroll when the track in the vertical scroll bar is pressed."
	 */
	String getVerticalPageScrollSize();

	/**
	 * The vertical scrollbar used in this container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The vertical scrollbar used in this container."
	 */
	String getVerticalScrollBar();

	/**
	 * If a background image is specified, this style specifies whether it is fixed with regard to the viewport (fixed) or scrolls along with the content (scroll).The default value is scroll.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "If a background image is specified, this style specifies whether it is fixed with regard to the viewport (fixed) or scrolls along with the content (scroll).The default value is scroll."
	 */
	String getBackgroundAttachment();

	/**
	 * The alpha value for the overlay that is placed on top of the container when it is disabled.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The alpha value for the overlay that is placed on top of the container when it is disabled."
	 */
	String getDisabledOverlayAlpha();

	/**
	 * Dispatched after a child has been added to a container.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched after a child has been added to a container."
	 */
	String getChildAdd();

	/**
	 * Dispatched after the index (among the container children) of a container child changes.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched after the index (among the container children) of a container child changes."
	 */
	String getChildIndexChange();

	/**
	 * Dispatched before a child of a container is removed.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched before a child of a container is removed."
	 */
	String getChildRemove();
	
}
