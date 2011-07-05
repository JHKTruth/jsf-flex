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
package com.googlecode.jsfFlex.component.ext.data.ext.properties.ext;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.ext.data.ext.AbstractMXMLUIObject;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.MXMLUIDataObjectBase;
import com.googlecode.jsfFlex.shared.context.MxmlContext;

/**
 * @JSFComponent
 *   name     = "jf:mxmlObjectElement"
 *   class    = "com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.MXMLUIObjectElement"
 *   type     = "com.googlecode.jsfFlex.MXMLUIObjectElement"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.data.properties.MXMLUIObjectElementTag"
 *   family   = "javax.faces.MXMLProperty"
 *   
 * Since this component is out of the norm in relation to writing MXML content, it will perform <br>
 * the write of MXML content within the component rather than within a Renderer [meaning Renderer does <br>
 * not exist for this component]. Also when stated that it is writing MXML content, it technically is <br>
 * writing to MXMLUIDataContainerBase's BufferedWriter.<br>
 * 
 * <ul>
 * This component can have following types of children :
 * 		<li> AbstractMXMLUIObjectProperty </li>
 * 		<li> AbstractMXMLUIObjectListEntries </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIObjectElement 
						extends MXMLUIDataObjectBase {
	
	private static final String OBJECT_START_TAG = "<mx:Object";
	private static final String OBJECT_START_TAG_CLOSER = ">";
	private static final String OBJECT_END_TAG = "</mx:Object>";
	
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		Map temporaryResourceMap = mxmlContext.getTemporaryResourceMap();
		AbstractMXMLUIObject currObjectContainerRef = (AbstractMXMLUIObject) temporaryResourceMap.get(AbstractMXMLUIObject.CURR_MXML_UI_OBJECT_CONTAINER_KEY);
		
		StringBuffer objectStartTagBuffer = new StringBuffer();
		objectStartTagBuffer.append(OBJECT_START_TAG);
		
		objectStartTagBuffer.append( processDataObjectProperties() );
		
		objectStartTagBuffer.append(OBJECT_START_TAG_CLOSER);
		
		//now the start tag has been generated so write to the buffer
		currObjectContainerRef.getCurrBodyContentBufferedWriter().write(objectStartTagBuffer.toString());
		
	}
	
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		Map temporaryResourceMap = mxmlContext.getTemporaryResourceMap();
		AbstractMXMLUIObject currObjectContainerRef = (AbstractMXMLUIObject) temporaryResourceMap.get(AbstractMXMLUIObject.CURR_MXML_UI_OBJECT_CONTAINER_KEY);
		
		currObjectContainerRef.getCurrBodyContentBufferedWriter().write(OBJECT_END_TAG);
		
	}
	
}
