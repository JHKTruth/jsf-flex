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
package com.googlecode.jsfFlex.component.ext;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUISelectedLabelAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexTree",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUITree",
        type                =   "com.googlecode.jsfFlex.FlexUITree",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUITreeTag",
        family              =   "javax.faces.FlexInput",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexTree",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.AbstractFlexUIInputTagBase"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUITree")
public abstract class AbstractFlexUITree 
						extends com.googlecode.jsfFlex.component.FlexUISelectedIndexBase
						implements IFlexUISelectedLabelAttribute, IFlexUIBaseAttributes {
	
	private static final String SELECTED_LABEL_ID_APPENDED = "_selectedLabel";
	private static final String SELECTED_LABEL_ATTR = "selectedLabel";
	
	@Override
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	java.util.Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String selectedLabelId = getId() + SELECTED_LABEL_ID_APPENDED;
    	String selectedLabelUpdateVal = requestMap.get(selectedLabelId);
    	
    	if(selectedLabelUpdateVal != null){
    		setSelectedLabel(selectedLabelUpdateVal);
    	}

    }
	
    @Override
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	javax.el.ValueExpression ve = getValueExpression(SELECTED_LABEL_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelectedIndex());
    		setSelectedIndex(null);
    	}
    	
    }
}
