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

import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILinkButtonStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISeparatorColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISeparatorWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.component.MXMLUICommandBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlLinkBar",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUILinkBar",
        type                =   "com.googlecode.jsfFlex.MXMLUILinkBar",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUILinkBarTag",
        family              =   "javax.faces.MXMLCommandBase",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLLinkBar"
)
public abstract class AbstractMXMLUILinkBar 
						extends MXMLUICommandBase 
						implements _MXMLUINavBarAttributes, _MXMLUILinkButtonStyleNameAttribute, _MXMLUIRollOverColorAttribute, 
                        _MXMLUISelectionColorAttribute, _MXMLUISeparatorColorAttribute, _MXMLUISeparatorSkinAttribute, 
                        _MXMLUISeparatorWidthAttribute, _MXMLUITextRollOverColorAttribute, _MXMLUITextSelectedColorAttribute, 
                        _MXMLUIDataProviderAttribute, _MXMLUISelectedIndexAttribute {
	
}
