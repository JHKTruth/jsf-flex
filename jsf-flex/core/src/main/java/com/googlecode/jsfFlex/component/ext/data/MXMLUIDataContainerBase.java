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
package com.googlecode.jsfFlex.component.ext.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.shared.context.MxmlContext;

/**
 * @author Ji Hoon Kim
 */
public abstract class MXMLUIDataContainerBase 
						extends MXMLUISimpleBase {
	
	private static final String TO_BE_CREATED_BODY_CONTENT_FILE_SUFFIX = "BodyContent.tmp";
	
	private String _currBodyContentFilePath;
	private BufferedWriter _currBodyContentBufferedWriter;
	
	public void encodeChildren(FacesContext context) throws IOException {
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		
		_currBodyContentFilePath = mxmlContext.getPreMxmlPath() + getClass().getSimpleName() + getId() + 
										TO_BE_CREATED_BODY_CONTENT_FILE_SUFFIX;
		_currBodyContentBufferedWriter = new BufferedWriter(new FileWriter(new File(_currBodyContentFilePath)));
		
		Map<String, ? super UIComponentBase> temporaryResourceMap = mxmlContext.getTemporaryResourceMap();
		temporaryResourceMap.put(getTemporaryMapDataContainerKey(), this);
		
		super.encodeChildren(context);
		
		temporaryResourceMap.remove(getTemporaryMapDataContainerKey());
	}
	
	public boolean getRendersChildren() {
		return true;
	}
	
	protected abstract String getTemporaryMapDataContainerKey();
	
	/**
	 * Id of the component.
	 */
    @JSFProperty(
            inheritTag  =   true,
            rtexprvalue =   true,
            literalOnly =   true,
            desc        =   "Id of the component."
    )
	public String getId(){
		return super.getId();
	}
	
	public String getCurrBodyContentFilePath() {
		return _currBodyContentFilePath;
	}
	public BufferedWriter getCurrBodyContentBufferedWriter() {
		return _currBodyContentBufferedWriter;
	}
	
}
