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
package com.googlecode.jsfFlex.shared.adapter;

import java.util.Collection;

/**
 * @author Ji Hoon Kim
 */
public interface _MXMLApplicationContract extends _MXMLContract {
	
	Collection<String> getSourcePath();
	
	String getExternalLibraryPath();
	
	String getRuntimeSharedLibraries();
	
	String getDefaultBgColor();
	
	Integer getMaxLvRecursion();
	
	Integer getMaxScriptExecTime();
	
	boolean isIncremental();
	
	String getLoadConfig();
	
	String getTitle();
	
	String getDescription();
	
	String getCreator();
	
	String getPublisher();
	
	String getLanguage();
	
	String getDate();
	
	boolean isAccessible();
    
    String getId();
	
	/*
	 * Error attributes for ValidationManagerScriptContent.java
	 */
	String getErrorColor();
	
	String getErrorFontAntiAliasType();
	
	String getErrorFontFamily();
	
	String getErrorFontGridFitType();
	
	String getErrorFontSharpness();
	
	String getErrorFontSize();
	
	String getErrorFontStyle();
	
	String getErrorFontThickness();
	
	String getErrorFontWeight();
	
	String getErrorPaddingLeft();
	
	String getErrorPaddingRight();
	
	String getErrorTextAlign();
	
	String getErrorTextDecoration();
	
	String getErrorTextIndent();
	
}
