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

import com.googlecode.jsfFlex.attributes._MXMLUIDataDescriptorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDefaultLeafIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDepthColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisclosureClosedIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisclosureOpenIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFirstVisibleItemAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFolderClosedIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFolderOpenIconAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIndentationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemCloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemIconsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemOpenAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemOpeningAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenItemsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRowCount;
import com.googlecode.jsfFlex.attributes._MXMLUIShowRootAttribute;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlTree",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUITree",
        type                =   "com.googlecode.jsfFlex.MXMLUITree",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUITreeTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLTree",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUITree 
						extends com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase
						implements _MXMLUIListAttributes, _MXMLUIDataDescriptorAttribute, _MXMLUIFirstVisibleItemAttribute, 
                        _MXMLUIItemIconsAttribute, _MXMLUIOpenItemsAttribute, _MXMLUIShowRootAttribute, _MXMLUIDefaultLeafIconAttribute,
                        _MXMLUIDepthColorsAttribute, _MXMLUIDisclosureClosedIconAttribute, _MXMLUIDisclosureOpenIconAttribute, 
                        _MXMLUIFolderClosedIconAttribute, _MXMLUIFolderOpenIconAttribute, _MXMLUIIndentationAttribute, 
                        _MXMLUIOpenDurationAttribute, _MXMLUIOpenEasingFunctionAttribute, _MXMLUIItemCloseAttribute, 
                        _MXMLUIItemOpenAttribute, _MXMLUIItemOpeningAttribute, _MXMLUIEditableAttribute, 
                        _MXMLUIDataProviderAttribute, _MXMLUIRowCount {
	
}
