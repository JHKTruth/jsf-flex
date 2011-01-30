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
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ji Hoon Kim
 */
final class NameValueServiceRequestDataRetrieverFlusher extends AbstractServiceRequestDataRetrieverFlusher {
	
    private final static Log _log = LogFactory.getLog(NameValueServiceRequestDataRetrieverFlusher.class);
	private static final String PLAIN_CONTENT_TYPE = "text/plain";
	
	private static final char EQUAL_CHAR = '=';
	private static final char SEPARATOR_CHAR = '&';
	
	NameValueServiceRequestDataRetrieverFlusher(){
		super();
	}
	
    @Override
	void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
		
		Map<? extends Object, ? extends Object> objectMap = null;
        
		try{
			objectMap = (Map<? extends Object, ? extends Object>) invokeResourceMethod(context, componentId, methodToInvoke, null, null);
		}catch(Exception methodInvocationException){
			throw new ServletException(methodInvocationException);
		}
		
		HttpServletResponse response = HttpServletResponse.class.cast( context.getExternalContext().getResponse() );
		response.setContentType(PLAIN_CONTENT_TYPE);
		
		if(objectMap != null){
			StringBuilder responseContent = new StringBuilder();
            
			for(Iterator<? extends Object> iterate = objectMap.keySet().iterator(); iterate.hasNext();){
				Object currKey = iterate.next();
				Object currValue = objectMap.get(currKey);
				String statementToWrite = currKey.toString() + EQUAL_CHAR + currValue.toString() + SEPARATOR_CHAR;
                responseContent.append(statementToWrite);
			}
			
            _log.info("Flushing content : " + responseContent.toString());
            
            Writer writer = response.getWriter();
            writer.write(responseContent.toString());
			writer.flush();
		}
		
	}
	
}
