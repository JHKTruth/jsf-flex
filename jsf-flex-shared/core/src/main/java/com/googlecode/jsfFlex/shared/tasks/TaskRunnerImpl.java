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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Previously TaskRunnerImpl was designed to allow lazy execution<br>
 * of the _Tasks within the List; however due to separation of implementation<br>
 * of the various _.+Runner interfaces, it was decided to execute the _Tasks<br>
 * upon addition.
 * 
 * @author Ji Hoon Kim
 */
class TaskRunnerImpl implements _TaskRunner {
	
	private final Object _lock;
	private final List _tasks;
	
	TaskRunnerImpl(){
		super();
		_tasks = new LinkedList();
	}
	
	TaskRunnerImpl(LinkedList tasks){
		_tasks = new LinkedList();
		_tasks.addAll(tasks);
	}
	
	{
		_lock = new Object();
	}

	public void addTask(_Task toAdd) {
		synchronized(_lock){
			_tasks.add(toAdd);
			execute();
		}
	}
	
	public void addTasks(Collection tasksToAdd) {
		synchronized(_lock){
			_tasks.addAll(tasksToAdd);
			execute();
		}
	}
	
	public void execute() {
		
		synchronized(_lock){
			for(Iterator iterate = _tasks.iterator(); iterate.hasNext();){
				_Task current = (_Task) iterate.next();
				current.performTask();
			}
			clearAllTask();
		}
	}
	
	private void clearAllTask(){
		_tasks.clear();
	}
	
}
