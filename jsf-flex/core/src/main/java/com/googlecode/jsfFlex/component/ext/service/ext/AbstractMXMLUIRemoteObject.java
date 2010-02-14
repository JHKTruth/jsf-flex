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

import com.googlecode.jsfFlex.attributes._MXMLUIConcurrencyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDestinationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEndpointAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFaultAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMakeObjectsBindableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResultAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowBusyCursorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISourceAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlRemoteObject",
        clazz               =   "com.googlecode.jsfFlex.component.ext.service.ext.MXMLUIRemoteObject",
        type                =   "com.googlecode.jsfFlex.MXMLUIRemoteObject",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.service.ext.MXMLUIRemoteObjectTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLRemoteObject"
)
public abstract class AbstractMXMLUIRemoteObject 
						extends MXMLUISimpleBase 
                        implements _MXMLUIConcurrencyAttribute, _MXMLUIDestinationAttribute, _MXMLUIEndpointAttribute, 
                        _MXMLUIShowBusyCursorAttribute, _MXMLUISourceAttribute, _MXMLUIMakeObjectsBindableAttribute, 
                        _MXMLUIFaultAttribute, _MXMLUIResultAttribute {
    
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
