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
package com.googlecode.jsfFlex.component.ext.service.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.attributes.IFlexUIConcurrencyAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIContentTypeAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIDestinationAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFaultAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIMakeObjectsBindableAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIMethodAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIResultAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIResultFormatAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIShowBusyCursorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIUrlAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIUseProxyAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIXmlDecodeAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIXmlEncodeAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexHTTPService",
        clazz               =   "com.googlecode.jsfFlex.component.ext.service.ext.FlexUIHTTPService",
        type                =   "com.googlecode.jsfFlex.FlexUIHTTPService",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.service.ext.FlexUIHTTPServiceTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexHTTPService"
)
public abstract class AbstractFlexUIHTTPService 
						extends AbstractFlexUISimpleBase 
                        implements IFlexUIConcurrencyAttribute, IFlexUIContentTypeAttribute, IFlexUIDestinationAttribute, 
                        IFlexUIMethodAttribute, IFlexUIResultFormatAttribute, IFlexUIShowBusyCursorAttribute, 
                        IFlexUIMakeObjectsBindableAttribute, IFlexUIUrlAttribute, IFlexUIUseProxyAttribute, 
                        IFlexUIXmlEncodeAttribute, IFlexUIXmlDecodeAttribute, IFlexUIFaultAttribute, 
                        IFlexUIResultAttribute {
    
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
