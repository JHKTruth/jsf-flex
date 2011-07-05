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
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.googlecode.jsfFlex.component.ext.AbstractMXMLUIDataGrid;
import com.googlecode.jsfFlex.component.ext.AbstractMXMLUIDataGridColumn;
import com.googlecode.jsfFlex.renderkit.component.MXMLComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * @JSFRenderer
 *  renderKitId = "MXML_BASIC" 
 *  family      = "javax.faces.MXMLSimple"
 *  type        = "com.googlecode.jsfFlex.MXMLColumns"
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLColumnsRenderer extends MXMLComponentBaseRenderer {
	
	private static final String INVALID_CHILD_COMPONENT = "Invalid Child Component : MXMLUIColumns can only have subclass of following abstract classes [ AbstractMXMLUIDataGridColumn ] as its children";
	private static final String INVALID_PARENT_COMPONENT = "Invalid Parent Component : MXMLUIColumns can only have subclass of following abstract classes [ AbstractMXMLUIDataGrid ] as its parent";
	
	private static final String DATA_GRID_SERVICE_REQUEST_IMPORT = "com.googlecode.jsfFlex.communication.component.DataGridServiceRequest";
	
	private static final String MXML_COMPONENT_NAME = "columns";
	
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		_MXMLContract componentMXML = (_MXMLContract) componentObj;
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		writer.createPreMxml(componentMXML, MXML_COMPONENT_NAME, null);
		
	}
	
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeEnd(context, componentObj);
		
		UIComponent dataGridComponent = componentObj.getParent();
		String dataGridComponentId = dataGridComponent.getId();
		if(!(dataGridComponent instanceof AbstractMXMLUIDataGrid)){
			throw new ComponentBuildException(INVALID_PARENT_COMPONENT);
		}
		
		AbstractMXMLUIDataGrid dataGrid = (AbstractMXMLUIDataGrid) dataGridComponent;
		if(dataGrid.getBindingBeanList() == null){
			return;
		}
		
		List childrenList = componentObj.getChildren();
		
		if(childrenList.size() > 0){
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			AdditionalApplicationScriptContent additionalAppScriptContent = mxmlContext.getAdditionalAppScriptContent();
			additionalAppScriptContent.addDataGridScriptContent(dataGridComponentId, dataGrid.computeBatchColumnDataRetrievalSize(), dataGrid.computeMaxDataPartitionIndex());
			additionalAppScriptContent.addActionScriptImport(DATA_GRID_SERVICE_REQUEST_IMPORT);
			
			for(Iterator iterate = childrenList.iterator(); iterate.hasNext();){
				UIComponent currChild = (UIComponent) iterate.next();
				if(!(currChild instanceof AbstractMXMLUIDataGridColumn)){
					throw new ComponentBuildException(INVALID_CHILD_COMPONENT);
				}
				
				AbstractMXMLUIDataGridColumn currChildInstance = (AbstractMXMLUIDataGridColumn) currChild;
				additionalAppScriptContent.addDataGridColumnToDataGridScriptContent(dataGridComponentId, currChildInstance.getId(), 
														currChildInstance.getDataField(), Boolean.valueOf(currChildInstance.getEditable()));
			}
			
		}
		
	}
	
}
