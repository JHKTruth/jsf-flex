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
package com.googlecode.jsfFlex.renderkit.mxml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.renderkit.mxml.MXMLResponseStateManagerImpl;
import com.googlecode.jsfFlex.renderkit.mxml.MXMLResponseWriterImpl;

/**
 * @author Ji Hoon Kim
 */
class MXMLRenderKitImpl extends RenderKit {
	
	private static final Log _log = LogFactory.getLog(MXMLRenderKitImpl.class);
	
	private static final String DEFAULT_CHAR_ENCODING = "ISO-8859-1";
	
	private final Map<String, Map<String, Renderer>> _renderers;
	private final ResponseStateManager _responseStateManager;
	
	MXMLRenderKitImpl(){
		super();
		_renderers = new HashMap<String, Map<String, Renderer>>();
		_responseStateManager = new MXMLResponseStateManagerImpl();
	}
	
	public void addRenderer(String family, String rendererType, Renderer renderer) {
		if(family == null){
			throw new NullPointerException("addRenderer: component family must not be null");
		}
		if(rendererType == null){
			throw new NullPointerException("addRenderer: rendererType must not be null");
		}
		
		Map<String, Renderer> rendererTypeMap;
		/*
		 * Since there exists many rendererType for a single family
		 */
		if((rendererTypeMap = _renderers.get(family)) == null){
			rendererTypeMap = new HashMap<String, Renderer>();
			_renderers.put(family, rendererTypeMap);
		}
		
		rendererTypeMap.put(rendererType, renderer);
		
	}
	
	public Renderer getRenderer(String family, String rendererType) {
		if(family == null){
			throw new NullPointerException("getRenderer: component family must not be null");
		}
		if(rendererType == null){
			throw new NullPointerException("getRenderer: rendererType must not be null");
		}
		
		Renderer renderer = null;
		Map<String, Renderer> rendererTypeMap = _renderers.get(family);
		if(rendererTypeMap == null){
			_log.info("Empty entry within getRenderes as MXML component for family [ " + family + " ] rendererType [ " + rendererType + " ], will look in other RenderKits ");
		}else{
			renderer = rendererTypeMap.get(rendererType); 
		}
		
		return renderer;
	}
	
	public ResponseStream createResponseStream(final OutputStream outPutStream) {
		
		return new ResponseStream(){
					
					public void write(int b) throws IOException {
						outPutStream.write(b);
		            }
					
		            public void write(byte b[]) throws IOException {
		            	outPutStream.write(b);
		            }
		            
		            public void write(byte b[], int off, int len) throws IOException {
		            	outPutStream.write(b, off, len);
		            }
		            
		            public void flush() throws IOException {
		            	outPutStream.flush();
		            }
		            
		            public void close() throws IOException {
		            	outPutStream.close();
		            }
						
				};
	}
	
	public ResponseWriter createResponseWriter(Writer writer, String contentTypeListString, String characterEncoding){
		String selectedContentType = MXMLRenderKitImplHelper.selectContentType(contentTypeListString);
		
        if(characterEncoding==null){
            characterEncoding = DEFAULT_CHAR_ENCODING;
        }
        
        AbstractMXMLResponseWriter mxmlResponseWriter = new MXMLResponseWriterImpl(writer, selectedContentType, characterEncoding);
        
        return mxmlResponseWriter;
	}

	public ResponseStateManager getResponseStateManager() {
		return _responseStateManager;
	}
	
	private static final class MXMLRenderKitImplHelper {
		
		private static final String HTML_CONTENT_TYPE = "text/html";
	    private static final String TEXT_ANY_CONTENT_TYPE = "text/*";
	    private static final String ANY_CONTENT_TYPE = "*/*";
	    
	    private static final String XHTML_CONTENT_TYPE = "application/xhtml+xml";
	    private static final String APPLICATION_XML_CONTENT_TYPE = "application/xml";
	    private static final String TEXT_XML_CONTENT_TYPE = "text/xml";
	    
	    private static final List<String> _htmlAcceptContentType;
	    private static final List<String> _xhtmlAcceptContentType;
	    
	    static{
	    	_htmlAcceptContentType = Arrays.asList(new String[]{HTML_CONTENT_TYPE, TEXT_ANY_CONTENT_TYPE, ANY_CONTENT_TYPE});
	    	
	    	_xhtmlAcceptContentType = Arrays.asList(new String[]{XHTML_CONTENT_TYPE, APPLICATION_XML_CONTENT_TYPE, TEXT_XML_CONTENT_TYPE});
	    }
	    
	    private static final String selectContentType(String contentTypeListString){
	    	
	    	if(contentTypeListString == null){
	    		FacesContext currFacesContext = FacesContext.getCurrentInstance();
	    		
	    		if(currFacesContext != null){
	    			//if passed in is null, try to fetch it from the requestMap
	    			contentTypeListString = (String) currFacesContext.getExternalContext().getRequestHeaderMap().get("Accept");
	    		}
	    		
	    		if(contentTypeListString == null){
	    			throw new NullPointerException("Content Type Listing passed into createResponseWriter and within RequestHeaderMap is null!");
	    		}
	    		
	    	}
	    	
	    	List<String> contentTypeList = Arrays.asList(contentTypeListString.replaceAll("[;\\s]", "").split(","));
		    //Always search first as htmlAcceptContentType
		    
	    	String contentType = null;
	    	for(String currentContentType : contentTypeList){
	    		
	    		if(_htmlAcceptContentType.contains(currentContentType)){
	    			contentType = HTML_CONTENT_TYPE;
	    			break;
	    		}else if(_xhtmlAcceptContentType.contains(currentContentType)){
	    			contentType = XHTML_CONTENT_TYPE;
	    			break;
	    		}
	    		
	    	}
	    	
	    	if(contentType == null){
	    		throw new NullPointerException("Content type : " + contentTypeListString + " is not one of the supported content Type");
	    	}
	    	
	    	return contentType;
	    }
		
	}
	
}
