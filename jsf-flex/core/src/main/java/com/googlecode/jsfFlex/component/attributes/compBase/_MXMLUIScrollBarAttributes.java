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
public interface _MXMLUIScrollBarAttributes {
	
	/**
	 * Amount to scroll when an arrow button is pressed, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Amount to scroll when an arrow button is pressed, in pixels."
	 */
	String getLineScrollSize();

	/**
	 * Number which represents the maximum scroll position.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number which represents the maximum scroll position."
	 */
	String getMaxScrollPosition();

	/**
	 * Number that represents the minimum scroll position.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number that represents the minimum scroll position."
	 */
	String getMinScrollPosition();

	/**
	 * Amount to move the scroll thumb when the scroll bar track is pressed, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Amount to move the scroll thumb when the scroll bar track is pressed, in pixels."
	 */
	String getPageScrollSize();

	/**
	 * The number of lines equivalent to one page.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The number of lines equivalent to one page."
	 */
	String getPageSize();

	/**
	 * Number that represents the current scroll position.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number that represents the current scroll position."
	 */
	String getScrollPosition();

	/**
	 * Name of the class to use as the icon for the thumb of the scroll bar.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the class to use as the icon for the thumb of the scroll bar."
	 */
	String getThumbIcon();
	
}
