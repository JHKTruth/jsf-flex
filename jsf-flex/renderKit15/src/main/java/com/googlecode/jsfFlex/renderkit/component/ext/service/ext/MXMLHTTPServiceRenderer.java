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
package com.googlecode.jsfFlex.renderkit.component.ext.service.ext;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLSimple",
		type="com.googlecode.jsfFlex.MXMLHTTPService"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="HTTPService",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="concurrency"),
				@JsfFlexAttribute(attribute="contentType"),
				@JsfFlexAttribute(attribute="destination"),
				@JsfFlexAttribute(attribute="id", byMethod=true),
				@JsfFlexAttribute(attribute="method"),
				@JsfFlexAttribute(attribute="resultFormat"),
				@JsfFlexAttribute(attribute="showBusyCursor"),
				@JsfFlexAttribute(attribute="makeObjectsBindable"),
				@JsfFlexAttribute(attribute="url"),
				@JsfFlexAttribute(attribute="useProxy"),
				@JsfFlexAttribute(attribute="xmlEncode"),
				@JsfFlexAttribute(attribute="xmlDecode"),
				@JsfFlexAttribute(attribute="fault"),
				@JsfFlexAttribute(attribute="result")
		}
)
public final class MXMLHTTPServiceRenderer extends MXMLComponentBaseRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
        _MXMLContract componentMXML = _MXMLContract.class.cast( componentObj );
        
        AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLHTTPServiceRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLHTTPServiceRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}
	
}
