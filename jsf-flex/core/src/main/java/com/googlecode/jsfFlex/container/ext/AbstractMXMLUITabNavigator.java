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

import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFirstTabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILastTabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedTabTextStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITabHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITabOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITabWidthAttribute;
import com.googlecode.jsfFlex.component.MXMLUIViewStackBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlTabNavigator",
        clazz               =   "com.googlecode.jsfFlex.container.ext.MXMLUITabNavigator",
        type                =   "com.googlecode.jsfFlex.MXMLUITabNavigator",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUITabNavigatorTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLTabNavigator",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUITabNavigator 
						extends MXMLUIViewStackBase 
						implements _MXMLUIViewStackAttributes, _MXMLUIFillAlphasAttribute, _MXMLUIFillColorsAttribute, 
                        _MXMLUIFirstTabStyleNameAttribute, _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, 
                        _MXMLUIHorizontalAlignAttribute, _MXMLUILastTabStyleNameAttribute, _MXMLUISelectedTabTextStyleNameAttribute, 
                        _MXMLUITabHeightAttribute, _MXMLUITabOffsetAttribute, _MXMLUITabStyleNameAttribute, _MXMLUITabWidthAttribute {
	
}
