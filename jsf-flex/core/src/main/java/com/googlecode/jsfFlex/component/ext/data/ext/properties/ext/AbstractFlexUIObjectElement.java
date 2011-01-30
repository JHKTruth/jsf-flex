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
package com.googlecode.jsfFlex.component.ext.data.ext.properties.ext;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.component.ext.data.ext.AbstractFlexUIObject;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.AbstractFlexUIDataObjectBase;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * Since this component is out of the norm in relation to writing Flex content, it will perform <br>
 * the write of Flex content within the component rather than within a Renderer [meaning Renderer does <br>
 * not exist for this component]. Also when stated that it is writing Flex content, it technically is <br>
 * writing to AbstractFlexUIDataContainerBase's BufferedWriter.<br>
 * 
 * <ul>
 * This component can have following types of children :
 * 		<li> AbstractFlexUIObjectProperty </li>
 * 		<li> AbstractFlexUIObjectListEntries </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexObjectElement",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.FlexUIObjectElement",
        type                =   "com.googlecode.jsfFlex.FlexUIObjectElement",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.data.ext.properties.ext.FlexUIObjectElementTag",
        family              =   "javax.faces.FlexProperty"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIObjectElement")
public abstract class AbstractFlexUIObjectElement 
						extends AbstractFlexUIDataObjectBase 
                        implements IFlexUIBaseAttributes {
	
    private final static Log _log = LogFactory.getLog(AbstractFlexUIObjectElement.class);
    
	private static final String OBJECT_START_TAG = "<fx:Object";
	private static final String OBJECT_START_TAG_CLOSER = ">";
	private static final String OBJECT_END_TAG = "</fx:Object>";
	
    @Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        
        if(!flexContext.isProductionEnv()){
    		Map<String, ? super UIComponentBase> temporaryResourceMap = flexContext.getTemporaryResourceMap();
    		AbstractFlexUIObject currObjectContainerRef = AbstractFlexUIObject.class.cast( temporaryResourceMap.get(AbstractFlexUIObject.CURR_FLEX_UI_OBJECT_CONTAINER_KEY) );
    		
    		StringBuilder objectStartTagBuffer = new StringBuilder();
    		objectStartTagBuffer.append(OBJECT_START_TAG);
    		
            Map<String, ? extends Object> componentAttributeMap = getComponentAttributes();
            if(componentAttributeMap != null){
                for(String attributeName : componentAttributeMap.keySet()){
                    String attributeValue = componentAttributeMap.get(attributeName).toString();
                    appendAttributeNameValue(objectStartTagBuffer, attributeName, attributeValue);
                }
            }
            
            String attributesJSONFormat = getComponentAttributesJSONFormat();
            if(attributesJSONFormat != null && attributesJSONFormat.trim().length() > 0){
                try{
                    JSONObject parsedJSONObject = new JSONObject(attributesJSONFormat);
                    JSONArray attributeName = parsedJSONObject.names();
                    
                    for(int i=0; i < attributeName.length(); i++){
                        String currKey = attributeName.get(i).toString();
                        String currValue = parsedJSONObject.getString(currKey);
                        
                        if(currValue != null){
                            appendAttributeNameValue(objectStartTagBuffer, currKey, currValue);
                        }
                    }
                }catch(JSONException jsonException){
                    _log.error("Error while parsing the following String to JSONObject : " + attributesJSONFormat);
                    throw new ComponentBuildException(jsonException);
                }
            }
            
    		objectStartTagBuffer.append( processDataObjectProperties() );
    		
    		objectStartTagBuffer.append(OBJECT_START_TAG_CLOSER);
    		
    		//now the start tag has been generated so write to the buffer
    		currObjectContainerRef.getCurrBodyContentBufferedWriter().write(objectStartTagBuffer.toString());
        }
	}
	
    @Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
		
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        if(!flexContext.isProductionEnv()){
    		Map<String, ? super UIComponentBase> temporaryResourceMap = flexContext.getTemporaryResourceMap();
    		AbstractFlexUIObject currObjectContainerRef = AbstractFlexUIObject.class.cast( temporaryResourceMap.get(AbstractFlexUIObject.CURR_FLEX_UI_OBJECT_CONTAINER_KEY) );
    		
    		currObjectContainerRef.getCurrBodyContentBufferedWriter().write(OBJECT_END_TAG);
        }
	}
    
    private void appendAttributeNameValue(StringBuilder content, String attributeName, String attributeValue){
        content.append(" ");
        content.append(attributeName);
        content.append("=");
        content.append(attributeValue);
    }
	
}
