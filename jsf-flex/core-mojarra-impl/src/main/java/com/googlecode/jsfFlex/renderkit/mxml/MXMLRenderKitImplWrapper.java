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
package com.googlecode.jsfFlex.renderkit.mxml;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @JSFRenderKit
 *   renderKitId = "MXML_BASIC"
 * 
 * @author Ji Hoon Kim
 */
public class MXMLRenderKitImplWrapper extends MXMLRenderKitImpl {
	
	private final static Log _log = LogFactory.getLog(MXMLRenderKitImplWrapper.class);
	
	private final List _additionalRenderKits;
	
    public MXMLRenderKitImplWrapper(){
		super();
		_additionalRenderKits = new LinkedList();
	}
	
	public Renderer getRenderer(String family, String rendererType) {
		Renderer renderer = super.getRenderer(family, rendererType);
		
		if(renderer == null){
			//simple HACK where to search for remaining renderKits, TODO implement it better later
			
			for(Iterator iterator = _additionalRenderKits.iterator(); iterator.hasNext();){
				
				RenderKit currRenderKit = (RenderKit) iterator.next();
				renderer = currRenderKit.getRenderer(family, rendererType);
				
				if(renderer != null){
					_log.info("Found Renderer of family [ " + family + " ], rendererType [ " + rendererType + " ] within RenderKit " + currRenderKit.getClass().getSimpleName());
					break;
				}
			}
			
		}
		
		return renderer;
	}
	
	public void addRenderKit(RenderKit renderKit){
		_additionalRenderKits.add(renderKit);
	}
	
	public void addRenderKitList(List renderKits){
		_additionalRenderKits.addAll(renderKits);
	}
	
}
