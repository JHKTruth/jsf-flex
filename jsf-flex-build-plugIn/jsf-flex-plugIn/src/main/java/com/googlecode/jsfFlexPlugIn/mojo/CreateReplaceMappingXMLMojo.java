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
package com.googlecode.jsfFlexPlugIn.mojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.velocity.util.StringUtils;

import com.googlecode.jsfFlexPlugIn.inspector._JsfFlexInspectListener;
import com.googlecode.jsfFlexPlugIn.inspector._JsfFlexInspectorBase;
import com.googlecode.jsfFlexPlugIn.inspector.qdox.JsfFlexQdoxInspector;
import com.googlecode.jsfFlexPlugIn.parser._JsfFlexParserListener;
import com.googlecode.jsfFlexPlugIn.parser.velocity.JsfFlexVelocityParser;
import com.googlecode.jsfFlexPlugIn.utils.tasks.ReplaceText;

/**
 * @goal    createMXMLComponentReplaceMappingXML
 * @phase   generate-resources
 * @author Ji Hoon Kim
 */
public final class CreateReplaceMappingXMLMojo extends AbstractMojo 
											   implements _JsfFlexInspectListener, _JsfFlexParserListener {
	
	private static final String JSF_FLEX_ATTRIBUTE = "JsfFlexAttributes";
	
	private static final String REPLACE_MAPPING_XML_ATTRIBUTE = "tokenList";
	private static final String REPLACE_MAPPING_XML_TEMPLATE = "jsf-flex-replaceMappingXML.vm";
	private static final String TO_CREATE_REPLACE_MAPPING_XML_DIRECTORY_NAME = "replaceMapping";
	private static final String TO_CREATE_REPLACE_MAPPING_XML_FILE_SUFFIX = "ReplaceMapping.xml";
	private static final String FILE_RESOURCE_LOADER_PATH_KEY = "file.resource.loader.path";
	
	private _JsfFlexInspectorBase _jsfFlexInspector;
	private JsfFlexVelocityParser _jsfFlexVelocityParser;
	
	public CreateReplaceMappingXMLMojo(){
		super();
	}
	
	/**
	 * @parameter expression="${project}"
	 */
	private MavenProject _project;
	
	/**
     * @parameter expression="target/classes"
     */
	private File _rootResourceDirectory;
	
	/**
     * @parameter expression="src/main/resources/META-INF"
     */
    private File _templateSourceDirectory;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		List compileSourceRoots = _project.getCompileSourceRoots();
		
		for(Iterator compileSourceRootsIterator = compileSourceRoots.iterator(); 
													compileSourceRootsIterator.hasNext();){
			String currDirPath = (String) compileSourceRootsIterator.next();
			_jsfFlexInspector = new JsfFlexQdoxInspector(currDirPath, JSF_FLEX_ATTRIBUTE);
			_jsfFlexInspector.addInspectListener(this);
			
			Properties velocityParserProperties = new Properties();
			velocityParserProperties.put(FILE_RESOURCE_LOADER_PATH_KEY, _templateSourceDirectory.getPath());
			
			_jsfFlexVelocityParser = new JsfFlexVelocityParser(velocityParserProperties);
			_jsfFlexVelocityParser.init();
			_jsfFlexVelocityParser.addParserListener(this);
			
			_jsfFlexInspector.inspectFiles();
			
		}
		
	}
	
	public final static class ReplaceMappingXMLVelocityObject{
		
		private final String _token;
		private final Boolean _byMethod;
		
		private ReplaceMappingXMLVelocityObject(){
			super();
			_token = null;
			_byMethod = null;
		}
		
		public ReplaceMappingXMLVelocityObject(String token, Boolean byMethod){
			super();
			_token = token;
			_byMethod = byMethod;
		}
		
		public String getToken(){
			return _token;
		}
		public Boolean getByMethod(){
			return _byMethod;
		}
		
	}
	
	public void inspectFileFinished(List<Map<String, ? extends Object>> inspected, String sourceInspected, String packageName){
		
		/*
		 * For this Mojo there will be a breakage in terms of concept for the Inspector's inspectFiles method
		 * Meaning under the design the implementation should be that each documentation of JsfFlexAttributes should contain
		 * a separate value for the keys "attribute" and "byMethod" within the _inspected Map.
		 * 
		 * However since this Mojo will be specifically used for JRE < 1.4, which is only for backward compatibility,
		 * and the size of the component files will grow tremendously under this method, documentation will be :
		 * @JsfFlexAttributes
		 *   attributeValue=byMethodValue
		 *   
		 * rather than the design that should be followed for all other implementation where the key value is unique
		 * [since the current implementation is per Class. if non-unique key entries are needed per Class, then
		 * simply change the implementation to invoke "inspectFileFinishedMap" per loop but realize the impact to
		 * this class]
		 * @JsfFlexAttributes
		 *   attribute=attributeValue
		 *   byMethod=byMethodValue
		 * 
		 */
		String path = StringUtils.getPackageAsPath(packageName);
		String replaceMappingXMLFileName = _rootResourceDirectory.getPath() + File.separatorChar + path + TO_CREATE_REPLACE_MAPPING_XML_DIRECTORY_NAME + 
												File.separatorChar + sourceInspected + TO_CREATE_REPLACE_MAPPING_XML_FILE_SUFFIX;
		String checkDirExists = _rootResourceDirectory.getPath() + File.separatorChar + path + TO_CREATE_REPLACE_MAPPING_XML_DIRECTORY_NAME + 
										File.separatorChar;
		try{
			
			File checkExists = new File(checkDirExists);
			if(!checkExists.exists()){
				checkExists.mkdirs();
			}
			
			FileWriter writer = new FileWriter(new File(replaceMappingXMLFileName));
			List<ReplaceMappingXMLVelocityObject> replaceMappingXMLVelocityObjects = generateReplaceMappingXMLVelocityObjects(inspected);
			Map<String, Object> contextInfoMap = new HashMap<String, Object>();
			contextInfoMap.put(REPLACE_MAPPING_XML_ATTRIBUTE, replaceMappingXMLVelocityObjects);
			_jsfFlexVelocityParser.mergeCollectionToTemplate(REPLACE_MAPPING_XML_TEMPLATE, contextInfoMap, 
																writer, replaceMappingXMLFileName);
			
		}catch(IOException _ioExcept){
			throw new RuntimeException("Error thrown for file " + replaceMappingXMLFileName, _ioExcept);
		}
		
	}
	
	public void inspectionCompleted(){
		
	}
	
	private List<ReplaceMappingXMLVelocityObject> generateReplaceMappingXMLVelocityObjects(List<Map<String, ? extends Object>> readNamedParameterList){
		List<ReplaceMappingXMLVelocityObject> replaceMappingXMLVelocityObjects = new LinkedList<ReplaceMappingXMLVelocityObject>();
		
		for(Map<String, ? extends Object> readNamedParameter : readNamedParameterList){
			
			for(String token : readNamedParameter.keySet()){
				Object byMethod = readNamedParameter.get(token);
				byMethod = byMethod == null ? "false" : byMethod;
				replaceMappingXMLVelocityObjects.add(new ReplaceMappingXMLVelocityObject(token, Boolean.valueOf((String) byMethod)));
			}
			
		}
		
		return replaceMappingXMLVelocityObjects;
	}
	
	public void mergeCollectionToTemplateFinished(String fileMerged){
		
		ReplaceText removeEmptySpace = new ReplaceText(fileMerged);
		removeEmptySpace.replaceRegExp(true);
		removeEmptySpace.regMatch(ReplaceText.CLEAN_REG_EXP_MATCH);
		removeEmptySpace.regReplace(ReplaceText.CLEAN_REG_EXP_REPLACE_WITH);
		
		removeEmptySpace.performTask();
		
	}
	
}
