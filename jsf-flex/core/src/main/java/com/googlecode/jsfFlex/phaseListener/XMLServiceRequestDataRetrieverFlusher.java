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
package com.googlecode.jsfFlex.phaseListener;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ji Hoon Kim
 */
final class XMLServiceRequestDataRetrieverFlusher extends _ServiceRequestDataRetrieverFlusher {
	
	private static final String XML_CONTENT_TYPE = "text/xml";
	
	private static final String XML_HEAD = "<?xml version='1.0' encoding='UTF-8'?>";
	private static final String XML_RESULT_ROOT_START_TAG = "<RESULT>";
	private static final String XML_RESULT_ROOT_END_TAG = "</RESULT>";
	
	private static final String XML_VALUE_START_TAG = "<VALUE>";
	private static final String XML_VALUE_END_TAG = "</VALUE>";
	
	XMLServiceRequestDataRetrieverFlusher(){
		super();
	}
	
	public void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
		
		Collection<? extends Object> objectCollection = null;
		
		try{
			objectCollection = (Collection<? extends Object>) invokeResourceMethod(context, componentId, methodToInvoke, null, null);
		}catch(Exception methodInvocationException){
			throw new ServletException(methodInvocationException);
		}
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType(XML_CONTENT_TYPE);
		
		Writer writer = response.getWriter();
		writer.write(XML_HEAD);
		
		writer.write(XML_RESULT_ROOT_START_TAG);
		if(objectCollection != null){
			for(Object currObj : objectCollection){
				writer.write(XML_VALUE_START_TAG);
				writer.write(currObj.toString());
				writer.write(XML_VALUE_END_TAG);
			}
		}
		writer.write(XML_RESULT_ROOT_END_TAG);
		
		writer.flush();
		
	}
	
}
