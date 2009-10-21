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

import com.googlecode.jsfFlex.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBarSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICompleteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICompleteEffectAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIConversionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHideAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIndeterminateAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIndeterminateMoveIntervalAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIndeterminateSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelPlacementAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaskSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaximumAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMinimumAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIProgressAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISourceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIThemeColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITrackColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITrackHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITrackSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;
import com.googlecode.jsfFlex.component.MXMLUIValueBase;

/**
 * Note though MXMLUIProgressBar extends MXMLUIValueBase, it will simply retrieve the value during the post-back phase<br>
 * and NOT set the field of the Flex component as this field is read only.<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlProgressBar",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIProgressBar",
        type                =   "com.googlecode.jsfFlex.MXMLUIProgressBar",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIProgressBarTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLProgressBar",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIProgressBar 
						extends MXMLUIValueBase 
						implements _MXMLUIBaseAttributes, _MXMLUIConversionAttribute, _MXMLUIIndeterminateAttribute, 
                        _MXMLUILabelAttribute, _MXMLUILabelPlacementAttribute, _MXMLUIMaximumAttribute, _MXMLUIMinimumAttribute, 
                        _MXMLUIModeAttribute, _MXMLUISourceAttribute, _MXMLUIBarColorAttribute, _MXMLUIBarSkinAttribute, 
                        _MXMLUIBorderColorAttribute, _MXMLUIColorAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIFontAntiAliasTypeAttribute, 
                        _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, 
                        _MXMLUIFontSizeAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontStyleAttribute, _MXMLUIFontWeightAttribute, 
                        _MXMLUIHorizontalGapAttribute, _MXMLUIIndeterminateMoveIntervalAttribute, _MXMLUIIndeterminateSkinAttribute, 
                        _MXMLUILabelWidthAttribute, _MXMLUILeadingAttribute, _MXMLUIMaskSkinAttribute, _MXMLUIPaddingLeftAttribute, 
                        _MXMLUIPaddingRightAttribute, _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, 
                        _MXMLUITextIndentAttribute, _MXMLUIThemeColorAttribute, _MXMLUITrackColorsAttribute, _MXMLUITrackHeightAttribute, 
                        _MXMLUITrackSkinAttribute, _MXMLUIVerticalGapAttribute, _MXMLUICompleteAttribute, _MXMLUIHideAttribute, 
                        _MXMLUIProgressAttribute, _MXMLUIShowAttribute, _MXMLUICompleteEffectAttribute {
	
	protected void populateComponentInitValues(){
		
	}
	
}
