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
package com.googlecode.jsfFlex.util;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * This Util class will provide functionalities that are need by JSF Flex components, such as : <br>
 * <ul>
 * 	<li> escapeCharacters 		: This method will take the argument and return an encoded version in UTF-8<br>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLJsfUtil {
	
	private final static String WINDOWS_LINE_FEED = "\r\n";
	private final static String UNIX_LINE_FEED = "\n";
	
	private final static String LINE_FEED_ESCAPER = "LINE_FEED";
	private final static String ENCODING = "UTF-8";
	
	private MXMLJsfUtil(){
		super();
	}
	
	/**
	 * This method will take the argument and return an encoded version in UTF-8. Also it will replace<br>
	 * line feeds ("\r\n", "\n") with the "LINE_FEED" string [due to how Flash interprets these two line feeds<br>
	 * differently. Then the conversion back to its non-replaced and encoded value will be made on the Flash side.<br>
	 * 
	 * @param toEscape
	 * @return Encoded version of toEscape
	 */
	public static String escapeCharacters(String toEscape) throws ComponentBuildException {
		if(toEscape == null){
			return "";
		}
		//TODO : implement this better
		try{
			/*
			 * special case for line feeds, since otherwise it is replaced with two
			 * line feeds on the flash side
			 */
			toEscape = toEscape.replaceAll(WINDOWS_LINE_FEED, LINE_FEED_ESCAPER);
			toEscape = toEscape.replaceAll(UNIX_LINE_FEED, LINE_FEED_ESCAPER);
			return java.net.URLEncoder.encode(toEscape, ENCODING);
		}catch(java.io.UnsupportedEncodingException unsupportedEncodingExcept){
			throw new ComponentBuildException("UnsupportedEncoding of " + ENCODING + ", in another words this " +
												"shouldn't happen", unsupportedEncodingExcept);
		}
		
	}
	
}