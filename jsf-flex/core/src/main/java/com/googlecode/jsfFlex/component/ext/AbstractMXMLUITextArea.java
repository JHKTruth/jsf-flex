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

import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICondenseWhiteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisplayAsPasswordAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIListDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIMaxCharsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRestrictAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionBeginIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionEndIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStyleSheetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWordWrapAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlTextArea",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUITextArea",
        type                =   "com.googlecode.jsfFlex.MXMLUITextArea",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUITextAreaTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLTextArea",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUITextArea 
						extends com.googlecode.jsfFlex.component.MXMLUIHtmlTextInputBase 
						implements _MXMLUIScrollControlAttributes, _MXMLUICondenseWhiteAttribute, _MXMLUIDataAttribute, 
                        _MXMLUIDisplayAsPasswordAttribute, _MXMLUIEditableAttribute, _MXMLUIImeModeAttribute, 
                        _MXMLUIListDataAttribute, _MXMLUIMaxCharsAttribute, _MXMLUIRestrictAttribute, 
                        _MXMLUISelectionBeginIndexAttribute, _MXMLUISelectionEndIndexAttribute, _MXMLUIStyleSheetAttribute, 
                        _MXMLUIWordWrapAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIFocusAlphaAttribute, 
                        _MXMLUIFocusRoundedCornersAttribute, _MXMLUIPaddingLeftAttribute, _MXMLUIPaddingRightAttribute, 
                        _MXMLUIChangeAttribute {
	
}
