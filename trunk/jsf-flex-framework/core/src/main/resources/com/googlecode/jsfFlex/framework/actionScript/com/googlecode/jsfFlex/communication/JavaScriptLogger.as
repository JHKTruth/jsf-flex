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
 * This class will be used for browser logging by all ActionScript classes
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication
{
	import flash.external.ExternalInterface;
	import flash.utils.getQualifiedClassName;
	
	internal class JavaScriptLogger{
		
		private static var CLASS_NAME:String = new String();
		
		public function JavaScriptLogger(){
			//TODO implement it better later
			var runTimeObjectName:String = getQualifiedClassName(this);
			if(runTimeObjectName != null){
				var qualifiedClassName:Array = runTimeObjectName.split(":");
				if(qualifiedClassName != null){
					CLASS_NAME = qualifiedClassName[qualifiedClassName.length - 1];
				}
			}
		}
		
		protected function log(errorMessage:String):void {
			logMessage(errorMessage, 1);
		}
		
		protected function logDebug(errorMessage:String):void {
			logMessage(errorMessage, 2);
		}
		
		protected function logInfo(errorMessage:String):void {
			logMessage(errorMessage, 3);
		}
		
		protected function logWarn(errorMessage:String):void {
			logMessage(errorMessage, 4);
		}
		
		protected function logError(errorMessage:String):void {
			logMessage(errorMessage, 5);
		}
		
		private function logMessage(message:String, severity:int):void {
			message = CLASS_NAME + " : " + message;
			ExternalInterface.call("com.googlecode.jsfFlex.communication.logFlashMessage", message, severity);
		}
		
	}
	
}