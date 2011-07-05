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
package com.googlecode.jsfFlex.effects.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUICustomFilterAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEffectEndAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEffectStartAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFilterAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHideFocusRingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPerElementOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRepeatCountAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRepeatDelayAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStartDelayAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISuspendBackgroundProcessingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITargetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITargetsAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIEffectAttributes extends 
                    _MXMLUICustomFilterAttribute, _MXMLUIDurationAttribute, _MXMLUIFilterAttribute,_MXMLUIHideFocusRingAttribute, 
                    _MXMLUIPerElementOffsetAttribute, _MXMLUIRepeatCountAttribute, _MXMLUIRepeatDelayAttribute, _MXMLUIStartDelayAttribute, 
                    _MXMLUISuspendBackgroundProcessingAttribute, _MXMLUITargetAttribute, _MXMLUITargetsAttribute, _MXMLUIEffectEndAttribute, 
                    _MXMLUIEffectStartAttribute {
    
}
