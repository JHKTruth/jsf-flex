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
package com.googlecode.jsfFlex.shared.util;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * This Util class will provide functionalities that are need by JSF Flex classes
 * 
 * @author Ji Hoon Kim
 */
public final class FlexJsfUtil {
    
    private final static String WINDOWS_LINE_FEED = "\r\n";
    private final static String UNIX_LINE_FEED = "\n";
    
    private final static String LINE_FEED_ESCAPER = "LINE_FEED";
    
    private FlexJsfUtil(){
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
    public static String escapeCharacters(String toEscape) {
        if(toEscape == null){
            return "";
        }
        
        /*
         * TODO : implement this better
         * special case for line feeds, since otherwise it is replaced with two
         * line feeds on the flash side
         */
        toEscape = toEscape.replaceAll(WINDOWS_LINE_FEED, LINE_FEED_ESCAPER);
        toEscape = toEscape.replaceAll(UNIX_LINE_FEED, LINE_FEED_ESCAPER);
        
        try{
            
            return java.net.URLEncoder.encode(toEscape, FlexConstants.UTF_8_ENCODING);
        }catch(java.io.UnsupportedEncodingException unsupportedEncodingExcept){
            throw new ComponentBuildException("UnsupportedEncoding of " + FlexConstants.UTF_8_ENCODING + ", in another words this " +
                                                "shouldn't happen", unsupportedEncodingExcept);
        }
        
    }
    
    public static String retrieveFormId(String componentId){
        int endIndex = componentId.lastIndexOf(':');
        return endIndex < 0 ? componentId : componentId.substring(0, componentId.lastIndexOf(':'));
    }
    
}
