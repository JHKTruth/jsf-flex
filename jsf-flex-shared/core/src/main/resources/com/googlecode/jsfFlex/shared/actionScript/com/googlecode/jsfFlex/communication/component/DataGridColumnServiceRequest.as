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
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.component
{
	import flash.utils.clearInterval;
	import flash.utils.setInterval;
	
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;
	
	import com.googlecode.jsfFlex.communication.logger.ILogger;
	import com.googlecode.jsfFlex.communication.logger.LoggerFactory;
	import com.googlecode.jsfFlex.communication.services.JsfFlexHttpService;
	import com.googlecode.jsfFlex.communication.utils.WebConstants;
	
	internal class DataGridColumnServiceRequest {
		
		private static const UPDATE_MODIFIED_DATA_FIELD_SERVICE_REQUEST_URL:String = WebConstants.WEB_CONTEXT_PATH 
																						+ "/jsfFlexHttpServiceRequestListener/updateModifiedDataFieldServiceRequest";
		private static const UPDATE_MODIFIED_DATA_FIELD:String = "updateModifiedDataField";
		
		private static var _log:ILogger;
		
		private var _columnId:String;
		private var _dataField:String;
		private var _dataGridColumnEditable:Boolean;
		
		private var _modifiedDataFieldObjectArray:Array;
		
		private var _dataGridServiceRequest:DataGridServiceRequest;
		private var _clearIntervalRef:int;
		
		{
			_log = LoggerFactory.newJSLoggerInstance(DataGridColumnServiceRequest);
		}
		
		public function DataGridColumnServiceRequest(columnId:String, dataField:String, 
														dataGridColumnEditable:Boolean, dataGridServiceRequest:DataGridServiceRequest) {
			super();
			_columnId = columnId;
			_dataField = dataField;
			_dataGridColumnEditable = dataGridColumnEditable;
			
			_modifiedDataFieldObjectArray = [];
			
			_dataGridServiceRequest = dataGridServiceRequest;
			_clearIntervalRef = -1;
		}
		
		internal function activateRequestCacheChangeFlushListener():void {
			if(_clearIntervalRef == -1){
				_clearIntervalRef = setInterval( requestCacheChangeFlush, 8000);
			}
		}
		
		internal function deActivateRequestCacheChangeFlushListener():void {
			clearInterval(_clearIntervalRef);
			_clearIntervalRef = -1;
		}
		
		internal function flushCacheChanges():void {
			_log.debug("Was informed to flushCacheChanges explicitly with unflushed cache changes of length : " + _modifiedDataFieldObjectArray.length);
			requestCacheChangeFlush();
		}
		
		internal function addModifiedDataField(modifiedDataFieldObject:Object):void {
			_modifiedDataFieldObjectArray.push(modifiedDataFieldObject);
		}
		
		internal function get columnId():String {
			return _columnId;
		}
		
		internal function get dataField():String {
			return _dataField;
		}
		
		internal function get dataGridColumnEditable():Boolean {
			return _dataGridColumnEditable;
		}
		
		internal function updateColumnDisplayEntries(columnEntries:ListCollectionView, populateCacheStartIndex:uint, fullDataRequest:Boolean):void {
			var dataGridDataProvider:ListCollectionView = _dataGridServiceRequest.dataGridDataProvider;
			
			var k:uint = 0;
			for(; k < columnEntries.length; k++, populateCacheStartIndex++){
				var currObject:Object = dataGridDataProvider.getItemAt(populateCacheStartIndex);
				currObject[_dataField] = columnEntries.getItemAt(k).toString();
			}
			
			/*
			 * if columnEntries length < batchColumnDataRetrievalSize,
			 * must populate the remaining entries with empty data and 
			 * set the disableEditPosition for DataGridServiceRequest, so user 
			 * won't be able to modify the values from thereforth.
			 */
			var endIndex:uint = fullDataRequest ? _dataGridServiceRequest.cacheSize : _dataGridServiceRequest.batchColumnDataRetrievalSize;
			 
			if(k < endIndex){
				_dataGridServiceRequest.disableEditPosition = populateCacheStartIndex;
				for(; k < endIndex; k++, populateCacheStartIndex++){
					dataGridDataProvider.setItemAt({}, populateCacheStartIndex);
				}
			}
		}
		
		private function requestCacheChangeFlush():void {
			if(_modifiedDataFieldObjectArray.length == 0){
				return;
			}
			_log.debug("Implicit timed flushCacheChanges invocation with unflushed cache changes of length : " + _modifiedDataFieldObjectArray.length);
			var dataRequestParameters:Object = {};
			dataRequestParameters.componentId = _dataGridServiceRequest.dataGridId;
			dataRequestParameters.COLUMN_DATA_FIELD = _dataField;
			dataRequestParameters.methodToInvoke = UPDATE_MODIFIED_DATA_FIELD;
			
			var requestKeys:String = "";
			while(_modifiedDataFieldObjectArray.length > 0){
				var currModifiedDataFieldObject:Object = _modifiedDataFieldObjectArray.pop();
				requestKeys += currModifiedDataFieldObject.actualRowIndex + ",";
				dataRequestParameters[String(currModifiedDataFieldObject.actualRowIndex)] = currModifiedDataFieldObject.modifiedValue;
			}
			dataRequestParameters.REQUEST_KEYS = requestKeys;
			
			var jsfFlexHttpServiceRequest:JsfFlexHttpService = new JsfFlexHttpService();
			jsfFlexHttpServiceRequest.sendHttpRequest(UPDATE_MODIFIED_DATA_FIELD_SERVICE_REQUEST_URL, this,
															function (lastResult:Object, event:ResultEvent):void {
																
																var resultCode:String = lastResult[WebConstants.RESULT_CODE];
																_log.info("Returned from : " + UPDATE_MODIFIED_DATA_FIELD + 
																			" of " + _dataGridServiceRequest.dataGridId + " with resultCode : " + resultCode);
															}, dataRequestParameters, JsfFlexHttpService.POST_METHOD, JsfFlexHttpService.FLASH_VARS_RESULT_FORMAT, null);
		}
		
	}
	
}