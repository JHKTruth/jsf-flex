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
package com.googlecode.jsfFlex.component.attributes.compBase;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLUIListBaseAttributes {
	
	/**
	 * A flag that indicates whether drag-selection is enabled.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether drag-selection is enabled."
	 */
	String getAllowDragSelection();

	/**
	 * The width of the control's columns.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The width of the control's columns."
	 */
	String getColumnWidth();

	/**
	 * Name of the field in the data provider items to display as the data tip.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the field in the data provider items to display as the data tip."
	 */
	String getDataTipField();

	/**
	 * User-supplied function to run on each item to determine its dataTip.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "User-supplied function to run on each item to determine its dataTip."
	 */
	String getDataTipFunction();

	/**
	 *  A flag that indicates whether you can dragitems out of this control and drop them on other controls.
	 *  
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether you can dragitems out of this control and drop them on other controls."
	 */
	String getDragEnabled();

	/**
	 * A flag that indicates whether dragged items can be dropped onto the control.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether dragged items can be dropped onto the control."
	 */
	String getDropEnabled();

	/**
	 * A user-supplied function to run on each item to determine its icon.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A user-supplied function to run on each item to determine its icon."
	 */
	String getIconFunction();

	/**
	 * The index of the first column in the control that scrolls.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The index of the first column in the control that scrolls."
	 */
	String getLockedColumnCount();

	/**
	 * The index of the first row in the control that scrolls.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The index of the first row in the control that scrolls."
	 */
	String getLockedRowCount();

	/**
	 * A flag that indicates whether menu-style selection should be used.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether menu-style selection should be used."
	 */
	String getMenuSelectionMode();

	/**
	 * An array of indices in the data provider of the selected items.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "An array of indices in the data provider of the selected items."
	 */
	String getSelectedIndices();

	/**
	 * An array of references to the selected items in the data provider.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "An array of references to the selected items in the data provider."
	 */
	String getSelectedItems();

	/**
	 * A flag that indicates whether dataTips are displayed for text in the rows.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether dataTips are displayed for text in the rows."
	 */
	String getShowDataTips();

	/**
	 * A flag that indicates whether the individual rows can have different height.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that indicates whether the individual rows can have different height."
	 */
	String getVariableRowHeight();

	/**
	 * The skin to use to indicate where a dragged item can be dropped.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The skin to use to indicate where a dragged item can be dropped."
	 */
	String getDropIndicatorSkin();

	/**
	 * A flag that controls whether items are highlighted as the mouse rolls over them.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A flag that controls whether items are highlighted as the mouse rolls over them."
	 */
	String getUseRollOver();

	/**
	 * Dispatched when the user double-clicks on an item in the control.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched when the user double-clicks on an item in the control."
	 */
	String getItemDoubleClick();
	
	/**
	 * The height of the rows in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The height of the rows in pixels."
	 */
	String getRowHeight();
	
}
