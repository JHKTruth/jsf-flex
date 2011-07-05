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

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataDescriptorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHighlightAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemClickAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOverAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenuBarItemRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenuHideAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenuShowAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenuStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenubarItemsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowRootAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlMenuBar",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIMenuBar",
        type                =   "com.googlecode.jsfFlex.MXMLUIMenuBar",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIMenuBarTag",
        family              =   "javax.faces.MXMLSimpleBase",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLMenuBar"
)
public abstract class AbstractMXMLUIMenuBar 
						extends MXMLUISimpleBase 
						implements _MXMLUIBaseAttributes, _MXMLUIDataDescriptorAttribute, _MXMLUIDataProviderAttribute, 
                        _MXMLUIIconFieldAttribute, _MXMLUILabelFieldAttribute, _MXMLUILabelFunctionAttribute, _MXMLUIMenuBarItemRendererAttribute, 
                        _MXMLUIMenubarItemsAttribute, _MXMLUIMenusAttribute, _MXMLUISelectedIndexAttribute, 
                        _MXMLUIShowRootAttribute, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundColorAttribute, 
                        _MXMLUIBackgroundSkinAttribute, _MXMLUIBorderColorAttribute, _MXMLUIColorAttribute, 
                        _MXMLUICornerRadiusAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIFillAlphasAttribute, 
                        _MXMLUIFillColorsAttribute, _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, 
                        _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, 
                        _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, _MXMLUIFontStyleAttribute, 
                        _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, _MXMLUIHighlightAlphasAttribute, 
                        _MXMLUIItemDownSkinAttribute, _MXMLUIItemOverSkinAttribute, _MXMLUIItemUpSkinAttribute, 
                        _MXMLUILeadingAttribute, _MXMLUIMenuStyleNameAttribute, _MXMLUIRollOverColorAttribute, _MXMLUISelectionColorAttribute, 
                        _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
                        _MXMLUIItemClickAttribute, _MXMLUIItemRollOutAttribute, _MXMLUIItemRollOverAttribute, 
                        _MXMLUIMenuHideAttribute, _MXMLUIMenuShowAttribute {
	
}
