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

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;

import com.googlecode.jsfFlex.shared.util.JSONConverter;

/**
 * Placing a note here for reference to data on the client side<br>
 * 
 * So if the following data is returned to the client side :
 * <root>
 *   <value>First</value>
 *   <value>Second</value>
 * </root>
 * 
 * Then one can refer to the value in the following syntax : 
 *      httpRequest.lastResult[0].root.value[0]
 *      httpRequest.lastResult[0].root.value[1]
 * 
 * @author Ji Hoon Kim
 */
final class ArrayServiceRequestDataRetrieverFlusher extends AbstractServiceRequestDataRetrieverFlusher {
    
    private final static Log _log = LogFactory.getLog(ArrayServiceRequestDataRetrieverFlusher.class);
    private static final String ERROR_CONVERTING_JSON_ARRAY_TO_XML = "Error while converting JSONArray to XML";
    
    ArrayServiceRequestDataRetrieverFlusher(){
        super();
    }
    
    @Override
    void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
        
        JSONArray methodResult = null;
        
        try{
            methodResult = JSONArray.class.cast( invokeResourceMethod(context, componentId, methodToInvoke, null, null) );
        }catch(Exception methodInvocationException){
            throw new ServletException(methodInvocationException);
        }
        
        HttpServletResponse response = HttpServletResponse.class.cast( context.getExternalContext().getResponse() );
        response.setContentType(XML_CONTENT_TYPE);
        
        StringBuilder responseContent = new StringBuilder();
        responseContent.append(XML_HEAD);
        responseContent.append(XML_RESULT_ROOT_START_TAG);
        
        try{
            responseContent.append( JSONConverter.convertJSONArrayToXMLString(methodResult) );
        }catch(JSONException jsonException){
            throw new ServletException(ERROR_CONVERTING_JSON_ARRAY_TO_XML, jsonException.getCause());
        }
        
        responseContent.append(XML_RESULT_ROOT_END_TAG);
        
        _log.info("Flushing content : " + responseContent.toString());
        
        Writer writer = response.getWriter();
        writer.write(responseContent.toString());
        writer.flush();
        
    }
    
}
