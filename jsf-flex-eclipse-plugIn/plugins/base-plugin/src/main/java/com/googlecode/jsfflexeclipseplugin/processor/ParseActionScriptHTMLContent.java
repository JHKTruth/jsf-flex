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
package com.googlecode.jsfflexeclipseplugin.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.FutureTask;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;
import com.googlecode.jsfflexeclipseplugin.model.JsfFlexCacheManager;

/**
 * @author Ji Hoon Kim
 */
public final class ParseActionScriptHTMLContent {
	
	//constant, public + protected methods. Use Stax
	
	//javax.xml.stream.XMLInputFactory
	/*
	 * javax.xml.stream.XMLStreamReader
	 * javax.xml.stream.events.XMLEvent
	 * 
	 * XMLInputFactory xif = XMLInputFactory.newInstance();
	 * 
	 * XMLStreamReader reader = sij.createXMLStreamReader();
	 * 
	 */
	private static final String ACTION_SCRIPT_URL_PATTERN = "<a .*? href=\".+?\" .*?>.+?</a>";
	private static final Pattern REQUEST_FOR_RESOURCE_PATTERN = Pattern.compile(ACTION_SCRIPT_URL_PATTERN);
	
	private String _packageClassName;
	
	public ParseActionScriptHTMLContent(String packageClassName) {
		super();
		
		_packageClassName = packageClassName;
	}
	
	public void execute() {
		
		String latestASAPIsURL = JsfFlexCacheManager.getLatestASAPIsURL();
		
		try{
			URL asAPIsUrl = new URL(latestASAPIsURL);
			BufferedReader content = new BufferedReader(new InputStreamReader(asAPIsUrl.openStream()));
			
			parseASHTMLContent(content);
			
		}catch(MalformedURLException malformedException) {
			
		}catch(IOException ioException) {
			
		}catch(XMLStreamException xmlStreamException) {
			
		}
		
	}
	
	private void parseASHTMLContent(BufferedReader content) throws XMLStreamException {
		XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
		final XMLStreamReader reader = xmlFactory.createXMLStreamReader(content);
		
		FutureTask<IJsfFlexASAttributesClass> constructJsfFlexASAttributesClass = new FutureTask<IJsfFlexASAttributesClass>(new Runnable() {
			
			@Override
			public void run() {
				
			}
			
		}, null);
	}
	
	public String getPackageClassName() {
		return _packageClassName;
	}
	public void setPackageClassName(String packageClassName) {
		_packageClassName = packageClassName;
	}
	
}
