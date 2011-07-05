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
 *   name     = "jf:mxmlAddChild"
 *   class    = "com.googlecode.jsfFlex.states.ext.MXMLUIAddChild"
 *   type     = "com.googlecode.jsfFlex.MXMLUIAddChild"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIAddChildTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLAddChild"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "target"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The child to be added."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "targetFactory"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The factory that creates the child."
 * 							,
 * 							
 *   						@JSFJspProperty
 *   						 name		= "creationPolicy"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The creation policy for this child."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "position"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The position of the child in the display list, relative to the object specified by the relativeTo property."
 * 							
 *   						,
 *   						@JSFJspProperty
 *   						 name		= "relativeTo"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The object relative to which the child is added."
 *
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIAddChild 
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
