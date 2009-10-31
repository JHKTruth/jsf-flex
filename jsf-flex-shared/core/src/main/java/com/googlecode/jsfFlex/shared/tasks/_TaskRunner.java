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

import java.util.Collection;

/**
 * @author Ji Hoon Kim
 */
public interface _TaskRunner {
	
    public enum QUEUE_TASK_ID {
        DELETE_RESOURCES, COPY_FILE, COPY_FILE_SET, 
        CREATE_SYSTEM_SWC_FILE, CREATE_SWF, COPY_LOCALE, 
        UNZIP_ARCHIVE_RELATIVE, UNZIP_ARCHIVE_ABSOLUTE_FI, UNZIP_ARCHIVE_ABSOLUTE_IS;
        
        public String getQueueTaskId(String queueTaskId){
            return toString() + ":" + queueTaskId + ":" + System.nanoTime();
        }
        
    }
    
	void addTask(_Task toAdd);
	
	void addTasks(Collection<_Task> tasksToAdd);
	
	void execute();
    
    void queueFutureTask(String taskName, _Task toAdd);
    
    boolean isTaskDone(String taskName);
    
    void waitForFutureTask(String taskName);
    
    void clearAllFutureTasks();
    
}
