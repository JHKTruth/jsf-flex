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
package com.googlecode.jsfFlex.shared.tasks;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.renderkit.annotationDocletParser._AnnotationDocletParser;

/**
 * Each implementation of _RunnerFactory should return a String specifying the package class<br>
 * for each of the following interfaces :<br>
 * <ul>
 *     <li> _CommonTaskRunner
 *     <li> _FileManipulatorTaskRunner
 *     <li> _FlexTaskRunner
 *     <li> _AnnotationDocletParser
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
public abstract class _RunnerFactory {
	
    private final static Log _log = LogFactory.getLog(_RunnerFactory.class);
    
	private static final _RunnerFactory INSTANCE;
	
	private static final String RUNNER_FACTORY_IMPL_PROPERTIES = "runnerFactoryImpl.properties";
	
	private static final String COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY = "common_task_rummer_impl_package";
	private static final String FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY = "file_manipulator_task_runner_impl_package";
	private static final String FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY = "flex_task_runner_impl_package";
	private static final String ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS_KEY = "annotation_doclet_parser_impl_package";
	
	private static final String COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS;
	private static final String FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS;
	private static final String FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS;
	private static final String ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS;
	
	private static Class COMMON_TASK_RUNNER_IMPL_CLASS;
	private static Class FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS;
	private static Class FLEX_TASK_RUNNER_IMPL_CLASS;
	private static final Class ANNOTATION_DOCLET_PARSER_IMPL_CLASS;
	
	static{
		
		Properties runnerFactoryImplProperties = new Properties();
		
		try{
			runnerFactoryImplProperties.load(_RunnerFactory.class.getResourceAsStream(RUNNER_FACTORY_IMPL_PROPERTIES));
		}catch(IOException ioExcept){
			throw new RuntimeException("Exception thrown when loading of " + RUNNER_FACTORY_IMPL_PROPERTIES, ioExcept);
		}
		
		String systemCommonTaskRunnerImplPackageClass = System.getProperty(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		String systemFileManipulatorTaskRunnerPackageClass = System.getProperty(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		String systemFlexTaskRunnerImplPackageClass = System.getProperty(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		
		String systemAnnotationDocletParserImplPackageClass = System.getProperty(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS_KEY);
		
		COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemCommonTaskRunnerImplPackageClass == null ? 
													runnerFactoryImplProperties.getProperty(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemCommonTaskRunnerImplPackageClass;
		FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemFileManipulatorTaskRunnerPackageClass == null ?
													runnerFactoryImplProperties.getProperty(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemFileManipulatorTaskRunnerPackageClass;
		FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemFlexTaskRunnerImplPackageClass == null ?
													runnerFactoryImplProperties.getProperty(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemFlexTaskRunnerImplPackageClass;
		ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS = systemAnnotationDocletParserImplPackageClass == null ?
													runnerFactoryImplProperties.getProperty(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS_KEY) : systemAnnotationDocletParserImplPackageClass;
		
		try{
			COMMON_TASK_RUNNER_IMPL_CLASS = Class.forName(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException classNotFound){
            _log.warn(classFindErrorMessage(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS));
            COMMON_TASK_RUNNER_IMPL_CLASS = null;
		}
		
		try{
			FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS = Class.forName(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException classNotFound){
            _log.warn(classFindErrorMessage(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS));
            FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS = null;
		}
		
		try{
			FLEX_TASK_RUNNER_IMPL_CLASS = Class.forName(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException classNotFound){
            _log.warn(classFindErrorMessage(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS));
            FLEX_TASK_RUNNER_IMPL_CLASS = null;
		}
		
		try{
			ANNOTATION_DOCLET_PARSER_IMPL_CLASS = Class.forName(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException classNotFound){
			throw new RuntimeException(errorMessage(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS, classNotFound), classNotFound);
		}
		
		INSTANCE = new _RunnerFactory(){
			
			String getAnnotationDocletParserImplPackage() {
				return ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS;
			}
			
			String getCommonTaskRunnerImplPackage() {
				return COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS;
			}
			
			String getFileManipulatorTaskRunnerImplPackage() {
				return FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS;
			}
			
			String getFlexTaskRunnerImplPackage() {
				return FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS;
			}
		};
		
	}
	
	private _RunnerFactory(){
		super();
	}
	
	public static final _RunnerFactory getInstance(){
		return INSTANCE;
	}
	
	public final _CommonTaskRunner getCommonTaskRunnerImpl(){
		
		_CommonTaskRunner specificInstance = null;
		
		try{
			specificInstance = (_CommonTaskRunner) COMMON_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException illegalAccess){
			throw new RuntimeException(errorMessage(getCommonTaskRunnerImplPackage(), illegalAccess), illegalAccess);
		}catch(InstantiationException instantiation){
			throw new RuntimeException(errorMessage(getCommonTaskRunnerImplPackage(), instantiation), instantiation);
		}
		
		return specificInstance;
	}
	
	public final _FileManipulatorTaskRunner getFileManipulatorTaskRunnerImpl(){
		
		_FileManipulatorTaskRunner specificInstance = null;
		
		try{
			specificInstance = (_FileManipulatorTaskRunner) FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException illegalAccess){
			throw new RuntimeException(errorMessage(getFileManipulatorTaskRunnerImplPackage(), illegalAccess), illegalAccess);
		}catch(InstantiationException instantiation){
			throw new RuntimeException(errorMessage(getFileManipulatorTaskRunnerImplPackage(), instantiation), instantiation);
		}
		
		return specificInstance;
	}
	
	public final _FlexTaskRunner getFlexTaskRunnerImpl(){
		
		_FlexTaskRunner specificInstance = null;
		
		try{
			specificInstance = (_FlexTaskRunner) FLEX_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException illegalAccess){
			throw new RuntimeException(errorMessage(getFlexTaskRunnerImplPackage(), illegalAccess), illegalAccess);
		}catch(InstantiationException instantiation){
			throw new RuntimeException(errorMessage(getFlexTaskRunnerImplPackage(), instantiation), instantiation);
		}
		
		return specificInstance;
	}
	
	public final _AnnotationDocletParser getAnnotationDocletParserImpl(){
		
		_AnnotationDocletParser specificInstance = null;
		
		try{
			specificInstance = (_AnnotationDocletParser) ANNOTATION_DOCLET_PARSER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException illegalAccess){
			throw new RuntimeException(errorMessage(getAnnotationDocletParserImplPackage(), illegalAccess), illegalAccess);
		}catch(InstantiationException instantiation){
			throw new RuntimeException(errorMessage(getAnnotationDocletParserImplPackage(), instantiation), instantiation);
		}
		
		return specificInstance;
	}
    
    private static String classFindErrorMessage(String packageClass){
        StringBuilder classFindErrorMessage = new StringBuilder();
        
        classFindErrorMessage.append("Following package class could not be found : " + packageClass);
        classFindErrorMessage.append(". If the application is not being ran in productionMode, please ensure to " +
                "include three jar files to the classpath that contain _CommonTaskRunner, _FileManipulatorTaskRunner, " + 
                "and _FlexTaskRunner implementations respectively.");
        
        return classFindErrorMessage.toString();
    }
	
	private static String errorMessage(String packageClass, Exception type){
        StringBuilder errorMessage = new StringBuilder();
		
		errorMessage.append("While creating an instance of " + packageClass);
		errorMessage.append(type.getClass().getName());
		errorMessage.append(" Exception was thrown.");
		
		return errorMessage.toString();
	}
	
	abstract String getCommonTaskRunnerImplPackage();
	
	abstract String getFileManipulatorTaskRunnerImplPackage();
	
	abstract String getAnnotationDocletParserImplPackage();
	
	abstract String getFlexTaskRunnerImplPackage();
	
}
