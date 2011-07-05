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

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
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
 *   name     = "jf:mxmlPanel"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIPanel"
 *   type     = "com.googlecode.jsfFlex.MXMLUIPanel"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIPanelTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLPanel"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "layout"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the layout mechanism used for this application."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "status"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Text in the status area of the title bar."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "titleIcon"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The icon displayed in the title bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderAlpha"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Alpha of the title bar, control bar and sides of the Panel. The default value is 0.4."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderThicknessBottom"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness of the bottom border of the Panel control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderThicknessLeft"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness of the left border of the Panel."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderThicknessRight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness of the right border of the Panel."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderThicknessTop"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Thickness of the top border of the Panel."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "closeButtonDisabledSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The close button disabled skin."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "closeButtonDownSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The close button down skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "closeButtonOverSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The close button over skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "closeButtonUpSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The close button up skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "controlBarStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the CSS style declaration that specifies styles to apply to any control bar child subcontrol."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "footerColors"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Array of two colors used to draw the footer background."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "roundedBottomCorners"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Flag to enable rounding for the bottom two corners of the container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "statusStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Style declaration name for the status in the title bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "titleBackgroundSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The title background skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "titleStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Style declaration name for the text in the title bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "resizeEndEffect"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the effect to play after a Resize effect finishes playing."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "resizeStartEffect"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Specifies the effect to play before a Resize effect begins playing."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "title"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Title or caption displayed in the title bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerColors"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Colors of the band at the top of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerHeight"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Height of each accordion header, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "highlightAlphas"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Alphas used for the highlight fill of controls."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalAlign"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Horizontal alignment of children in the container."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "horizontalGap"
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
 * 							 name		= "modalTransparencyDuration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Duration, in milliseconds, of the modal transparency effect that plays when a modal window opens or closes. The default value is 100."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "modalTransparency"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Modality of components launched by the PopUp Manager is simulated by creating a large translucent overlay underneath the component. Because of the way translucent objects are rendered, you may notice a slight dimming of the objects under the overlay. The effective transparency can be set by changing the modalTransparency value from 0.0 (fully transparent) to 1.0 (fully opaque). You can also set the color of the overlay by changing the modalTransparencyColor style. The default value is 0.5."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "modalTransparencyColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the modal overlay layer. This style is used in conjunction with the modalTransparency style to determine the colorization applied to the application when a modal window is open. The default value is #DDDDDD."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "modalTransparencyBlur"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The blur applied to the application while a modal window is open. A Blur effects oftens the details of an image. The default value is 3."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalAlign"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The vertical alignment of a renderer in a row."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "close"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the drop-down list is dismissed for any reason."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIPanel 
						extends MXMLUISimpleBase 
						implements _MXMLUIContainerAttributes, _MXMLUIBaseAttributes, _MXMLUIFontSpecificAttributes, 
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, 
						_MXMLUIBackgroundDisabledColorAttribute, _MXMLUIBarColorAttribute, _MXMLUIBorderAttributes, 
						_MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIIconAttribute,
						_MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, _MXMLUIDataChangeAttribute, 
						_MXMLUIDisabledColorAttribute, _MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, 
						_MXMLUIHorizontalScrollPositionAttribute, _MXMLUIPaddingHorizontalAttributes, _MXMLUITrackAttributes,  
						_MXMLUILabelAttribute, _MXMLUIThumbSkinAttributes, _MXMLUITextStyleAttributes,
						_MXMLUIPaddingVerticalAttributes, _MXMLUIScrollAttribute, _MXMLUIScrollAttributes, 
						_MXMLUIScrollBarAttributes, _MXMLUIShadowAttributes {
	
}
