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
@JSFComponent(configExcluded=true)
public interface IFlexUISelectedValueAttribute {
    
    /**
     * This will represent the selectedValue chosen for the RadioButtonGroup. It should be used for databinding, so to figure out which radioButton within the same groupNamehas been chosen. Meaning it serves no purpose for display, so rationally you shouldhave databinded to ONE of the RadioButton with the same groupName.
     */
    @JSFProperty(desc   =   "This will represent the selectedValue chosen for the RadioButtonGroup. It should be used for databinding, so to figure out which radioButton within the same groupNamehas been chosen. Meaning it serves no purpose for display, so rationally you shouldhave databinded to ONE of the RadioButton with the same groupName.")
    String getSelectedValue();
    
    void setSelectedValue(String selectedValue);
    
}
