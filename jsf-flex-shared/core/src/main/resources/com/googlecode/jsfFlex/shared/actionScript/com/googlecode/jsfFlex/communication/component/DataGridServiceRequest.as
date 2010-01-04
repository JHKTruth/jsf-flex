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
 
/**
 * TODO : Implement better later
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.component
{
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.DragSource;
	import mx.core.UIComponent;
	import mx.events.CollectionEvent;
	import mx.events.CollectionEventKind;
	import mx.events.DataGridEvent;
	import mx.events.DragEvent;
	import mx.managers.DragManager;
	import mx.rpc.events.ResultEvent;
	
	import com.googlecode.jsfFlex.communication.event.helper.ScrollEventHelper;
	import com.googlecode.jsfFlex.communication.logger.ILogger;
	import com.googlecode.jsfFlex.communication.logger.LoggerFactory;
	import com.googlecode.jsfFlex.communication.services.JsfFlexHttpService;
	import com.googlecode.jsfFlex.communication.utils.WebConstants;
	
	public class DataGridServiceRequest {
		
		private static const ADD_DATA_ENTRY_SERVICE_REQUEST_URL:String = WebConstants.WEB_CONTEXT_PATH 
																			+ "/jsfFlexHttpServiceRequestListener/addDataEntryServiceRequest";
		private static const REMOVE_DATA_ENTRY_SERVICE_REQUEST_URL:String = WebConstants.WEB_CONTEXT_PATH 
																			+ "/jsfFlexHttpServiceRequestListener/removeDataEntryServiceRequest";
		private static const SORT_DATA_ENTRY_SERVICE_REQUEST_URL:String = WebConstants.WEB_CONTEXT_PATH 
																						+ "/jsfFlexHttpServiceRequestListener/sortDataEntryServiceRequest";
		
		private static const ADD_DATA_ENTRY:String = "addDataEntry";
		private static const REMOVE_DATA_ENTRY:String = "removeDataEntry";
		private static const SORT_DATA_ENTRY:String = "sortDataEntry";
		
		private static const ADD_DATA_ENTRY_DELIM:String = "_";
		private static const DRAG_SOURCE_DATA:String = "items";
		
		private static var _log:ILogger;
		
		/*
		 * Field for tracking modification of values within dataProvider
		 */
		private var _currDataFieldWithFocus:String;
		private var _disableEditPosition:int;
		
		/*
		 * Fields for tracking scroll event, meaning whether to request for 
		 * additional data from the server side if _dataPartitioned is set to true
		 */
		private var _checkingScrollState:Boolean;
		private var _scrollEnabled:Boolean;
		private var _scrollEventHelper:ScrollEventHelper;
		
		/*
		 * Fields for tracking sorting event of columns, meaning to sort the data 
		 * on the server side
		 */
		private var _currColumnSortedAscending:Boolean;
		private var _currColumnSortedDataField:String;
		
		/*
		 * Fields for data partitioning
		 */
		private var _batchColumnDataRetrievalSize:uint;
		private var _cacheSize:uint;
		private var _currentInitialHalfDataPartitionIndex:uint;
		private var _dataPartitioned:Boolean;
		private var _maxDataPartitionIndex:uint;
		
		/*
		 * Flag if > 0 means waiting for data from the server
		 */
		private var _numberOfWaitingColumnDataInfo:uint;
		
		private var _dataFieldToDataGridColumnEntriesDictionary:Dictionary;
		private var _dataGridComp:DataGrid;
		private var _dataGridCompEditable:Boolean;
		private var _dataGridDataProvider:ListCollectionView;
		
		{
			_log = LoggerFactory.newJSLoggerInstance(DataGridServiceRequest);
		}
		
		public function DataGridServiceRequest(dataGridId:String, batchColumnDataRetrievalSize:uint, 
												maxDataPartitionIndex:uint, refApp:UIComponent) {
			super();
			_dataGridComp = refApp[dataGridId];
			_dataGridComp.variableRowHeight = true;
			_dataGridCompEditable = _dataGridComp.editable;
			
			_dataFieldToDataGridColumnEntriesDictionary = new Dictionary();
			
			/*
			 * Internal setting of the fields for possible dataPartitioning 
			 */
			resetDataPartitionParameters(maxDataPartitionIndex, batchColumnDataRetrievalSize);
		}
		
		/*
		 * This method will be invoked after the constructor has been created and all DataGridColumnServiceRequest
		 * objects have been added to the current instance. Main purpose is to have a single loop to set the reference
		 * to DataGridColumn component and to start off ScrollEventHelper if data has been partitioned
		 */
		public function initialize():void {
			
			for each(var dataGridColumn:DataGridColumn in _dataGridComp.columns){
				var currDataGridColumnEntry:Object = _dataFieldToDataGridColumnEntriesDictionary[dataGridColumn.dataField];
				currDataGridColumnEntry.dataGridColumn = dataGridColumn;
			}
			
			_currColumnSortedAscending = true;
			_currColumnSortedDataField = _dataGridComp.columns[0].dataField;
			
			getDataGridColumnInfo(_currentInitialHalfDataPartitionIndex, 0);
			
			if(_dataPartitioned){
				//get the second half as well
				getDataGridColumnInfo((_currentInitialHalfDataPartitionIndex + 1), _batchColumnDataRetrievalSize);
			}
			
		}
		
		private function resetDataPartitionParameters(maxDataPartitionIndex:uint, batchColumnDataRetrievalSize:uint):void {
			
			_maxDataPartitionIndex = maxDataPartitionIndex;
			_batchColumnDataRetrievalSize = batchColumnDataRetrievalSize;
			
			_dataPartitioned = _maxDataPartitionIndex > 0;
			_cacheSize = _dataPartitioned ? (_batchColumnDataRetrievalSize * 2) : _batchColumnDataRetrievalSize;
			
			_scrollEnabled = _maxDataPartitionIndex > 1;
			if(_scrollEnabled && _scrollEventHelper == null){
				_scrollEventHelper = new ScrollEventHelper(_dataGridComp, this, scrollAdditionalDataRetrievalCheck);
			}
			
			if(!_scrollEnabled && _scrollEventHelper != null){
				_scrollEventHelper.deActivateListener();
			}
			
			clearDataGridDataProvider();
		}
		
		private function clearDataGridDataProvider():void {
			
			/*
			 * remove all the entries within dataProvider and add empty objects with hidden row index
			 */
			_dataGridDataProvider = new ArrayCollection();
			
			for(var i:uint=0; i < _cacheSize; i++){
				_dataGridDataProvider.addItem(new Object());
			}
			
			_dataGridComp.dataProvider = _dataGridDataProvider;
			
		}
		
		private function computeActualCacheStartIndex():uint {
			return _currentInitialHalfDataPartitionIndex * _batchColumnDataRetrievalSize;
		}
		
		public function addDataGridColumServiceRequest(dataGridColumnId:String, dataField:String, columnEditable:Boolean):void {
			//provide whether column is editable or not
			var dataGridColumnEditable:Boolean = columnEditable ? true : _dataGridCompEditable;
			var dataGridColumnServiceRequest:DataGridColumnServiceRequest = new DataGridColumnServiceRequest(dataGridColumnId, dataField, 
																					dataGridColumnEditable, this);
			
			_dataFieldToDataGridColumnEntriesDictionary[dataField] = {dataGridColumnServiceRequest: dataGridColumnServiceRequest, dataGridColumn: null };
			_log.debug("Added dataGridColumnId : " + dataGridColumnId + " for dataField : " + dataField + " with columnEditable : " + 
						columnEditable + " to DataGridServiceRequest");
		}
		
		public function getDataGridColumnInfo(dataFetchPartitionIndex:uint, populateCacheStartIndex:uint):void {
			/*
			 * below set is for getDataGridColumnInfo method call. When
			 * data is returned by each DataGridColumnServiceRequest, this field will be
			 * decremented and when it reaches 0 the instance will start listening for 
			 * possible modification and possible scrolling for additional data.
			 */
			_numberOfWaitingColumnDataInfo += _dataGridComp.columns.length;
			
			_disableEditPosition = _cacheSize;
			
			var dataStartIndex:uint = dataFetchPartitionIndex * _batchColumnDataRetrievalSize;
			var dataEndIndex:uint = dataStartIndex + _batchColumnDataRetrievalSize;
			
			for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
				//disable editing for DataGridColumn component
				dataGridColumnEntry.dataGridColumn.editable = false;
				dataGridColumnEntry.dataGridColumnServiceRequest.getDataColumnInfo(dataStartIndex, dataEndIndex, populateCacheStartIndex);
			}
		}
		
		public function flushCacheChanges():void {
			deActivateListener();
			
			for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
				dataGridColumnEntry.dataGridColumnServiceRequest.flushCacheChanges();
			}
		}
		
		public function scrollAdditionalDataRetrievalCheck():void {
			_scrollEventHelper.lockScrollParameters();
			
			if(_numberOfWaitingColumnDataInfo > 0 || _checkingScrollState || _dataGridComp.selectedIndices.length > 1){
				/*
				 * There will be a condition where additional information will NOT be fetched if selectedIndices is
				 * greater than 1.  This is because if the user desires drag + drop action, currently selected selectedIndices 
				 * will be cleared out when fetching for additional data.
				 */
				_scrollEventHelper.unLockScrollParameters();
				return;
			}
			
			_checkingScrollState = true;
			
			var fetchData:Boolean;
			var dataFetchPartitionIndex:uint;
			var selectedIndex:int = -1;
			var viewScrollPosition:int;
			
			var cacheIndex:uint = Math.floor( (_scrollEventHelper.verticalScrollPosition / _batchColumnDataRetrievalSize) );
			var currentDataPartitionIndex:uint = cacheIndex + _currentInitialHalfDataPartitionIndex;
			if(_scrollEventHelper.scrolledDown){
				if(cacheIndex == 1 && ((_batchColumnDataRetrievalSize - (_scrollEventHelper.verticalScrollPosition % _batchColumnDataRetrievalSize)) 
						< _dataGridComp.rowCount) && (currentDataPartitionIndex < _maxDataPartitionIndex)){
					/*
					 * increment _currentInitialHalfDataPartitionIndex and fetch the data
					 */
					
					_currentInitialHalfDataPartitionIndex++;
					dataFetchPartitionIndex = currentDataPartitionIndex + 1;
					
					if(_dataGridComp.selectedIndex != -1){
						selectedIndex = _dataGridComp.selectedIndex - _batchColumnDataRetrievalSize - 1;
					}
					viewScrollPosition = _dataGridComp.verticalScrollPosition - _batchColumnDataRetrievalSize;
					fetchData = true;
				}
			}else{
				if(cacheIndex == 0 && ((_scrollEventHelper.verticalScrollPosition % _batchColumnDataRetrievalSize) < _dataGridComp.rowCount)
						&& currentDataPartitionIndex > 0){
					/*
					 * decrement _currentInitialHalfDataPartitionIndex and fetch the data
					 */
					
					_currentInitialHalfDataPartitionIndex--;
					dataFetchPartitionIndex = currentDataPartitionIndex - 1;
					
					if(_dataGridComp.selectedIndex != -1){
						selectedIndex = _dataGridComp.selectedIndex + _batchColumnDataRetrievalSize + 1;
					}
					viewScrollPosition = _dataGridComp.verticalScrollPosition + _batchColumnDataRetrievalSize;
					fetchData = true;
				}
			}
			
			if(fetchData){
				/*
				 * Need to fetch additional data, note that .5 cacheSize will 
				 * be fetched with the other .5 remaining for better user experience
				 */
				deActivateListener();
				alterPropertiesForScrollServiceRequest(dataFetchPartitionIndex, viewScrollPosition, selectedIndex);
				var populateCacheStartIndex:uint = !_scrollEventHelper.scrolledDown ? 0 : _batchColumnDataRetrievalSize;
				_log.debug("Fetching additional data due to scroll with dataFetchPartitionIndex : " + dataFetchPartitionIndex + " and populateCacheStartIndex : " + populateCacheStartIndex);
				getDataGridColumnInfo(dataFetchPartitionIndex, populateCacheStartIndex);
			}else{
				_scrollEventHelper.resetState(_dataGridComp.verticalScrollPosition);
				_scrollEventHelper.unLockScrollParameters();
			}
			
			_checkingScrollState = false;
			
		}
		
		internal function get batchColumnDataRetrievalSize():uint {
			return _batchColumnDataRetrievalSize;
		}
		
		internal function get dataGridDataProvider():ListCollectionView {
		    return _dataGridDataProvider;
		}
		
		internal function get dataGridId():String {
			return _dataGridComp.id;
		}
		
		internal function set disableEditPosition(disableEditPosition:int):void {
			_disableEditPosition = disableEditPosition;
		}
		
		internal function notifyRetrievalOfColumnData():void {
			
			_numberOfWaitingColumnDataInfo--;
			
			if(_numberOfWaitingColumnDataInfo == 0){
				//start listening for changes if editable
				
				activateListener();
				
				_dataGridComp.editable = _dataGridCompEditable;
				for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
					//set the editable field for DataGridColumn component
					dataGridColumnEntry.dataGridColumn.editable = dataGridColumnEntry.dataGridColumnServiceRequest.dataGridColumnEditable;
				}
				
				var dataHeightStartIndex:uint = _dataGridComp.verticalScrollPosition;
				_dataGridComp.height = _dataGridComp.measureHeightOfItems(dataHeightStartIndex, _dataGridComp.rowCount) + _dataGridComp.headerHeight;
				
				_dataGridComp.invalidateList();
				
				_log.debug("All data request returned so have activated listener and other setting for " + _dataGridComp.id);
			}
			
		}
		
		private function activateListener():void {
			
			if(_scrollEnabled){
				_scrollEventHelper.unLockScrollParameters();
				_scrollEventHelper.activateListener();
			}
			
			if(_dataGridComp.dragEnabled){
				_dataGridComp.addEventListener(DragEvent.DRAG_COMPLETE, dragSourceDragCompleteListener, false, 0, true);
			}
			
			if(_dataGridComp.dropEnabled){
				_dataGridComp.addEventListener(DragEvent.DRAG_DROP, dragSourceDragDropListener, false, 0, true);
			}
			
			_dataGridComp.addEventListener(DataGridEvent.HEADER_RELEASE, columnSortListener, false, -10, true);
			
			_dataGridComp.addEventListener(DataGridEvent.ITEM_EDIT_BEGINNING, itemEditBeginListener, false, 0, true);
			_dataGridComp.addEventListener(DataGridEvent.ITEM_EDIT_END, initialValueListener, false, 0, true);
			_dataGridComp.addEventListener(DataGridEvent.ITEM_EDIT_END, possiblyModifiedValueListener, false, -60, true);
			
			for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
				dataGridColumnEntry.dataGridColumnServiceRequest.activateRequestCacheChangeFlushListener();
			}
			
		}
		
		private function deActivateListener():void {
			
			if(_scrollEnabled){
				_scrollEventHelper.deActivateListener();
			}
			
			if(_dataGridComp.dragEnabled){
				_dataGridComp.removeEventListener(DragEvent.DRAG_COMPLETE, dragSourceDragCompleteListener);
			}
			
			if(_dataGridComp.dropEnabled){
				_dataGridComp.removeEventListener(DragEvent.DRAG_DROP, dragSourceDragDropListener);
			}
			
			_dataGridComp.removeEventListener(DataGridEvent.HEADER_RELEASE, columnSortListener);
			
			_dataGridComp.removeEventListener(DataGridEvent.ITEM_EDIT_BEGINNING, itemEditBeginListener);
			_dataGridComp.removeEventListener(DataGridEvent.ITEM_EDIT_END, initialValueListener);
			_dataGridComp.removeEventListener(DataGridEvent.ITEM_EDIT_END, possiblyModifiedValueListener);
			
			for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
				dataGridColumnEntry.dataGridColumnServiceRequest.deActivateRequestCacheChangeFlushListener();
			}
			
		}
		
		private function alterPropertiesForScrollServiceRequest(dataFetchPartitionIndex:uint, viewScrollPosition:int, selectedIndex:int):void {
			
			//disable editing for the DataGrid component
			_dataGridComp.editable = false;
			
			var removeIndex:uint = _scrollEventHelper.scrolledDown ? 0 : _batchColumnDataRetrievalSize;
			
			/*
			 * First remove the elements
			 */
			for(var p:uint = 0; p < _batchColumnDataRetrievalSize; p++){
				_dataGridDataProvider.removeItemAt(removeIndex);
			}
			
			/*
			 * Now add empty elements
			 */
			if(_scrollEventHelper.scrolledDown){
				
				for(var i:uint = 0; i < _batchColumnDataRetrievalSize; i++){
					_dataGridDataProvider.addItem(new Object());
				}
			}else{
				
				for(var k:uint = 0; k < _batchColumnDataRetrievalSize; k++){
					_dataGridDataProvider.addItemAt(new Object(), k);
				}
			}
			
			viewScrollPosition = viewScrollPosition < 0 ? 0 : viewScrollPosition;
			_dataGridComp.scrollToIndex(viewScrollPosition);
			_scrollEventHelper.resetState(viewScrollPosition);
			
			/*
			 * HACK to set the selectedIndex field
			 */
			if(selectedIndex != -1){
				_dataGridComp.selectedIndex = selectedIndex < 0 ? 0 : selectedIndex;
			}
		}
		
		private function columnSortListener(event:DataGridEvent):void {
			
			event.preventDefault();
			deActivateListener();
			
			flushCacheChanges();
			
			var sortColumnDataField:String = event.dataField;
			var scrollPosition:uint = _dataGridComp.verticalScrollPosition;
			
			_currColumnSortedAscending = sortColumnDataField == _currColumnSortedDataField ? !_currColumnSortedAscending : _currColumnSortedAscending;
			_currColumnSortedDataField = sortColumnDataField;
			
			clearDataGridDataProvider();
			
			_dataGridComp.scrollToIndex(scrollPosition);
			
			if(_scrollEnabled){
				_scrollEventHelper.resetState(scrollPosition);
			}
			
			/*
			 * HACK to display the sort arrow
			 */
			_dataGridDataProvider.sort = new Sort();
			_dataGridDataProvider.sort.fields = [new SortField(_currColumnSortedDataField, false, _currColumnSortedAscending)];
			var refreshEvent:CollectionEvent = new CollectionEvent(CollectionEvent.COLLECTION_CHANGE);
            refreshEvent.kind = CollectionEventKind.REFRESH;
            _dataGridDataProvider.dispatchEvent(refreshEvent);
			
			var sortRequestParameters:Object = new Object();
			sortRequestParameters.componentId = _dataGridComp.id;
			sortRequestParameters.methodToInvoke = SORT_DATA_ENTRY;
			sortRequestParameters.columnDataFieldToSortBy = _currColumnSortedDataField;
			sortRequestParameters.sortAscending = _currColumnSortedAscending;
			
			_log.debug("Requesting column sort with columnDataFieldToSortBy : " + _currColumnSortedDataField + " and sortAscending : " + _currColumnSortedAscending);
			var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
			jsfFlexHttpServiceRequest.sendHttpRequest(SORT_DATA_ENTRY_SERVICE_REQUEST_URL, this,
															function (lastResult:Object, event:ResultEvent):void {
																
																var resultCode:String = lastResult.resultCode;
																
																_log.info("Returned from : " + SORT_DATA_ENTRY_SERVICE_REQUEST_URL + 
																			" of " + _dataGridComp.id + " with resultCode : " + resultCode);
																if(resultCode == "true"){
																	//now fetch the new data
																	getDataGridColumnInfo(_currentInitialHalfDataPartitionIndex, 0);
																	
																	if(_dataPartitioned){
																		getDataGridColumnInfo((_currentInitialHalfDataPartitionIndex + 1), _batchColumnDataRetrievalSize);
																	}
																}
																
															}, sortRequestParameters, JsfFlexHttpService.GET_METHOD, JsfFlexHttpService.FLASH_VARS_RESULT_FORMAT, null);
			
		}
		
		private function dragSourceDragDropListener(event:DragEvent):void {
			
			if(event.action == DragManager.COPY || event.action == DragManager.MOVE){
				event.preventDefault();
				
				var targetObj:DataGrid = event.currentTarget as DataGrid;
				targetObj.hideDropFeedback(event);
				
				flushCacheChanges();
				
				var dragSource:DragSource = event.dragSource;
				var dragSourceEntries:Array = dragSource.dataForFormat(DRAG_SOURCE_DATA) as Array;
				var dropIndex:int = _dataGridComp.calculateDropIndex(event);
				
				/*
				 * Have the dropIndex, so send the request to add the entries to the backEnd
				 * [note that one needs to sort the entries before returning]
				 */
				var currentActualCacheStartIndex:uint = computeActualCacheStartIndex();
				var addEntryStartIndex:int = _dataGridDataProvider.length > dropIndex ? currentActualCacheStartIndex + dropIndex : 0;
				var addEntryEndIndex:int = addEntryStartIndex + dragSourceEntries.length;
				
				var addDataRequestParameters:Object = new Object();
				addDataRequestParameters.componentId = _dataGridComp.id;
				addDataRequestParameters.methodToInvoke = ADD_DATA_ENTRY;
				addDataRequestParameters.addEntryStartIndex = addEntryStartIndex;
				addDataRequestParameters.addEntryEndIndex = addEntryEndIndex;
				
				var index:uint = 0;
				for each(var dragSourceEntry:Object in dragSourceEntries){
					
					for each(var dataGridColumnEntry:Object in _dataFieldToDataGridColumnEntriesDictionary){
						
						addDataRequestParameters[String(dataGridColumnEntry.dataGridColumn.dataField) + ADD_DATA_ENTRY_DELIM + index] = 
									dragSourceEntry[dataGridColumnEntry.dataGridColumn.dataField];
					}
					index++;
				}
				
				_log.debug("Requesting of addition of data of length : " + dragSourceEntries.length + " at position : " + addEntryStartIndex + " due to drag + drop event");
				var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
				jsfFlexHttpServiceRequest.sendHttpRequest(ADD_DATA_ENTRY_SERVICE_REQUEST_URL, this,
																function (lastResult:Object, event:ResultEvent):void {
																	
																	var resultCode:String = lastResult.resultCode;
																	
																	_log.info("Returned from : " + ADD_DATA_ENTRY_SERVICE_REQUEST_URL + 
																				" of " + _dataGridComp.id + " with resultCode : " + resultCode);
																	if(resultCode == "true"){
																		resetDataPartitionParameters(parseInt(lastResult.maxDataPartitionIndex), 
																										parseInt(lastResult.batchColumnDataRetrievalSize));
																		_log.debug("Have reset maxDataPartitionIndex : " + _maxDataPartitionIndex + " _batchColumnDataRetrievalSize : " +
																					_batchColumnDataRetrievalSize + " of " + _dataGridComp.id);
																		//now fetch the new data
																		getDataGridColumnInfo(_currentInitialHalfDataPartitionIndex, 0);
																		
																		if(_dataPartitioned){
																			getDataGridColumnInfo((_currentInitialHalfDataPartitionIndex + 1), _batchColumnDataRetrievalSize);
																		}
																	}
																	
																}, addDataRequestParameters, JsfFlexHttpService.POST_METHOD, JsfFlexHttpService.FLASH_VARS_RESULT_FORMAT, null);
				
			}
			
		}
		
		private function dragSourceDragCompleteListener(event:DragEvent):void {
			
			if(event.action == DragManager.MOVE){
				event.preventDefault();
				
				DragManager.showFeedback(DragManager.MOVE);
				
				var targetObj:DataGrid = event.currentTarget as DataGrid;
				var selectedIndices:Array = targetObj.selectedIndices;
				
				/*
				 * Moved the entries, so send the request to remove them from the backEnd
				 */
				var removeDataRequestParameters:Object = new Object();
				removeDataRequestParameters.componentId = _dataGridComp.id;
				removeDataRequestParameters.methodToInvoke = REMOVE_DATA_ENTRY;
				
				var currentActualCacheStartIndex:uint = computeActualCacheStartIndex();
				var deleteIndices:String = "";
				for(var i:uint=0; i < selectedIndices.length; i++){
					deleteIndices += (currentActualCacheStartIndex + selectedIndices[i]) + ",";
				}
				
				removeDataRequestParameters.deleteIndices = deleteIndices;
				
				_log.debug("Requesting of deletion of indices " + deleteIndices + " due to drag + drop event");
				var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
				jsfFlexHttpServiceRequest.sendHttpRequest(REMOVE_DATA_ENTRY_SERVICE_REQUEST_URL, this,
																function (lastResult:Object, event:ResultEvent):void {
																	
																	var resultCode:String = lastResult.resultCode;
																	
																	_log.info("Returned from : " + REMOVE_DATA_ENTRY_SERVICE_REQUEST_URL + 
																				" of " + _dataGridComp.id + " with resultCode : " + resultCode);
																	if(resultCode == "true"){
																		resetDataPartitionParameters(parseInt(lastResult.maxDataPartitionIndex), 
																										parseInt(lastResult.batchColumnDataRetrievalSize));
																		
																		_log.debug("Have reset maxDataPartitionIndex : " + _maxDataPartitionIndex + " _batchColumnDataRetrievalSize : " +
																					_batchColumnDataRetrievalSize + " of " + _dataGridComp.id);
																		//now fetch the new data
																		getDataGridColumnInfo(_currentInitialHalfDataPartitionIndex, 0);
																		
																		if(_dataPartitioned){
																			getDataGridColumnInfo((_currentInitialHalfDataPartitionIndex + 1), _batchColumnDataRetrievalSize);
																		}
																	}
																	
																}, removeDataRequestParameters, JsfFlexHttpService.POST_METHOD, JsfFlexHttpService.FLASH_VARS_RESULT_FORMAT, null);
			}
			
		}
		
		private function itemEditBeginListener(event:DataGridEvent):void {
			_log.debug("Edit rowIndex is : " + event.rowIndex + " with disableEditPosition : " + _disableEditPosition);
			if(event.rowIndex >= _disableEditPosition){
				event.preventDefault();
			}
		}
		
		private function initialValueListener(event:DataGridEvent):void {
			_currDataFieldWithFocus = _dataGridComp.selectedItem[event.dataField];
		}
		
		private function possiblyModifiedValueListener(event:DataGridEvent):void {
			var currDataField:String = event.dataField;
			var possiblyUpdatedValue:String = _dataGridComp.selectedItem[currDataField];
			if(possiblyUpdatedValue == _currDataFieldWithFocus){
				return;
			}
			
			var dataGridColumnServiceRequest:DataGridColumnServiceRequest = _dataFieldToDataGridColumnEntriesDictionary[currDataField].dataGridColumnServiceRequest;
			var currentActualCacheStartIndex:uint = computeActualCacheStartIndex();
			var actualRowIndex:int = currentActualCacheStartIndex + event.rowIndex;
			dataGridColumnServiceRequest.addModifiedDataField({actualRowIndex: actualRowIndex, modifiedValue: possiblyUpdatedValue});
			_log.debug("Added the modified dataField " + possiblyUpdatedValue + "at " + actualRowIndex + " to " + dataGridColumnServiceRequest.columnId);
		}
		
	}
	
}