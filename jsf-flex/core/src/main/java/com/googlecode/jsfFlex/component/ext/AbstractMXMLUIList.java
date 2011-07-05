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

import com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIAllowMultipleSelectionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColumnCountAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDragAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDropDownEventColorAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIItemClickAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIItemEventAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIItemInfoAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIRepeatAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectableAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedItemAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectionAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextEventColorAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIWordWrapAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIListBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIScrollControlAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlList"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIList"
 *   type     = "com.googlecode.jsfFlex.MXMLUIList"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIListTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLList"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "editable"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "A flag that indicates whether the control is editable."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editedItemPosition"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The column and row index of the item renderer for the data provider item being edited, if any."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditorInstance"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "A reference to the currently active instance of the item editor, if it exists."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemFocusIn"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when an item renderer gets focus, which can occur if the user clicks on an item in the List control or navigates to the item using a keyboard."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditBegin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the editedItemPosition property is set and the item can be edited."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemEditEnd"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when an item editing session is ending for any reason."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemFocusOut"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Dispatched when an item renderer loses the focus, which can occur if the user clicks another item in the List control or outside the list, or uses the keyboard to navigate to another item in the List control or outside the List control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editorDataField"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the property of the item editor that contains the new data for the list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editorHeightOffset"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The height of the item editor, in pixels, relative to the size of the item renderer."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "editorUsesEnterKey"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether the item editor uses Enter key."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editorWidthOffset"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The width of the item editor, in pixels, relative to the size of the item renderer."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "editorXOffset"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The x location of the upper-left corner of the item editor, in pixels, relative to the upper-left corner of the item."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "editorYOffset"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The y location of the upper-left corner of the item editor, in pixels, relative to the upper-left corner of the item."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemEditor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The class factory for the item editor to use for the control, if the editable property is set to true."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "rendererIsEditor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether the item renderer is also an item editor."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditBeginning"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user releases the mouse button while over an item, tabs to the List or within the List, or in any other way attempts to edit an item."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "imeMode"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the IME (input method editor) mode."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundDisabledColor"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Background color of the component when it is disabled."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIList 
						extends MXMLUISelectedIndexBase
						implements _MXMLUIListBaseAttributes, _MXMLUIScrollControlAttributes, 
						_MXMLUIAllowMultipleSelectionAttribute, _MXMLUIBaseAttributes, _MXMLUITextEventColorAttributes, 
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, 
						_MXMLUIBorderAttributes, _MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, 
						_MXMLUIChangeAttribute, _MXMLUIColorAttribute, _MXMLUIWordWrapAttribute, _MXMLUIShadowAttributes,
						_MXMLUICornerRadiusAttribute, _MXMLUIDataChangeAttribute, _MXMLUIDataProviderAttribute, 
						_MXMLUIDisabledColorAttribute, _MXMLUIDragAttributes, _MXMLUIDropDownEventColorAttributes, 
						_MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, _MXMLUITrackAttributes, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIHorizontalScrollPositionAttribute,  
						_MXMLUIIconFieldAttribute, _MXMLUIThumbSkinAttributes, _MXMLUITextStyleAttributes, 
						_MXMLUIItemClickAttribute, _MXMLUIItemEventAttributes, _MXMLUIVerticalAlignAttribute,
						_MXMLUIItemInfoAttributes, _MXMLUILabelFieldAttribute, _MXMLUILabelFunctionAttribute, 
						_MXMLUILeadingAttribute, _MXMLUIPaddingHorizontalAttributes, _MXMLUIPaddingVerticalAttributes,  
						_MXMLUIRepeatAttributes, _MXMLUIScrollAttribute, _MXMLUIScrollAttributes, _MXMLUIScrollBarAttributes,  
						_MXMLUISelectableAttribute, _MXMLUIColumnCountAttribute, _MXMLUISelectedItemAttribute, 
						_MXMLUISelectionAttributes {
	
}
