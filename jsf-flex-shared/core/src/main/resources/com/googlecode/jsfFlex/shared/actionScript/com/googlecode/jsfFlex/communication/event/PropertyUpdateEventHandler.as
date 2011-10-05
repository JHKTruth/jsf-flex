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
 * TODO : implement it better later
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.event
{
	import flash.events.Event;
	import mx.core.UIComponent;
	import mx.rpc.events.ResultEvent;
	
	import com.googlecode.jsfFlex.communication.logger.ILogger;
	import com.googlecode.jsfFlex.communication.logger.LoggerFactory;
	import com.googlecode.jsfFlex.communication.services.JsfFlexHttpService;
	import com.googlecode.jsfFlex.communication.utils.JsfFlexUtils;
	
	public class PropertyUpdateEventHandler extends AbstractEventHandler {
		
		private static const SOURCE_PROPERTY_CURRENT_VALUE_ATTR:String = "SOURCE_PROPERTY_CURRENT_VALUE";
		private static var _log:ILogger;
		
		private var _srcId:String;
		private var _tgtId:String;
		private var _eventHandlerId:String;
		private var _additionalArgs:Object;
		private var _sourceValueHolderObject:Object;
		private var _targetValueUpdateObject:Object;
		
		private var _sourceValuePropertyField:String;
		private var _targetValuePropertyField:String;
		
		{
			_log = LoggerFactory.newJSLoggerInstance(PropertyUpdateEventHandler);
		}
		
		public function PropertyUpdateEventHandler(srcId:String, tgtId:String, eventHandlerId:String, eventName:String, additionalArgs:Object) {
			super(JsfFlexUtils.getCurrentApplication()[srcId], eventName);
			
			var app:UIComponent = JsfFlexUtils.getCurrentApplication();
			_srcId = srcId;
			_tgtId = tgtId;
			_eventHandlerId = eventHandlerId;
			_additionalArgs = additionalArgs;
			
			/*
			 * Traverse until length - 1, since one wishes to get the reference of the 
			 * Object that holds the property of which one wishes to fetch or update.
			 *
			 * Can have a function to take care of this task, since ActionScript 3 is
			 * pass by reference
			 */
			
			var sourceComponent:Object = app[_srcId];
			var sourcePropertyTraverseArray:Array = _additionalArgs.SOURCE_PROPERTY;
			_sourceValuePropertyField = sourcePropertyTraverseArray[sourcePropertyTraverseArray.length - 1];
			setPropertyObjects(sourceComponent, true, sourcePropertyTraverseArray);
			
			var targetComponent:Object = app[_tgtId];
			var targetPropertyTraverseArray:Array = _additionalArgs.TARGET_PROPERTY;
			_targetValuePropertyField = targetPropertyTraverseArray[targetPropertyTraverseArray.length - 1]; 
			setPropertyObjects(targetComponent, false, targetPropertyTraverseArray);
			
			activateListener();
		}
		
		private function setPropertyObjects(component:Object, srcValueObject:Boolean, propertyTraverseList:Array):void {
			_log.info("PropertyTraverseList length is " + propertyTraverseList.length);
			var valueHolder:Object;
			if(propertyTraverseList.length == 1){
			    valueHolder = component;
			}else{
				valueHolder = component[propertyTraverseList[0]];
				for(var i:uint=1, j:uint=propertyTraverseList.length-1; i < j; i++){
					valueHolder = valueHolder[propertyTraverseList[i]];
				}
			}
			
			if(srcValueObject){
			    _sourceValueHolderObject = valueHolder;
			}else{
			    _targetValueUpdateObject = valueHolder;
			}
			
		}
		
		override public function handleEvent(event:Event):void {
			_log.info("Executing a data update value request for component " + _tgtId);
			
			/*
			 * Since _sourceValueHolderObject and _targetValueUpdateObject have already been
			 * set, only need to get the property and update the property by using the last 
			 * property within the list
			 */
			
			var currPropertyValue:Object = _sourceValueHolderObject[_sourceValuePropertyField];
			
			var dataRequestParameters:Object = {};
			dataRequestParameters.componentId = _eventHandlerId;
			dataRequestParameters.methodToInvoke = ASYNC_PROCESS_REQUEST;
			dataRequestParameters[SOURCE_PROPERTY_CURRENT_VALUE_ATTR] = currPropertyValue;
			
			var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
			jsfFlexHttpServiceRequest.sendHttpRequest(ASYNC_SERVICE_REQUEST_URL, this,
															function (lastResult:Object, event:ResultEvent):void {
																_log.info("Returned from : " + ASYNC_PROCESS_REQUEST + " of src/target :" + _srcId + "/" + _tgtId);
																
																var updateValue:String = lastResult.UPDATE_VALUE_ATTRIBUTE;
																_targetValueUpdateObject[_targetValuePropertyField] = updateValue;
																
															}, dataRequestParameters, JsfFlexHttpService.POST_METHOD, JsfFlexHttpService.OBJECT_RESULT_FORMAT, null);
			
			
		}
		
	}
	
}