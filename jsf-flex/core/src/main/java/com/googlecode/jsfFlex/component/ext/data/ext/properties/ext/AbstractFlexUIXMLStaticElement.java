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

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

import com.googlecode.jsfFlex.component.ext.data.AbstractFlexUIXMLContainerBase;
import com.googlecode.jsfFlex.component.ext.data.ext.properties.AbstractFlexUIXMLElementBase;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;

/**
 * Since this component is out of the norm in relation to writing Flex content, it will perform <br>
 * the write of Flex content within the component rather than within a Renderer [meaning Renderer does <br>
 * not exist for this component]. Also when stated that it is writing Flex content, it technically is <br>
 * writing to AbstractFlexUIDataContainerBase's BufferedWriter.<br>
 * 
 * <ul>
 * This component can have following types of children :
 * 		<li> AbstractFlexUIXMLStaticAttribute </li>
 * 		<li> AbstractFlexUIXMLListEntries </li>
 * </ul>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexXMLStaticElement",
        clazz               =   "com.googlecode.jsfFlex.component.ext.data.ext.properties.ext.FlexUIXMLStaticElement",
        type                =   "com.googlecode.jsfFlex.FlexUIXMLStaticElement",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.data.ext.properties.ext.FlexUIXMLStaticElementTag",
        family              =   "javax.faces.FlexProperty"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIXMLStaticElement")
public abstract class AbstractFlexUIXMLStaticElement 
						extends AbstractFlexUIXMLElementBase {
	
    @Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        if(!flexContext.isProductionEnv()){
    		Map<String, ? super UIComponentBase> temporaryResourceMap = flexContext.getTemporaryResourceMap();
    		AbstractFlexUIXMLContainerBase currXMLContainerBaseRef = AbstractFlexUIXMLContainerBase.class.cast( temporaryResourceMap.get(AbstractFlexUIXMLContainerBase.CURR_FLEX_UI_XML_CONTAINER_KEY) );
    		
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
