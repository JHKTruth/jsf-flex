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

import com.googlecode.jsfFlex.component.MXMLUIValueBase;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlNumericStepper"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUINumericStepper"
 *   type     = "com.googlecode.jsfFlex.MXMLUINumericStepper"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUINumericStepperTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLNumericStepper"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 * 							@JSFJspProperty
 * 							 name		= "stepSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Non-zero unit of change between values."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderCapColor"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Specifies borderCapColor."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "imeMode"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the IME (input method editor) mode."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "maxChars"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Maximum number of characters that users can enter in the text field."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "minimum"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Minimum value of the NumericStepper."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "maximum"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Maximum value of the NumericStepper."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name = "backgroundAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc = "Alpha level of the color defined by the backgroundColor property."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Background color of a component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundImage"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Background image of a component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Scales the image specified by backgroundImage to different percentage sizes."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dropShadowColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderSides"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Bounding box sides. A space-delimited String that specifies the sides of the border to show."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Bounding box style."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The border skin of the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderThickness"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Bounding box thickness."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "color"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cornerRadius"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Radius of component corners."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "downArrowDisabledSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "downArrowDownSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the up arrow when the arrow is enabled and a user presses the mouse button over the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "downArrowUpSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is enabled and the mouse pointer is not on the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "upArrowOverSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is enabled and the mouse pointer is over the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "upArrowDownSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is enabled and a user presses the mouse button over the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "upArrowDisabledSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is disabled."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "downArrowOverSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is enabled and the mouse pointer is over the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "upArrowUpSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the Up arrow when the arrow is enabled and the mouse pointer is not on the arrow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dropShadowEnabled"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Boolean property that specifies whether the component has a visible drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "shadowDirection"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Direction of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "shadowDistance"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Distance of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusRoundedCorners"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontGridFitType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the gridFitType property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontSharpness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the sharpness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontAntiAliasType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the antiAliasType property of internal TextFields."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontThickness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the thickness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontFamily"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the font to use."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is italic font."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontWeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is boldface."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of the text, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "highlightAlphas"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Alphas used for the highlight fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "leading"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Additional vertical space between lines of text."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingLeft"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of pixels between the container's left border and the left edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingRight"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Number of pixels between the container's right border and the right edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textIndent"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Offset of first line of text from the left side of the container, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textDecoration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is underlined."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textAlign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alignment of text within a container."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "change"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dataChange"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUINumericStepper 
						extends MXMLUIValueBase 
						implements _MXMLUIBaseAttributes {
	
}
