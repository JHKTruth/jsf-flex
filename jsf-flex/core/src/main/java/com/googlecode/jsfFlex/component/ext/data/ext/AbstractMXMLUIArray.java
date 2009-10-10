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
package com.googlecode.jsfFlex.component.ext.data.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

/**
 * <ul>
 * This component can have as its direct children components of :
 * 		<li> AbstractMXMLUIObjectListEntries </li>
 * 		<li> AbstractMXMLUIObjectElement </li>
 * </ul>
 * 
 * To have nesting of objects, one should have jf:mxmlObjectElement and <br>
 * jf:mxmlObjectListEntries tags as this component's children which have <br>
 * jf:mxmlObjectProperty or jf:mxmlObjectStaticProperty tag for properties. <br> 
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlArray",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.MXMLUIArray",
        type                =   "com.googlecode.jsfFlex.MXMLUIArray",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.data.MXMLUIArrayTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLArray"
)
public abstract class AbstractMXMLUIArray 
						extends AbstractMXMLUIObject {
	
}
