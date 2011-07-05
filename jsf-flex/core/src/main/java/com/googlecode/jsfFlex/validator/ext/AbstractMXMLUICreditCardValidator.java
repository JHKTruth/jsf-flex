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
 *   name     = "jf:mxmlCreditCardValidator"
 *   class    = "com.googlecode.jsfFlex.validator.ext.MXMLUICreditCardValidator"
 *   type     = "com.googlecode.jsfFlex.MXMLUICreditCardValidator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUICreditCardValidatorTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLCreditCardValidator"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "cardNumberListener"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that listens for the validation result for the card number subfield."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "cardNumberProperty"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the card number property to validate."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cardNumberSource"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Object that contains the value of the card number field."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "cardTypeListener"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The component that listens for the validation result for the card type subfield."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cardTypeProperty"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the card type property to validate."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "cardTypeSource"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Object that contains the value of the card type field."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "invalidNumberError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the credit card number is invalid."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "noNumError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the cardNumber field is empty."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "noTypeError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the cardType field is blank."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "wrongTypeError"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Error message the cardType field contains an invalid credit card type."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "allowedFormatChars"
 *  						 returnType = "java.lang.String"
 *   						 longDesc	= "The set of formatting characters allowed."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "invalidCharError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the value contains invalid characters."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "wrongLengthError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the field contains the wrong number of digits for the specified type."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 	
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUICreditCardValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes {
	
}
