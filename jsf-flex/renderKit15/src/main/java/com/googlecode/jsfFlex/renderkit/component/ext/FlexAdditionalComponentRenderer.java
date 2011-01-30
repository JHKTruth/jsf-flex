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
import java.lang.annotation.Annotation;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.component.ext.AbstractFlexUIAdditionalComponent;
import com.googlecode.jsfFlex.renderkit.annotation.IFlexComponentNodeAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.beans.templates.TokenValue;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
        renderKitId="FLEX_BASIC",
        family="javax.faces.FlexSimple",
        type="com.googlecode.jsfFlex.FlexAdditionalComponent"
)
@FacesRenderer(
        renderKitId="FLEX_BASIC",
        componentFamily="javax.faces.FlexSimple",
        rendererType="com.googlecode.jsfFlex.FlexAdditionalComponent"
)
public final class FlexAdditionalComponentRenderer extends AbstractFlexComponentBaseRenderer {
    
    private final static Log _log = LogFactory.getLog(FlexAdditionalComponentRenderer.class);
    
    private static final IFlexComponentNodeAttribute[] FLEX_COMPONENT_NODE_ATTRIBUTE_ARRAY = new IFlexComponentNodeAttribute[0];
    private static final IJsfFlexAttribute[] JSF_FLEX_ATTRIBUTE_ARRAY = new IJsfFlexAttribute[0];
    
    /* 
     * Have to take care of attributes in a form of a HACK. So will fetch the corresponding component's attributes from a Map<br>
     * and create TokenValue objects from it. Also will fetch the corresponding component's name which will be passed to the createPreMxml method. <br>
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeBegin(context, componentObj);
        
        AbstractFlexUIAdditionalComponent additionalComponent = AbstractFlexUIAdditionalComponent.class.cast( componentObj );
        AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
        
        Map<String, ? super Object> componentAttributes = additionalComponent.getComponentAttributes();
        if(componentAttributes != null){
            for(String currKey : componentAttributes.keySet()){
                Object currValue = componentAttributes.get(currKey);
                if(currValue != null){
                    //HACK for the special component
                    additionalComponent.getAnnotationDocletParserInstance().getTokenValueSet().add(new TokenValue(currKey, currValue.toString()));
                }
            }
        }
        
        String componentAttributesJSONFormat = additionalComponent.getComponentAttributesJSONFormat();
        if(componentAttributesJSONFormat != null && componentAttributesJSONFormat.trim().length() > 0){
            try{
                JSONObject parsedJSONObject = new JSONObject(componentAttributesJSONFormat);
                JSONArray attributeName = parsedJSONObject.names();
                
                for(int i=0; i < attributeName.length(); i++){
                    String currKey = attributeName.get(i).toString();
                    String currValue = parsedJSONObject.getString(currKey);
                    
                    if(currValue != null){
                        //HACK for the special component
                        additionalComponent.getAnnotationDocletParserInstance().getTokenValueSet().add(new TokenValue(currKey, currValue.toString()));
                    }
                }
            }catch(JSONException jsonException){
                _log.error("Error while parsing the following String to JSONObject : " + componentAttributesJSONFormat);
                throw new ComponentBuildException(jsonException);
            }
        }
        
        final String componentName = additionalComponent.getComponentName();
        final String componentNameSpace = additionalComponent.getComponentNameSpace();
        
        IJsfFlexAttributeProperties jsfFlexAttributeProperties = new IJsfFlexAttributeProperties(){
            
            /*
             * TODO:
             *  Allow users to provide values for componentNodeAttributes, which will be read during 
             *  Runtime time NOT Build time to create componentValueMapper.xml. In another words, 
             *  allow user to map the values back to the bean for custom components.
             */
            
            public String componentName() {
                return componentName;
            }
            
            public String componentNameSpace() {
                return componentNameSpace;
            }
            
            public IFlexComponentNodeAttribute[] componentNodeAttributes() {
                return FLEX_COMPONENT_NODE_ATTRIBUTE_ARRAY;
            }
            
            public String[] componentPackages() {
                return null;
            }
            
            public IJsfFlexAttribute[] jsfFlexAttributes() {
                return JSF_FLEX_ATTRIBUTE_ARRAY;
            }
            
            public Class<? extends Annotation> annotationType() {
                return IJsfFlexAttributeProperties.class;
            }
            
        };
        
        writer.createPreMxml(additionalComponent, jsfFlexAttributeProperties, null);
        
    }
    
}
