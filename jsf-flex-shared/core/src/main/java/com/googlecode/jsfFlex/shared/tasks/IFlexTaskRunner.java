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
package com.googlecode.jsfFlex.shared.tasks;

import java.util.List;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;

/**
 * @author Ji Hoon Kim
 */
public interface IFlexTaskRunner extends ITaskRunner {
	
	void makeDirectory(String directoryToCreate);
	
	void deleteResources(String resourceToDelete, boolean isDirectory, String queueTaskId);
	
	void writeBodyContent(IFlexContract componentFlex);
	
	void replaceTokenWithValue(String targetAbsolutePath, String valueToReplaceWith, String tokenReplace);
	
	void copyFile(String fileToCopy, String fileToCopyTo, String queueTaskId);
	
	void copyFileSet(String copyDir, String copyInclude, String copyExclude, String copyTo, String queueTaskId);
	
	void createMXML(String targetAbsolutePath, String copyTo);
	
	void createSwcSourceFiles(String swcPath, List<String> systemSourceFiles, String jsfFlexMainSwcConfigFile, String webContextPath);
	
	void createSystemSWCFile(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath, IFlexApplicationContract componentFlex, String queueTaskId);
	
	void createSWF(String flexFile, String swfPath, IFlexApplicationContract componentFlex, String flexSDKRootPath, String locale, String localePath, String queueTaskId);
	
    void copyLocale(String locale, String flexSDKRootPath, String queueTaskId);
    
	void createSwfSourceFiles(String swfBasePath, List<String> systemSwfSourceFiles);
	
	void renameFile(String sourceFile, String destFile, boolean overWrite);
	
}
