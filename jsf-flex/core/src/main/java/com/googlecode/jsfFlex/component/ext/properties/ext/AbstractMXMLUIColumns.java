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
package com.googlecode.jsfFlex.component.ext.properties.ext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.component.ext.AbstractMXMLUIDataGrid;
import com.googlecode.jsfFlex.component.ext.AbstractMXMLUIDataGridColumn;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlColumns",
        clazz               =   "com.googlecode.jsfFlex.component.ext.properties.ext.MXMLUIColumns",
        type                =   "com.googlecode.jsfFlex.MXMLUIColumns",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.properties.MXMLUIColumnsTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLColumns"
)
public abstract class AbstractMXMLUIColumns 
                        extends MXMLUISimpleBase {
    
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        
        AbstractMXMLUIDataGrid dataGridComponent = (AbstractMXMLUIDataGrid) getParent();
        Map<String, AbstractMXMLUIDataGridColumn> dataGridColumnComponentMapping = dataGridComponent.getDataGridColumnComponentMapping();
        
        List<UIComponent> dataGridColumnComponents = getChildren();
        for(UIComponent currChild : dataGridColumnComponents){
            AbstractMXMLUIDataGridColumn currDataGridColumnComponent = (AbstractMXMLUIDataGridColumn) currChild;
            dataGridColumnComponentMapping.put(currDataGridColumnComponent.getDataField(), currDataGridColumnComponent);
        }
        
    }
    
}
