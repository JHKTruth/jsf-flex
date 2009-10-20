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
package com.googlecode.jsfFlex.container.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIAutoLayoutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundAttachmentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundImageAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderSidesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChildAddAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChildIndexChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChildRemoveAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIClipContentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICreationIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICreationPolicyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDefaultButtonAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledOverlayAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalLineScrollSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalPageScrollSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollBarAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollBarStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollPolicyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDirectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDistanceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalLineScrollSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalPageScrollSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalScrollBarAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalScrollBarStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalScrollPolicyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalScrollPositionAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIContainerAttributes 
                    extends _MXMLUIAutoLayoutAttribute, _MXMLUIClipContentAttribute, _MXMLUICreationIndexAttribute, 
                    _MXMLUICreationPolicyAttribute, _MXMLUIDefaultButtonAttribute, _MXMLUIHorizontalLineScrollSizeAttribute, 
                    _MXMLUIHorizontalPageScrollSizeAttribute, _MXMLUIHorizontalScrollBarAttribute, _MXMLUIHorizontalScrollPolicyAttribute, 
                    _MXMLUIHorizontalScrollPositionAttribute, _MXMLUIIconAttribute, _MXMLUILabelAttribute, _MXMLUIVerticalLineScrollSizeAttribute, 
                    _MXMLUIVerticalPageScrollSizeAttribute, _MXMLUIVerticalScrollBarAttribute, _MXMLUIVerticalScrollPolicyAttribute, 
                    _MXMLUIVerticalScrollPositionAttribute, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttachmentAttribute, 
                    _MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundDisabledColorAttribute, _MXMLUIBackgroundImageAttribute, 
                    _MXMLUIBackgroundSizeAttribute, _MXMLUIBarColorAttribute, _MXMLUIBorderColorAttribute, _MXMLUIBorderSidesAttribute,
                    _MXMLUIBorderSkinAttribute, _MXMLUIBorderStyleAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, 
                    _MXMLUICornerRadiusAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIDisabledOverlayAlphaAttribute, 
                    _MXMLUIDropShadowColorAttribute, _MXMLUIDropShadowEnabledAttribute, _MXMLUIFontAntiAliasTypeAttribute, 
                    _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, 
                    _MXMLUIFontSizeAttribute, _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, 
                    _MXMLUIHorizontalScrollBarStyleNameAttribute, _MXMLUIPaddingBottomAttribute, _MXMLUIPaddingLeftAttribute, 
                    _MXMLUIPaddingRightAttribute, _MXMLUIPaddingTopAttribute, _MXMLUIShadowDirectionAttribute, _MXMLUIShadowDistanceAttribute,
                    _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
                    _MXMLUIVerticalScrollBarStyleNameAttribute, _MXMLUIChildAddAttribute, _MXMLUIChildIndexChangeAttribute, 
                    _MXMLUIChildRemoveAttribute, _MXMLUIDataChangeAttribute, _MXMLUIScrollAttribute, _MXMLUIBaseAttributes {
	
}
