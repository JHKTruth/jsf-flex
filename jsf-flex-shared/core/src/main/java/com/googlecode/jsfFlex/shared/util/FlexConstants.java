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
package com.googlecode.jsfFlex.shared.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.googlecode.jsfFlex.shared.util.annotation.ISwcActionScriptFile;
import com.googlecode.jsfFlex.shared.util.annotation.ISwcActionScriptFiles;

/**
 * A constant class that defines various properties for the system.<br>
 * 
 * @author Ji Hoon Kim
 */
@ISwcActionScriptFiles(
        actionScriptFiles={
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.component.DataGridColumnServiceRequest"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.component.DataGridServiceRequest"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.core.ComponentValueMapper"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.core.ConstructActionScriptObject"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.AbstractEventHandler"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.DataUpdateEventHandler"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.PropertyUpdateEventHandler"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.SubmitFormEventHandler"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.helper.ItemSelectHelper"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.event.helper.ScrollEventHelper"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.logger.AbstractLogger"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.logger.ILogger"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.logger.JavaScriptLogger"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.logger.LoggerFactory"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.services.JsfFlexHttpService"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.utils.JsfFlexUtils"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.utils.WebConstants"),
                @ISwcActionScriptFile(actionScriptFile="com.googlecode.jsfFlex.communication.validator.ValidationManager")
        }
)
public final class FlexConstants {
	
	public static final String FLEX_JAVA_SDK_PATH = "com.googlecode.jsfFlex.FlexJavaPath";
	public static final String FLEX_JVM_CONFIG_PATH = "bin/jvm.config";
	public static final String FLEX_JVM_CONFIG_JAVA_HOME = "java.home=";
	
	public static final String LOCALE_WEB_CONTEXT_RELATIVE_PATH = "com.googlecode.jsfFlex.LocaleWebContextRelativePath";
	public static final String DEFAULT_LOCALE = "com.googlecode.jsfFlex.DefaultLocale";
	public static final String DEFAULT_LOCALE_SWF_PATH_KEY = "DefaultLocaleSwfPathKey";
    public static final String EN_US = "en_us";
	public static final char SWF_FILE_NAME_LOCALE_SEPARATOR = '-';
	
	public static final String FLASH_TO_JAVASCRIPT_LOG_LEVEL_NAME = "com.googlecode.jsfFlex.FlashToJavaScriptLogLevel";
	public static final String FLASH_TO_JAVASCRIPT_LOG_LOG_LEVEL = "Log";
	public static final String FLASH_TO_JAVASCRIPT_LOG_DEBUG_LEVEL = "Debug";
	public static final String FLASH_TO_JAVASCRIPT_LOG_INFO_LEVEL = "Info";
	public static final String FLASH_TO_JAVASCRIPT_LOG_WARN_LEVEL = "Warn";
	public static final String FLASH_TO_JAVASCRIPT_LOG_ERROR_LEVEL = "Error";
	
	public static final String CONFIG_MODE_NAME = "com.googlecode.jsfFlex.MODE";
	public static final String PRODUCTION_MODE = "productionMode";
	public static final String PROVIDED_FLEX_SDK_PATH = "flexSDKPath";
    
    
	public static final String UTF_8_ENCODING = "UTF-8";
	public static final String STRING_QUOTE = "\"";
	
	public static final String PRE_MXML_FILE_EXT = ".preMxml";
	public static final String MXML_FILE_EXT = ".mxml";
	public static final String SWF_FILE_EXT = ".swf";
	public static final String XML_FILE_EXT = ".xml";
	public static final String SWC_FILE_EXT = ".swc";
    
    public static final String FLEX_SDK_PART_1_ZIP = "flexSDK_1.zip";
    public static final String FLEX_SDK_PART_2_ZIP = "flexSDK_2.zip";
    public static final String FLEX_SDK_PART_3_ZIP = "flexSDK_3.zip";
	public static final String DEFAULT_SWC_LIBRARY_SWF_NAME = "library.swf";
	public static final String JSF_FLEX_MAIN_SWC_CONFIGURATIONFILE = "jsfFlexMainSwcConfigurationFile.xml";
	public static final String JSF_FLEX_MAIN_SWC_DIRECTORY_NAME = "jsfFlexMainSwcFileSystem";
	public static final String JSF_FLEX_MAIN_SWC_ARCHIVE_NAME = "jsfFlexMainSwc";
	
	public static final String CHILD_REPLACE_TOKEN = "<!-- Child Component -->";
	public static final String SIBLING_REPLACE_TOKEN = "<!-- Sibling Component -->";
	
	public static final String CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE = "<!-- Child Component ";
	public static final String CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF = " -->";
	public static final String SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE = "<!-- Sibling Component ";
	public static final String SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF = " -->";
	
	public static final String TAG_BODY_CONTENT_ATTR = "tagBodyContent";
	public static final String TAG_BODY_CONTENT_TOKEN = "{tagBodyContent}";
	
	public static final String MXML_DIRECTORY_NAME = "mxml";
	public static final String SWF_DIRECTORY_NAME = "swf";
	public static final String PREMXML_DIRECTORY_NAME = "preMxml";
	public static final String FLEX_SDK_DIRECTORY_NAME = "flexSDK";
	public static final String SWC_DIRECTORY_NAME = "swc";
	
	private static final String FLEX_CONSTANTS_XML = "flexConstants.xml";
    
    public static final int HASH_CODE_INIT_VALUE = 3;
	public static final int HASH_CODE_MULTIPLY_VALUE = 31;
	
	public static final boolean WINDOWS_SYSTEM;
	
	public static final String CLASS_ID;
	public static final String CODE_BASE;
	public static final String PLUGINS_PAGE;
	
	public static final String JSF_FLEX_MAIN_SWC_CONFIG_FILE;
	
	private static final List<String> _swcSourceFiles;
	private static final List<String> _swfSourceFiles;
	
	private static Map<String, String> _tempParseMap;
	
	private FlexConstants(){
		super();
	}
	
	static{
		
		WINDOWS_SYSTEM = (System.getProperty("line.separator").equals("\r\n"));
		_tempParseMap = new HashMap<String, String>();
		
		_swcSourceFiles = new LinkedList<String>();
		_swfSourceFiles = new LinkedList<String>();
		
		try{
			parseSetProperties();
		}catch(Exception rendererListException){
			throw new RuntimeException("Failure in parsing of " + FLEX_CONSTANTS_XML, rendererListException);
		}
		
		CLASS_ID = _tempParseMap.get("CLASS_ID");
		CODE_BASE = _tempParseMap.get("CODE_BASE");
		PLUGINS_PAGE = _tempParseMap.get("PLUGINS_PAGE");
		JSF_FLEX_MAIN_SWC_CONFIG_FILE = _tempParseMap.get("JSF_FLEX_MAIN_SWC_CONFIG_FILE");
		
		_tempParseMap = null;
	}
	
	private static void parseSetProperties() throws IOException, ParserConfigurationException, SAXException{
		
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(FlexConstants.class.getResourceAsStream(FLEX_CONSTANTS_XML), new DefaultHandler(){
			
			private boolean settings = true;
			private boolean windows = false;
			private boolean nonWindows = false;
			private boolean systemIndependent = false;
			
			private boolean swfHtmlAttribute = false;
			private boolean classId = false;
			private boolean codeBase = false;
			private boolean pluginsPage = false;
			private boolean swcSourceFiles = false;
			private boolean swfSourceFiles = false;
			private boolean sourceFile = false;
			private boolean jsfFlexMainSwcConfigFile = false;
			
			private StringBuilder nodeValue;
			
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				
				if(qName.equals("settings")){
					//Container so return after set
					settings = true;
					return;
				}else if(qName.equals("windows")){
					settings = false;
					windows = true;
				}else if(qName.equals("non-windows")){
					windows = false;
					nonWindows = true;
				}else if(qName.equals("system-independent")){
					nonWindows = false;
					systemIndependent = true;
				}else if(qName.equals("swf-html-attibute")){
					swfHtmlAttribute = true;
				}else if(qName.equals("class-id")){
					classId = true;
				}else if(qName.equals("code-base")){
					codeBase = true;
				}else if(qName.equals("plugins-page")){
					pluginsPage = true;
				}else if(qName.equals("swc-source-files")){
					swcSourceFiles = true;
				}else if(qName.equals("swf-source-files")){
					swfSourceFiles = true;
				}else if(qName.equals("source-file")){
					sourceFile = true;
				}else if(qName.equals("jsf-flex-main-swc-config-file")){
					jsfFlexMainSwcConfigFile = true;
				}
				
				nodeValue = new StringBuilder();
			}
			
			public void endElement(String uri, String localName, String qName) throws SAXException {
				super.endElement(uri, localName, qName);
				
				String currValue = null;
				if(nodeValue != null){
					currValue = nodeValue.toString().trim();
				}
				
				if(codeBase){
					_tempParseMap.put("CODE_BASE", currValue);
					codeBase = false;
				}else if(classId){
					_tempParseMap.put("CLASS_ID", currValue);
					classId = false;
				}else if(pluginsPage){
					_tempParseMap.put("PLUGINS_PAGE", currValue);
					pluginsPage = false;
				}else if(jsfFlexMainSwcConfigFile){
					_tempParseMap.put("JSF_FLEX_MAIN_SWC_CONFIG_FILE", currValue);
					jsfFlexMainSwcConfigFile = false;
				}else if(swfHtmlAttribute){
					swfHtmlAttribute = false;
				}else if(sourceFile){
					if(swcSourceFiles){
						_swcSourceFiles.add(currValue);
					}else if(swfSourceFiles){
						_swfSourceFiles.add(currValue);
					}
					sourceFile = false;
				}else if(swcSourceFiles){
					swcSourceFiles = false;
				}else if(swfSourceFiles){
					swfSourceFiles = false;
				}else if(systemIndependent){
					systemIndependent = false;
				}
			}
			
			public void characters(char[] ch, int start, int length) throws SAXException {
				super.characters(ch, start, length);
				if(settings){
					return;
				}
				nodeValue.append(new String(ch, start, length));
			}
			
		});
		
	}
	
	public static List<String> getSwcSourceFiles(){
		/*
		 * To disallow removing elements within swcSourceFiles 
		 */
		return new LinkedList<String>(_swcSourceFiles);
	}
	
	public static void addSwcSourceFiles(String sourceToAdd){
		if(sourceToAdd != null){
			_swcSourceFiles.add(sourceToAdd);
		}
	}
	
	public static List<String> getSwfSourceFiles(){
		/*
		 * To disallow removing elements within swfSourceFiles 
		 */
		return new LinkedList<String>(_swfSourceFiles);
	}
	
	public static void addSwfSourceFiles(String sourceToAdd){
		if(sourceToAdd != null){
			_swfSourceFiles.add(sourceToAdd);
		}
	}
	
}
