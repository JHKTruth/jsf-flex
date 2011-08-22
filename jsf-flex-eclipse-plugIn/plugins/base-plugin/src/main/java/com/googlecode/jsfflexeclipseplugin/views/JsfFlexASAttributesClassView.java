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

import java.lang.ref.SoftReference;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource.JsfFlexClassAttribute;
import com.googlecode.jsfflexeclipseplugin.model.AbstractJsfFlexASAttributesClassResource;
import com.googlecode.jsfflexeclipseplugin.model.IJsfFlexASAttributesClass;
import com.googlecode.jsfflexeclipseplugin.processor.ParseActionScriptHTMLContent.CLASS_ATTRIBUTES_FIELD;
import com.googlecode.jsfflexeclipseplugin.util.JsfFlexEclipsePluginLogger;


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

	private TableViewer _viewer;
	private TableColumn _field;
	private TableColumn _description;
	
	private Action _viewAll;
	private Action _viewProperties;
	private Action _viewEvents;
	private Action _viewEffects;
	private Action _viewCommonStyles;
	private Action _viewSparkThemeStyles;
	private Action _viewHaloThemeStyles;
	private Action _clear;
	
	private JsfFlexASAttributesClassViewContentProvider _contentProvider;
	
	private static JsfFlexASAttributesClassView _currView;
	
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> ALL_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.PROPERTY, CLASS_ATTRIBUTES_FIELD.EVENT, CLASS_ATTRIBUTES_FIELD.EFFECT, 
														CLASS_ATTRIBUTES_FIELD.COMMON_STYLE, CLASS_ATTRIBUTES_FIELD.SPARK_THEME_STYLE, CLASS_ATTRIBUTES_FIELD.HALO_THEME_STYLE);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> PROPERTY_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.PROPERTY);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> EVENT_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.EVENT);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> EFFECT_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.EFFECT);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> COMMON_STYLE_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.COMMON_STYLE);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> SPARK_THEME_STYLE_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.SPARK_THEME_STYLE);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> HALO_THEME_STYLE_SET = EnumSet.of(CLASS_ATTRIBUTES_FIELD.HALO_THEME_STYLE);
	private static final EnumSet<CLASS_ATTRIBUTES_FIELD> NONE_SET = EnumSet.noneOf(CLASS_ATTRIBUTES_FIELD.class);
	
	private class JsfFlexASAttributesClassViewContentProvider implements IStructuredContentProvider {
		
		private final JsfFlexClassAttribute[] CAST_ARRAY = new JsfFlexClassAttribute[0];
		
		private EnumSet<CLASS_ATTRIBUTES_FIELD> _selectedAttributesViewSet = ALL_SET;
		private IJsfFlexASAttributesClass _jsfFlexASAttributesClass;
		private SoftReference<IJsfFlexASAttributesClass> _aggregatedClass;
		
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		
		public void dispose() {
		}
		
		private void setJsfFlexASAttributeClass(IJsfFlexASAttributesClass jsfFlexASAttributesClass) {
			_jsfFlexASAttributesClass = jsfFlexASAttributesClass;
			_aggregatedClass = null;
			
			Display.getDefault().asyncExec(new Runnable(){
				
				@Override
				public void run() {
					
					try{
						
						IWorkbenchPage pageSecond = _currView.getSite().getPage();
						IViewPart part2 = pageSecond.showView(JsfFlexASAttributesClassView.ID);
						pageSecond.activate(part2);
						
					}catch(PartInitException e) {
						
					}catch(Throwable error){
						
					}
					
					_viewer.refresh();
				}
			});
			
		}
		
		private void setSelectedAttributesViewSet(EnumSet<CLASS_ATTRIBUTES_FIELD> selectedAttributesViewSet, boolean refreshRatherThanClear) {
			_selectedAttributesViewSet = selectedAttributesViewSet;
			
			if(refreshRatherThanClear){
				_viewer.refresh();
			}else{
				_viewer.getTable().clearAll();
			}
		}
		
		public Object[] getElements(Object parent) {
			if(_jsfFlexASAttributesClass == null) {
				return CAST_ARRAY;
			}
			
			IJsfFlexASAttributesClass currAggregatedClass;
			/*
			 * When there exists a change in the org.w3c.dom.Node selection, _aggregatedClass should be set to NULL
			 * and one should invoke setJsfFlexASAttributeClass with the new instance of IJsfFlexASAttributesClass
			 */
			if(_aggregatedClass == null || (currAggregatedClass = _aggregatedClass.get()) == null) {
				currAggregatedClass = AbstractJsfFlexASAttributesClassResource.aggregateClassAttributes(_jsfFlexASAttributesClass);
				_aggregatedClass = new SoftReference<IJsfFlexASAttributesClass>(currAggregatedClass);
			}
			
			List<JsfFlexClassAttribute> jsfFlexClassAttributes = new LinkedList<JsfFlexClassAttribute>();
			for(CLASS_ATTRIBUTES_FIELD currAttributesField : _selectedAttributesViewSet) {
				currAttributesField.addClassAttributesToAggregator(currAggregatedClass, jsfFlexClassAttributes);
			}
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
			if(index == 0){
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
			}else{
				return null;
			}
		}
	}
	
	public JsfFlexASAttributesClassView() {
		super();
		
		_currView = this;
	}
	
	
	public static void jsfFlexASAttributesClassSelectionChanged(IJsfFlexASAttributesClass jsfFlexASAttributesClass) {
		
		_currView._contentProvider.setJsfFlexASAttributeClass(jsfFlexASAttributesClass);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		_viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		
		Table table = _viewer.getTable();
		
		_field = new TableColumn(table, SWT.LEFT);
		_field.setWidth(200);
		_field.setText(Messages.FIELD);
		
		_description = new TableColumn(table, SWT.LEFT);
		_description.setWidth(900);
		_description.setText(Messages.DESCRIPTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		
		_contentProvider = new JsfFlexASAttributesClassViewContentProvider();
		_viewer.setContentProvider(_contentProvider);
		_viewer.setLabelProvider(new JsfFlexASAttributesClassViewLabelProvider());
		_viewer.setInput(getViewSite());
		
		makeActions();
		hookContextMenu();
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
		Menu menu = menuMgr.createContextMenu(_viewer.getControl());
		_viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, _viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
	}
	
	private void addGenericMenus(IContributionManager contributionManager) {
		contributionManager.add(_viewAll);
		contributionManager.add(_viewProperties);
		contributionManager.add(_viewEvents);
		contributionManager.add(_viewEffects);
		contributionManager.add(_viewCommonStyles);
		contributionManager.add(_viewSparkThemeStyles);
		contributionManager.add(_viewHaloThemeStyles);
		contributionManager.add(new Separator());
		contributionManager.add(_clear);
	}
	
	private void fillLocalPullDown(IMenuManager manager) {
		addGenericMenus(manager);
	}

	private void fillContextMenu(IMenuManager manager) {
		addGenericMenus(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.M_EDIT));
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void makeActions() {
		
		_viewAll = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(ALL_SET, true);
			}
		};
		_viewAll.setText(Messages.VIEW_ALL_ATTRIBUTES);
		_viewAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewProperties = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(PROPERTY_SET, true);
			}
		};
		_viewProperties.setText(Messages.VIEW_PROPERTY_ATTRIBUTES);
		_viewProperties.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewEvents = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(EVENT_SET, true);
			}
		};
		_viewEvents.setText(Messages.VIEW_EVENT_ATTRIBUTES);
		_viewEvents.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewEffects = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(EFFECT_SET, true);
			}
		};
		_viewEffects.setText(Messages.VIEW_EFFECT_ATTRIBUTES);
		_viewEffects.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewCommonStyles = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(COMMON_STYLE_SET, true);
			}
		};
		_viewCommonStyles.setText(Messages.VIEW_COMMON_STYLE_ATTRIBUTES);
		_viewCommonStyles.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewSparkThemeStyles = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(SPARK_THEME_STYLE_SET, true);
			}
		};
		_viewSparkThemeStyles.setText(Messages.VIEW_SPARK_THEME_STYLE_ATTRIBUTES);
		_viewSparkThemeStyles.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_viewHaloThemeStyles = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(HALO_THEME_STYLE_SET, true);
			}
		};
		_viewHaloThemeStyles.setText(Messages.VIEW_HALO_THEME_STYLE_ATTRIBUTES);
		_viewHaloThemeStyles.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		_clear = new Action() {
			public void run() {
				_contentProvider.setSelectedAttributesViewSet(NONE_SET, false);
			}
		};
		_clear.setText(Messages.CLEAR_ATTRIBUTES);
		_clear.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_REMOVEALL));
		
	}
	
	public IStructuredSelection getSelection() {
		return IStructuredSelection.class.cast( _viewer.getSelection() );
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		_viewer.getControl().setFocus();
	}
	
}