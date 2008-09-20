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
import java.util.Iterator;
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

import com.googlecode.jsfFlex.renderkit.html.util.JsfFlexResource;

/**
 * @author Ji Hoon Kim
 */
public final class JsfFlexResourceFilter implements Filter {
	
	private static final Log _log = LogFactory.getLog(JsfFlexResourceFilter.class);
	
	private static final String ENCODING = "UTF-8";
	
	private static final String HEAD_SEARCH_PATTERN = "<head";
	private static final String BODY_SEARCH_PATTERN = "<body";
	private static final String END_TAG_CHAR_SEARCH_PATTERN = ">";
	
	private static final String HEAD_START_TAG = "<head>";
	private static final String HEAD_END_TAG = "</head>";
	
	private static final Pattern _headPattern = Pattern.compile(HEAD_SEARCH_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final Pattern _bodyPattern = Pattern.compile(BODY_SEARCH_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final Pattern _endTagCharPattern = Pattern.compile(END_TAG_CHAR_SEARCH_PATTERN);
	
	private FilterConfig _filterConfig;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
	}
	
	public void destroy() {
		_filterConfig = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		JsfFlexResponseWrapper jsfFlexResponseWrapper = new JsfFlexResponseWrapper((HttpServletResponse) response);
		JsfFlexResource jsfFlexResource = JsfFlexResource.getInstance();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestURI = httpRequest.getRequestURI();
		String[] requestURISplitted = requestURI.split("/");
		
		if(isRequestForResource(requestURISplitted)){
			/** If request is of resource, process it and return */
			jsfFlexResource.processRequestResource(httpResponse, requestURISplitted);
			return;
		}
		
		/** Perform the regular path of request/response */
		
		chain.doFilter(request, jsfFlexResponseWrapper);
		
		jsfFlexResponseWrapper.finishResponse();
		
		/** Finished with the usual path of request/response. Now specific for JSF Flex*/
		
		Collection resourceCollection = jsfFlexResource.getResources();
		
		if(jsfFlexResponseWrapper.getContentType() != null && isValidContentType(jsfFlexResponseWrapper.getContentType())){
			
			Matcher headMatcher = _headPattern.matcher( jsfFlexResponseWrapper.toString() );
			boolean headMatchedBoolean = headMatcher.find();
			
			PrintWriter actualWriter = httpResponse.getWriter();
			
			if(headMatchedBoolean){
				int headMatchIndex = headMatcher.start();
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(0, headMatchIndex+5) );
				
				Matcher endTagCharMatcher = _endTagCharPattern.matcher( jsfFlexResponseWrapper.toString() );
				endTagCharMatcher.find(headMatchIndex);
				
				int endTagCharIndex = endTagCharMatcher.start();
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(headMatchIndex+5, endTagCharIndex+1) );
				
				String resourceConvertedToScriptElements = constructResourceToScriptTags(resourceCollection, requestURISplitted);
				
				actualWriter.write(resourceConvertedToScriptElements);
				
				actualWriter.write(jsfFlexResponseWrapper.toString().substring(endTagCharIndex+1));
				
			}else{
				
				Matcher bodyMatcher = _bodyPattern.matcher( jsfFlexResponseWrapper.toString() ); 
				bodyMatcher.find();
				
				int bodyMatchIndex = bodyMatcher.start();
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(0, bodyMatchIndex) );
				
				actualWriter.write(HEAD_START_TAG);
				
				String resourceConvertedToScriptElements = constructResourceToScriptTags(resourceCollection, requestURISplitted);
				
				actualWriter.write(resourceConvertedToScriptElements);
				
				actualWriter.write(HEAD_END_TAG);
				
				actualWriter.write( jsfFlexResponseWrapper.toString().substring(bodyMatchIndex) );
				
			}
			
			actualWriter.flush();
		}
		
	}
	
	private boolean isRequestForResource(String[] requestURI){
		boolean isForResource = true;
		
		if(requestURI.length < 2){
			isForResource = false;
		}else{
			try{
				isForResource = requestURI[2].startsWith(java.net.URLEncoder.encode(JsfFlexResource.JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX, ENCODING));
			}catch(java.io.UnsupportedEncodingException unsupportedEncodingExcept){
				isForResource = false;
			}
			
		}
		
		return isForResource;
	}
	
	/**
	 * The format of the html script element's src will be :
	 * 	JsfFlexResource.JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX/[Component Class Name]/[Resource Name within Component Class Name directory]
	 * 
	 * @param _resources
	 * @return
	 */
	public String constructResourceToScriptTags(Collection resources, String[] requestURI){
		StringBuffer scriptElements = new StringBuffer();
		
		String webProjectName = (requestURI.length < 2) ? requestURI[0] : requestURI[1]; 
		
		for(Iterator iterate = resources.iterator(); iterate.hasNext();){
			
			scriptElements.append("<script type='text/javascript' src='/");
			scriptElements.append(webProjectName);
			scriptElements.append("/");
			scriptElements.append(iterate.next());
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