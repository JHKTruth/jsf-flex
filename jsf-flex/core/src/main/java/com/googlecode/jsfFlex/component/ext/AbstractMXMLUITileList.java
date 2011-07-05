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
 *   name     = "jf:mxmlTileList"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUITileList"
 *   type     = "com.googlecode.jsfFlex.MXMLUITileList"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUITileListTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLTileList"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUITileList 
						extends MXMLUISelectedIndexBase
						implements _MXMLUIListBaseAttributes, _MXMLUIScrollControlAttributes, _MXMLUIBaseAttributes, 
						_MXMLUITrackAttributes, _MXMLUIAllowMultipleSelectionAttribute, _MXMLUIBackgroundAlphaAttribute, 
						_MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, _MXMLUIBorderAttributes, 
						_MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIChangeAttribute, 
						_MXMLUIColorAttribute, _MXMLUIWordWrapAttribute, _MXMLUICornerRadiusAttribute, 
						_MXMLUIDataChangeAttribute, _MXMLUIDataProviderAttribute, _MXMLUIDisabledColorAttribute, 
						_MXMLUIDragAttributes, _MXMLUIDropDownEventColorAttributes, _MXMLUIFocusAlphaAttribute, 
						_MXMLUIFocusRoundedCornersAttribute, _MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes,  
						_MXMLUIHorizontalScrollPositionAttribute, _MXMLUIIconFieldAttribute, _MXMLUIVerticalAlignAttribute,  
						_MXMLUIItemClickAttribute, _MXMLUIItemEventAttributes, _MXMLUIItemInfoAttributes, 
						_MXMLUILabelFieldAttribute, _MXMLUILabelFunctionAttribute, _MXMLUILeadingAttribute, 
						_MXMLUIPaddingHorizontalAttributes, _MXMLUIPaddingVerticalAttributes, _MXMLUIRepeatAttributes,  
						_MXMLUIScrollAttribute, _MXMLUIScrollAttributes, _MXMLUIScrollBarAttributes, _MXMLUISelectableAttribute,  
						_MXMLUIColumnCountAttribute, _MXMLUISelectedItemAttribute, _MXMLUISelectionAttributes, 
						_MXMLUIShadowAttributes, _MXMLUITextEventColorAttributes, _MXMLUITextStyleAttributes, 
						_MXMLUIThumbSkinAttributes {
	
}
