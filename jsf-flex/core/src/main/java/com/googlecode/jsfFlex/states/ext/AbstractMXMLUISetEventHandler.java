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
package com.googlecode.jsfFlex.states.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @JSFComponent
 *   name     = "jf:mxmlSetEventHandler"
 *   class    = "com.googlecode.jsfFlex.states.ext.MXMLUISetEventHandler"
 *   type     = "com.googlecode.jsfFlex.MXMLUISetEventHandler"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUISetEventHandlerTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLSetEventHandler"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "name"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the event whose handler is being set."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "handlerFunction"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The handler function for the event."
 * 							,
 * 							
 *   						@JSFJspProperty
 *   						 name		= "target"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that dispatches the event."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "handler"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The event handler function to execute in response to the event that is specified by the name property."
 * 							
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUISetEventHandler 
						extends MXMLUISimpleBase {
	
	/**
	 * Id of the component.
	 * 
	 * @JSFProperty
	 *     desc			= "Id of the component."
	 *     inheritedTag	= true
	 */
	public String getId(){
		return super.getId();
	}
	
}
