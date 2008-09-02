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

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import com.googlecode.jsfFlex.component.MXMLUIButtonBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIImmediateAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIValueAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlRadioButton"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIRadioButton"
 *   type     = "com.googlecode.jsfFlex.MXMLUIRadioButton"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIRadioButtonTag"
 *   family   = "javax.faces.MXMLInput"
 *   tagSuperclass = "org.apache.myfaces.shared_impl.taglib.UIComponentTagBase"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLInput"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "autoRepeat"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether to dispatch repeated buttonDown  events if the user holds down the mouse button."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "emphasized"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Draws a thick border around the Button control when the control is in its upstate if emphasized  is set to true."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedField"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of the field in the data property which specifies the value of the Button control's selected property."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "stickyHighlighting"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If false, the Button displays its down skin when the user presses it but changes to its over skin when the user drags the mouse off of it. If true,the Button displays its down skin when the user presses it, and continues todisplay this skin when the user drags the mouse off of it."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "toggle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Controls whether a Button is in a toggle state or not."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "disabledIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is not disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "downIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is not selected and the mouse button is down."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "overIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is not selected and the mouse is over the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectedDisabledIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is selected and disabled."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedDisabledSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when a toggle button is selected and disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectedDownIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is selected and the mouse button is down."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedDownSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when a toggle button is selected and the mouse button is down."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectedOverIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is selected and the mouse is over the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectedOverSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when a toggle button is selected and the mouse is over the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedUpIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when the button is selected and the mouse button is up."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedUpSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when a toggle button is selected and the mouse is not over the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "upIcon"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the icon when a toggle button is not selected and the mouse is not over the button."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "buttonDown"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user presses the Button control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "label"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Text to appear on the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "labelPlacement"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Placement of the label."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "color"
 *  						 returnType = "java.lang.String"
 *   						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cornerRadius"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Radius of component corners."
 *   						,
 *   						
 *							@JSFJspProperty
 * 							 name		= "disabledColor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "upSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when the mouse is not over the control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when the control is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "downSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when the user holds down the mouse button."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "overSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the skin for the background and border when the mouse is over the control."
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
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "focusRoundedCorners"
 *   						 returnType = "java.lang.String"
 *							 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
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
 * 							 name		= "fontFamily"
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
 * 							 returnType = "java.lang.String"
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
 *   						 longDesc	= "Vertical gap."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "icon"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the class to use as the default icon."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "leading"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Additional vertical space between lines of text."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingBottom"
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
 *   						 name		= "repeatDelay"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of milliseconds to wait."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "repeatInterval"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of milliseconds in the actions."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textIndent"
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
 *   						 name		= "textAlign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alignment of text within a container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textSelectedColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Text color of the label as the user presses it."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textRollOverColor"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Text color of the label as the user moves the mouse pointer over the button."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "change"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dataChange"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIRadioButton 
						extends MXMLUIButtonBase
						implements _MXMLUIBaseAttributes, _MXMLUIValueAttribute, _MXMLUIImmediateAttribute, 
						_MXMLUITextAttribute, _MXMLUISelectedAttribute {
	
	private static final String MXML_COMPONENT_RENDERER = "com.googlecode.jsfFlex.MXMLRadioButton";
	private static final String SELECTED_VALUE_ID_APPENDED = "_selectedValue";
	
	public String getMXMLComponentRenderer(){
    	return MXML_COMPONENT_RENDERER;
    }
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	HttpServletRequest httpRequest = (HttpServletRequest) context.getExternalContext().getRequest();
    	
    	/*
    	 *	So to make it easy for databinding, will utilize groupName + _selectedValue
    	 *	as the search parameter when setting the selectedValue variable.
    	 *	This way the developer has to simply dataBind on selectedValue per groupName entry
    	 *	to check which value was selected by user.
    	 */
    	
    	String selectedValueId = getGroupName() + SELECTED_VALUE_ID_APPENDED;
    	String selectedValueUpdateVal = httpRequest.getParameter(selectedValueId);
    	if(selectedValueUpdateVal != null){
    		setSelectedValue(selectedValueUpdateVal);
    		setSubmittedValue(selectedValueUpdateVal);
    	}
    	
    	if(getValue() == null){
    		setSelected(Boolean.valueOf(false));
    	}else if(getSelectedValue() != null){
    		setSelected(Boolean.valueOf(getSelectedValue().equals(getValue())));
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueBinding vb = getValueBinding("selectedValue");
		if(vb != null && !vb.isReadOnly(getFacesContext())){
			vb.setValue(getFacesContext(), getSelectedValue());
			setSelectedValue(null);
		}
    	
    }
	
	/**
	 * Specifies the name of the group to which this RadioButton control belongs, or specifies the value of the id property of a RadioButtonGroup control if this RadioButton is part of agroup defined by a RadioButtonGroup control.
	 * 
	 *@JSFProperty
	 *    required        = true
	 *    rtexprvalue     = false
	 *    desc            = "Specifies the name of the group to which this RadioButton control belongs, or specifies the value of the id property of a RadioButtonGroup control if this RadioButton is part of agroup defined by a RadioButtonGroup control."
	 */
	public abstract String getGroupName();
    
	/**
	 * This will represent the selectedValue chosen for the RadioButtonGroup. It should be used for databinding, so to figure out which radioButton within the same groupNamehas been chosen. Meaning it serves no purpose for display, so rationally you shouldhave databinded to ONE of the RadioButton with the same groupName.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = true
	 *    desc            = "This will represent the selectedValue chosen for the RadioButtonGroup. It should be used for databinding, so to figure out which radioButton within the same groupNamehas been chosen. Meaning it serves no purpose for display, so rationally you shouldhave databinded to ONE of the RadioButton with the same groupName."
	 */
	public abstract String getSelectedValue();
	
	public abstract void setSelectedValue(String selectedValue);
	
}