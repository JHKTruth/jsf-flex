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

import com.googlecode.jsfFlexPlugIn.inspector.IJsfFlexInspectListener;
import com.googlecode.jsfFlexPlugIn.inspector.AbstractJsfFlexInspectorBase;
import com.googlecode.jsfFlexPlugIn.parser.IJsfFlexParserListener;
import com.googlecode.jsfFlexPlugIn.parser.velocity.JsfFlexVelocityParser;
import com.googlecode.jsfFlexPlugIn.utils.tasks.ReplaceText;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.annotation.AnnotationValue;

import static com.googlecode.jsfFlexPlugIn.utils.JsfFlexBuildPluginConstant.*;
import static com.googlecode.jsfFlexPlugIn.utils.JsfFlexBuildPluginUtil.*;

/**
 * @goal    createComponentValueMapperXML
 * @phase   generate-resources
 * @author Ji Hoon Kim
 */
public final class CreateComponentValueMapperXMLMojo extends AbstractMojo 
											   implements IJsfFlexInspectListener, IJsfFlexParserListener {
	
	private static final String COMPONENT_PACKAGES_KEY = "componentPackages";
	private static final String COMPONENT_NAME_KEY = "componentName";
	
	private static final String HTML_TYPE_KEY = "htmlType";
	private static final String TYPE_ATTRIBUTE_VALUE_KEY = "typeAttributeValue";
	
	private static final String VALUE_ATTRIBUTE_VALUE_KEY = "valueAttributeValue";
	private static final String VALUE_DYNAMIC_KEY = "valueDynamic";
	private static final String VALUE_NESTED_KEY = "valueNested";
    private static final String VALUE_RECURSE_KEY = "recurse";
	private static final String VALUE_NESTED_VALUES_KEY = "valueNestedValues";
	
	private static final String NAME_ATTRIBUTE_VALUE_KEY = "nameAttributeValue";
	private static final String NAME_DYNAMIC_KEY = "nameDynamic";
	private static final String NAME_APPEND_KEY = "nameAppend";
	
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
    
    private AbstractJsfFlexInspectorBase _jsfFlexInspector;
    private JsfFlexVelocityParser _jsfFlexVelocityParser;
    
    public CreateComponentValueMapperXMLMojo() {
    	super();
    	_classInfoSet = new HashSet<ClassInfo>();
    }
    
    
    public void execute() throws MojoExecutionException, MojoFailureException {
		
        final String JSF_FLEX_PROJECT = "jsf-flex";
        final String JSF_FLEX_SHARED_PROJECT = "jsf-flex-shared";
        
        final String CORE_PROJECT_NAME = "core";
        final String RENDERKIT_15_PROJECT_NAME = "renderKit15";
        
        String currDirPath = String.class.cast( _project.getCompileSourceRoots().get(0) );
		currDirPath = currDirPath.replace(JSF_FLEX_SHARED_PROJECT, JSF_FLEX_PROJECT);
		currDirPath = currDirPath.replace(CORE_PROJECT_NAME, RENDERKIT_15_PROJECT_NAME);
		
		Properties velocityParserProperties = new Properties();
		velocityParserProperties.put(FILE_RESOURCE_LOADER_PATH_KEY, _templateSourceDirectory.getPath());
		
		_jsfFlexVelocityParser = new JsfFlexVelocityParser(velocityParserProperties);
		_jsfFlexVelocityParser.init();
		_jsfFlexVelocityParser.addParserListener(this);
		
		_jsfFlexInspector = new AbstractJsfFlexInspectorBase(currDirPath){
            
            private static final String JSF_FLEX_ATTRIBUTE_PROPERTIES_ANNOTATION_NAME = "com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties";
            private static final String COMPONENT_NODE_ATTRIBUTES_KEY = "componentNodeAttributes";
            
            private static final String IS_VALUE_DYNAMIC_ATTRIBUTE_KEY = "isValueDynamic";
            private static final String IS_VALUE_NESTED_ATTRIBUTE_KEY = "isValueNested";
            private static final String IS_VALUE_RECURSE_ATTRIBUTE_KEY = "isValueRecurse";
            private static final String IS_NAME_DYNAMIC_ATTRIBUTE_KEY = "isNameDynamic";
            
			public void inspectFiles(){
				/*
				 * In order to keep in synch with the QDox parsing, following is the 
				 * format to be inserted into the LinkedList<Map<String, ? extends Object>> :
				 * 		(1) Map containing 	COMPONENT_PACKAGE_KEY
				 * 							COMPONENT_NAME_KEY
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
					Annotation[] qdoxAnnotations = currClass.getAnnotations();
                    
                    if(qdoxAnnotations == null){
                        continue;
                    }
                    
                    for(Annotation currAnnotation : qdoxAnnotations){
                        if(currAnnotation.getType().getValue().equals(JSF_FLEX_ATTRIBUTE_PROPERTIES_ANNOTATION_NAME)){
                            List<Map<String, ? extends Object>> inspectedList = new LinkedList<Map<String, ? extends Object>>();
                            Map<String, Object> inspectedMap = new LinkedHashMap<String, Object>();
                            
                            Annotation jsfFlexAttributeListAnnotation = currAnnotation;
                            AnnotationValue componentPackage = jsfFlexAttributeListAnnotation.getProperty(COMPONENT_PACKAGES_KEY);
                            AnnotationValue componentName = jsfFlexAttributeListAnnotation.getProperty(COMPONENT_NAME_KEY);
                            
                            if(componentPackage == null || componentName == null){
                                continue;
                            }
                            
                            inspectedMap.put(COMPONENT_PACKAGES_KEY, componentPackage.getParameterValue() );
                            inspectedMap.put(COMPONENT_NAME_KEY, removeQuotes( componentName.getParameterValue().toString() ));
                            
                            inspectedList.add(inspectedMap);
                            //have added Map info containing CLASS_* info
                            AnnotationValue componentNodeAttributes = jsfFlexAttributeListAnnotation.getProperty(COMPONENT_NODE_ATTRIBUTES_KEY);
                            
                            @SuppressWarnings("unchecked")
                            List<Annotation> flexComponentNodeAttributes = (List<Annotation>) componentNodeAttributes.getParameterValue();
                            
                            for(Annotation currFlexComponentNodeAttribute : flexComponentNodeAttributes){
                                Map<String, String> componentNodeAttributeMap = new LinkedHashMap<String, String>();
                                
                                componentNodeAttributeMap.put(HTML_TYPE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(HTML_TYPE_KEY).getParameterValue().toString() ));
                                componentNodeAttributeMap.put(TYPE_ATTRIBUTE_VALUE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(TYPE_ATTRIBUTE_VALUE_KEY).getParameterValue().toString() ));
                                
                                AnnotationValue valueAttributeValue = currFlexComponentNodeAttribute.getProperty(VALUE_ATTRIBUTE_VALUE_KEY);
                                if(valueAttributeValue != null){
                                    componentNodeAttributeMap.put(VALUE_ATTRIBUTE_VALUE_KEY, removeQuotes( valueAttributeValue.getParameterValue().toString() ));
                                }
                                
                                componentNodeAttributeMap.put(VALUE_DYNAMIC_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_VALUE_DYNAMIC_ATTRIBUTE_KEY).getParameterValue().toString() ));
                                componentNodeAttributeMap.put(VALUE_NESTED_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_VALUE_NESTED_ATTRIBUTE_KEY).getParameterValue().toString() ));
                                
                                AnnotationValue isRecurse = currFlexComponentNodeAttribute.getProperty(IS_VALUE_RECURSE_ATTRIBUTE_KEY);
                                String recurseValue = "false";
                                if(isRecurse != null){
                                    recurseValue = removeQuotes( isRecurse.getParameterValue().toString() );
                                }
                                componentNodeAttributeMap.put(VALUE_RECURSE_KEY, recurseValue);
                                
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
                                
                                componentNodeAttributeMap.put(VALUE_NESTED_VALUES_KEY, builtString);
                                
                                componentNodeAttributeMap.put(NAME_ATTRIBUTE_VALUE_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(NAME_ATTRIBUTE_VALUE_KEY).getParameterValue().toString() ));
                                componentNodeAttributeMap.put(NAME_DYNAMIC_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(IS_NAME_DYNAMIC_ATTRIBUTE_KEY).getParameterValue().toString() ));
                                componentNodeAttributeMap.put(NAME_APPEND_KEY, removeQuotes( currFlexComponentNodeAttribute.getProperty(NAME_APPEND_KEY).getParameterValue().toString() ));
                                
                                inspectedList.add(componentNodeAttributeMap);
                                
                            }
                            
                            inspectFileFinished(inspectedList, currClass.getName(), currClass.getPackage().toString());
                        }
                    }
                    
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
                    @SuppressWarnings("unchecked")
					List<String> classPackage = new LinkedList<String>(List.class.cast( inspected.get(COMPONENT_PACKAGES_KEY) ));
					String className = String.class.cast( inspected.get(COMPONENT_NAME_KEY) );
					
					List<String> fullClassNames = new LinkedList<String>();
                    for(String currPackage : classPackage){
                        fullClassNames.add(removeQuotes(currPackage) + "::" + className);
                    }
					currClassInfo  = new ClassInfo(fullClassNames);
                    
					_classInfoSet.add(currClassInfo);
					continue;
				}
				//created the ClassInfo instance and now created the NodeInfo to add
				
				Object htmlType = inspected.get(HTML_TYPE_KEY);
				Object typeAttributeValue = inspected.get(TYPE_ATTRIBUTE_VALUE_KEY);
				
				Object valueAttributeValue = inspected.get(VALUE_ATTRIBUTE_VALUE_KEY);
				Boolean isValueDynamic = inspected.get(VALUE_DYNAMIC_KEY) != null && inspected.get(VALUE_DYNAMIC_KEY).equals("true");
				Boolean isValueNested = inspected.get(VALUE_NESTED_KEY) != null && inspected.get(VALUE_NESTED_KEY).equals("true");
                Boolean isValueRecurse = inspected.get(VALUE_RECURSE_KEY) != null && inspected.get(VALUE_RECURSE_KEY).equals("true");
				Object valueNestedValues = inspected.get(VALUE_NESTED_VALUES_KEY);
				List<String> valueNestedList;
				if(valueNestedValues != null){
					valueNestedList = Arrays.asList( (String.class.cast( valueNestedValues )).split("_") );
				}else{
					valueNestedList = new LinkedList<String>();
				}
				
				Object nameAttributeValue = inspected.get(NAME_ATTRIBUTE_VALUE_KEY);
				Boolean isNameDynamic = inspected.get(NAME_DYNAMIC_KEY) != null && inspected.get(NAME_DYNAMIC_KEY).equals("true");
				Object nameAppend = inspected.get(NAME_APPEND_KEY);
				
				currClassInfo.addNodeInfo(new NodeInfo(returnEmptyStringForNull(htmlType), returnEmptyStringForNull(typeAttributeValue), isValueNested, 
															isValueDynamic, isValueRecurse, returnEmptyStringForNull(valueAttributeValue), valueNestedList, isNameDynamic, 
															returnEmptyStringForNull(nameAppend), returnEmptyStringForNull(nameAttributeValue)));
				
			}
		
		}
		
	}
    
    public void inspectionCompleted() {
		
        final String JSF_FLEX_CLASS_SET_ATTRIBUTE = "classSet";
        final String JSF_FLEX_COMPONENT_VALUE_MAPPER_TEMPLATE = "jsf-flex-componentValueMapperXML.vm";
        final String TO_CREATE_COMPONENT_VALUE_MAPPER_XML_FILE_NAME = "componentValueMapper.xml";
        
		String toCreateComponentValueMapperXMLPath = _toCreateComponentValueMapperXMLPath.getPath();
		
		try{
            checkForDirectoryExistenceOrCreate(toCreateComponentValueMapperXMLPath);
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
    
    public static final class ClassInfo {
        
        private final List<String> _fullClassNames;
        private final List<NodeInfo> _nodeList;
        
        private ClassInfo(){
            super();
            _fullClassNames = null;
            _nodeList = null;
        }
        
        private ClassInfo(List<String> fullClassNames) {
            super();
            _fullClassNames = fullClassNames;
            _nodeList = new LinkedList<NodeInfo>();
        }
        
        public void addNodeInfo(NodeInfo _nodeInfo){
            _nodeList.add(_nodeInfo);
        }
        
        public List<String> getFullClassNames() {
            return _fullClassNames;
        }
        public List<NodeInfo> getNodeList() {
            return _nodeList;
        }
        
        @Override
        public boolean equals(Object instance) {
            if(!(instance instanceof ClassInfo)){
                return false;
            }
            
            ClassInfo classInfoInstance = ClassInfo.class.cast( instance );
            return _fullClassNames.equals(classInfoInstance._fullClassNames);
        }
        
        @Override
        public int hashCode() {
            return _fullClassNames.hashCode();
        }
        
    }
    
    public static final class NodeInfo {
        
        private final String _htmlType;
        private final String _typeAttributeValue;
        
        private final Boolean _valueNested;
        private final Boolean _valueDynamic;
        private final Boolean _valueRecurse;
        private final String _valueAttributeValue;
        private final List<String> _nestedList;
        
        private final Boolean _nameDynamic;
        private final String _nameAppend;
        private final String _nameAttributeValue;
        
        private final int HASH_CODE;
        
        private NodeInfo() {
            super();
            _htmlType = null;
            _typeAttributeValue = null;
            
            _valueNested = Boolean.FALSE;
            _valueDynamic = Boolean.FALSE;
            _valueRecurse = Boolean.FALSE;
            _valueAttributeValue = null;
            _nestedList = null;
            
            _nameDynamic = Boolean.FALSE;
            _nameAppend = null;
            _nameAttributeValue = null;
            
            HASH_CODE = -1;
        }
        
        private NodeInfo(String htmlType, String typeAttributeValue, Boolean valueNested, Boolean valueDynamic,
                            Boolean valueRecurse, String valueAttributeValue, List<String> nestedList, Boolean nameDynamic, 
                            String nameAppend, String nameAttributeValue) {
            super();
            _htmlType = htmlType;
            _typeAttributeValue = typeAttributeValue;
            
            _valueNested = valueNested;
            _valueDynamic = valueDynamic;
            _valueRecurse = valueRecurse;
            _valueAttributeValue = valueAttributeValue;
            _nestedList = nestedList;
            
            _nameDynamic = nameDynamic;
            _nameAppend = nameAppend;
            _nameAttributeValue = nameAttributeValue;
            
            int hashCodeVal = HASH_CODE_INIT_VALUE;
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _htmlType.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _typeAttributeValue.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _valueNested.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _valueDynamic.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _valueRecurse.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _valueAttributeValue.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _nestedList.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _nameDynamic.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _nameAppend.hashCode();
            hashCodeVal = HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _nameAttributeValue.hashCode();
            HASH_CODE = hashCodeVal;
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
        public Boolean isValueRecurse() {
            return _valueRecurse;
        }
        
        @Override
        public boolean equals(Object instance) {
            if(!(instance instanceof NodeInfo)){
                return false;
            }
            
            NodeInfo nodeInfoInstance = NodeInfo.class.cast( instance );
            return _htmlType.equals(nodeInfoInstance._htmlType) && _typeAttributeValue.equals(nodeInfoInstance._typeAttributeValue) &&
                    _valueNested.equals(nodeInfoInstance._valueNested) && _valueDynamic.equals(nodeInfoInstance._valueDynamic) && 
                    _valueRecurse.equals(nodeInfoInstance._valueRecurse) && _valueAttributeValue.equals(nodeInfoInstance._valueAttributeValue) && 
                    _nestedList.equals(nodeInfoInstance._nestedList) && _nameDynamic.equals(nodeInfoInstance._nameDynamic) && 
                    _nameAppend.equals(nodeInfoInstance._nameAppend) && _nameAttributeValue.equals(nodeInfoInstance._nameAttributeValue);
        }
        
        @Override
        public int hashCode() {
            return HASH_CODE;
        }
        
    }
    
}
