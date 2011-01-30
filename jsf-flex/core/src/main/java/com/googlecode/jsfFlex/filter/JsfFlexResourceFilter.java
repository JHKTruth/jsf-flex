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
package com.googlecode.jsfFlex.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

import com.googlecode.jsfFlex.renderkit.html.util.AbstractJsfFlexResource;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * TODO: Implement it better later
 * @author Ji Hoon Kim
 */
public final class JsfFlexResourceFilter implements Filter {
	
	private static final Log _log = LogFactory.getLog(JsfFlexResourceFilter.class);
	
	private static final String META_HTTP_EQUIV_EXPIRE = "<META HTTP-EQUIV='Expires' CONTENT='0' />";
	private static final String META_HTTP_EQUIV_PRAGMA_NO_CACHE = "<META HTTP-EQUIV='PRAGMA' CONTENT='NO-CACHE' />";
	private static final String META_HTTP_EQUIV_CACHE_CONTROL_NO_CACHE = "<META HTTP-EQUIV='CACHE-CONTROL' CONTENT='NO-CACHE' />";
    
    private static final String REQUEST_FOR_RESOURCE_SEARCH_PATTERN = "%2F" + AbstractJsfFlexResource.JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX + "%2F";
	
	private static final String HEAD_SEARCH_PATTERN = "<head";
	private static final String BODY_SEARCH_PATTERN = "<body";
	private static final String END_TAG_CHAR_SEARCH_PATTERN = ">";
	
	private static final String HEAD_START_TAG = "<head>";
	private static final String HEAD_END_TAG = "</head>";
	
	private static final Pattern REQUEST_FOR_RESOURCE_PATTERN = Pattern.compile(REQUEST_FOR_RESOURCE_SEARCH_PATTERN);
	
	private static final Pattern HEAD_PATTERN = Pattern.compile(HEAD_SEARCH_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final Pattern BODY_PATTERN = Pattern.compile(BODY_SEARCH_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final Pattern END_TAG_CHAR_PATTERN = Pattern.compile(END_TAG_CHAR_SEARCH_PATTERN);
	
	/** Below is for future configuration */
	private FilterConfig _filterConfig;
	private boolean isDebugMode;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
		String mode = _filterConfig.getServletContext().getInitParameter(FlexConstants.CONFIG_MODE_NAME);
		
		if(!(mode == null || mode.equals(FlexConstants.PRODUCTION_MODE))){
			isDebugMode = true;
		}
	}
	
	public void destroy() {
		_filterConfig = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
        HttpServletRequest httpRequest = HttpServletRequest.class.cast( request );
        HttpServletResponse httpResponse = HttpServletResponse.class.cast( response );
        
		JsfFlexResponseWrapper jsfFlexResponseWrapper = new JsfFlexResponseWrapper(httpResponse);
		AbstractJsfFlexResource jsfFlexResource = AbstractJsfFlexResource.getInstance();
		
		String requestURI = httpRequest.getRequestURI();
		String[] requestURISplitted = requestURI.split("/");
		
		if(isRequestForResource(requestURI)){
			/** If request is of resource, process it and return */
			jsfFlexResource.processRequestResource(httpResponse, requestURISplitted);
			return;
		}
		
		/** Perform the regular path of request/response */
		
		chain.doFilter(request, jsfFlexResponseWrapper);
		
		jsfFlexResponseWrapper.finishResponse();
		
		/** Finished with the usual path of request/response. Now specific for JSF Flex*/
		
		if(jsfFlexResponseWrapper.getContentType() != null && isValidContentType(jsfFlexResponseWrapper.getContentType())){
			
			Matcher headMatcher = HEAD_PATTERN.matcher( jsfFlexResponseWrapper.toString() );
			boolean headMatchedBoolean = headMatcher.find();
			
			PrintWriter actualWriter = httpResponse.getWriter();
			
			if(headMatchedBoolean){
				int headMatchIndex = headMatcher.start();
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(0, headMatchIndex+5) );
				
				Matcher endTagCharMatcher = END_TAG_CHAR_PATTERN.matcher( jsfFlexResponseWrapper.toString() );
				endTagCharMatcher.find(headMatchIndex);
				
				int endTagCharIndex = endTagCharMatcher.start();
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(headMatchIndex+5, endTagCharIndex+1) );
				
				if(isDebugMode){
					actualWriter.write(META_HTTP_EQUIV_EXPIRE);
					actualWriter.write(META_HTTP_EQUIV_PRAGMA_NO_CACHE);
					actualWriter.write(META_HTTP_EQUIV_CACHE_CONTROL_NO_CACHE);
                }
				
				Collection<String> resourceCollection = jsfFlexResource.getResources();
				String resourceConvertedToScriptElements = constructResourceToScriptTags(resourceCollection, requestURISplitted);
				
				actualWriter.write(resourceConvertedToScriptElements);
				
				actualWriter.write(jsfFlexResponseWrapper.toString().substring(endTagCharIndex+1));
				
			}else{
				
				Matcher bodyMatcher = BODY_PATTERN.matcher( jsfFlexResponseWrapper.toString() ); 
				boolean bodyMatched = bodyMatcher.find();
				
				if(bodyMatched){
					int bodyMatchIndex = bodyMatcher.start();
					
					actualWriter.write( jsfFlexResponseWrapper.toString().substring(0, bodyMatchIndex) );
					
					actualWriter.write(HEAD_START_TAG);
					
					if(isDebugMode){
						actualWriter.write(META_HTTP_EQUIV_EXPIRE);
						actualWriter.write(META_HTTP_EQUIV_PRAGMA_NO_CACHE);
						actualWriter.write(META_HTTP_EQUIV_CACHE_CONTROL_NO_CACHE);
                    }
					
					Collection<String> resourceCollection = jsfFlexResource.getResources();
					String resourceConvertedToScriptElements = constructResourceToScriptTags(resourceCollection, requestURISplitted);
					
					actualWriter.write(resourceConvertedToScriptElements);
					
					actualWriter.write(HEAD_END_TAG);
					
					actualWriter.write( jsfFlexResponseWrapper.toString().substring(bodyMatchIndex) );
				}else{
					//Must not be for mxml components, so flush
					actualWriter.write( jsfFlexResponseWrapper.toString() );
				}
			}
			
			actualWriter.flush();
		}
		
	}
	
	private boolean isRequestForResource(String requestURI){
		boolean isForResource = true;
		
		try{
			Matcher requestForResourceMatcher = REQUEST_FOR_RESOURCE_PATTERN.matcher(java.net.URLEncoder.encode(requestURI, FlexConstants.UTF_8_ENCODING));
			isForResource = requestForResourceMatcher.find();
		}catch(java.io.UnsupportedEncodingException unsupportedEncodingExcept){
			isForResource = false;
		}
		
		return isForResource;
	}
	
	/**
	 * The format of the html script element's src will be :
	 * 	AbstractJsfFlexResource.JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX/[Component Class Name]/[Resource Name within Component Class Name directory]
	 * 
	 * @param _resources
	 * @return
	 */
	public String constructResourceToScriptTags(Collection<String> resources, String[] requestURI){
		StringBuilder scriptElements = new StringBuilder();
		
		String webProjectName = (requestURI.length < 2) ? requestURI[0] : requestURI[1]; 
		
		for(String currResource : resources){
			
			scriptElements.append("<script type='text/javascript' src='/");
			scriptElements.append(webProjectName);
			scriptElements.append("/");
			scriptElements.append(currResource);
			scriptElements.append("'></script>");
		}
		
		return scriptElements.toString();
	}
	
	public boolean isValidContentType(String contentType){
        return contentType.startsWith("text/html") ||
                contentType.startsWith("text/xml") ||
                contentType.startsWith("application/xhtml+xml") ||
                contentType.startsWith("application/xml");
    }
	
	private final class JsfFlexResponseWrapper extends HttpServletResponseWrapper {
		
		private PrintWriter _printWriter = null;
	    private String _contentType;
		private final ByteArrayOutputStream _outPutStream;
		
		private JsfFlexResponseWrapper(HttpServletResponse response){
			super(response);
			_outPutStream = new ByteArrayOutputStream();
		}
		
		public byte[] getBytes(){
			return _outPutStream.toByteArray();
		}
		
		public String toString(){
	        try{
	            return _outPutStream.toString(getCharacterEncoding());
	        }catch(UnsupportedEncodingException unsupportedEncodingException){
	            throw new RuntimeException("Invalid character encoding for JsfFlexResponseWrapper toString for : " + getCharacterEncoding());
	        }
	    }
		
	    public PrintWriter getWriter(){
	        if( _printWriter == null ){
	            OutputStreamWriter streamWriter = new OutputStreamWriter(_outPutStream, Charset.forName(getCharacterEncoding()));
	            _printWriter = new PrintWriter(streamWriter, true);
	        }
	        return _printWriter;
	    }
	    
	    public ServletOutputStream getOutputStream(){
	    	
	        return new ServletOutputStream(){
	        	public void write(int numBytes){
		        	_outPutStream.write(numBytes);
		        }
		        
		        public void write(byte[] bytes) throws IOException{
		        	_outPutStream.write( bytes );
		        }
		        
		        public void write(byte[] bytes, int off, int len){
		        	_outPutStream.write(bytes, off, len);
		        }
		    };
	    }
	    
	    public InputSource getInputSource(){
	        ByteArrayInputStream bais = new ByteArrayInputStream( _outPutStream.toByteArray() );
	        return new InputSource( bais );
	    }
	    
	    public void setContentLength(int contentLength) {
            
	    }
	    
	    public void setContentType(String contentType) {
	        super.setContentType(contentType);
	        _contentType = contentType;
	    }
	    
	    public String getContentType() {
	        return _contentType;
	    }
	    
	    public void flushBuffer() throws IOException{
	    	_outPutStream.flush();
	    }
	    
	    public void finishResponse() {
	        try {
	            if(_printWriter != null) {
	                _printWriter.close();
	            }else {
	            	if (_outPutStream != null) {
	                	_outPutStream.close();
	                }
	            }
	        }catch (IOException ioException) {
	        	_log.info("ioException thrown while flushing out and closing the writer/stream");
	        }
	    }
	    
	}
	
}
