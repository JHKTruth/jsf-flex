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

import com.googlecode.jsfFlex.component.MXMLUIViewStackBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIGapAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHistoryManagementAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIResizeToContentAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIContainerAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlTabNavigator"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUITabNavigator"
 *   type     = "com.googlecode.jsfFlex.MXMLUITabNavigator"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUITabNavigatorTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLTabNavigator"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "firstTabStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of CSS style declaration that specifies styles for the first tab."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "lastTabStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of CSS style declaration that specifies styles for the last tab."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedTabTextStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of CSS style declaration that specifies styles for the text of the selected tab."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "tabHeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of each tab, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "tabStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of CSS style declaration that specifies styles for the tabs."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "tabWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Width of each tab, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusAlpha"
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
 * 							 name		= "horizontalAlign"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Horizontal alignment of children in the container."
 *   
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUITabNavigator 
						extends MXMLUIViewStackBase 
						implements _MXMLUIBaseAttributes, _MXMLUIResizeToContentAttribute, _MXMLUIContainerAttributes, 
						_MXMLUITextStyleAttributes, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, 
						_MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundDisabledColorAttribute, 
						_MXMLUIBarColorAttribute, _MXMLUIBorderAttributes, _MXMLUIBorderColorAttribute, 
						_MXMLUIBorderThicknessAttribute, _MXMLUIChangeAttribute, _MXMLUIColorAttribute, 
						_MXMLUITrackAttributes, _MXMLUICornerRadiusAttribute, _MXMLUIDataChangeAttribute, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIDisabledColorAttribute,  
						_MXMLUIFontSpecificAttributes, _MXMLUIGapAttributes, _MXMLUIThumbSkinAttributes,
						_MXMLUIHorizontalScrollPositionAttribute, _MXMLUIHistoryManagementAttribute, _MXMLUILabelAttribute, 
						_MXMLUIPaddingHorizontalAttributes, _MXMLUIPaddingVerticalAttributes, _MXMLUIScrollAttribute, 
						_MXMLUIScrollAttributes, _MXMLUIScrollBarAttributes, _MXMLUIIconAttribute, 
						_MXMLUIShadowAttributes {
	
}
