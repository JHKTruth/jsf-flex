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
 * @author Ji Hoon Kim
 */

if(typeof com == "undefined"){
    com = {};
}else if(typeof com != "object"){
	throw new Error("com exists but is not of type object");
}

if(typeof com.googlecode == "undefined"){
    com.googlecode = {};
}else if(typeof com.googlecode != "object"){
	throw new Error("com.googlecode exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex == "undefined"){
    com.googlecode.jsfFlex = {};
}else if(typeof com.googlecode.jsfFlex != "object"){
	throw new Error("com.googlecode.jsfFlex exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex.communication == "undefined"){
    com.googlecode.jsfFlex.communication = {};
}else if(typeof com.googlecode.jsfFlex.communication != "object"){
	throw new Error("com.googlecode.jsfFlex.communication exists but is not of type object");
}

if(typeof com.googlecode.jsfFlex.communication.logger == "undefined"){
    com.googlecode.jsfFlex.communication.logger = {};
}else if(typeof com.googlecode.jsfFlex.communication.logger != "object"){
	throw new Error("com.googlecode.jsfFlex.communication.logger exists but is not of type object");
}

//private namespace
(function() {
	function logMessage(className, message, severity){
		if(typeof console != "undefined"){
			var displayAsObject;
			if((typeof message == "string") || (message instanceof String)){
				message = className + " : " + message;
			} else{
				displayAsObject = message;
				message = className + " printing object : ";
			}
			
			switch(severity){
				case 1 :	console.log(message); break;
				case 2 :	console.debug(message); break;
				case 3 :	console.info(message); break;
				case 4 :	console.warn(message); break;
				case 5 :	console.error(message); break;
			}
			if(displayAsObject != null){
				console.dir(displayAsObject);
			}
		}else{
			if(severity == 5){
				throw new Error(message);
			}
		}
	}
	
	//callers
	com.googlecode.jsfFlex.communication.logger.logMessage = logMessage;
})();
