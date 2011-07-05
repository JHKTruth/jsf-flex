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
 *   name     = "jf:mxmlCurrencyFormatter"
 *   class    = "com.googlecode.jsfFlex.convert.ext.MXMLUICurrencyFormatter"
 *   type     = "com.googlecode.jsfFlex.MXMLUICurrencyFormatter"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUICurrencyFormatterTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLCurrencyFormatter"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "alignSymbol"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Aligns currency symbol to the left side or the right side of the formatted number."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "currencySymbol"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Character to use as a currency symbol for a formatted number."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "decimalSeparatorFrom"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Decimal separator character to use when parsing an input string."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "decimalSeparatorTo"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Decimal separator character to use when outputting formatted decimal numbers."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "precision"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of decimal places to include in the output String."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "rounding"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "How to round the number."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "thousandsSeparatorFrom"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Character to use as the thousands separator in the input String."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "thousandsSeparatorTo"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Character to use as the thousands separator in the output string."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "useNegativeSign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, format a negative number by preceding it with a minus "-" sign."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "useThousandsSeparator"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, split the number into thousands increments by using a separator character."
 * 
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUICurrencyFormatter 
						extends MXMLUISimpleBase 
						implements _MXMLUIFormatter {
	
}
