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

import com.googlecode.jsfFlex.component.MXMLUIOutputBase;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlLabel"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUILabel"
 *   type     = "com.googlecode.jsfFlex.MXMLUILabel"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUILabelTag"
 *   family   = "javax.faces.MXMLOutput"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLLabel"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "data"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Lets you pass a value to the component when you use it in an item renderer or item editor."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "listData"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "When a component is used as a drop-in item renderer or drop-in item editor, Flex initializes the listData property of the component with the appropriate data from the list control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "condenseWhite"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether extra white space (spaces, line breaks, and so on) should be removed in a control with HTML text."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "preloader"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Specifies the path of a SWC component class or ActionScript component class that defines a custom progress bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "htmlText"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the text displayed by the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectable"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies whether the text can be selected."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "text"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Plain text that appears in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "truncateToFit"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "If this propery is true, and the Label control size is smaller than its text, the text of the Label control is truncated using a localizable string, such as ...."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "color"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontGridFitType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the gridFitType property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontSharpness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the sharpness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontAntiAliasType"
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
 *   						 name		= "fontFamily"
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
 *   						 name		= "textAlign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alignment of text within a container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dataChange"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 *   
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUILabel 
						extends MXMLUIOutputBase 
						implements _MXMLUIBaseAttributes {
	
}
