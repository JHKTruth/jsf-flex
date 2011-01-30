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

import org.python.core.PyException;
import org.python.core.PyObject;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks.AbstractTask;

/**
 * @author Ji Hoon Kim
 */
abstract class AbstractJythonBaseTask extends AbstractTask {
	
	protected IJythonTaskPerformer _jythonTask;
	
	protected void performTask() {
		
		build();
		
		try{
			_jythonTask.performTask();
		}catch(PyException pyException){
			
			StringBuilder exceptionMessage = new StringBuilder("Exception thrown with ");
			PyObject valueObject = pyException.value;
			exceptionMessage.append(" valueContent [ ");
			exceptionMessage.append(valueObject.__str__());
			exceptionMessage.append(" ] ");
			throw new ComponentBuildException(exceptionMessage.toString(), pyException);
		}
		
	}
	
	abstract void build();
	
}
