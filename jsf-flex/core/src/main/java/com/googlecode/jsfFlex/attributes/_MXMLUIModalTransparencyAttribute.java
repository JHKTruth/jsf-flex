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
package com.googlecode.jsfFlex.attributes;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFJspProperty;

/**
 * @author Ji Hoon Kim
 */
@JSFJspProperty(name="modalTransparency", returnType="java.lang.String", longDesc="Modality of components launched by the PopUp Manager is simulated by creating a large translucent overlay underneath the component. Because of the way translucent objects are rendered, you may notice a slight dimming of the objects under the overlay. The effective transparency can be set by changing the modalTransparency value from 0.0 (fully transparent) to 1.0 (fully opaque). You can also set the color of the overlay by changing the modalTransparencyColor style. The default value is 0.5.")
@JSFComponent
public interface _MXMLUIModalTransparencyAttribute {
    
}
