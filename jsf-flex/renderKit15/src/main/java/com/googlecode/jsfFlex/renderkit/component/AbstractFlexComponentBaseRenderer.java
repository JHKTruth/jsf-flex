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
package com.googlecode.jsfFlex.renderkit.component;

import java.io.IOException;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.renderkit.FlexRendererBase;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexAttributeNode;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.templates.TokenValue;

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractFlexComponentBaseRenderer extends FlexRendererBase {
    
    private final static Log _log = LogFactory.getLog(AbstractFlexComponentBaseRenderer.class);
	
    @Override
    public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeBegin(context, componentObj);
        
        IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
        Set<TokenValue> tokenValueSet = componentFlex.getAnnotationDocletParserInstance().getTokenValueSet();
        
        for(UIComponent currChild : componentObj.getChildren()){
            if(currChild instanceof IFlexAttributeNode){
                IFlexAttributeNode currAttributeNode = IFlexAttributeNode.class.cast( currChild );
                addTokenValue(tokenValueSet, currAttributeNode.getName(), currAttributeNode.getValue());
            }
        }
        
    }
    
    @Override
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeEnd(context, componentObj);
		
		IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
        
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		writer.getFlexTaskRunner().writeBodyContent(componentFlex);
        
	}
    
    private void addTokenValue(Set<TokenValue> tokenValueSet, String attributeName, String attributeValue){
        tokenValueSet.add(new TokenValue(attributeName, attributeValue));
    }
	
}
