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
package com.googlecode.jsfFlex.component.ext;

import java.io.File;
import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.json.JSONObject;

import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAlphaAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBackgroundDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBarColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontGeneralAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIFontSpecificAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHideAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIHorizontalScrollPositionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIIconAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUILabelAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingHorizontalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIPaddingVerticalAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIScrollBarAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIShadowAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextStyleAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIThumbSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUITitleAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITrackAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIContainerAttributes;
import com.googlecode.jsfFlex.renderkit.annotationDocletParser._AnnotationDocletParser;
import com.googlecode.jsfFlex.renderkit.html.util.JsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.context.MxmlContextImpl;
import com.googlecode.jsfFlex.shared.tasks._RunnerFactory;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @JSFComponent
 *   name     = "jf:mxmlApplication"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIApplication"
 *   family   = "javax.faces.MXMLApplication"
 *   type     = "com.googlecode.jsfFlex.MXMLUIApplication"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIApplicationTag"
 *   defaultRendererType= "com.googlecode.jsfFlex.MXMLApplication"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "controlBar"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The ApplicationControlBar for this Application."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "frameRate"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the frame rate of the application."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "pageTitle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies a string that appears in the title bar of the browser."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "preloader"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Specifies the path of a SWC component class or ActionScript component class that defines a custom progress bar."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "resetHistory"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, the application's history state is reset to its initial state whenever the application is reloaded."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "scriptRecursionLimit"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the maximum depth of the Adobe Flash Player call stack before Flash Player stops."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "scriptTimeLimit"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the maximum duration, in seconds, that an ActionScript event handler can execute beforethe Flash Player assumes that it is hung, and aborts it."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "usePreloader"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "If true, specifies to display the application preloader."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "viewSourceURL"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "URL where the application's source can be viewed."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "xmlnsMX"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Namespace."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "backgroundGradientAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the alpha transparency values used for the background gradient fill of the application."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundGradientColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the colors used to tint the background gradient fill of the application."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "applicationComplete"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched after the Application has been initialized, processed by the LayoutManager,and attached to the display list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "error"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when an error occurs anywhere in the application, such as an HTTPService,WebService, or RemoteObject fails."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "layout"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Specifies the layout mechanism used for this application."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalAlign"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "Horizontal alignment of children in the container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "horizontalGap"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Horizontal gap."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalGap"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Vertical gap."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "modalTransparencyDuration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Duration, in milliseconds, of the modal transparency effect that plays when a modal window opens or closes. The default value is 100."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "modalTransparency"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Modality of components launched by the PopUp Manager is simulated by creating a large translucent overlay underneath the component. Because of the way translucent objects are rendered, you may notice a slight dimming of the objects under the overlay. The effective transparency can be set by changing the modalTransparency value from 0.0 (fully transparent) to 1.0 (fully opaque). You can also set the color of the overlay by changing the modalTransparencyColor style. The default value is 0.5."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "modalTransparencyColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Color of the modal overlay layer. This style is used in conjunction with the modalTransparency style to determine the colorization applied to the application when a modal window is open. The default value is #DDDDDD."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "modalTransparencyBlur"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The blur applied to the application while a modal window is open. A Blur effects oftens the details of an image. The default value is 3."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "verticalAlign"
 *   						 returnType = "java.lang.String"
 *   						 longDesc	= "The vertical alignment of a renderer in a row."
 *   						
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIApplication 
						extends UIComponentBase 
						implements _MXMLUIContainerAttributes, _MXMLUIBaseAttributes, _MXMLApplicationContract,
						_MXMLUIBackgroundAlphaAttribute, _MXMLUIBackgroundAttributes, _MXMLUIBackgroundColorAttribute, 
						_MXMLUIBackgroundDisabledColorAttribute, _MXMLUIBarColorAttribute, _MXMLUIBorderAttributes, 
						_MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUIColorAttribute, 
						_MXMLUICornerRadiusAttribute, _MXMLUIDataChangeAttribute, _MXMLUIDisabledColorAttribute, 
						_MXMLUIFontFamilyAttribute, _MXMLUIFontGeneralAttributes, _MXMLUIFontSpecificAttributes, 
						_MXMLUIHorizontalScrollPositionAttribute, _MXMLUILabelAttribute, _MXMLUIPaddingHorizontalAttributes,  
						_MXMLUIPaddingVerticalAttributes, _MXMLUIScrollAttribute, _MXMLUIScrollAttributes, 
						_MXMLUIScrollBarAttributes, _MXMLUIShadowAttributes, _MXMLUITextStyleAttributes, 
						_MXMLUIThumbSkinAttributes, _MXMLUITrackAttributes, _MXMLUIHideAttribute,
						_MXMLUITitleAttribute, _MXMLUIIconAttribute, _MXMLContract {
	
	private static final String JSF_FLEX_COMMUNICATOR_CORE_JS = "jsfFlexCommunicatorCore.js";
	private static final String JSF_FLEX_COMMUNICATOR_LOGGER_JS = "jsfFlexCommunicatorLogger.js";
	
	private static final String INITIALIZE_ATTR = "initialize";
	
	private static final String MX_ACTUAL_KEY = "xmlns:mx";
	private static final String MX_XMLNS_KEY = "xmlnsMX";
	private static final String MX_DEFAULT_XMLNS = "http://www.adobe.com/2006/mxml";
    private static final String INITIALIZE_CALL = "initializeApp(event);";
	
    private _AnnotationDocletParser _annotationDocletParserInstance;
    
    private String _absolutePathToPreMxmlFile;
	
	private String _preMxmlIdentifier;
	private String _parentPreMxmlIdentifier;
	/*
	 * below two variables dictate the depth and the height of this component
	 * in reference to the top component which should be of MXMLApplication. 
	 */
	private int _majorLevel = -1;
	private int _minorLevel = -1;
    
	private String _applicationPath;
	private String _externalLibraryPath;
	private String _runtimeSharedLibraries;
	private boolean _accessible;
	
	public void encodeBegin(FacesContext context) throws IOException {
		
		ServletContext servContext = (ServletContext) context.getExternalContext().getContext();
		setApplicationPath( servContext.getRealPath("") );
		
		if(getAttributes().get(MX_XMLNS_KEY) == null){
			getAttributes().put(MX_ACTUAL_KEY, MX_DEFAULT_XMLNS);
		}else{
			getAttributes().put(MX_ACTUAL_KEY, getAttributes().get(MX_XMLNS_KEY));
		}
		
		MxmlContext mxmlContext = new MxmlContextImpl(getMxmlPackageName(), this);
		
		String mode = context.getExternalContext().getInitParameter(MXMLConstants.CONFIG_MODE_NAME);
		
		boolean isSimplySwf = false;
		boolean isProduction = true;
		
		if(mode != null){
			
			isSimplySwf = mode.equals(MXMLConstants.SIMPLY_SWF_MODE);
			isProduction = mode.equals(MXMLConstants.PRODUCTION_MODE);
			
		}
		
		mxmlContext.setProductionEnv(isProduction);
		mxmlContext.setSimplySWF(isSimplySwf);
		
		String webContextPath = context.getExternalContext().getRequestContextPath();
		String swfWebPath = webContextPath + "/" + MXMLConstants.SWF_DIRECTORY_NAME + "/" + getMxmlPackageName() + "/";
		mxmlContext.setSwfWebPath(swfWebPath);
		mxmlContext.setWebContextPath(webContextPath);
		
		//setting or appending scripts to execute upon application initialization
		String init = (String) getAttributes().get(INITIALIZE_ATTR);
		init = (init == null) ? INITIALIZE_CALL : init + " " + INITIALIZE_CALL;
		getAttributes().put(INITIALIZE_ATTR, init);
		
		String localeWebContextRelativePath = context.getExternalContext().getInitParameter(MXMLConstants.LOCALE_WEB_CONTEXT_RELATIVE_PATH);
		if(localeWebContextRelativePath != null){
			mxmlContext.setLocaleWebContextPath(_applicationPath + File.separatorChar + localeWebContextRelativePath + File.separatorChar);
		}
		
		//to reflect the correct state when debugging
		if(isProduction){
			//do not need to create preMXML, MXML, and SWF files
			
		}else{
			String mxmlPath = _applicationPath + File.separatorChar + MXMLConstants.MXML_DIRECTORY_NAME + File.separatorChar +
									getMxmlPackageName() + File.separatorChar;
			String swfPath = _applicationPath + File.separatorChar + MXMLConstants.SWF_DIRECTORY_NAME + File.separatorChar +
									getMxmlPackageName() + File.separatorChar + getMxmlPackageName() + MXMLConstants.SWF_FILE_EXT;
			String swfBasePath = _applicationPath + File.separatorChar + MXMLConstants.SWF_DIRECTORY_NAME + File.separatorChar;
			/*
			 * 	The above swfBasePath will hold placeholder of where swf-source-files's source-file[s] will be echoed to.
			 * 	The files that will be echoed can be found in mxmlConstants.xml and are simply the contents that will be used
			 * 	by the system's ActionScripts.
			 */
			String flexSDKPath = _applicationPath + File.separatorChar + MXMLConstants.FLEX_SDK_DIRECTORY_NAME + File.separatorChar;
			
			String swcPath = _applicationPath + File.separatorChar + MXMLConstants.SWC_DIRECTORY_NAME + File.separatorChar +
									MXMLConstants.JSF_FLEX_MAIN_SWC_DIRECTORY_NAME + File.separatorChar;
			
			//externalLibraryPath will contain .swc file
			String swcFileAbsolutePath = swcPath + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWC_FILE_EXT;
			setExternalLibraryPath(swcFileAbsolutePath);
			
			//runtimeSharedLibrary has to be relative to the Web root path file
			String jsfFlexMainSwcWebpath = webContextPath + "/swf/" + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWF_FILE_EXT;
			setRuntimeSharedLibraries(jsfFlexMainSwcWebpath);
			
			mxmlContext.setFlexSDKPath(flexSDKPath);
			mxmlContext.setMxmlPath(mxmlPath);
			mxmlContext.setSwfPath(swfPath);
			mxmlContext.setSwfBasePath(swfBasePath);
			mxmlContext.setSwcPath(swcPath);
			
			//set the attributes for jsfFlexFlashApplicationConfiguration
			JsfFlexFlashApplicationConfiguration jsfFlexFlashApplicationConfiguration = mxmlContext.getJsfFlexFlashApplicationConfiguration();
			String flashToJavaScriptLogLevel = context.getExternalContext().getInitParameter(MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_LEVEL_NAME);
			if(flashToJavaScriptLogLevel == null){
				
				flashToJavaScriptLogLevel = context.getExternalContext().getInitParameter(MXMLConstants.CONFIG_MODE_NAME);
				if(flashToJavaScriptLogLevel.equals(MXMLConstants.PRODUCTION_MODE)){
					flashToJavaScriptLogLevel = MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_WARN_LEVEL;
				}else{
					flashToJavaScriptLogLevel = MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_LOG_LEVEL;
				}
			}
			
			if(flashToJavaScriptLogLevel.equals(MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_LOG_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("1");
			}else if(flashToJavaScriptLogLevel.equals(MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_DEBUG_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("2");
			}else if(flashToJavaScriptLogLevel.equals(MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_INFO_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("3");
			}else if(flashToJavaScriptLogLevel.equals(MXMLConstants.FLASH_TO_JAVASCRIPT_LOG_WARN_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("4");
			}else {
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("5");
			}
			
			if(isSimplySwf){
				//do not need to create preMXML files
				
			}else{
				String preMxmlPath = _applicationPath + File.separatorChar + MXMLConstants.PREMXML_DIRECTORY_NAME + File.separatorChar +
										getMxmlPackageName() + File.separatorChar;
				mxmlContext.setPreMxmlPath(preMxmlPath);
				
			}
			
			//Does this even need to be present within the JSF-component or should it passed as default within the task?
			setAccessible(true);
			
		}
		
		super.encodeBegin(context);
	}
	
	public void encodeEnd(FacesContext context) throws IOException {
		
		JsfFlexResource jsfFlexResource = JsfFlexResource.getInstance();
		jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_CORE_JS);
		jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_LOGGER_JS);
		
		super.encodeEnd(context);
	}
	
	public JSONObject getComponentInitValues(){
    	return null;
    }
	
	public _AnnotationDocletParser getAnnotationDocletParserInstance(){
		
		if(_annotationDocletParserInstance == null){
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			_RunnerFactory runnerFactoryInstance = mxmlContext.getRunnerFactoryInstance();
			_annotationDocletParserInstance = runnerFactoryInstance.getAnnotationDocletParserImpl();
		}
		
		return _annotationDocletParserInstance;
	}
	
	public String getApplicationPath() {
		return _applicationPath;
	}
	public void setApplicationPath(String applicationPath) {
		_applicationPath = applicationPath;
	}
	public String getRuntimeSharedLibraries() {
		return _runtimeSharedLibraries;
	}
	public void setRuntimeSharedLibraries(String runtimeSharedLibraries) {
		_runtimeSharedLibraries = runtimeSharedLibraries;
	}
	public String getExternalLibraryPath(){
		return _externalLibraryPath;
	}
	public void setExternalLibraryPath(String externalLibraryPath){
		_externalLibraryPath = externalLibraryPath;
	}
	public boolean isAccessible() {
		return _accessible;
	}
	public void setAccessible(boolean accessible) {
		_accessible = accessible;
	}
	
	public String getAbsolutePathToPreMxmlFile() {
		return _absolutePathToPreMxmlFile;
	}
	public void setAbsolutePathToPreMxmlFile(String absolutePathToPreMxmlFile) {
		_absolutePathToPreMxmlFile = absolutePathToPreMxmlFile;
	}
	public int getMajorLevel() {
		return _majorLevel;
	}
	public void setMajorLevel(int majorLevel) {
		_majorLevel = majorLevel;
	}
	public int getMinorLevel() {
		return _minorLevel;
	}
	public void setMinorLevel(int minorLevel) {
		_minorLevel = minorLevel;
	}
	public String getParentPreMxmlIdentifier() {
		return _parentPreMxmlIdentifier;
	}
	public void setParentPreMxmlIdentifier(String parentPreMxmlIdentifier) {
		_parentPreMxmlIdentifier = parentPreMxmlIdentifier;
	}
	public String getPreMxmlIdentifier() {
		return _preMxmlIdentifier;
	}
	public void setPreMxmlIdentifier(String preMxmlIdentifier) {
		_preMxmlIdentifier = preMxmlIdentifier;
	}
	
	/**
	 * The mxmlPackageName for the application.
	 * 
	 *@JSFProperty
	 *    required        = true
	 *    rtexprvalue     = false
	 *    desc            = "The mxmlPackageName for the application."
	 */
	public abstract String getMxmlPackageName();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that areneeded for the current SWF generation are located at. There can be multiple valuesseparated with a space.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that areneeded for the current SWF generation are located at. There can be multiple valuesseparated with a space."
	 */
	public abstract String getSourcePath();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the defaultBgColor, surprise.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It represents the defaultBgColor, surprise."
	 */
	public abstract String getDefaultBgColor();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the max level of recursion that the Flash VM will allow.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It represents the max level of recursion that the Flash VM will allow."
	 */
	public abstract Integer getMaxLvRecursion();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the max script exec time that the Flash VM will allow.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It represents the max script exec time that the Flash VM will allow."
	 */
	public abstract Integer getMaxScriptExecTime();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents whether the creation of the SWF files will based incrementally.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It represents whether the creation of the SWF files will based incrementally."
	 */
	public abstract boolean isIncremental();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It should bean absolutePath to a loadConfig XML file that specifies attributes for the mxmlc.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It should bean absolutePath to a loadConfig XML file that specifies attributes for the mxmlc."
	 */
	public abstract String getLoadConfig();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF."
	 */
	public abstract String getDescription();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF."
	 */
	public abstract String getCreator();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF."
	 */
	public abstract String getPublisher();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It simply isa  metadata for the SWF."
	 */
	public abstract String getLanguage();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF."
	 */
	public abstract String getDate();
	
	
	/*
	 * Error attributes for ValidationManagerScriptContent.java
	 */
	
	/**
	 * Color of text for the error component. The default value is 0x0B333C.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Color of text for the error component. The default value is 0x0B333C."
	 */
	public abstract String getErrorColor();
	
	/**
	 * Sets the antiAliasType property of internal TextFields for the error component. Possible values are normal and advanced.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Sets the antiAliasType property of internal TextFields for the error component. Possible values are normal and advanced."
	 */
	public abstract String getErrorFontAntiAliasType();
	
	/**
	 * Name of the font to use for the error component. The default value is Verdana.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Name of the font to use for the error component. The default value is Verdana."
	 */
	public abstract String getErrorFontFamily();
	
	/**
	 * Sets the gridFitType property of internal TextFields for the error component that represent text in Flex controls. The possible values are none, pixel, and subpixel.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Sets the gridFitType property of internal TextFields for the error component that represent text in Flex controls. The possible values are none, pixel, and subpixel."
	 */
	public abstract String getErrorFontGridFitType();
	
	/**
	 * Sets the sharpness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the sharpness of the glyph edges. The possible values are Numbers from -400 through 400.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Sets the sharpness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the sharpness of the glyph edges. The possible values are Numbers from -400 through 400."
	 */
	public abstract String getErrorFontSharpness();
	
	/**
	 * Height of the text for the error component, in pixels. The default value is 10.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Height of the text for the error component, in pixels. The default value is 10."
	 */
	public abstract String getErrorFontSize();
	
	/**
	 * Determines whether the text for the error component is italic font. Recognized values are normal and italic.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Determines whether the text for the error component is italic font. Recognized values are normal and italic."
	 */
	public abstract String getErrorFontStyle();
	
	/**
	 * Sets the thickness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the thickness of the glyph edges. The possible values are Numbers from -200 to 200.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Sets the thickness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the thickness of the glyph edges. The possible values are Numbers from -200 to 200."
	 */
	public abstract String getErrorFontThickness();
	
	/**
	 * Determines whether the text for the error component is boldface. Recognized values are normal and bold.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Determines whether the text for the error component is boldface. Recognized values are normal and bold."
	 */
	public abstract String getErrorFontWeight();
	
	/**
	 * Number of pixels between the error component's container's left border and the left edge of its content area.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels between the error component's container's left border and the left edge of its content area."
	 */
	public abstract String getErrorPaddingLeft();
	
	/**
	 * Number of pixels between the error component's container's right border and the right edge of its content area.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Number of pixels between the error component's container's right border and the right edge of its content area."
	 */
	public abstract String getErrorPaddingRight();
	
	/**
	 * Alignment of text for the error component within a container. Possible values are left, right, or center.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Alignment of text for the error component within a container. Possible values are left, right, or center."
	 */
	public abstract String getErrorTextAlign();
	
	/**
	 * Determines whether the text for the error component is underlined. Possible values are none and underline.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Determines whether the text for the error component is underlined. Possible values are none and underline."
	 */
	public abstract String getErrorTextDecoration();
	
	/**
	 * Offset of first line of text for the error component from the left side of the container, in pixels.
	 * 
	 *@JSFProperty
	 *    required        = false
	 *    rtexprvalue     = false
	 *    desc            = "Offset of first line of text for the error component from the left side of the container, in pixels."
	 */
	public abstract String getErrorTextIndent();
	
}
