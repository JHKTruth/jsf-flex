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

import com.googlecode.jsfFlex.attributes._MXMLUIArrowButtonWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenAlwaysAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPopUpAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPopUpDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPopUpGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPopUpIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPopUpOverSkinAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIPopUpButtonAttributes 
                extends _MXMLUIOpenAlwaysAttribute, _MXMLUIPopUpAttribute, _MXMLUIArrowButtonWidthAttribute, 
                _MXMLUICloseDurationAttribute, _MXMLUICloseEasingFunctionAttribute, _MXMLUIDisabledIconColorAttribute, 
                _MXMLUIIconColorAttribute, _MXMLUIOpenDurationAttribute, _MXMLUIOpenEasingFunctionAttribute, _MXMLUIPopUpDownSkinAttribute, 
                _MXMLUIPopUpGapAttribute, _MXMLUIPopUpIconAttribute, _MXMLUIPopUpOverSkinAttribute, _MXMLUICloseAttribute, 
                _MXMLUIOpenAttribute, _MXMLUIButtonAttributes {
    
}
