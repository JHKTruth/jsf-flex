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
package com.googlecode.jsfFlex.component.ext.data.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @JSFComponent
 *   name     = "jf:mxmlArrayCollection"
 *   class    = "com.googlecode.jsfFlex.component.ext.data.ext.MXMLUIArrayCollection"
 *   type     = "com.googlecode.jsfFlex.MXMLUIArrayCollection"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.data.MXMLUIArrayCollectionTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLArrayCollection"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "filterFunction"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A function that the view will use to eliminate items that do not match the function's criteria."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "list"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The IList that this collection view wraps."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "sort"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The Sort that will be applied to the ICollectionView."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "source"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The source of data in the ArrayCollection."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIArrayCollection 
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
