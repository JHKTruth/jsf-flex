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
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlMenuBar"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIMenuBar"
 *   type     = "com.googlecode.jsfFlex.MXMLUIMenuBar"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIMenuBarTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLMenuBar"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "menubarItems"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "An Array of MenuBarItem objects that populate this MenuBar."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "menus"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "An Array containing the Menu objects for this MenuBar."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The background skin of the MenuBar control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemDownSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The skin when a MenuBar item is selected."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemOverSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The skin when focus is over a MenuBar item either."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemUpSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The skin when a MenuBar item is not selected."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "menuHide"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when a menu or submenu closes."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "menuShow"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when a menu or submenu opens, or the mouse pointer rolls over an item with no drop-down menu."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name = "showRoot"
 *   						 returnType = "java.lang.String"
 *   						 longDesc = "A Boolean flag that specifies whether to display the data provider's root node."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name = "dataDescriptor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc = "The object that accesses and manipulates data in the data provider."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "iconField"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The name of the field in the data provider object that determines what to display as the icon."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "labelField"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The name of the field in the data provider items to display as the label."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "labelFunction"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "User-supplied function to run on each item to determine its label."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name = "backgroundAlpha"
 *   						 returnType = "java.lang.String"
 *   						 longDesc = "Alpha level of the color defined by the backgroundColor property."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Background color of a component."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "color"
 *   						 returnType = "java.lang.String"
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
 * 							 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusAlpha"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusRoundedCorners"
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
 *   						 name		= "fontSharpness"
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
 *   						 name		= "fontThickness"
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
 * 							 name		= "fontWeight"
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
 * 							 name		= "highlightAlphas"
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
 * 							 name		= "rollOverColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The rollOverColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectionColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textIndent"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Offset of first line of text from the left side of the container, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textDecoration"
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
 * 							 name		= "itemClick"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user clicks on an item in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemRollOver"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemRollOut"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIMenuBar 
						extends MXMLUISimpleBase 
						implements _MXMLUIBaseAttributes, _MXMLUISelectedIndexAttribute,
						_MXMLUIDataProviderAttribute {
	
}
