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
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDirectionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIGapAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIContainerAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlDividedBox"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIDividedBox"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDividedBox"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIDividedBoxTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLDividedBox"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "liveDragging"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "If true, the children adjacent to a divider are continuously resized while the user drags it."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "resizeToContent"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "If true, the ViewStack container automatically resizes to the size of its current child."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dividerAffordance"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness in pixels of the area where the user can click to drag a divider."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dividerAlpha"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The alpha value that determines the transparency of the dividers."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dividerColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the dividers when the user presses or drags the dividers if the liveDragging property is set to false."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dividerSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The divider skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dividerThickness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness in pixels of the dividers when the user presses or drags the dividers, if the liveDragging property is set to false."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "horizontalDividerCursor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The cursor skin for a horizontal DividedBox."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "verticalDividerCursor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The cursor skin for a vertical DividedBox."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dividerPress"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user presses any divider in this container."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dividerDrag"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched multiple times as the user drags any divider."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dividerRelease"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user releases a divider."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIDividedBox 
						extends MXMLUISimpleBase
						implements _MXMLUIVerticalAlignAttribute, _MXMLUIIconAttribute, _MXMLUIContainerAttributes, 
						_MXMLUIBaseAttributes, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, 
						_MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundDisabledColorAttribute, 
						_MXMLUIBarColorAttribute, _MXMLUIBorderAttributes, _MXMLUIBorderColorAttribute, 
						_MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, 
						_MXMLUIDataChangeAttribute, _MXMLUIDirectionAttribute, _MXMLUIDisabledColorAttribute, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIFontSpecificAttributes, 
						_MXMLUIGapAttributes, _MXMLUIHorizontalAlignAttribute, _MXMLUIHorizontalScrollPositionAttribute,  
						_MXMLUILabelAttribute, _MXMLUIPaddingHorizontalAttributes, _MXMLUIPaddingVerticalAttributes, 
						_MXMLUIScrollAttribute, _MXMLUIScrollAttributes, _MXMLUIScrollBarAttributes, 
						_MXMLUIShadowAttributes, _MXMLUITextStyleAttributes, _MXMLUIThumbSkinAttributes, 
						_MXMLUITrackAttributes {
	
}
