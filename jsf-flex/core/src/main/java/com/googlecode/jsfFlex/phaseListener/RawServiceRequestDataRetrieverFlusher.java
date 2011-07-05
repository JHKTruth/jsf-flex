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
import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ji Hoon Kim
 */
final class RawServiceRequestDataRetrieverFlusher extends _ServiceRequestDataRetrieverFlusher {
	
	private static final String PLAIN_CONTENT_TYPE = "text/plain";
	
	RawServiceRequestDataRetrieverFlusher(){
		super();
	}
	
	public void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
		
		Collection objectCollection = null;
		
		try{
			objectCollection = (Collection) invokeResourceMethod(context, componentId, methodToInvoke, null, null);
		}catch(Exception methodInvocationException){
			throw new ServletException(methodInvocationException);
		}
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType(PLAIN_CONTENT_TYPE);
		
		if(objectCollection != null){
			Writer writer = response.getWriter();
			
			for(Iterator iterate = objectCollection.iterator(); iterate.hasNext();){
				Object currObj = iterate.next();
				writer.write(currObj.toString());
			}
			
			writer.flush();
		}
		
	}
	
}
