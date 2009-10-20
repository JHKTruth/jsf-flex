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

import com.googlecode.jsfFlex.attributes._MXMLUIBorderAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseButtonDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseButtonDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseButtonOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseButtonUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIControlBarStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFooterColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHighlightAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILayoutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyBlurAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResizeEndEffectAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResizeStartEffectAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRoundedBottomCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStatusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStatusStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITitleBackgroundSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITitleIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITitleStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIPanelAttributes 
                extends _MXMLUILayoutAttribute, _MXMLUIStatusAttribute, _MXMLUITitleIconAttribute, _MXMLUIBorderAlphaAttribute, 
                _MXMLUIBorderThicknessBottomAttribute, _MXMLUIBorderThicknessLeftAttribute, _MXMLUIBorderThicknessRightAttribute, 
                _MXMLUIBorderThicknessTopAttribute, _MXMLUICloseButtonDisabledSkinAttribute, _MXMLUICloseButtonDownSkinAttribute, 
                _MXMLUICloseButtonOverSkinAttribute, _MXMLUICloseButtonUpSkinAttribute, _MXMLUIControlBarStyleNameAttribute, 
                _MXMLUIFooterColorsAttribute, _MXMLUIHeaderColorsAttribute, _MXMLUIHeaderHeightAttribute, _MXMLUIHighlightAlphasAttribute, 
                _MXMLUIHorizontalAlignAttribute, _MXMLUIHorizontalGapAttribute, _MXMLUIModalTransparencyAttribute, 
                _MXMLUIModalTransparencyBlurAttribute, _MXMLUIModalTransparencyColorAttribute, _MXMLUIModalTransparencyDurationAttribute, 
                _MXMLUIRoundedBottomCornersAttribute, _MXMLUIStatusStyleNameAttribute, _MXMLUITitleBackgroundSkinAttribute, 
                _MXMLUITitleStyleNameAttribute, _MXMLUIVerticalAlignAttribute, _MXMLUIVerticalGapAttribute, _MXMLUIResizeEndEffectAttribute, 
                _MXMLUIResizeStartEffectAttribute, _MXMLUICloseAttribute, _MXMLUIContainerAttributes {
    
    String getTitle();
    
}
