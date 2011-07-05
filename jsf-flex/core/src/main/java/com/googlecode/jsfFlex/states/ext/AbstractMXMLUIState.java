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
 *   name     = "jf:mxmlState"
 *   class    = "com.googlecode.jsfFlex.states.ext.MXMLUIState"
 *   type     = "com.googlecode.jsfFlex.MXMLUIState"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIStateTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLState"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "basedOn"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the view state upon which this view state is based, or null if this view state is not based on a named view state."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "name"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the view state."
 * 							,
 * 							
 *   						@JSFJspProperty
 *   						 name		= "overrides"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The overrides for this view state, as an Array of objects that implement the IOverride interface."
 *
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIState 
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
