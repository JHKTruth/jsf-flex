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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.googlecode.jsfflexeclipseplugin.util.JsfFlexEclipsePluginConstants;

/**
 * @author Ji Hoon Kim
 */
public abstract class AbstractJsfFlexASAttributesClassResource 
							implements IJsfFlexASAttributesClass {
	
	private static final Random RANDOM_GENERATOR = new Random();
	
	private final String _packageClassName;
	private final List<IJsfFlexASAttributesClass> _childrenASClasses;
	
	private final List<JsfFlexClassAttribute> _propertyAttributes;
	private final List<JsfFlexClassAttribute> _eventAttributes;
	private final List<JsfFlexClassAttribute> _effectAttributes;
	private final List<JsfFlexClassAttribute> _styleAttributes;
	
	private IResource _resource;
	private IDOMElement _domElement;
	
	AbstractJsfFlexASAttributesClassResource(String packageClassName, String resourceInfo, IDOMElement domElement) {
		super();
		
		_packageClassName = packageClassName;
		_childrenASClasses = new LinkedList<IJsfFlexASAttributesClass>();
		
		_propertyAttributes = new LinkedList<JsfFlexClassAttribute>();
		_effectAttributes = new LinkedList<JsfFlexClassAttribute>();
		_eventAttributes = new LinkedList<JsfFlexClassAttribute>();
		_styleAttributes = new LinkedList<JsfFlexClassAttribute>();
		
		_resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(resourceInfo));
		_domElement = domElement;
	}
	
	/**
	 * Used for aggregating the properties, effects, events, and styles of IJsfFlexASAttributesClass and its 
	 * children and returning a single instance of IJsfFlexASAttributesClass for the View
	 */
	AbstractJsfFlexASAttributesClassResource() {
		super();
		
		_packageClassName = null;
		_childrenASClasses = null;
		
		_propertyAttributes = new LinkedList<JsfFlexClassAttribute>();
		_effectAttributes = new LinkedList<JsfFlexClassAttribute>();
		_eventAttributes = new LinkedList<JsfFlexClassAttribute>();
		_styleAttributes = new LinkedList<JsfFlexClassAttribute>();
	}
	
	/**
	 * Used for creating a child IJsfFlexASAttributesClass instance for the currently selected IDOMElement 
	 * during the parsing of the content
	 * @param packageClassName
	 */
	AbstractJsfFlexASAttributesClassResource(String packageClassName) {
		super();
		
		_packageClassName = packageClassName;
		
		_childrenASClasses = new LinkedList<IJsfFlexASAttributesClass>();
		
		_propertyAttributes = new LinkedList<JsfFlexClassAttribute>();
		_effectAttributes = new LinkedList<JsfFlexClassAttribute>();
		_eventAttributes = new LinkedList<JsfFlexClassAttribute>();
		_styleAttributes = new LinkedList<JsfFlexClassAttribute>();
	}
	
	public String getPackageClassName() {
		return _packageClassName;
	}
	
	public void addChildrenASClass(IJsfFlexASAttributesClass childrenASClass) {
		_childrenASClasses.add(childrenASClass);
	}
	
	public void addPropertyAttribute(String name, String description) {
		_propertyAttributes.add(new JsfFlexClassAttribute(name, description));
	}
	public void addEventAttribute(String name, String description) {
		_eventAttributes.add(new JsfFlexClassAttribute(name, description));
	}
	public void addEffectAttribute(String name, String description) {
		_effectAttributes.add(new JsfFlexClassAttribute(name, description));
	}
	public void addStyleAttribute(String name, String description) {
		_styleAttributes.add(new JsfFlexClassAttribute(name, description));
	}
	
	public List<IJsfFlexASAttributesClass> getChildrenASClasses() {
		return _childrenASClasses;
	}
	
	public List<JsfFlexClassAttribute> getPropertyAttributes() {
		return _propertyAttributes;
	}
	public List<JsfFlexClassAttribute> getEventAttributes() {
		return _eventAttributes;
	}
	public List<JsfFlexClassAttribute> getEffectAttributes() {
		return _effectAttributes;
	}
	public List<JsfFlexClassAttribute> getStyleAttribute() {
		return _styleAttributes;
	}
	
	@Override
	public boolean equals(Object instance) {
		if(!(instance instanceof AbstractJsfFlexASAttributesClassResource)){
			return false;
		}
		
		AbstractJsfFlexASAttributesClassResource currResource = AbstractJsfFlexASAttributesClassResource.class.cast( instance );
		
		if(_domElement == null){
			return _packageClassName.equals(currResource._packageClassName);
		}
		return _domElement.equals(currResource._domElement);
		//return _resource.equals(currResource._resource);
	}
	
	@Override
	public int hashCode() {
		if(_domElement == null){
			return _packageClassName.hashCode();
		}
		return _domElement.hashCode();
		//return _resource.hashCode();
	}
	
	public final class JsfFlexClassAttribute {
		
		private final String _name;
		private final String _description;
		private final int HASH_CODE;
		
		private JsfFlexClassAttribute(String name, String description) {
			super();
			
			_name = name;
			_description = description;
			
			int hashCodeVal = JsfFlexEclipsePluginConstants.HASH_CODE_INIT_VALUE;
			hashCodeVal = JsfFlexEclipsePluginConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _name.hashCode();
			hashCodeVal = JsfFlexEclipsePluginConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _description.hashCode();
			HASH_CODE = hashCodeVal;
		}
		
		public String getName() {
			return _name;
		}
		public String getDescription() {
			return _description;
		}
		
		@Override
		public boolean equals(Object instance) {
			if(!(instance instanceof JsfFlexClassAttribute)) {
				return false;
			}
			
			JsfFlexClassAttribute currAttribute = JsfFlexClassAttribute.class.cast( instance );
			return currAttribute._name.equals(_name);
		}
		
		@Override
		public int hashCode() {
			return HASH_CODE;
		}
		
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if(adapter.isInstance(_resource)) {
			return _resource;
		}
		
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}
	
	public void addChildrenProperties(IJsfFlexASAttributesClass child) {
		
		_effectAttributes.addAll(child.getEffectAttributes());
		_eventAttributes.addAll(child.getEventAttributes());
		_propertyAttributes.addAll(child.getPropertyAttributes());
		_styleAttributes.addAll(child.getStyleAttribute());
		
	}
	
	public static IJsfFlexASAttributesClass aggregateClassAttributes(IJsfFlexASAttributesClass topClass) {
		
		AbstractJsfFlexASAttributesClassResource.AggregatorJsfFlexASAttributesClassResource aggregator = 
			new AbstractJsfFlexASAttributesClassResource.AggregatorJsfFlexASAttributesClassResource();
		
		aggregateClassAttributesHelper(aggregator, topClass);
		
		return aggregator;
	}
	
	private static void aggregateClassAttributesHelper(IJsfFlexASAttributesClass aggregateClass, IJsfFlexASAttributesClass workingClass){
		
		for(IJsfFlexASAttributesClass currChild : workingClass.getChildrenASClasses()) {
			
			aggregateClassAttributesHelper(aggregateClass, currChild);
			aggregateClass.addChildrenProperties(currChild);
			
		}
		
	}
	
	/**
	 * Class for returning an instance of IJsfFlexASAttributesClass with the aggregated content
	 * 
	 * @author JihoonKim
	 */
	private static class AggregatorJsfFlexASAttributesClassResource extends AbstractJsfFlexASAttributesClassResource {
		
		private int _randomNum;
		
		private AggregatorJsfFlexASAttributesClassResource(){
			super();
			
			_randomNum = RANDOM_GENERATOR.nextInt();
		}
		
		@Override
		public boolean equals(Object instance) {
			if(!(instance instanceof AggregatorJsfFlexASAttributesClassResource)){
				return false;
			}
			
			AggregatorJsfFlexASAttributesClassResource currInstance = AggregatorJsfFlexASAttributesClassResource.class.cast( instance );
			return _randomNum == currInstance._randomNum;
		}
		
		@Override
		public int hashCode() {
			return _randomNum;
		}
		
		@Override
		public Object getAdapter(Class adapter) {
			return null;
		}
		
	}

}
