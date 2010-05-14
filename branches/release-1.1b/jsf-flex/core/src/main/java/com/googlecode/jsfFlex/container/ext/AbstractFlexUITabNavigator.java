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

import com.googlecode.jsfFlex.attributes.IFlexUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFirstTabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUILastTabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISelectedTabTextStyleNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITabHeightAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITabOffsetAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITabStyleNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITabWidthAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUIViewStackBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexTabNavigator",
        clazz               =   "com.googlecode.jsfFlex.container.ext.FlexUITabNavigator",
        type                =   "com.googlecode.jsfFlex.FlexUITabNavigator",
        tagClass            =   "com.googlecode.jsfFlex.taglib.container.ext.FlexUITabNavigatorTag",
        family              =   "javax.faces.FlexInput",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexTabNavigator",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.FlexUIInputTagBase"
)
public abstract class AbstractFlexUITabNavigator 
						extends AbstractFlexUIViewStackBase 
						implements IFlexUIViewStackAttributes, IFlexUIFillAlphasAttribute, IFlexUIFillColorsAttribute, 
                        IFlexUIFirstTabStyleNameAttribute, IFlexUIFocusAlphaAttribute, IFlexUIFocusRoundedCornersAttribute, 
                        IFlexUIHorizontalAlignAttribute, IFlexUILastTabStyleNameAttribute, IFlexUISelectedTabTextStyleNameAttribute, 
                        IFlexUITabHeightAttribute, IFlexUITabOffsetAttribute, IFlexUITabStyleNameAttribute, IFlexUITabWidthAttribute {
	
}