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
package com.googlecode.jsfFlexPlugIn.utils;

import java.io.File;

/**
 * @author Ji Hoon Kim
 */
public final class JsfFlexBuildPluginUtil {
    
    private JsfFlexBuildPluginUtil() {
        super();
    }
    
    public static void checkForDirectoryExistenceOrCreate(String path) {
        File directory = new File(path);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }
    
    public static String removeQuotes(String content) {
        return content == null ? "" : content.replaceAll("\"", "");
    }
    
    public static String returnEmptyStringForNull(Object checkForNull) {
        return checkForNull == null ? "" : checkForNull.toString();
    }
    
}
