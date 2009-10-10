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
package com.googlecode.jsfFlex.states.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.attributes._MXMLUIBasedOnAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOverridesAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlState",
        clazz               =   "com.googlecode.jsfFlex.states.ext.MXMLUIState",
        type                =   "com.googlecode.jsfFlex.MXMLUIState",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIStateTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLState"
)
public abstract class AbstractMXMLUIState 
						extends MXMLUISimpleBase 
                        implements _MXMLUIBasedOnAttribute, _MXMLUINameAttribute, _MXMLUIOverridesAttribute {
	
	/**
	 * Id of the component.
	 */
    @JSFProperty(
            inheritTag  =   true,
            desc        =   "Id of the component."
    )
	public String getId(){
		return super.getId();
	}
	
}