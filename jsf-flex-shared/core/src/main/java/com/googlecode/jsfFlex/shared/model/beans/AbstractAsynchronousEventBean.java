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
abstract class AbstractAsynchronousEventBean {
    
    enum JSON_ATTRIBUTE_VALUES {
        SOURCE_COMPONENT_ID_ATTRIBUTE,
        TARGET_COMPONENT_ID_ATTRIBUTE
    }
    
    private final String _sourceComponentId;
    private final String _targetComponentId;
    
    AbstractAsynchronousEventBean(){
        super();
        
        _sourceComponentId = null;
        _targetComponentId = null;
    }
    
    AbstractAsynchronousEventBean(String sourceComponentId, String targetComponentId){
        super();
        
        _sourceComponentId = sourceComponentId;
        _targetComponentId = targetComponentId;
    }
    
    public abstract JSONObject formatResponseToJSON() throws JSONException;
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof AbstractAsynchronousEventBean)){
            return false;
        }
        
        AbstractAsynchronousEventBean currInstance = AbstractAsynchronousEventBean.class.cast( instance );
        boolean equals = _sourceComponentId.equals(currInstance._sourceComponentId);
        
        if(equals){
            equals = _targetComponentId.equals(currInstance._targetComponentId);
        }
        
        return equals;
    }
    
    @Override
    public int hashCode() {
        int hashCodeVal = FlexConstants.HASH_CODE_INIT_VALUE;
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _sourceComponentId.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _targetComponentId.hashCode();
        
        return hashCodeVal;
    }
    
    public String getSourceComponentId(){
        return _sourceComponentId;
    }
    
    public String getTargetComponentId(){
        return _targetComponentId;
    }
    
}
