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
package com.googlecode.jsfFlex.attributes;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
public interface IFlexUIQueuedFilterListBreakUpSizeAttribute {

	/**
     * This value is associated with queueFilterThreshold. It represents the size of the queued list size 
     * [i.e. 1000 entries and if this entry is 250, then there will be 4 lists or Threads queued up]
     */
    @JSFProperty(
    		desc   			=   "This value is associated with queueFilterThreshold. It represents the size of the queued list size [i.e. 1000 entries and if this entry is 250, then there will be 4 lists or Threads queued up]",
    		defaultValue	=	"250"
    )
	String getQueuedFilterListBreakUpSize();
	
}
