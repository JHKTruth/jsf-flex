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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;
import com.googlecode.jsfflexeclipseplugin.preferences.PreferenceConstants;

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
	private Map<String, JsfFlexASAttributesClassResource> _persistentClassTypeCache;
	private Map<String, JsfFlexASAttributesClassResource> _weaklyBoundClassTypeCache;
	
	private static File jsfFlexASAttributeClassStateFile;
	
	static {
		jsfFlexASAttributeClassStateFile = JsfFlexActivator.getDefault().getStateLocation().append(JSF_FLEX_AS_ATTRIBUTE_CLASS_STATE_FILE_NAME).toFile();
	}
	
	private JsfFlexCacheManager(){
		super();
		
		_persistentClassTypeCache = new HashMap<String, JsfFlexASAttributesClassResource>();
		_weaklyBoundClassTypeCache = new WeakHashMap<String, JsfFlexASAttributesClassResource>();
		loadLastViewedJsfFlexASAttributeClass();
	}
	
	public static boolean attemptFetchOfLatestASAPIs() {
		IPreferenceStore preferences = JsfFlexActivator.getDefault().getPreferenceStore();
		
		return preferences.getBoolean(PreferenceConstants.ATTEMPT_FETCH_OF_LATEST_AS_APIS);
	}
	
	public static String getLatestASAPIsURL() {
		IPreferenceStore preferences = JsfFlexActivator.getDefault().getPreferenceStore();
		
		return preferences.getString(PreferenceConstants.LATEST_AS_APIS_URL);
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
			writer = new FileWriter(jsfFlexASAttributeClassStateFile);
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
		
		FileReader reader = null;
		try{
			reader = new FileReader(jsfFlexASAttributeClassStateFile);
			IMemento memento = XMLMemento.createReadRoot(reader);
			IMemento rootNode = memento.getChild(ROOT_TAG);
			
			String storedLastViewedJsfFlexASAttributeClassPackageName = rootNode.getString(LAST_VIEWED_JSF_FLEX_AS_ATTRIBUTE_CLASS);
			
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
