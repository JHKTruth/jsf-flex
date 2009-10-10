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

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderHeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHeaderStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHistoryManagementEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResizeToContentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;

/**
 * Since Accordion is written to maintain it's state [which container is chosen], it
 * will extend directly from MXMLUIInputBase and not of a Container
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlAccordion",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIAccordion",
        type                =   "com.googlecode.jsfFlex.MXMLUIAccordion",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIAccordionTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLAccordion",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIAccordion 
						extends com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase
						implements _MXMLUIContainerAttributes, _MXMLUIHeaderRendererAttribute, _MXMLUIHistoryManagementEnabledAttribute,
                        _MXMLUIResizeToContentAttribute, _MXMLUIFillAlphasAttribute, _MXMLUIFillColorsAttribute, 
                        _MXMLUIFocusAlphaAttribute, _MXMLUIFocusRoundedCornersAttribute, _MXMLUIHeaderHeightAttribute, 
                        _MXMLUIHeaderStyleNameAttribute, _MXMLUIHorizontalGapAttribute, _MXMLUIOpenDurationAttribute, 
                        _MXMLUIOpenEasingFunctionAttribute, _MXMLUISelectedFillColorsAttribute, _MXMLUITextRollOverColorAttribute, 
                        _MXMLUITextSelectedColorAttribute, _MXMLUIVerticalGapAttribute, _MXMLUIChangeAttribute {
	
	public void encodeBegin(FacesContext context) throws IOException {
		/*
		 * HACK :
		 * 	Setting creationPolicy to "all", so the components which are not
		 * 	shown by the non-selected Container would be created and can be referred 
		 * 	during the initialization/value preserving process
		 * 
		 *	I think this is the most prudent choice
		 */
        getAttributes().put("creationPolicy", "all");
		
		super.encodeBegin(context);
	}
	
}
