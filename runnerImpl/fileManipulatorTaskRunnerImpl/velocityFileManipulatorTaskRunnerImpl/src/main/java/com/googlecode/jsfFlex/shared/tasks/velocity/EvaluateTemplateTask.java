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
package com.googlecode.jsfFlex.shared.tasks.velocity;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks._Task;

/**
 * @author Ji Hoon Kim
 */
public final class EvaluateTemplateTask extends _Task {
	
	private final static Log _log = LogFactory.getLog(EvaluateTemplateTask.class);
	
	private VelocityEngine _velocityEngine;
	private VelocityContext _context;
	private Properties _initProperties;
	private Map _contextValues;
	private String _logTag;
	private Reader _template;
	private Writer _writer;
	
	public EvaluateTemplateTask(){
		super();
	}
	
	public EvaluateTemplateTask(Properties initProperties, Map contextValues, String logTag, 
									Reader template, Writer writer){
		super();
		_initProperties = initProperties;
		_contextValues = contextValues;
		_logTag = logTag;
		_template = template;
		_writer = writer;
	}
	
	{
		_velocityEngine = new VelocityEngine();
		_context = new VelocityContext();
	}

	protected void performTask() {
		
		init();
		populateContext();
		mergeCollectionToTemplate();
		
	}
	
	private void init() {
		try{
			if(_initProperties != null){
				_velocityEngine.init(_initProperties);
			}else{
				_velocityEngine.init();
			}
			
		}catch(Exception exceptionWhileInitializing){
			StringBuffer errorMessage = new StringBuffer();
			errorMessage.append("Error in MergeTemplateTask's init with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), exceptionWhileInitializing);
		}
		
	}
	
	private void populateContext(){
		
		for(Iterator keys = _contextValues.keySet().iterator(); keys.hasNext();){
			Object key = keys.next();
			Object value = _contextValues.get(key);
			_context.put(key.toString(), value);
		}
		
	}
	
	private void mergeCollectionToTemplate() {
		
		try{
			_velocityEngine.evaluate(_context, _writer, _logTag, _template);
			_writer.flush();
			_log.debug("EvaluateTemplateTask mergeCollectionToTemplate has been completed with " + toString());
		}catch(Exception exceptionWhileMerging){
			StringBuffer errorMessage = new StringBuffer();
			errorMessage.append("Error in MergeTemplateTask's mergeCollectionToTemplate with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), exceptionWhileMerging);
		}finally{
			try{
				if(_writer != null){
					_writer.close();
				}
			}catch(IOException closerException){
				_log.debug("Error while closing the writer within mergeCollectionToTemplate", closerException);
			}
		}
		
	}
	
	public String toString() {
		StringBuffer content = new StringBuffer();
		
		content.append("contextValues [ ");
		for(Iterator keys = _contextValues.keySet().iterator(); keys.hasNext();){
			Object key = keys.next();
			Object value = _contextValues.get(key);
			content.append(key.toString());
			content.append(":");
			content.append(value.toString());
			if(keys.hasNext()){
				content.append(", ");
			}
		}
		content.append(" ] ");
		content.append(" logTag [ ");
		content.append(_logTag);
		content.append(" ] ");
		
		return content.toString();
	}

	public void contextValues(Map contextValues) {
		_contextValues = contextValues;
	}
	public void initProperties(Properties initProperties) {
		_initProperties = initProperties;
	}
	public void logTag(String logTag) {
		_logTag = logTag;
	}
	public void template(Reader template) {
		_template = template;
	}
	public void writer(Writer writer) {
		_writer = writer;
	}
	
}
