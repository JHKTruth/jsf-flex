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
package com.googlecode.jsfFlex.renderkit.component.ext.data;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLSimpleBase"
 *  type        = "com.googlecode.jsfFlex.MXMLObject"
 * 
 * @JsfFlexAttributes
 *  id=false
 * 	label=false
 * 	data=false
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLObjectRenderer extends MXMLComponentBaseRenderer {
	
	private static final String MXML_OBJECT_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "Object";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLObjectRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_OBJECT_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLObjectRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLObjectRenderer.class, componentObj, MXML_OBJECT_REPLACE_MAPPING);
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}
	
}