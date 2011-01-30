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
package com.googlecode.jsfFlex.shared.tasks.ant;

import java.io.File;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline.Argument;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class SWCTask extends AbstractAntBaseTask {
	
	private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "compc.exe";
	private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "compc.sh";
	
	private static final String SOURCE_PATH = " -source-path ";
	private static final String OUTPUT = " -output ";
	private static final String LOAD_CONFIG_ARG_SYNTAX = " -load-config+=";
    private static final String OPTIMIZE = " -compiler.optimize ";
	
	private static final String SWC_TARGET = "swc_compile";
	
	private final ExecTask _swcTask;
	private final Target _swcTarget;
	
	private final String _sourcePath;
	private final String _outPut;
	private final String _loadConfig;
	private final String _flexSDKRootPath;
    private final IFlexApplicationContract _componentFlex;
	
	public SWCTask(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath, IFlexApplicationContract componentFlex){
		super();
		_sourcePath = sourcePath;
		_outPut = outPut;
		_loadConfig = loadConfigFilePath;
		_flexSDKRootPath = flexSDKRootPath;
		_componentFlex = componentFlex;
        
		_swcTarget = new Target();
		_swcTarget.setName(SWC_TARGET);
		_swcTarget.setProject(_taskProject);
		_taskProject.addTarget(_swcTarget);
		
		_swcTask = new ExecTask();
		_swcTask.setOwningTarget(_swcTarget);
		_swcTask.setProject(_taskProject);
		_swcTask.setFailonerror(true);
		
		_swcTarget.addTask(_swcTask);
	}
	
	private void setArguments(){
		
		Argument arg;
		if(FlexConstants.WINDOWS_SYSTEM){
			_swcTask.setExecutable(_flexSDKRootPath + WINDOWS_EXEC);
		}else{
			_swcTask.setExecutable(_flexSDKRootPath + NON_WINDOWS_SHELL);
		}
		
		arg = _swcTask.createArg();
		arg.setLine(SOURCE_PATH + FlexConstants.STRING_QUOTE + _sourcePath + FlexConstants.STRING_QUOTE);
		
		arg = _swcTask.createArg();
		arg.setLine(OUTPUT + FlexConstants.STRING_QUOTE + _outPut + FlexConstants.STRING_QUOTE);
		
        arg = _swcTask.createArg();
        arg.setLine(OPTIMIZE);
        
		if(_loadConfig != null){
			arg = _swcTask.createArg();
			arg.setLine(LOAD_CONFIG_ARG_SYNTAX + FlexConstants.STRING_QUOTE + _loadConfig + FlexConstants.STRING_QUOTE);
		}
		
        Map <String, String> additionalSwcCommandArguments = _componentFlex.getAdditionalSwccCommandArguments();
        if(additionalSwcCommandArguments != null){
            for(String currKey : additionalSwcCommandArguments.keySet()){
                arg = _swcTask.createArg();
                arg.setLine(currKey + additionalSwcCommandArguments.get(currKey));
            }
        }
        
		_swcTask.maybeConfigure();
		
	}
	
	protected void performTask() {
		try {
			
			setArguments();
			buildProject(SWC_TARGET);
			
		} catch (BuildException buildException) {
			_taskProject.fireBuildFinished(buildException);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Error in SWC's performTask with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), buildException);
		}
	}
	
	public String toString(){
		StringBuilder content = new StringBuilder();
		content.append("source [ ");
		content.append(_sourcePath);
		content.append(" ] ");
		content.append("outPut [ ");
		content.append(_outPut);
		content.append(" ] ");
		content.append("loadConfig [ ");
		content.append(_loadConfig);
		content.append(" ] ");
		content.append(" flexSDKRootPath [ ");
		content.append(_flexSDKRootPath);
		content.append(" ] ");
		return content.toString();
	}
	
}
