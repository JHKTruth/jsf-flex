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
package com.googlecode.jsfFlex.component.ext.service.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @JSFComponent
 *   name     = "jf:mxmlHTTPService"
 *   class    = "com.googlecode.jsfFlex.component.ext.service.ext.MXMLUIHTTPService"
 *   type     = "com.googlecode.jsfFlex.MXMLUIHTTPService"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.service.MXMLUIHTTPServiceTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLHTTPService"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "concurrency"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Value that indicates how to handle multiple calls to the same service."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "contentType"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Type of content for service requests."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "destination"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "An HTTPService destination name in the services-config.xml file."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "id"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Id of the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "method"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "HTTP method for sending the request."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "resultFormat"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Value that indicates how you want to deserialize the result returned by the HTTP call."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "showBusyCursor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "If true, a busy cursor is displayed while a service is executing."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "makeObjectsBindable"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "When this value is true, anonymous objects returned are forced to bindable objects."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "url"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Location of the service."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "useProxy"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Specifies whether to use the Flex proxy service."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "xmlEncode"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "ActionScript function used to encode a service request as XML."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "xmlDecode"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "ActionScript function used to decode a service result from XML."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fault"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when an HTTPService call fails."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "result"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when an HTTPService call returns successfully."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIHTTPService 
						extends MXMLUISimpleBase {
	
	
	
}
