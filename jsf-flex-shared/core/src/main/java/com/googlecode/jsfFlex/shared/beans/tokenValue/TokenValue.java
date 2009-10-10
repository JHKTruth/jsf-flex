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
package com.googlecode.jsfFlex.shared.beans.tokenValue;

/**
 * @author Ji Hoon Kim
 */
public final class TokenValue {
	
	private final String _token;
	private final Object _value;
	
	private TokenValue(){
		super();
		_token = null;
		_value = null;
	}
	
	public TokenValue(String token, Object value){
		super();
		_token = token;
		_value = value;
	}
	
	public String getToken() {
		return _token;
	}
	public Object getValue() {
		return _value;
	}
	
	public String toString(){
		StringBuilder content = new StringBuilder();
		
		content.append("token [ ");
		content.append(_token);
		content.append(" ] ");
		
		content.append("value [ ");
		content.append(_value);
		content.append(" ] ");
		
		return content.toString();
	}
	
	public String toTokenValueSyntax(){
		StringBuilder tokenValue = new StringBuilder();
		tokenValue.append(_token);
		tokenValue.append("=");
		tokenValue.append("\"");
		tokenValue.append(_value);
		tokenValue.append("\" ");
		return tokenValue.toString();
	}
	
    @Override
	public boolean equals(Object instance) {
		if(!(instance instanceof TokenValue)){
			return false;
		}
		
		TokenValue tokenValueInstance = (TokenValue) instance;
		return _token.equals(tokenValueInstance._token);
	}
	
    @Override
	public int hashCode() {
		return _token.hashCode();
	}
	
}
