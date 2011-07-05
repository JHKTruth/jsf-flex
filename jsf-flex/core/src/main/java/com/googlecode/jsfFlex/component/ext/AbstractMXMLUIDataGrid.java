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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIAllowMultipleSelectionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBatchColumnDataRetrievalSize;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBindingBeanClassNameAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBindingBeanListAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColumnCountAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDragAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHeaderHeightAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIItemInfoAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIRepeatAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShowHeadersAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIListBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIScrollControlAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlDataGrid"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIDataGrid"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDataGrid"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIDataGridTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLDataGrid"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "columns"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "An array of DataGridColumn objects, one for each column that can be displayed."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "draggableColumns"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether the user is allowed to reorder columns."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "editable"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether or not the user can edit items in the data provider."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "editedItemPosition"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The column and row index of the item renderer for the data provider item being edited, if any."
 *   						,
 *   						
 *							@JSFJspProperty
 *   						 name		= "horizontalScrollPosition"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The offset into the content from the left edge."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "imeMode"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the IME (input method editor) mode."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditorInstance"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A reference to the currently active instance of the item editor, if it exists."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "minColumnWidth"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The minimum width of the columns, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "resizableColumns"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether the user can change the size of the columns."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "sortableColumns"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether the user can sort the data provider items by clicking on a column header cell."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundDisabledColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The background color of the DataGrid when disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "columnDropIndicatorSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The class to use as the skin that indicates that a column can be dropped in the current location."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "columnResizeSkin"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The class to use as the skin for a column that is being resized."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerColors"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "An array of two colors used to draw the header background gradient."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerDragProxyStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of a CSS style declaration for controlling aspects of the appearance of column when the user is dragging it to another location."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerSeparatorSkin"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "The skin that defines the appearance of the separators between column headers in a DataGrid."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerStyleName"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The name of a CSS style declaration for controlling other aspects of the appearance of the column headers."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalGridLineColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The color of the horizontal grid lines."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalGridLines"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether to show horizontal grid lines between the rows."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "rollOverColor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The color of the row background when the user rolls over the row."
 *   						,
 *   						
 *							@JSFJspProperty
 *   						 name		= "selectionColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The color of the background for the row when the user selects an item renderer in the row."
 *   						,
 *   
 *							@JSFJspProperty
 *   						 name		= "sortArrowSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The class to use as the skin for the arrow that indicates the column sort direction."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "stretchCursor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The class to use as the skin for the cursor that indicates that a column can be resized."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalGridLineColor"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The color of the vertical grid lines."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalGridLines"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "A flag that indicates whether to show vertical grid lines between the columns."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "columnStretch"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when a user changes the width of a column, indicating that the amount of data displayed in that column may have changed."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerRelease"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user releases the mouse button on a column header to request the control to sort the grid contents based on the contents of the column."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "headerShift"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user releases the mouse button on a column header after having dragged the column to a new location resulting in shifting the column to a new index."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditBegin"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when the editedItemPosition property has been set and the item can be edited."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemEditEnd"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Dispatched when an item editing session ends for any reason."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemFocusIn"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when an item renderer gets focus, which can occur if the user clicks on an item in the DataGrid control or navigates to the item using a keyboard."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemFocusOut"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Dispatched when an item renderer loses focus, which can occur if the user clicks another item in the DataGrid control or clicks outside the control, or uses the keyboard to navigate to another item in the DataGrid control or outside the control."
 *   						   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIDataGrid 
						extends MXMLUISimpleBase
						implements _MXMLUIHeaderHeightAttribute, _MXMLUIShowHeadersAttribute,
						_MXMLUIListBaseAttributes, _MXMLUIAllowMultipleSelectionAttribute, _MXMLUIColumnCountAttribute, 
						_MXMLUIScrollControlAttributes, _MXMLUIScrollAttributes, _MXMLUIDataProviderAttribute,
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundColorAttribute, _MXMLUIDragAttributes,
						_MXMLUIIconFieldAttribute, _MXMLUIItemInfoAttributes, _MXMLUILabelFieldAttribute, 
						_MXMLUILabelFunctionAttribute, _MXMLUIScrollAttribute, _MXMLUITextStyleAttributes,
						_MXMLUIBackgroundAttributes, _MXMLUIBorderColorAttribute, _MXMLUIBorderAttributes, 
						_MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, 
						_MXMLUIDisabledColorAttribute, _MXMLUIShadowAttributes, _MXMLUIFontFamilyAttribute, 
						_MXMLUIFontGeneralAttributes, _MXMLUIScrollBarAttributes, _MXMLUILeadingAttribute, 
						_MXMLUIRepeatAttributes, _MXMLUIBatchColumnDataRetrievalSize, _MXMLUIBindingBeanListAttribute, 
						_MXMLUIBindingBeanClassNameAttribute, _MXMLUIBaseAttributes {
	
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
	
	private Map _dataGridColumnComponentMapping;
	
	{
		_dataGridColumnComponentMapping = new HashMap();
	}
	
	public List getFormatedColumnData() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		
		String columnDataField = (String) requestMap.get(COLUMN_DATA_FIELD_KEY);
		String dataStartIndex = (String) requestMap.get(DATA_START_INDEX_KEY);
		String dataEndIndex = (String) requestMap.get(DATA_END_INDEX_KEY);
		
		_log.info("Requested additional data with dataStartIndex : " + dataStartIndex + " , dataEndIndex : " + dataEndIndex + 
                        " for dataField : " + columnDataField + " for component : " + getId());
		
		int parsedStartIndex = -1;
		int parsedEndIndex = -1;
		
		try{
			parsedStartIndex = Integer.parseInt(dataStartIndex);
			parsedEndIndex = Integer.parseInt(dataEndIndex);
		}catch(NumberFormatException parsingException){
			_log.error("Error parsing of following values [" + dataStartIndex + ", " + dataEndIndex + "] to an int", parsingException);
			return new LinkedList();
		}
		
        int dataSize = getBindingBeanList().size();
		parsedEndIndex = parsedEndIndex < dataSize ? parsedEndIndex : dataSize;
		
        _log.debug("Parsed start + end index are [ " + parsedStartIndex + ", " + parsedEndIndex + " ] with dataSize : " + dataSize + " for component : " + getId());
        
		AbstractMXMLUIDataGridColumn dataGridColumnComponent = (AbstractMXMLUIDataGridColumn) _dataGridColumnComponentMapping.get(columnDataField);
		
		List formatedColumnData;
		
		synchronized(getBindingBeanList()){
			formatedColumnData = dataGridColumnComponent.getFormatedColumnData(getBindingBeanList(), parsedStartIndex, parsedEndIndex);
		}
		
		return formatedColumnData;
	}
	
	public Map updateModifiedDataField() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		
		String columnDataField = (String) requestMap.get(COLUMN_DATA_FIELD_KEY);
		AbstractMXMLUIDataGridColumn dataGridColumnComponent = (AbstractMXMLUIDataGridColumn) _dataGridColumnComponentMapping.get(columnDataField);
		
        _log.info("Update requested for dataField : " + columnDataField + " for component : " + getId());
        
		Map updateResult;
		
		synchronized(getBindingBeanList()){
			updateResult = dataGridColumnComponent.updateModifiedDataField(context, requestMap, getBindingBeanList());
		}
		
        return updateResult;
	}
	
	public Map addDataEntry(){
		
		final String BEAN_ENTRY_CLASS_NAME = getBindingBeanList().size() > 0 ? getBindingBeanList().get(0).getClass().getName() : getBindingBeanClassName();
		
		Map addDataResult = new HashMap();
		boolean success = true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		
		String addEntryStartIndex = (String) requestMap.get(ADD_ENTRY_START_INDEX_KEY);
		String addEntryEndIndex = (String) requestMap.get(ADD_ENTRY_END_INDEX_KEY);
		
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
				
				for(Iterator iterate = _dataGridColumnComponentMapping.keySet().iterator(); iterate.hasNext();){
					String currDataGridColumnDataField = (String) iterate.next();
					String currDataFieldKey = currDataGridColumnDataField + ADD_DATA_ENTRY_DELIM + i;
					
					Object currDataFieldValue = requestMap.get(currDataFieldKey);
					
					_log.debug("Setting dataField : " + currDataGridColumnDataField + " with value : " + currDataFieldValue + 
									" for class : " + beanEntryInstance.getClass().getName() + " for component : " + getId());
					AbstractMXMLUIDataGridColumn currDataGridColumnComponent = (AbstractMXMLUIDataGridColumn) _dataGridColumnComponentMapping.get(currDataGridColumnDataField);
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
	
	public Map removeDataEntry(){
		
        final Comparator DELETE_INDICES_COMPARATOR = new Comparator(){
            
            public int compare(Object firstInstance, Object secondInstance) {
                Integer firstCompare = Integer.valueOf(firstInstance.toString());
                Integer secondCompare = Integer.valueOf(secondInstance.toString());
                return firstCompare.compareTo(secondCompare);
            }
            
        };
        
		Map removeDataResult = new HashMap();
		boolean success = true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		
		String deleteIndices = (String) requestMap.get(DELETE_INDICES_KEY);
        List deleteIndicesList = Arrays.asList(deleteIndices.split(","));
        Collections.sort(deleteIndicesList, DELETE_INDICES_COMPARATOR);
        Collections.reverse(deleteIndicesList);
		_log.info("Requested deleteIndices are : " + deleteIndices + " for component : " + getId());
        
		synchronized(getBindingBeanList()){
			
            for(Iterator iterate = deleteIndicesList.iterator(); iterate.hasNext();){
				
				String currDeleteIndex = (String) iterate.next();
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
	
	public Map sortDataEntry() {
		
		Map sortResult = new HashMap();
		boolean success = true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		
		String columnDataFieldToSortBy = (String) requestMap.get(COLUMN_DATA_FIELD_TO_SORT_BY_KEY);
		boolean sortAscending = Boolean.valueOf((String) requestMap.get(SORT_ASCENDING_KEY)).booleanValue();
		
		_log.info("Requested sort of data entries with columnDataFieldToSortBy : " + columnDataFieldToSortBy + " sortAscending : " + sortAscending + " for component : " + getId());
		
		AbstractMXMLUIDataGridColumn dataGridColumnComponent = (AbstractMXMLUIDataGridColumn) _dataGridColumnComponentMapping.get(columnDataFieldToSortBy);
		Comparator dataFieldComparator = sortAscending ? dataGridColumnComponent.getAscendingComparator() : 
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
		Map sessionMap = context.getExternalContext().getSessionMap();
		sessionMap.put(getId(), this);
		
	}
	
	public void decode(FacesContext context) {
		super.decode(context);
		
		/*
		 * No longer needed, so remove the content.
		 * Below is a pure HACK till JSF 2.0.
		 */
		Map sessionMap = context.getExternalContext().getSessionMap();
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
	
	public Map getDataGridColumnComponentMapping(){
		return _dataGridColumnComponentMapping;
	}
	
}
