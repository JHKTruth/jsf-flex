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

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.component.MXMLUIInputBase;
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
 *   name     = "jf:mxmlColorPicker"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIColorPicker"
 *   type     = "com.googlecode.jsfFlex.MXMLUIColorPicker"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIColorPickerTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLColorPicker"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "colorField"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the field in the objects of the dataProvider Array that specifies the hexadecimal values of the colors that the swatch panel displays."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "showTextField"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether to show the text box that displays the color label or hexadecimalcolor value."
 *   						, 
 *   						
 *   						@JSFJspProperty
 * 							 name		= "previewHeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of the larger preview swatch that appears above the swatch grid on theupper left of the SwatchPanel object."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "previewWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Width of the larger preview swatch."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchBorderColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the swatches' borders."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "swatchBorderSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Size of the outlines of the swatches' borders."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchGridBackgroundColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the background rectangle behind the swatch grid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchGridBorderSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Size of the single border around the grid of swatches."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "swatchHeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of each swatch."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchHighlightColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the highlight that appears around the swatch when the user rollsover a swatch."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchHighlightSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Size of the highlight that appears around the swatch when the user rollsover a swatch."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "swatchPanelStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the style for the SwatchPanel object."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "swatchWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Width of each swatch."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textFieldWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Width of the text box that appears above the swatch grid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "labelField"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The name of the field in the data provider items to display as the label."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Background color of a component."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "closeEasingFunction"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Easing function to control component tweening."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "closeDuration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Length of a close transition, in milliseconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "color"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "columnCount"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Number of columns in the swatch grid."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusRoundedCorners"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontGridFitType"
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
 * 							 name		= "fontAntiAliasType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the antiAliasType property of internal TextFields."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontThickness"
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
 *   						 name		= "fontStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is italic font."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontWeight"
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
 *   						 name		= "highlightAlphas"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Alphas used for the highlight fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalGap"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Horizontal gap."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalGap"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Vertical gap.
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "leading"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Additional vertical space between lines of text."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "openDuration"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Length of an open or close transition, in milliseconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "openEasingFunction"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Easing function to control component tweening."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "paddingLeft"
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
 * 							 name		= "paddingBottom"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of pixels between the container's bottom border and the bottom of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingTop"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of pixels between the container's top border and the top of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textIndent"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Offset of first line of text from the left side of the container, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "textDecoration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is underlined."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textAlign"
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
 *   						 name		= "close"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the drop-down list is dismissed for any reason."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "enter"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched if the editable property is set to true and the user presses the Enter key while typing in the editable text field."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemRollOver"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemRollOut"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "open"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user clicks the drop-down button to display the drop-down list."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIColorPicker 
						extends MXMLUIInputBase 
						implements _MXMLUIComboBaseAttributes, _MXMLUIBaseAttributes, _MXMLUIRestrictAttribute, 
						_MXMLUIControlSkinAttributes, _MXMLUIDataProviderAttribute, _MXMLUIEditableAttribute, 
						_MXMLUIImeModeAttribute, _MXMLUISelectedIndexAttribute, _MXMLUISelectedItemAttribute {
	
	private final static Log _log = LogFactory.getLog(AbstractMXMLUIColorPicker.class);
	
	private static final String SELECTED_COLOR_ATTR = "selectedColor";
	private static final String SELECTED_COLOR_ID_APPENDED = "_selectedColor";
	
	private JSONObject initValue;
	
	{
		try{
			initValue = new JSONObject();
			initValue.put(ATTRIBUTE, SELECTED_COLOR_ATTR);
			
			_initValues.put(initValue);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		try{
			if(getSelectedColor() != null){
				initValue.put(VALUE, getSelectedColor());
			}
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	Map requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String selectedColorId = getId() + SELECTED_COLOR_ID_APPENDED;
    	String selectedColorUpdateVal = (String) requestMap.get(selectedColorId);
    	
    	if(selectedColorUpdateVal != null){
    		setSelectedColor(selectedColorUpdateVal);
    		setSubmittedValue(selectedColorUpdateVal);
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueExpression ve = getValueExpression(SELECTED_COLOR_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelectedColor());
    		setSelectedColor(null);
    	}
    	
    }
	
	/**
	 * The value of the currently selected color in the SwatchPanel object.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = true
	 *    desc            = "The value of the currently selected color in the SwatchPanel object."
	 */
	public abstract String getSelectedColor();
	
	public abstract void setSelectedColor(String selectedColor);
	
}
