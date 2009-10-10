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

/**
 * Since this component is out of the norm in relation to writing MXML content, it will perform <br>
 * the write of MXML content within the component rather than within a Renderer [meaning Renderer does <br>
 * not exist for this component]. Also when stated that it is writing MXML content, it technically is <br>
 * writing to MXMLUIDataContainerBase's BufferedWriter.<br>
 * 
 * <ul>
 * This component can have following types of children :
 * 		<li> AbstractMXMLUIXMLStaticAttribute </li>
 * 		<li> AbstractMXMLUIXMLListEntries </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlXMLStaticElement",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.MXMLUIXMLStaticElement",
        type                =   "com.googlecode.jsfFlex.MXMLUIXMLStaticElement",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.data.properties.MXMLUIXMLStaticElementTag",
        family              =   "javax.faces.MXMLProperty"
)
public abstract class AbstractMXMLUIXMLStaticElement 
						extends MXMLUIXMLElementBase {
	
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		Map<String, ? super UIComponentBase> temporaryResourceMap = mxmlContext.getTemporaryResourceMap();
		MXMLUIXMLContainerBase currXMLContainerBaseRef = (MXMLUIXMLContainerBase) temporaryResourceMap.get(MXMLUIXMLContainerBase.CURR_MXML_UI_XML_CONTAINER_KEY);
		
		StringBuilder xmlElementStartTagBuffer = new StringBuilder();
		
		xmlElementStartTagBuffer.append("<");
		xmlElementStartTagBuffer.append(getStaticNodeName());
		
		xmlElementStartTagBuffer.append( processDataObjectProperties() );
		
		xmlElementStartTagBuffer.append(">");
		
		//now need to set xml element's end tag
		StringBuilder xmlElementEndTagBuffer = new StringBuilder();
		xmlElementEndTagBuffer.append(getStaticNodeValue() == null ? "" : getStaticNodeValue());
		xmlElementEndTagBuffer.append("</");
		xmlElementEndTagBuffer.append(getStaticNodeName());
		xmlElementEndTagBuffer.append(">");
		_xmlElementEndTag = xmlElementEndTagBuffer.toString();
		
		//now the start tag has been generated so write to the buffer
		currXMLContainerBaseRef.getCurrBodyContentBufferedWriter().write(xmlElementStartTagBuffer.toString());
		
	}
	
	/**
	 * Static name of the node.
	 */
    @JSFProperty(
            required    =   true,
            desc        =   "Static name of the node."
    )
	public abstract String getStaticNodeName();
	
	/**
	 * Static value of the node.
	 */
    @JSFProperty(desc        =   "Static value of the node.")
	public abstract String getStaticNodeValue();
	
}
