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

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponentBase;

import org.json.JSONObject;

import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.tasks._CommonTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FileManipulatorTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FlexTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._RunnerFactory;

/**
 * An abstract class providing the needed methods of its implementation.<br>
 * Also static method getCurrentInstance provides an easy method of retrieving current<br>
 * MxmlContext instance from the ThreadLocal.<br>
 * 
 * @author Ji Hoon Kim
 */
public abstract class MxmlContext {
	
	MxmlContext(){
		super();
	}
	
	public abstract String getCurrMxml();
	
	public abstract List<JSONObject> getApplicationInitValueList();
	
	public abstract Map<Integer, Set<_MXMLContract>> getPreMxmlCompMap();
	
	public abstract Map<String, ? super UIComponentBase> getTemporaryResourceMap();
	
	public abstract AdditionalApplicationScriptContent getAdditionalAppScriptContent();
	
	public abstract JsfFlexFlashApplicationConfiguration getJsfFlexFlashApplicationConfiguration();
	
	public abstract _RunnerFactory getRunnerFactoryInstance();
	
	public abstract _CommonTaskRunner getCommonRunner();
	
	public abstract _FileManipulatorTaskRunner getFileManipulatorRunner();
	
	public abstract _FlexTaskRunner getFlexRunner();
	
	
	public abstract boolean isProductionEnv();
	
	public abstract void setProductionEnv(boolean productionEnv);
	
	public abstract String getFlexSDKPath();
	
	public abstract void setFlexSDKPath(String flexSDKPath);
	
	public abstract String getLocaleWebContextPath();
	
	public abstract void setLocaleWebContextPath(String localeWebContextPath);
	
	public abstract String getMxmlPath();
	
	public abstract void setMxmlPath(String mxmlPath);
	
	public abstract String getPreMxmlPath();
	
	public abstract void setPreMxmlPath(String preMxmlPath);
	
	public abstract String getSwcPath();
	
	public abstract void setSwcPath(String swcPath);
	
	public abstract String getSwfBasePath();
	
	public abstract void setSwfBasePath(String swfBasePath);
	
	public abstract String getSwfPath();
	
	public abstract void setSwfPath(String swfPath);
	
	public abstract String getSwfWebPath();
	
	public abstract void setSwfWebPath(String swfWebPath);
	
	public abstract String getWebContextPath();
	
	public abstract void setWebContextPath(String webContextPath);
	
	private static ThreadLocal<MxmlContext> _currentInstance = new ThreadLocal<MxmlContext>()
    {
        protected MxmlContext initialValue()
        {
            return null;
        }
    };

    public static MxmlContext getCurrentInstance()
    {
        return _currentInstance.get();
    }

    protected static void setCurrentInstance(MxmlContext context)
    {
    	_currentInstance.set(context);
    }

}
