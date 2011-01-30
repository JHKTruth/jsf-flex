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
	import flash.external.ExternalInterface;
	
	internal class JavaScriptLogger extends AbstractLogger {
		
		private static const JS_COMMUNICATION_LOG_MESSAGE_FUNCTION:String = "com.googlecode.jsfFlex.communication.logger.logMessage";
		
		public function JavaScriptLogger(logClass:Class) {
			super(logClass);
		}
		
		override public function logMessage(message:Object, severity:int):void {
			ExternalInterface.call(JS_COMMUNICATION_LOG_MESSAGE_FUNCTION, getClassName(), message, severity);
		}
		
	}
	
}