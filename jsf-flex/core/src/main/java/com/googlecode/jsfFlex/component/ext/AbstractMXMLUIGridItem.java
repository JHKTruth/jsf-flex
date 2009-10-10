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

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIColSpanAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRowSpanAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlGridItem",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIGridItem",
        type                =   "com.googlecode.jsfFlex.MXMLUIGridItem",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIGridItemTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLGridItem"
)
public abstract class AbstractMXMLUIGridItem 
                        extends MXMLUISimpleBase 
                        implements _MXMLUIBoxAttributes, _MXMLUIRowSpanAttribute, _MXMLUIColSpanAttribute {
    
    public void encodeBegin(FacesContext context) throws IOException {
        /*
         * Grid item will have direction set to horizontal
         */
        getAttributes().put("direction", "horizontal");
        
        super.encodeBegin(context);
    }
    
}
