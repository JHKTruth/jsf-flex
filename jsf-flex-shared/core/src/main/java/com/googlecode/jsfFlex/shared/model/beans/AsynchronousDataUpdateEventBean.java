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
package com.googlecode.jsfFlex.shared.model.beans;

import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public class AsynchronousDataUpdateEventBean extends AbstractAsynchronousEventBean {
    
    protected static final String UPDATE_VALUE_ATTRIBUTE = "UPDATE_VALUE_ATTRIBUTE";
    
    private final String _updateValue;
    
    protected AsynchronousDataUpdateEventBean(){
        super();
        
        _updateValue = null;
    }
    
    public AsynchronousDataUpdateEventBean(String updateValue, String sourceId, String targetId){
        super(sourceId, targetId);
        
        _updateValue = updateValue;
    }
    
    @Override
    public JSONObject formatResponseToJSON() throws JSONException {
        JSONObject response = new JSONObject();
        
        response.put(JSON_ATTRIBUTE_VALUES.SOURCE_COMPONENT_ID_ATTRIBUTE.toString(), getSourceComponentId());
        response.put(JSON_ATTRIBUTE_VALUES.TARGET_COMPONENT_ID_ATTRIBUTE.toString(), getTargetComponentId());
        response.put(UPDATE_VALUE_ATTRIBUTE, _updateValue);
        
        return response;
    }
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof AsynchronousDataUpdateEventBean)){
            return false;
        }
        
        boolean equals = super.equals(instance);
        if(!equals){
            return false;
        }
        
        AsynchronousDataUpdateEventBean currInstance = AsynchronousDataUpdateEventBean.class.cast( instance );
        return _updateValue.equals(currInstance._updateValue);
    }
    
    @Override
    public int hashCode() {
        int hashCodeVal = super.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _updateValue.hashCode();
        
        return hashCodeVal;
    }
    
    public String getUpdateValue(){
        return _updateValue;
    }
    
}
