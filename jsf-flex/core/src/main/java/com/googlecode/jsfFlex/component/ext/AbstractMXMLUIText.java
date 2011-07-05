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

import com.googlecode.jsfFlex.component.MXMLUIOutputBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHtmlTextAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectableAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITruncateToFitAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlText"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIText"
 *   type     = "com.googlecode.jsfFlex.MXMLUIText"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUITextTag"
 *   family   = "javax.faces.MXMLOutput"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLText"
 *   
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIText 
						extends MXMLUIOutputBase
						implements _MXMLUITruncateToFitAttribute, _MXMLUIBaseAttributes, _MXMLUIColorAttribute, 
						_MXMLUIDataAttributes, _MXMLUIDataChangeAttribute, _MXMLUIDisabledColorAttribute, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIFontSpecificAttributes, 
						_MXMLUIHtmlTextAttribute, _MXMLUIPaddingHorizontalAttributes, _MXMLUISelectableAttribute, 
						_MXMLUITextAttribute, _MXMLUITextStyleAttributes {
	
}
