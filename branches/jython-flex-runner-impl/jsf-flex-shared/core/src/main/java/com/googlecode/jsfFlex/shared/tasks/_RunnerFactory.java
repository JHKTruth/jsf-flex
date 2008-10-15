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
	
	private static final Class COMMON_TASK_RUNNER_IMPL_CLASS;
	private static final Class FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS;
	private static final Class FLEX_TASK_RUNNER_IMPL_CLASS;
	private static final Class ANNOTATION_DOCLET_PARSER_IMPL_CLASS;
	
	static{
		
		Properties _runnerFactoryImplProperties = new Properties();
		
		try{
			_runnerFactoryImplProperties.load(_RunnerFactory.class.getResourceAsStream(RUNNER_FACTORY_IMPL_PROPERTIES));
		}catch(IOException _ioExcept){
			throw new RuntimeException("Exception thrown when loading of " + RUNNER_FACTORY_IMPL_PROPERTIES, _ioExcept);
		}
		
		String systemCommonTaskRunnerImplPackageClass = System.getProperty(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		String systemFileManipulatorTaskRunnerPackageClass = System.getProperty(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		String systemFlexTaskRunnerImplPackageClass = System.getProperty(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY);
		
		String systemAnnotationDocletParserImplPackageClass = System.getProperty(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS_KEY);
		
		COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemCommonTaskRunnerImplPackageClass == null ? 
													_runnerFactoryImplProperties.getProperty(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemCommonTaskRunnerImplPackageClass;
		FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemFileManipulatorTaskRunnerPackageClass == null ?
													_runnerFactoryImplProperties.getProperty(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemFileManipulatorTaskRunnerPackageClass;
		FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS = systemFlexTaskRunnerImplPackageClass == null ?
													_runnerFactoryImplProperties.getProperty(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS_KEY) : systemFlexTaskRunnerImplPackageClass;
		ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS = systemAnnotationDocletParserImplPackageClass == null ?
													_runnerFactoryImplProperties.getProperty(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS_KEY) : systemAnnotationDocletParserImplPackageClass;
		
		try{
			COMMON_TASK_RUNNER_IMPL_CLASS = Class.forName(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException _classNotFound){
			throw new RuntimeException(errorMessage(COMMON_TASK_RUNNER_IMPL_PACKAGE_CLASS, _classNotFound), _classNotFound);
		}
		
		try{
			FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS = Class.forName(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException _classNotFound){
			throw new RuntimeException(errorMessage(FILE_MANIPULATOR_TASK_RUNNER_IMPL_PACKAGE_CLASS, _classNotFound), _classNotFound);
		}
		
		try{
			FLEX_TASK_RUNNER_IMPL_CLASS = Class.forName(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException _classNotFound){
			throw new RuntimeException(errorMessage(FLEX_TASK_RUNNER_IMPL_PACKAGE_CLASS, _classNotFound), _classNotFound);
		}
		
		try{
			ANNOTATION_DOCLET_PARSER_IMPL_CLASS = Class.forName(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS);
		}catch(ClassNotFoundException _classNotFound){
			throw new RuntimeException(errorMessage(ANNOTATION_DOCLET_PARSER_IMPL_PACKAGE_CLASS, _classNotFound), _classNotFound);
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
	
	public static final synchronized _RunnerFactory getInstance(){
		return INSTANCE;
	}
	
	public final synchronized _CommonTaskRunner getCommonTaskRunnerImpl(){
		
		_CommonTaskRunner _specificInstance = null;
		
		try{
			_specificInstance = (_CommonTaskRunner) COMMON_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException _illegalAccess){
			throw new RuntimeException(errorMessage(getCommonTaskRunnerImplPackage(), _illegalAccess), _illegalAccess);
		}catch(InstantiationException _instantiation){
			throw new RuntimeException(errorMessage(getCommonTaskRunnerImplPackage(), _instantiation), _instantiation);
		}
		
		return _specificInstance;
	}
	
	public final synchronized _FileManipulatorTaskRunner getFileManipulatorTaskRunnerImpl(){
		
		_FileManipulatorTaskRunner _specificInstance = null;
		
		try{
			_specificInstance = (_FileManipulatorTaskRunner) FILE_MANIPULATOR_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException _illegalAccess){
			throw new RuntimeException(errorMessage(getFileManipulatorTaskRunnerImplPackage(), _illegalAccess), _illegalAccess);
		}catch(InstantiationException _instantiation){
			throw new RuntimeException(errorMessage(getFileManipulatorTaskRunnerImplPackage(), _instantiation), _instantiation);
		}
		
		return _specificInstance;
	}
	
	public final synchronized _FlexTaskRunner getFlexTaskRunnerImpl(){
		
		_FlexTaskRunner _specificInstance = null;
		
		try{
			_specificInstance = (_FlexTaskRunner) FLEX_TASK_RUNNER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException _illegalAccess){
			throw new RuntimeException(errorMessage(getFlexTaskRunnerImplPackage(), _illegalAccess), _illegalAccess);
		}catch(InstantiationException _instantiation){
			throw new RuntimeException(errorMessage(getFlexTaskRunnerImplPackage(), _instantiation), _instantiation);
		}
		
		return _specificInstance;
	}
	
	public final synchronized _AnnotationDocletParser getAnnotationDocletParserImpl(){
		
		_AnnotationDocletParser _specificInstance = null;
		
		try{
			_specificInstance = (_AnnotationDocletParser) ANNOTATION_DOCLET_PARSER_IMPL_CLASS.newInstance();
		}catch(IllegalAccessException _illegalAccess){
			throw new RuntimeException(errorMessage(getAnnotationDocletParserImplPackage(), _illegalAccess), _illegalAccess);
		}catch(InstantiationException _instantiation){
			throw new RuntimeException(errorMessage(getAnnotationDocletParserImplPackage(), _instantiation), _instantiation);
		}
		
		return _specificInstance;
	}
	
	private static String errorMessage(String packageClass, Exception type){
		StringBuffer errorMessage = new StringBuffer();
		
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