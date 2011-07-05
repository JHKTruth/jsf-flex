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

import com.googlecode.jsfFlex.renderkit.annotation.FlexComponentNodeAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLListTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLInput",
		type="com.googlecode.jsfFlex.MXMLTree"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="Tree",
		mxmlComponentPackage="mx.controls",
		mxmlComponentNodeAttributes={
				@FlexComponentNodeAttribute(
						htmlType="input",
						typeAttributeValue="hidden",
						valueAttributeValue="selectedIndex",
						isValueDynamic=true,
						isValueNested=false,
						valueNestedValues={},
						nameAttributeValue="id",
						isNameDynamic=true,
						nameAppend="_selectedIndex")
		},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="dataDescriptor", byMethod=false),
				@JsfFlexAttribute(attribute="firstVisibleItem", byMethod=false),
				@JsfFlexAttribute(attribute="itemIcons", byMethod=false),
				@JsfFlexAttribute(attribute="openItems", byMethod=false),
				@JsfFlexAttribute(attribute="showRoot", byMethod=false),
				@JsfFlexAttribute(attribute="defaultLeafIcon", byMethod=false),
				@JsfFlexAttribute(attribute="depthColors", byMethod=false),
				@JsfFlexAttribute(attribute="disclosureClosedIcon", byMethod=false),
				@JsfFlexAttribute(attribute="disclosureOpenIcon", byMethod=false),
				@JsfFlexAttribute(attribute="folderClosedIcon", byMethod=false),
				@JsfFlexAttribute(attribute="folderOpenIcon", byMethod=false),
				@JsfFlexAttribute(attribute="indentation", byMethod=false),
				@JsfFlexAttribute(attribute="openDuration", byMethod=false),
				@JsfFlexAttribute(attribute="openEasingFunction", byMethod=false),
				@JsfFlexAttribute(attribute="itemClose", byMethod=false),
				@JsfFlexAttribute(attribute="itemOpen", byMethod=false),
				@JsfFlexAttribute(attribute="itemOpening", byMethod=false)
		}
)
public final class MXMLTreeRenderer extends MXMLListTemplateRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.mapFields(MXMLTreeRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLTreeRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}

}
