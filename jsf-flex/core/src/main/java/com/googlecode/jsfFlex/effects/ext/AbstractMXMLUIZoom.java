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
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.attributes._MXMLUICaptureRollEventsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOriginXAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOriginYAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIZoomHeightFromAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIZoomHeightToAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIZoomWidthFromAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIZoomWidthToAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlZoom",
        clazz               =   "com.googlecode.jsfFlex.effects.ext.MXMLUIZoom",
        type                =   "com.googlecode.jsfFlex.MXMLUIZoom",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIZoomTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLZoom"
)
public abstract class AbstractMXMLUIZoom 
                                extends MXMLUISimpleBase 
                                implements _MXMLUITweenEffectAttributes, _MXMLUICaptureRollEventsAttribute, _MXMLUIOriginXAttribute, 
                                _MXMLUIOriginYAttribute, _MXMLUIZoomWidthFromAttribute, _MXMLUIZoomWidthToAttribute, 
                                _MXMLUIZoomHeightFromAttribute, _MXMLUIZoomHeightToAttribute {

    /**
     * Id of the component.
     */
    @JSFProperty(
            inheritTag  =   true,
            desc        =   "Id of the component."
    )
    public String getId(){
        return super.getId();
    }
    
}
