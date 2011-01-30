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
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.shared.util.JSONConverter;

/**
 * This implementation expects resultFormat to be "object" on the client side and<br>
 * expects the return value for the method invoked to be of JSONObject.<br>
 * 
 * It will parse the JSONObject, creating an XML construct to be returned to the client.<br>
 * In a nutshell it provides a single point of converting JSONObject to XML string.
 * 
 * @author Ji Hoon Kim
 */
class ObjectServiceRequestDataRetrieverFlusher extends AbstractServiceRequestDataRetrieverFlusher {
    
    private final static Log _log = LogFactory.getLog(ObjectServiceRequestDataRetrieverFlusher.class);
    private static final String ERROR_CONVERTING_JSON_OBJECT_TO_XML = "Error while converting JSONObject to XML";
    
    ObjectServiceRequestDataRetrieverFlusher(){
        super();
    }
    
    @Override
    void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException {
        
        JSONObject methodResult = null;
        
        try{
            methodResult = JSONObject.class.cast( invokeResourceMethod(context, componentId, methodToInvoke, null, null) );
        }catch(Exception methodInvocationException){
            throw new ServletException(methodInvocationException);
        }
        
        HttpServletResponse response = HttpServletResponse.class.cast( context.getExternalContext().getResponse() );
        response.setContentType(XML_CONTENT_TYPE);
        
        StringBuilder responseContent = new StringBuilder();
        responseContent.append(XML_HEAD);
        
        try{
            responseContent.append( JSONConverter.convertJSONObjectToXMLString(methodResult) );
        }catch(JSONException jsonException){
            throw new ServletException(ERROR_CONVERTING_JSON_OBJECT_TO_XML, jsonException.getCause());
        }
        
        _log.info("Flushing content : " + responseContent.toString());
        
        Writer writer = response.getWriter();
        writer.write( responseContent.toString() );
        writer.flush();
        
    }
    
}
