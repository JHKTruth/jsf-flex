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
public interface _MXMLUIScrollControlAttributes {
	
	/**
	 * The border object.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The border object."
	 */
	String getBorder();

	/**
	 * A flag that indicates whether scrolling is live as the scrollbar thumb is moved or the view is not updated until the thumb is released.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether scrolling is live as the scrollbar thumb is moved or the view is not updated until the thumb is released."
	 */
	String getLiveScrolling();

	/**
	 * The maximum value for the horizontalScrollPosition property.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The maximum value for the horizontalScrollPosition property."
	 */
	String getMaxHorizontalScrollPosition();

	/**
	 * The maximum value for the verticalScrollPosition property.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The maximum value for the verticalScrollPosition property."
	 */
	String getMaxVerticalScrollPosition();

	/**
	 * A function that computes the string to be displayed as the ScrollTip.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A function that computes the string to be displayed as the ScrollTip."
	 */
	String getScrollTipFunction();

	/**
	 * A flagthat indicates whether a tooltip should appear near the scroll thumb when it is being dragged.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flagthat indicates whether a tooltip should appear near the scroll thumb when it is being dragged."
	 */
	String getShowScrollTips();
	
}
