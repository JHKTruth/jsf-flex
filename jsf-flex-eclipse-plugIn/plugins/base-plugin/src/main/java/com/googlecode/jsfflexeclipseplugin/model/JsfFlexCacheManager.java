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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.w3c.dom.Node;

import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;
import com.googlecode.jsfflexeclipseplugin.preferences.PreferenceConstants;
import com.googlecode.jsfflexeclipseplugin.processor.ParseActionScriptHTMLContent;

/**
 * 
 * @author Ji Hoon Kim
 */
public final class JsfFlexCacheManager {
	
	private static final String JSF_FLEX_AS_ATTRIBUTE_CLASS_STATE_FILE_NAME = "jsfFlexASAttributeClassState.xml";
	private static final String ROOT_TAG = "JsfFlexASAttributeClassStates";
	private static final String LAST_VIEWED_JSF_FLEX_AS_ATTRIBUTE_CLASS = "LastViewedJsfFlexASAttributeClass";
	
	private static JsfFlexCacheManager _instance;
	private static String lastViewedJsfFlexASAttributeClassPackageName;
	
	/*
	 * Will contain references to elements which are used 
	 * frequently [i.e. Object class]
	 */
	private Map<String, IJsfFlexASAttributesClass> _persistentClassTypeCache;
	private Map<WeaklyBoundCacheKey<String>, IJsfFlexASAttributesClass> _weaklyBoundClassTypeCache;
	
	private static File _jsfFlexASAttributeClassStateFile;
	
	static {
		IPath path = JsfFlexActivator.getDefault().getStateLocation().append(JSF_FLEX_AS_ATTRIBUTE_CLASS_STATE_FILE_NAME);
		if(!path.isEmpty()){
			_jsfFlexASAttributeClassStateFile = path.toFile();
		}
	}
	
	private class WeaklyBoundCacheKey <E> {
		
		private final E _cacheKey;
		private final int HASH_CODE;
		
		private WeaklyBoundCacheKey(E cacheKey){
			super();
			
			_cacheKey = cacheKey;
			HASH_CODE = _cacheKey.hashCode();
		}
		
		@Override
		public boolean equals(Object instance) {
			if(!(instance instanceof WeaklyBoundCacheKey)){
				return false;
			}
			
			WeaklyBoundCacheKey<E> currInstance = WeaklyBoundCacheKey.class.cast( instance );
			return _cacheKey.equals(currInstance._cacheKey);
		}
		
		@Override
		public int hashCode() {
			return HASH_CODE;
		}
		
	}
	
	private JsfFlexCacheManager(){
		super();
		
		_persistentClassTypeCache = new HashMap<String, IJsfFlexASAttributesClass>();
		_weaklyBoundClassTypeCache = new WeakHashMap<WeaklyBoundCacheKey<String>, IJsfFlexASAttributesClass>();
		loadLastViewedJsfFlexASAttributeClass();
	}
	
	public boolean containsPackageClassName(String packageClassName) {
		if(_persistentClassTypeCache.containsKey(packageClassName)){
			return true;
		}
		
		WeaklyBoundCacheKey<String> weakCacheTest = new WeaklyBoundCacheKey<String>(packageClassName);
		boolean inWeaklyBoundClassTypeCache = _weaklyBoundClassTypeCache.containsKey(weakCacheTest);
		if(inWeaklyBoundClassTypeCache){
			IJsfFlexASAttributesClass classResource = _weaklyBoundClassTypeCache.remove(weakCacheTest);
			_persistentClassTypeCache.put(packageClassName, classResource);
			
			return true;
		}else{
			return false;
		}
		
	}
	
	public void addJsfFlexASAttributesClassResource(IJsfFlexASAttributesClass classResource) {
		
		String packageClassName = classResource.getPackageClassName();
		if(_persistentClassTypeCache.containsKey(packageClassName)){
			return;
		}
		
		WeaklyBoundCacheKey<String> weakCacheTest = new WeaklyBoundCacheKey<String>(packageClassName);
		boolean inWeaklyBoundClassTypeCache = _weaklyBoundClassTypeCache.containsKey(weakCacheTest);
		boolean isTopClass = classResource.getNode() != null;
		
		if(isTopClass){
			if(inWeaklyBoundClassTypeCache){
				//then will need to remove it from weak Map and push it to the persistent Map
				_weaklyBoundClassTypeCache.remove(weakCacheTest);
			}
			
			_persistentClassTypeCache.put(packageClassName, classResource);
		}else{
			
			_weaklyBoundClassTypeCache.put(weakCacheTest, classResource);
		}
		
	}
	
	public static boolean attemptFetchOfLatestASAPIs() {
		IPreferenceStore preferences = JsfFlexActivator.getDefault().getPreferenceStore();
		
		return preferences.getBoolean(PreferenceConstants.ATTEMPT_FETCH_OF_LATEST_AS_APIS);
	}
	
	public static String getLatestASAPIsURL() {
		IPreferenceStore preferences = JsfFlexActivator.getDefault().getPreferenceStore();
		
		return preferences.getString(PreferenceConstants.LATEST_AS_APIS_URL);
	}
	
	public static IJsfFlexASAttributesClass getJsfFlexASAttributesClass(String packageClassName, String resourceInfo, Node node) {
		return new JsfFlexASAttributesClassResource(packageClassName, resourceInfo, node);
	}
	
	/**
	 * 
	 * Will be used :
	 * 1)	When fetching the last viewed Jsf Flex AS Attribute Class
	 * 2)	When populating for the super class for the currently inspecting top class
	 * 
	 * @param packageClassName
	 * @param classResource
	 * @return
	 */
	public static IJsfFlexASAttributesClass getDummyJsfFlexASAttributesClass(String packageClassName, boolean classResource) {
		if(classResource) {
			return new JsfFlexASAttributesClassResource(packageClassName);
		}else{
			return new JsfFlexASAttributesInterfaceResource(packageClassName);
		}
	}
	
	public static synchronized JsfFlexCacheManager getInstance() {
		
		if(_instance == null){
			_instance = new JsfFlexCacheManager();
		}
		
		return _instance;
	}
	
	public static synchronized void saveLastViewedJsfFlexASAttributeClass() {
		if(lastViewedJsfFlexASAttributeClassPackageName == null) {
			return;
		}
		
		XMLMemento root = XMLMemento.createWriteRoot(ROOT_TAG);
		root.putString(LAST_VIEWED_JSF_FLEX_AS_ATTRIBUTE_CLASS, lastViewedJsfFlexASAttributeClassPackageName);
		
		FileWriter writer = null;
		try{
			writer = new FileWriter(_jsfFlexASAttributeClassStateFile);
			root.save(writer);
		}catch(IOException ioException) {
			
		}finally {
			try{
				if(writer != null) {
					writer.close();
				}
			}catch(IOException ioException) {
				
			}
		}
		
	}
	
	public static synchronized void loadLastViewedJsfFlexASAttributeClass() {
		
		if(_jsfFlexASAttributeClassStateFile != null && _jsfFlexASAttributeClassStateFile.exists()){
			FileReader reader = null;
			try{
				reader = new FileReader(_jsfFlexASAttributeClassStateFile);
				IMemento memento = XMLMemento.createReadRoot(reader);
				IMemento rootNode = memento.getChild(ROOT_TAG);
				
				String storedLastViewedJsfFlexASAttributeClassPackageName = rootNode.getString(LAST_VIEWED_JSF_FLEX_AS_ATTRIBUTE_CLASS);
				
				IJsfFlexASAttributesClass topJsfFlexASAttributesClass = JsfFlexCacheManager.getDummyJsfFlexASAttributesClass(storedLastViewedJsfFlexASAttributeClassPackageName, true);
				ParseActionScriptHTMLContent parserActionScriptHTMLContent = new ParseActionScriptHTMLContent(topJsfFlexASAttributesClass);
				parserActionScriptHTMLContent.schedule();
				
			}catch(FileNotFoundException fnfException) {
				
			}catch(WorkbenchException wbException) {
				
			}finally {
				try{
				if(reader != null){
					reader.close();
				}
				}catch(IOException ioException) {
					
				}
			}
		}
		
	}
	
}
