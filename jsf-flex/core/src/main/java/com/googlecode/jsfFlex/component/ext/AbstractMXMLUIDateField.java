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

import com.googlecode.jsfFlex.component.MXMLUITextInputBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIControlSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIRestrictAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedItemAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIComboBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlDateField"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIDateField"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDateField"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIDateFieldTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLDateField"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "maxYear"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The last year selectable in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "parseFunction"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Function used to parse the date entered as text in the text field area of the DateField control and return a Date object to the control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dateChooserStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the CSS Style declaration to use for the styles for the DateChooser control's drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "todayStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the style sheet definition to configure the appearance of the current day's numeric text, which is highlighted in the control when the showToday property is true. Specify a color style to change the font color. If omitted, the current day textinherits the text styles of the control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "todayColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the background of today's date. The default value is 0x2B333C."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "yearNavigationEnabled"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Enables year navigation."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledRanges"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Disables single and multiple days."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "displayedMonth"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Used together with the displayedYear property, the displayedMonth property specifies the month displayed in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedDate"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Date selected in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "displayedYear"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Used together with the displayedMonth property, the displayedYear property specifies the month displayed in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "showToday"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, specifies that today is highlighted in the DateChooser control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "firstDayOfWeek"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number representing the day of the week to display in the first column of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dayNames"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The weekday names for the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "monthNames"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Names of the months displayed at the top of the DateChooser control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "disabledDays"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The days to disable in a week."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectableRange"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Range of dates between which dates are selectable."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "yearSymbol"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "This property is appended to the end of the year displayed at the top of the DateChooser control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "minYear"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The first year selectable in the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "monthSymbol"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "This property is appended to the end of the value specified by the monthNames property to define the names of the months displayed at the top of the DateChooser control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "weekDayStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the style sheet definition to configure the weekday names of the control. If omitted, the weekday names inherit the text styles of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "formatString"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The mask pattern."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "labelFunction"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "User-supplied function to run on each item to determine its label."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderThickness"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Bounding box thickness."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "color"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "cornerRadius"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Radius of component corners."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusRoundedCorners"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontGridFitType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the gridFitType property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontSharpness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the sharpness property of internal TextFields that represent text in Flex controls."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontAntiAliasType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the antiAliasType property of internal TextFields."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontThickness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the thickness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontFamily"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the font to use."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is italic font."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontWeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is boldface."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of the text, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerColors"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Colors of the band at the top of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerStyleName"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the style sheet definition to configure the text (month name and year) and appearance of the header area of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "highlightAlphas"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Alphas used for the highlight fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "leading"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Additional vertical space between lines of text."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingLeft"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of pixels between the container's left border and the left edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingRight"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Number of pixels between the container's right border and the right edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "rollOverColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The rollOverColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectionColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textIndent"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Offset of first line of text from the left side of the container, in pixels."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textDecoration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is underlined."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textAlign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alignment of text within a container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "change"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "close"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the drop-down list is dismissed for any reason."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dataChange"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "open"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user clicks the drop-down button to display the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "scroll"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user manually scrolls the container."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIDateField 
						extends MXMLUITextInputBase 
						implements _MXMLUIComboBaseAttributes, _MXMLUIBaseAttributes, _MXMLUISelectedIndexAttribute, 
						_MXMLUIControlSkinAttributes, _MXMLUIDataProviderAttribute, _MXMLUISelectedItemAttribute,  
						_MXMLUIEditableAttribute, _MXMLUIImeModeAttribute, _MXMLUIRestrictAttribute {
	
}
