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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks.sdk.UnzipTask;

/**
 * A SDKStandard implementation of _CommonTaskRunner interface.<br>
 * 
 * @author Ji Hoon Kim
 */
final class SDKStandardCommonTaskRunnerImpl extends TaskRunnerImpl implements _CommonTaskRunner {
	
	SDKStandardCommonTaskRunnerImpl(){
		super();
	}
	
	public void unZipArchiveRelative(String file, String dest, String queueTaskId) {
		InputStream fileIO = UnzipTask.class.getResourceAsStream(file);
		UnzipTask toUnzip = new UnzipTask(fileIO, dest);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, toUnzip);
        }else{
            addTask(toUnzip);
        }
	}
	
	public void unZipArchiveAbsolute(File file, String dest, String queueTaskId) {
		try{
			FileInputStream fileIO = new FileInputStream(file);
			UnzipTask toUnzip = new UnzipTask(fileIO, dest);
            if(queueTaskId != null){
                queueFutureTask(queueTaskId, toUnzip);
            }else{
                addTask(toUnzip);
            }
		}catch(FileNotFoundException fileNotFoundExcept){
			throw new ComponentBuildException(fileNotFoundExcept);
		}
	}
	
	public void unZipArchiveAbsolute(InputStream file, String dest, String queueTaskId) {
		UnzipTask toUnzip = new UnzipTask(file, dest);
        if(queueTaskId != null){
            queueFutureTask(queueTaskId, toUnzip);
        }else{
            addTask(toUnzip);
        }
	}
    
}
