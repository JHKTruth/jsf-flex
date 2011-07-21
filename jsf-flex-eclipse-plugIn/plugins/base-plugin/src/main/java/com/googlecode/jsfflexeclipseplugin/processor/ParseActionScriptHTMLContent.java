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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;
import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;
import com.googlecode.jsfflexeclipseplugin.model.JsfFlexCacheManager;
import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource.JsfFlexClassAttribute;

/**
 * @author Ji Hoon Kim
 */
public final class ParseActionScriptHTMLContent extends Job {
	
	/*
	 * Use Stax for fun?
	 */
	private static final String HREF_ATTRIBUTE = "href";
	private static final CleanerProperties HTML_CLEANER_PROPERTIES;
	private static final int NUM_OF_PARSE_THREADS = 10;
	private final ExecutorService _parseService = Executors.newFixedThreadPool(NUM_OF_PARSE_THREADS);
	
	static {
		HTML_CLEANER_PROPERTIES = new CleanerProperties();
		HTML_CLEANER_PROPERTIES.setOmitComments(true);
		HTML_CLEANER_PROPERTIES.setNamespacesAware(true);
	}
	
	private IJsfFlexASAttributesClass _classResource;
	private IProgressMonitor _progressMonitor;
	
	public ParseActionScriptHTMLContent(IJsfFlexASAttributesClass classResource) {
		super(Messages.PARSING_OF_TOP_AS_CLASS + classResource.getPackageClassName());
		
		_classResource = classResource;
	}
	
	private String getSimpleClassName(IJsfFlexASAttributesClass classResource) {
		String[] splitted = classResource.getPackageClassName().split("\\.");
		return splitted[splitted.length - 1];
	}
	
	@Override
	protected IStatus run(IProgressMonitor progressMonitor) {
		
		_progressMonitor = progressMonitor;
		_progressMonitor.beginTask(Messages.STARTING_THE_AS_PARSING_PROCESS, IProgressMonitor.UNKNOWN);
		String latestASAPIsURL = JsfFlexCacheManager.getLatestASAPIsURL();
		
		BufferedReader asPackageUrlReader = null;
		try{
			URL asAPIsUrl = new URL(latestASAPIsURL);
			asPackageUrlReader = new BufferedReader(new InputStreamReader(asAPIsUrl.openStream()));
			
			String simpleClassName = getSimpleClassName(_classResource);
			String elementUrl = retrieveElementHref(asPackageUrlReader, simpleClassName);
			if(elementUrl != null){
				URL asElementUrl = new URL(elementUrl);
				parseASHTMLContent(asElementUrl, _classResource);
			}
			
			_parseService.shutdownNow();
		}catch(MalformedURLException malformedException){
			return new Status(Status.ERROR, JsfFlexActivator.PLUGIN_ID, Messages.MALFORMED_URL_EXCEPTION_WAS_THROWN, malformedException);
		}catch(IOException ioException){
			return new Status(Status.ERROR, JsfFlexActivator.PLUGIN_ID, Messages.IO_EXCEPTION_WAS_THROWN, ioException);
		}catch(XPatherException xpatherException){
			return new Status(Status.ERROR, JsfFlexActivator.PLUGIN_ID, Messages.XPATHER_EXCEPTION_WAS_THROWN, xpatherException);
		}finally{
			if(asPackageUrlReader != null){
				try{
					asPackageUrlReader.close();
				}catch(IOException ioException){
					
				}
			}
		}
		
		return Status.OK_STATUS;
	}
	
	private static String AS_LATEST_URL_BASE_PATH;
	
	public static void resetASLatestUrlBasePath() {
		//will be invoked when the user changes the value within the preference page
		
		AS_LATEST_URL_BASE_PATH = null;
	}
	
	private static synchronized String getASLatestUrlBasePath() {
		if(AS_LATEST_URL_BASE_PATH == null){
			String latestASAPIsURL = JsfFlexCacheManager.getLatestASAPIsURL();
			int lastDirectoryIndex = latestASAPIsURL.lastIndexOf('/');
			AS_LATEST_URL_BASE_PATH = latestASAPIsURL.substring(0, lastDirectoryIndex + 1);
		}
		
		return AS_LATEST_URL_BASE_PATH;
	}
	
	private static final String SUMMARY_CLASS_PATH = "//div[@id='content']";
	
	private String retrieveElementHref(BufferedReader asPackageUrlReader, String simpleClassName) throws IOException, XPatherException {
		
		String elementUrl = null;
		
		String anchorXPath = "//a[.='" + simpleClassName + "']";
		HtmlCleaner asPackageCleaner = new HtmlCleaner(HTML_CLEANER_PROPERTIES);
		
		TagNode rootContent = asPackageCleaner.clean(asPackageUrlReader);
		Object[] contentResult = rootContent.evaluateXPath(SUMMARY_CLASS_PATH);
		
		if(contentResult.length == 1){
			TagNode nodeContent = TagNode.class.cast( contentResult[0] );
			Object[] pathContent = nodeContent.evaluateXPath(anchorXPath);
			
			if(pathContent.length == 1){
				//should be a size of one
				TagNode anchorNode = TagNode.class.cast( pathContent[0] );
				elementUrl = ParseActionScriptHTMLContent.getASLatestUrlBasePath() + anchorNode.getAttributeByName(HREF_ATTRIBUTE);
			}
		}
		
		return elementUrl;
	}
	
	public enum CLASS_ATTRIBUTES_FIELD {
		
		PROPERTY("//table[@id='summaryTableProperty']//tr[@class='']", "//a[@class='signatureLink']", "//div[@class='summaryTableDescription']"), 
		EVENT("//table[@id='summaryTableEvent']//tr[@class='']", "//a[@class='signatureLink']", "//td[@class='summaryTableDescription summaryTableCol']"), 
		EFFECT("//table[@id='summaryTableEffect']//tr[@class='']", "//span[@class='signatureLink']", "//td[@class='summaryTableDescription']"), 
		COMMON_STYLE("//table[@id='summaryTablecommonStyle']//tr[@class='']", "//span[@class='signatureLink']", "//td[@class='summaryTableDescription']"), 
		SPARK_THEME_STYLE("//table[@id='summaryTablesparkStyle']//tr[@class='']", "//span[@class='signatureLink']", "//td[@class='summaryTableDescription']"), 
		HALO_THEME_STYLE("//table[@id='summaryTablehaloStyle']//tr[@class='']", "//span[@class='signatureLink']", "//td[@class='summaryTableDescription']");
		
		private final String _tableXMLPath;
		private final String _nameXMLPath;
		private final String _descriptionXMLPath;
		
		CLASS_ATTRIBUTES_FIELD(String tableXMLPath, String nameXMLPath, String descriptionXMLPath){
			_tableXMLPath = tableXMLPath;
			_nameXMLPath = nameXMLPath;
			_descriptionXMLPath = descriptionXMLPath;
		}
		
		private String getTableXMLPath(){
			return _tableXMLPath;
		}
		
		private String getNameXMLPath(){
			return _nameXMLPath;
		}
		
		private String getDescriptionXMLPath(){
			return _descriptionXMLPath;
		}
		
		private void addClassAttribute(IJsfFlexASAttributesClass asAttributesClass, String name, String description) {
			switch(this){
			case PROPERTY: asAttributesClass.addPropertyAttribute(name, description); break;
			case EVENT: asAttributesClass.addEventAttribute(name, description); break;
			case EFFECT: asAttributesClass.addEffectAttribute(name, description); break;
			case COMMON_STYLE: asAttributesClass.addCommonStyleAttribute(name, description); break;
			case SPARK_THEME_STYLE: asAttributesClass.addSparkThemeStyleAttribute(name, description); break;
			case HALO_THEME_STYLE: asAttributesClass.addHaloThemeStyleAttribute(name, description); break;
			}
		}
		
		public void addClassAttributesToAggregator(IJsfFlexASAttributesClass asAggregatedAttributesClass, List<JsfFlexClassAttribute> jsfFlexClassAttributes) {
			switch(this){
			case PROPERTY: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getPropertyAttributes()); break;
			case EVENT: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getEventAttributes()); break;
			case EFFECT: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getEffectAttributes()); break;
			case COMMON_STYLE: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getCommonStyleAttributes()); break;
			case SPARK_THEME_STYLE: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getSparkThemeStyleAttributes()); break;
			case HALO_THEME_STYLE: jsfFlexClassAttributes.addAll(asAggregatedAttributesClass.getHaloThemeStyleAttributes()); break;
			}
		}
		
	}
	
	private void parseASHTMLAttributes(CLASS_ATTRIBUTES_FIELD currClassAttributesField, TagNode asElementRootNode, IJsfFlexASAttributesClass currentInspectingASAttributesClass) {
		try{
			
			_progressMonitor.subTask(Messages.PARSING + currClassAttributesField + Messages.ATTRIBUTES_OF_AS_CLASS + currentInspectingASAttributesClass.getPackageClassName());
			//allow certain attributes to error by catching the exception here
			Object[] attributeQueryResult = asElementRootNode.evaluateXPath(currClassAttributesField.getTableXMLPath());
			for(Object currAttributeQueryResult : attributeQueryResult) {
				
				if(currAttributeQueryResult instanceof TagNode) {
					TagNode currAttributeNode = TagNode.class.cast( currAttributeQueryResult );
					Object[] nameResult = currAttributeNode.evaluateXPath( currClassAttributesField.getNameXMLPath() );
					Object[] descriptionResult = currAttributeNode.evaluateXPath( currClassAttributesField.getDescriptionXMLPath() );
					
					if(nameResult != null && nameResult.length == 1 && descriptionResult != null && descriptionResult.length == 1){
						TagNode nameNode = TagNode.class.cast( nameResult[0] );
						TagNode descriptionNode = TagNode.class.cast( descriptionResult[0] );
						
						String name = nameNode.getText().toString();
						String description = descriptionNode.getText().toString();
						currClassAttributesField.addClassAttribute(currentInspectingASAttributesClass, name, description);
						
					}
				}
				
			}
			
		}catch(XPatherException xpatherException){
			
		}
	}
	
	private static final String INHERITANCE_LIST_XML_PATH = "//td[@class='inheritanceList']/a";
	
	private void parseASHTMLContent(URL asElementUrl, IJsfFlexASAttributesClass currentInspectingASAttributesClass) {
		
		BufferedReader asElementUrlReader = null;
		/*
		 * If one gets to here, technically one should have network access since otherwise one would have hit the exception 
		 * within the run method 
		 */
		
		try{
			_progressMonitor.subTask(Messages.STARTING_THE_PARSE_OF_AS_CLASS + currentInspectingASAttributesClass.getPackageClassName());
			asElementUrlReader = new BufferedReader(new InputStreamReader(asElementUrl.openStream()));
			
			HtmlCleaner asElementCleaner = new HtmlCleaner(HTML_CLEANER_PROPERTIES);
			/*
			 * First check out the inheritance list and perform a recursive call on them
			 */
			TagNode rootNode = asElementCleaner.clean(asElementUrlReader);
			Object[] inheritanceList = rootNode.evaluateXPath(INHERITANCE_LIST_XML_PATH);
			Map<IJsfFlexASAttributesClass, URL> inheritanceMap = new HashMap<IJsfFlexASAttributesClass, URL>();
			JsfFlexCacheManager cacheManagerInstance = JsfFlexCacheManager.getInstance();
			
			for(Object currSuperClass : inheritanceList){
				TagNode currentSuperClass = TagNode.class.cast( currSuperClass );
				String packageClassName = currentSuperClass.getText().toString().trim();
				boolean containsPackageClassName = cacheManagerInstance.containsPackageClassName(packageClassName);
				if(!containsPackageClassName){
					//Add it to a list of entries
					
					IJsfFlexASAttributesClass newPackageClassInstance = JsfFlexCacheManager.getDummyJsfFlexASAttributesClass(packageClassName, true);
					BufferedReader newPackageClassInstanceReader = null;
					try{
						URL newPackageClassInstanceUrl = asElementUrl.toURI().resolve(currentSuperClass.getAttributeByName(HREF_ATTRIBUTE)).toURL();
						
						inheritanceMap.put(newPackageClassInstance, newPackageClassInstanceUrl);
					}catch(URISyntaxException uriSyntaxException){
						continue;
					}catch(MalformedURLException malformedUrlException){
						continue;
					}finally{
						if(newPackageClassInstanceReader != null){
							try{
								newPackageClassInstanceReader.close();
							}catch(IOException ioException){
								
							}
						}
					}
				}
			}
			
			if(inheritanceMap.size() > 0){
				final CountDownLatch currASElementLatch = new CountDownLatch(inheritanceMap.size());
				//recurse on each children
				
				for(final IJsfFlexASAttributesClass currAttributesClass : inheritanceMap.keySet()){
					final URL currAttributesClassUrl = inheritanceMap.get(currAttributesClass);
					currentInspectingASAttributesClass.addChildrenASClass(currAttributesClass);
					
					_parseService.execute(new Runnable(){
						
						@Override
						public void run() {
							parseASHTMLContent(currAttributesClassUrl, currAttributesClass);
							currASElementLatch.countDown();
						}
						
					});
				}
				
				try{
					currASElementLatch.await();
			    }catch(InterruptedException interruptedExcept){
			    	Thread.currentThread().interrupt();
			    }
			    
			}
			
			for(CLASS_ATTRIBUTES_FIELD currClassAttributesField : CLASS_ATTRIBUTES_FIELD.values()){
				
				parseASHTMLAttributes(currClassAttributesField, rootNode, currentInspectingASAttributesClass);
				
			}
			
		}catch(XPatherException xpatherException){
			
		}catch(IOException ioException){
			
		}finally{
			if(asElementUrlReader != null){
				try{
					asElementUrlReader.close();
				}catch(IOException ioException) {
					
				}
			}
		}
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(_parseService != null){
			_parseService.shutdownNow();
		}
	}
	
}
