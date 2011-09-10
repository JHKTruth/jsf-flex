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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ji Hoon Kim
 */
public class FlexRenderKitFactoryWrapper extends RenderKitFactory {
    
    private final static Log _log = LogFactory.getLog(FlexRenderKitFactoryWrapper.class);
    
    private static final Map<String, RenderKit> _additionalRenderKitMap = new HashMap<String, RenderKit>();
    
    private FlexRenderKitWrapper _flexRenderKit;
    private Set<String> renderKitIds;
    
    public FlexRenderKitFactoryWrapper() {
		super();
		
		RenderKit htmlBasic = getRenderKit(FacesContext.getCurrentInstance(), HTML_BASIC_RENDER_KIT);
		
		if(htmlBasic == null){
			
			try{
				Class<?> renderKitImplClass = Class.forName("com.sun.faces.renderkit.RenderKitImpl");
				htmlBasic = RenderKit.class.cast( renderKitImplClass.newInstance() );
				
				//HACK to allow Mojarra impl to work, but why is it not working properly in comparison to MyFaces
				_log.info("Hacking to instantiate HTML_BASIC_RENDER_KIT renderkit to be added to FlexRenderKitFactoryWrapper for Mojarra impl (Would be nice for it to work in the same way as MyFaces");
				if(htmlBasic != null){
					_log.info("htmlBasic is Not NULL so this should be a mojarra implementation");
					addRenderKit(HTML_BASIC_RENDER_KIT, htmlBasic);
				}
			}catch(Exception mojarraInstantiateException){
				
			}
		}
	}
    
    @Override
    public void addRenderKit(String renderKitId, RenderKit renderKit) {
        if(renderKitId == null){
            throw new NullPointerException("addRenderKit : renderKitId is null");
        }
        
        if(renderKit == null){
            throw new NullPointerException("addRenderKit : renderKit is null");
        }
        
        //HACK for now, TODO implement it better later.
        if(renderKitId.equals(FlexRenderKitWrapper.FLEX_RENDER_KIT_ID)){
            
            _flexRenderKit = FlexRenderKitWrapper.class.cast( renderKit );
            _flexRenderKit.addRenderKitSet(_additionalRenderKitMap.values());
        }else{
            
            _log.info("Adding renderKitId [ " + renderKitId + " ] as additional search renderKits for " + FlexRenderKitWrapper.FLEX_RENDER_KIT_ID);
            
            if(_flexRenderKit != null){
                _flexRenderKit.addRenderKit(renderKit);
            }
            
            _additionalRenderKitMap.put(renderKitId, renderKit);
        }
        
    }
    
    @Override
    public RenderKit getRenderKit(FacesContext context, String renderKitId) {
        if(renderKitId.equals(FlexRenderKitWrapper.FLEX_RENDER_KIT_ID)){
            return _flexRenderKit;
        }else{
            return _additionalRenderKitMap.get(renderKitId);
        }
    }
    
    @Override
    public synchronized Iterator<String> getRenderKitIds() {
        if(renderKitIds == null){
            renderKitIds = new HashSet<String>(_additionalRenderKitMap.keySet());
            renderKitIds.add(FlexRenderKitWrapper.FLEX_RENDER_KIT_ID);
        }
        return renderKitIds.iterator();
    }
    
}
