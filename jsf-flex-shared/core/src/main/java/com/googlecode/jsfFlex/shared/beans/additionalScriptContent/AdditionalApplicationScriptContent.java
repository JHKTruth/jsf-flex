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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;

/**
 * @author Ji Hoon Kim
 */
public final class AdditionalApplicationScriptContent {
	
	private final Set _actionScriptImports;
	private final Set _simpleDataProviderSetter;
	private final Map _dataGridScriptContent;
	private final ValidationManagerScriptContent _validationManagerScriptContent;
	
	public AdditionalApplicationScriptContent(String currMxml, _MXMLApplicationContract currApplicationContract){
		super();
		_actionScriptImports = new LinkedHashSet();
		_simpleDataProviderSetter = new LinkedHashSet();
		_dataGridScriptContent = new HashMap();
		_validationManagerScriptContent = new ValidationManagerScriptContent(currMxml, currApplicationContract);
	}
	
	public void addActionScriptImport(String actionScriptImport){
		_actionScriptImports.add(actionScriptImport);
	}
	
	public void addSimpleDataProviderSetter(String componentId, String dataProviderContent){
		_simpleDataProviderSetter.add(new SimpleDataProviderSetter(componentId, dataProviderContent));
	}
	
	public void addDataGridScriptContent(String dataGridId, Integer batchColumnDataRetrievalSize, Integer maxDataPartitionIndex){
		_dataGridScriptContent.put(dataGridId, new DataGridScriptContent(dataGridId, batchColumnDataRetrievalSize, maxDataPartitionIndex));
	}
	
	public void addDataGridColumnToDataGridScriptContent(String dataGridId, String dataGridColumnId, String dataField, Boolean columnEditable){
		DataGridScriptContent dataGridScriptContentInstance;
		if((dataGridScriptContentInstance = (DataGridScriptContent) _dataGridScriptContent.get(dataGridId)) == null){
			throw new IllegalStateException("DataGridScriptContent doesn't exist for " + dataGridId + 
												" : addDataGridScriptContent should be invoked prior to this method call");							
		}
		
		dataGridScriptContentInstance.addDataGridColumnContent(dataGridColumnId, dataField, columnEditable);
	}
	
	public void addValidationManagerValidatorId(String validatorId){
		_validationManagerScriptContent.addValidationManagerValidatorId(validatorId);
	}
	
	public Set getActionScriptImports() {
		return new HashSet(_actionScriptImports);
	}
	public Set getSimpleDataProviderSetter() {
		return new HashSet(_simpleDataProviderSetter);
	}
	public Map getDataGridScriptContent() {
		return new HashMap(_dataGridScriptContent);
	}
	public ValidationManagerScriptContent getValidationManagerScriptContent() {
		return _validationManagerScriptContent;
	}
	
}
