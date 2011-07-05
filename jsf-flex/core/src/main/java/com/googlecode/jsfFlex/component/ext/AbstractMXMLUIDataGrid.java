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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIBatchColumnDataRetrievalSize;
import com.googlecode.jsfFlex.attributes._MXMLUIBindingBeanClassNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBindingBeanListAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRowCount;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlDataGrid",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIDataGrid",
        type                =   "com.googlecode.jsfFlex.MXMLUIDataGrid",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIDataGridTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLDataGrid"
)
public abstract class AbstractMXMLUIDataGrid 
                        extends MXMLUISimpleBase
                        implements _MXMLUIDataGridAttributes, _MXMLUIBindingBeanListAttribute, _MXMLUIBindingBeanClassNameAttribute,
                        _MXMLUIBatchColumnDataRetrievalSize, _MXMLUIEditableAttribute, _MXMLUIDataProviderAttribute, 
                        _MXMLUIRowCount, _MXMLUISelectedIndexAttribute {
    
    private final static Log _log = LogFactory.getLog(AbstractMXMLUIDataGrid.class);
    
    private static final Integer ZERO_BATCH_COLUMN_DATA_RETRIEVAL_SIZE = Integer.valueOf(0);
    
    private static final String COLUMN_DATA_FIELD_KEY = "columnDataField";
    private static final String RESULT_CODE_KEY = "resultCode";
    
    private static final String DATA_START_INDEX_KEY = "dataStartIndex";
    private static final String DATA_END_INDEX_KEY = "dataEndIndex";
    
    private static final String COLUMN_DATA_FIELD_TO_SORT_BY_KEY = "columnDataFieldToSortBy";
    private static final String SORT_ASCENDING_KEY = "sortAscending";
    
    private static final String BATCH_COLUMN_DATA_RETRIEVAL_SIZE = "batchColumnDataRetrievalSize";
    private static final String MAX_DATA_PARTITION_INDEX = "maxDataPartitionIndex";
    
    private static final String ADD_DATA_ENTRY_DELIM = "_";
    private static final String ADD_ENTRY_START_INDEX_KEY = "addEntryStartIndex";
    private static final String ADD_ENTRY_END_INDEX_KEY = "addEntryEndIndex";
    
    private static final String DELETE_INDICES_KEY = "deleteIndices";
    
    private Map<String, AbstractMXMLUIDataGridColumn> _dataGridColumnComponentMapping;
    
    {
        _dataGridColumnComponentMapping = new HashMap<String, AbstractMXMLUIDataGridColumn>();
    }
    
    public List<String> getFormatedColumnData() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String columnDataField = requestMap.get(COLUMN_DATA_FIELD_KEY);
        String dataStartIndex = requestMap.get(DATA_START_INDEX_KEY);
        String dataEndIndex = requestMap.get(DATA_END_INDEX_KEY);
        
        _log.info("Requested additional data with dataStartIndex : " + dataStartIndex + " , dataEndIndex : " + dataEndIndex + 
                        " for dataField : " + columnDataField + " for component : " + getId());
        
        int parsedStartIndex = -1;
        int parsedEndIndex = -1;
        
        try{
            parsedStartIndex = Integer.parseInt(dataStartIndex);
            parsedEndIndex = Integer.parseInt(dataEndIndex);
        }catch(NumberFormatException parsingException){
            _log.error("Error parsing of following values [" + dataStartIndex + ", " + dataEndIndex + "] to an int", parsingException);
            return new LinkedList<String>();
        }
        
        int dataSize = getBindingBeanList().size();
        parsedEndIndex = parsedEndIndex < dataSize ? parsedEndIndex : dataSize;
        
        _log.debug("Parsed start + end index are [ " + parsedStartIndex + ", " + parsedEndIndex + " ] with dataSize : " + dataSize + " for component : " + getId());
        
        AbstractMXMLUIDataGridColumn dataGridColumnComponent = _dataGridColumnComponentMapping.get(columnDataField);
        
        List<String> formatedColumnData;
        
        synchronized(getBindingBeanList()){
            formatedColumnData = dataGridColumnComponent.getFormatedColumnData(getBindingBeanList(), parsedStartIndex, parsedEndIndex);
        }
        
        return formatedColumnData;
    }
    
    public Map<String, ? super Object> updateModifiedDataField() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String columnDataField = requestMap.get(COLUMN_DATA_FIELD_KEY);
        AbstractMXMLUIDataGridColumn dataGridColumnComponent = _dataGridColumnComponentMapping.get(columnDataField);
        
        _log.info("Update requested for dataField : " + columnDataField + " for component : " + getId());
        
        Map<String, ? super Object> updateResult;
        
        synchronized(getBindingBeanList()){
            updateResult = dataGridColumnComponent.updateModifiedDataField(context, requestMap, getBindingBeanList());
        }
        
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
            addDataResult.put(RESULT_CODE_KEY, Boolean.valueOf(success));
            return addDataResult;
        }
        
        _log.info("Parsed add entry start + end index are [ " + parsedAddEntryStartIndex + ", " + parsedAddEntryEndIndex + " ] for component : " + getId());
        
        try{
            Class beanEntryClass = Class.forName(BEAN_ENTRY_CLASS_NAME);
            Object beanEntryInstance;
            
            int loopLength = parsedAddEntryEndIndex - parsedAddEntryStartIndex;
            for(int i=0; i < loopLength; i++){
                
                beanEntryInstance = beanEntryClass.newInstance();
                
                for(String currDataGridColumnDataField : _dataGridColumnComponentMapping.keySet()){
                    String currDataFieldKey = currDataGridColumnDataField + ADD_DATA_ENTRY_DELIM + i;
                    Object currDataFieldValue = requestMap.get(currDataFieldKey);
                    
                    _log.debug("Setting dataField : " + currDataGridColumnDataField + " with value : " + currDataFieldValue + 
                                    " for class : " + beanEntryInstance.getClass().getName() + " for component : " + getId());
                    AbstractMXMLUIDataGridColumn currDataGridColumnComponent = _dataGridColumnComponentMapping.get(currDataGridColumnDataField);
                    currDataGridColumnComponent.setDataField(context, beanEntryInstance, currDataFieldValue);
                    
                }
                
                synchronized(getBindingBeanList()){
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
        
        addDataResult.put(BATCH_COLUMN_DATA_RETRIEVAL_SIZE, batchColumnDataRetrievalSize);
        addDataResult.put(MAX_DATA_PARTITION_INDEX, maxDataPartitionIndex);
        addDataResult.put(RESULT_CODE_KEY, Boolean.valueOf(success));
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
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        
        String deleteIndices = requestMap.get(DELETE_INDICES_KEY);
        List<String> deleteIndicesList = Arrays.asList(deleteIndices.split(","));
        Collections.sort(deleteIndicesList, DELETE_INDICES_COMPARATOR);
        Collections.reverse(deleteIndicesList);
        _log.info("Requested deleteIndices are : " + deleteIndices + " for component : " + getId());
        
        synchronized(getBindingBeanList()){
            
            for(String currDeleteIndex : deleteIndicesList){
                
                int parsedDeleteIndex = -1;
                
                try{
                    parsedDeleteIndex = Integer.parseInt(currDeleteIndex);
                }catch(NumberFormatException parsingException){
                    _log.error("Error parsing of " + currDeleteIndex + " to an int", parsingException);
                    success = false;
                    break;
                }
                
                getBindingBeanList().remove(parsedDeleteIndex);
                
                _log.debug("Have removed element at : " + currDeleteIndex + " for component : " + getId());
            }
            
        }
        
        Integer batchColumnDataRetrievalSize = computeBatchColumnDataRetrievalSize();
        Integer maxDataPartitionIndex = computeMaxDataPartitionIndex();
        
        _log.info("Returning reset values of batchColumnDataRetrievalSize + maxDataPartitionIndex are [ " + 
                        batchColumnDataRetrievalSize + ", " + maxDataPartitionIndex + "] for component : " + getId());
        
        removeDataResult.put(BATCH_COLUMN_DATA_RETRIEVAL_SIZE, batchColumnDataRetrievalSize);
        removeDataResult.put(MAX_DATA_PARTITION_INDEX, maxDataPartitionIndex);
        removeDataResult.put(RESULT_CODE_KEY, Boolean.valueOf(success));
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
        
        AbstractMXMLUIDataGridColumn dataGridColumnComponent = _dataGridColumnComponentMapping.get(columnDataFieldToSortBy);
        Comparator<Object> dataFieldComparator = sortAscending ? dataGridColumnComponent.getAscendingComparator() : 
                                                                            dataGridColumnComponent.getDescendingComparator();
        
        synchronized(getBindingBeanList()){
            Collections.sort(getBindingBeanList(), dataFieldComparator);
        }
        
        _log.info("Success result code for sorting is : " + success + " for component : " + getId());
        
        sortResult.put(RESULT_CODE_KEY, Boolean.valueOf(success));
        return sortResult;
    }
    
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        
        /*
         * adding the component to the map for future asynchronous request by
         * DataGridColumnServiceRequest.as 
         * 
         * instances of AbstractMXMLUIDataGridColumn to _dataGridColumnComponents Map
         * will be added by AbstractMXMLUIColumns
         */
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.put(getId(), this);
        
    }
    
    public void decode(FacesContext context) {
        super.decode(context);
        
        /*
         * No longer needed, so remove the content.
         * Below is a pure HACK till JSF 2.0.
         */
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.remove(getId());
        
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
        
        int dataEntrySize = getBindingBeanList().size();
        if(dataEntrySize < batchColumnDataRetrievalSize.intValue()){
            batchColumnDataRetrievalSize = Integer.valueOf(dataEntrySize);
        }
        return batchColumnDataRetrievalSize;
    }
    
    public Integer computeMaxDataPartitionIndex(){
        
        double dataEntrySize = getBindingBeanList().size();
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
        return maxDataPartitionIndex;
    }
    
    public Map<String, AbstractMXMLUIDataGridColumn> getDataGridColumnComponentMapping(){
        return _dataGridColumnComponentMapping;
    }
    
}
