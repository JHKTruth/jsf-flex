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
 *   name     = "jf:mxmlRemoteObject"
 *   class    = "com.googlecode.jsfFlex.component.ext.service.ext.MXMLUIRemoteObject"
 *   type     = "com.googlecode.jsfFlex.MXMLUIRemoteObject"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.service.MXMLUIRemoteObjectTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLRemoteObject"
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
 *   						 name		= "destination"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The destination of the service."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "id"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Id of the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "endpoint"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "This property allows the developer to quickly specify an endpoint for a RemoteObject destination without referring to a services configuration file at compile time or programmatically creating a ChannelSet."
 *   						, 
 *   						
 *   						@JSFJspProperty
 * 							 name		= "showBusyCursor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "If true, a busy cursor is displayed while a service is executing."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "source"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Lets you specify a source value on the client; not supported for destinations that use the JavaAdapter."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "makeObjectsBindable"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "When this value is true, anonymous objects returned are forced to bindable objects."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fault"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The fault event is dispatched when a service call fails and isn't handled by the Operation itself."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "result"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The result event is dispatched when a service call successfully returns and isn't handled by the Operation itself."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIRemoteObject 
						extends MXMLUISimpleBase {
	
	
	
}
