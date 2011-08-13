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
package com.googlecode.jsfFlex.shared.model.event;

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public class AsynchronousPropertyUpdateEvent extends AsynchronousGlueEvent {
    
    private String _currentSourceValue;
    
    private AsynchronousPropertyUpdateEvent(){
        super();
    }
    
    public AsynchronousPropertyUpdateEvent(String currentSourceValue, String sourceId, String targetId){
        super(sourceId, targetId);
        
        _currentSourceValue = currentSourceValue;
    }
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof AsynchronousPropertyUpdateEvent)){
            return false;
        }
        
        boolean equals = super.equals(instance);
        if(!equals){
            return false;
        }
        
        AsynchronousPropertyUpdateEvent currInstance = AsynchronousPropertyUpdateEvent.class.cast( instance );
        return _currentSourceValue.equals(currInstance._currentSourceValue);
    }
    
    @Override
    public int hashCode() {
        int hashCodeVal = super.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _currentSourceValue.hashCode();
        
        return hashCodeVal;
    }
    
    @Override
    public String toString() {
    	StringBuilder content = new StringBuilder();
    	content.append("{ currentSourceValue: " + _currentSourceValue);
    	content.append(", sourceComponentId: " + getSourceComponentId());
    	content.append(", targetComponentId: " + getTargetComponentId());
    	content.append(" }");
    	
    	return content.toString();
    }
    
    public String getCurrSourceValue(){
        return _currentSourceValue;
    }
    
}
