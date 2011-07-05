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

import java.io.IOException;

import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIContainerAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlAccordion"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIAccordion"
 *   type     = "com.googlecode.jsfFlex.MXMLUIAccordion"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIAccordionTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLAccordion"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "headerRenderer"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "A factory used to create the navigation buttons for each child."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectedFillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The two colors used to tint the background of the component when inits selected state."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "historyManagementEnabled"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "If true, enables history management within this ViewStack container."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "resizeToContent"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "If true, the ViewStack container automatically resizes to the size of its current child."
 *   						,
 *   						
 *							@JSFJspProperty
 *   						 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusAlpha"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusRoundedCorners"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerHeight"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Height of each accordion header, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerStyleName"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Name of the style sheet definition to configure the text (month name and year) and appearance of the header area of the control."
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
 *   						 name		= "openDuration"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Length of an open or close transition, in milliseconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "openEasingFunction"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Easing function to control component tweening."
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
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						
 * Since Accordion is written to maintain it's state [which container is chosen], it
 * will extend directly from MXMLUIInputBase and not of a Container
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIAccordion 
						extends MXMLUISelectedIndexBase
						implements _MXMLUIContainerAttributes, _MXMLUIBaseAttributes, _MXMLUIIconAttribute, 
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, 
						_MXMLUIBackgroundDisabledColorAttribute, _MXMLUIBarColorAttribute, _MXMLUIBorderAttributes, 
						_MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIScrollBarAttributes,
						_MXMLUIColorAttribute, _MXMLUITrackAttributes, _MXMLUICornerRadiusAttribute, _MXMLUIShadowAttributes, 
						_MXMLUIDataChangeAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIFontFamilyAttribute, 
						_MXMLUIFontGeneralAttributes, _MXMLUIFontSpecificAttributes, _MXMLUIThumbSkinAttributes,
						_MXMLUIHorizontalScrollPositionAttribute, _MXMLUITextStyleAttributes, _MXMLUILabelAttribute, 
						_MXMLUIPaddingHorizontalAttributes, _MXMLUIPaddingVerticalAttributes, _MXMLUIScrollAttribute, 
						_MXMLUIScrollAttributes {
	
	public void encodeBegin(FacesContext context) throws IOException {
		/*
		 * HACK :
		 * 	Setting creationPolicy to "all", so the components which are not
		 * 	shown by the non-selected Container would be created and can be referred 
		 * 	during the initialization/value preserving process
		 * 
		 *	I think this is the most prudent choice
		 */
		setCreationPolicy("all");
		
		super.encodeBegin(context);
	}
	
}
