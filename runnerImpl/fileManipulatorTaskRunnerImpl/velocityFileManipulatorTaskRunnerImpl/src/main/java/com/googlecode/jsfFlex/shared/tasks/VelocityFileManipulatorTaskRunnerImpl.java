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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks.velocity.EvaluateTemplateTask;

/**
 * A Velocity implementation of _FileManipulatorTaskRunner interface.<br>
 * 
 * @author Ji Hoon Kim
 */
final class VelocityFileManipulatorTaskRunnerImpl extends _FileManipulatorTaskRunner {
	
	private final static String JSF_FLEX_LOG_TAG = "jsf-flex";
	private final static String JSF_FLEX_TEMPLATE = "jsf-flex-template.vm";
	private final static String TOKEN_LIST_TOKEN = "tokenList";
	private final static String MXML_COMPONENT_NAME_TOKEN = "mxmlComponent";
	private final static String INITIAL_BODY_CONTENT_TOKEN = "initialBodyContent";
	private final static String CHILD_PRE_MXML_IDENTIFIER_TOKEN = "childIdentifier";
	private final static String SIBLING_PRE_MXML_IDENTIFIER_TOKEN = "siblingIdentifier";
	
	VelocityFileManipulatorTaskRunnerImpl(){
		super();
	}
	
	public synchronized void createFileContent(String filePath, String templateFile, Properties initProperties, Map<String, ? extends Object> tokenMap){
		
		try{
			Reader templateReader = new InputStreamReader(EvaluateTemplateTask.class.getResourceAsStream(templateFile));
			FileWriter targetWriter = new FileWriter(new File(filePath));
			EvaluateTemplateTask mergeTemplateTask = new EvaluateTemplateTask(initProperties, tokenMap, JSF_FLEX_LOG_TAG, templateReader, targetWriter);
			addTask(mergeTemplateTask);
			
		}catch(IOException ioException){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("filePath [ ");
			errorMessage.append(filePath);
			errorMessage.append(" ] ");
			errorMessage.append("templateFile [ ");
			errorMessage.append(templateFile);
			errorMessage.append(" ] ");
			
			if(tokenMap != null){
				
				errorMessage.append("overView of tokenMap [ ");
				for(String key : tokenMap.keySet()){
					errorMessage.append("key : ");
					errorMessage.append(key);
					errorMessage.append(", value : ");
					
					Object value = tokenMap.get(key);
					errorMessage.append(value.toString());
					errorMessage.append(" ");
					
				}
				errorMessage.append(" ] ");
			}
			
			throw new ComponentBuildException(errorMessage.toString(), ioException);
		}
		
	}
	
	public synchronized void createPreMxmlFile(String preMxmlFilePath, Properties initProperties, Set<TokenValue> tokenList, String mxmlComponentName, 
													String bodyContent, String childIdentifier, String siblingIdentifier) {
		if(tokenList == null){
			tokenList = new LinkedHashSet<TokenValue>();
		}
		
		bodyContent = bodyContent == null ? "" : bodyContent;
		
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put(TOKEN_LIST_TOKEN, tokenList);
		tokenMap.put(MXML_COMPONENT_NAME_TOKEN, mxmlComponentName);
		tokenMap.put(INITIAL_BODY_CONTENT_TOKEN, bodyContent);
		tokenMap.put(CHILD_PRE_MXML_IDENTIFIER_TOKEN, childIdentifier);
		tokenMap.put(SIBLING_PRE_MXML_IDENTIFIER_TOKEN, siblingIdentifier);
		
		createFileContent(preMxmlFilePath, JSF_FLEX_TEMPLATE, initProperties, tokenMap);
		
	}
	
}
