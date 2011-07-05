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
package com.googlecode.jsfFlex.renderkit.component.ext;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLSimple",
		type="com.googlecode.jsfFlex.MXMLVideoDisplay"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="VideoDisplay",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="autoBandWidthDetection", byMethod=false),
				@JsfFlexAttribute(attribute="autoPlay", byMethod=false),
				@JsfFlexAttribute(attribute="autoRewind", byMethod=false),
				@JsfFlexAttribute(attribute="bufferTime", byMethod=false),
				@JsfFlexAttribute(attribute="cuePointManagerClass", byMethod=false),
				@JsfFlexAttribute(attribute="cuePoints", byMethod=false),
				@JsfFlexAttribute(attribute="idleTimeout", byMethod=false),
				@JsfFlexAttribute(attribute="live", byMethod=false),
				@JsfFlexAttribute(attribute="maintainAspectRatio", byMethod=false),
				@JsfFlexAttribute(attribute="playheadTime", byMethod=false),
				@JsfFlexAttribute(attribute="playheadUpdateInterval", byMethod=false),
				@JsfFlexAttribute(attribute="progressInterval", byMethod=false),
				@JsfFlexAttribute(attribute="source", byMethod=false),
				@JsfFlexAttribute(attribute="totalTime", byMethod=false),
				@JsfFlexAttribute(attribute="volume", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundAlpha", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundColor", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundImage", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundSize", byMethod=false),
				@JsfFlexAttribute(attribute="borderColor", byMethod=false),
				@JsfFlexAttribute(attribute="borderSides", byMethod=false),
				@JsfFlexAttribute(attribute="borderSkin", byMethod=false),
				@JsfFlexAttribute(attribute="borderStyle", byMethod=false),
				@JsfFlexAttribute(attribute="borderThickness", byMethod=false),
				@JsfFlexAttribute(attribute="cornerRadius", byMethod=false),
				@JsfFlexAttribute(attribute="dropShadowColor", byMethod=false),
				@JsfFlexAttribute(attribute="dropShadowEnabled", byMethod=false),
				@JsfFlexAttribute(attribute="shadowDirection", byMethod=false),
				@JsfFlexAttribute(attribute="shadowDistance", byMethod=false),
				@JsfFlexAttribute(attribute="close", byMethod=false),
				@JsfFlexAttribute(attribute="complete", byMethod=false),
				@JsfFlexAttribute(attribute="cuePoint", byMethod=false),
				@JsfFlexAttribute(attribute="playheadUpdate", byMethod=false),
				@JsfFlexAttribute(attribute="progress", byMethod=false),
				@JsfFlexAttribute(attribute="ready", byMethod=false),
				@JsfFlexAttribute(attribute="rewind", byMethod=false),
				@JsfFlexAttribute(attribute="stateChange", byMethod=false)
		}
)
public final class MXMLVideoDisplayRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLVideoDisplayRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLVideoDisplayRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
