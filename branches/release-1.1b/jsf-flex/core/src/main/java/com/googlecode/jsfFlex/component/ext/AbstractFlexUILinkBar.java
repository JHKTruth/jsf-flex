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

import com.googlecode.jsfFlex.attributes.IFlexUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUILinkButtonStyleNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISelectedIndexAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISeparatorColorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISeparatorWidthAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexLinkBar",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUILinkBar",
        type                =   "com.googlecode.jsfFlex.FlexUILinkBar",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUILinkBarTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexLinkBar"
)
public abstract class AbstractFlexUILinkBar 
						extends AbstractFlexUISimpleBase 
						implements IFlexUINavBarAttributes, IFlexUILinkButtonStyleNameAttribute, IFlexUIRollOverColorAttribute, 
                        IFlexUISelectionColorAttribute, IFlexUISeparatorColorAttribute, IFlexUISeparatorSkinAttribute, 
                        IFlexUISeparatorWidthAttribute, IFlexUITextRollOverColorAttribute, IFlexUITextSelectedColorAttribute, 
                        IFlexUIDataProviderAttribute, IFlexUISelectedIndexAttribute {
    
}