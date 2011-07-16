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
package com.googlecode.jsfflexeclipseplugin.views;

import org.eclipse.osgi.util.NLS;

/**
 * @author Ji Hoon Kim
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.googlecode.jsfflexeclipseplugin.views.messages"; //$NON-NLS-1$
	public static String CLEAR_ATTRIBUTES;
	public static String DESCRIPTION;
	public static String FIELD;
	public static String VIEW_ALL_ATTRIBUTES;
	public static String VIEW_COMMON_STYLE_ATTRIBUTES;
	public static String VIEW_EFFECT_ATTRIBUTES;
	public static String VIEW_EVENT_ATTRIBUTES;
	public static String VIEW_HALO_THEME_STYLE_ATTRIBUTES;
	public static String VIEW_PROPERTY_ATTRIBUTES;
	public static String VIEW_SPARK_THEME_STYLE_ATTRIBUTES;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
