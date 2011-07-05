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
 *   name     = "jf:mxmlNumberValidator"
 *   class    = "com.googlecode.jsfFlex.validator.ext.MXMLUINumberValidator"
 *   type     = "com.googlecode.jsfFlex.MXMLUINumberValidator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUINumberValidatorTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLNumberValidator"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "integerError"
 *  						 returnType = "java.lang.String"
 *   						 longDesc	= "Error message when the number must be an integer, as defined by the domain property."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "allowNegative"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether negative numbers are permitted."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "decimalPointCountError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the decimal separator character occurs more than once."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "decimalSeparator"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The character used to separate the whole from the fractional part of the number."
 *   						,
 *   						
 *							@JSFJspProperty
 *   						 name		= "exceedsMaxError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the value exceeds the maxValue property."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "invalidFormatCharsError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the value contains invalid format characters, which means that it contains a digit or minus sign (-) as a separator character, or it contains two or more consecutive separator characters."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "lowerThanMinError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the value is less than minValue."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "maxValue"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Maximum value for a valid number."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "minValue"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Minimum value for a valid number."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "negativeError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the value is negative and the allowNegative property is false."   						
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "precisionError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the value has a precision that exceeds the value defined by the precision property."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "separationError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the thousands separator is in the wrong location."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "thousandsSeparator"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The character used to separate thousands in the whole part of the number."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "domain"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Type to be validated."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "invalidCharError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the value contains invalid characters."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "precision"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The maximum number of digits allowed to follow the decimal point."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUINumberValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes {
	
}
