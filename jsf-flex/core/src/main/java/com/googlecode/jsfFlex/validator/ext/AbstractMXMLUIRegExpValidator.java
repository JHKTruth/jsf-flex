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
package com.googlecode.jsfFlex.validator.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.validator.attributes.compBase._MXMLUIValidatorAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlRegExpValidator"
 *   class    = "com.googlecode.jsfFlex.validator.ext.MXMLUIRegExpValidator"
 *   type     = "com.googlecode.jsfFlex.MXMLUIRegExpValidator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIRegExpValidatorTag"
 *   family   = "javax.faces.MXMLSimpleBase"
 *   tagSuperclass = "org.apache.myfaces.shared_impl.taglib.UIComponentTagBase"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLSimpleBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "expression"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The regular expression to use for validation."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "flags"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The regular expression flags to use when matching."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "noExpressionError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there is no regular expression specifed."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "noMatchError"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Error message when there are no matches to the regular expression."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIRegExpValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes {
	
	private static final String MXML_COMPONENT_RENDERER = "com.googlecode.jsfFlex.MXMLRegExpValidator";
	
	public String getMXMLComponentRenderer() {
		return MXML_COMPONENT_RENDERER;
	}
	
}
