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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Previously TaskRunnerImpl was designed to allow lazy execution<br>
 * of the AbstractTasks within the List; however due to separation of implementation<br>
 * of the various .+Runner interfaces, it was decided to execute the AbstractTasks<br>
 * upon addition.
 * 
 * In order to improve performance, Executors's ThreadPool will be used to span off Threads for<br>
 * FutureTasks. However, one thing to note is that these AbstractTasks should be<br>
 * INDEPENDENT to other tasks added with the addTask method and other tasks<br>
 * added with the queueFutureTask method.<br>
 * 
 * @author Ji Hoon Kim
 */
class TaskRunnerImpl implements ITaskRunner {
	
    private final static Log _log = LogFactory.getLog(TaskRunnerImpl.class);
    
    private static final String DUPLICATE_QUEUE_TASK_ID_PROVIDED = "Duplicate future queue task id has been provided : ";
    
    /*
     * Technically most of the computers have Duo Core, so consider that when setting the value 
     * for NUM_OF_EXECUTOR_THREADS
     */
    private static final int NUM_OF_EXECUTOR_THREADS = 6;
    private static final int EXECUTOR_SERVICE_SHUT_DOWN_LIMIT = 20;
    
    private final ConcurrentMap<String, Future> _queuedTasks;
    private final ExecutorService _queuedService = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS);
    
	private final Object _lock = new Object();
	private final List<AbstractTask> _tasks;
    
	TaskRunnerImpl(){
		super();
		_tasks = new LinkedList<AbstractTask>();
        _queuedTasks = new ConcurrentHashMap<String, Future>();
	}
	
	public void addTask(AbstractTask toAdd) {
		synchronized(_lock){
			_tasks.add(toAdd);
			execute();
		}
	}
	
	public void addTasks(Collection<AbstractTask> tasksToAdd) {
		synchronized(_lock){
			_tasks.addAll(tasksToAdd);
			execute();
		}
	}
	
	public void execute() {
		
		synchronized(_lock){
			for(AbstractTask current : _tasks){
				current.performTask();
			}
			clearAllTask();
		}
	}
	
	private void clearAllTask(){
		_tasks.clear();
	}
    
    /* 
     * Though finalize method is not always invoked, place in the code to clean up ExecutorService<br>
     * for times when it is called.
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(_queuedService != null){
            _queuedService.shutdownNow();
        }
        if(_queuedTasks != null){
            _queuedTasks.clear();
        }
    }
    
    public void queueFutureTask(final String taskName, final AbstractTask toAdd){
        final FutureTask<?> task = new FutureTask<Void>(new Runnable(){
            public void run() {
                toAdd.performTask();
                _queuedTasks.remove(taskName);
                _log.info("Queued taskName has been completed : " + taskName);
            }
        }, null);
        
        Future previousTask = _queuedTasks.putIfAbsent(taskName, task);
        if(previousTask != null){
            throw new RuntimeException(DUPLICATE_QUEUE_TASK_ID_PROVIDED + taskName);
        }
        _queuedService.submit(task);
    }
    
    public boolean isTaskDone(String taskName){
        Future task = _queuedTasks.get(taskName);
        return (task != null && task.isDone());
    }
    
    public void waitForFutureTask(String taskName){
        Future task = _queuedTasks.get(taskName);
        if(task != null){
            try{
                _log.info("Waiting for taskName : " + taskName);
                task.get();
                _log.info("Finished waiting for the taskName : " + taskName);
            }catch(ExecutionException executeExcept){
                _log.error("Execution exception thrown within waitForFutureTask for " + taskName, executeExcept);
            }catch(InterruptedException interruptedExcept){
                Thread.currentThread().interrupt();
            }finally{
                task.cancel(true);
            }
        }
    }
    
    public void clearAllFutureTasks(){
        _queuedService.shutdown();
        try{
            _queuedService.awaitTermination(EXECUTOR_SERVICE_SHUT_DOWN_LIMIT, TimeUnit.SECONDS);
        }catch(InterruptedException interruptedException){
            Thread.currentThread().interrupt();
        }finally{
            if(_queuedTasks != null){
                _queuedTasks.clear();
            }
        }
    }
    
}
