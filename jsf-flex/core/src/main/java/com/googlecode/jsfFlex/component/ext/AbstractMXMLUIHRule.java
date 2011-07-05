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
package com.googlecode.jsfFlex.component.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlHRule"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIHRule"
 *   type     = "com.googlecode.jsfFlex.MXMLUIHRule"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIHRuleTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLHRule"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "shadowColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The shadow color of the line."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "strokeWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The thickness of the rule in pixels."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "strokeColor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The color of the line."
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIHRule 
						extends MXMLUISimpleBase 
						implements _MXMLUIBaseAttributes {
	
}
