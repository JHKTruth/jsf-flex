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
 *   name     = "jf:mxmlEmailValidator"
 *   class    = "com.googlecode.jsfFlex.validator.ext.MXMLUIEmailValidator"
 *   type     = "com.googlecode.jsfFlex.MXMLUIEmailValidator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIEmailValidatorTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLEmailValidator"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "invalidIPDomainError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when the IP domain is invalid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "invalidPeriodsInDomainError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there are continuous periods in the domain."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "missingAtSignError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there is no at sign in the email address."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "missingPeriodInDomainError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there is no period in the domain."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "missingUsernameError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there is no username."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "tooManyAtSignsError"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Error message when there is more than one at sign in the e-mail address."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "invalidCharError"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Error message when the value contains invalid characters."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "domain"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Type to be validated."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 		
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIEmailValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes {
	
}
