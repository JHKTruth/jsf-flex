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
package com.googlecode.jsfFlex.convert.ext;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.convert.attributes.compBase._MXMLUIFormatter;

/**
 * @JSFComponent
 *   name     = "jf:mxmlPhoneFormatter"
 *   class    = "com.googlecode.jsfFlex.convert.ext.MXMLUIPhoneFormatter"
 *   type     = "com.googlecode.jsfFlex.MXMLUIPhoneFormatter"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIPhoneFormatterTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLPhoneFormatter"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "areaCode"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Area code number added to a seven-digit United States format phone number to form a 10-digit phone number."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "areaCodeFormat"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Default format for the area code when the areacode  property is rendered by a seven-digit format."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "validPatternChars"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "List of valid characters that can be used in the formatString property."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "formatString"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The mask pattern."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br> 
 * 				
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIPhoneFormatter 
						extends MXMLUISimpleBase 
						implements _MXMLUIFormatter {
	
}
