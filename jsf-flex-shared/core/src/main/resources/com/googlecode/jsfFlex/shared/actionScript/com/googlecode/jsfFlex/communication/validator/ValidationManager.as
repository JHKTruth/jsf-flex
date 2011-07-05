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
 * This class will manage the validations needed when submitting the<br>
 * information to the server side. It will traverse through the list of validation<br>
 * components, invoke the validate function for the components, and return their result.<br>
 * On the Javascript side, it will check if there are any errors and decide whether to<br>
 * submit the form depending upon that logic.<br>
 * 
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.validator
{
	import mx.core.UIComponent;
	import mx.validators.Validator;
	import mx.validators.ValidationResult;
	
	import mx.events.ValidationResultEvent;
	
	public class ValidationManager {
		
		private static const VALIDATION_ERROR_RESULT:String = "validationErrorResult";
		
		private var _errorTextComponentId:String;
		private var _validationComponentList:Array;
		private var _refApp:UIComponent;
		
		public function ValidationManager(errorTextComponentId:String, refApp:UIComponent) {
			super();
			_errorTextComponentId = errorTextComponentId;
			_validationComponentList = new Array();
			_refApp = refApp;
		}
		
		public function addValidatorId(validatorId:String):void {
			_validationComponentList.push(validatorId);
		}
		
		public function validateComponents():Object {
			
			var errorMessage:String = new String();
			var validationErrorOccurred:Boolean = Boolean(false);
			for each (var validatorId:String in _validationComponentList){
				var currValidator:Validator = _refApp[validatorId];
				
				var currValidationResult:ValidationResultEvent = currValidator.validate();
				if(currValidationResult.results != null){
					//means there was an error
					
					if(!validationErrorOccurred){
						validationErrorOccurred = Boolean(true);
						errorMessage += "<ul>";
					}
					
					for each(var validationError:ValidationResult in currValidationResult.results){
						
						if(validationError.isError){
							errorMessage += "<li> " + validationError.errorCode + " : " + validationError.errorMessage + "</li>";
						}
						
					}
					
				}
				
			}
			if(validationErrorOccurred){
				errorMessage += "</ul>";
			}
			
			_refApp[_errorTextComponentId].htmlText = errorMessage;
			return {type: VALIDATION_ERROR_RESULT, result: validationError};
		}
		
	}
	
}