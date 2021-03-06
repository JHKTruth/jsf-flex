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
package com.googlecode.jsfflexeclipseplugin.commands.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Node;

import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;
import com.googlecode.jsfflexeclipseplugin.model.JsfFlexCacheManager;
import com.googlecode.jsfflexeclipseplugin.processor.ParseActionScriptHTMLContent;
import com.googlecode.jsfflexeclipseplugin.util.JsfFlexEclipsePluginConstants;

/**
 * @author Ji Hoon Kim
 */
public class JsfFlexAddASAttributesClassHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = IStructuredSelection.class.cast( selection );
			Object element = structuredSelection.getFirstElement();
			if(element instanceof Node){
				Node node = Node.class.cast(element);
				String nodeUrl = node.getNamespaceURI();
				if(nodeUrl != null && nodeUrl.trim().equals(JsfFlexEclipsePluginConstants.JSF_FLEX_URL_NAMESPACE)){
					String nodeName = node.getNodeName();
					int indexPoint = nodeName.indexOf(JsfFlexEclipsePluginConstants.JSF_FLEX_TAG_START_PREFIX);
					if(indexPoint > -1){
						String flexCompName = nodeName.substring(nodeName.indexOf(JsfFlexEclipsePluginConstants.JSF_FLEX_TAG_START_PREFIX) + JsfFlexEclipsePluginConstants.JSF_FLEX_TAG_START_PREFIX.length());
						String nameSpaceOverride = null;
						Node nameSpaceOverrideNode = node.getAttributes().getNamedItem(JsfFlexEclipsePluginConstants.NAME_SPACE_OVERRIDE_ATTR);
						
						if(nameSpaceOverrideNode != null){
							nameSpaceOverride = nameSpaceOverrideNode.getNodeValue();
						}
						String packageClassName = ParseActionScriptHTMLContent.getPackageClassName(flexCompName, nameSpaceOverride);
						if(packageClassName != null){
							JsfFlexCacheManager currInstance = JsfFlexCacheManager.getInstance();
							if(!currInstance.containsPackageClassName(packageClassName)){
								IJsfFlexASAttributesClass topJsfFlexASAttributesClass = JsfFlexCacheManager.getJsfFlexASAttributesClass(packageClassName, packageClassName, node);
								ParseActionScriptHTMLContent parserActionScriptHTMLContent = new ParseActionScriptHTMLContent(topJsfFlexASAttributesClass);
								parserActionScriptHTMLContent.schedule();
							}
						}
					}
					
				}
			}
			
		}
		
		return null;
	}
	
}
