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

import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditedItemPositionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorDataFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorHeightOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorUsesEnterKeyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorWidthOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorXOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorYOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditBeginAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditBeginningAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditEndAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditorInstanceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemFocusInAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemFocusOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRendererIsEditorAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIListAttributes 
                    extends _MXMLUIEditedItemPositionAttribute, _MXMLUIEditorDataFieldAttribute, _MXMLUIEditorHeightOffsetAttribute, 
                    _MXMLUIEditorUsesEnterKeyAttribute, _MXMLUIEditorWidthOffsetAttribute, _MXMLUIEditorXOffsetAttribute, 
                    _MXMLUIEditorYOffsetAttribute, _MXMLUIImeModeAttribute, _MXMLUIItemEditorAttribute, _MXMLUIItemEditorInstanceAttribute, 
                    _MXMLUIRendererIsEditorAttribute, _MXMLUIBackgroundDisabledColorAttribute, _MXMLUIItemEditBeginAttribute, 
                    _MXMLUIItemEditEndAttribute, _MXMLUIItemEditBeginningAttribute, _MXMLUIItemFocusInAttribute, 
                    _MXMLUIItemFocusOutAttribute, _MXMLUIListBaseAttributes {
    
    String getEditable();
    
}
