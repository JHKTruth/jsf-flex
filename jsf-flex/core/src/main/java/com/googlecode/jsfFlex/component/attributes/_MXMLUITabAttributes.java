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
package com.googlecode.jsfFlex.component.attributes;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLUITabAttributes {
	
	/**
	 * Name of CSS style declaration that specifies styles for the first tab.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of CSS style declaration that specifies styles for the first tab."
	 */
	public abstract String getFirstTabStyleName();

	/**
	 * Name of CSS style declaration that specifies styles for the last tab.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of CSS style declaration that specifies styles for the last tab."
	 */
	public abstract String getLastTabStyleName();

	/**
	 * Name of CSS style declaration that specifies styles for the text of the selected tab.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of CSS style declaration that specifies styles for the text of the selected tab."
	 */
	public abstract String getSelectedTabTextStyleName();

	/**
	 * Height of each tab, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Height of each tab, in pixels."
	 */
	public abstract String getTabHeight();

	/**
	 * Name of CSS style declaration that specifies styles for the tabs.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of CSS style declaration that specifies styles for the tabs."
	 */
	public abstract String getTabStyleName();

	/**
	 * Width of each tab, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Width of each tab, in pixels."
	 */
	public abstract String getTabWidth();
	
}
