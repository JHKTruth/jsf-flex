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
package com.googlecode.jsfFlex.phaseListener;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

/**
 * @author Ji Hoon Kim
 */
abstract class _ServiceRequestDataRetrieverFlusher {
	
	abstract void retrieveFlushData(FacesContext context, String componentId, String methodToInvoke) throws ServletException, IOException;
	
	Object invokeResourceMethod(FacesContext context, String componentId, String methodToInvoke, Class[] methodParameters, Object[] methodArguments) throws Exception {
		
		UIComponent component = UIComponent.class.cast( context.getExternalContext().getSessionMap().get(componentId) );
		
		Object obj = null;
		
		try{
			Method method = component.getClass().getMethod(methodToInvoke, methodParameters);
			obj = method.invoke(component, methodArguments);
		}catch(NoSuchMethodException noSuchMethodException){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
			errorMessage.append(methodToInvoke);
			errorMessage.append(" for component with id : ");
			errorMessage.append(componentId);
			throw new Exception(errorMessage.toString(), noSuchMethodException);
		}catch(Exception additionalAccessException){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
			errorMessage.append(methodToInvoke);
			errorMessage.append(" for component with id : ");
			errorMessage.append(componentId);
			throw new Exception(errorMessage.toString(), additionalAccessException);
		}
		
		return obj;
	}
	
}
