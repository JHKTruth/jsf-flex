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
public interface _MXMLUIFocusRoundedCornersAttribute {
	
	/**
	 * Specifies which corners of the focus rectangle should be rounded. This value is a space-separated String that can contain any combination of tl, tr, bl  and br. For example, to specify that the right side corners should be rounded, but the left side corners should be square, use tr br. The cornerRadius style property specifies the radius of the rounded corners. The default value depends on the component class; if not overridden for the class,default value is tl tr bl br.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies which corners of the focus rectangle should be rounded. This value is a space-separated String that can contain any combination of tl, tr, bl  and br. For example, to specify that the right side corners should be rounded, but the left side corners should be square, use tr br. The cornerRadius style property specifies the radius of the rounded corners. The default value depends on the component class; if not overridden for the class,default value is tl tr bl br."
	 */
	String getFocusRoundedCorners();
	
}
