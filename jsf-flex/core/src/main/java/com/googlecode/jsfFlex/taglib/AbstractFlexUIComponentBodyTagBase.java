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
package com.googlecode.jsfFlex.taglib;

import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * This tag captures the contents of the JSP tag as an attribute of FlexConstants.TAG_BODY_CONTENT_ATTR.<br>
 * This will enable writing of ActionScript contents as body of mxmlScript JSP tag.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class AbstractFlexUIComponentBodyTagBase extends UIComponentELTag {
	
	private String _scriptContent;
	
	public int doStartTag() throws JspException
    {
        super.doStartTag();
        return BodyTag.EVAL_BODY_BUFFERED;
    }
	
	public int doAfterBody() throws JspException
    {
		BodyContent bodyContent = getBodyContent();
        if (bodyContent != null)
        {
        	setScriptContent(bodyContent.getString());
        	bodyContent.clearBody();
        	getComponentInstance().getAttributes().put(FlexConstants.TAG_BODY_CONTENT_ATTR, _scriptContent);
        }
        return super.doAfterBody();
    }
	
	public void release() {
		super.release();
		
		_scriptContent = null;
		
	}
	
	public String getScriptContent() {
		return _scriptContent;
	}
	public void setScriptContent(String scriptContent) {
		_scriptContent = scriptContent;
	}
	
}
