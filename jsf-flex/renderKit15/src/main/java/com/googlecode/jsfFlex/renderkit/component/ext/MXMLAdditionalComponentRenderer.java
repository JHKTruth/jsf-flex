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
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.component.ext.AbstractAdditionalComponent;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
        renderKitId="MXML_BASIC",
        family="javax.faces.MXMLSimple",
        type="com.googlecode.jsfFlex.MXMLAdditionalComponent"
)
public final class MXMLAdditionalComponentRenderer extends MXMLComponentBaseRenderer {
    
    private final static Log _log = LogFactory.getLog(MXMLAdditionalComponentRenderer.class);
    
    /* 
     * Have to take care of attributes in a form of a HACK. So will fetch the corresponding component's attributes from a Map<br>
     * and create TokenValue objects from it. Also will fetch the corresponding component's name which will be passed to the createPreMxml method. <br>
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeBegin(context, componentObj);
        
        AbstractAdditionalComponent additionalComponent = (AbstractAdditionalComponent) componentObj;
        AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
        
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
                _log.warn("JSONException thrown while parsing of " + componentAttributesJSONFormat);
            }
        }
        
        writer.createPreMxml(additionalComponent, additionalComponent.getComponentName(), null);
        
    }
    
}
