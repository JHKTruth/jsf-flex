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

import com.googlecode.jsfFlex.attributes._MXMLUIAutoLoadAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIBrokenImageBorderSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBrokenImageSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICompleteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICompleteEffectAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHttpStatusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIInitAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIoErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILoadForCompatibilityAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILoaderContextAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaintainAspectRatioAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIProgressAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIScaleContentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISecurityErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowBusyCursorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISourceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITrustContentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUnloadAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalAlignAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUISwfLoaderAttributes 
                extends _MXMLUIAutoLoadAttribute, _MXMLUILoaderContextAttribute, _MXMLUILoadForCompatibilityAttribute, 
                _MXMLUIMaintainAspectRatioAttribute, _MXMLUIScaleContentAttribute, _MXMLUIShowBusyCursorAttribute, 
                _MXMLUISourceAttribute, _MXMLUITrustContentAttribute, _MXMLUIBrokenImageBorderSkinAttribute, 
                _MXMLUIBrokenImageSkinAttribute, _MXMLUIHorizontalAlignAttribute, _MXMLUIVerticalAlignAttribute, 
                _MXMLUICompleteEffectAttribute, _MXMLUICompleteAttribute, _MXMLUIHttpStatusAttribute, 
                _MXMLUIInitAttribute, _MXMLUIIoErrorAttribute, _MXMLUIOpenAttribute, _MXMLUIProgressAttribute, 
                _MXMLUISecurityErrorAttribute, _MXMLUIUnloadAttribute, _MXMLUIBaseAttributes {
    
}
