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

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLUITextAttributes {
	
	/**
	 * Indicates whether this control is used for entering passwords.
	 */
    @JSFProperty(
            required        =   false,
            rtexprvalue     =   false,
            desc            =   "Indicates whether this control is used for entering passwords."
    )
	String getDisplayAsPassword();

	/**
	 * The zero-based index of the position after the last character in the current selection (equivalent to the one-based index of the last character).
	 */
    @JSFProperty(
            required        =   false,
            rtexprvalue     =   false,
            desc            =   "The zero-based index of the position after the last character in the current selection(equivalent to the one-based index of the last character)."
    )
	String getSelectionEndIndex();

	/**
	 * The zero-based character index value of the first character in the current selection.
	 */
    @JSFProperty(
            required        =   false,
            rtexprvalue     =   false,
            desc            =   "The zero-based character index value of the first character in the current selection."
    )
	String getSelectionBeginIndex();
	
}
