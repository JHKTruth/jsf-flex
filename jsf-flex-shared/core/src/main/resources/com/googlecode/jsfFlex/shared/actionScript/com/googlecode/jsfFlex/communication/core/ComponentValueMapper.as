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
package com.googlecode.jsfFlex.communication.core
{
	import flash.external.ExternalInterface;
	import flash.events.Event;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLVariables;
	import flash.utils.getQualifiedClassName;
	
	import mx.core.UIComponent;
	
	import com.googlecode.jsfFlex.communication.logger.ILogger;
	import com.googlecode.jsfFlex.communication.logger.LoggerFactory;
	import com.googlecode.jsfFlex.communication.utils.WebConstants;
	
	public class ComponentValueMapper {
		
		private static const JSON_RESULT:String = "jsonResult";
		
		private static const LINE_FEED:String = "\n";
		private static const LINE_FEED_ESCAPER:RegExp = /LINE_FEED/g;
		
		private static const APPEND_E4X_ATTR:String = "@append";
		private static const NESTED_E4X_ATTR:String = "@nested";
		private static const DYNAMIC_E4X_ATTR:String = "@dynamic";
		
		private static const AS_GET_COMP_VALUE_FUNCTION:String = "getCompValue";
		private static const COMP_VALUE_MAPPER:String = WebConstants.WEB_CONTEXT_PATH + "/swf/componentValueMapper.xml";
		private static const NULL_STRING:String = "null";
		private static const VALUE_ASSIGNMENT_STATEMENT:String = "value=";
		private static const VALUE_ATTR:String = "VALUE";
		
		private static var _compValueMapper:XML;
		private static var _loader:URLLoader;
		
		private static var _log:ILogger;
		
		private var _refApp:UIComponent;
		
		{
			_log = LoggerFactory.newJSLoggerInstance(ComponentValueMapper);
			
			_loader = new URLLoader();
			
			_loader.addEventListener(Event.COMPLETE, function (event:Event):void {
										_loader.removeEventListener(Event.COMPLETE, arguments.callee);
										_compValueMapper = new XML(_loader.data);
										_loader = null;
										_log.info("Have loaded componentValueMapper.xml");
									});
			
			try{
				_loader.load(new URLRequest(COMP_VALUE_MAPPER));
			}catch(loadingError:Error){
				trace("Failure in loading of the componentValueMapper.xml file");
				_log.error("Failure in loading of the componentValueMapper.xml file");
			}
		}
		
		public function ComponentValueMapper(refApp:UIComponent) {
			super();
			_refApp = refApp;
		}
		
		public function initialize():void {
			
			try{
				ExternalInterface.addCallback(AS_GET_COMP_VALUE_FUNCTION, this.getCompValue);
			}catch(callBackError:Error){
				trace("Failure in setting up of getCompValue callBack");
				_log.error("Failure in setting up of getCompValue callBack");
			}
			_log.info("Finished with the initialization of " + _refApp["id"]);
		}
		
		public function populateInitValues(appInfo:Object):void {
			var initValueObjects:Array = appInfo.initValueObjects;
			
			for each (var currInitValueObject:Object in initValueObjects){
				
				if(currInitValueObject.id == null || currInitValueObject.initValues == null){
					continue;
				}
				
				var currId:String = currInitValueObject.id as String;
				var initValues:Array = currInitValueObject.initValues;
				for each (var currInitValue:Object in initValues){
					
					var currValue:Object;
					if(currInitValue.attribute != null && (currValue = currInitValue.value) != null && currInitValue.attribute is String){
						
						var currAttr:String = currInitValue.attribute as String;
						
						if(currValue != null){
							var objectRef:Object;
							
							if(currValue is String){
								
								var currValueString:String = currValue as String;
								if(currValueString != NULL_STRING){
									objectRef = _refApp[currId];
									currValueString = unEscapeCharacters(currValueString);
									objectRef[currAttr] = currValueString;
								}
								
							}else{
								
								objectRef = _refApp[currId];
								objectRef[currAttr] = currValue;
								
							}
						}
					}
				}
			}
			
		}
		
		private function unEscapeCharacters(toUnEscape:String):String {
			//TODO : implement this better later
			var toReturn:String = toUnEscape.replace(LINE_FEED_ESCAPER, LINE_FEED);
			var toEscape:URLVariables = new URLVariables();
			var toDecode:String = VALUE_ASSIGNMENT_STATEMENT + toReturn;
			toEscape.decode(toDecode);
			return toEscape.value;
		}
		
		public function getCompValue(id:String):Array {
			if(id == null){
				return null;
			}
			
			var objectRef:Object = _refApp[id];
			var className:String = getQualifiedClassName(objectRef);
			if(className == null){
				return null;
			}
			
			var classInfo:XMLList = getClassInfo(className);
			if(classInfo == null){
				return null;
			}
			
			var classInfoNodes:XMLList = classInfo.node_list.node;
			if(classInfoNodes == null){
				return null;
			}
			
			var valueToReturn:Array = new Array();
			
			for each (var classInfoNode:XML in classInfoNodes){
				var classInfoNodeAttributes:XMLList = classInfoNode.attribute_list.attribute;
				
				for each (var attribute:XML in classInfoNodeAttributes){
					if(attribute.name.toString() == VALUE_ATTR){
						
						var attributeValueObject:Object = getAttributeValue(attribute, objectRef);
						
						if(attributeValueObject.value == null){
							continue;
						}
						
						valueToReturn.push(attributeValueObject);
					}
				}
			}
			
			return valueToReturn;
		}
		
		private function getAttributeValue(attribute:XML, objectRef:Object):Object {
			
			var attributeCheck:XMLList = attribute.value.(hasOwnProperty(APPEND_E4X_ATTR));
			var toAppend:String = (attributeCheck != null && attributeCheck.length() > 0) ? attribute.value.@append.toString() : new String();
			
			attributeCheck = attribute.value.(hasOwnProperty(NESTED_E4X_ATTR));
			var isNested:Boolean = (attributeCheck != null && attributeCheck.length() > 0 && attribute.value.@nested.toString() == "true");
			attributeCheck = attribute.value.(hasOwnProperty(DYNAMIC_E4X_ATTR));
			var isDynamic:Boolean = (attributeCheck != null && attributeCheck.length() > 0 && attribute.value.@dynamic.toString() == "true");
			
			var attributeValue:String;
			var attributeId:String;
			
			if(isNested){
				/*
				 * TODO : consider implementing it better later [you knew this was going to be typed, didn't you???] 
				 * For certain cases [i.e. for RadioButton], one needs to access a reference to an object/property
				 * to get relevant information. The nested XML element symbolizes the object/property and the
				 * last element will represent the relevant value that one desires for.
				 */
				var nestedObjects:XMLList = attribute.value.nested;
				for(var k:uint=0; k < nestedObjects.length(); k++){
					if( k == (nestedObjects.length() - 1) ){
						//now set the attribute
						attributeId = nestedObjects[k].toString();
						attributeValue = objectRef[attributeId];
						if(toAppend != null && toAppend.length > 0){
							attributeValue += toAppend;
						}
						break;
					}
					objectRef = objectRef[nestedObjects[k].toString()];
					if(objectRef == null){
						attributeId = null;
						attributeValue = null;
						trace("Failure in getting access to reference " + nestedObjects[k].toString());
						_log.warn("Failure in getting access to reference " + nestedObjects[k].toString());
						break;
					}
				}
			}else{
				attributeId = attribute.value.toString();
				attributeValue = isDynamic ? objectRef[attributeId] : attributeId;
				if(toAppend != null && toAppend.length > 0){
					attributeValue += toAppend;
				}
			}
			
			return {id: attributeId, value: attributeValue};
		}
		
		public function getJSON(appInfo:Object):Object {
			var retVal:Array = new Array();
			var initValueObjects:Array = appInfo.initValueObjects;
			
			for each (var currInitValueObject:Object in initValueObjects){
				var inspectedObject:Object = objectInfo(appInfo.appId, currInitValueObject.id as String);
				if(inspectedObject != null){
					retVal.push(inspectedObject);
				}
			}
			return {type: JSON_RESULT, result: retVal};
		}
		
		private function objectInfo(_appId:String, objectToGet:String):Object {
			if(_appId == null || objectToGet == null){
				return null;
			}
			
			var objectRef:Object = _refApp[objectToGet];
			var className:String = getQualifiedClassName(objectRef);
			if(className == null){
				return null;
			}
			
			var classInfo:XMLList = getClassInfo(className);
			if(classInfo == null){
				return null;
			}
			
			var classInfoNodes:XMLList = classInfo.node_list.node;
			if(classInfoNodes == null){
				return null;
			}
			
			var nodes:Array = new Array();
			
			for each (var classInfoNode:XML in classInfoNodes){
				var classInfoNodeAttributes:XMLList = classInfoNode.attribute_list.attribute;
				if(classInfoNodeAttributes == null){
					continue;
				}
				
				var attributes:Array = new Array();
				
				for each (var classInfoNodeAttribute:XML in classInfoNodeAttributes){
					
					var attributeValueObject:Object = getAttributeValue(classInfoNodeAttribute, objectRef);
					
					if(attributeValueObject.value == null){
						continue;
					}
					
					attributes.push({attribute: classInfoNodeAttribute.name.toString(), value: attributeValueObject.value});
					
				}
				nodes.push({htmlType: classInfoNode.html_type.toString(), attributeArray: attributes});
			}
			
			return nodes;
		}
		
		private function getClassInfo(className:String):XMLList {
			return _compValueMapper.class_info.(class_name.indexOf(className) == 0);
		}
		
	}
	
}