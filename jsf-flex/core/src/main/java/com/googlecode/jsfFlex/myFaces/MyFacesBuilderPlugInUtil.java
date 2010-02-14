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
package com.googlecode.jsfFlex.myFaces;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will provide the needed methods + functionality for MyFaces Builder<br/>
 * Plug-in. Namely the implementations are taken from the MyFaces code. Would be nice<br/>
 * if they encapsulated the functionality within the builder, because this is a pain.
 * 
 * @author Ji Hoon Kim
 */
public final class MyFacesBuilderPlugInUtil {
    
    /**
     * These are normally NMTOKEN type in attributes
     * String --> String[]
     * @param value
     * @return
     */
    public static String[] getStringArray(Object  value) throws ParseException {
        if (value == null || value.equals("")){
            return null;
        }
        
        String stringValue = String.class.cast( value );
        List<String> list = new ArrayList<String>(5);
        int length = stringValue.length();
        boolean inSpace = true;
        int start = 0;
        for(int i = 0; i < length; i++) {
            char ch = stringValue.charAt(i);
            
            // We're in whitespace;  if we've just departed
            // a run of non-whitespace, append a string.
            // Now, why do we use the supposedly deprecated "Character.isSpace()"
            // function instead of "isWhitespace"?  We're following XML rules
            // here for the meaning of whitespace, which specifically
            // EXCLUDES general Unicode spaces.
            if (Character.isWhitespace(ch)) {
                if (!inSpace) {
                    list.add(stringValue.substring(start, i));
                    inSpace = true;
                }
            } else {
                //We're out of whitespace;  if we've just departed
                // a run of whitespace, start keeping track of this string
                if (inSpace) {
                    start = i;
                    inSpace = false;
                }
            }
        }

        if (!inSpace){
            list.add(stringValue.substring(start));
        }
        if (list.isEmpty()){
            return null;
        }else{
            return list.toArray(new String[list.size()]);
        }
    }
    
}
