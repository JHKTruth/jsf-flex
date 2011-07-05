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

import com.googlecode.jsfFlex.attributes._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICondenseWhiteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIListDataAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIStyleSheetAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITruncateToFitAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent
interface _MXMLUILabelAttributes 
            extends _MXMLUICondenseWhiteAttribute, _MXMLUIDataAttribute, _MXMLUIListDataAttribute, _MXMLUISelectableAttribute, 
            _MXMLUITruncateToFitAttribute, _MXMLUIColorAttribute, _MXMLUIDisabledColorAttribute, _MXMLUIFontAntiAliasTypeAttribute, 
            _MXMLUIFontFamilyAttribute, _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute,
            _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, _MXMLUIPaddingLeftAttribute, 
            _MXMLUIPaddingRightAttribute, _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
            _MXMLUIDataChangeAttribute, _MXMLUIPaddingTopAttribute, _MXMLUIPaddingBottomAttribute, _MXMLUIStyleSheetAttribute, 
            _MXMLUIBaseAttributes {
    
    String getHtmlText();
    
    void setHtmlText(String htmlText);
    
    String getText();
    
    void setText(String text);
    
}
