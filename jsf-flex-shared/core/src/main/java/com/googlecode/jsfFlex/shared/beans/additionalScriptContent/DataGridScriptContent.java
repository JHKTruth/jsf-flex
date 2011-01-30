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

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ji Hoon Kim
 */
public final class DataGridScriptContent {
	
	private final String _dataGridId;
	private final Integer _batchColumnDataRetrievalSize;
	private final Integer _maxDataPartitionIndex;
	private final List<DataGridColumnScriptContent> _dataGridColumns;
    private final String _filterComponentId;
    private final String _filterColumnComponentId;
    private final String _filterEventListener;
	
	DataGridScriptContent(String dataGridId, Integer batchColumnDataRetrievalSize, Integer maxDataPartitionIndex, 
                            String filterComponentId, String filterColumnComponentId, String filterEventListener){
		super();
		_dataGridId = dataGridId;
		_batchColumnDataRetrievalSize = batchColumnDataRetrievalSize;
		_maxDataPartitionIndex = maxDataPartitionIndex;
		_filterComponentId = filterComponentId == null ? "" : filterComponentId;
        _filterColumnComponentId = filterColumnComponentId == null ? "" : filterColumnComponentId;
        _filterEventListener = filterEventListener == null ? "" : filterEventListener;
        _dataGridColumns = new LinkedList<DataGridColumnScriptContent>();
	}
	
	void addDataGridColumnContent(String dataGridColumnId, String dataField, Boolean columnEditable){
		_dataGridColumns.add(new DataGridColumnScriptContent(dataGridColumnId, dataField, columnEditable));
	}
	
	public String getDataGridId() {
		return _dataGridId;
	}
	public Integer getBatchColumnDataRetrievalSize() {
		return _batchColumnDataRetrievalSize;
	}
	public List<DataGridColumnScriptContent> getDataGridColumns() {
		return new LinkedList<DataGridColumnScriptContent>(_dataGridColumns);
	}
	public Integer getMaxDataPartitionIndex() {
		return _maxDataPartitionIndex;
	}
    public String getFilterComponentId() {
        return _filterComponentId;
    }
    public String getFilterColumnComponentId() {
        return _filterColumnComponentId;
    }
    public String getFilterEventListener() {
        return _filterEventListener;
    }
	
    @Override
	public boolean equals(Object instance) {
		if(!(instance instanceof DataGridScriptContent)){
			return false;
		}
		
		DataGridScriptContent dataGridScriptContentInstance = DataGridScriptContent.class.cast( instance ); 
		return _dataGridId.equals(dataGridScriptContentInstance._dataGridId);
	}
    
    @Override
	public int hashCode() {
		return _dataGridId.hashCode();
	}
	
	public final class DataGridColumnScriptContent {
		
		private final String _dataGridColumnId;
		private final String _dataField;
		private final Boolean _columnEditable;
		
		private DataGridColumnScriptContent(String dataGridColumnId, String dataField, Boolean columnEditable){
			super();
			_dataGridColumnId = dataGridColumnId;
			_dataField = dataField;
			_columnEditable = columnEditable;
		}
		
		public String getDataGridColumnId() {
			return _dataGridColumnId;
		}
		public String getDataField() {
			return _dataField;
		}
		public Boolean getColumnEditable() {
			return _columnEditable;
		}
		
        @Override
		public boolean equals(Object instance) {
			if(!(instance instanceof DataGridColumnScriptContent)){
				return false;
			}
			
			DataGridColumnScriptContent dataGridColumnScriptContentInstance = DataGridColumnScriptContent.class.cast( instance );
			return _dataGridColumnId.equals(dataGridColumnScriptContentInstance._dataGridColumnId);
		}
        
        @Override
		public int hashCode() {
			return _dataGridColumnId.hashCode();
		}
		
	}
	
}
