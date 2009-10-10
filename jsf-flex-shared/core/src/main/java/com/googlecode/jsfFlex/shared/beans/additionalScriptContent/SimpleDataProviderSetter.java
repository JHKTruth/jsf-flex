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
	
	private final String _componentId;
	private final String _dataProviderContent;
	
	SimpleDataProviderSetter(String componentId, String dataProviderContent){
		super();
		_componentId = componentId;
		_dataProviderContent = dataProviderContent;
	}
	
	public String getComponentId() {
		return _componentId;
	}
	public String getDataProviderContent() {
		return _dataProviderContent;
	}
	
    @Override
	public boolean equals(Object instance) {
		if(!(instance instanceof SimpleDataProviderSetter)){
			return false;
		}
		
		SimpleDataProviderSetter simpleDataProviderSetter = (SimpleDataProviderSetter) instance;
		return _componentId.equals(simpleDataProviderSetter._componentId);
	}
    
    @Override
	public int hashCode() {
		return _componentId.hashCode();
	}
	
}
