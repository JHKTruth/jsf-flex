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
public interface _MXMLUIBaseEventsAttributes {
	
	/**
	 * Used by a validator to associate a subfield with this component.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the component is added to a container as a content child by using the addChild() or addChildAt() method."
	 */
	String getAdd();
	
	/**
	 * Dispatched after the view state has changed.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched after the view state has changed."
	 */
	String getCurrentStateChange();
	
	/**
	 * Dispatched after the currentState property changes, but before the view state changes.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched after the currentState property changes, but before the view state changes."
	 */
	String getCurrentStateChanging();
	
	/**
	 * Dispatched by the drag initiator (the component that is the source of the data being dragged) when the drag operation completes, either when you drop the dragged data onto a drop target or when you end the drag-and-drop operation without performing a drop.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the drag initiator (the component that is the source of the data being dragged) when the drag operation completes, either when you drop the dragged data onto a drop target or when you end the drag-and-drop operation without performing a drop."
	 */
	String getDragComplete();
	
	/**
	 * Dispatched by the drop target when the user releases the mouse over it.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the drop target when the user releases the mouse over it."
	 */
	String getDragDrop();
	
	/**
	 * Dispatched by a component when the user moves the mouse over the component during a drag operation.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by a component when the user moves the mouse over the component during a drag operation."
	 */
	String getDragEnter();
	
	/**
	 * Dispatched by the component when the user drags outside the component, but does not drop the data onto the target.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when the user drags outside the component, but does not drop the data onto the target."
	 */
	String getDragExit();
	
	/**
	 * Dispatched by a component when the user moves the mouse while over the component during a drag operation.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by a component when the user moves the mouse while over the component during a drag operation."
	 */
	String getDragOver();
	
	/**
	 * Dispatched after an effect ends.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched after an effect ends."
	 */
	String getEffectEnd();
	
	/**
	 * Dispatched just before an effect starts.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched just before an effect starts."
	 */
	String getEffectStart();
	
	/**
	 * Dispatched after the component has returned to the root view state.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched after the component has returned to the root view state."
	 */
	String getEnterState();
	
	/**
	 * Dispatched before the component exits from the root view state.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched before the component exits from the root view state."
	 */
	String getExitState();
	
	/**
	 * Dispatched when an object's state changes from visible to invisible.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when an object's state changes from visible to invisible."
	 */
	String getHide();

	/**
	 * Dispatched when the component has finished its construction and has all initialization properties set.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the component has finished its construction and has all initialization properties set."
	 */
	String getInitialize();
	
	/**
	 * Dispatched when a component is monitored by a Validator and the validation failed.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when a component is monitored by a Validator and the validation failed."
	 */
	String getInvalid();
	
	/**
	 * Dispatched from a component opened using the PopUpManager when the user clicks outside it.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched from a component opened using the PopUpManager when the user clicks outside it."
	 */
	String getMouseDownOutside();
	
	/**
	 * Dispatched from a component opened using the PopUpManager when the user scrolls the mouse wheel outside it.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched from a component opened using the PopUpManager when the user scrolls the mouse wheel outside it."
	 */
	String getMouseWheelOutside();
	
	/**
	 * Dispatched when the object has moved.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the object has moved."
	 */
	String getMove();
	
	/**
	 * Dispatched at the beginning of the component initialization sequence.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched at the beginning of the component initialization sequence."
	 */
	String getPreinitialize();
	
	/**
	 * Record
	 * 
	 * @JSFProperty
	 * 		desc		= "Record"
	 */
	String getRecord();
	
	/**
	 * Dispatched when the component is removed from a container as a content child by using the removeChild() or removeChildAt() method.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the component is removed from a container as a content child by using the removeChild() or removeChildAt() method."
	 */
	String getRemove();
	
	/**
	 * Dispatched when an object's state changes from invisible to visible.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when an object's state changes from invisible to visible."
	 */
	String getShow();
	
	/**
	 * Dispatched when the component is resized.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when the component is resized."
	 */
	String getResize();
	
	/**
	 * Dispatched by the component when it is time to create a ToolTip.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when it is time to create a ToolTip."
	 */
	String getToolTipCreate();
	
	/**
	 * Dispatched by the component when its ToolTip has been hidden and will be discarded soon.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when its ToolTip has been hidden and will be discarded soon."
	 */
	String getToolTipEnd();
	
	/**
	 * Dispatched by the component when its ToolTip is about to be hidden.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when its ToolTip is about to be hidden."
	 */
	String getToolTipHide();
	
	/**
	 * Dispatched by the component when its ToolTip is about to be shown.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when its ToolTip is about to be shown."
	 */
	String getToolTipShow();
	
	/**
	 * Dispatched by the component when its ToolTip has been shown.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by the component when its ToolTip has been shown."
	 */
	String getToolTipShown();
	
	/**
	 * Dispatched by a component whose toolTip property is set, as soon as the user moves the mouse over it.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched by a component whose toolTip property is set, as soon as the user moves the mouse over it."
	 */
	String getToolTipStart();
	
	/**
	 * Dispatched when an object has had its commitProperties(), measure(), and updateDisplayList() methods called (if needed).
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when an object has had its commitProperties(), measure(), and updateDisplayList() methods called (if needed)."
	 */
	String getUpdateComplete();
	
	/**
	 * Dispatched when a component is monitored by a Validator and the validation succeeded.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when a component is monitored by a Validator and the validation succeeded."
	 */
	String getValid();
	
	/**
	 * Dispatched when values are changed programmatically or by user interaction.
	 * 
	 * @JSFProperty
	 *     desc			= "Dispatched when values are changed programmatically or by user interaction."
	 */
	String getValueCommit();
	
}
