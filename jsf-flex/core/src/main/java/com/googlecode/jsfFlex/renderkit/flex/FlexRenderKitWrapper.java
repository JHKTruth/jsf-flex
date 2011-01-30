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

import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderKit;

/**
 * @author Ji Hoon Kim
 */
@JSFRenderKit(renderKitId   =   "FLEX_BASIC")
public class FlexRenderKitWrapper extends RenderKit {
    
    static final String BASIC_HTML_RENDER_KIT_ID = "HTML_BASIC";
    static final String FLEX_RENDER_KIT_ID = "FLEX_BASIC";
    
    private final static Log _log = LogFactory.getLog(FlexRenderKitWrapper.class);
    private static final String DEFAULT_CHAR_ENCODING = "ISO-8859-1";
    
    private final Collection<RenderKit> _additionalRenderKits;
    private final Map<String, Map<String, Renderer>> _renderers;
    
    private RenderKit basicHTML;
    
    {
        _additionalRenderKits = new LinkedHashSet<RenderKit>();
        _renderers = new HashMap<String, Map<String, Renderer>>();
        
        RenderKitFactory factory = RenderKitFactory.class.cast( FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY) );
        basicHTML = factory.getRenderKit(FacesContext.getCurrentInstance(), BASIC_HTML_RENDER_KIT_ID);
    }
    
    @Override
    public Renderer getRenderer(String family, String rendererType) {
        
        Renderer renderer = null;
        Map<String, Renderer> rendererMap = _renderers.get(family);
        if(rendererMap != null){
            renderer = rendererMap.get(rendererType);
        }
        
        if(renderer == null){
            //simple HACK where to search for remaining renderKits, TODO implement it better later
            
            for(RenderKit currRenderKit : _additionalRenderKits){
                
                renderer = currRenderKit.getRenderer(family, rendererType);
                
                if(renderer != null){
                    _log.info("Found Renderer of family [ " + family + " ], rendererType [ " + rendererType + " ] within RenderKit " + currRenderKit.getClass().getSimpleName());
                    break;
                }
            }
            
        }
        
        return renderer;
    }
    
    @Override
    public void addRenderer(String family, String rendererType, Renderer renderer) {
        if(family == null){
            throw new NullPointerException("addRenderer: component family must not be null");
        }
        if(rendererType == null){
            throw new NullPointerException("addRenderer: rendererType must not be null");
        }
        
        Map<String, Renderer> rendererTypeMap;
        /*
         * Since there exists many rendererType for a single family
         */
        if((rendererTypeMap = _renderers.get(family)) == null){
            rendererTypeMap = new HashMap<String, Renderer>();
            _renderers.put(family, rendererTypeMap);
        }
        
        rendererTypeMap.put(rendererType, renderer);
        
    }

    @Override
    public ResponseStream createResponseStream(OutputStream outPutStream) {
        return basicHTML.createResponseStream(outPutStream);
    }
    
    @Override
    public ResponseStateManager getResponseStateManager() {
        return basicHTML.getResponseStateManager();
    }
    
    @Override
    public ResponseWriter createResponseWriter(Writer writer, String contentTypeListString, String characterEncoding){
        String selectedContentType = FlexRenderKitImplHelper.selectContentType(contentTypeListString);
        
        if(characterEncoding==null){
            characterEncoding = DEFAULT_CHAR_ENCODING;
        }
        
        AbstractFlexResponseWriter flexResponseWriter = new FlexResponseWriterImpl(writer, selectedContentType, characterEncoding);
        
        return flexResponseWriter;
    }
    
    public void addRenderKit(RenderKit renderKit){
        _additionalRenderKits.add(renderKit);
    }
    
    public void addRenderKitSet(Collection<RenderKit> renderKits){
        _additionalRenderKits.addAll(renderKits);
    }
    
    private static final class FlexRenderKitImplHelper {
        
        private static final String HTML_CONTENT_TYPE = "text/html";
        private static final String TEXT_ANY_CONTENT_TYPE = "text/*";
        private static final String ANY_CONTENT_TYPE = "*/*";
        
        private static final String XHTML_CONTENT_TYPE = "application/xhtml+xml";
        private static final String APPLICATION_XML_CONTENT_TYPE = "application/xml";
        private static final String TEXT_XML_CONTENT_TYPE = "text/xml";
        
        private static final List<String> _htmlAcceptContentType;
        private static final List<String> _xhtmlAcceptContentType;
        
        static{
            _htmlAcceptContentType = Arrays.asList(new String[]{HTML_CONTENT_TYPE, TEXT_ANY_CONTENT_TYPE, ANY_CONTENT_TYPE});
            
            _xhtmlAcceptContentType = Arrays.asList(new String[]{XHTML_CONTENT_TYPE, APPLICATION_XML_CONTENT_TYPE, TEXT_XML_CONTENT_TYPE});
        }
        
        private static final String selectContentType(String contentTypeListString){
            
            if(contentTypeListString == null){
                FacesContext currFacesContext = FacesContext.getCurrentInstance();
                
                if(currFacesContext != null){
                    //if passed in is null, try to fetch it from the requestMap
                    contentTypeListString = String.class.cast( currFacesContext.getExternalContext().getRequestHeaderMap().get("Accept") );
                }
                
                if(contentTypeListString == null){
                    throw new NullPointerException("Content Type Listing passed into createResponseWriter and within RequestHeaderMap is null!");
                }
                
            }
            
            List<String> contentTypeList = Arrays.asList(contentTypeListString.replaceAll("[;\\s]", "").split(","));
            //Always search first as htmlAcceptContentType
            
            String contentType = null;
            for(String currentContentType : contentTypeList){
                
                if(_htmlAcceptContentType.contains(currentContentType)){
                    contentType = HTML_CONTENT_TYPE;
                    break;
                }else if(_xhtmlAcceptContentType.contains(currentContentType)){
                    contentType = XHTML_CONTENT_TYPE;
                    break;
                }
                
            }
            
            if(contentType == null){
                throw new NullPointerException("Content type : " + contentTypeListString + " is not one of the supported content Type");
            }
            
            return contentType;
        }
        
    }
    
}
