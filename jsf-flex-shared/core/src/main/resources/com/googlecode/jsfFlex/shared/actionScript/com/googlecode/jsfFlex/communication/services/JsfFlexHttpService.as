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
 * This class is a simple wrapper to HTTPService for JsfFlex.
 * In future it is considered to improve the implementation by adding caching and etcetera.
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.services
{
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	public class JsfFlexHttpService {
		
		public static const ARRAY_RESULT_FORMAT:String = "array";
		public static const E4X_RESULT_FORMAT:String = "e4x";
		public static const FLASH_VARS_RESULT_FORMAT:String = "flashvars";
		public static const OBJECT_RESULT_FORMAT:String = "object";
		public static const TEXT_RESULT_FORMAT:String = "text";
		public static const XML_RESULT_FORMAT:String = "xml";
		
		public static const GET_METHOD:String = "GET";
		public static const POST_METHOD:String = "POST";
		
		private static const SERVLET_ARRAY_RESULT_FORMAT:String = "array";
		private static const SERVLET_NAME_VALUE_RESULT_FORMAT:String = "nameValue";
		private static const SERVLET_OBJECT_RESULT_FORMAT:String = "object";
		private static const SERVLET_RAW_RESULT_FORMAT:String = "raw";
		private static const SERVLET_XML_RESULT_FORMAT:String = "xml";
		
		private static const SERVLET_RETURN_METHOD:String = "servletReturnMethod";
		
		public function JsfFlexHttpService() {
			super();
		}
		
		public function sendHttpRequest(serviceUrl:String, thisObject:Object, callBack:Function, parameters:Object = null, method:String = GET_METHOD, 
													resultFormatMethod:String = XML_RESULT_FORMAT, rootUrl:String = null):void {
			//NOTE : rootUrl is only to specify absolute URL, if not specified the path to SWF is used
			var httpRequest:HTTPService = rootUrl == null ? new HTTPService() : new HTTPService(rootUrl);
			httpRequest.url = serviceUrl;
			httpRequest.method = method;
			
			var servletResultFormat:String;
			switch(resultFormatMethod){
				case ARRAY_RESULT_FORMAT : servletResultFormat = SERVLET_ARRAY_RESULT_FORMAT; break;
				case FLASH_VARS_RESULT_FORMAT : servletResultFormat = SERVLET_NAME_VALUE_RESULT_FORMAT; break;
				case OBJECT_RESULT_FORMAT : servletResultFormat = SERVLET_OBJECT_RESULT_FORMAT; break;
				case TEXT_RESULT_FORMAT : servletResultFormat = SERVLET_RAW_RESULT_FORMAT; break;
				case E4X_RESULT_FORMAT :
				case XML_RESULT_FORMAT :
				default : servletResultFormat = XML_RESULT_FORMAT; break;
			}
			
			parameters = parameters == null ? {} : parameters;
			parameters[SERVLET_RETURN_METHOD] = servletResultFormat;
			
			httpRequest.resultFormat = resultFormatMethod;
			httpRequest.addEventListener(ResultEvent.RESULT, function(event:ResultEvent):void{
																	httpRequest.removeEventListener(ResultEvent.RESULT, arguments.callee, false);
																	callBack.call(thisObject, httpRequest.lastResult, event);
																}, false, 0, true);
			
			httpRequest.send(parameters);
		}
		
	}
	
}