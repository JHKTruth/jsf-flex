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

import com.googlecode.jsfFlex.attributes._MXMLUIAllowDragSelectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAllowMultipleSelectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIAlternatingItemColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColumnCountAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColumnWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataTipFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataTipFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDragEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDragMoveEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropIndicatorSkinAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemClickAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemDoubleClickAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOverAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILockedColumnCountAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILockedRowCountAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMenuSelectionModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOffscreenExtraRowsOrColumnsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRowHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndicesAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedItemAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedItemsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowDataTipsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUseRollOverAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVariableRowHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWordWrapAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUIListBaseAttributes 
                    extends _MXMLUIAllowDragSelectionAttribute, _MXMLUIAllowMultipleSelectionAttribute, _MXMLUIColumnCountAttribute, 
                    _MXMLUIColumnWidthAttribute, _MXMLUIDataTipFieldAttribute, _MXMLUIDataTipFunctionAttribute, 
                    _MXMLUIDragEnabledAttribute, _MXMLUIDragMoveEnabledAttribute, _MXMLUIDropEnabledAttribute, 
                    _MXMLUIIconFieldAttribute, _MXMLUIIconFunctionAttribute, _MXMLUIItemRendererAttribute, _MXMLUILabelFieldAttribute, 
                    _MXMLUILabelFunctionAttribute, _MXMLUILockedColumnCountAttribute, _MXMLUILockedRowCountAttribute, 
                    _MXMLUIMenuSelectionModeAttribute, _MXMLUIRowHeightAttribute, _MXMLUISelectableAttribute, _MXMLUISelectedIndicesAttribute, 
                    _MXMLUISelectedItemAttribute, _MXMLUISelectedItemsAttribute, _MXMLUIShowDataTipsAttribute, 
                    _MXMLUIVariableRowHeightAttribute, _MXMLUIWordWrapAttribute, _MXMLUIAlternatingItemColorsAttribute, 
                    _MXMLUIDropIndicatorSkinAttribute, _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, 
                    _MXMLUIPaddingBottomAttribute, _MXMLUIPaddingLeftAttribute, _MXMLUIPaddingRightAttribute, _MXMLUIPaddingTopAttribute, 
                    _MXMLUIRollOverColorAttribute, _MXMLUISelectionColorAttribute, _MXMLUISelectionDisabledColorAttribute, 
                    _MXMLUISelectionDurationAttribute, _MXMLUISelectionEasingFunctionAttribute, _MXMLUITextRollOverColorAttribute, 
                    _MXMLUITextSelectedColorAttribute, _MXMLUIUseRollOverAttribute, _MXMLUIVerticalAlignAttribute, 
                    _MXMLUIChangeAttribute, _MXMLUIDataChangeAttribute, _MXMLUIItemDoubleClickAttribute, 
                    _MXMLUIItemRollOutAttribute, _MXMLUIItemRollOverAttribute, _MXMLUIItemClickAttribute, 
                    _MXMLUIOffscreenExtraRowsOrColumnsAttribute, _MXMLUIScrollControlAttributes {
    
    String getDataProvider();
    
    void setDataProvider(String dataProvider);
    
    String getRowCount();
    
    Integer getSelectedIndex();
    
    void setSelectedIndex(Integer selectedIndex);
    
}
