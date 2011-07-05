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
import com.googlecode.jsfFlex.attributes._MXMLUIColumnDropIndicatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColumnResizeSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColumnStretchAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColumnsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDraggableColumnsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditedItemPositionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderDragProxyStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderReleaseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderSeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderShiftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGridLineColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGridLinesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalLockedSeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalSeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditBeginAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditBeginningAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditEndAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditorInstanceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemFocusInAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemFocusOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMinColumnWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResizableColumnsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISortArrowSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISortableColumnsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStretchCursorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGridLineColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGridLinesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalLockedSeparatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalSeparatorSkinAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIDataGridAttributes 
            extends _MXMLUIColumnsAttribute, _MXMLUIDraggableColumnsAttribute, _MXMLUIEditedItemPositionAttribute, 
            _MXMLUIHorizontalScrollPositionAttribute, _MXMLUIImeModeAttribute, _MXMLUIItemEditorInstanceAttribute, 
            _MXMLUIMinColumnWidthAttribute, _MXMLUIResizableColumnsAttribute, _MXMLUISortableColumnsAttribute, 
            _MXMLUIBackgroundDisabledColorAttribute, _MXMLUIColumnDropIndicatorSkinAttribute, _MXMLUIColumnResizeSkinAttribute, 
            _MXMLUIHeaderColorsAttribute, _MXMLUIHeaderDragProxyStyleNameAttribute, _MXMLUIHeaderSeparatorSkinAttribute, 
            _MXMLUIHeaderStyleNameAttribute, _MXMLUIHorizontalGridLineColorAttribute, _MXMLUIHorizontalGridLinesAttribute, 
            _MXMLUIRollOverColorAttribute, _MXMLUISelectionColorAttribute, _MXMLUISortArrowSkinAttribute, _MXMLUIStretchCursorAttribute, 
            _MXMLUIVerticalGridLineColorAttribute, _MXMLUIVerticalGridLinesAttribute, _MXMLUIColumnStretchAttribute, 
            _MXMLUIHeaderReleaseAttribute, _MXMLUIHeaderShiftAttribute, _MXMLUIItemEditBeginAttribute, _MXMLUIItemEditEndAttribute, 
            _MXMLUIItemFocusInAttribute, _MXMLUIItemFocusOutAttribute, _MXMLUIHorizontalLockedSeparatorSkinAttribute, 
            _MXMLUIHorizontalSeparatorSkinAttribute, _MXMLUIIconColorAttribute, _MXMLUIVerticalLockedSeparatorSkinAttribute, 
            _MXMLUIVerticalSeparatorSkinAttribute, _MXMLUIItemEditBeginningAttribute, _MXMLUIDataGridBaseAttributes {

    String getEditable();
    
}
