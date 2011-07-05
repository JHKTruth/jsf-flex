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

import com.googlecode.jsfFlex.attributes._MXMLUIAlphaFromAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAlphaToAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBlurXFromAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBlurXToAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBlurYFromAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBlurYToAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIInnerAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIKnockoutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStrengthAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlGlow",
        clazz               =   "com.googlecode.jsfFlex.effects.ext.MXMLUIGlow",
        type                =   "com.googlecode.jsfFlex.MXMLUIGlow",
        tagClass            =   "com.googlecode.jsfFlex.taglib.effects.ext.MXMLUIGlowTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLGlow"
)
public abstract class AbstractMXMLUIGlow 
                                extends MXMLUISimpleBase 
                                implements _MXMLUITweenEffectAttributes, _MXMLUIAlphaFromAttribute, _MXMLUIAlphaToAttribute, 
                                _MXMLUIBlurXFromAttribute, _MXMLUIBlurXToAttribute, _MXMLUIBlurYFromAttribute, _MXMLUIBlurYToAttribute, 
                                _MXMLUIColorAttribute, _MXMLUIInnerAttribute, _MXMLUIKnockoutAttribute, _MXMLUIStrengthAttribute {

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
