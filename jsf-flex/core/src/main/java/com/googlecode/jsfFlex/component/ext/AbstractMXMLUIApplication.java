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

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes._MXMLUIApplicationCompleteAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundGradientAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBackgroundGradientColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIControlBarAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFrameRateAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHistoryManagementEnabledAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILayoutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyBlurAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIModalTransparencyDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPageTitleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPreloaderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIResetHistoryAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIScriptRecursionLimitAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIScriptTimeLimitAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITitleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIUsePreloaderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIVerticalGapAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIViewSourceURLAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIXmlnsMXAttribute;
import com.googlecode.jsfFlex.container.ext._MXMLUIContainerAttributes;
import com.googlecode.jsfFlex.renderkit.annotationDocletParser._AnnotationDocletParser;
import com.googlecode.jsfFlex.renderkit.html.util.JsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.context.MxmlContextImpl;
import com.googlecode.jsfFlex.shared.tasks._RunnerFactory;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlApplication",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIApplication",
        type                =   "com.googlecode.jsfFlex.MXMLUIApplication",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIApplicationTag",
        family              =   "javax.faces.MXMLApplication",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLApplication"
)
public abstract class AbstractMXMLUIApplication 
						extends UIComponentBase 
						implements _MXMLUIContainerAttributes, _MXMLApplicationContract, _MXMLUIControlBarAttribute, 
                        _MXMLUIFrameRateAttribute, _MXMLUIHistoryManagementEnabledAttribute, _MXMLUILayoutAttribute, 
                        _MXMLUIPageTitleAttribute, _MXMLUIPreloaderAttribute, _MXMLUIResetHistoryAttribute, _MXMLUIScriptRecursionLimitAttribute, 
                        _MXMLUIScriptTimeLimitAttribute, _MXMLUIUsePreloaderAttribute, _MXMLUIViewSourceURLAttribute, 
                        _MXMLUIXmlnsMXAttribute, _MXMLUIBackgroundGradientAlphasAttribute, _MXMLUIBackgroundGradientColorsAttribute, 
                        _MXMLUIHorizontalAlignAttribute, _MXMLUIHorizontalGapAttribute, _MXMLUIModalTransparencyAttribute, 
                        _MXMLUIModalTransparencyBlurAttribute, _MXMLUIModalTransparencyColorAttribute, _MXMLUIModalTransparencyDurationAttribute, 
                        _MXMLUIVerticalAlignAttribute, _MXMLUIVerticalGapAttribute, _MXMLUIApplicationCompleteAttribute, 
                        _MXMLUIErrorAttribute, _MXMLUITitleAttribute {
	
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
		
		String mode = context.getExternalContext().getInitParameter(MXMLConstants.CONFIG_MODE_NAME);
		MxmlContext mxmlContext = new MxmlContextImpl(getMxmlPackageName(), mode, this);
        
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
		if(mxmlContext.isProductionEnv()){
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
			
			if(mxmlContext.isSimplySWF()){
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
	
	public synchronized _AnnotationDocletParser getAnnotationDocletParserInstance(){
		
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
	 */
    @JSFProperty(
            required    =   true,
            desc        =   "The mxmlPackageName for the application."
    )
	public abstract String getMxmlPackageName();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that areneeded for the current SWF generation are located at. There can be multiple valuesseparated with a space.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that areneeded for the current SWF generation are located at. There can be multiple valuesseparated with a space.")
	public abstract String getSourcePath();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the defaultBgColor, surprise.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It represents the defaultBgColor, surprise.")
	public abstract String getDefaultBgColor();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the max level of recursion that the Flash VM will allow.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It represents the max level of recursion that the Flash VM will allow.")
	public abstract Integer getMaxLvRecursion();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents the max script exec time that the Flash VM will allow.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It represents the max script exec time that the Flash VM will allow.")
	public abstract Integer getMaxScriptExecTime();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It represents whether the creation of the SWF files will based incrementally.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It represents whether the creation of the SWF files will based incrementally.")
	public abstract boolean isIncremental();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It should bean absolutePath to a loadConfig XML file that specifies attributes for the mxmlc.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It should bean absolutePath to a loadConfig XML file that specifies attributes for the mxmlc.")
	public abstract String getLoadConfig();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.")
	public abstract String getDescription();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.")
	public abstract String getCreator();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.")
	public abstract String getPublisher();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It simply isa  metadata for the SWF.")
	public abstract String getLanguage();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It simply is a metadata for the SWF.")
	public abstract String getDate();
	
	
	/*
	 * Error attributes for ValidationManagerScriptContent.java
	 */
	
	/**
	 * Color of text for the error component. The default value is 0x0B333C.
	 */
    @JSFProperty(desc   =   "Color of text for the error component. The default value is 0x0B333C.")
	public abstract String getErrorColor();
	
	/**
	 * Sets the antiAliasType property of internal TextFields for the error component. Possible values are normal and advanced.
	 */
    @JSFProperty(desc   =   "Sets the antiAliasType property of internal TextFields for the error component. Possible values are normal and advanced.")
	public abstract String getErrorFontAntiAliasType();
	
	/**
	 * Name of the font to use for the error component. The default value is Verdana.
	 */
    @JSFProperty(desc   =   "Name of the font to use for the error component. The default value is Verdana.")
	public abstract String getErrorFontFamily();
	
	/**
	 * Sets the gridFitType property of internal TextFields for the error component that represent text in Flex controls. The possible values are none, pixel, and subpixel.
	 */
    @JSFProperty(desc   =   "Sets the gridFitType property of internal TextFields for the error component that represent text in Flex controls. The possible values are none, pixel, and subpixel.")
	public abstract String getErrorFontGridFitType();
	
	/**
	 * Sets the sharpness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the sharpness of the glyph edges. The possible values are Numbers from -400 through 400.
	 */
    @JSFProperty(desc   =   "Sets the sharpness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the sharpness of the glyph edges. The possible values are Numbers from -400 through 400.")
	public abstract String getErrorFontSharpness();
	
	/**
	 * Height of the text for the error component, in pixels. The default value is 10.
	 */
    @JSFProperty(desc   =   "Height of the text for the error component, in pixels. The default value is 10.")
	public abstract String getErrorFontSize();
	
	/**
	 * Determines whether the text for the error component is italic font. Recognized values are normal and italic.
	 */
    @JSFProperty(desc   =   "Determines whether the text for the error component is italic font. Recognized values are normal and italic.")
	public abstract String getErrorFontStyle();
	
	/**
	 * Sets the thickness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the thickness of the glyph edges. The possible values are Numbers from -200 to 200.
	 */
    @JSFProperty(desc   =   "Sets the thickness property of internal TextFields for the error component that represent text in Flex controls. This property specifies the thickness of the glyph edges. The possible values are Numbers from -200 to 200.")
	public abstract String getErrorFontThickness();
	
	/**
	 * Determines whether the text for the error component is boldface. Recognized values are normal and bold.
	 */
    @JSFProperty(desc   =   "Determines whether the text for the error component is boldface. Recognized values are normal and bold.")
	public abstract String getErrorFontWeight();
	
	/**
	 * Number of pixels between the error component's container's left border and the left edge of its content area.
	 */
    @JSFProperty(desc   =   "Number of pixels between the error component's container's left border and the left edge of its content area.")
	public abstract String getErrorPaddingLeft();
	
	/**
	 * Number of pixels between the error component's container's right border and the right edge of its content area.
	 */
    @JSFProperty(desc   =   "Number of pixels between the error component's container's right border and the right edge of its content area.")
	public abstract String getErrorPaddingRight();
	
	/**
	 * Alignment of text for the error component within a container. Possible values are left, right, or center.
	 */
    @JSFProperty(desc   =   "Alignment of text for the error component within a container. Possible values are left, right, or center.")
	public abstract String getErrorTextAlign();
	
	/**
	 * Determines whether the text for the error component is underlined. Possible values are none and underline.
	 */
    @JSFProperty(desc   =   "Determines whether the text for the error component is underlined. Possible values are none and underline.")
	public abstract String getErrorTextDecoration();
	
	/**
	 * Offset of first line of text for the error component from the left side of the container, in pixels.
	 */
    @JSFProperty(desc   =   "Offset of first line of text for the error component from the left side of the container, in pixels.")
	public abstract String getErrorTextIndent();
	
}
