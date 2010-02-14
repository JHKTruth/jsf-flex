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
import com.googlecode.jsfFlex.attributes._MXMLUIFaultAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMakeObjectsBindableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResultAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIServiceNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowBusyCursorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUseProxyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWsdlAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlWebService",
        clazz               =   "com.googlecode.jsfFlex.component.ext.service.ext.MXMLUIWebService",
        type                =   "com.googlecode.jsfFlex.MXMLUIWebService",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.service.ext.MXMLUIWebServiceTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLWebService"
)
public abstract class AbstractMXMLUIWebService 
						extends MXMLUISimpleBase 
                        implements _MXMLUIConcurrencyAttribute, _MXMLUIServiceNameAttribute, _MXMLUIShowBusyCursorAttribute, 
                        _MXMLUIMakeObjectsBindableAttribute, _MXMLUIUseProxyAttribute, _MXMLUIWsdlAttribute, 
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
