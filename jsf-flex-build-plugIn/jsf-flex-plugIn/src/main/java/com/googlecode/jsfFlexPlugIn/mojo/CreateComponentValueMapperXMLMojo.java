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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.googlecode.jsfFlexPlugIn.inspector._JsfFlexInspectListener;
import com.googlecode.jsfFlexPlugIn.inspector._JsfFlexInspectorBase;
import com.googlecode.jsfFlexPlugIn.parser._JsfFlexParserListener;
import com.googlecode.jsfFlexPlugIn.parser.velocity.JsfFlexVelocityParser;
import com.googlecode.jsfFlexPlugIn.utils.tasks.ReplaceText;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.annotation.AnnotationValue;

/**
 * @goal    createComponentValueMapperXML
 * @phase   generate-resources
 * @author Ji Hoon Kim
 */
public final class CreateComponentValueMapperXMLMojo extends AbstractMojo 
											   implements _JsfFlexInspectListener, _JsfFlexParserListener {
	
	private static final String JSF_FLEX_PROJECT = "jsf-flex";
	private static final String JSF_FLEX_SHARED_PROJECT = "jsf-flex-shared";
	
	private static final String CORE_PROJECT_NAME = "core";
	private static final String RENDERKIT_15_PROJECT_NAME = "renderKit15";
	
	private static final String JSF_FLEX_CLASS_SET_ATTRIBUTE = "classSet";
	private static final String JSF_FLEX_COMPONENT_VALUE_MAPPER_TEMPLATE = "jsf-flex-componentValueMapperXML.vm";
	private static final String TO_CREATE_COMPONENT_VALUE_MAPPER_XML_FILE_NAME = "componentValueMapper.xml";
	private static final String FILE_RESOURCE_LOADER_PATH_KEY = "file.resource.loader.path";
	
	private static final String MXML_COMPONENT_PACKAGE_KEY = "mxmlComponentPackage";
	private static final String MXML_COMPONENT_NAME_KEY = "mxmlComponentName";
	
	private static final String HTML_TYPE_KEY = "htmlType";
	private static final String TYPE_ATTRIBUTE_VALUE_KEY = "typeAttributeValue";
	
	private static final String VALUE_ATTRIBUTE_VALUE_KEY = "valueAttributeValue";
	private static final String VALUE_DYNAMIC_KEY = "valueDynamic";
	private static final String VALUE_NESTED_KEY = "valueNested";
	private static final String VALUE_NESTED_VALUES_KEY = "valueNestedValues";
	
	private static final String NAME_ATTRIBUTE_VALUE_KEY = "nameAttributeValue";
	private static final String NAME_DYNAMIC_KEY = "nameDynamic";
	private static final String NAME_APPEND_KEY = "nameAppend";
	
	private _JsfFlexInspectorBase _jsfFlexInspector;
	private JsfFlexVelocityParser _jsfFlexVelocityParser;
	
	/**
	 * @parameter expression="${project}"
	 */
	private MavenProject _project;
	
	/**
     * @parameter expression="${basedir}/target/classes/com/googlecode/jsfFlex/shared/swfSourceFiles"
     */
	private File _toCreateComponentValueMapperXMLPath;
	
	/**
     * @parameter expression="src/main/resources/META-INF"
     */
    private File _templateSourceDirectory;
    
    private final Set<ClassInfo> _classInfoSet;
	
    public CreateComponentValueMapperXMLMojo(){
    	super();
    	_classInfoSet = new HashSet<ClassInfo>();
    }
    
    public static final class ClassInfo{
    	
    	private final String _fullClassName;
    	private final List<NodeInfo> _nodeList;
    	
    	private ClassInfo(){
    		super();
    		_fullClassName = null;
    		_nodeList = null;
    	}
    	
    	public ClassInfo(String fullClassName){
    		super();
    		_fullClassName = fullClassName;
    		_nodeList = new LinkedList<NodeInfo>();
    	}
    	
    	public void addNodeInfo(NodeInfo _nodeInfo){
    		_nodeList.add(_nodeInfo);
    	}
    	
		public String getFullClassName() {
			return _fullClassName;
		}
		public List<NodeInfo> getNodeList() {
			return _nodeList;
		}
		
		@Override
		public boolean equals(Object instance) {
			if(!(instance instanceof ClassInfo)){
				return false;
			}
			
			ClassInfo classInfoInstance = (ClassInfo) instance;
			return _fullClassName.equals(classInfoInstance._fullClassName);
		}
		
		@Override
		public int hashCode() {
			return _fullClassName.hashCode();
		}
    	
    }
    
    public static final class NodeInfo{
    	
    	private final String _htmlType;
    	private final String _typeAttributeValue;
    	
    	private final Boolean _valueNested;
    	private final Boolean _valueDynamic;
    	private final String _valueAttributeValue;
    	private final List<String> _nestedList;
    	
    	private final Boolean _nameDynamic;
    	private final String _nameAppend;
    	private final String _nameAttributeValue;
    	
    	private NodeInfo(){
    		super();
    		_htmlType = null;
    		_typeAttributeValue = null;
    		
    		_valueNested = Boolean.FALSE;
    		_valueDynamic = Boolean.FALSE;
    		_valueAttributeValue = null;
    		_nestedList = null;
    		
    		_nameDynamic = Boolean.FALSE;
    		_nameAppend = null;
    		_nameAttributeValue = null;
    	}
    	
    	public NodeInfo(String htmlType, String typeAttributeValue, Boolean valueNested, Boolean valueDynamic,
    						String valueAttributeValue, List<String> nestedList, Boolean nameDynamic, 
    						String nameAppend, String nameAttributeValue){
    		super();
    		_htmlType = htmlType;
    		_typeAttributeValue = typeAttributeValue;
    		
    		_valueNested = valueNested;
    		_valueDynamic = valueDynamic;
    		_valueAttributeValue = valueAttributeValue;
    		_nestedList = nestedList;
    		
    		_nameDynamic = nameDynamic;
    		_nameAppend = nameAppend;
    		_nameAttributeValue = nameAttributeValue;
    	}

		public String getHtmlType() {
			return _htmlType;
		}
		public String getNameAppend() {
			return _nameAppend;
		}
		public String getNameAttributeValue() {
			return _nameAttributeValue;
		}
		public Boolean isNameDynamic() {
			return _nameDynamic;
		}
		public List<String> getNestedList() {
			/*
			 * since it's final it should be returned as a defensive copy,
			 * but it's a plug-in so return it
			 */
			return _nestedList;
		}
		public String getTypeAttributeValue() {
			return _typeAttributeValue;
		}
		public String getValueAttributeValue() {
			return _valueAttributeValue;
		}
		public Boolean isValueDynamic() {
			return _valueDynamic;
		}
		public Boolean isValueNested() {
			return _valueNested;
		}
    	
    }
    
    public void execute() throws MojoExecutionException, MojoFailureException {
		
        String currDirPath = (String) _project.getCompileSourceRoots().get(0);
		currDirPath = currDirPath.replace(JSF_FLEX_SHARED_PROJECT, JSF_FLEX_PROJECT);
		currDirPath = currDirPath.replace(CORE_PROJECT_NAME, RENDERKIT_15_PROJECT_NAME);
		
		Properties velocityParserProperties = new Properties();
		velocityParserProperties.put(FILE_RESOURCE_LOADER_PATH_KEY, _templateSourceDirectory.getPath());
		
		_jsfFlexVelocityParser = new JsfFlexVelocityParser(velocityParserProperties);
		_jsfFlexVelocityParser.init();
		_jsfFlexVelocityParser.addParserListener(this);
		
		_jsfFlexInspector = new _JsfFlexInspectorBase(currDirPath){
            
            private static final String JSF_FLEX_ATTRIBUTE_PROPERTIES_ANNOTATION_NAME = "com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties";
            private static final String MXML_COMPONENT_NODE_ATTRIBUTES_KEY = "mxmlComponentNodeAttributes";
            
            private static final String IS_VALUE_DYNAMIC_ATTRIBUTE_KEY = "isValueDynamic";
            private static final String IS_VALUE_NESTED_ATTRIBUTE_KEY = "isValueNested";
            private static final String IS_NAME_DYNAMIC_ATTRIBUTE_KEY = "isNameDynamic";
            
			public void inspectFiles(){
				/*
				 * In order to keep in synch with the QDox parsing, following is the 
				 * format to be inserted into the LinkedList<Map<String, ? extends Object>> :
				 * 		(1) Map containing 	CLASS_PACKAGE_KEY
				 * 							CLASS_NAME_KEY
				 * 
				 * 		(2) Map containing 	HTML_TYPE_KEY
				 * 							TYPE_ATTRIBUTE_VALUE_KEY
				 * 							
				 * 							VALUE_ATTRIBUTE_VALUE_KEY
				 * 							VALUE_DYNAMIC_KEY
				 * 							VALUE_NESTED_KEY
				 * 							VALUE_NESTED_VALUES_KEY
				 * 							
				 * 							NAME_ATTRIBUTE_VALUE_KEY
				 * 							NAME_DYNAMIC_KEY
				 * 							NAME_APPEND_KEY
				 * 							
				 */
				JavaDocBuilder builder = new JavaDocBuilder();
				builder.addSourceTree(new File(getDirPath()));
				JavaClass[] inspectableFiles = builder.getClasses();
                
				for(JavaClass currClass : inspectableFiles){
					
                    List<Map<String, ? extends Object>> inspectedList = new LinkedList<Map<String, ? extends Object>>();
					Map<String, String> inspectedMap = new LinkedHashMap<String, String>();
					
                    Annotation[] qdoxAnnotations = currClass.getAnnotations();
                    Annotation jsfFlexAttributeListAnnotation = null;
                    
                    if(qdoxAnnotations == null){
                        continue;
                    }
                    
                    for(Annotation currAnnotation : qdoxAnnotations){
                        if(currAnnotation.getType().getValue().equals(JSF_FLEX_ATTRIBUTE_PROPERTIES_ANNOTATION_NAME)){
                            jsfFlexAttributeListAnnotation = currAnnotation;
                            break;
                        }
                    }
                    
                    if(jsfFlexAttributeListAnnotation == null){
                        continue;
                    }
                    
                    AnnotationValue mxmlComponentPackage = jsfFlexAttributeListAnnotation.getProperty(MXML_COMPONENT_PACKAGE_KEY);
                    AnnotationValue mxmlComponentName = jsfFlexAttributeListAnnotation.getProperty(MXML_COMPONENT_NAME_KEY);
                    
                    if(mxmlComponentPackage == null || mxmlComponentName == null){
                        continue;
                    }
                    
                    inspectedMap.put(MXML_COMPONENT_PACKAGE_KEY, removeQuotes( mxmlComponentPackage.getParameterValue().toString() ));
					inspectedMap.put(MXML_COMPONENT_NAME_KEY, removeQuotes( mxmlComponentName.getParameterValue().toString() ));
					
					inspectedList.add(inspectedMap);
					//have added Map info containing CLASS_* info
					AnnotationValue mxmlComponentNodeAttributes = jsfFlexAttributeListAnnotation.getProperty(MXML_COMPONENT_NODE_ATTRIBUTES_KEY);
                    
                    @SuppressWarnings("unchecked")
                    List<Annotation> flexComponentNodeAttributes = (List<Annotation>) mxmlComponentNodeAttributes.getParameterValue();
                    
                    for(Annotation currFlexComponentNodeAttribute : flexComponentNodeAttributes){
                        inspectedMap = new LinkedHashMap<String, String>();
                        
                        inspectedMap.put(HTML_TYPE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(HTML_TYPE_KEY).getParameterValue().toString() ));
                        inspectedMap.put(TYPE_ATTRIBUTE_VALUE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(TYPE_ATTRIBUTE_VALUE_KEY).getParameterValue().toString() ));
                        
                        AnnotationValue valueAttributeValue = currFlexComponentNodeAttribute.getProperty(VALUE_ATTRIBUTE_VALUE_KEY);
                        if(valueAttributeValue != null){
                            inspectedMap.put(VALUE_ATTRIBUTE_VALUE_KEY, removeQuotes( valueAttributeValue.getParameterValue().toString() ));
                        }
                        
                        inspectedMap.put(VALUE_DYNAMIC_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_VALUE_DYNAMIC_ATTRIBUTE_KEY).getParameterValue().toString() ));
                        inspectedMap.put(VALUE_NESTED_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_VALUE_NESTED_ATTRIBUTE_KEY).getParameterValue().toString() ));
                        
                        String builtString;
                        boolean isValueNested = Boolean.valueOf(removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_VALUE_NESTED_ATTRIBUTE_KEY).getParameterValue().toString() ));
                        if(isValueNested){
                            StringBuilder toBuildString = new StringBuilder();
                            
                            @SuppressWarnings("unchecked")
                            List<String> valueNestedValues = (List<String>) currFlexComponentNodeAttribute.getProperty(VALUE_NESTED_VALUES_KEY).getParameterValue();
                            for(String buildInto : valueNestedValues){
                                toBuildString.append(removeQuotes( buildInto ));
                                toBuildString.append("_");
                            }
                            
                            toBuildString.deleteCharAt(toBuildString.length()-1);
                            builtString = toBuildString.toString();
                        }else{
                            builtString = "";
                        }
                        
                        inspectedMap.put(VALUE_NESTED_VALUES_KEY, builtString);
                        
                        inspectedMap.put(NAME_ATTRIBUTE_VALUE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(NAME_ATTRIBUTE_VALUE_KEY).getParameterValue().toString() ));
                        inspectedMap.put(NAME_DYNAMIC_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_NAME_DYNAMIC_ATTRIBUTE_KEY).getParameterValue().toString() ));
                        inspectedMap.put(NAME_APPEND_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(NAME_APPEND_KEY).getParameterValue().toString() ));
                        
                        inspectedList.add(inspectedMap);
                        
                    }
                    
                    inspectFileFinished(inspectedList, currClass.getName(), currClass.getPackage().toString());
				}
				
				inspectionCompleted();
			}
		};
        
		_jsfFlexInspector.addInspectListener(this);
		
		_jsfFlexInspector.inspectFiles();
		
	}
	
	public void inspectFileFinished(List<Map<String, ? extends Object>> inspectedList, String sourceInspected, String packageName) {
		
		ClassInfo currClassInfo = null;
		
		for(Map<String, ? extends Object> inspected : inspectedList){
		
			if(inspected != null && inspected.size() > 0){
				
				if(currClassInfo == null){
					String classPackage = (String) inspected.get(MXML_COMPONENT_PACKAGE_KEY);
					String className = (String) inspected.get(MXML_COMPONENT_NAME_KEY);
					
					String fullClassName = classPackage + "::" + className;
					
					currClassInfo  = new ClassInfo(fullClassName);
					_classInfoSet.add(currClassInfo);
					continue;
				}
				//created the ClassInfo instance and now created the NodeInfo to add
				
				Object htmlType = inspected.get(HTML_TYPE_KEY);
				Object typeAttributeValue = inspected.get(TYPE_ATTRIBUTE_VALUE_KEY);
				
				Object valueAttributeValue = inspected.get(VALUE_ATTRIBUTE_VALUE_KEY);
				Boolean isValueDynamic = inspected.get(VALUE_DYNAMIC_KEY) != null && inspected.get(VALUE_DYNAMIC_KEY).equals("true");
				Boolean isValueNested = inspected.get(VALUE_NESTED_KEY) != null && inspected.get(VALUE_NESTED_KEY).equals("true");
				Object valueNestedValues = inspected.get(VALUE_NESTED_VALUES_KEY);
				List<String> valueNestedList;
				if(valueNestedValues != null){
					valueNestedList = Arrays.asList( ((String) valueNestedValues).split("_") );
				}else{
					valueNestedList = new LinkedList<String>();
				}
				
				Object nameAttributeValue = inspected.get(NAME_ATTRIBUTE_VALUE_KEY);
				Boolean isNameDynamic = inspected.get(NAME_DYNAMIC_KEY) != null && inspected.get(NAME_DYNAMIC_KEY).equals("true");
				Object nameAppend = inspected.get(NAME_APPEND_KEY);
				
				currClassInfo.addNodeInfo(new NodeInfo(returnEmptyStringForNull(htmlType), returnEmptyStringForNull(typeAttributeValue), isValueNested, 
															isValueDynamic, returnEmptyStringForNull(valueAttributeValue), valueNestedList, isNameDynamic, 
															returnEmptyStringForNull(nameAppend), returnEmptyStringForNull(nameAttributeValue)));
				
			}
		
		}
		
	}
    
    private String removeQuotes(String content){
        return content == null ? "" : content.replaceAll("\"", "");
    }
	
	private String returnEmptyStringForNull(Object checkForNull){
		return checkForNull == null ? "" : checkForNull.toString();
	}
	
	public void inspectionCompleted() {
		
		String toCreateComponentValueMapperXMLPath = _toCreateComponentValueMapperXMLPath.getPath();
		
		try{
			File toCreateComponentValueMapperXMLFilePath = new File(toCreateComponentValueMapperXMLPath);
			if(!toCreateComponentValueMapperXMLFilePath.exists()){
				toCreateComponentValueMapperXMLFilePath.mkdirs();
			}
			toCreateComponentValueMapperXMLPath +=  File.separatorChar + TO_CREATE_COMPONENT_VALUE_MAPPER_XML_FILE_NAME; 
			FileWriter writer = new FileWriter(new File(toCreateComponentValueMapperXMLPath));
			Map<String, Object> contextInfoMap = new HashMap<String, Object>();
			
			contextInfoMap.put(JSF_FLEX_CLASS_SET_ATTRIBUTE, _classInfoSet);
			_jsfFlexVelocityParser.mergeCollectionToTemplate(JSF_FLEX_COMPONENT_VALUE_MAPPER_TEMPLATE, contextInfoMap, 
																writer, toCreateComponentValueMapperXMLPath);
			
		}catch(IOException ioException){
			
		}
		
	}
	
	public void mergeCollectionToTemplateFinished(String fileMerged) {
		
		ReplaceText removeEmptySpace = new ReplaceText(fileMerged);
		removeEmptySpace.replaceRegExp(true);
		removeEmptySpace.regMatch(ReplaceText.CLEAN_REG_EXP_MATCH);
		removeEmptySpace.regReplace(ReplaceText.CLEAN_REG_EXP_REPLACE_WITH);
		
		removeEmptySpace.performTask();
		
	}
	
}
