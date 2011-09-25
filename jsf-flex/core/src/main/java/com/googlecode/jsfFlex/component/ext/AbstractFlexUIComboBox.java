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
package com.googlecode.jsfFlex.component.ext;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUIDataProviderCollectionAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITextAttribute;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent.ACTION_SCRIPT_IMPORT;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.SimpleDataProviderSetter.DATA_PROVIDER_TYPE;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.FlexJsfUtil;

/**
 * AbstractFlexUIComboBox is a special case where the preserving of the state of the "text" field<br>
 * is held within the code. Main reason is because it extends FlexUISelectedIndexBase and there exists<br>
 * no reason to create an another base class to preserve both "selectedIndex" + "text".<br>
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexComboBox",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUIComboBox",
        type                =   "com.googlecode.jsfFlex.FlexUIComboBox",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUIComboBoxTag",
        family              =   "javax.faces.FlexInput",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexComboBox",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.AbstractFlexUIInputTagBase"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIComboBox")
public abstract class AbstractFlexUIComboBox 
						extends com.googlecode.jsfFlex.component.FlexUISelectedIndexBase
						implements IFlexUIBaseAttributes, IFlexUIDataProviderCollectionAttribute, IFlexUITextAttribute {
	
	private final static Log _log = LogFactory.getLog(AbstractFlexUIComboBox.class);
	
	private static final String DATA_PROPERTY = "data";
	private static final String LABEL_PROPERTY = "label";
	
	private static final String TEXT_ATTR = "text";
	private static final String TEXT_ID_APPENDED = "_text";
	
	private JSONObject initValue;
	
	{
		try{
			initValue = new JSONObject();
			initValue.put(ATTRIBUTE, TEXT_ATTR);
			
			_initValues.put(initValue);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
    @Override
	protected void populateComponentInitValues(){
		super.populateComponentInitValues();
		
		try{
			if(getText() != null){
				initValue.put(VALUE, FlexJsfUtil.escapeCharacters( getText() ));
			}
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
    @Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        
        if(!flexContext.isProductionEnv()){
    		Collection<? extends Object> dataProviderCollection = getDataProviderCollection();
    		if(dataProviderCollection != null && dataProviderCollection.size() > 0){
    			//For AbstractFlexUIComboBox, entries within the collection must be of type SelectItem
    			AdditionalApplicationScriptContent additionalApplicationScriptContent = flexContext.getAdditionalAppScriptContent();
    			additionalApplicationScriptContent.addActionScriptImport(ACTION_SCRIPT_IMPORT.COMBO_BOX_COMPONENT_AS);
    			additionalApplicationScriptContent.addActionScriptImport(ACTION_SCRIPT_IMPORT.MX_COLLECTIONS_ILIST_AS);
    			
    			JSONArray comboBoxContent = new JSONArray();
    			for(Object currInstace : dataProviderCollection){
    				SelectItem currSelectItem = SelectItem.class.cast( currInstace );
    				
    				JSONObject comboBoxEntry = new JSONObject();
    				
    				try{
    					comboBoxEntry.put(DATA_PROPERTY, currSelectItem.getValue().toString());
    					comboBoxEntry.put(LABEL_PROPERTY, currSelectItem.getLabel());
    					comboBoxContent.put(comboBoxEntry);
    				}catch(JSONException jsonException){
    					_log.info("Error setting the following content for dataProviderCollection " + 
    											currSelectItem.getValue() + currSelectItem.getLabel(), jsonException);
                        throw new ComponentBuildException(jsonException);
                    }
    				
    			}
    			
    			additionalApplicationScriptContent.addSimpleDataProviderSetter(getId(), DATA_PROVIDER_TYPE.COMBO_BOX, comboBoxContent.toString());
    			
    		}
        }
        
	}
	
    @Override
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String textId = getId() + TEXT_ID_APPENDED;
    	String textUpdateVal = requestMap.get(textId);
    	
    	if(textUpdateVal != null){
    		setText(textUpdateVal);
    	}
    }
    
    @Override
    public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueExpression ve = getValueExpression(TEXT_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getText());
    		setText(null);
    	}
    	
    }
	
}
