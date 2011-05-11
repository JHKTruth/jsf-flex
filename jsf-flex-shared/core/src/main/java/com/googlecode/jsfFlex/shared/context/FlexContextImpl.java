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
package com.googlecode.jsfFlex.shared.context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.component.UIComponentBase;

import org.json.JSONObject;

import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.tasks.ICommonTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.AbstractFileManipulatorTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.IFlexTaskRunner;
import com.googlecode.jsfFlex.shared.tasks.AbstractRunnerFactory;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * An implementation of AbstractFlexContext which will instantiate and store all the needed data structures<br>
 * and instantiate and store the implementation of following interfaces :<br>
 * <ul>
 *     <li> AbstractFileManipulatorTaskRunner
 *     <li> IFlexTaskRunner
 *     <li> ICommonTaskRunner
 * </ul>
 * Also it will set the current FlexContextImpl to the ThreadLocal.<br>
 * 
 * @author Ji Hoon Kim
 */
public class FlexContextImpl extends AbstractFlexContext {
	
	private final String _currMxml;
	
	private final List<JSONObject> _applicationInitValueList;
	private final Map<Integer, Set<IFlexContract>> _preMxmlCompMap;
	private final Map<String, ? super UIComponentBase> _temporaryResourceMap;
	private final AdditionalApplicationScriptContent _additionalAppScriptContent;
	private final JsfFlexFlashApplicationConfiguration _jsfFlexFlashApplicationConfiguration;
	
	private final AbstractRunnerFactory _runnerFactoryInstance;
	private final ICommonTaskRunner _commonRunner;
	private final AbstractFileManipulatorTaskRunner _fileManipulatorRunner;
	private final IFlexTaskRunner _flexRunner;
	
	private boolean _productionEnv;
	
	private String _projectWorkspaceWebFlashDirectory;
	private String _flexSDKPath;
	private String _flexJavaSDKPath;
	private String _localeWebContextPath;
	private String _mxmlPath;
	private String _preMxmlPath;
    private String _jsfFlexSwcPath;
	private String _swcPath;
	private String _applicationSwfPath;
	private String _swfPath;
	private String _applicationSwfWebPath;
    private String _swfWebPath;
	private String _webContextPath;
	
	public FlexContextImpl(String currMxml, String mode, IFlexApplicationContract currApplicationContract){
		super();
		_currMxml = currMxml;
		_applicationInitValueList = new LinkedList<JSONObject>();
		_preMxmlCompMap = new TreeMap<Integer, Set<IFlexContract>>();
		_temporaryResourceMap = new HashMap<String, UIComponentBase>();
		_additionalAppScriptContent = new AdditionalApplicationScriptContent(_currMxml, currApplicationContract);
		_jsfFlexFlashApplicationConfiguration = new JsfFlexFlashApplicationConfiguration();
		
        if(mode != null){
            _productionEnv = mode.equals(FlexConstants.PRODUCTION_MODE);
        }
        
        _runnerFactoryInstance = AbstractRunnerFactory.getInstance();
        _commonRunner = !_productionEnv ? _runnerFactoryInstance.getCommonTaskRunnerImpl() : null;
        _fileManipulatorRunner = !_productionEnv ? _runnerFactoryInstance.getFileManipulatorTaskRunnerImpl() : null;
        _flexRunner = !_productionEnv ? _runnerFactoryInstance.getFlexTaskRunnerImpl() : null;
        
		AbstractFlexContext.setCurrentInstance(this);
	}
	
	public String getCurrMxml() {
		return _currMxml;
	}
	public List<JSONObject> getApplicationInitValueList() {
		return _applicationInitValueList;
	}
	public Map<Integer, Set<IFlexContract>> getPreMxmlCompMap() {
		return _preMxmlCompMap;
	}
	public Map<String, ? super UIComponentBase> getTemporaryResourceMap() {
		return _temporaryResourceMap;
	}
	public AdditionalApplicationScriptContent getAdditionalAppScriptContent() {
		return _additionalAppScriptContent;
	}
	public JsfFlexFlashApplicationConfiguration getJsfFlexFlashApplicationConfiguration() {
		return _jsfFlexFlashApplicationConfiguration;
	}
	public AbstractRunnerFactory getRunnerFactoryInstance() {
		return _runnerFactoryInstance;
	}
	public ICommonTaskRunner getCommonRunner() {
		return _commonRunner;
	}
	public AbstractFileManipulatorTaskRunner getFileManipulatorRunner() {
		return _fileManipulatorRunner;
	}
	public IFlexTaskRunner getFlexRunner() {
		return _flexRunner;
	}
	
	public boolean isProductionEnv() {
		return _productionEnv;
	}
	public void setProductionEnv(boolean productionEnv) {
		_productionEnv = productionEnv;
	}
	public String getProjectWorkspaceWebFlashDirectory() {
		return _projectWorkspaceWebFlashDirectory;
	}
	public void setProjectWorkspaceWebFlashDirectory(String projectWorkspaceWebFlashDirectory) {
		_projectWorkspaceWebFlashDirectory = projectWorkspaceWebFlashDirectory;
	}
	public String getFlexSDKPath() {
		return _flexSDKPath;
	}
	public void setFlexSDKPath(String flexSDKPath) {
		_flexSDKPath = flexSDKPath;
	}
	public String getFlexJavaSDKPath() {
		return _flexJavaSDKPath;
	}
	public void setFlexJavaSDKPath(String flexJavaSDKPath) {
		_flexJavaSDKPath = flexJavaSDKPath;
	}
	public String getLocaleWebContextPath() {
		return _localeWebContextPath;
	}
	public void setLocaleWebContextPath(String localeWebContextPath) {
		_localeWebContextPath = localeWebContextPath;
	}
	public String getMxmlPath() {
		return _mxmlPath;
	}
	public void setMxmlPath(String mxmlPath) {
		_mxmlPath = mxmlPath;
	}
	public String getPreMxmlPath() {
		return _preMxmlPath;
	}
	public void setPreMxmlPath(String preMxmlPath) {
		_preMxmlPath = preMxmlPath;
	}
    public String getJsfFlexSwcPath(){
        return _jsfFlexSwcPath;
    }
    public void setJsfFlexSwcPath(String jsfFlexSwcPath){
        _jsfFlexSwcPath = jsfFlexSwcPath;
    }
	public String getSwcPath() {
		return _swcPath;
	}
	public void setSwcPath(String swcPath) {
		_swcPath = swcPath;
	}
	public String getApplicationSwfPath() {
		return _applicationSwfPath;
	}
	public void setApplicationSwfPath(String applicationSwfPath) {
		_applicationSwfPath = applicationSwfPath;
	}
	public String getSwfPath() {
		return _swfPath;
	}
	public void setSwfPath(String swfPath) {
		_swfPath = swfPath;
	}
	public String getApplicationSwfWebPath() {
		return _applicationSwfWebPath;
	}
	public void setApplicationSwfWebPath(String applicationSwfWebPath) {
		_applicationSwfWebPath = applicationSwfWebPath;
	}
    public String getSwfWebPath(){
        return _swfWebPath;
    }
    public void setSwfWebPath(String swfWebPath){
        _swfWebPath = swfWebPath;
    }
	public String getWebContextPath(){
		return _webContextPath;
	}
	public void setWebContextPath(String webContextPath){
		_webContextPath = webContextPath;
	}
	
}
