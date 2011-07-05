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

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataTipFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataTipFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorDataFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorHeightOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorUsesEnterKeyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorWidthOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorXOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditorYOffsetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderTextAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderWordWrapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemEditorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMinWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRendererIsEditorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResizableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowDataTipsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISortCompareFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISortDescendingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISortableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVisibleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWordWrapAttribute;
import com.googlecode.jsfFlex.component.MXMLUIInputBase;
import com.googlecode.jsfFlex.shared.util.ReflectionHelperUtil;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlDataGridColumn",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIDataGridColumn",
        type                =   "com.googlecode.jsfFlex.MXMLUIDataGridColumn",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIDataGridColumnTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLDataGridColumn"
)
public abstract class AbstractMXMLUIDataGridColumn 
                        extends MXMLUIInputBase 
                        implements _MXMLUIDataFieldAttribute, _MXMLUIDataTipFieldAttribute, _MXMLUIDataTipFunctionAttribute, 
                        _MXMLUIEditableAttribute, _MXMLUIEditorDataFieldAttribute, _MXMLUIEditorHeightOffsetAttribute, 
                        _MXMLUIEditorUsesEnterKeyAttribute, _MXMLUIEditorWidthOffsetAttribute, _MXMLUIEditorXOffsetAttribute, 
                        _MXMLUIEditorYOffsetAttribute, _MXMLUIHeaderRendererAttribute, _MXMLUIHeaderTextAttribute, 
                        _MXMLUIHeaderWordWrapAttribute, _MXMLUIImeModeAttribute, _MXMLUIItemEditorAttribute, 
                        _MXMLUIItemRendererAttribute, _MXMLUILabelFunctionAttribute, _MXMLUIMinWidthAttribute, 
                        _MXMLUIRendererIsEditorAttribute, _MXMLUIResizableAttribute, _MXMLUIShowDataTipsAttribute, 
                        _MXMLUISortableAttribute, _MXMLUISortCompareFunctionAttribute, _MXMLUISortDescendingAttribute, 
                        _MXMLUIVisibleAttribute, _MXMLUIWordWrapAttribute, _MXMLUIBackgroundColorAttribute, _MXMLUIColorAttribute, 
                        _MXMLUIDisabledColorAttribute, _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, 
                        _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, 
                        _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, 
                        _MXMLUIHeaderStyleNameAttribute, _MXMLUIPaddingLeftAttribute, _MXMLUIPaddingRightAttribute, 
                        _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute {
    
    private final static Log _log = LogFactory.getLog(AbstractMXMLUIDataGridColumn.class);
    
    private static final String REQUEST_KEYS_KEY = "requestKeys";
    private static final String RESULT_CODE_KEY = "resultCode";
    
    private Comparator<Object> ascendingComparator;
    private Comparator<Object> descendingComparator;
    
    public JSONObject getComponentInitValues(){
        return null;
    }
    
    protected void populateComponentInitValues(){
        
    }
    
    public List<String> getFormatedColumnData(List dataGridEntries, int dataStartIndex, int dataEndIndex) {
        
        //for more complicated Grids, the return data might consist of XML entries
        List<String> formatedColumnData = new LinkedList<String>();
        
        for(int i = dataStartIndex; i < dataEndIndex; i++){
            Object currDataEntry = dataGridEntries.get(i);
            Object currDataValue = null;
            
            try{
                currDataValue = ReflectionHelperUtil.getValue(currDataEntry, getDataFieldMethodName());
            }catch(NoSuchMethodException noSuchMethodException){
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("NoSuchMethodException was thrown while invoking method : ");
                errorMessage.append(getDataFieldMethodName());
                _log.error(errorMessage.toString(), noSuchMethodException);
                return new LinkedList<String>();
            }catch(Exception additionalAccessException){
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("Other exception aside from NoSuchMethodException was thrown while invoking method : ");
                errorMessage.append(getDataFieldMethodName());
                _log.error(errorMessage.toString(), additionalAccessException);
                return new LinkedList<String>();
            }
            
            String currValue = currDataValue == null ? "" : currDataValue.toString();
            
            _log.debug("Adding value : " + currValue + " to the to be returned getFormatedColumnData List");
            
            formatedColumnData.add(currValue);
        }
        
        _log.info("Returning list of size : " + formatedColumnData.size() + " for dataField : " + getDataField());
        return formatedColumnData;
    }
    
    public Map<String, ? super Object> updateModifiedDataField(FacesContext context, Map<String, String> requestMap, List<? extends Object> dataGridEntries) {
        
        Map<String, ? super Object> updateResult = new LinkedHashMap<String, Object>();
        boolean success = true;
        
        String requestKey = requestMap.get(REQUEST_KEYS_KEY);
        List<String> requestKeyList = Arrays.asList(requestKey.split(","));
        
        _log.info("Requested update of data with requestKey : " + requestKey + " for dataField : " + getDataField());
        
        for(String currKey : requestKeyList){
            Object currValue = requestMap.get(currKey);
            
            int rowIndex;
            
            try{
                rowIndex = Integer.parseInt(currKey);
            }catch(NumberFormatException parsingException){
                _log.error("Error parsing of " + currKey + " to an int", parsingException);
                success = false;
                break;
            }
            
            Object currDataEntry = dataGridEntries.get(rowIndex);
            success = setDataField(context, currDataEntry, currValue);
            
            _log.debug("Success result code of : " + success + " when setting value of : " + currValue + " to an instance of : " + currDataEntry.getClass().getName());
            
            if(!success){
                break;
            }
            
        }
        
        _log.info("Returning success code of : " + success + " during updateModifiedDataField of : " + getDataField());
        
        updateResult.put(RESULT_CODE_KEY, Boolean.valueOf(success));
        return updateResult;
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
    
    public synchronized Comparator<Object> getAscendingComparator() {
        if(ascendingComparator == null){
            ascendingComparator = new Comparator<Object>(){
                
                public int compare(Object obj1, Object obj2) {
                    
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
        
        return ascendingComparator;
    }
    
    public synchronized Comparator<Object> getDescendingComparator() {
        if(descendingComparator == null){
            descendingComparator = new Comparator<Object>(){
                
                public int compare(Object obj1, Object obj2) {
                    
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
        
        return descendingComparator;
    }
    
}
