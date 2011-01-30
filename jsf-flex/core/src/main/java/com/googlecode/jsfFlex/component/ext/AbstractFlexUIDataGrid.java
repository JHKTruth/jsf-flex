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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIAsynchronousEventGlueHandlerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUIBatchColumnDataRetrievalSizeAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBindingBeanClassNameAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBindingBeanListAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIEditableAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFilterColumnComponentIdAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFilterComponentIdAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFilterEventListenerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIRowCountAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUIPreserveInServer;
import com.googlecode.jsfFlex.shared.model.event.AbstractEvent;
import com.googlecode.jsfFlex.shared.model.event.AsynchronousFilterEvent;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexDataGrid",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUIDataGrid",
        type                =   "com.googlecode.jsfFlex.FlexUIDataGrid",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUIDataGridTag",
        family              =   "javax.faces.FlexUIPreserveInServer",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexDataGrid"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIDataGrid")
public abstract class AbstractFlexUIDataGrid 
                        extends AbstractFlexUIPreserveInServer
                        implements IFlexUIBaseAttributes, IFlexUIBindingBeanListAttribute, IFlexUIBindingBeanClassNameAttribute,
                        IFlexUIBatchColumnDataRetrievalSizeAttribute, IFlexUIEditableAttribute, IFlexUIDataProviderAttribute, 
                        IFlexUIRowCountAttribute, IFlexUIFilterComponentIdAttribute, IFlexUIFilterEventListenerAttribute, 
                        IFlexUIAsynchronousEventGlueHandlerAttribute, IFlexUIFilterColumnComponentIdAttribute {
    
    private final static Log _log = LogFactory.getLog(AbstractFlexUIDataGrid.class);
    
    private static final Integer ZERO_BATCH_COLUMN_DATA_RETRIEVAL_SIZE = Integer.valueOf(0);
    
    private static final String COLUMN_DATA_FIELD_KEY = "COLUMN_DATA_FIELD";
    
    private static final String DATA_START_INDEX_KEY = "DATA_START_INDEX";
    private static final String DATA_END_INDEX_KEY = "DATA_END_INDEX";
    
    private static final String COLUMN_DATA_FIELD_TO_SORT_BY_KEY = "COLUMN_DATA_FIELD_TO_SORT_BY";
    private static final String SORT_ASCENDING_KEY = "SORT_ASCENDING";
    
    private static final String BATCH_COLUMN_DATA_RETRIEVAL_SIZE_KEY = "BATCH_COLUMN_DATA_RETRIEVAL_SIZE";
    private static final String MAX_DATA_PARTITION_INDEX_KEY = "MAX_DATA_PARTITION_INDEX";
    
    private static final String ADD_DATA_ENTRY_DELIM = "_DELIM_";
    private static final String ADD_ENTRY_START_INDEX_KEY = "ADD_ENTRY_START_INDEX";
    private static final String ADD_ENTRY_END_INDEX_KEY = "ADD_ENTRY_END_INDEX";
    
    private static final String DELETE_INDICES_KEY = "DELETE_INDICES";
    
    private static final String DELTA_DESELECTED_ENTRIES_KEY = "DELTA_DESELECTED_ENTRIES";
    private static final String DELTA_SELECTED_ENTRIES_KEY = "DELTA_SELECTED_ENTRIES";
    private static final String FETCH_SELECTION_ITEM_PARTITION_INDEX_KEY = "FETCH_SELECTION_ITEM_PARTITION_INDEX";
    private static final String REQUEST_KEYS_KEY = "REQUEST_KEYS";
    private static final String UPDATE_ITEM_PARTITION_INDEX_KEY = "UPDATE_ITEM_PARTITION_INDEX";
    private static final String SELECT_ALL_KEY = "SELECT_ALL";
    private static final String DESELECT_ALL_KEY = "DESELECT_ALL";
    private static final String RETURNED_SELECT_ENTRIES = "RETURNED_SELECT_ENTRIES";
    
    private static final String FILTER_VALUE_KEY = "FILTER_VALUE";
    private static final String FILTER_COLUMN_VALUE = "FILTER_COLUMN_VALUE";
    private static final JSONObject ERROR_JSON_OBJECT = new JSONObject();
    
    static{
        try{
            ERROR_JSON_OBJECT.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), "Error");
        }catch(JSONException jsonException){
            _log.error("Error while creating ERROR_JSON_OBJECT");
        }
    }
    
    private Map<String, AbstractFlexUIDataGridColumn> _dataGridColumnComponentMapping;
    private String _filterColumn;
    private String _filterValue;
    
    private List<WrappedBeanEntry> _filteredList;
    private List<WrappedBeanEntry> _wrappedList;
    
    {
        _dataGridColumnComponentMapping = new HashMap<String, AbstractFlexUIDataGridColumn>();
        
        _filteredList = new ArrayList<WrappedBeanEntry>();
        _wrappedList = new ArrayList<WrappedBeanEntry>();
    }
    
    public boolean isRowSelected(int rowIndex) {
        return getCurrentList().get(rowIndex).isSelected();
    }
    
    public void selectRow(int rowIndex) {
        getCurrentList().get(rowIndex).setSelected(true);
    }
    
    public void deselectRow(int rowIndex) {
        getCurrentList().get(rowIndex).setSelected(false);
    }
    
    public void selectRows(int beginRowIndex, int endRowIndex) {
        List<WrappedBeanEntry> currentList = getCurrentList();
        
        for(; beginRowIndex < endRowIndex; beginRowIndex++) {
            currentList.get(beginRowIndex).setSelected(true);
        }
    }
    
    public void deselectRows(int beginRowIndex, int endRowIndex) {
        List<WrappedBeanEntry> currentList = getCurrentList();
        
        for(; beginRowIndex < endRowIndex; beginRowIndex++) {
            currentList.get(beginRowIndex).setSelected(false);
        }
    }
    
    public void deselectAll() {
        
        for(WrappedBeanEntry currEntry : _wrappedList) {
            currEntry.setSelected(false);
        }
    }
    
    public void selectAll() {
        
        for(WrappedBeanEntry currEntry : _wrappedList) {
            currEntry.setSelected(true);
        }
    }
    
    public JSONObject updateRowSelectionEntry() throws JSONException {
        
        JSONObject updateRowSelectionResult = new JSONObject();
        boolean success = true;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String[]> requestParameterValuesMap = context.getExternalContext().getRequestParameterValuesMap();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String[] deltaDeselectedEntries = requestParameterValuesMap.get(DELTA_DESELECTED_ENTRIES_KEY);
        String[] deltaSelectedEntries = requestParameterValuesMap.get(DELTA_SELECTED_ENTRIES_KEY);
        
        int batchColumnDataRetrievalSize = computeBatchColumnDataRetrievalSize();
        try{
            int updateItemPartitionIndex = Integer.valueOf(requestMap.get(UPDATE_ITEM_PARTITION_INDEX_KEY));
            int updateSelectionStartIndex = updateItemPartitionIndex * batchColumnDataRetrievalSize;
            
            _log.info("updateRowSelectionEntry: updateItemPartitionIndex is " + updateItemPartitionIndex + ", updateSelectionStartIndex is " + updateSelectionStartIndex);
            
            boolean selectAll = Boolean.valueOf(requestMap.get(SELECT_ALL_KEY));
            boolean deselectAll = Boolean.valueOf(requestMap.get(DESELECT_ALL_KEY));
            
            selectDeselectAllBlock: {
                if(selectAll) {
                    selectAll();
                    break selectDeselectAllBlock;
                }
                
                if(deselectAll) {
                    deselectAll();
                    break selectDeselectAllBlock;
                }
                
                if(deltaDeselectedEntries != null){
                    for(String deltaDeselectedEntry : deltaDeselectedEntries) {
                        deselectRow(updateSelectionStartIndex + Integer.valueOf(deltaDeselectedEntry));
                    }
                }
                
                if(deltaSelectedEntries != null){
                    for(String deltaSelectedEntry : deltaSelectedEntries) {
                        selectRow(updateSelectionStartIndex + Integer.valueOf(deltaSelectedEntry));
                    }
                }
            }
            
        }catch(NumberFormatException numberFormatException) {
            _log.error("A parsing exception occurred within updateRowSelectionEntry", numberFormatException);
            success = false;
        }
        
        if(success){
            int fetchSelectionItemPartitionIndex = Integer.valueOf(requestMap.get(FETCH_SELECTION_ITEM_PARTITION_INDEX_KEY));
            int startIndex = fetchSelectionItemPartitionIndex * batchColumnDataRetrievalSize;
            int endIndex = Math.min((fetchSelectionItemPartitionIndex + 1) * batchColumnDataRetrievalSize, getCurrentList().size());
            
             _log.info("updateRowSelectionEntry: fetchSelectionItemPartitionIndex is " + fetchSelectionItemPartitionIndex + ", startIndex is " + startIndex
                                    + ", endIndex is " + endIndex);
             
             if(endIndex < startIndex){
                 _log.info("Okay startIndex is greater than endIndex, what went wrong. StartIndex : " + startIndex + ", endIndex : " + endIndex);
                 success = false;
             }else{
                 /*
                  * Now loop through the BitSet and push the entries within a JSONArray
                  */
                 JSONArray selectedEntries = new JSONArray();
                 for(; startIndex < endIndex; startIndex++){
                     if(isRowSelected(startIndex)){
                         selectedEntries.put(startIndex);
                     }
                 }
                 updateRowSelectionResult.put(RETURNED_SELECT_ENTRIES, selectedEntries);
            }
        }
        
        updateRowSelectionResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), success);
        return updateRowSelectionResult;
    }
    
    /**
     * @param filterEvent
     * @return if the value is true the row will be filtered
     */
    private static Boolean defaultFilterMethod(AsynchronousFilterEvent filterEvent){
        return filterEvent.getFilterValue().length() > 0 && !filterEvent.getComponentValue().contains(filterEvent.getFilterValue());
    }
    
    private Boolean invokeFilterMethod(String currRowValue, String filterValue) {
        Boolean toFilter = false;
        
        MethodExpression userProvidedFilterMethod = getAsynchronousEventGlueHandler();
        if(userProvidedFilterMethod != null){
            FacesContext context = FacesContext.getCurrentInstance();
            toFilter = Boolean.valueOf( userProvidedFilterMethod.invoke(context.getELContext(), new Object[]{new AsynchronousFilterEvent(this, currRowValue, filterValue)}).toString() );
        }else{
            toFilter = defaultFilterMethod(new AsynchronousFilterEvent(this, currRowValue, filterValue));
        }
        
        return toFilter;
    }
    
    public JSONObject filterList(int parsedStartIndex, int parsedEndIndex) throws JSONException {
        _log.info("Filtering the list " + parsedStartIndex + ", " + parsedEndIndex);
        
        AbstractFlexUIDataGridColumn filterColumnComponent = _dataGridColumnComponentMapping.get(_filterColumn);
        Map<String, AbstractFlexUIDataGridColumn> traverseDataGridColumnMap = new HashMap<String, AbstractFlexUIDataGridColumn>(_dataGridColumnComponentMapping); 
        traverseDataGridColumnMap.remove(_filterColumn);
        
        JSONObject formatedColumnData = new JSONObject();
        JSONArray filterColumnContent = new JSONArray();
        /*
         * First add the filterColumn, then add the remaining column values
         */
        
        formatedColumnData.put(_filterColumn, filterColumnContent);
        for(String currKey : traverseDataGridColumnMap.keySet()) {
            formatedColumnData.put(currKey, new JSONArray());
        }
        
        int dataSize = _wrappedList.size();
        parsedEndIndex = parsedEndIndex < dataSize ? parsedEndIndex : dataSize;
        _log.info("Parsed start + end index are [ " + parsedStartIndex + ", " + parsedEndIndex + " ] with dataSize : " + dataSize + " for component : " + getId());
        
        synchronized(_wrappedList){
            
        	int dataRequestSize = parsedEndIndex - parsedStartIndex;
        	int dataAdded = 0;
            /*
             * It is assumed that parsedStartIndex is starting from 0
             */
            for(;(parsedStartIndex < dataSize) && (dataAdded < dataRequestSize); parsedStartIndex++) {
                WrappedBeanEntry currEntry = _wrappedList.get(parsedStartIndex);
                Object currValue = currEntry.getBeanEntry();
                String filterCheckValue = filterColumnComponent.getFormatedColumnData(currValue);
                
                Boolean filterCurrentRow = false;
                
                if(getFilterComponentId() != null){
                    filterCurrentRow = invokeFilterMethod(filterCheckValue, _filterValue);
                }
                
                if(filterCurrentRow){
                    _log.info("Row containing value of " + filterCheckValue + " was filtered");
                    continue;
                }else{
                    /*
                     * Since this row does not need to be filtered, add first the filter column value and add the reamining entries into 
                     * the JSONObject
                     */
                    filterColumnContent.put(filterCheckValue);
                    
                    for(String currKey : traverseDataGridColumnMap.keySet()) {
                        AbstractFlexUIDataGridColumn currDataGridColumn = traverseDataGridColumnMap.get(currKey);
                        JSONArray currEntryList = JSONArray.class.cast( formatedColumnData.get(currKey) );
                        String currColumnValue = currDataGridColumn.getFormatedColumnData(currValue);
                        currEntryList.put(currColumnValue);
                    }
                    
                    dataAdded++;
                    _filteredList.add(currEntry);
                }
                
            }
            
            /*
             * Now filter through the remaining list
             */
            for(; parsedStartIndex < dataSize; parsedStartIndex++){
                
                WrappedBeanEntry currEntry = _wrappedList.get(parsedStartIndex);
                Object currValue = currEntry.getBeanEntry();
                String filterCheckValue = filterColumnComponent.getFormatedColumnData(currValue);
                
                Boolean filterCurrentRow = false;
                if(getFilterComponentId() != null){
                    filterCurrentRow = invokeFilterMethod(filterCheckValue, _filterValue);
                    
                    if(filterCurrentRow){
                        _log.info("Remaining - row containing value of " + filterCheckValue + " was filtered");
                        continue;
                    }else{
                        _filteredList.add(currEntry);
                    }
                }
            }
            
        }
        
        _log.info("Size of filteredList is " + _filteredList.size() + ", " + filterColumnContent.length());
        return formatedColumnData;
    }
    
    public JSONObject getGridData() throws JSONException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String dataStartIndex = requestMap.get(DATA_START_INDEX_KEY);
        String dataEndIndex = requestMap.get(DATA_END_INDEX_KEY);
        String filterValue = requestMap.get(FILTER_VALUE_KEY);
        String filterColumnValue = requestMap.get(FILTER_COLUMN_VALUE);
        
        _log.info("Requested additional data with dataStartIndex : " + dataStartIndex + " , dataEndIndex : " + dataEndIndex + 
                        ", filterValue: " + filterValue + ", filterColumnValue:" + filterColumnValue + " for component : " + getId());
        
        int parsedStartIndex = -1;
        int parsedEndIndex = -1;
        
        try{
            parsedStartIndex = Integer.parseInt(dataStartIndex);
            parsedEndIndex = Integer.parseInt(dataEndIndex);
        }catch(NumberFormatException parsingException){
            _log.error("Error parsing of following values [" + dataStartIndex + ", " + dataEndIndex + "] to an int", parsingException);
            return ERROR_JSON_OBJECT;
        }
        
        synchronized(this){
            if((filterColumnValue.length() > 0 && !filterColumnValue.equals(_filterColumn)) ||
                    !filterValue.equals(_filterValue)){
                /*
                 * means that filtering is active and a new value has been requested
                 * Perform filter and return a list of values requested. Return the result.
                 */
                resetFilterList(filterColumnValue, filterValue);
                if(filterValue.length() > 0){ 
                	return filterList(parsedStartIndex, parsedEndIndex);
                }
            }
        }
        
        JSONObject formatedColumnData = new JSONObject();
        for(String currKey : _dataGridColumnComponentMapping.keySet()) {
            formatedColumnData.put(currKey, new JSONArray());
        }
        
        List<WrappedBeanEntry> currentList = getCurrentList();
        int dataSize = currentList.size();
        parsedEndIndex = parsedEndIndex < dataSize ? parsedEndIndex : dataSize;
        _log.info("Parsed start + end index are [ " + parsedStartIndex + ", " + parsedEndIndex + " ] with dataSize : " + dataSize + " for component : " + getId());
        
        synchronized(currentList){
            for(; parsedStartIndex < parsedEndIndex; parsedStartIndex++) {
                WrappedBeanEntry currEntry = currentList.get(parsedStartIndex);
                Object currValue = currEntry.getBeanEntry();
                
                for(String currKey : _dataGridColumnComponentMapping.keySet()) {
                    AbstractFlexUIDataGridColumn currDataGridColumn = _dataGridColumnComponentMapping.get(currKey);
                    JSONArray currEntryList = JSONArray.class.cast( formatedColumnData.get(currKey) );
                    String currColumnValue = currDataGridColumn.getFormatedColumnData(currValue);
                    currEntryList.put(currColumnValue);
                }
            }
        }
        
        return formatedColumnData;
    }
    
    public Map<String, ? super Object> updateModifiedDataField() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String columnDataField = requestMap.get(COLUMN_DATA_FIELD_KEY);
        AbstractFlexUIDataGridColumn dataGridColumnComponent = _dataGridColumnComponentMapping.get(columnDataField);
        
        _log.info("Update requested for dataField : " + columnDataField + " for component : " + getId());
        
        Map<String, ? super Object> updateResult = new HashMap<String, Object>();
        boolean success = true;
        
        String requestKey = requestMap.get(REQUEST_KEYS_KEY);
        List<String> requestKeyList = Arrays.asList(requestKey.split(","));
        
        List<WrappedBeanEntry> currentList = getCurrentList();
        _log.info("Requested update of data with requestKey : " + requestKey + " for dataField : " + dataGridColumnComponent.getDataField());
        
        for(String currKey : requestKeyList){
            
            Object currValue = requestMap.get(currKey);
            int rowIndex;
            
            try{
                rowIndex = Integer.parseInt(currKey);
            }catch(NumberFormatException parsingException){
                _log.error("Error parsing of " + currKey + " to an int", parsingException);
                success = false;
                break;
            }
            
            synchronized(currentList){
                WrappedBeanEntry currEntry = currentList.get(rowIndex);
                Object currDataEntry = currEntry.getBeanEntry();
                success = dataGridColumnComponent.setDataField(context, currDataEntry, currValue);
                
                _log.debug("Success result code of : " + success + " when setting value of : " + currValue + " to an instance of : " + currDataEntry.getClass().getName());
                
                if(!success){
                    break;
                }                
            }
        }
        
        updateResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), Boolean.valueOf(success));
        return updateResult;
    }
    
    public Map<String, ? super Object> addDataEntry(){
        
        final String BEAN_ENTRY_CLASS_NAME = getBindingBeanList().size() > 0 ? getBindingBeanList().get(0).getClass().getName() : getBindingBeanClassName();
        
        Map<String, ? super Object> addDataResult = new HashMap<String, Object>();
        boolean success = true;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String addEntryStartIndex = requestMap.get(ADD_ENTRY_START_INDEX_KEY);
        String addEntryEndIndex = requestMap.get(ADD_ENTRY_END_INDEX_KEY);
        
        int parsedAddEntryStartIndex = -1;
        int parsedAddEntryEndIndex = -1;
        
        try{
            parsedAddEntryStartIndex = Integer.parseInt(addEntryStartIndex);
            parsedAddEntryEndIndex = Integer.parseInt(addEntryEndIndex);
        }catch(NumberFormatException parsingException){
            _log.error("Error parsing of following values [" + addEntryStartIndex + ", " + addEntryEndIndex + "] to an int", parsingException);
            success = false;
            addDataResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), Boolean.valueOf(success));
            return addDataResult;
        }
        
        _log.info("Parsed add entry start + end index are [ " + parsedAddEntryStartIndex + ", " + parsedAddEntryEndIndex + " ] for component : " + getId());
        int loopLength = parsedAddEntryEndIndex - parsedAddEntryStartIndex;
        try{
            Class beanEntryClass = Class.forName(BEAN_ENTRY_CLASS_NAME);
            Comparable<? super Object> beanEntryInstance;
            
            for(int i=0; i < loopLength; i++){
                
                beanEntryInstance = (Comparable<? super Object>) beanEntryClass.newInstance();
                
                for(String currDataGridColumnDataField : _dataGridColumnComponentMapping.keySet()){
                    String currDataFieldKey = currDataGridColumnDataField + ADD_DATA_ENTRY_DELIM + i;
                    String currDataFieldValue = requestMap.get(currDataFieldKey);
                    
                    _log.debug("Setting dataField : " + currDataGridColumnDataField + " with value : " + currDataFieldValue + 
                                    " for class : " + beanEntryInstance.getClass().getName() + " for component : " + getId());
                    AbstractFlexUIDataGridColumn currDataGridColumnComponent = _dataGridColumnComponentMapping.get(currDataGridColumnDataField);
                    currDataGridColumnComponent.setDataField(context, beanEntryInstance, currDataFieldValue);
                }
                
                WrappedBeanEntry currEntry = new WrappedBeanEntry(beanEntryInstance);
                if(isFiltered()){
                    
                    AbstractFlexUIDataGridColumn filterColumnComponent = _dataGridColumnComponentMapping.get(_filterColumn);
                    String filterCheckValue = filterColumnComponent.getFormatedColumnData(beanEntryInstance);
                    
                    Boolean filterCurrentRow = false;
                    if(getFilterComponentId() != null){
                        filterCurrentRow = invokeFilterMethod(filterCheckValue, _filterValue);
                        
                        if(filterCurrentRow){
                            _log.info("Row containing value of " + filterCheckValue + " was filtered");
                        }else{
                            _filteredList.add(currEntry);
                            
                        }
                    }
                    
                    getBindingBeanList().add(parsedAddEntryStartIndex, beanEntryInstance);
                }
                
                synchronized(_wrappedList) {
                    _wrappedList.add(parsedAddEntryStartIndex, currEntry);
                }
                
                synchronized(getBindingBeanList()) {
                    getBindingBeanList().add(parsedAddEntryStartIndex, beanEntryInstance);
                }
            }
            
        }catch(ClassNotFoundException classNotFoundException){
            _log.error("Failure in finding className " + BEAN_ENTRY_CLASS_NAME, classNotFoundException);
            success = false;
        }catch(IllegalAccessException illegalAccessException){
            _log.error("Failure in instantiating " + BEAN_ENTRY_CLASS_NAME, illegalAccessException);
            success = false;
        }catch(InstantiationException instantiationException){
            _log.error("Failure in instantiating " + BEAN_ENTRY_CLASS_NAME, instantiationException);
            success = false;
        }
        
        _log.info("Success result code after adding the entries to bindingBeanList is : " + success + " for component : " + getId());
        _log.info("New size of bindingBeanList is : " + getBindingBeanList().size() + " for component : " + getId());
        
        Integer batchColumnDataRetrievalSize = computeBatchColumnDataRetrievalSize();
        Integer maxDataPartitionIndex = computeMaxDataPartitionIndex();
        
        _log.info("Returning reset values of batchColumnDataRetrievalSize + maxDataPartitionIndex are [ " + 
                        batchColumnDataRetrievalSize + ", " + maxDataPartitionIndex + "] for component : " + getId());
        
        deselectAll();
        selectRows(parsedAddEntryEndIndex, parsedAddEntryEndIndex + loopLength);
        
        addDataResult.put(BATCH_COLUMN_DATA_RETRIEVAL_SIZE_KEY, batchColumnDataRetrievalSize);
        addDataResult.put(MAX_DATA_PARTITION_INDEX_KEY, maxDataPartitionIndex);
        addDataResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), Boolean.valueOf(success));
        return addDataResult;
    }
    
    public Map<String, ? super Object> removeDataEntry(){
        
        final Comparator<? super String> DELETE_INDICES_COMPARATOR = new Comparator<String>(){
            
            public int compare(String firstInstance, String secondInstance) {
                Integer firstCompare = Integer.valueOf(firstInstance);
                Integer secondCompare = Integer.valueOf(secondInstance);
                return firstCompare.compareTo(secondCompare);
            }
        };
        
        Map<String, ? super Object> removeDataResult = new HashMap<String, Object>();
        boolean success = true;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String[]> requestParameterValuesMap = context.getExternalContext().getRequestParameterValuesMap();
        String[] deleteIndices = requestParameterValuesMap.get(DELETE_INDICES_KEY);
        List<String> deleteIndicesList = Arrays.asList(deleteIndices);
        Collections.sort(deleteIndicesList, DELETE_INDICES_COMPARATOR);
        Collections.reverse(deleteIndicesList);
        _log.info("Requested deleteIndices are : " + deleteIndices + " for component : " + getId());
        
        synchronized(this){
            
            for(String currDeleteIndex : deleteIndicesList){
                int parsedDeleteIndex = -1;
                
                try{
                    parsedDeleteIndex = Integer.parseInt(currDeleteIndex);
                }catch(NumberFormatException parsingException){
                    _log.error("Error parsing of " + currDeleteIndex + " to an int", parsingException);
                    success = false;
                    break;
                }
                
                Object removeEntry = null;
                if(isFiltered()){
                    /*
                     * Need to remove from _filteredList, _wrappedList, and getBindingBeanList()
                     */
                    WrappedBeanEntry filterEntry = _filteredList.remove(parsedDeleteIndex);
                    
                    /*
                     * Below two are costly operations, try to improve
                     */
                    _wrappedList.remove(filterEntry);
                    removeEntry = filterEntry.getBeanEntry();
                }else{
                    
                    removeEntry = _wrappedList.remove(parsedDeleteIndex).getBeanEntry();
                }
                
                getBindingBeanList().remove(removeEntry);
                _log.debug("Have removed element at : " + currDeleteIndex + " for component : " + getId());
            }
            
        }
        
        Integer batchColumnDataRetrievalSize = computeBatchColumnDataRetrievalSize();
        Integer maxDataPartitionIndex = computeMaxDataPartitionIndex();
        
        _log.info("Returning reset values of batchColumnDataRetrievalSize + maxDataPartitionIndex are [ " + 
                        batchColumnDataRetrievalSize + ", " + maxDataPartitionIndex + "] for component : " + getId());
        
        deselectAll();
        
        removeDataResult.put(BATCH_COLUMN_DATA_RETRIEVAL_SIZE_KEY, batchColumnDataRetrievalSize);
        removeDataResult.put(MAX_DATA_PARTITION_INDEX_KEY, maxDataPartitionIndex);
        removeDataResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), Boolean.valueOf(success));
        return removeDataResult;
    }
    
    public Map<String, ? super Object> sortDataEntry() {
        
        Map<String, ? super Object> sortResult = new HashMap<String, Object>();
        boolean success = true;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String columnDataFieldToSortBy = requestMap.get(COLUMN_DATA_FIELD_TO_SORT_BY_KEY);
        boolean sortAscending = Boolean.valueOf(requestMap.get(SORT_ASCENDING_KEY)).booleanValue();
        _log.info("Requested sort of data entries with columnDataFieldToSortBy : " + columnDataFieldToSortBy + " sortAscending : " + sortAscending + " for component : " + getId());
        
        AbstractFlexUIDataGridColumn dataGridColumnComponent = _dataGridColumnComponentMapping.get(columnDataFieldToSortBy);
        
        Comparator<WrappedBeanEntry> dataFieldComparator = sortAscending ? dataGridColumnComponent.getWrappedEntryAscendingComparator() :
                                                                        dataGridColumnComponent.getWrappedEntryDescendingComparator();
        
        List<WrappedBeanEntry> currentList = getCurrentList();
        synchronized(currentList){
            Collections.sort(currentList, dataFieldComparator);
        }
        _log.info("Success result code for sorting is : " + success + " for component : " + getId());
        
        sortResult.put(AbstractEvent.ASYNCHRONOUS_VARIABLES.RESULT_CODE.toString(), Boolean.valueOf(success));
        return sortResult;
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        
        syncWrapListToActual();
    }
    
    public void syncWrapListToActual() {
        
        List<Comparable<? super Object>> bindingBeanList = getBindingBeanList();
        
        _wrappedList = new ArrayList<WrappedBeanEntry>();
        for(Object currentEntry : bindingBeanList) {
            _wrappedList.add(new WrappedBeanEntry(currentEntry));
        }
        
        _log.info("WrappedList size is " + _wrappedList.size());
    }
    
    public Integer computeBatchColumnDataRetrievalSize(){
        
        Integer batchColumnDataRetrievalSize = getBatchColumnDataRetrievalSize() != null ? Integer.valueOf(getBatchColumnDataRetrievalSize()) : ZERO_BATCH_COLUMN_DATA_RETRIEVAL_SIZE;
        String rowCount = getRowCount();
        
        if(rowCount != null){
            int parsedRowCount = Integer.parseInt(rowCount);
            if(parsedRowCount > batchColumnDataRetrievalSize.intValue()){
                batchColumnDataRetrievalSize = Integer.valueOf(parsedRowCount);
            }
        }
        
        int dataEntrySize = isFiltered() ? _filteredList.size() : getBindingBeanList().size();
        if(dataEntrySize < batchColumnDataRetrievalSize.intValue()){
            batchColumnDataRetrievalSize = Integer.valueOf(dataEntrySize);
        }
        
        _log.info("New computeBatchColumnDataRetrievalSize is " + batchColumnDataRetrievalSize);
        return batchColumnDataRetrievalSize;
    }
    
    public Integer computeMaxDataPartitionIndex(){
        
        double dataEntrySize = isFiltered() ? _filteredList.size() : getBindingBeanList().size();
        Integer batchColumnDataRetrievalSize = computeBatchColumnDataRetrievalSize();
        Integer maxDataPartitionIndex = null;
        
        if(batchColumnDataRetrievalSize.intValue() > 0){
            maxDataPartitionIndex = Integer.valueOf((int) Math.ceil( dataEntrySize / batchColumnDataRetrievalSize.intValue() ));
        }else{
            maxDataPartitionIndex = ZERO_BATCH_COLUMN_DATA_RETRIEVAL_SIZE;
        }
        
        //since index of partition begins with zero, decrement by 1
        if(maxDataPartitionIndex.intValue() > 0){
            maxDataPartitionIndex = Integer.valueOf(maxDataPartitionIndex.intValue() - 1);
        }
        
        _log.info("New computeMaxDataPartitionIndex is " + maxDataPartitionIndex);
        return maxDataPartitionIndex;
    }
    
    public void setFilterColumn(String filterColumn) {
        _filterColumn = filterColumn;
    }
    
    private List<WrappedBeanEntry> getCurrentList() {
        return isFiltered() ? _filteredList : _wrappedList;
    }
    
    private boolean isFiltered() {
        return _filterValue != null && _filterValue.length() > 0;
    }
    
    private void resetFilterList(String filterColumnId, String filterValue) {
        _filteredList = new ArrayList<WrappedBeanEntry>();
        
        _filterColumn = filterColumnId;
        _filterValue = filterValue;
    }
    
    public Map<String, AbstractFlexUIDataGridColumn> getDataGridColumnComponentMapping(){
        return _dataGridColumnComponentMapping;
    }
    
    static class WrappedBeanEntry {
        
        private Object _beanEntry;
        private boolean _selected;
        
        private WrappedBeanEntry(Object beanEntry) {
            super();
            
            _beanEntry = beanEntry;
        }
        
        private WrappedBeanEntry(Object beanEntry, boolean selected) {
            this(beanEntry);
            
            _selected = selected;
        }
        
        Object getBeanEntry() {
            return _beanEntry;
        }
        void setBeanEntry(Object beanEntry) {
            _beanEntry = beanEntry;
        }
        
        boolean isSelected() {
            return _selected;
        }
        void setSelected(boolean selected) {
            _selected = selected;
        }
        
        @Override
        public int hashCode() {
            return _beanEntry.hashCode();
        }
        
        @Override
        public boolean equals(Object instance) {
            if(!(instance instanceof WrappedBeanEntry)) {
                return false;
            }
            
            WrappedBeanEntry currEntry = WrappedBeanEntry.class.cast( instance );
            return currEntry._beanEntry.equals(_beanEntry);
        }
        
    }
    
}
