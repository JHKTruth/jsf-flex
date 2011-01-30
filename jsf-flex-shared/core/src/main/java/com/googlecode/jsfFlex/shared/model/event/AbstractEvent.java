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

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractEvent {
    
    public enum ASYNCHRONOUS_VARIABLES {
        RESULT_CODE
    }
    
    private final String _targetComponentId;
    
    AbstractEvent(){
        super();
        
        _targetComponentId = null;
    }
    
    public AbstractEvent(String targetComponentId){
        super();
        
        _targetComponentId = targetComponentId;
    }
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof AbstractEvent)){
            return false;
        }
        
        AbstractEvent currInstance = AbstractEvent.class.cast( instance );
        
        return _targetComponentId.equals(currInstance._targetComponentId);
    }
    
    @Override
    public int hashCode() {
        
        return _targetComponentId.hashCode();
    }
    
    public String getTargetComponentId(){
        return _targetComponentId;
    }
    
}
