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
	import flash.external.ExternalInterface;
	import mx.rpc.events.ResultEvent;
	
	import com.googlecode.jsfFlex.communication.core.ComponentValueMapper;
	import com.googlecode.jsfFlex.communication.logger.ILogger;
	import com.googlecode.jsfFlex.communication.logger.LoggerFactory;
	import com.googlecode.jsfFlex.communication.services.JsfFlexHttpService;
	import com.googlecode.jsfFlex.communication.utils.JsfFlexUtils;
	
	public class DataUpdateEventHandler extends AbstractEventHandler {
		
		private static const DATA_UPDATE_ATTRIBUTE_ATTR:String = "DATA_UPDATE_ATTRIBUTE";
		private static const DATA_UPDATE_VALUE_ATTR:String = "DATA_UPDATE_VALUE";
		private static var _log:ILogger;
		
		private var _srcId:String;
		private var _tgtId:String;
		private var _eventHandlerId:String;
		
		{
			_log = LoggerFactory.newJSLoggerInstance(DataUpdateEventHandler);
		}
		
		public function DataUpdateEventHandler(srcId:String, tgtId:String, eventHandlerId:String, eventName:String, additionalArgs:Object) {
			super(JsfFlexUtils.getCurrentApplication()[srcId], eventName);
			
			_srcId = srcId;
			_tgtId = tgtId;
			_eventHandlerId = eventHandlerId;
			activateListener();
		}
		
		override public function handleEvent(event:Event):void {
			_log.info("Executing a data update value request for component " + _tgtId);
			
			var compValMapper:ComponentValueMapper = ComponentValueMapper.getInstance(JsfFlexUtils.getCurrentApplication());
			var compValue:Object = compValMapper.getCompValue(_srcId)[0];
			var dataRequestParameters:Object = {};
			dataRequestParameters.componentId = _eventHandlerId;
			dataRequestParameters.methodToInvoke = ASYNC_PROCESS_REQUEST;
			dataRequestParameters[DATA_UPDATE_ATTRIBUTE_ATTR] = compValue.id;
			dataRequestParameters[DATA_UPDATE_VALUE_ATTR] = compValue.value;
			
			var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
			jsfFlexHttpServiceRequest.sendHttpRequest(ASYNC_SERVICE_REQUEST_URL, this,
															function (lastResult:Object, event:ResultEvent):void {
																_log.info("Returned from : " + ASYNC_PROCESS_REQUEST + " of src/target :" + _srcId + "/" + _tgtId);
																
																var updateValue:String = lastResult.UPDATE_VALUE_ATTRIBUTE;
																var compValue:Object = compValMapper.getCompValue(_tgtId)[0];
																JsfFlexUtils.getCurrentApplication()[_tgtId][compValue.id] = updateValue;
																
															}, dataRequestParameters, JsfFlexHttpService.POST_METHOD, JsfFlexHttpService.OBJECT_RESULT_FORMAT, null);
			
			
		}
		
	}
	
}