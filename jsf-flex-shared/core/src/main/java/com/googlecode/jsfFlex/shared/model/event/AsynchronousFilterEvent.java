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

import javax.faces.component.UIComponent;

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public class AsynchronousFilterEvent extends AbstractEvent {
    
    private final UIComponent _targetComponent;
    private final String _componentValue;
    private final String _filterValue;
    
    public AsynchronousFilterEvent(UIComponent targetComponent, String componentValue, String filterValue) {
        super(targetComponent.getId());
        
        _targetComponent = targetComponent;
        _componentValue = componentValue;
        _filterValue = filterValue;
        
    }
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof AsynchronousFilterEvent)){
            return false;
        }
        
        boolean equals = super.equals(instance);
        if(!equals){
            return false;
        }
        
        AsynchronousFilterEvent currInstance = AsynchronousFilterEvent.class.cast( instance );
        return _targetComponent.equals(currInstance._targetComponent) && _componentValue.equals(currInstance._componentValue)
                && _filterValue.equals(currInstance._filterValue);
    }
    
    @Override
    public int hashCode() {
        int hashCodeVal = super.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _targetComponent.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _componentValue.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _filterValue.hashCode();
        
        return hashCodeVal;
    }
    
    @Override
    public String toString() {
    	StringBuilder content = new StringBuilder();
    	content.append("{ componentValue: " + _componentValue);
    	content.append(", filterValue: " + _filterValue);
    	content.append(", targetComponent: " + _targetComponent);
    	
    	return content.toString();
    }
    
    public UIComponent getTargetComponent() {
        return _targetComponent;
    }
    
    public String getComponentValue() {
        return _componentValue;
    }
    
    public String getFilterValue() {
        return _filterValue;
    }
    
}
