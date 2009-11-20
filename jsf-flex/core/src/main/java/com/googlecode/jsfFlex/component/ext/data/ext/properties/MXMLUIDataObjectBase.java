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
import java.util.LinkedList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.util.ReflectionHelperUtil;

/**
 * @author Ji Hoon Kim
 */
public abstract class MXMLUIDataObjectBase 
						extends UIComponentBase {
	
	private List<? super UIComponent> subComponentPropertyList;
	
	private Object _currBeanRef;
	
	public void encodeChildren(FacesContext context) throws IOException {
		
		for(UIComponent currChild : getChildren()){
			
			if(!(currChild instanceof MXMLUIDynamicPropertyBase || 
					currChild instanceof MXMLUIStaticPropertyBase)){
				currChild.encodeBegin(context);
				currChild.encodeChildren(context);
				currChild.encodeEnd(context);
			}
			
		}
		
	}
	
	public boolean getRendersChildren() {
		return true;
	}
	
	protected String processDataObjectProperties(){
		
		StringBuilder properties = new StringBuilder();
		List<? super UIComponent> subComponentPropertyList = getSubComponentPropertyList();
		
		if(subComponentPropertyList != null){
			for(Object currProperty : subComponentPropertyList){
				properties.append(processDataObjectProperty(currProperty));
			}
		}
		
		return properties.toString();
	}
	
	private String processDataObjectProperty(Object currProperty){
		
		StringBuilder property = new StringBuilder();
		
		if(currProperty instanceof MXMLUIDynamicPropertyBase){
			MXMLUIDynamicPropertyBase currDynamicProperty = MXMLUIDynamicPropertyBase.class.cast( currProperty );
			
			String propertyName = currDynamicProperty.getProperty();
			String propertyMethodName = currDynamicProperty.getPropertyMethodName();
			
			try{
				String propertyValue = ReflectionHelperUtil.getValue(_currBeanRef, propertyMethodName).toString();
				
				property.append(" ");
				property.append(propertyName);
				property.append("='");
				property.append(propertyValue);
				property.append("'");
				
			}catch(Exception reflectionException){
				throw new ComponentBuildException("Exception was triggered while invoking " + propertyMethodName, reflectionException);
			}
			
		}else if(currProperty instanceof MXMLUIStaticPropertyBase){
			MXMLUIStaticPropertyBase currStaticProperty = MXMLUIStaticPropertyBase.class.cast( currProperty );
			
			property.append(" ");
			property.append(currStaticProperty.getStaticPropertyName());
			property.append("='");
			property.append(currStaticProperty.getStaticPropertyValue());
			property.append("'");
			
		}
		
		return property.toString();
	}
	
	private synchronized List<? super UIComponent> getSubComponentPropertyList() {
		if(subComponentPropertyList == null){
			subComponentPropertyList = new LinkedList<UIComponent>();
			
			for(UIComponent currInstance : getChildren()){
				if(currInstance instanceof MXMLUIDynamicPropertyBase || 
						currInstance instanceof MXMLUIStaticPropertyBase){
					subComponentPropertyList.add(currInstance);
				}
			}
			
		}
		
		return subComponentPropertyList;
	}
	
	public Object getCurrBeanRef() {
		return _currBeanRef;
	}
	public void setCurrBeanRef(Object currBeanRef) {
		_currBeanRef = currBeanRef;
	}
	
}
