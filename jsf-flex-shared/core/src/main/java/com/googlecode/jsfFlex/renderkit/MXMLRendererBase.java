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
package com.googlecode.jsfFlex.renderkit;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public class MXMLRendererBase extends Renderer {
	
	public static final String MAJOR_MINOR_DELIM = "-";
	private static final String PRE_MXML_FILE_NAME_DELIM = "_";
	
	private MXMLRendererBaseHelper _mxmlRendererBaseHelper;
	
	{
		_mxmlRendererBaseHelper = new MXMLRendererBaseHelper();
	}
	
	private static final Comparator<? super _MXMLContract> _majorKeyComparator = new Comparator<_MXMLContract>(){
		public int compare(_MXMLContract actObj1, _MXMLContract actObj2){
			double actObj1Double = Double.valueOf(actObj1.getPreMxmlIdentifier().replaceAll(MAJOR_MINOR_DELIM, "")).doubleValue();
			double actObj2Double = Double.valueOf(actObj2.getPreMxmlIdentifier().replaceAll(MAJOR_MINOR_DELIM, "")).doubleValue();
			return actObj1Double == actObj2Double ? 0 : actObj1Double < actObj2Double ? -1 : 1;
		}
	};
	
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		super.encodeBegin(context, component);
		
		UIComponent parent = component.getParent();
		
		if(parent == null){
    		//this should never happen
    		throw new NullPointerException("Component " + component.getClass().getName() + 
    											" lacks parent component");
    	}
		
		_mxmlRendererBaseHelper.createStructureForPreMxmlFiles(component);
		
	}
	
	private final class MXMLRendererBaseHelper{
		
		private MXMLRendererBaseHelper(){
			super();
		}
		
		private void createStructureForPreMxmlFiles(UIComponent component) throws IOException {
			
			_MXMLContract mxmlUIComp = _MXMLContract.class.cast( component );
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			
			UIComponent parent = component.getParent();
			
			if(mxmlContext.isProductionEnv() || mxmlContext.isSimplySWF()){
				//do not need to create preMxml files
				return;
			}
			
			if(parent instanceof _MXMLContract && !(component instanceof _MXMLApplicationContract)){
	    		_MXMLContract mxmlInstance = _MXMLContract.class.cast( parent );
	    		int tempInt = mxmlInstance.getMajorLevel();
	    		mxmlUIComp.setMajorLevel(++tempInt);
	    		
				//now set the minor level
	        	List<UIComponent> childrenList = parent.getChildren();
	        	int counter = 0;
	        	
	        	for(UIComponent currComp : childrenList){
	        		//get the component's position within structure
	        		if(currComp == component){
	        			//got the component's position
	        			break;
	        		}
	        		if(currComp instanceof _MXMLContract){
	        			counter++;
	        		}
	        	}
	        	
	        	mxmlUIComp.setMinorLevel(counter);
	        	setPreMxmlIdentifiers(mxmlInstance, mxmlUIComp);
	    		
	    	}else if(component instanceof _MXMLApplicationContract){
	    		//means that it is not of MXML component, so set the major and minor level to 0
	    		mxmlUIComp.setMajorLevel(0);
	    		mxmlUIComp.setMinorLevel(0);
	    		setPreMxmlIdentifiers(null, mxmlUIComp);
	    		
	    	}else{
	    		throw new IllegalStateException("Failed to meet the condition of either being a top component of " +
	    												"MXMLUIApplication or having a parent as implementation of " +
	    												"MXMLContract");
	    	}
	    	
	    	setAbsolutePathToPreMxmlFile(mxmlContext.getCurrMxml(), mxmlUIComp, mxmlContext.getPreMxmlPath());
			
			insertComponentToPreMxmlCompMap(component);
			
		}
		
		private void setPreMxmlIdentifiers(_MXMLContract parentInstance, _MXMLContract currInstance){
			//TODO : implement this whole process better later
			StringBuilder parentPreMxmlIdentifier = new StringBuilder();
			StringBuilder preMxmlIdentifier = new StringBuilder();
			
			if(parentInstance != null){
				parentPreMxmlIdentifier.append(parentInstance.getPreMxmlIdentifier());
				
				preMxmlIdentifier.append(parentInstance.getPreMxmlIdentifier());
				preMxmlIdentifier.append(MAJOR_MINOR_DELIM);
				preMxmlIdentifier.append(currInstance.getMajorLevel());
				preMxmlIdentifier.append(currInstance.getMinorLevel());
				
			}else{
				//currInstance is an instance of MXMLUIApplication
				preMxmlIdentifier.append(currInstance.getMajorLevel());
				preMxmlIdentifier.append(currInstance.getMinorLevel());
			}
			
			currInstance.setParentPreMxmlIdentifier(parentPreMxmlIdentifier.toString());
			currInstance.setPreMxmlIdentifier(preMxmlIdentifier.toString());
		}
		
		private void setAbsolutePathToPreMxmlFile(String mxmlPackageName, _MXMLContract currInstance, String preMxmlPath){
			StringBuilder toReturn = new StringBuilder(preMxmlPath);
			toReturn.append(mxmlPackageName);
			toReturn.append(PRE_MXML_FILE_NAME_DELIM);
			toReturn.append(currInstance.getClass().getSimpleName());
			toReturn.append(PRE_MXML_FILE_NAME_DELIM);
			toReturn.append(currInstance.getPreMxmlIdentifier());
			toReturn.append(MXMLConstants.PRE_MXML_FILE_EXT);
			
			currInstance.setAbsolutePathToPreMxmlFile(toReturn.toString());
			
		}
		
		private void insertComponentToPreMxmlCompMap(UIComponent component) throws IOException {
			
			_MXMLContract mxmlUIComp = _MXMLContract.class.cast( component );
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			
			if(!(component instanceof _MXMLApplicationContract)){
				/*
				 * Ignore the addition of MXMLUIApplication, because MXMLApplication is responsible
				 * for traversing through the Set and creating the mxml and swf files.
				 */
				Map<Integer, Set<_MXMLContract>> preMxmlCompMap = mxmlContext.getPreMxmlCompMap();
				Integer majorKey = mxmlUIComp.getMajorLevel();
				
				Set<_MXMLContract> majorKeySet;
				if((majorKeySet = preMxmlCompMap.get(majorKey)) == null){
					majorKeySet = new TreeSet<_MXMLContract>( _majorKeyComparator );
					preMxmlCompMap.put(majorKey, majorKeySet);
				}
				
				majorKeySet.add(mxmlUIComp);
				
			}
		}
		
	}
	
}
