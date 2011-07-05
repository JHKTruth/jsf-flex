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

import com.googlecode.jsfFlex.attributes._MXMLUIAutoRepeatAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIButtonDownAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDownIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEmphasizedAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontContextAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHighlightAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIKerningAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelPlacementAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILetterSpacingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOverIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRepeatDelayAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRepeatIntervalAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedDisabledIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedDownIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedOverIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedUpIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStickyHighlightingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIToggleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUpIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIButtonAttributes 
                    extends _MXMLUIAutoRepeatAttribute, _MXMLUIEmphasizedAttribute, _MXMLUILabelAttribute, 
                    _MXMLUILabelPlacementAttribute, _MXMLUISelectedFieldAttribute, _MXMLUIStickyHighlightingAttribute,
                    _MXMLUIToggleAttribute, _MXMLUIBorderColorAttribute, _MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute,
                    _MXMLUIDisabledColorAttribute, _MXMLUIDisabledIconAttribute, _MXMLUIDisabledSkinAttribute, 
                    _MXMLUIDownIconAttribute, _MXMLUIDownSkinAttribute, _MXMLUIFillAlphasAttribute, _MXMLUIFillColorsAttribute,
                    _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, _MXMLUIFontAntiAliasTypeAttribute, 
                    _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, 
                    _MXMLUIFontSizeAttribute, _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, 
                    _MXMLUIHighlightAlphasAttribute, _MXMLUIHorizontalGapAttribute, _MXMLUIIconAttribute, _MXMLUILeadingAttribute, 
                    _MXMLUIOverIconAttribute, _MXMLUIOverSkinAttribute, _MXMLUIPaddingBottomAttribute, _MXMLUIPaddingLeftAttribute, 
                    _MXMLUIPaddingRightAttribute, _MXMLUIPaddingTopAttribute, _MXMLUIRepeatDelayAttribute, _MXMLUIRepeatIntervalAttribute, 
                    _MXMLUISelectedDisabledIconAttribute, _MXMLUISelectedDisabledSkinAttribute, _MXMLUISelectedDownIconAttribute, 
                    _MXMLUISelectedDownSkinAttribute, _MXMLUISelectedOverIconAttribute, _MXMLUISelectedOverSkinAttribute, 
                    _MXMLUISelectedUpIconAttribute, _MXMLUISelectedUpSkinAttribute, _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, 
                    _MXMLUITextIndentAttribute, _MXMLUITextRollOverColorAttribute, _MXMLUITextSelectedColorAttribute, 
                    _MXMLUIUpIconAttribute, _MXMLUIUpSkinAttribute, _MXMLUIVerticalGapAttribute, _MXMLUIButtonDownAttribute, 
                    _MXMLUIChangeAttribute, _MXMLUIDataChangeAttribute, _MXMLUIFontContextAttribute, _MXMLUIKerningAttribute, 
                    _MXMLUILetterSpacingAttribute, _MXMLUISkinAttribute, _MXMLUIBaseAttributes {
    
}
