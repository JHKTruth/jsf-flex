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
package com.googlecode.jsfFlex.validator.attributes.compBase;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLUIValidatorAttributes {
	
	/**
	 * Setting this value to false will stop the validator from performing validation.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Setting this value to false will stop the validator from performing validation."
	 */
	String getEnabled();

	/**
	 * Specifies the validation listener.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the validation listener."
	 */
	String getListener();

	/**
	 * A String specifying the name of the property of the source object that contains the value to validate.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A String specifying the name of the property of the source object that contains the value to validate."
	 */
	String getProperty();

	/**
	 * If true, specifies that a missing or empty value causes a validation error.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "If true, specifies that a missing or empty value causes a validation error."
	 */
	String getRequired();

	/**
	 * Error message when a value is missing and the required property is true.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Error message when a value is missing and the required property is true."
	 */
	String getRequiredFieldError();

	/**
	 * Specifies the object containing the property to validate.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the object containing the property to validate."
	 */
	String getSource();

	/**
	 * Specifies the component generating the event that triggers the validator.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the component generating the event that triggers the validator."
	 */
	String getTrigger();

	/**
	 * Specifies the event that triggers the validation.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the event that triggers the validation."
	 */
	String getTriggerEvent();
	
}
