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
 *   name     = "jf:mxmlImage"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIImage"
 *   type     = "com.googlecode.jsfFlex.MXMLUIImage"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIImageTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLImage"
 * 
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "autoLoad"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, the content loads automatically."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "loaderContext"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "A LoaderContext object to use to load the content."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "scaleContent"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "If true, the content scales to fit the SWFLoader control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "showBusyCursor"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "If true, shows a busy cursor while the content loads."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "trustContent"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "If true, the content is loaded into your security domain."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "brokenImageBorderSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of class to use as the SWFLoader border skin if the control can not load the content."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "brokenImageSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Name of the class to use as the SWFLoader skin if the control can not load the content."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "httpStatus"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when a network request is made over HTTP and Flash Player can detect theHTTP status code."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "init"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the properties and methods of a loaded SWF file are accessible."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "ioError"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Dispatched when an input/output error occurs."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "securityError"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Dispatched when a security error occurs while content is loading."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "unload"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Dispatched when a loaded object is removed, or when a second load is performed by the same SWFLoader control and the original content is removed prior to the new load beginning."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "maintainAspectRatio"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "If true, specifies to display the image with the same ratio of height to width as the original image."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "source"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the content to load."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "progress"
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
 *   						 name		= "horizontalAlign"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Horizontal alignment of children in the container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalAlign"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The vertical alignment of a renderer in a row."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "completeEffect"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Effect called when Flex dispatches the complete event, which occurs when the load completes."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "open"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user clicks the drop-down button to display the drop-down list."
 *  						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dataChange"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIImage 
						extends MXMLUISimpleBase
						implements _MXMLUIBaseAttributes {
	
}
