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
package com.googlecode.jsfFlex.renderkit.annotationDocletParser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * A class that extends _AnnotationDocletParser for JRE less than 1.5. This class will get<br>
 * the fields to inspect by parsing an XML file.<br>
 * 
 * @author Ji Hoon Kim
 */
public final class AnnotationDocletParser14Impl extends _AnnotationDocletParser {
	
	private final static Log _log = LogFactory.getLog(AnnotationDocletParser14Impl.class);
	
	public AnnotationDocletParser14Impl(){
		super();
	}
	
	public void mapComponentFields(Class mapClass, final Object componentObj, 
									final String replaceMappingXML) {
		
		try{
			final SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			final ClassLoader loader = mapClass.getClassLoader();
			
			parser.parse(loader.getResourceAsStream(replaceMappingXML), new DefaultHandler() {
				
				private StringBuffer nodeValue;
				private String replaceToken;
				
				private boolean replaceTokenCheck;
				private boolean byMethod;
				
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					super.startElement(uri, localName, qName, attributes);
					if(qName.equals("replace-token")){
						replaceTokenCheck = true;
						byMethod = (attributes.getLength() == 0 || attributes.getValue(BY_ATTRIBUTE) == null);
					}
					nodeValue = new StringBuffer();
				}
				
				public void endElement(String uri, String localName, String qName) throws SAXException {
					super.endElement(uri, localName, qName);
					String currentValue = null;
					
					if(nodeValue != null){
						currentValue = nodeValue.toString().trim();
					}
					
					if(replaceTokenCheck){
						replaceToken = currentValue;
						
						if(byMethod){
							setMapper(MXML_METHOD_MAPPER);
						}else{
							setMapper(MXML_ATTRIBUTE_MAPPER);
						}
						
						try{
							TokenValue tokenValue = getMapper().mapField(replaceToken, componentObj);
							if(tokenValue != null){
								getTokenValueSet().add(tokenValue);
							}
						}catch(ComponentBuildException _componentBuildExcept){
							_log.debug("Exception thrown for [ Class : " + componentObj.getClass().getName() + ", replaceToken : " + replaceToken + " ] ");
						}
						
						replaceTokenCheck = false;
					}
					
				}
				
				public void characters(char[] ch, int start, int length) throws SAXException {
					super.characters(ch, start, length);
					if(!replaceTokenCheck){
						return;
					}
					nodeValue.append(new String(ch, start, length));
				}
				
			});
			
		}catch(SAXException saxExcept){
			Exception except = saxExcept.getException();
			if(except != null && except instanceof ComponentBuildException){
				throw (ComponentBuildException) except;
			}else{
				throw new ComponentBuildException(getErrorMessage("mapFields", replaceMappingXML), saxExcept);
			}
		}catch(ParserConfigurationException parserConfigExcept){
			throw new ComponentBuildException(getErrorMessage("mapFields", replaceMappingXML), parserConfigExcept);
		}catch(IOException ioExcept){
			throw new ComponentBuildException(getErrorMessage("mapFields", replaceMappingXML), ioExcept);
		}
		
	}
	
}
