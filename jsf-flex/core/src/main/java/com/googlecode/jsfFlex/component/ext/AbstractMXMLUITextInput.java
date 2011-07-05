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
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundImageAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderSidesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICondenseWhiteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisplayAsPasswordAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEnterAttribute;
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
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIListDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaxCharsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRestrictAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionBeginIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionEndIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDirectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDistanceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextInputAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlTextInput",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUITextInput",
        type                =   "com.googlecode.jsfFlex.MXMLUITextInput",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUITextInputTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLTextInput",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUITextInput 
						extends com.googlecode.jsfFlex.component.MXMLUIHtmlTextInputBase
						implements _MXMLUIBaseAttributes, _MXMLUICondenseWhiteAttribute, _MXMLUIDataAttribute, 
                        _MXMLUIDisplayAsPasswordAttribute, _MXMLUIEditableAttribute, _MXMLUIHorizontalScrollPositionAttribute, 
                        _MXMLUIImeModeAttribute, _MXMLUIListDataAttribute, _MXMLUIMaxCharsAttribute, _MXMLUIRestrictAttribute, 
                        _MXMLUISelectionBeginIndexAttribute, _MXMLUISelectionEndIndexAttribute, _MXMLUIBackgroundAlphaAttribute, 
                        _MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundImageAttribute, _MXMLUIBackgroundSizeAttribute, 
                        _MXMLUIBorderColorAttribute, _MXMLUIBorderSidesAttribute, _MXMLUIBorderSkinAttribute, 
                        _MXMLUIBorderStyleAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, 
                        _MXMLUICornerRadiusAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIDropShadowColorAttribute, 
                        _MXMLUIDropShadowEnabledAttribute, _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, 
                        _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, 
                        _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, _MXMLUIFontStyleAttribute, 
                        _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, _MXMLUIPaddingLeftAttribute, 
                        _MXMLUIPaddingRightAttribute, _MXMLUIShadowDirectionAttribute, _MXMLUIShadowDistanceAttribute, 
                        _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
                        _MXMLUIChangeAttribute, _MXMLUIDataChangeAttribute, _MXMLUIEnterAttribute, 
                        _MXMLUITextInputAttribute {
	
}
