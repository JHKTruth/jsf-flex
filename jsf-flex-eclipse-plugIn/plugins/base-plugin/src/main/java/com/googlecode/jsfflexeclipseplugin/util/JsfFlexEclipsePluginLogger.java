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
package com.googlecode.jsfflexeclipseplugin.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;

/**
 * @author Ji Hoon Kim
 */
public final class JsfFlexEclipsePluginLogger {
	
	private JsfFlexEclipsePluginLogger() {
		super();
	}
	
	public static void logInfo(String message) {
		
		JsfFlexActivator.getDefault().getLog().log(new Status(IStatus.INFO, JsfFlexActivator.PLUGIN_ID, message));
	}
	
	public static void logError(String message, Throwable error) {
		
		JsfFlexActivator.getDefault().getLog().log(new Status(IStatus.ERROR, JsfFlexActivator.PLUGIN_ID, message, error));
	}
	
	public static void logWarning(String message) {
		
		JsfFlexActivator.getDefault().getLog().log(new Status(IStatus.WARNING, JsfFlexActivator.PLUGIN_ID, message)); 
	}
	
}
