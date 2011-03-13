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
public interface IFlexUIAsynchronousPropertyUpdateDelimAttributes {
    
    /**
     * This ',' deliminated String is to provide which property the user wishes to fetch from the source component back to the server side. The parameter for this method is in a form of a String deliminated to allow nesting. Meaning if the String is "first, second", then following will be fetched sourceComp["first"]["second"]. This is to allow much flexibility.
     */
    @JSFProperty(desc   =   "This ',' deliminated String is to provide which property the user wishes to fetch from the source component back to the server side. The parameter for this method is in a form of a String deliminated to allow nesting. Meaning if the String is 'first, second', then following will be fetched sourceComp['first']['second']. This is to allow much flexibility.")
    String getSourcePropertyDelim();
    
    /**
     * This ',' deliminated String is to provide which property the user wishes to update for the target component on the client side. The parameter for this method is in a form of a String deliminated to allow nesting. Meaning if the String is "first, second", then following will be set sourceComp["first"]["second"] = val. This is to allow much flexibility.
     */
    @JSFProperty(desc   =   "This ',' deliminated String is to provide which property the user wishes to update for the target component on the client side. The parameter for this method is in a form of a String deliminated to allow nesting. Meaning if the String is 'first, second', then following will be set sourceComp['first']['second'] = val. This is to allow much flexibility.")
    String getTargetPropertyDelim();
    
}
