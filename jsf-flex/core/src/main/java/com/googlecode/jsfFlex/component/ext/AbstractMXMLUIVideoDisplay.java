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

import com.googlecode.jsfFlex.attributes._MXMLUIAutoBandWidthDetectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAutoPlayAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAutoRewindAttribute;
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
import com.googlecode.jsfFlex.attributes._MXMLUIBufferTimeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICompleteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICuePointAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICuePointManagerClassAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICuePointsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropShadowEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIdleTimeoutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILiveAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaintainAspectRatioAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPlayheadTimeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPlayheadUpdateAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPlayheadUpdateIntervalAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIProgressAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIProgressIntervalAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIReadyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRewindAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDirectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShadowDistanceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISourceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStateChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITotalTimeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVolumeAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlVideoDisplay",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIVideoDisplay",
        type                =   "com.googlecode.jsfFlex.MXMLUIVideoDisplay",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIVideoDisplayTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLVideoDisplay"
)
public abstract class AbstractMXMLUIVideoDisplay 
						extends MXMLUISimpleBase 
						implements _MXMLUIBaseAttributes, _MXMLUIAutoBandWidthDetectionAttribute, _MXMLUIAutoPlayAttribute, 
                        _MXMLUIAutoRewindAttribute, _MXMLUIBufferTimeAttribute, _MXMLUICuePointManagerClassAttribute, 
                        _MXMLUICuePointsAttribute, _MXMLUIIdleTimeoutAttribute, _MXMLUILiveAttribute, _MXMLUIMaintainAspectRatioAttribute, 
                        _MXMLUIPlayheadTimeAttribute, _MXMLUIPlayheadUpdateIntervalAttribute, _MXMLUIProgressIntervalAttribute, 
                        _MXMLUISourceAttribute, _MXMLUITotalTimeAttribute, _MXMLUIVolumeAttribute, _MXMLUIBackgroundAlphaAttribute, 
                        _MXMLUIBackgroundColorAttribute, _MXMLUIBackgroundImageAttribute, _MXMLUIBackgroundSizeAttribute, 
                        _MXMLUIBorderColorAttribute, _MXMLUIBorderSidesAttribute, _MXMLUIBorderSkinAttribute, 
                        _MXMLUIBorderStyleAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUICornerRadiusAttribute, 
                        _MXMLUIDropShadowColorAttribute, _MXMLUIDropShadowEnabledAttribute, _MXMLUIShadowDirectionAttribute, 
                        _MXMLUIShadowDistanceAttribute, _MXMLUICloseAttribute, _MXMLUICompleteAttribute, 
                        _MXMLUICuePointAttribute, _MXMLUIPlayheadUpdateAttribute, _MXMLUIProgressAttribute, 
                        _MXMLUIReadyAttribute, _MXMLUIRewindAttribute, _MXMLUIStateChangeAttribute {
	
}
