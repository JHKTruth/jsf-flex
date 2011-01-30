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
import java.io.InputStream;

/**
 * Stub implementation in case user provides flesSDK path [preferred method, b/c size difference is small {sarcasm here meaning it's large}]
 * 
 * @author Ji Hoon Kim
 */
final class StubStandardCommonTaskRunnerImpl extends TaskRunnerImpl implements ICommonTaskRunner {
    
    StubStandardCommonTaskRunnerImpl(){
        super();
    }
    
    public void unZipArchiveAbsolute(File file, String dest, String queueTaskId) {
        
    }
    
    public void unZipArchiveAbsolute(InputStream file, String dest, String queueTaskId) {
        
    }
    
    public void unZipArchiveRelative(String file, String dest, String queueTaskId) {
        
    }
    
}
