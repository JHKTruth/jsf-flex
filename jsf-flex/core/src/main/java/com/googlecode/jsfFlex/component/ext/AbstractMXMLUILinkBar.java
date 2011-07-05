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

import com.googlecode.jsfFlex.component.MXMLUISimpleBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDirectionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIGapAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIItemClickAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIToolTipFieldAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIContainerAttributes;

/**
 * @JSFComponent
 *   name     = "jf:mxmlLinkBar"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUILinkBar"
 *   type     = "com.googlecode.jsfFlex.MXMLUILinkBar"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUILinkBarTag"
 *   family   = "javax.faces.MXMLSimple"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLLinkBar"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "separatorColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Separator color used by the default separator skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "separatorSkin"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Seperator symbol between LinkButton controls in the LinkBar."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "separatorWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Separator pixel width, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "rollOverColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The rollOverColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectionColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textSelectedColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Text color of the label as the user presses it."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textRollOverColor"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Text color of the label as the user moves the mouse pointer over the button."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUILinkBar 
						extends MXMLUISimpleBase 
						implements _MXMLUIToolTipFieldAttribute, _MXMLUIBaseAttributes, _MXMLUIIconFieldAttribute,
						_MXMLUIDataProviderAttribute, _MXMLUILabelFieldAttribute, _MXMLUISelectedIndexAttribute,
						_MXMLUIItemClickAttribute, _MXMLUIDirectionAttribute, _MXMLUIHorizontalAlignAttribute, 
						_MXMLUIGapAttributes, _MXMLUIVerticalAlignAttribute, _MXMLUIContainerAttributes,
						_MXMLUIScrollAttributes, _MXMLUIHorizontalScrollPositionAttribute, _MXMLUIIconAttribute,
						_MXMLUILabelAttribute, _MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundColorAttribute,
						_MXMLUIBackgroundDisabledColorAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBarColorAttribute,
						_MXMLUIBorderColorAttribute, _MXMLUIBorderAttributes, _MXMLUIBorderThicknessAttribute, 
						_MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, _MXMLUIDisabledColorAttribute, 
						_MXMLUIShadowAttributes, _MXMLUIFontSpecificAttributes, _MXMLUIFontFamilyAttribute, 
						_MXMLUIFontGeneralAttributes, _MXMLUIScrollBarAttributes, _MXMLUIPaddingHorizontalAttributes,
						_MXMLUIPaddingVerticalAttributes, _MXMLUITextStyleAttributes, _MXMLUIDataChangeAttribute, 
						_MXMLUIScrollAttribute {
	
}
