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
package com.googlecode.jsfFlex.shared.tasks.jython;

import java.io.File;
import java.util.Vector;

import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public final class CopyLocaleTask extends _JythonBaseTask {
    
    private static final String PYTHON_EXECUTION_FILE = "commandExecuteTask.py";
    
    private static final PyObject _commandExecuteTaskClass;
    
    static{
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(CopyLocaleTask.class.getResourceAsStream(PYTHON_EXECUTION_FILE));
        _commandExecuteTaskClass = interpreter.get("CommandExecuteTask");
    }
    
    private static final String WINDOWS_EXEC = "bin" + File.separatorChar + "copylocale.exe";
    private static final String NON_WINDOWS_SHELL = "bin" + File.separatorChar + "copylocale.sh";
        
    private String _locale;
    private String _flexSDKRootPath;
    
    public CopyLocaleTask(){
        super();
    }
    
    public CopyLocaleTask(String locale, String flexSDKRootPath){
        _locale = locale;
        _flexSDKRootPath = flexSDKRootPath;
    }
    
    @Override
    void build() {
        String commandToExecute = MXMLConstants.WINDOWS_SYSTEM ? _flexSDKRootPath + WINDOWS_EXEC : _flexSDKRootPath + NON_WINDOWS_SHELL;
        Vector<String> commandArguments = getCommandArguments();
        
        PyObject commandExecuteTaskObject = _commandExecuteTaskClass.__call__(new PyString(commandToExecute), 
                                                                        new PyList(commandArguments));
        _jythonTask = _JythonTaskPerformer.class.cast( commandExecuteTaskObject.__tojava__(_JythonTaskPerformer.class) );
    }
    
    private Vector<String> getCommandArguments(){
        
        Vector<String> commandArguments = new Vector<String>();
        
        commandArguments.add(MXMLConstants.EN_US + " " + _locale);
        
        return commandArguments;
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
