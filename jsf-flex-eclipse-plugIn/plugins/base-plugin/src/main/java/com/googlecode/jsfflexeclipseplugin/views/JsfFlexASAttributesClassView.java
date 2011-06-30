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
package com.googlecode.jsfflexeclipseplugin.views;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource.JsfFlexClassAttribute;
import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource;
import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 * 
 * @author Ji Hoon Kim
 */
public final class JsfFlexASAttributesClassView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.googlecode.jsfflexeclipseplugin.views.JsfFlexASAttributesClassView"; //$NON-NLS-1$

	private TableViewer viewer;
	private TableColumn field;
	private TableColumn description;
	
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	private class JsfFlexASAttributesClassViewContentProvider implements IStructuredContentProvider {
		
		private final JsfFlexClassAttribute[] CAST_ARRAY = new JsfFlexClassAttribute[0];
		
		private IJsfFlexASAttributesClass _jsfFlexASAttributeClass;
		
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			_jsfFlexASAttributeClass = IJsfFlexASAttributesClass.class.cast( newInput );
			
		}
		
		public void dispose() {
		}
		
		public Object[] getElements(Object parent) {
			IJsfFlexASAttributesClass aggregatedClass = AbstractJsfFlexASAttributesClassResource.aggregateClassAttributes(_jsfFlexASAttributeClass);
			
			List<JsfFlexClassAttribute> jsfFlexClassAttributes = new LinkedList<JsfFlexClassAttribute>(); 
			jsfFlexClassAttributes.addAll(aggregatedClass.getPropertyAttributes());
			jsfFlexClassAttributes.addAll(aggregatedClass.getEventAttributes());
			jsfFlexClassAttributes.addAll(aggregatedClass.getEffectAttributes());
			jsfFlexClassAttributes.addAll(aggregatedClass.getStyleAttribute());
			
			return jsfFlexClassAttributes.toArray(CAST_ARRAY);
		}
	}
	private class JsfFlexASAttributesClassViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		
		public String getColumnText(Object obj, int index) {
			
			JsfFlexClassAttribute currClassAttribute = JsfFlexClassAttribute.class.cast( obj );
			String columnValue = null;
			
			switch(index) {
			case 0 : columnValue = currClassAttribute.getName(); break;
			case 1 : columnValue = currClassAttribute.getDescription(); break;
			}
			
			return columnValue;
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	
	class NameSorter extends ViewerSorter {
	}
	
	public JsfFlexASAttributesClassView() {
		super();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		
		Table table = viewer.getTable();
		
		field = new TableColumn(table, SWT.LEFT);
		field.setWidth(200);
		field.setText(Messages.FIELD);
		
		description = new TableColumn(table, SWT.LEFT);
		description.setWidth(500);
		description.setText(Messages.DESCRIPTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		
		viewer.setContentProvider(new JsfFlexASAttributesClassViewContentProvider());
		viewer.setLabelProvider(new JsfFlexASAttributesClassViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				JsfFlexASAttributesClassView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.M_EDIT));
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		/*action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};*/
	}
	
	public IStructuredSelection getSelection() {
		return IStructuredSelection.class.cast( viewer.getSelection() );
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"JsfFlexAttributes", //$NON-NLS-1$
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	/**
	 * For testing purpose only
	 * @return TableViewer of the Jsf Flex Attributes View
	 */
	public TableViewer getJsfFlexAttributesViewer() {
		return viewer;
	}
	
}