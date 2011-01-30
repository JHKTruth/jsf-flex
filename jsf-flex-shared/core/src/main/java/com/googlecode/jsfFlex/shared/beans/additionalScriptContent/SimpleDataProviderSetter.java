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
package com.googlecode.jsfFlex.shared.beans.additionalScriptContent;

/**
 * @author Ji Hoon Kim
 */
public final class SimpleDataProviderSetter {
	
    public enum DATA_PROVIDER_TYPE {
        COMBO_BOX("ComboBox", "spark.components.ComboBox", "mx.collections.IList");
        
        private final String _componentTypeCast;
        private final String _componentTypeVariableDeclaration;
        private final String _dataProviderCast;
        
        DATA_PROVIDER_TYPE(String componentTypeCast, String componentTypeVariableDeclaration, String dataProviderCast) {
            
            _componentTypeCast = componentTypeCast;
            _componentTypeVariableDeclaration = componentTypeVariableDeclaration;
            _dataProviderCast = dataProviderCast;
        }
        
        public String getComponentTypeCast() {
            return _componentTypeCast;
        }
        public String getComponentTypeVariableDeclaration() {
            return _componentTypeVariableDeclaration;
        }
        public String getDataProviderCast() {
            return _dataProviderCast;
        }
        
    }
    
	private final String _componentId;
    private final DATA_PROVIDER_TYPE _componentType;
	private final String _dataProviderContent;
	
	SimpleDataProviderSetter(String componentId, DATA_PROVIDER_TYPE componentType, String dataProviderContent){
		super();
		_componentId = componentId;
        _componentType = componentType;
        _dataProviderContent = dataProviderContent;
	}
	
	public String getComponentId() {
		return _componentId;
	}
    public String getComponentTypeCast() {
        return _componentType.getComponentTypeCast();
    }
    public String getComponentTypeVariableDeclaration() {
        return _componentType.getComponentTypeVariableDeclaration();
    }
	public String getDataProviderContent() {
		return _dataProviderContent;
	}
    public String getDataProviderCast() {
        return _componentType.getDataProviderCast();
    }
	
    @Override
	public boolean equals(Object instance) {
		if(!(instance instanceof SimpleDataProviderSetter)){
			return false;
		}
		
		SimpleDataProviderSetter simpleDataProviderSetter = SimpleDataProviderSetter.class.cast( instance );
		return _componentId.equals(simpleDataProviderSetter._componentId);
	}
    
    @Override
	public int hashCode() {
		return _componentId.hashCode();
	}
	
}
