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
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlVideoDisplay"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIVideoDisplay"
 *   type     = "com.googlecode.jsfFlex.MXMLUIVideoDisplay"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIVideoDisplayTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLVideoDisplay"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "autoBandWidthDetection"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether the VideoDisplay control should use the built-in automatic bandwidth detection feature."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "autoPlay"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether the video should start playing immediately when the source property is set."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "autoRewind"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Specifies whether the FLV file should be rewound to the first frame when play stops, either by calling the stop() method or by reaching the end of the stream."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "bufferTime"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of seconds of video to buffer in memory before starting to play the video file."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cuePointManagerClass"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Cue point manager to use."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "cuePoints"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The Array of cue points associated with the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "idleTimeout"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the amount of time, in milliseconds, that the connection is idle (playing is paused or stopped) before the connection to the Flash Media Server is stopped."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "live"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies whether the control is streaming a live feed."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "playheadTime"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Playhead position, measured in seconds, since the video starting playing."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "playheadUpdateInterval"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the amount of time, in milliseconds, between each playheadUpdate event."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "progressInterval"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the amount of time, in milliseconds, between each progress event."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "totalTime"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Total length of the media, in seconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "volume"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The volume level, specified as an value between 0 and 1."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cuePoint"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the value of a cue point's time property is equal to the current playhead location."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "playheadUpdate"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched every 0.25 seconds while the video is playing."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "ready"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the FLV file is loaded and ready to play."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "rewind"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the control autorewinds."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "stateChange"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the state of the control changes."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "maintainAspectRatio"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "If true, specifies to display the image with the same ratio of height to width as the original image."
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
 *   						 name		= "complete"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when content loading is complete."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name = "backgroundAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc = "Alpha level of the color defined by the backgroundColor property."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Background color of a component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundImage"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Background image of a component."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Scales the image specified by backgroundImage to different percentage sizes."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dropShadowColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderSides"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Bounding box sides. A space-delimited String that specifies the sides of the border to show."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Bounding box style."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The border skin of the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderThickness"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Bounding box thickness."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cornerRadius"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Radius of component corners."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dropShadowEnabled"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Boolean property that specifies whether the component has a visible drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "shadowDirection"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Direction of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "shadowDistance"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Distance of the drop shadow."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "close"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the drop-down list is dismissed for any reason."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIVideoDisplay 
						extends MXMLUISimpleBase 
						implements _MXMLUIBaseAttributes {
	
}
