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

import com.googlecode.jsfFlex.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemClickAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIToolTipFieldAttribute;
import com.googlecode.jsfFlex.container.ext._MXMLUIBoxAttributes;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUINavBarAttributes 
            extends _MXMLUIIconFieldAttribute, _MXMLUILabelFieldAttribute, _MXMLUIToolTipFieldAttribute, _MXMLUIItemClickAttribute, 
            _MXMLUIBoxAttributes {
    
    String getDataProvider();
    
    Integer getSelectedIndex();
    
    void setSelectedIndex(Integer selectedIndex);
    
}
