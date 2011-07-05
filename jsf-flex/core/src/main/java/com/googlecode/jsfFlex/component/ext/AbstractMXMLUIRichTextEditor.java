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

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIDefaultLinkProtocolAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowControlBarAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowToolTipsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITitleAttribute;
import com.googlecode.jsfFlex.container.ext._MXMLUIPanelAttributes;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlRichTextEditor",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIRichTextEditor",
        type                =   "com.googlecode.jsfFlex.MXMLUIRichTextEditor",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIRichTextEditorTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLRichTextEditor",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIRichTextEditor 
						extends com.googlecode.jsfFlex.component.MXMLUIHtmlTextInputBase 
						implements _MXMLUIPanelAttributes, _MXMLUIDefaultLinkProtocolAttribute, _MXMLUIShowControlBarAttribute, 
                        _MXMLUIShowToolTipsAttribute, _MXMLUITitleAttribute {
	
}
