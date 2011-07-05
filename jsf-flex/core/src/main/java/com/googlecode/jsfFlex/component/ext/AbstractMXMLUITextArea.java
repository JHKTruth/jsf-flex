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

import com.googlecode.jsfFlex.component.MXMLUIHtmlTextInputBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIRepeatAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIScrollControlAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlTextArea"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUITextArea"
 *   type     = "com.googlecode.jsfFlex.MXMLUITextArea"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUITextAreaTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLTextArea"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 * 
 * @JSFJspProperties
 * 		properties	=		
 * 						   @JSFJspProperty
 *   						name		= "styleSheet"
 *  						returnType = "java.lang.String"
 *   						longDesc	= "A flash.text.StyleSheet object that can perform rendering on the TextArea control's text."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "data"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Lets you pass a value to the component when you use it in an item renderer or item editor."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "listData"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "When a component is used as a drop-in item renderer or drop-in item editor, Flex initializes the listData property of the component with the appropriate data from the list control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "condenseWhite"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether extra white space (spaces, line breaks, and so on) should be removed in a control with HTML text."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "displayAsPassword"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Indicates whether this control is used for entering passwords."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectionEndIndex"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The zero-based index of the position after the last character in the current selection (equivalent to the one-based index of the last character)."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectionBeginIndex"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The zero-based character index value of the first character in the current selection."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editable"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "A flag that indicates whether the control is editable."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "imeMode"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the IME (input method editor) mode."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "maxChars"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Maximum number of characters that users can enter in the text field."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "restrict"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Set of characters that a user can or cannot enter into the text field."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "wordWrap"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "A flag that indicates whether text in the row should be word wrapped."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusAlpha"
 *  						 returnType = "java.lang.String"
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
 *   						 name		= "change"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUITextArea 
						extends MXMLUIHtmlTextInputBase 
						implements _MXMLUIScrollControlAttributes, _MXMLUIBaseAttributes, _MXMLUIScrollAttributes, 
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, 
						_MXMLUIBorderAttributes, _MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, 
						_MXMLUIColorAttribute, _MXMLUITrackAttributes, _MXMLUICornerRadiusAttribute, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIRepeatAttributes,  
						_MXMLUIDisabledColorAttribute, _MXMLUILeadingAttribute, _MXMLUIThumbSkinAttributes, 
						_MXMLUIScrollAttribute, _MXMLUIScrollBarAttributes, _MXMLUIShadowAttributes,  
						_MXMLUITextStyleAttributes, _MXMLUIHorizontalScrollPositionAttribute {
	
}
