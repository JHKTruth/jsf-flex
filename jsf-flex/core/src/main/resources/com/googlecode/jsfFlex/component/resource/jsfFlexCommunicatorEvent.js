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
 
/**
 * @author Ji Hoon Kim
 */

if(typeof com == "undefined"){
    com = {};
}else if(typeof com != "object"){
	throw new Error("com exists but is not of type object");
}

if(typeof com.googlecode == "undefined"){
    com.googlecode = {};
}else if(typeof com.googlecode != "object"){
	throw new Error("com.googlecode exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex == "undefined"){
    com.googlecode.jsfFlex = {};
}else if(typeof com.googlecode.jsfFlex != "object"){
	throw new Error("com.googlecode.jsfFlex exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex.communication == "undefined"){
    com.googlecode.jsfFlex.communication = {};
}else if(typeof com.googlecode.jsfFlex.communication != "object"){
	throw new Error("com.googlecode.jsfFlex.communication exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex.communication.event != "undefined"){
    throw new Error("com.googlecode.jsfFlex.communication.event already exists");
}

com.googlecode.jsfFlex.communication.event = {
	submitForm			:	function(submitElementId, formId){
								var domHelperRef = com.googlecode.jsfFlex.communication.core.domHelpers;
								var formElement = domHelperRef.getElementByIdOrName(formId);
								var submitElement = domHelperRef.getElementByIdOrName(submitElementId);
								
								/*
								 * First create an input hidden element as a child of form
								 * so that one can know which component triggered the submission.
								 * Then dispatch a submit event
								 */
								domHelperRef.appendElement(formElement, "input", [{attribute: "type", value: "hidden"}, {attribute: "name", value: submitElementId},
															{attribute: "value", value: "submitted"}]);
								
								domHelperRef.dispatchEvent(formElement, domHelperRef.DISPATCH_EVENT_TYPE.HTML_EVENTS, "submit");
								if(document.all){
									/*
									 * since IE does not invoke the submit function of the form HTML element, 
									 * will invoke it here. Note that this is possible because dispatchEvent and 
									 * fireEvent work synchronously rather then deferring the execution until 
									 * all previously pending events have been handled
									 */
									formElement.submit();
								}
							}
};