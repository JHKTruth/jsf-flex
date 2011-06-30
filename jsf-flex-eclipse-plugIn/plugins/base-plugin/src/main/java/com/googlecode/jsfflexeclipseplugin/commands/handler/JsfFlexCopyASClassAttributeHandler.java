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
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Ji Hoon Kim
 */
public class JsfFlexCopyASClassAttributeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Clipboard clipboard = new Clipboard(HandlerUtil.getActiveShell(event).getDisplay());
		try{
			ISelection selection = HandlerUtil.getCurrentSelection(event);
			if(selection instanceof IStructuredSelection) {
				Object[] objects = IStructuredSelection.class.cast(selection).toArray();
				try{
					clipboard.setContents(new Object[] { JsfFlexASAttributesClassHandlerUtil.convertToResources(objects), 
														JsfFlexASAttributesClassHandlerUtil.convertToText(objects) }, 
														new Transfer[] { Transfer.class.cast( RTFTransfer.getInstance() ), Transfer.class.cast( TextTransfer.getInstance() )});
				} catch(SWTError error){
					
				}
			}
			
			return null;
		}finally {
			clipboard.dispose();
		}
		
	}
	
}
