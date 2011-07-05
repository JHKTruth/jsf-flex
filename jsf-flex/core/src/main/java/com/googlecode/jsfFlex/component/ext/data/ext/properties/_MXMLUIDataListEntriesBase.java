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
package com.googlecode.jsfFlex.component.ext.data.ext.properties;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.attributes._MXMLUIBindingBeanListAttribute;

/**
 * @JSFComponent
 * 	 class    = "com.googlecode.jsfFlex.component.ext.data.ext.properties.MXMLUIDataListEntriesBase"
 *   type     = "com.googlecode.jsfFlex.MXMLUIDataListEntriesBase"
 *   family   = "javax.faces.MXMLProperty"
 *   desc	  = "Base component for DataList Entries MXMLProperty component"
 *   template = "true"
 *   
 * @author Ji Hoon Kim
 */
public abstract class _MXMLUIDataListEntriesBase 
						extends UIComponentBase 
						implements _MXMLUIBindingBeanListAttribute {
	
	public void encodeChildren(FacesContext context) throws IOException {
		
		if(getBindingBeanList().size() > 0){
		
			for(java.util.Iterator iterate = getBindingBeanList().iterator(); iterate.hasNext();){
				Object _currBeanRef = iterate.next();
				
				for(java.util.Iterator childrenIterate = getChildren().iterator(); childrenIterate.hasNext();){
					MXMLUIDataObjectBase currComponent = (MXMLUIDataObjectBase) childrenIterate.next();
					
					currComponent.setCurrBeanRef(_currBeanRef);
					currComponent.encodeBegin(context);
					currComponent.encodeChildren(context);
					currComponent.encodeEnd(context);
					
				}
				
			}
			
		}
		
	}
	
	public boolean getRendersChildren() {
		return true;
	}
	
}
