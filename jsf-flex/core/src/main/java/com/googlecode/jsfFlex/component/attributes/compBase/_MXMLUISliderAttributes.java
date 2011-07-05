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
public interface _MXMLUISliderAttributes {
	
	/**
	 * If set to false, then each thumb can only be moved to the edge of the adjacent thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "If set to false, then each thumb can only be moved to the edge of the adjacent thumb."
	 */
	String getAllowThumbOverlap();

	/**
	 * Specifies whether clicking on the track will move the slider thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies whether clicking on the track will move the slider thumb."
	 */
	String getAllowTrackClick();

	/**
	 * Callback function that formats the data tip text.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Callback function that formats the data tip text."
	 */
	String getDataTipFormatFunction();

	/**
	 * An array of strings used for the slider labels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "An array of strings used for the slider labels."
	 */
	String getLabels();

	/**
	 * If set to true, show a data tip during user interaction containing the current value of the slider.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "If set to true, show a data tip during user interaction containing the current value of the slider."
	 */
	String getShowDataTip();

	/**
	 * A reference to the class to use for the data tip.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A reference to the class to use for the data tip."
	 */
	String getSliderDataTipClass();

	/**
	 * A reference to the class to use for each thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "A reference to the class to use for each thumb."
	 */
	String getSliderThumbClass();

	/**
	 * Specifies the increment value of the slider thumb as the user moves the thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the increment value of the slider thumb as the user moves the thumb."
	 */
	String getSnapInterval();

	/**
	 * The number of thumbs allowed on the slider.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The number of thumbs allowed on the slider."
	 */
	String getThumbCount();

	/**
	 * The spacing of the tick marks relative to the maximum value of the control.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The spacing of the tick marks relative to the maximum value of the control."
	 */
	String getTickInterval();

	/**
	 * The offset, in pixels, of the data tip relative to the thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The offset, in pixels, of the data tip relative to the thumb."
	 */
	String getDataTipOffset();

	/**
	 * Number of decimal places to use for the data tip text.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of decimal places to use for the data tip text."
	 */
	String getDataTipPrecision();

	/**
	 * The name of the style declaration to use for the data tip.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The name of the style declaration to use for the data tip."
	 */
	String getDataTipStyleName();

	/**
	 * The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the labels relative to the track.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the labels relative to the track."
	 */
	String getLabelOffset();

	/**
	 * The name of the style to use for the slider label.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The name of the style to use for the slider label."
	 */
	String getLabelStyleName();

	/**
	 * Specifies whether to enable track highlighting between thumbs (or a single thumb and the beginning of the track).
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Specifies whether to enable track highlighting between thumbs (or a single thumb and the beginning of the track)."
	 */
	String getShowTrackHighlight();

	/**
	 * Duration in milliseconds for the sliding animation when you click on the track to move a thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Duration in milliseconds for the sliding animation when you click on the track to move a thumb."
	 */
	String getSlideDuration();

	/**
	 * Tweening function used by the sliding animation when you click on the track to move a thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Tweening function used by the sliding animation when you click on the track to move a thumb."
	 */
	String getSlideEasingFunction();

	/**
	 * The skin for the slider thumb disabled state.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The skin for the slider thumb disabled state."
	 */
	String getThumbDisabledSkin();

	/**
	 * The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the thumb relative to the track.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the thumb relative to the track."
	 */
	String getThumbOffset();

	/**
	 * The color of the tick marks.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The color of the tick marks."
	 */
	String getTickColor();

	/**
	 * The length in pixels of the tick marks.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The length in pixels of the tick marks."
	 */
	String getTickLength();

	/**
	 * The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the tick marks relative to the track.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The y-position offset (if direction is horizontal) or x-position offset (if direction is vertical) of the tick marks relative to the track."
	 */
	String getTickOffset();

	/**
	 * The thickness in pixels of the tick marks.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The thickness in pixels of the tick marks."
	 */
	String getTickThickness();

	/**
	 * The skin for the slider track when it is selected.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The skin for the slider track when it is selected."
	 */
	String getTrackHighlightSkin();

	/**
	 * The size of the track margins, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "The size of the track margins, in pixels."
	 */
	String getTrackMargin();

	/**
	 * Dispatched when the slider's thumb is pressed and then moved by the mouse.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched when the slider's thumb is pressed and then moved by the mouse."
	 */
	String getThumbDrag();

	/**
	 * Dispatched when the slider's thumb is pressed, meaning the user presses the mouse button over the thumb.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched when the slider's thumb is pressed, meaning the user presses the mouse button over the thumb."
	 */
	String getThumbPress();

	/**
	 * Dispatched when the slider's thumb is released, meaning the user releases the mouse button after a thumbPress event.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Dispatched when the slider's thumb is released, meaning the user releases the mouse button after a thumbPress event."
	 */
	String getThumbRelease();
	
}
