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
package com.googlecode.jsfFlexPlugIn.parser.velocity;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.googlecode.jsfFlexPlugIn.parser.IJsfFlexParserListener;

/**
 * @author Ji Hoon Kim
 */
public class JsfFlexVelocityParser {
	
	private final static Log _log = LogFactory.getLog(JsfFlexVelocityParser.class);
	
	private final List<IJsfFlexParserListener> _jsfFlexVelocityParserListeners;
	private final VelocityEngine _velocityEngine;
	private final VelocityContext _context;
	private final Properties _initProperties;
	
	public JsfFlexVelocityParser(){
		super();
		_initProperties = null;
	}
	
	public JsfFlexVelocityParser(Properties initProperties){
		super();
		_initProperties = initProperties;
	}
	
	{
		_velocityEngine = new VelocityEngine();
		_context = new VelocityContext();
		_jsfFlexVelocityParserListeners = new LinkedList<IJsfFlexParserListener>();
	}
	
	public void init(){
		try{
			if(_initProperties != null){
				_velocityEngine.init(_initProperties);
			}else{
				_velocityEngine.init();
			}
			
		}catch(Exception _exceptionWhileInitializing){
			throw new RuntimeException(_exceptionWhileInitializing);
		}
		
	}
	
	public synchronized void mergeCollectionToTemplate(String template, Map<String, Object> contextInfo, Writer targetWriter, String fileMerged){
		
		for(String currKey : contextInfo.keySet()){
			_context.put(currKey, contextInfo.get(currKey));
		}
		
		try{
			_velocityEngine.mergeTemplate(template, _context, targetWriter);
			targetWriter.flush();
			
		}catch(Exception exceptionWhileMerging){
			throw new RuntimeException(exceptionWhileMerging);
		}finally{
			try{
				if(targetWriter != null){
					targetWriter.close();
				}
			}catch(IOException closerException){
				_log.debug("Error in closing the writer within mergeCollectionToTemplate", closerException);
			}
		}
		
		mergeCollectionToTemplateFinished(fileMerged);
	}
	
	public synchronized void addParserListener(IJsfFlexParserListener callBack){
		_jsfFlexVelocityParserListeners.add(callBack);
	}
	
	private synchronized void mergeCollectionToTemplateFinished(String fileMerged){
		for(IJsfFlexParserListener mergeCollectionToTemplateCallBack : _jsfFlexVelocityParserListeners){
			mergeCollectionToTemplateCallBack.mergeCollectionToTemplateFinished(fileMerged);
		}
	}
	
}
