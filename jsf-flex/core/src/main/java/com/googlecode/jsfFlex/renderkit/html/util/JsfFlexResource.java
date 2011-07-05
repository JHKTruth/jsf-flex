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
package com.googlecode.jsfFlex.renderkit.html.util;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ji Hoon Kim
 */
public abstract class JsfFlexResource {
	
	public static final String JSF_FLEX_SCRIPT_RESOURCE_REQUEST_PREFIX = "jsfFlexResourceRequest";
	
	JsfFlexResource(){
		super();
	}
	
	private static ThreadLocal<? super JsfFlexResource> _currentResourceInstance = new ThreadLocal<JsfFlexResource>()
    {
        protected JsfFlexResource initialValue()
        {
            return null;
        }
    };
    
    public static synchronized JsfFlexResource getInstance(){
		JsfFlexResource instance = null;
        
		if(_currentResourceInstance.get() == null){
			instance = new JsfFlexResourceImpl();
			_currentResourceInstance.set(instance);
		}else{
			instance = JsfFlexResource.class.cast( _currentResourceInstance.get() );
		}
        
		return instance;
	}
    
    public abstract Collection<String> getResources();
    
    public abstract void addResource(Class jsfFlexComponent, String resourceName);
    
    public abstract void processRequestResource(HttpServletResponse httpResponse, String[] requestURISplitted);
    
}
