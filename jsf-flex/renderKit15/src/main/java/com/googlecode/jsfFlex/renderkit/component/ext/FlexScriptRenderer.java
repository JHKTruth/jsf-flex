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
import javax.faces.render.FacesRenderer;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
        renderKitId="FLEX_BASIC",
        family="javax.faces.FlexSimple",
        type="com.googlecode.jsfFlex.FlexScript"
)
@FacesRenderer(
		renderKitId="FLEX_BASIC",
		componentFamily="javax.faces.FlexSimple",
		rendererType="com.googlecode.jsfFlex.FlexScript"
)
@IJsfFlexAttributeProperties(componentName="Script")
public final class FlexScriptRenderer extends AbstractFlexComponentBaseRenderer {
	
	private static final String FLEX_SCRIPT_BODY_TEMPLATE;
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = FlexScriptRenderer.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		FLEX_SCRIPT_BODY_TEMPLATE = packageName + "/templates/FlexScriptBody.template";
	}
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		
		String bodyContent = writer.getComponentTemplate(FlexScriptRenderer.class.getClassLoader(), FLEX_SCRIPT_BODY_TEMPLATE);

		writer.createPreMxml(componentFlex, FlexScriptRenderer.class.getAnnotation(IJsfFlexAttributeProperties.class), 
				bodyContent);
		
	}
	
}
