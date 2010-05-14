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

import com.googlecode.jsfFlex.component.ext.data.ext.AbstractFlexUIObject;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.AbstractFlexUIObjectStaticProperty;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="FLEX_BASIC",
		family="javax.faces.FlexSimple",
		type="com.googlecode.jsfFlex.FlexObject"
)
@IJsfFlexAttributeProperties(
		componentName="Object",
		componentNodeAttributes={},
		
		jsfFlexAttributes={
				@IJsfFlexAttribute(attribute="id", byMethod=true)
		}
)
public final class FlexObjectRenderer extends AbstractFlexComponentBaseRenderer {
	
	@SuppressWarnings("unchecked")
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.mapFields(FlexObjectRenderer.class, componentObj, null);
		
		Set<TokenValue> tokenValueSet = componentFlex.getAnnotationDocletParserInstance().getTokenValueSet();
		List children = componentObj.getChildren();
		for(Iterator iterate = children.iterator(); iterate.hasNext();){
			AbstractFlexUIObjectStaticProperty currObjectProperty = AbstractFlexUIObjectStaticProperty.class.cast( iterate.next() );
			tokenValueSet.add(new TokenValue(currObjectProperty.getStaticPropertyName(), currObjectProperty.getStaticPropertyValue()));
		}
		
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		
		AbstractFlexUIObject componentFlex = AbstractFlexUIObject.class.cast( componentObj );
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		
		String currBodyContentFilePath = componentFlex.getCurrBodyContentFilePath();
		String bodyContent = null;
		
		if(currBodyContentFilePath != null){
			Writer bodyContentWriter = componentFlex.getCurrBodyContentBufferedWriter();
			
			bodyContentWriter.flush();
			bodyContentWriter.close();
			bodyContent = writer.readFileContent(componentFlex.getCurrBodyContentFilePath());
			
		}
		
		writer.createPreMxml(componentFlex, FlexObjectRenderer.class.getAnnotation(IJsfFlexAttributeProperties.class).componentName(), 
				bodyContent);
		
		super.encodeEnd(context, componentObj);
		
	}
	
}
