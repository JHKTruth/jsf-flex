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
package com.googlecode.jsfFlex.facelet.tagHandler;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRuleset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ji Hoon Kim
 */
public final class AsynchronousEventGlueTagHandler extends ComponentHandler {
	
	private final static Log _log = LogFactory.getLog(AsynchronousEventGlueTagHandler.class);
	
	private static final String ASYNCHRONOUS_GLUE_EVENT = "asynchronousEventGlueHandler";
	
    private final static Class<?>[] ASYNCHRONOUS_GLUE_EVENT_LISTENER_SIG = new Class[] { com.googlecode.jsfFlex.shared.model.event.AsynchronousDataUpdateEvent.class };
    
    public AsynchronousEventGlueTagHandler(ComponentConfig config) {
		super(config);
	}
    
    protected final static MethodRule asynchronousGlueEventListenerTagRule = new MethodRule( ASYNCHRONOUS_GLUE_EVENT, Object.class , ASYNCHRONOUS_GLUE_EVENT_LISTENER_SIG );
    
	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		_log.warn("Within createMetaRuleset method");
		
		
		return super.createMetaRuleset(type).addRule( asynchronousGlueEventListenerTagRule );
	}
	
	

}
