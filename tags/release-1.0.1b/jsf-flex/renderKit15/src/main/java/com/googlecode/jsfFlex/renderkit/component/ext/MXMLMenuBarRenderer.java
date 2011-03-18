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
		family="javax.faces.MXMLSimpleBase",
		type="com.googlecode.jsfFlex.MXMLMenuBar"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="MenuBar",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="dataDescriptor"),
				@JsfFlexAttribute(attribute="dataProvider", byMethod=true),
				@JsfFlexAttribute(attribute="iconField"),
				@JsfFlexAttribute(attribute="labelField"),
				@JsfFlexAttribute(attribute="labelFunction"),
                @JsfFlexAttribute(attribute="menuBarItemRenderer"),
				@JsfFlexAttribute(attribute="menubarItems"),
				@JsfFlexAttribute(attribute="menus"),
				@JsfFlexAttribute(attribute="selectedIndex", byMethod=true),
				@JsfFlexAttribute(attribute="showRoot"),
				@JsfFlexAttribute(attribute="backgroundAlpha"),
				@JsfFlexAttribute(attribute="backgroundColor"),
				@JsfFlexAttribute(attribute="backgroundSkin"),
				@JsfFlexAttribute(attribute="borderColor"),
				@JsfFlexAttribute(attribute="color"),
				@JsfFlexAttribute(attribute="cornerRadius"),
				@JsfFlexAttribute(attribute="disabledColor"),
				@JsfFlexAttribute(attribute="fillAlphas"),
				@JsfFlexAttribute(attribute="fillColors"),
				@JsfFlexAttribute(attribute="focusAlpha"),
				@JsfFlexAttribute(attribute="focusRoundedCorners"),
				@JsfFlexAttribute(attribute="fontAntiAliasType"),
				@JsfFlexAttribute(attribute="fontFamily"),
				@JsfFlexAttribute(attribute="fontGridFitType"),
				@JsfFlexAttribute(attribute="fontSharpness"),
				@JsfFlexAttribute(attribute="fontSize"),
				@JsfFlexAttribute(attribute="fontStyle"),
				@JsfFlexAttribute(attribute="fontThickness"),
				@JsfFlexAttribute(attribute="fontWeight"),
				@JsfFlexAttribute(attribute="highlightAlphas"),
				@JsfFlexAttribute(attribute="itemDownSkin"),
				@JsfFlexAttribute(attribute="itemOverSkin"),
				@JsfFlexAttribute(attribute="itemUpSkin"),
				@JsfFlexAttribute(attribute="leading"),
                @JsfFlexAttribute(attribute="menuStyleName"),
                @JsfFlexAttribute(attribute="rollOverColor"),
				@JsfFlexAttribute(attribute="selectionColor"),
				@JsfFlexAttribute(attribute="textAlign"),
				@JsfFlexAttribute(attribute="textDecoration"),
				@JsfFlexAttribute(attribute="textIndent"),
				@JsfFlexAttribute(attribute="itemClick"),
				@JsfFlexAttribute(attribute="itemRollOut"),
				@JsfFlexAttribute(attribute="itemRollOver"),
				@JsfFlexAttribute(attribute="menuHide"),
				@JsfFlexAttribute(attribute="menuShow")
		}
)
public final class MXMLMenuBarRenderer extends MXMLComponentRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = _MXMLContract.class.cast( componentObj );
		
		AbstractMXMLResponseWriter writer = AbstractMXMLResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(MXMLMenuBarRenderer.class, componentObj, null);
		writer.createPreMxml(componentMXML, MXMLMenuBarRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), 
				null);
		
	}
	
}