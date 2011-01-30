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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline.Argument;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class CopyLocaleTask extends AbstractAntBaseTask {
    
    private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "copylocale.exe";
    private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "copylocale.sh";
    
    private static final String COPY_LOCALE_TARGET = "copy_locale";
    
    private final ExecTask _copyLocaleTask;
    private final Target _copyLocaleTarget;
    
    private String _locale;
    private String _flexSDKRootPath;
    
    public CopyLocaleTask(String locale, String flexSDKRootPath){
        _locale = locale;
        _flexSDKRootPath = flexSDKRootPath;
        
        _copyLocaleTarget = new Target();
        _copyLocaleTarget.setName(COPY_LOCALE_TARGET);
        _copyLocaleTarget.setProject(_taskProject);
        _taskProject.addTarget(_copyLocaleTarget);
        
        _copyLocaleTask = new ExecTask();
        _copyLocaleTask.setOwningTarget(_copyLocaleTarget);
        _copyLocaleTask.setProject(_taskProject);
        _copyLocaleTask.setFailonerror(true);
        
        _copyLocaleTarget.addTask(_copyLocaleTask);
    }
    
    private void setArguments(){
        
        //TODO : Implement it better later
        Argument arg;
        if(FlexConstants.WINDOWS_SYSTEM){
            _copyLocaleTask.setExecutable(_flexSDKRootPath + WINDOWS_EXEC);
        }else{
            _copyLocaleTask.setExecutable(_flexSDKRootPath + NON_WINDOWS_SHELL);
        }
        
        arg = _copyLocaleTask.createArg();
        arg.setLine(FlexConstants.EN_US + " " + _locale);
        
        _copyLocaleTask.maybeConfigure();
        
    }
    
    @Override
    protected void performTask() {
        
        try {
            
            setArguments();
            buildProject(COPY_LOCALE_TARGET);
            
        } catch (BuildException buildException) {
            _taskProject.fireBuildFinished(buildException);
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Error in CopyLocale's performTask with following fields \n");
            errorMessage.append(toString());
            throw new ComponentBuildException(errorMessage.toString(), buildException);
        }

    }
    
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append("locale [ ");
        content.append(_locale);
        content.append(" ] ");
        content.append("flexSDKRootPath [ ");
        content.append(_flexSDKRootPath);
        content.append(" ] ");
        return content.toString();
    }
    
    public CopyLocaleTask locale(String locale){
        _locale = locale;
        return this;
    }
    
}
