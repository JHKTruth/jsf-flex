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

import com.googlecode.jsfFlex.attributes.IFlexUIAutoLoadAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBufferTimeAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICompleteAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIId3Attribute;
import com.googlecode.jsfFlex.attributes.IFlexUIIoErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUILoopsAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIPanEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIPanFromAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIProgressAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUISourceAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIStartTimeAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIUseDurationAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIVolumeEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIVolumeFromAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIVolumeToAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexSoundEffect",
        clazz               =   "com.googlecode.jsfFlex.effects.ext.FlexUISoundEffect",
        type                =   "com.googlecode.jsfFlex.FlexUISoundEffect",
        tagClass            =   "com.googlecode.jsfFlex.taglib.effects.ext.FlexUISoundEffectTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexSoundEffect"
)
public abstract class AbstractFlexUISoundEffect 
                                extends AbstractFlexUISimpleBase 
                                implements IFlexUIEffectAttributes, IFlexUIAutoLoadAttribute, IFlexUIBufferTimeAttribute, 
                                IFlexUILoopsAttribute, IFlexUIPanEasingFunctionAttribute, IFlexUIPanFromAttribute, IFlexUISourceAttribute, 
                                IFlexUIStartTimeAttribute, IFlexUIUseDurationAttribute, IFlexUIVolumeEasingFunctionAttribute, 
                                IFlexUIVolumeFromAttribute, IFlexUIVolumeToAttribute, IFlexUICompleteAttribute, IFlexUIId3Attribute, 
                                IFlexUIIoErrorAttribute, IFlexUIProgressAttribute {

    /**
     * Id of the component.
     */
    @JSFProperty(
            inheritTag  =   true,
            rtexprvalue =   true,
            literalOnly =   true,
            desc        =   "Id of the component."
    )
    public String getId(){
        return super.getId();
    }
    
}