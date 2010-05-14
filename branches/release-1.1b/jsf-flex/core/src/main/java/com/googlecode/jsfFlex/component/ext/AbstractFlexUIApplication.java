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
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes.IFlexUIApplicationCompleteAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBackgroundGradientAlphasAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIBackgroundGradientColorsAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIControlBarAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIFrameRateAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIHistoryManagementEnabledAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIHorizontalAlignAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIHorizontalGapAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUILayoutAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIModalTransparencyAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIModalTransparencyBlurAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIModalTransparencyColorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIModalTransparencyDurationAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIPageTitleAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIPreloaderAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIResetHistoryAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIScriptRecursionLimitAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIScriptTimeLimitAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUITitleAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIUsePreloaderAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIVerticalAlignAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIVerticalGapAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIViewSourceURLAttribute;
import com.googlecode.jsfFlex.container.ext.IFlexUIContainerAttributes;
import com.googlecode.jsfFlex.renderkit.annotationDocletParser.AbstractAnnotationDocletParser;
import com.googlecode.jsfFlex.renderkit.html.util.AbstractJsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.beans.others.JsfFlexFlashApplicationConfiguration;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.context.FlexContextImpl;
import com.googlecode.jsfFlex.shared.tasks.AbstractRunnerFactory;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexApplication",
        clazz               =   "com.googlecode.jsfFlex.component.ext.FlexUIApplication",
        type                =   "com.googlecode.jsfFlex.FlexUIApplication",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.FlexUIApplicationTag",
        family              =   "javax.faces.FlexApplication",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexApplication"
)
public abstract class AbstractFlexUIApplication 
						extends UIComponentBase 
						implements IFlexUIContainerAttributes, IFlexApplicationContract, IFlexUIControlBarAttribute, 
                        IFlexUIFrameRateAttribute, IFlexUIHistoryManagementEnabledAttribute, IFlexUILayoutAttribute, 
                        IFlexUIPageTitleAttribute, IFlexUIPreloaderAttribute, IFlexUIResetHistoryAttribute, IFlexUIScriptRecursionLimitAttribute, 
                        IFlexUIScriptTimeLimitAttribute, IFlexUIUsePreloaderAttribute, IFlexUIViewSourceURLAttribute, 
                        IFlexUIBackgroundGradientAlphasAttribute, IFlexUIBackgroundGradientColorsAttribute, 
                        IFlexUIHorizontalAlignAttribute, IFlexUIHorizontalGapAttribute, IFlexUIModalTransparencyAttribute, 
                        IFlexUIModalTransparencyBlurAttribute, IFlexUIModalTransparencyColorAttribute, IFlexUIModalTransparencyDurationAttribute, 
                        IFlexUIVerticalAlignAttribute, IFlexUIVerticalGapAttribute, IFlexUIApplicationCompleteAttribute, 
                        IFlexUIErrorAttribute, IFlexUITitleAttribute {
	
	private static final String JSF_FLEX_COMMUNICATOR_CORE_JS = "jsfFlexCommunicatorCore.js";
	private static final String JSF_FLEX_COMMUNICATOR_LOGGER_JS = "jsfFlexCommunicatorLogger.js";
	
	private static final String INITIALIZE_ATTR = "initialize";
	
	private static final String MX_XMLNS = "xmlns:mx";
    private static final String XMLNS_PREFIX = "xmlns:";
	private static final String MX_DEFAULT_XMLNS_URL = "http://www.adobe.com/2006/mxml";
    private static final String INITIALIZE_CALL = "initializeApp(event);";
	
    private AbstractAnnotationDocletParser _annotationDocletParserInstance;
    
    private String _absolutePathToPreMxmlFile;
	
	private String _preMxmlIdentifier;
	private String _parentPreMxmlIdentifier;
	/*
	 * below two variables dictate the depth and the height of this component
	 * in reference to the top component which should be of FlexApplication. 
	 */
	private int _majorLevel = -1;
	private int _minorLevel = -1;
    
	private String _applicationPath;
	private Collection<String> _externalLibraryPath;
	private Collection<String> _runtimeSharedLibraries;
    private Map<String, String> _xmlnsMap;
    
	private boolean _accessible;
	
    {
        _externalLibraryPath = new LinkedList<String>();
        _runtimeSharedLibraries = new LinkedList<String>();
        _xmlnsMap = new HashMap<String, String>();
    }
    
	public void encodeBegin(FacesContext context) throws IOException {
		
		ServletContext servContext = (ServletContext) context.getExternalContext().getContext();
		setApplicationPath( servContext.getRealPath("") );
		
        Map<String, String> xmlnsMap = getXmlnsMap();
        xmlnsMap.put(MX_XMLNS, MX_DEFAULT_XMLNS_URL);
        
        if(getProvidedAdditionalXmlnsMap() != null){
            Map<String, String> providedAdditionalXmlnsMap = getProvidedAdditionalXmlnsMap();
            for(String currXmlnsKey : providedAdditionalXmlnsMap.keySet()){
                String currXmlnsUrl = providedAdditionalXmlnsMap.get(currXmlnsKey);
                currXmlnsKey = currXmlnsKey.indexOf(':') > -1 ? currXmlnsKey : XMLNS_PREFIX + currXmlnsKey;
                xmlnsMap.put(currXmlnsKey, currXmlnsUrl);
            }
        }
        
		String mode = context.getExternalContext().getInitParameter(FlexConstants.CONFIG_MODE_NAME);
		AbstractFlexContext mxmlContext = new FlexContextImpl(getMxmlPackageName(), mode, this);
        
		String webContextPath = context.getExternalContext().getRequestContextPath();
        String swfWebPath = webContextPath + "/" + FlexConstants.SWF_DIRECTORY_NAME + "/";
		String applicationSwfWebPath = swfWebPath + getMxmlPackageName() + "/";
        mxmlContext.setSwfWebPath(swfWebPath);
		mxmlContext.setApplicationSwfWebPath(applicationSwfWebPath);
		mxmlContext.setWebContextPath(webContextPath);
		
		//setting or appending scripts to execute upon application initialization
		String init = (String) getAttributes().get(INITIALIZE_ATTR);
		init = (init == null) ? INITIALIZE_CALL : init + " " + INITIALIZE_CALL;
		getAttributes().put(INITIALIZE_ATTR, init);
		
		String localeWebContextRelativePath = context.getExternalContext().getInitParameter(FlexConstants.LOCALE_WEB_CONTEXT_RELATIVE_PATH);
		if(localeWebContextRelativePath != null){
			mxmlContext.setLocaleWebContextPath(_applicationPath + File.separatorChar + localeWebContextRelativePath + File.separatorChar);
		}
		
		//to reflect the correct state when debugging
		if(mxmlContext.isProductionEnv()){
			//do not need to create preMXML, MXML, and SWF files
			
		}else{
			String mxmlPath = _applicationPath + File.separatorChar + FlexConstants.FLEX_DIRECTORY_NAME + File.separatorChar +
									getMxmlPackageName() + File.separatorChar;
            String swfPath = _applicationPath + File.separatorChar + FlexConstants.SWF_DIRECTORY_NAME + File.separatorChar;
            String applicationSwfPath = swfPath + getMxmlPackageName() + File.separatorChar + getMxmlPackageName() + FlexConstants.SWF_FILE_EXT;
			
			/*
			 * 	The above swfBasePath will hold placeholder of where swf-source-files's source-file[s] will be echoed to.
			 * 	The files that will be echoed can be found in mxmlConstants.xml and are simply the contents that will be used
			 * 	by the system's ActionScripts.
			 */
			String flexSDKPath = _applicationPath + File.separatorChar + FlexConstants.FLEX_SDK_DIRECTORY_NAME + File.separatorChar;
			
            String swcPath = _applicationPath + File.separatorChar + FlexConstants.SWC_DIRECTORY_NAME + File.separatorChar;
			String jsfFlexSwcPath = swcPath + FlexConstants.JSF_FLEX_MAIN_SWC_DIRECTORY_NAME + File.separatorChar;
			
			//externalLibraryPath will contain .swc file
			String swcFileAbsolutePath = jsfFlexSwcPath + FlexConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + FlexConstants.SWC_FILE_EXT;
            addExternalLibraryPath(swcFileAbsolutePath);
			
			//runtimeSharedLibrary has to be relative to the Web root path file
			String jsfFlexMainSwcWebpath = swfWebPath + FlexConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + FlexConstants.SWF_FILE_EXT;
            addRuntimeSharedLibrary(jsfFlexMainSwcWebpath);
			
			mxmlContext.setFlexSDKPath(flexSDKPath);
			mxmlContext.setMxmlPath(mxmlPath);
			mxmlContext.setApplicationSwfPath(applicationSwfPath);
			mxmlContext.setSwfPath(swfPath);
            mxmlContext.setJsfFlexSwcPath(jsfFlexSwcPath);
			mxmlContext.setSwcPath(swcPath);
			
			//set the attributes for jsfFlexFlashApplicationConfiguration
			JsfFlexFlashApplicationConfiguration jsfFlexFlashApplicationConfiguration = mxmlContext.getJsfFlexFlashApplicationConfiguration();
			String flashToJavaScriptLogLevel = context.getExternalContext().getInitParameter(FlexConstants.FLASH_TO_JAVASCRIPT_LOG_LEVEL_NAME);
			if(flashToJavaScriptLogLevel == null){
				
				flashToJavaScriptLogLevel = context.getExternalContext().getInitParameter(FlexConstants.CONFIG_MODE_NAME);
				if(flashToJavaScriptLogLevel.equals(FlexConstants.PRODUCTION_MODE)){
					flashToJavaScriptLogLevel = FlexConstants.FLASH_TO_JAVASCRIPT_LOG_WARN_LEVEL;
				}else{
					flashToJavaScriptLogLevel = FlexConstants.FLASH_TO_JAVASCRIPT_LOG_LOG_LEVEL;
				}
			}
			
			if(flashToJavaScriptLogLevel.equals(FlexConstants.FLASH_TO_JAVASCRIPT_LOG_LOG_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("1");
			}else if(flashToJavaScriptLogLevel.equals(FlexConstants.FLASH_TO_JAVASCRIPT_LOG_DEBUG_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("2");
			}else if(flashToJavaScriptLogLevel.equals(FlexConstants.FLASH_TO_JAVASCRIPT_LOG_INFO_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("3");
			}else if(flashToJavaScriptLogLevel.equals(FlexConstants.FLASH_TO_JAVASCRIPT_LOG_WARN_LEVEL)){
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("4");
			}else {
				jsfFlexFlashApplicationConfiguration.setFlashToJavaScriptLogMode("5");
			}
			
			String preMxmlPath = _applicationPath + File.separatorChar + FlexConstants.PREMXML_DIRECTORY_NAME + File.separatorChar +
										getMxmlPackageName() + File.separatorChar;
			mxmlContext.setPreMxmlPath(preMxmlPath);
			
			//Does this even need to be present within the JSF-component or should it passed as default within the task?
			setAccessible(true);
			
		}
		
		super.encodeBegin(context);
	}
	
	public void encodeEnd(FacesContext context) throws IOException {
		
		AbstractJsfFlexResource jsfFlexResource = AbstractJsfFlexResource.getInstance();
		jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_CORE_JS);
		jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_LOGGER_JS);
		
		super.encodeEnd(context);
	}
	
	public JSONObject getComponentInitValues(){
    	return null;
    }
	
	public synchronized AbstractAnnotationDocletParser getAnnotationDocletParserInstance(){
		
		if(_annotationDocletParserInstance == null){
			AbstractFlexContext mxmlContext = AbstractFlexContext.getCurrentInstance();
			AbstractRunnerFactory runnerFactoryInstance = mxmlContext.getRunnerFactoryInstance();
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
	public Collection<String> getExternalLibraryPath() {
		return _externalLibraryPath;
	}
	public void addExternalLibraryPath(String externalLibraryPath) {
        _externalLibraryPath.add(externalLibraryPath);
    }
    public Collection<String> getRuntimeSharedLibraries() {
        return _runtimeSharedLibraries;
    }
    public void addRuntimeSharedLibrary(String runtimeSharedLibrary) {
        _runtimeSharedLibraries.add(runtimeSharedLibrary);
    }
    public Map<String, String> getXmlnsMap() {
        return _xmlnsMap;
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
     * Additional xmlns in form of key/value where key is the prefix and the value being the url.
     */
    @JSFProperty(desc   =   "Additional xmlns in form of key/value where key is the prefix and the value being the url.")
    public abstract Map<String, String> getProvidedAdditionalXmlnsMap();

	/**
	 * This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that are needed for the current SWF generation are located at.
	 */
    @JSFProperty(desc   =   "This value will be passed to the mxmlc compiler when creating a SWF. It must be an absolutePath to a filesystem where additional ActionScript and MXML files that are needed for the current SWF generation are located at.")
	public abstract Collection<String> getSourcePath();
    
    /**
     * This value represents Collection of additional SWC files. For instance, if one wishes to use additional open source projects or self projects that is archived as a SWC file, JSF Flex will unzip those files and place them within the same directory that JSF Flex's SWC file is extracted to to create links for the to be created SWF file.
     */
    @JSFProperty(desc   =   "This value represents Collection of additional SWC files. For instance, if one wishes to use additional open source projects or self projects that is archived as a SWC files, JSF Flex will unzip those files and place them within the same directory that JSF Flex's SWC file is extracted to to create dynamic links for the to be created SWF file.")
    public abstract Collection<String> getProvidedAdditionalExternalLibaryPath();
    
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