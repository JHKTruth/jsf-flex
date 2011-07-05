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
 * This class will represent the constants needed for the web project
 * such as the contextPath that the swf rests within. Technically one can
 * evalute this value using communication to Javascript, but gets a bit messy
 * and can possibly cause unexpected issues depending on how the user sets up
 * the web app [sanity sake].
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.utils
{
	import flash.errors.IllegalOperationError;
	
	public class WebConstants {
		
		public static const WEB_CONTEXT_PATH:String = "{webContextPath}";
		
		public function WebConstants(){
			throw new IllegalOperationError("WebConstants should be used statically");
		}
		
	}
	
}