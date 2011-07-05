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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.component.ext.data.ext.AbstractMXMLUIObject;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.AbstractMXMLUIObjectStaticProperty;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLSimple",
		type="com.googlecode.jsfFlex.MXMLObject"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="Object",
		mxmlComponentNodeAttributes={},
		
		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="id", byMethod=true)
		}
)
public final class MXMLObjectRenderer extends MXMLComponentBaseRenderer {
	
	@SuppressWarnings("unchecked")
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLObjectRenderer.class, componentObj, null);
		
		Set tokenValueSet = componentMXML.getAnnotationDocletParserInstance().getTokenValueSet();
		List children = componentObj.getChildren();
		for(Iterator iterate = children.iterator(); iterate.hasNext();){
			AbstractMXMLUIObjectStaticProperty currObjectProperty = (AbstractMXMLUIObjectStaticProperty) iterate.next();
			tokenValueSet.add(new TokenValue(currObjectProperty.getStaticPropertyName(), currObjectProperty.getStaticPropertyValue()));
		}
		
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		
		AbstractMXMLUIObject componentMXML = (AbstractMXMLUIObject) componentObj;
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		
		String currBodyContentFilePath = componentMXML.getCurrBodyContentFilePath();
		String bodyContent = null;
		
		if(currBodyContentFilePath != null){
			Writer bodyContentWriter = componentMXML.getCurrBodyContentBufferedWriter();
			
			bodyContentWriter.flush();
			bodyContentWriter.close();
			bodyContent = writer.readFileContent(componentMXML.getCurrBodyContentFilePath());
			
		}
		
		writer.createPreMxml(componentMXML, MXMLObjectRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				bodyContent);
		
		super.encodeEnd(context, componentObj);
		
	}
	
}
