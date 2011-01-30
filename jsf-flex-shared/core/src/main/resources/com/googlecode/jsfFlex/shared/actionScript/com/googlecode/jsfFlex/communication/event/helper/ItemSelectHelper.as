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
 * TODO : Implement better later, especially how the selection is stored and cleared
 * In a nutshell, this class provides holding the selection of items as delta Array.
 *
 * @author Ji Hoon Kim
 */
package com.googlecode.jsfFlex.communication.event.helper
{
	import mx.controls.listClasses.ListBase;
	import mx.events.ListEvent;
	
	public class ItemSelectHelper {
		
		private var _componentInstance:ListBase;
		private var _deltaSelectedArray:Array;
		private var _deltaDeselectedArray:Array;
		private var _selectAll:Boolean;
		private var _deselectAll:Boolean;
		
		public function ItemSelectHelper(componentInstance:ListBase) {
			super();
			
			_componentInstance = componentInstance;
			
			/*
			 * Since the selected rows are usually sparse, will use an Array to keep track 
			 * of row positions that have been selected + deselected. These entries will periodically
			 * be sent to the server side for synch up and will only represent delta.
			 */
			_deltaSelectedArray = [];
			_deltaDeselectedArray = [];
			componentInstance.addEventListener(ListEvent.CHANGE, selectionChangeListener, false, 0, true);
		}
		
		public function get deltaSelectedArray():Array {
			return _deltaSelectedArray;
		}
		
		public function get deltaDeselectedArray():Array {
			return _deltaDeselectedArray;
		}
		
		public function get deselectAll():Boolean {
			return _deselectAll;
		}
		
		public function set deselectAll(deselectAll:Boolean):void {
			_deselectAll = deselectAll;
		}
		
		public function get selectAll():Boolean {
			return _selectAll;
		}
		
		public function set selectAll(selectAll:Boolean):void {
			_selectAll = selectAll;
		}
		
		public function clearDeltas():void {
			/*
			 * This method should be invoked prior to sending the entries to the server side. This way
			 * the entries can be mapped properly while the values are being sent to the server side.
			 * Meaning after this method is invoked it will be assumed that the server's state is the 
			 * client's state.
			 */
			_deltaSelectedArray = [];
			_deltaDeselectedArray = [];
		}
		
		private function selectionChangeListener(event:ListEvent):void {
			var rowIndex:int = event.rowIndex;
			
			/*
			 * If the entry exists within a _delta<name> array remove it since it aligns with 
			 * the server selection. However if the index doesn't exist in both array, then add it
			 * to a specific array depending on whether that row is selected or not.
			 */
			
			var selectedIndex:int = _deltaSelectedArray.indexOf(rowIndex);
			var deselectedIndex:int = _deltaDeselectedArray.indexOf(rowIndex);
			var selectedOrDeselected:int = _componentInstance.selectedIndices.indexOf(rowIndex);
			
			if(selectedIndex == -1 && deselectedIndex == -1){
				if(selectedOrDeselected == -1){
					_deltaDeselectedArray.push(rowIndex);
				}else{
					_deltaSelectedArray.push(rowIndex);
				}
			}else if(selectedIndex == -1){
				_deltaDeselectedArray.splice(deselectedIndex, 1);
			}else if(deselectedIndex == -1){
				_deltaSelectedArray.splice(deselectedIndex, 1);
			}
			
		}
		
	}
	
}