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
package com.googlecode.jsfFlex.component;

import java.io.IOException;

import javax.faces.context.FacesContext;

/**
 * This class will extend from MXMLUISelectedIndexBase class, so it will process the needed actions<br>
 * of setting and retrieving of "selectedIndex" and "text" attributes. Additional task taken by the<br>
 * component is to set the "creationPolicy" to "all", so that the Flex component will be accessible<br>
 * after it finished its loading.<br>
 * 
 * @JSFComponent
 *   type     = "com.googlecode.jsfFlex.MXMLUIViewStackBase"
 *   family   = "javax.faces.MXMLUIViewStackBase"
 *   desc	  = "Base component for MXMLInput components that contain selected attribute AND sets the creationPolicy to all [for setting the initial values]"
 * 
 * @author Ji Hoon Kim
 */
public abstract class MXMLUIViewStackBase extends com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase {
	
	public void encodeBegin(FacesContext context) throws IOException {
		/*
		 * HACK :
		 * 	Setting creationPolicy to "all", so the components which are not
		 * 	shown by the non-selected Container would be created and can be referred 
		 * 	during the initialization/value preserving process
		 * 
		 *	I think this is the most prudent choice
		 */
		getAttributes().put("creationPolicy", "all");
		
		super.encodeBegin(context);
	}
	
}
