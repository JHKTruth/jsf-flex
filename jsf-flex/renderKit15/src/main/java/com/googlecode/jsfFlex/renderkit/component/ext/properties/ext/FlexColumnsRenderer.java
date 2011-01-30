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
package com.googlecode.jsfFlex.renderkit.component.ext.properties.ext;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

import com.googlecode.jsfFlex.component.ext.AbstractFlexUIDataGrid;
import com.googlecode.jsfFlex.component.ext.AbstractFlexUIDataGridColumn;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent.ACTION_SCRIPT_IMPORT;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderer(
        renderKitId="FLEX_BASIC",
        family="javax.faces.FlexSimple",
        type="com.googlecode.jsfFlex.FlexColumns"
)
@FacesRenderer(
        renderKitId="FLEX_BASIC",
        componentFamily="javax.faces.FlexSimple",
        rendererType="com.googlecode.jsfFlex.FlexColumns"
)
@IJsfFlexAttributeProperties(componentName="columns")
public final class FlexColumnsRenderer extends AbstractFlexComponentBaseRenderer {
    
    private static final String INVALID_CHILD_COMPONENT = "Invalid Child Component : FlexUIColumns can only have subclass of following abstract classes [ AbstractFlexUIDataGridColumn ] as its children";
    private static final String INVALID_PARENT_COMPONENT = "Invalid Parent Component : FlexUIColumns can only have subclass of following abstract classes [ AbstractFlexUIDataGrid ] as its parent";
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeBegin(context, componentObj);
        
        IFlexContract componentFlex = IFlexContract.class.cast( componentObj );
        
        AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
        writer.createPreMxml(componentFlex, FlexColumnsRenderer.class.getAnnotation(IJsfFlexAttributeProperties.class), 
                null);
        
    }
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
        super.encodeEnd(context, componentObj);
        
        UIComponent dataGridComponent = componentObj.getParent();
        String dataGridComponentId = dataGridComponent.getId();
        if(!(dataGridComponent instanceof AbstractFlexUIDataGrid)){
            throw new ComponentBuildException(INVALID_PARENT_COMPONENT);
        }
        
        AbstractFlexUIDataGrid dataGrid = AbstractFlexUIDataGrid.class.cast( dataGridComponent );
        if(dataGrid.getBindingBeanList() == null){
            return;
        }
        
        List<UIComponent> childrenList = componentObj.getChildren();
        
        if(childrenList.size() > 0){
            AbstractFlexContext mxmlContext = AbstractFlexContext.getCurrentInstance();
            AdditionalApplicationScriptContent additionalAppScriptContent = mxmlContext.getAdditionalAppScriptContent();
            additionalAppScriptContent.addDataGridScriptContent(dataGridComponentId, dataGrid.computeBatchColumnDataRetrievalSize(), 
                                                                    dataGrid.computeMaxDataPartitionIndex(), dataGrid.getFilterComponentId(),
                                                                    dataGrid.getFilterColumnComponentId(), dataGrid.getFilterEventListener());
            additionalAppScriptContent.addActionScriptImport(ACTION_SCRIPT_IMPORT.DATA_GRID_SERVICE_REQUEST_AS);
            
            for(UIComponent currChild : childrenList){
                if(!(currChild instanceof AbstractFlexUIDataGridColumn)){
                    throw new ComponentBuildException(INVALID_CHILD_COMPONENT);
                }
                
                AbstractFlexUIDataGridColumn currChildInstance = AbstractFlexUIDataGridColumn.class.cast( currChild );
                additionalAppScriptContent.addDataGridColumnToDataGridScriptContent(dataGridComponentId, currChildInstance.getId(), 
                                                        currChildInstance.getDataField(), Boolean.valueOf(currChildInstance.getEditable()));
            }
            
        }
        
    }
    
}
