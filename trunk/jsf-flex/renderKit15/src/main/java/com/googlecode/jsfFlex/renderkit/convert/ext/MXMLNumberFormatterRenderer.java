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
package com.googlecode.jsfFlex.renderkit.convert.ext;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.convert.MXMLFormatterTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.MXMLResponseWriterImpl;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLInput",
		type="com.googlecode.jsfFlex.MXMLNumberFormatter"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="NumberFormatter",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="decimalSeparatorFrom", byMethod=false),
				@JsfFlexAttribute(attribute="decimalSeparatorTo", byMethod=false),
				@JsfFlexAttribute(attribute="precision", byMethod=false),
				@JsfFlexAttribute(attribute="rounding", byMethod=false),
				@JsfFlexAttribute(attribute="thousandsSeparatorFrom", byMethod=false),
				@JsfFlexAttribute(attribute="thousandsSeparatorTo", byMethod=false),
				@JsfFlexAttribute(attribute="useNegativeSign", byMethod=false),
				@JsfFlexAttribute(attribute="useThousandsSeparator", byMethod=false)
		}
)
public final class MXMLNumberFormatterRenderer extends MXMLFormatterTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		MXMLResponseWriterImpl writer = (MXMLResponseWriterImpl) context.getResponseWriter();
		writer.mapFields(MXMLNumberFormatterRenderer.class, componentObj, null);
		writer.createPreMxml(writer, componentMXML, MXMLNumberFormatterRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}