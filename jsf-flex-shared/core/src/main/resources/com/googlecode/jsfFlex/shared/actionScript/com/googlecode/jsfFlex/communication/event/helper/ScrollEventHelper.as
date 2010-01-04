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
package com.googlecode.jsfFlex.communication.event.helper
{
	import flash.events.EventDispatcher;
	import flash.utils.clearInterval;
	import flash.utils.setInterval;
	
	import mx.core.ScrollControlBase;
	import mx.events.ScrollEvent;
	import mx.events.ScrollEventDirection;
	
	public class ScrollEventHelper {
		
		private var _componentInstance:Object;
		private var _callBackInstance:Object;
		private var _callBackFunction:Function;
		private var _intervalTime:uint;
		private var _verticalEventDirection:Boolean;
		
		private var _clearIntervalRef:uint;
		private var _currentlyCheckingScrollInfo:Boolean;
		private var _lockScrollParameters:Boolean;
		private var _modifiedFromPreviousIntervalCheck:Boolean;
		
		private var _scrolledDown:Boolean;
		private var _verticalScrollPosition:uint;
		
		public function ScrollEventHelper(componentInstance:Object, callBackInstance:Object, callBackFunction:Function, 
											intervalTime:uint = 500, verticalEventDirection:Boolean = true) {
			super();
			
			_componentInstance = componentInstance;
			_callBackInstance = callBackInstance;
			_callBackFunction = callBackFunction;
			_intervalTime = intervalTime;
			_verticalEventDirection = verticalEventDirection;
		}
		
		public function activateListener():void {
			var componentToListenFor:EventDispatcher = _componentInstance as EventDispatcher;
			componentToListenFor.addEventListener(ScrollEvent.SCROLL, scrolledListener, false, 0, true);
			_clearIntervalRef = setInterval( scrollVerifyFunction, _intervalTime);
		}
		
		public function deActivateListener():void {
			var componentToListenFor:EventDispatcher = _componentInstance as EventDispatcher;
			componentToListenFor.removeEventListener(ScrollEvent.SCROLL, scrolledListener);
			clearInterval(_clearIntervalRef);
		}
		
		public function lockScrollParameters():void {
			_lockScrollParameters = true;
		}
		
		public function unLockScrollParameters():void {
			_lockScrollParameters = false;
		}
		
		public function resetState(verticalScrollPosition:uint):void {
			_verticalScrollPosition = verticalScrollPosition;
			_modifiedFromPreviousIntervalCheck = false;
		}
		
		public function get scrolledDown():Boolean {
			return _scrolledDown;
		}
		
		public function get verticalScrollPosition():uint {
			return _verticalScrollPosition;
		}
		
		private function scrolledListener(event:ScrollEvent):void{
			var direction:String = _verticalEventDirection ? ScrollEventDirection.VERTICAL : ScrollEventDirection.HORIZONTAL;
			
			if(event.direction == direction && event.delta != 0 
						&& !_currentlyCheckingScrollInfo && !_lockScrollParameters){
				
				_currentlyCheckingScrollInfo = true;
				
				_scrolledDown = (event.delta > 0);
				
				var scrollControlBaseInstance:ScrollControlBase = _componentInstance as ScrollControlBase;
				var currVerticalScrollPosition:uint = scrollControlBaseInstance.verticalScrollPosition;
				
				_modifiedFromPreviousIntervalCheck = currVerticalScrollPosition != _verticalScrollPosition;
				_verticalScrollPosition = scrollControlBaseInstance.verticalScrollPosition;
				
				_currentlyCheckingScrollInfo = false;
				
			}
			
		}
		
		private function scrollVerifyFunction():void {
			
			_currentlyCheckingScrollInfo = true;
			
			if(_modifiedFromPreviousIntervalCheck){
				_callBackFunction.call(_callBackInstance);
			}
			
			_currentlyCheckingScrollInfo = false;
			
		}
		
	}
	
}