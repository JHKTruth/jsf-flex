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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ji Hoon Kim
 */
final class XMLServiceRequestDataRetrieverFlusher extends AbstractServiceRequestDataRetrieverFlusher {
	
    private final static Log _log = LogFactory.getLog(XMLServiceRequestDataRetrieverFlusher.class);
    
	XMLServiceRequestDataRetrieverFlusher(){
		super();
	}
	
    @Override
	void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
		
		Collection<? extends Object> objectCollection = null;
		
		try{
			objectCollection = (Collection<? extends Object>) invokeResourceMethod(context, componentId, methodToInvoke, null, null);
		}catch(Exception methodInvocationException){
			throw new ServletException(methodInvocationException);
		}
		
		HttpServletResponse response = HttpServletResponse.class.cast( context.getExternalContext().getResponse() );
		response.setContentType(XML_CONTENT_TYPE);
		
        StringBuilder responseContent = new StringBuilder();
        responseContent.append(XML_HEAD);
		
        responseContent.append(XML_RESULT_ROOT_START_TAG);
		if(objectCollection != null){
			for(Object currObj : objectCollection){
				responseContent.append(XML_VALUE_START_TAG);
                responseContent.append(currObj.toString());
				responseContent.append(XML_VALUE_END_TAG);
			}
		}
        responseContent.append(XML_RESULT_ROOT_END_TAG);
        
        _log.info("Flushing content : " + responseContent.toString());
		
        Writer writer = response.getWriter();
        writer.write(responseContent.toString());
		writer.flush();
		
	}
	
}
