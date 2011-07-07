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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;
import com.googlecode.jsfflexeclipseplugin.model.JsfFlexCacheManager;

/**
 * @author Ji Hoon Kim
 */
public final class ParseActionScriptHTMLContent {
	
	/*
	 * Use Stax for fun?
	 */
	private static final String HREF_ATTRIBUTE = "href";
	
	private static final CleanerProperties HTML_CLEANER_PROPERTIES;
	
	static{
		HTML_CLEANER_PROPERTIES = new CleanerProperties();
		HTML_CLEANER_PROPERTIES.setOmitComments(true);
		HTML_CLEANER_PROPERTIES.setNamespacesAware(true);
	}
	
	private String _packageClassName;
	private IJsfFlexASAttributesClass _classResource;
	private FutureTask<IJsfFlexASAttributesClass> _constructJsfFlexASAttributesClass;
	
	
	public ParseActionScriptHTMLContent(String packageClassName, IJsfFlexASAttributesClass classResource) {
		super();
		
		_classResource = classResource;
		_packageClassName = packageClassName;
	}
	
	public void execute() {
		
		String latestASAPIsURL = JsfFlexCacheManager.getLatestASAPIsURL();
		
		BufferedReader asPackageUrlReader = null;
		
		try{
			URL asAPIsUrl = new URL(latestASAPIsURL);
			asPackageUrlReader = new BufferedReader(new InputStreamReader(asAPIsUrl.openStream()));
			
			String elementUrl = retrieveElementHref(asPackageUrlReader);
			if(elementUrl != null) {
				URL asElementUrl = new URL(elementUrl);
				parseASHTMLContent(asElementUrl);
			}
		}catch(MalformedURLException malformedException) {
			
		}catch(IOException ioException) {
			
		}catch(XPatherException xpatherException){
			
		}finally {
			if(asPackageUrlReader != null){
				try{
					asPackageUrlReader.close();
				}catch(IOException ioException){
					
				}
			}
		}
		
	}
	
	private String retrieveElementHref(BufferedReader asPackageUrlReader) throws IOException, XPatherException{
		
		String elementUrl = null;
		String[] splitted = _packageClassName.split("\\.");
		
		String anchorXPath = "//a[.='" + splitted[splitted.length - 1] + "']";
		HtmlCleaner asPackageCleaner = new HtmlCleaner(HTML_CLEANER_PROPERTIES);
		
		TagNode nodeContent = asPackageCleaner.clean(asPackageUrlReader);
		Object[] pathContent = nodeContent.evaluateXPath(anchorXPath);
		
		if(pathContent.length == 1){
			//should be a size of one
			TagNode anchorNode = TagNode.class.cast( pathContent[0] );
			elementUrl = anchorNode.getAttributeByName(HREF_ATTRIBUTE);
		}
		
		return elementUrl;
	}
	
	private static int NUMBER_OF_ATTRIBUTES_TO_PARSE_FOR_A_CLASS = 4;
	
	private enum CLASS_PARSE_ATTRIBUTES {
		PROPERTY("//table[@id='summaryTableProperty']//tr", "//a[@class='signatureLink']", "//div[@class='summaryTableDescription']"), 
		EVENT("", "", ""), EFFECTS("", "", ""), COMMON_STYLES("", "", ""), SPARK_THEME_STYLES("", "", ""), HALO_THEME_STYLES("", "", "");
		
		private final String _tableXMLPath;
		private final String _nameXMLPath;
		private final String _descriptionXMLPath;
		
		CLASS_PARSE_ATTRIBUTES(String tableXMLPath, String nameXMLPath, String descriptionXMLPath){
			_tableXMLPath = tableXMLPath;
			_nameXMLPath = nameXMLPath;
			_descriptionXMLPath = descriptionXMLPath;
		}
		
	}
	
	private void parseASHTMLAttributes(final CountDownLatch currASElementLatch, final CLASS_PARSE_ATTRIBUTES currParseAttribute, 
										final TagNode asElementTopNode, final Map<String, String> currAttributeMap){
		new Thread(new Runnable(){
            
            public void run() {
            	try{
            		Object[] nodes = asElementTopNode.evaluateXPath( "" );
            		//List<TagNode> tag
            	}catch(XPatherException xpatherException) {
            		
            	}
            	
                currASElementLatch.countDown();
            }
            
        }).start();
	}
	
	private void parseASHTMLContent(final URL asElementUrl) {
		
		_constructJsfFlexASAttributesClass = new FutureTask<IJsfFlexASAttributesClass>(new Runnable() {
			
			@Override
			public void run() {
				
				BufferedReader asElementUrlReader = null;
				
				try{
					asElementUrlReader = new BufferedReader(new InputStreamReader(asElementUrl.openStream()));
					HtmlCleaner asElementCleaner = new HtmlCleaner(HTML_CLEANER_PROPERTIES);
					
					CountDownLatch currASElementLatch = new CountDownLatch(NUMBER_OF_ATTRIBUTES_TO_PARSE_FOR_A_CLASS);
					
					for(CLASS_PARSE_ATTRIBUTES classParseAttributes : CLASS_PARSE_ATTRIBUTES.values()){
						
					}
					
					try{
						currASElementLatch.await();
		            }catch(InterruptedException interruptedExcept){
		                Thread.currentThread().interrupt();
		            }
		            
					//summaryTableProperty
				}catch(IOException ioException) {
					
				}finally{
					if(asElementUrlReader != null){
						try{
							asElementUrlReader.close();
						}catch(IOException ioException) {
							
						}
					}
				}
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
