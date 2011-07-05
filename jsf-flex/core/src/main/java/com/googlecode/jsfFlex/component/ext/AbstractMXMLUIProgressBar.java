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

import com.googlecode.jsfFlex.component.MXMLUIValueBase;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlProgressBar"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIProgressBar"
 *   type     = "com.googlecode.jsfFlex.MXMLUIProgressBar"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIProgressBarTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLProgressBar"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "conversion"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number used to convert incoming current bytes loaded value and the total bytes loaded values."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "indeterminate"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Whether the ProgressBar control has a determinate or indeterminate appearance."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "mode"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the method used to update the bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "barSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Skin style for a determinate progress bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "indeterminateSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Skin style for an indeterminate progress bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "labelWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The width of the label in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "trackHeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The height of the track in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "direction"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Direction in which the fill of the ProgressBar expands toward completion."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "label"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Text to appear on the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "labelPlacement"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Placement of the label."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "minimum"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Minimum value of the NumericStepper."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "maximum"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Maximum value of the NumericStepper."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "source"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the content to load."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "progress"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when content is loading."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "complete"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when content loading is complete."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "barColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Determines the color of a ProgressBar."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "color"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontGridFitType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the gridFitType property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontSharpness"
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
 *   						 name		= "fontThickness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the thickness property of internal TextFields that represent text in Flex controls."
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
 *   						 name		= "fontFamily"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the font to use."
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
 * 							 name		= "leading"
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
 * 							 name		= "paddingRight"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Number of pixels between the container's right border and the right edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textIndent"
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
 * 							 name		= "themeColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Theme color of a component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "trackColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The colors of the track, as an array of two colors."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "trackSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Skin style for the progress indicator track."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "hide"
 * 							 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when an object's state changes from visible to invisible."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "show"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when an object's state changes from invisible to visible."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "completeEffect"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Effect called when Flex dispatches the complete event, which occurs when the load completes."
 * 
 * Note though MXMLUIProgressBar extends MXMLUIValueBase, it will simply retrieve the value during the post-back phase<br>
 * and NOT set the field of the Flex component as this field is read only.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIProgressBar 
						extends MXMLUIValueBase 
						implements _MXMLUIBaseAttributes {
	
	protected void populateComponentInitValues(){
		
	}
	
}
