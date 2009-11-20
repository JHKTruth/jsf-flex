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
package com.googlecode.jsfFlex.component.ext.data.ext.properties.ext;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.component.ext.data.MXMLUIXMLContainerBase;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.MXMLUIXMLElementBase;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.util.ReflectionHelperUtil;

/**
 * Since this component is out of the norm in relation to writing MXML content, it will perform <br>
 * the write of MXML content within the component rather than within a Renderer [meaning Renderer does <br>
 * not exist for this component]. Also when stated that it is writing MXML content, it technically is <br>
 * writing to MXMLUIDataContainerBase's BufferedWriter. <br>
 * 
 * <ul>
 * This component can have following type of children :
 * 		<li> AbstractMXMLUIXMLAttribute </li>
 * 		<li> AbstractMXMLUIXMLStaticAttribute </li>
 * 		<li> AbstractMXMLUIXMLListEntries </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlXMLElement",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.MXMLUIXMLElement",
        type                =   "com.googlecode.jsfFlex.MXMLUIXMLElement",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.data.ext.properties.ext.MXMLUIXMLElementTag",
        family              =   "javax.faces.MXMLProperty"
)
public abstract class AbstractMXMLUIXMLElement 
						extends MXMLUIXMLElementBase {
	
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		Map<String, ? super UIComponentBase> temporaryResourceMap = mxmlContext.getTemporaryResourceMap();
		MXMLUIXMLContainerBase currXMLContainerBaseRef = MXMLUIXMLContainerBase.class.cast( temporaryResourceMap.get(MXMLUIXMLContainerBase.CURR_MXML_UI_XML_CONTAINER_KEY) );
		
		StringBuilder xmlElementStartTagBuffer = new StringBuilder();
		
		xmlElementStartTagBuffer.append( processXMLTagDynamically(currXMLContainerBaseRef) );
		
		//now the start tag has been generated so write to the buffer
		currXMLContainerBaseRef.getCurrBodyContentBufferedWriter().write(xmlElementStartTagBuffer.toString());
		
	}
	
	private String processXMLTagDynamically(MXMLUIXMLContainerBase currXMLListRef){
		
		final String GET_NODE_NAME_METHOD_NAME = getNodeName() != null ? "get" + getNodeName().substring(0, 1).toUpperCase() + getNodeName().substring(1) : null;
		final String GET_NODE_VALUE_METHOD_NAME = getNodeValue() != null ? "get" + getNodeValue().substring(0, 1).toUpperCase() + getNodeName().substring(1) : null;
		
		StringBuilder xmlElementStartTagBuffer = new StringBuilder();
		StringBuilder xmlElementEndTagBuffer = new StringBuilder();
		
		try{
			String nodeName = ReflectionHelperUtil.getValue(getCurrBeanRef(), GET_NODE_NAME_METHOD_NAME).toString();
			String nodeValue = "";
			
			if(GET_NODE_VALUE_METHOD_NAME != null){
				nodeValue = ReflectionHelperUtil.getValue(getCurrBeanRef(), GET_NODE_VALUE_METHOD_NAME).toString();
			}
			
			xmlElementStartTagBuffer.append("<");
			xmlElementStartTagBuffer.append(nodeName);
			
			xmlElementStartTagBuffer.append( processDataObjectProperties() );
			
			xmlElementStartTagBuffer.append(">");
			
			//below content will be written within encodeEnd
			xmlElementEndTagBuffer.append(nodeValue);
			xmlElementEndTagBuffer.append("</");
			xmlElementEndTagBuffer.append(nodeName);
			xmlElementEndTagBuffer.append(">");
			_xmlElementEndTag = xmlElementEndTagBuffer.toString();
			
		}catch(Exception reflectionException){
			throw new ComponentBuildException("Exception was triggered while invoking " + GET_NODE_NAME_METHOD_NAME  + " or " + GET_NODE_VALUE_METHOD_NAME, 
					reflectionException);
		}
		
		return xmlElementStartTagBuffer.toString();
	}
	
	/**
	 * Name of the node which will be fetched dynamically using reflection. This field should be a static string representing the name of the field within the binding bean.
	 */
    @JSFProperty(
            required    =   true,
            desc        =   "Name of the node which will be fetched dynamically using reflection. This field should be a static string representing the name of the field within the binding bean."
    )
	public abstract String getNodeName();
	
	/**
	 * Value of the node which will be fetched dynamically using reflection. This field should be a static string representing the value of the field within the binding bean.
	 */
    @JSFProperty(desc        =   "Value of the node which will be fetched dynamically using reflection. This field should be a static string representing the value of the field within the binding bean.")
	public abstract String getNodeValue();
	
}
