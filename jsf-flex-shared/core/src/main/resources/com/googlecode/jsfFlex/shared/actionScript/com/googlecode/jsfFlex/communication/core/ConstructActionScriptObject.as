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
 * TODO: Implement this better or find an alternative method
 * This class will provide static methods for construction of ActionScript Objects<br>
 * One use is within ComponentValueMapper.as where conversion needs to take place<br>
 * when setting of the initial values of the components.
 * 
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.core
{
	import flash.errors.IllegalOperationError;
	
	public class ConstructActionScriptObject {
		
		public function ConstructActionScriptObject(){
			throw new IllegalOperationError("ConstructActionScriptObject should be used statically");
		}
		
		public static function constructDateObject(dateConstructorArguments:Array):Date {
			if(dateConstructorArguments == null || dateConstructorArguments.length < 7){
				return null;
			}
			return new Date(dateConstructorArguments[0], dateConstructorArguments[1], dateConstructorArguments[2], dateConstructorArguments[3], 
								dateConstructorArguments[4], dateConstructorArguments[5], dateConstructorArguments[6]);
		}
		
	}
	
}