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

import java.util.Comparator;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIBaseAttributes;
import com.googlecode.jsfFlex.attributes.IFlexUIDataFieldAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIEditableAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUIInputBase;
import com.googlecode.jsfFlex.component.ext.AbstractFlexUIDataGrid.WrappedBeanEntry;
import com.googlecode.jsfFlex.shared.util.ReflectionHelperUtil;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexDataGridColumn",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUIDataGridColumn",
        type                =   "com.googlecode.jsfFlex.FlexUIDataGridColumn",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUIDataGridColumnTag",
        family              =   "javax.faces.FlexInput",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexDataGridColumn"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIDataGridColumn")
public abstract class AbstractFlexUIDataGridColumn 
                        extends AbstractFlexUIInputBase 
                        implements IFlexUIBaseAttributes, IFlexUIDataFieldAttribute, IFlexUIEditableAttribute {
    
    private final static Log _log = LogFactory.getLog(AbstractFlexUIDataGridColumn.class);
    
    private Comparator<WrappedBeanEntry> wrappedEntryAscendingComparator;
    private Comparator<WrappedBeanEntry> wrappedEntryDescendingComparator;
    
    @Override
    public JSONObject getComponentInitValues(){
        return null;
    }
    
    @Override
    protected void populateComponentInitValues(){
        
    }
    
    public String getFormatedColumnData(Object currDataEntry) {
        Object formatedColumnData = "";
        
        try{
            formatedColumnData = ReflectionHelperUtil.getValue(currDataEntry, getDataFieldMethodName());
            formatedColumnData = formatedColumnData == null ? "" : formatedColumnData.toString();
        }catch(NoSuchMethodException noSuchMethodException){
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
            errorMessage.append(getDataFieldMethodName());
            _log.error(errorMessage.toString(), noSuchMethodException);
            return formatedColumnData.toString();
        }catch(Exception additionalAccessException){
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
            errorMessage.append(getDataFieldMethodName());
            _log.error(errorMessage.toString(), additionalAccessException);
            return formatedColumnData.toString();
        }
        
        return formatedColumnData.toString();
    }
    
    public boolean setDataField(FacesContext context, Object currDataEntry, Object currValue){
        
        final String SET_DATA_FIELD_METHOD_NAME = "set" + getDataField().substring(0, 1).toUpperCase() + getDataField().substring(1);
        
        boolean success = true;
        
        currValue = getConvertedValue(context, currValue);
        _log.debug("Converted value's type prior to setting to an instance bean is : " + currValue.getClass().getName());
        
        try{
            ReflectionHelperUtil.invokeMethod(currDataEntry, SET_DATA_FIELD_METHOD_NAME, new Class[]{ currValue.getClass() }, new Object[]{ currValue });
        }catch(NoSuchMethodException noSuchMethodException){
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
            errorMessage.append(SET_DATA_FIELD_METHOD_NAME);
            success = false;
            _log.error(errorMessage.toString(), noSuchMethodException);
        }catch(Exception additionalAccessException){
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
            errorMessage.append(SET_DATA_FIELD_METHOD_NAME);
            success = false;
            _log.error(errorMessage.toString(), additionalAccessException);
        }
        
        return success;
    }
    
    private synchronized String getDataFieldMethodName(){
        final String GET_DATA_FIELD_METHOD_NAME = "get" + getDataField().substring(0, 1).toUpperCase() + getDataField().substring(1);
        
        return GET_DATA_FIELD_METHOD_NAME;
    }
    
    public synchronized Comparator<WrappedBeanEntry> getWrappedEntryAscendingComparator() {
        if(wrappedEntryAscendingComparator == null) {
            wrappedEntryAscendingComparator = new Comparator<WrappedBeanEntry>() {
                
                public int compare(WrappedBeanEntry entry1, WrappedBeanEntry entry2) {
                    
                    Object obj1 = entry1.getBeanEntry();
                    Object obj2 = entry2.getBeanEntry();
                    
                    try{
                        Comparable<? super Object> act1 = (Comparable<? super Object>) ReflectionHelperUtil.getValue(obj1, getDataFieldMethodName());
                        Comparable<? super Object> act2 = (Comparable<? super Object>) ReflectionHelperUtil.getValue(obj2, getDataFieldMethodName());
                        
                        return (act1.compareTo(act2) * -1);
                    }catch(NoSuchMethodException noSuchMethodException){
                        StringBuilder errorMessage = new StringBuilder();
                        errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
                        errorMessage.append(getDataFieldMethodName());
                        throw new RuntimeException(errorMessage.toString(), noSuchMethodException);
                    }catch(Exception additionalAccessException){
                        StringBuilder errorMessage = new StringBuilder();
                        errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
                        errorMessage.append(getDataFieldMethodName());
                        throw new RuntimeException(errorMessage.toString(), additionalAccessException);
                    }
                }
                
            };
            
        }
        
        return wrappedEntryAscendingComparator;
    }
    
    public synchronized Comparator<WrappedBeanEntry> getWrappedEntryDescendingComparator() {
        if(wrappedEntryDescendingComparator == null) {
            wrappedEntryDescendingComparator = new Comparator<WrappedBeanEntry>() {
                
                public int compare(WrappedBeanEntry entry1, WrappedBeanEntry entry2) {
                    
                    Object obj1 = entry1.getBeanEntry();
                    Object obj2 = entry2.getBeanEntry();
                    
                    try{
                        Comparable<? super Object> act1 = (Comparable<? super Object>) ReflectionHelperUtil.getValue(obj1, getDataFieldMethodName());
                        Comparable<? super Object> act2 = (Comparable<? super Object>) ReflectionHelperUtil.getValue(obj2, getDataFieldMethodName());
                        
                        return act1.compareTo(act2);
                    }catch(NoSuchMethodException noSuchMethodException){
                        StringBuilder errorMessage = new StringBuilder();
                        errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
                        errorMessage.append(getDataFieldMethodName());
                        throw new RuntimeException(errorMessage.toString(), noSuchMethodException);
                    }catch(Exception additionalAccessException){
                        StringBuilder errorMessage = new StringBuilder();
                        errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
                        errorMessage.append(getDataFieldMethodName());
                        throw new RuntimeException(errorMessage.toString(), additionalAccessException);
                    }
                    
                }
                
            };
            
        }
        
        return wrappedEntryDescendingComparator;
    }
    
}
