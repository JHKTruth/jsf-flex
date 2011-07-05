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
 *   name     = "jf:mxmlDateValidator"
 *   class    = "com.googlecode.jsfFlex.validator.ext.MXMLUIDateValidator"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDateValidator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIDateValidatorTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLDateValidator"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "dayListener"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that listens for the validation result for the day subfield."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dayProperty"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the day property to validate."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "daySource"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Object that contains the value of the day field."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "formatError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the inputFormat property is not in the correct format."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "inputFormat"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The date format to validate the value against."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "monthListener"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that listens for the validation result for the monthsubfield."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "monthProperty"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the month property to validate."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "monthSource"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Object that contains the value of the month field."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "validateAsString"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines how to validate the value."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "wrongDayError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the day is invalid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "wrongMonthError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the month is invalid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "wrongYearError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the year is invalid."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "yearListener"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that listens for the validation result for the year subfield."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "yearProperty"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the year property to validate."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "yearSource"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Object that contains the value of the year field."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "allowedFormatChars"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The set of formatting characters allowed."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "invalidCharError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the value contains invalid characters."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "wrongLengthError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the field contains the wrong number of digits for the specified type."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIDateValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes {
	
}
