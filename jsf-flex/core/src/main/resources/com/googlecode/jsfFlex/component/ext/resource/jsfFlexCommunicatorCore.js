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

if(typeof com.googlecode.jsfFlex.communication.core != "undefined"){
    throw new Error("com.googlecode.jsfFlex.communication.core already exists");
}

com.googlecode.jsfFlex.communication.core = {
	data 			:	{ 
							flashAppsKeyNamingContainer	: {},
							flashAppsKeyAppId			: {}
						},
	addFlashApp		: 	function(flashApp){
							var data = this.data;
							var flashAppsKeyNamingContainer = data.flashAppsKeyNamingContainer;
							var namingContainerPrefixList = flashAppsKeyNamingContainer[flashApp.namingContainerPrefix];
							if(namingContainerPrefixList == null){
								namingContainerPrefixList = [];
								flashAppsKeyNamingContainer[flashApp.namingContainerPrefix] = namingContainerPrefixList;
							}
							data.flashAppsKeyAppId[flashApp.appId] = flashApp;
							namingContainerPrefixList.push(flashApp);
				 		},
	getApplication	:	function(appId){
							if (navigator.appName.indexOf("Microsoft") != -1) {
								this.getApplication = function(appId) { return document.getElementById(appId); };
							}else{
								this.getApplication = 	function(appId){
														var doc = document;
														var initialAttempt = doc[appId];
														return initialAttempt ? initialAttempt : doc.getElementsByName(appId)[0];
													};
							}
							return this.getApplication(appId);
						},
	getCompValue	:	function(appId, objectId){
							var access = this.getApplication(appId);
							if(access == null){
								throw new Error("appId [" + appId + "] returned a null value during lookup");
							}
							var value = null;
							try{
								value = access.getCompValue(objectId);
							}catch(error){
								com.googlecode.jsfFlex.communication.logger.logMessage("Error while invoking getCompValue for appId, objectId [" + appId + ", " + objectId + "] : " + error, 5);
							}
							return value;
						}
};

if(typeof com.googlecode.jsfFlex.communication.core.domHelpers != "undefined"){
    throw new Error("com.googlecode.jsfFlex.communication.core.domHelpers already exists");
}

com.googlecode.jsfFlex.communication.core.domHelpers = {
	DISPATCH_EVENT_TYPE	:	{
								HTML_EVENTS		:	{	
														eventType	: "HTMLEvents", 
														events		: ["abort", "blur", "change", "error", "focus", "load", "reset", "resize", 
																				"select", "submit", "unload"]
													},
								MOUSE_EVENTS	:	{
														eventType	: "MouseEvents",
														events		: ["click", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"]
													},
								UI_EVENTS		:	{
														eventType	: "UIEvents",
														events		: ["DOMActivate", "DOMFocusIn", "DOMFocusOut"]
													}
							},
	data 			:	{ 
							eventListenerToRelease		: []
						},
	addEventListener	:	function(element, eventName, objectInstance, functionListener, argument, capturing, removeAtPageUnload){
								element = element == null ? window : element;
								removeAtPageUnload = removeAtPageUnload == null ? true : removeAtPageUnload;
								
								var index = eventName.toUpperCase().indexOf("ON");
								eventName = index == 0 ? eventName.substring(2) : eventName;
								
								var ieEventName = "on" + eventName;
								var functionRef;
								if(window.addEventListener){
									functionRef	=	function(event){
														functionListener.call(objectInstance, event, argument);
													};
									element.addEventListener(eventName, functionRef, capturing == null ? false : capturing);
								}else if(window.attachEvent){
									functionRef	=	function(event){
														functionListener.call(objectInstance, event, argument);
													};
									element.attachEvent(ieEventName, functionRef);
								}else{
									if(window.event){
										var previousIEFunction = element[ieEventName];
										element[ieEventName] = function(event){
															functionListener.call(objectInstance, event, argument);
															previousIEFunction();
														};
									}else{
										var previousFunction = element[eventName];
										element[eventName] = function(event){
																functionListener.call(objectInstance, event, argument);
																previousFunction();
															};
									}
								}
								
								this.data.eventListenerToRelease.push({element: element, eventName: eventName, releaseFunction: functionRef, capturing: capturing});
							},
	removeEventListener :	function(element, eventName, functionRef, capturing){
								element = element == null ? window : element;
								
								var index = eventName.toUpperCase().indexOf("ON");
								eventName = index == 0 ? eventName.substring(2) : eventName;
								
								var ieEventName = "on" + eventName;
								if(window.removeEventListener){
									element.removeEventListener(eventName, functionRef, capturing);
								}else if(window.detachEvent){
									element.detachEvent(ieEventName, functionRef);
								}
							},
	dispatchEvent		:	function(element, DISPATCH_EVENT_TYPE, eventTrigger, bubble, cancelable, data){
								var doc = document;
								if(typeof element == "string"){
									element = doc.getElementById(element);
								}
								
								bubble = bubble == null ? true : bubble;
								cancelable = cancelable == null ? true : cancelable;
								data = data == null ? new Object() : data;
								if(doc.createEvent){
									var event = doc.createEvent(DISPATCH_EVENT_TYPE.eventType);
									
									if(DISPATCH_EVENT_TYPE == this.DISPATCH_EVENT_TYPE.HTML_EVENTS){
										event.initEvent(eventTrigger, bubble, cancelable);
									}else if(DISPATCH_EVENT_TYPE == this.DISPATCH_EVENT_TYPE.MOUSE_EVENTS){
										event.initMouseEvent(eventTrigger, bubble, cancelable);
									}else if(DISPATCH_EVENT_TYPE == this.DISPATCH_EVENT_TYPE.UI_EVENTS){
										event.initUIEvent(eventTrigger, bubble, cancelable);
									}else{
										return;
									}
									event.data = data;
									element.dispatchEvent(event);
								}else if(doc.createEventObject){
									var index = eventTrigger.toUpperCase().indexOf("ON");
									if(index == -1){
										eventTrigger = "on" + eventTrigger;
									}
									var event = doc.createEventObject();
									event.data = data;
									element.fireEvent(eventTrigger, event);
								}else{
									return;
								}
							},
	appendElement		:	function(appendToElement, elementType, attributeList){
								var doc = document;
								var element = doc.createElement(elementType);
								for(var i=0, j=attributeList.length; i < j; i++){
									var attr = doc.createAttribute(attributeList[i].attribute);
									attr.nodeValue = attributeList[i].value;
									element.setAttributeNode(attr);
								}
								appendToElement.appendChild(element);
							},
	getElementByIdOrName:	function(elementId){
								var doc = document;
								var element = doc.getElementById(elementId);
								if(element == null){
									element = doc.getElementsByName(elementId)[0];
								}
								return element;
							},
	getEvent			:	function(event){
								return (window.event) ? window.event : event;
							},
	getSrcElement		:	function(event){
								return (event.target) ? event.target : event.srcElement;
							},
	stopEvent			:	function(event){
								if(event.stopPropagation){
									event.stopPropagation();
									if(event.preventDefault){
										event.preventDefault();
									}
								}else{
									event.cancelBubble = true;
									event.returnValue = false;
								}
							}
};

if(typeof com.googlecode.jsfFlex.communication.core.util != "undefined"){
    throw new Error("com.googlecode.jsfFlex.communication.core.util already exists");
}

com.googlecode.jsfFlex.communication.core.util = {
	sliceArguments		:	function(passedArgs, startIndex, stopIndex){
								if(passedArgs == null){
									passedArgs = [];
								}
								
								if(stopIndex == null){
									stopIndex = passedArgs.length;
								}
								
								if(passedArgs instanceof Array){
									passedArgs = passedArgs.slice(startIndex, stopIndex);
								}else if(typeof passedArgs.callee != "undefined"){
									//make a special case for Arguments object
									var args = [];
									for(var i=startIndex; i < stopIndex; i++){
										args.push(passedArgs[i]);
									}
									passedArgs = args;
								}else{
									var args = [];
									args.push(passedArgs);
									passedArgs = args;
								}
								
								return passedArgs;
							},
	callFunction		:	function(objectInstance, invokeFunction, argument){
								return invokeFunction.call(objectInstance, argument);
							},
	applyFunction		:	function(objectInstance, invokeFunction, passedArgs){
								//remember arguments within apply are passed as an array
								return invokeFunction.apply(objectInstance, this.sliceArguments(arguments, 2));
							}
};

//private namespace
(function() {
	var VALIDATION_ERROR_RESULT = "validationErrorResult";
	var JSON_RESULT = "jsonResult";
	
	var pageLoadSet = false;
	var currFormSubmitRef = null;
    var jsonResult = [];
    
    function amReady(readyAmI){
		var flashApp = com.googlecode.jsfFlex.communication.core.data.flashAppsKeyAppId[readyAmI];
		if(flashApp){
			if(flashApp.initValueObjects){
				return flashApp;
			}
		}else{
			/* Must not have been added yet, so add a simple interval */
			var handle = window.setInterval( function(){
													var flashApp = com.googlecode.jsfFlex.communication.core.data.flashAppsKeyAppId[readyAmI];
													if(flashApp){
														if(flashApp.initValueObjects){
															var access = com.googlecode.jsfFlex.communication.core.getApplication( readyAmI );
															access.populateInitValues( flashApp );
														}
														window.clearInterval(handle);
													}
												}, 500);
		}
	}
	
	function appendElement(jsonNodes){
		var htmlType;
		var attributeArray;
		var ele;
		var attr;
		var domHelpers = com.googlecode.jsfFlex.communication.core.domHelpers;
		for(var i=0, j=jsonNodes.length; i < j; i++){
			htmlType = jsonNodes[i].htmlType;
			attributeArray = jsonNodes[i].attributeArray;
			if(htmlType != null && htmlType != "null"){
				domHelpers.appendElement(currFormSubmitRef, htmlType, attributeArray);
			}
		}
	}
	
	function pageLoad(){
		if(pageLoadSet){
			return;
		}
		
		pageLoadSet = true;
		var doc = document;
		var domHelpers = com.googlecode.jsfFlex.communication.core.domHelpers;
		for(var i=0, j=doc.forms.length; i < j; i++){
			domHelpers.addEventListener(doc.forms[i], "submit", null, formSubmit, null, false, true);
		}
		
		domHelpers.addEventListener(window, "unload", null, pageUnLoad, null, false, true);
	}
	
	function pageUnLoad(){
		var domHelpers = com.googlecode.jsfFlex.communication.core.domHelpers;
		var eventListenerToRelease = domHelpers.data.eventListenerToRelease;
		for(var i=0, j=eventListenerToRelease.length; i < j; i++){
			domHelpers.removeEventListener(eventListenerToRelease[i].element, eventListenerToRelease[i].eventName, 
												eventListenerToRelease[i].releaseFunction, eventListenerToRelease[i].capturing);
		}
	}
	
	function formSubmit(event){
		/*
		 * during the page creation :
		 * 	JSON with =>
		 *	  appId 					: applicationId specifying the flash app
		 *    namingContainerPrefix		: namingContainerPrefix [i.e. form that this flashApp is affiliated with]
		 *	  initValueObjects 			: an array of...
		 *									id			:	id of the component
		 *									initValues	:	an array of...
		 *													attribute	:	attribute to set for the initValue
		 *													value		:	value to set as the initValue
		 *	  
		 *  will be created
		 */
		var domHelpers = com.googlecode.jsfFlex.communication.core.domHelpers;
		currFormSubmitRef = domHelpers.getSrcElement( domHelpers.getEvent(event) );
		
		var core = com.googlecode.jsfFlex.communication.core;
		var namingContainerPrefixList = core.data.flashAppsKeyNamingContainer[currFormSubmitRef.id];
		var access;
		
		var validationError = false;
		jsonResult = [];
		for(var i=0, j=namingContainerPrefixList.length; i < j; i++){
			/** If there does not exist any value to retrieve of, simply continue */
			if(!namingContainerPrefixList[i].initValueObjects){
				continue;
			}
			access = core.getApplication(namingContainerPrefixList[i].appId);
			try{
				var processedResult = access.formSubmitting(namingContainerPrefixList[i]);
				
				/*
				 * will be an Object with :
				 *		type 	: [validationErrorResult | jsonResult]
				 *		result	: [nothing | jsonResult]
				 * 
				 * For jsonResult :
				 * 		will have an array of array =>
				 *	 		array	: that will contain array as elements
				 *				arrayElement :	will contain htmlType to create and an array of attributes
				 *					htmlType		:	htmlType to create of [node]
				 *					attributeArray	:	an array of attributes
				 *					attribute	:	attribute to create of
				 *					value		:	value to set to
				 *	
				 */
				
				if(processedResult.type == VALIDATION_ERROR_RESULT){
					validationError = true;
				}else if(processedResult.type == JSON_RESULT){
					jsonResult.push(processedResult.result);
				}
				
			}catch(error){
				validationError = true;
				com.googlecode.jsfFlex.communication.logger.logMessage("During the formSubmitting process, an error occurred while invoking formSubmitting for appId [" + 
																		namingContainerPrefixList[i].appId + "] : " + error, 5);
				domHelpers.stopEvent(event);
				return false;
			}
		}
		
		var returnValue = true;
		
		if(validationError){
			domHelpers.stopEvent(event);
			returnValue = false;
		}else{
			//means all inputs passed validation
			for(var i=0, k=jsonResult.length; i < k; i++){
				for(var j=0, l=jsonResult[i].length; j < l; j++){
					appendElement(jsonResult[i][j]);
				}
			}
		}
		
		return returnValue;
	}
	
	//callers
	com.googlecode.jsfFlex.communication.core.amReady = amReady;
	com.googlecode.jsfFlex.communication.core.pageLoad = pageLoad;
	
})();