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
package com.googlecode.jsfFlexPlugIn.utils.tasks;

import java.io.PrintStream;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;

/**
 * @author Ji Hoon Kim
 */
abstract class AntBase {
	
	Project _taskProject;
	private DefaultLogger _consoleLogger;
	
	AntBase(){
		super();
		_taskProject = new Project();
		_consoleLogger = new DefaultLogger();
		_consoleLogger.setErrorPrintStream(System.err);
		_consoleLogger.setOutputPrintStream(System.out);
		_consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		_taskProject.addBuildListener(_consoleLogger);
	}
	
	void buildProject(String targetToExecute){
		_taskProject.fireBuildStarted();
		_taskProject.init();
		_taskProject.executeTarget(targetToExecute);
		_taskProject.fireBuildFinished(null);
	}
	
	void addBuildListener(DefaultLogger consoleLogger){
		_taskProject.removeBuildListener(_consoleLogger);
		_consoleLogger = consoleLogger;
		_taskProject.addBuildListener(_consoleLogger);
	}
	
	void setErrorPrintstream(PrintStream errorStream){
		_consoleLogger.setErrorPrintStream(errorStream);
	}
	
	void setOutputPrintStream(PrintStream outStream){
		_consoleLogger.setOutputPrintStream(outStream);
	}
	
	void setMessageOutputLevel(int outLevel){
		_consoleLogger.setMessageOutputLevel(outLevel);
	}
	
}
