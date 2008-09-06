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
package com.googlecode.jsfFlex.framework.component.ext;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.framework.component.MXMLContainerTemplate;
import com.googlecode.jsfFlex.framework.context.MxmlContext;
import com.googlecode.jsfFlex.framework.exception.ComponentBuildException;
import com.googlecode.jsfFlex.framework.tasks._FileManipulatorTaskRunner;
import com.googlecode.jsfFlex.framework.util.MXMLConstants;
import com.googlecode.jsfFlex.framework.beans.TokenValue;
import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;

/**
 * @JsfFlexAttributes
 * 	controlBar=false
 * 	frameRate=false
 * 	layout=false
 * 	pageTitle=false
 * 	preloader=false
 * 	resetHistory=false
 * 	scriptRecursionLimit=false
 * 	scriptTimeLimit=false
 * 	usePreloader=false
 * 	viewSourceURL=false
 * 	backgroundGradientAlphas=false
 * 	backgroundGradientColors=false
 * 	horizontalAlign=false
 * 	horizontalGap=false
 * 	modalTransparency=false
 * 	modalTransparencyBlur=false
 * 	modalTransparencyColor=false
 * 	modalTransparencyDuration=false
 * 	verticalAlign=false
 * 	verticalGap=false
 * 	applicationComplete=false
 * 	error=false
 * 
 * @JsfFlexRenderKitAttribute
 *  componentFamily=javax.faces.MXMLApplication
 *  rendererName=com.googlecode.jsfFlex.MXMLApplication
 *  rendererClass=com.googlecode.jsfFlex.framework.component.ext.MXMLApplication
 * 
 * Aside from its normal task of mapping the field to the Set and creating the preMxml file, MXMLApplication has<br>
 * an added responsibility of :<br>
 * <ul>
 *     <li> merging preMxml file into a single preMxml file.
 *     <li> creating an application MXML file.
 *     <li> extracting the flexSDK zip file.
 *     <li> creating necessary SWC source files.
 *     <li> creating system SWC file.
 *     <li> creating necessary application SWF source files.
 *     <li> and creating application SWF file.
 * </ul>
 * Other than merging of the preMxml file into a single preMxml file, all other tasks are performed by invoking processCreateSwf<br>
 * method within MXMLComponentBaseActions class.
 * 
 * @author Ji Hoon Kim
 */
public final class MXMLApplication extends MXMLContainerTemplate {
	
	private final static Log _log = LogFactory.getLog(MXMLApplication.class);
	
	private static final String MXML_APPLICATION_BODY_TEMPLATE;
	private static final String MXML_APPLICATION_REPLACE_MAPPING;
	private static final String MX_KEY = "xmlns:mx";
	
	private static final String MXML_COMPONENT_NAME = "Application";
	
	static{
		//TODO : find a better method to implement the below tasks
		String packageName = MXMLApplication.class.getPackage().getName();
		packageName = packageName.replace('.', '/');
		MXML_APPLICATION_BODY_TEMPLATE = packageName + "/templates/MXMLApplicationBody.template";
		MXML_APPLICATION_REPLACE_MAPPING = packageName + "/replaceMapping/MXMLApplicationReplaceMapping.xml";
	}
	
	public MXMLApplication(){
		super();
	}
	
	public void buildComponentBegin(Object componentObj) throws ComponentBuildException{
		
		/*
		 * special case for MXMLApplication to filter out attribute "id"
		 * In Flex, id attribute is not allowed on the root tag of a component
		 */
		addMapperFilterString("id");
		super.buildComponentBegin(componentObj);
		
		_MXMLApplicationContract componentMXML = (_MXMLApplicationContract) componentObj;
		mapFields(MXMLApplication.class, componentObj, MXML_APPLICATION_REPLACE_MAPPING);
		/*
		 * HACK
		 * Because of the colon, the detection of qdox is giving issues. So for the time
		 * being until a better solution is found, manually pull and push the info.
		 */
		getTokenValueSet().add(new TokenValue(MX_KEY, componentMXML.getAttributes().get(MX_KEY).toString()));
		
	}
	
	public void buildComponentInterlude(Object componentObj) throws ComponentBuildException {
		super.buildComponentInterlude(componentObj);
		
		_MXMLApplicationContract componentMXML = (_MXMLApplicationContract) componentObj;
		String _bodyContent = _FileManipulatorTaskRunner.getComponentTemplate(MXMLApplication.class.getClassLoader(), 
																		MXML_APPLICATION_BODY_TEMPLATE);

		addCreatePreMxmlTask(componentMXML, MXML_COMPONENT_NAME, _bodyContent);
		
	}
	
	public void buildComponentEnd(Object componentObj) throws ComponentBuildException {
		super.buildComponentEnd(componentObj);
		
		/*
		 * Now must go through the Set and place the component's within the main preMxml file
		 * 		DataStructure is as follows within the Session Map :
		 * 			Key being the current mxmlPackage_count and value being a HashMap implementation that contains =>
		 * 				Key being the major number and value being a TreeSet with absolutePathToPreMxmlFile
		 * Afterwards will create it as a MXML file and will create the SWF file 
		 */
		_MXMLApplicationContract componentMXML = (_MXMLApplicationContract) componentObj;
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		String mxmlFile = mxmlContext.getMxmlPath() + mxmlContext.getCurrMxml() + MXMLConstants.MXML_FILE_EXT;;
		
		//check if simplySWF boolean flag is set and if so, create the SWF file and exit
		if(mxmlContext.isSimplySWF()){
			if(!new File(mxmlContext.getFlexSDKPath()).exists()){
				addMakeDirectoryTask(mxmlContext.getFlexSDKPath());
				unZipArchiveRelative(MXMLConstants.FLEX_SDK_ZIP, mxmlContext.getFlexSDKPath());
			}
			createSWF(componentMXML, mxmlFile, mxmlContext.getSwfPath(), mxmlContext.getFlexSDKPath());
			return;
		}
		
		Map _preMxmlMap = mxmlContext.getPreMxmlCompMap();
		Iterator majorIterator = _preMxmlMap.keySet().iterator();
		Integer currMajor;
		
		Set siblingSet;
		Iterator siblingIterator;
		_MXMLContract currComp;
		if(_preMxmlMap.keySet().size() > 0){
			
			//Application must be a top component with others as children component
			while(majorIterator.hasNext()){
				
				currMajor = (Integer) majorIterator.next();
				siblingSet = (Set) _preMxmlMap.get(currMajor);
				siblingIterator = siblingSet.iterator();
				
				while(siblingIterator.hasNext()){
				
					currComp = (_MXMLContract) siblingIterator.next();
					
					if(currComp.getMinorLevel() == 0){
						addReplaceTokenWithValueTask((_MXMLContract) componentMXML, readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
								childReplaceTokenWithPreMxmlIdentifier(currComp));
						
						_log.debug("Replacing token with value as a child for " + currComp.getAbsolutePathToPreMxmlFile());
					}else{
						addReplaceTokenWithValueTask((_MXMLContract) componentMXML, readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
															siblingReplaceTokenWithPreMxmlIdentifier(currComp));
						_log.debug("Replacing token with value as a sibling for " + currComp.getAbsolutePathToPreMxmlFile());
					}
					
				}
				
			}
			
			processCreateSwf(componentMXML, mxmlFile);
			
		}
		
	}
	
}