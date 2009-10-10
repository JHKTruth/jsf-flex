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
package com.googlecode.jsfFlex.renderkit.html.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
class JsfFlexResourceImpl extends JsfFlexResource {
	
	private static final Log _log = LogFactory.getLog(JsfFlexResourceImpl.class);
	
	private static final String RESOURCE_DIRECTORY_NAME = "resource";
	
	private final Set<JsfFlexResourceElement> _resourceSet;
	
	JsfFlexResourceImpl(){
		super();
		_resourceSet = new LinkedHashSet<JsfFlexResourceElement>();
	}
	
	public void addResource(Class jsfFlexComponent, String resourceName){
		_resourceSet.add(new JsfFlexResourceElement(jsfFlexComponent, resourceName));
	}
	
	public Collection<String> getResources(){
		
		List<String> resourceList = new LinkedList<String>();
		for(JsfFlexResourceElement currResourceElement : _resourceSet){
			resourceList.add(currResourceElement.generateResourcePath());
		}
		
    	return resourceList;
    }
	
	public void processRequestResource(HttpServletResponse httpResponse, String[] requestURISplitted){
		
		/*
		 * need to get the resource as stream and flush it out using httpResponse
		 *  The key should be [3] + [4] where :
		 *  	[3]	=	name of the packaged class where the resource lives [use it for loading the resource]
		 *  	[4] =	name of the resource file
		 */
		
		Class resourceClass = null;
		
		try{
			resourceClass = Class.forName(requestURISplitted[3]);
		}catch(ClassNotFoundException classNotFound){
			_log.debug("Class Not found for " + requestURISplitted[3], classNotFound);
		}
		
		StringBuilder resourcePath = new StringBuilder(RESOURCE_DIRECTORY_NAME);
		resourcePath.append("/");
		
		for(int i=4; i < requestURISplitted.length; i++){
			resourcePath.append(requestURISplitted[i]);
			
			if((i+1) < requestURISplitted.length){
				resourcePath.append("/");
			}
		}
		
		InputStream resourceStream = resourceClass.getResourceAsStream(resourcePath.toString());
		
		readInputWriteOutput(resourceStream, httpResponse);
		
	}
	
	private void readInputWriteOutput(InputStream resourceStream, HttpServletResponse httpResponse){
		
		PrintWriter responseWriter = null;
		
		try{
			responseWriter = httpResponse.getWriter();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));
			
			char[] charBuffer = new char[2048];
			int offSet = 0;
			while((offSet = reader.read(charBuffer, 0, 2048)) > -1){
				responseWriter.write(charBuffer, 0, offSet);
			}
			
			responseWriter.flush();
		}catch(IOException ioException){
			_log.debug("IOException while writing the script's content to PrintWriter of HttpServletResponse", ioException);
		}
		
	}
	
	private final class JsfFlexResourceElement{
		
		private final Class _jsfFlexComponent;
		private final String _resourceName;
		
		private final int HASH_CODE_VAL;
		
		private JsfFlexResourceElement(){
			super();
			_jsfFlexComponent = null;
			_resourceName = null;
			HASH_CODE_VAL = -1;
		}
		
		private JsfFlexResourceElement(Class jsfFlexComponent, String resourceName){
			super();
			_jsfFlexComponent = jsfFlexComponent;
			_resourceName = resourceName;
			int hashCodeVal = MXMLConstants.HASH_CODE_INIT_VALUE;
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _jsfFlexComponent.getPackage().getName().hashCode();
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _jsfFlexComponent.getName().hashCode();
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _resourceName.hashCode();
			HASH_CODE_VAL = hashCodeVal;
		}
		
		public String generateResourcePath(){
			StringBuilder resourcePath = new StringBuilder();
			
			resourcePath.append(JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX);
			resourcePath.append("/");
			resourcePath.append( _jsfFlexComponent.getPackage().getName() );
			resourcePath.append(".");
			resourcePath.append( _jsfFlexComponent.getSimpleName() );
			resourcePath.append("/");
			resourcePath.append( _resourceName );
			
			return resourcePath.toString();
		}
		
        @Override
		public boolean equals(Object instance) {
			if(!(instance instanceof JsfFlexResourceElement)){
				return false;
			}
			JsfFlexResourceElement jsfFlexResourceElementInstance = (JsfFlexResourceElement) instance;
			
			return _jsfFlexComponent.getPackage().getName().equals( jsfFlexResourceElementInstance._jsfFlexComponent.getPackage().getName() ) &&
					_resourceName.equals( jsfFlexResourceElementInstance._resourceName );
		}
		
        @Override
		public int hashCode() {
			return HASH_CODE_VAL;
		}
		
	}
	
}
