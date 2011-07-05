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
package com.googlecode.jsfFlex.renderkit.component.ext.data.ext;

import java.io.IOException;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.ext.data.ext.AbstractMXMLUIArray;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLSimple"
 *  type        = "com.googlecode.jsfFlex.MXMLArray"
 * 
 * @JsfFlexAttributes
 *  id=true
 * 	
 * @author Ji Hoon Kim
 */
public final class MXMLArrayRenderer extends MXMLComponentBaseRenderer {
	
	private static final String MXML_ARRAY_REPLACE_MAPPING;
	private static final String MXML_COMPONENT_NAME = "Array";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLArrayRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_ARRAY_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLArrayRendererReplaceMapping.xml";
	}
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLArrayRenderer.class, componentObj, MXML_ARRAY_REPLACE_MAPPING);
		
	}
	
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		
		AbstractMXMLUIArray componentMXML = (AbstractMXMLUIArray) componentObj;
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		
		String currBodyContentFilePath = componentMXML.getCurrBodyContentFilePath();
		String bodyContent = null;
		
		if(currBodyContentFilePath != null){
			Writer bodyContentWriter = componentMXML.getCurrBodyContentBufferedWriter();
			
			bodyContentWriter.flush();
			bodyContentWriter.close();
			bodyContent = writer.readFileContent(componentMXML.getCurrBodyContentFilePath());
			
		}
		
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, bodyContent);
		
		super.encodeEnd(context, componentObj);
		
	}
	
}
