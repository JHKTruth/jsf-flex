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
 *   name     = "jf:mxmlSetProperty"
 *   class    = "com.googlecode.jsfFlex.states.ext.MXMLUISetProperty"
 *   type     = "com.googlecode.jsfFlex.MXMLUISetProperty"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUISetPropertyTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLSetProperty"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "id"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "ID of the component."
 *   						,
 *   						
 * 							@JSFJspProperty
 * 							 name		= "name"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the property to change."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "target"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The object containing the property to be changed."
 * 							,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "value"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The new value for the property."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUISetProperty 
						extends MXMLUISimpleBase {
	
}
