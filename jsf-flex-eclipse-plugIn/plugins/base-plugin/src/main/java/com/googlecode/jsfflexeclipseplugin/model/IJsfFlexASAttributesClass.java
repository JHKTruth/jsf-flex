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
package com.googlecode.jsfflexeclipseplugin.model;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.w3c.dom.Node;

import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource.JsfFlexClassAttribute;

/**
 * @author Ji Hoon Kim
 */
public interface IJsfFlexASAttributesClass extends IAdaptable {
	
	Node getNode();
	
	String getPackageClassName();
	
	void addChildrenASClass(IJsfFlexASAttributesClass childrenASClass);
	
	void addPropertyAttribute(String name, String description);
	
	void addEventAttribute(String name, String description);
	
	void addEffectAttribute(String name, String description);
	
	void addCommonStyleAttribute(String name, String description);
	
	void addSparkThemeStyleAttribute(String name, String description);
	
	void addHaloThemeStyleAttribute(String name, String description);
	
	void addChildrenProperties(IJsfFlexASAttributesClass child);
	
	List<IJsfFlexASAttributesClass> getChildrenASClasses();
	
	List<JsfFlexClassAttribute> getPropertyAttributes();
	
	List<JsfFlexClassAttribute> getEventAttributes();
	
	List<JsfFlexClassAttribute> getEffectAttributes();
	
	List<JsfFlexClassAttribute> getCommonStyleAttributes();
	
	List<JsfFlexClassAttribute> getSparkThemeStyleAttributes();
	
	List<JsfFlexClassAttribute> getHaloThemeStyleAttributes();
	
}
