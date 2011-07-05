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
package com.googlecode.jsfFlex.renderkit.annotationDocletParser;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * @author Ji Hoon Kim
 */
public abstract class _AnnotationDocletParser {
	
	static final String BY_ATTRIBUTE = "byAttribute";
	
	private Set _tokenValueSet;
	private _MXMLMapper _mapper;
	
	_AnnotationDocletParser(){
		super();
	}
	
	{
		_tokenValueSet = new LinkedHashSet();
	}
	
	public Set getTokenValueSet(){
		return _tokenValueSet;
	}
	
	_MXMLMapper getMapper(){
		return _mapper;
	}
	void setMapper(_MXMLMapper mapperToSet){
		_mapper = mapperToSet;
	}
	
	public static String getErrorMessage(String caller, String parameter){
		StringBuffer errorMessage = new StringBuffer();
		errorMessage.append("Exception when ");
		errorMessage.append(caller);
		errorMessage.append(" with parameter(s) [ ");
		errorMessage.append(parameter);
		errorMessage.append(" ] ");
		return errorMessage.toString();
	}
	
	public abstract void mapComponentFields(Class mapClass, Object componentObj, 
												String replaceMappingXML);
	
	abstract class _MXMLMapper {
		
		abstract TokenValue mapField(String tokenName, Object componentObj);
		
	}
	
	final _MXMLMapper MXML_ATTRIBUTE_MAPPER = new _MXMLMapper(){
		TokenValue mapField(String tokenName, Object componentObj) {
			//this class must have Object passed in as a MXMLContract
			_MXMLContract comp = (_MXMLContract) componentObj;
			Map attributeMap = comp.getAttributes();
			Object obj;
			
			if(attributeMap != null && (obj = attributeMap.get(tokenName)) != null){
				return new TokenValue(tokenName, obj.toString());
			}
			
			return null;
		}
	};
	
	final _MXMLMapper MXML_METHOD_MAPPER = new _MXMLMapper(){
		TokenValue mapField(String tokenName, Object componentObj) {
			
			try{
				String searchMethodName = "get" + String.valueOf(tokenName.charAt(0)).toUpperCase() + tokenName.substring(1);
				Method method = componentObj.getClass().getMethod(searchMethodName, null);
				Object obj = method.invoke(componentObj, null);
				
				if(obj != null){
					return new TokenValue(tokenName, obj);
				}
				
				return null;
			}catch(Exception exceptionThroughReflection){
				StringBuffer errorMessage = new StringBuffer();
				errorMessage.append("Exception when mapping field for tokenName [ ");
				errorMessage.append(tokenName);
				errorMessage.append(" ] for ");
				errorMessage.append(componentObj.getClass().getName());
				throw new ComponentBuildException(errorMessage.toString(), exceptionThroughReflection);
			}

		}
	};
												
}
