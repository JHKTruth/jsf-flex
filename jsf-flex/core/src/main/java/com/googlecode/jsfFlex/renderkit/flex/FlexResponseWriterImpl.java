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
package com.googlecode.jsfFlex.renderkit.flex;

import java.io.Writer;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

/**
 * @author Ji Hoon Kim
 */
class FlexResponseWriterImpl extends AbstractFlexResponseWriter {
    
    private final ResponseWriter _basicWriter;
    private final String _selectedContentType;
    private final String _characterEncoding;
    
    private FlexResponseWriterImpl(){
        super();
        
        _basicWriter = null;
        _selectedContentType = null;
        _characterEncoding = null;
    }
    
    FlexResponseWriterImpl(Writer writer, String selectedContentType, String characterEncoding){
        super();
        
        _selectedContentType = selectedContentType;
        _characterEncoding = characterEncoding;
        
        RenderKitFactory factory = RenderKitFactory.class.cast( FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY) );
        RenderKit basicHTMLRenderKit = factory.getRenderKit(FacesContext.getCurrentInstance(), FlexRenderKitWrapper.BASIC_HTML_RENDER_KIT_ID);
        _basicWriter = basicHTMLRenderKit.createResponseWriter(writer, selectedContentType, characterEncoding);
        
    }
    
    @Override
    public ResponseWriter cloneWithWriter(Writer writer) {
        return new FlexResponseWriterImpl(writer, _selectedContentType, _characterEncoding);
    }
    
    @Override
    public ResponseWriter getWrapped() {
        return _basicWriter;
    }
    
}
