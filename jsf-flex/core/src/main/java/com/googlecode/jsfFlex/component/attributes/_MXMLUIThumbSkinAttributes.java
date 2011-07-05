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
public interface _MXMLUIThumbSkinAttributes {
	
	/**
	 * Name of the class to use as the skin for the thumb of the scroll bar when you click the thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the class to use as the skin for the thumb of the scroll bar when you click the thumb."
	 */
	String getThumbDownSkin();

	/**
	 * Name of the class to use as the skin for the thumb of the scroll bar when the mouse pointer is over the thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the class to use as the skin for the thumb of the scroll bar whenthe mouse pointer is over the thumb."
	 */
	String getThumbOverSkin();

	/**
	 * Name of the class to use as the skin for the thumb of the scroll bar.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the class to use as the skin for the thumb of the scroll bar."
	 */
	String getThumbUpSkin();
	
}
