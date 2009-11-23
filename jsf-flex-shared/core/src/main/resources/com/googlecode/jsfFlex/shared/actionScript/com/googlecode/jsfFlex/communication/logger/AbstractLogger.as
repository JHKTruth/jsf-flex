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
 * This class will be used for browser logging
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.logger
{
	import flash.errors.IllegalOperationError;
	import flash.events.Event;
	
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import flash.utils.clearInterval;
	import flash.utils.setInterval;
	
	import com.googlecode.jsfFlex.communication.utils.WebConstants;
	
	internal class AbstractLogger implements ILogger {
		
		private static const JSF_FLEX_FLASH_APPLICATION_CONFIG:String = WebConstants.WEB_CONTEXT_PATH + "/swf/jsfFlexFlashApplicationConfig.xml";
		private static const PRIOR_TO_LOG_MODE_SETTING_MESSAGES:Array = new Array();
		
		private static var _clearIntervalRef:uint;
		private static var _loader:URLLoader;
		private static var _logModeLoaded:Boolean;
		
		private static var _isLog:Boolean;
		private static var _isDebug:Boolean;
		private static var _isInfo:Boolean;
		private static var _isWarn:Boolean;
		private static var _isError:Boolean;
		
		{
			_clearIntervalRef = setInterval( logPreLogModeSettingMessages, 500);
			_loader = new URLLoader();
			
			_loader.addEventListener(Event.COMPLETE, function (event:Event):void {
										_loader.removeEventListener(Event.COMPLETE, arguments.callee);
										var jsfFlexFlashApplicationConfig:XML = new XML(_loader.data);
										var _logMode:uint = int(jsfFlexFlashApplicationConfig.flash_to_javascript_log_level.toString());
										
										switch(_logMode){
											case 1 : _isLog = true;
											case 2 : _isDebug = true;
											case 3 : _isInfo = true;
											case 4 : _isWarn = true;
											case 5 : _isError = true;
										}
										
										_loader = null;
										_logModeLoaded = true;
										clearInterval(_clearIntervalRef);
										logPreLogModeSettingMessages();
									});
			
			try{
				_loader.load(new URLRequest(JSF_FLEX_FLASH_APPLICATION_CONFIG));
			}catch(loadingError:Error){
				trace("Failure in loading of the jsfFlexFlashApplicationConfig.xml file");
			}
		}
		
		public static function logPreLogModeSettingMessages():void {
			
			if(!_logModeLoaded){
				return;
			}
			
			for each(var messageObject:Object in PRIOR_TO_LOG_MODE_SETTING_MESSAGES){
				var currSeverity:int = messageObject.severity;
				var currFunction:Function = messageObject.method;
				var okayLogLevel:Boolean = false;
				
				switch(currSeverity){
					case 1 : if(_isLog) okayLogLevel = true; break;
					case 2 : if(_isDebug) okayLogLevel = true; break;
					case 3 : if(_isInfo) okayLogLevel = true; break;
					case 4 : if(_isWarn) okayLogLevel = true; break;
					case 5 : if(_isError) okayLogLevel = true; break;
				}
				
				if(okayLogLevel){
					currFunction.call(messageObject.instanceRef, messageObject.message, currSeverity);
				}
			}
			
		}
		
		public function AbstractLogger(logClass:Class) {
			super();
		}
		
		public function log(message:String):void {
			if(!_logModeLoaded){
				PRIOR_TO_LOG_MODE_SETTING_MESSAGES.push({instanceRef: this, method: logMessage, message: message, severity: 1});
				return;
			}
			if(_isLog){
				logMessage(message, 1);
			}
		}
		
		public function debug(debugMessage:String):void {
			if(!_logModeLoaded){
				PRIOR_TO_LOG_MODE_SETTING_MESSAGES.push({instanceRef: this, method: logMessage, message: debugMessage, severity: 2});
				return;
			}
			if(_isDebug){
				logMessage(debugMessage, 2);
			}
		}
		
		public function info(infoMessage:String):void {
			if(!_logModeLoaded){
				PRIOR_TO_LOG_MODE_SETTING_MESSAGES.push({instanceRef: this, method: logMessage, message: infoMessage, severity: 3});
				return;
			}
			if(_isInfo){
				logMessage(infoMessage, 3);
			}
		}
		
		public function warn(warnMessage:String):void {
			if(!_logModeLoaded){
				PRIOR_TO_LOG_MODE_SETTING_MESSAGES.push({instanceRef: this, method: logMessage, message: warnMessage, severity: 4});
				return;
			}
			if(_isWarn){
				logMessage(warnMessage, 4);
			}
		}
		
		public function error(errorMessage:String):void {
			if(!_logModeLoaded){
				PRIOR_TO_LOG_MODE_SETTING_MESSAGES.push({instanceRef: this, method: logMessage, message: errorMessage, severity: 5});
				return;
			}
			if(_isError){
				logMessage(errorMessage, 5);
			}
		}
		
		public function logMessage(message:String, severity:int):void {
			throw new IllegalOperationError("logMessage must be implemented by the sub class");
		}
		
	}
	
}