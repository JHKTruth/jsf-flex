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
package com.googlecode.jsfflexeclipseplugin.commands.handler;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;

/**
 * @author Ji Hoon Kim
 */
public final class JsfFlexASAttributesClassHandlerUtil {
	
	private static final IResource[] RESOURCE_CAST = new IResource[0];
	
	private JsfFlexASAttributesClassHandlerUtil() {
		super();
	}
	
	public static IResource[] convertToResources(Object[] objects) {
		
		Set<IResource> resources = new LinkedHashSet<IResource>(objects.length);
		for(Object currObject : objects) {
			if(currObject instanceof IAdaptable) {
				IAdaptable adaptable = IAdaptable.class.cast( currObject );
				IResource resource = IResource.class.cast( adaptable.getAdapter(IResource.class) );
				
				if(resource != null) {
					resources.add(resource);
				}
			}
		}
		
		
		return resources.toArray(RESOURCE_CAST);
	}
	
	public static String convertToText(Object[] objects) {
		
		StringBuilder content = new StringBuilder();
		for(Object currObject : objects) {
			if(currObject instanceof IJsfFlexASAttributesClass) {
				
				
				
			}
		}
		
		
		return content.toString();
	}
	
}
