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

import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class will provide methods of converting from JSONObject/JSONArray to <br>
 * other forms [i.e. XML in a form of String] and other forms to JSONObject [i.e. Calendar]<br>
 * 
 * @author Ji Hoon Kim
 */
public final class JSONConverter {
    
    private static final String XML_VALUE_START_TAG = "<VALUE>";
    private static final String XML_VALUE_END_TAG = "</VALUE>";
    
    private JSONConverter(){
        super();
    }
    
    @SuppressWarnings("unchecked")
    public static String convertJSONObjectToXMLString(JSONObject jsonObject) throws JSONException {
        
        StringBuilder converted = new StringBuilder();
        
        for(Iterator<String> keyIterator = jsonObject.keys(); keyIterator.hasNext();){
            String currKey = keyIterator.next();
            Object value = jsonObject.get(currKey);
            converted.append( "<" + currKey + ">" );
            
            /*
             * And here comes the ugly instanceof checks
             */
            if(value instanceof JSONObject) {
                converted.append( convertJSONObjectToXMLString(JSONObject.class.cast( value )) );
            }else if(value instanceof JSONArray) {
                converted.append( convertJSONArrayToXMLString(JSONArray.class.cast( value )) );
            }else {
                converted.append( value.toString() );
            }
            
            converted.append( "</" + currKey + ">" );
        }
        
        return converted.toString();
    }
    
    public static String convertJSONArrayToXMLString(JSONArray jsonArray) throws JSONException {
        
        StringBuilder converted = new StringBuilder();

        /*
         * TODO: 
         * Check out if better JSON.jar exists, since this one is outdated and doesn't even have an Iterator. 
         */
        for(int index = 0; index < jsonArray.length(); index++){
            Object value = jsonArray.get(index);
            converted.append( XML_VALUE_START_TAG );
            /*
             * And here comes the ugly instanceof checks
             */
            if(value instanceof JSONObject) {
                converted.append( convertJSONObjectToXMLString(JSONObject.class.cast( value )) );
            }else if(value instanceof JSONArray) {
                converted.append( convertJSONArrayToXMLString(JSONArray.class.cast( value )) );
            }else {
                /*
                 * Should technically only contain JSONArray or JSONObject, b/c we are now using generic value XML tag
                 * and assuming this to be known.
                 */
                converted.append( value.toString() );
            }
            
            converted.append( XML_VALUE_END_TAG );
        }
        
        return converted.toString();
    }
    
    public static JSONArray convertJavaDateToASDateConstructorArguments(Calendar toConvert){
        JSONArray dateConstructorArguments = new JSONArray();
        
        dateConstructorArguments.put(toConvert.get(Calendar.YEAR));
        dateConstructorArguments.put(toConvert.get(Calendar.MONTH));
        dateConstructorArguments.put(toConvert.get(Calendar.DATE));
        dateConstructorArguments.put(toConvert.get(Calendar.HOUR_OF_DAY));
        dateConstructorArguments.put(toConvert.get(Calendar.MINUTE));
        dateConstructorArguments.put(toConvert.get(Calendar.SECOND));
        dateConstructorArguments.put(toConvert.get(Calendar.MILLISECOND));
        
        return dateConstructorArguments;
    }
    
}
