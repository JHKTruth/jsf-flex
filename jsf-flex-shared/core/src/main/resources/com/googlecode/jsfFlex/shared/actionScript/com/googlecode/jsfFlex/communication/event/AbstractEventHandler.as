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
 * TODO : Implement better later
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.event
{
	import flash.errors.IllegalOperationError;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import com.googlecode.jsfFlex.communication.utils.WebConstants;
	
	public class AbstractEventHandler {
		
		protected static const ASYNC_SERVICE_REQUEST_URL:String = WebConstants.WEB_CONTEXT_PATH 
																						+ "/jsfFlexHttpServiceRequestListener/ayncProcessRequestServiceRequest";
		protected static const ASYNC_PROCESS_REQUEST:String = "ayncProcessRequest";
		
		private var _componentInstance:EventDispatcher;
		private var _eventName:String;
		
		public function AbstractEventHandler(componentInstance:EventDispatcher, eventName:String) {
			super();
			
			_componentInstance = componentInstance;
			_eventName = eventName;
		}
		
		public function activateListener():void {
			_componentInstance.addEventListener(_eventName, handleEvent, false, 0, true);
		}
		
		public function deActivateListener():void {
			_componentInstance.removeEventListener(_eventName, handleEvent);
		}
		
		public function handleEvent(event:Event):void {
			throw new IllegalOperationError("handleEvent must be implemented by the sub class");
		}
		
	}
	
}