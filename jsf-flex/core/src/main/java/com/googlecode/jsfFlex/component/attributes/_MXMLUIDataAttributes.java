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
public interface _MXMLUIDataAttributes {
	
	/**
	 * Lets you pass a value to the component when you use it in an item renderer or item editor.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Lets you pass a value to the component when you use it in an item renderer or item editor."
	 */
	String getData();

	/**
	 * When a component is used as a drop-in item renderer or drop-in item editor, Flex initializesthe listData property of the component with the appropriate data from the list control.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "When a component is used as a drop-in item renderer or drop-in item editor, Flex initializesthe listData property of the component with the appropriate data from the list control."
	 */
	String getListData();

	/**
	 * Specifies whether extra white space (spaces, line breaks, and so on) should be removed in a control with HTML text.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies whether extra white space (spaces, line breaks, and so on) should be removed in a control with HTML text."
	 */
	String getCondenseWhite();
	
}
