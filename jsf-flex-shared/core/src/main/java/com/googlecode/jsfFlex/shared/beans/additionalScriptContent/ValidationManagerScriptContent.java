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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;
import com.googlecode.jsfFlex.shared.util.MXMLAttributeConstants;

/**
 * @author Ji Hoon Kim
 */
public final class ValidationManagerScriptContent {
	
	private static final String ERROR_TEXT_COMPONENT_ID_SUFFIX = "ErrorTextComponent";
	
	private final String _errorTextComponentId;
	private final Set<String> _validationManagerValidatorIds;
	private final ErrorComponentAttributeContainer _errorComponentAttributeContainer;
	
	ValidationManagerScriptContent(String errorTextComponentId, _MXMLApplicationContract currApplicationContract){
		super();
		_errorTextComponentId = errorTextComponentId + ERROR_TEXT_COMPONENT_ID_SUFFIX;
		_validationManagerValidatorIds = new LinkedHashSet<String>();
		_errorComponentAttributeContainer = new ErrorComponentAttributeContainer(currApplicationContract);
	}
	
	void addValidationManagerValidatorId(String validatorId){
		_validationManagerValidatorIds.add(validatorId);
	}
	
	public String getErrorTextComponentId() {
		return _errorTextComponentId;
	}
	public Set<String> getValidationManagerValidatorIds() {
		return new HashSet<String>(_validationManagerValidatorIds);
	}
	
	public Iterator getErrorComponentAttributeContainer(){
		_errorComponentAttributeContainer.resetCollection();
		return _errorComponentAttributeContainer;
	}
    
    @Override
	public boolean equals(Object instance) {
		if(!(instance instanceof ValidationManagerScriptContent)){
			return false;
		}
		
		ValidationManagerScriptContent validationManagerScriptContentInstance = ValidationManagerScriptContent.class.cast( instance );
		return _errorTextComponentId.equals(validationManagerScriptContentInstance._errorTextComponentId);
	}
    
    @Override
	public int hashCode() {
		return _errorTextComponentId.hashCode();
	}
	
	public static final class ErrorComponentAttributeContainer implements Iterator {
		
		private final Set<TokenValue> _errorComponentAttributeSet;
		
		private Iterator _errorComponentAttributeIterator;
		
		private ErrorComponentAttributeContainer(_MXMLApplicationContract currApplicationContract){
			super();
			
			//TODO : implement this better later, wow ugly
			_errorComponentAttributeSet = new LinkedHashSet<TokenValue>();
			
			String attributeValue;
			if((attributeValue = currApplicationContract.getErrorColor()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.COLOR_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontAntiAliasType()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_ANTI_ALIAS_TYPE_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontFamily()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_FAMILY_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontGridFitType()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_GRID_FIT_TYPE_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontSharpness()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_SHARPNESS_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontSize()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_SIZE_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontStyle()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_STYLE_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontThickness()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_THICKNESS_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorFontWeight()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.FONT_WEIGHT_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorPaddingLeft()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.PADDING_LEFT_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorPaddingRight()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.PADDING_RIGHT_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorTextAlign()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.TEXT_ALIGN_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorTextDecoration()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.TEXT_DECORATION_ATTR, attributeValue));
			}
			
			if((attributeValue = currApplicationContract.getErrorTextIndent()) != null){
				_errorComponentAttributeSet.add(new TokenValue(MXMLAttributeConstants.TEXT_INDENT_ATTR, attributeValue));
			}
			
		}
		
		public boolean hasNext() {
			return _errorComponentAttributeIterator.hasNext();
		}
		public Object next() {
			return _errorComponentAttributeIterator.next();
		}
		public void remove() {
			_errorComponentAttributeIterator.remove();
		}
		
		private void resetCollection(){
			_errorComponentAttributeIterator = _errorComponentAttributeSet.iterator();
		}
		
	}
	
}
