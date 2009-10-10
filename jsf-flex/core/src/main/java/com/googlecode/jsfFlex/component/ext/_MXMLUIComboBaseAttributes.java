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

import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableDisabledSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableDownSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableUpSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOverSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRestrictAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedItemAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextInputStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUpSkinAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIComboBaseAttributes 
                        extends _MXMLUIImeModeAttribute, _MXMLUIRestrictAttribute, _MXMLUISelectedItemAttribute,
                        _MXMLUIDisabledSkinAttribute, _MXMLUIDownSkinAttribute, _MXMLUIEditableDisabledSkinAttribute, 
                        _MXMLUIEditableDownSkinAttribute, _MXMLUIEditableOverSkinAttribute, _MXMLUIEditableUpSkinAttribute,
                        _MXMLUIOverSkinAttribute, _MXMLUITextInputStyleNameAttribute, _MXMLUIUpSkinAttribute, 
                        _MXMLUIBaseAttributes {
    
    String getDataProvider();
    
    void setDataProvider(String dataProvider);
    
    String getEditable();
    
    Integer getSelectedIndex();
    
    void setSelectedIndex(Integer selectedIndex);
    
    String getText();
    
    void setText(String text);
    
}
