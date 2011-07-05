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
package com.googlecode.jsfFlex.renderkit.component.ext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.JsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.MXMLContainerTemplateRenderer;
import com.googlecode.jsfFlex.renderkit.mxml.AbstractMXMLResponseWriter;
import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.tokenValue.TokenValue;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.util.MXMLAttributeConstants;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * Aside from its normal task of mapping the field to the Set and creating the preMxml file, MXMLApplicationRenderer has<br>
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
 * Other than merging of the preMxml file into a single preMxml file, all other tasks are performed by invoking processCreateSwf.<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFRenderer(
		renderKitId="MXML_BASIC",
		family="javax.faces.MXMLApplication",
		type="com.googlecode.jsfFlex.MXMLApplication"
)
@JsfFlexAttributeProperties(
		mxmlComponentName="Application",
		mxmlComponentNodeAttributes={},

		jsfFlexAttributes={
				@JsfFlexAttribute(attribute="controlBar", byMethod=false),
				@JsfFlexAttribute(attribute="frameRate", byMethod=false),
				@JsfFlexAttribute(attribute="layout", byMethod=false),
				@JsfFlexAttribute(attribute="pageTitle", byMethod=false),
				@JsfFlexAttribute(attribute="preloader", byMethod=false),
				@JsfFlexAttribute(attribute="resetHistory", byMethod=false),
				@JsfFlexAttribute(attribute="scriptRecursionLimit", byMethod=false),
				@JsfFlexAttribute(attribute="scriptTimeLimit", byMethod=false),
				@JsfFlexAttribute(attribute="usePreloader", byMethod=false),
				@JsfFlexAttribute(attribute="viewSourceURL", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundGradientAlphas", byMethod=false),
				@JsfFlexAttribute(attribute="backgroundGradientColors", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalAlign", byMethod=false),
				@JsfFlexAttribute(attribute="horizontalGap", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparency", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyBlur", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyColor", byMethod=false),
				@JsfFlexAttribute(attribute="modalTransparencyDuration", byMethod=false),
				@JsfFlexAttribute(attribute="verticalAlign", byMethod=false),
				@JsfFlexAttribute(attribute="verticalGap", byMethod=false),
				@JsfFlexAttribute(attribute="applicationComplete", byMethod=false),
				@JsfFlexAttribute(attribute="error", byMethod=false)
		}
)
public final class MXMLApplicationRenderer extends MXMLContainerTemplateRenderer {
	
	private final static Log _log = LogFactory.getLog(MXMLApplicationRenderer.class);
	
	private static final String MXML_APPLICATION_BODY_TEMPLATE  = "MXMLApplicationBody.vm";
	
	private static final String MX_KEY = "xmlns:mx";
	
	private static final String TO_BE_CREATED_ADDITIONAL_APP_SCRIPT_CONTENT_TEMPLATE_SUFFIX = "AdditionalAppScriptContent.tmp";
	private static final String ADDITIONAL_APPLICATION_SCRIPT_CONTENT_TOKEN = "additionalApplicationScriptContent";
	
	private final MXMLApplicationHTMLRenderer _mxmlApplicationHtmlRenderer;
	
	{
		_mxmlApplicationHtmlRenderer = new MXMLApplicationHTMLRenderer();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		_MXMLApplicationContract componentMXML = (_MXMLApplicationContract) componentObj;
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		
		if(mxmlContext.isSimplySWF() || mxmlContext.isProductionEnv()){
			return;
		}
		/*
		 * special case for MXMLApplication to filter out attribute "id"
		 * In Flex, id attribute is not allowed on the root tag of a component
		 */
		
		componentMXML.getAnnotationDocletParserInstance().getTokenValueSet().remove(new TokenValue("id", null));
		writer.mapFields(MXMLApplicationRenderer.class, componentObj, null);
		
		/*
		 * HACK
		 * Because of the colon, the detection of qdox is giving issues. So for the time
		 * being until a better solution is found, manually pull and push the info.
		 */
		componentMXML.getAnnotationDocletParserInstance().getTokenValueSet().add(new TokenValue(MX_KEY, componentMXML.getAttributes().get(MX_KEY).toString()));
		
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		
		_MXMLApplicationContract componentMXML = (_MXMLApplicationContract) componentObj;
		AbstractMXMLResponseWriter writer = (AbstractMXMLResponseWriter) context.getResponseWriter();
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		String mxmlFile = mxmlContext.getMxmlPath() + mxmlContext.getCurrMxml() + MXMLConstants.MXML_FILE_EXT;
		String localeWebContextPath = mxmlContext.getLocaleWebContextPath();
		Map multiLingualSupportMap = writer.getMultiLingualSupportMap();
		
		//check if simplySWF boolean flag is set and if so, create the SWF file and exit
		if(mxmlContext.isSimplySWF()){
			if(!new File(mxmlContext.getFlexSDKPath()).exists()){
				writer.makeDirectory(mxmlContext.getFlexSDKPath());
				writer.unZipArchiveRelative(MXMLConstants.FLEX_SDK_ZIP, mxmlContext.getFlexSDKPath());
			}
			
			writer.createSWF(mxmlFile, componentMXML, mxmlContext.getFlexSDKPath(), multiLingualSupportMap, localeWebContextPath);
			
		}else if(!mxmlContext.isProductionEnv()){
			//means it is of debugMode, so must create mxml and etcetera
			
			/* Beginning of creating application body content dynamically [since need to allow additional application script content] */
			AdditionalApplicationScriptContent additionalAppScriptContent = mxmlContext.getAdditionalAppScriptContent();
			String filePath = mxmlContext.getPreMxmlPath() + mxmlContext.getCurrMxml() + TO_BE_CREATED_ADDITIONAL_APP_SCRIPT_CONTENT_TEMPLATE_SUFFIX;
			Map<String, AdditionalApplicationScriptContent> tokenMap = new HashMap<String, AdditionalApplicationScriptContent>();
			tokenMap.put(ADDITIONAL_APPLICATION_SCRIPT_CONTENT_TOKEN, additionalAppScriptContent);
			writer.createFileContent(filePath, MXML_APPLICATION_BODY_TEMPLATE, null, tokenMap);
			
			String bodyContent = writer.readFileContent(filePath);
			writer.createPreMxml(componentMXML, MXMLApplicationRenderer.class.getAnnotation(JsfFlexAttributeProperties.class).mxmlComponentName(), bodyContent);
			/* End of creating application body content dynamicall */
			
			Map preMxmlMap = mxmlContext.getPreMxmlCompMap();
			
			if(preMxmlMap.keySet().size() > 0){
				
				//Application must be a top component with others as children component
				for(Iterator majorIterator = preMxmlMap.keySet().iterator(); majorIterator.hasNext();){
					
					Integer currMajor = (Integer) majorIterator.next();
					Set siblingSet = (Set) preMxmlMap.get(currMajor);
					
					for(Iterator siblingIterator = siblingSet.iterator(); siblingIterator.hasNext();){
					
						_MXMLContract currComp = (_MXMLContract) siblingIterator.next();
						
						if(currComp.getMinorLevel() == 0){
							writer.replaceTokenWithValue(componentMXML.getAbsolutePathToPreMxmlFile(), writer.readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
									writer.childReplaceTokenWithPreMxmlIdentifier(currComp));
							
							_log.debug("Replacing token with value as a child for " + currComp.getAbsolutePathToPreMxmlFile());
						}else{
							writer.replaceTokenWithValue(componentMXML.getAbsolutePathToPreMxmlFile(), writer.readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
									writer.siblingReplaceTokenWithPreMxmlIdentifier(currComp));
							_log.debug("Replacing token with value as a sibling for " + currComp.getAbsolutePathToPreMxmlFile());
						}
						
					}
					
				}
				
				writer.processCreateSwf(mxmlFile, componentMXML, multiLingualSupportMap);
				
			}
		
		}
		
		_mxmlApplicationHtmlRenderer.renderHtmlContent(context, componentObj, multiLingualSupportMap);
		
	}
	
	private final class MXMLApplicationHTMLRenderer {
		
		private static final String APP_ID = "appId";
		private static final String NAMING_CONTAINER_PREFIX = "namingContainerPrefix";
		private static final String INIT_VALUE_OBJECTS = "initValueObjects";
		
        private static final String JS_ADD_EVENT_LISTENER = "com.googlecode.jsfFlex.communication.core.domHelpers.addEventListener";
		private static final String JS_COMMUNICATION_CORE_NS = "com.googlecode.jsfFlex.communication.core";
		private static final String JS_COMMUNICATION_CORE_PAGE_LOAD_NS = "com.googlecode.jsfFlex.communication.core.pageLoad";
		
		private void renderHtmlContent(FacesContext context, UIComponent component, Map multiLingualSupportMap) throws IOException {
			
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			com.googlecode.jsfFlex.component.ext.MXMLUIApplication appComponent = 
												(com.googlecode.jsfFlex.component.ext.MXMLUIApplication) component;
			
			Map preMxmlCompMap = mxmlContext.getPreMxmlCompMap();
			preMxmlCompMap.clear();
			
			ResponseWriter writer = context.getResponseWriter();
			
			writer.startElement(MXMLAttributeConstants.SCRIPT_ELEM, component);
			writer.writeAttribute(MXMLAttributeConstants.SCRIPT_TYPE_ATTR, MXMLAttributeConstants.SCRIPT_TYPE_TEXT_JAVASCRIPT, null);
			
			//print out the JSON objects here
			StringBuilder toWrite = new StringBuilder();
			
            toWrite.append(JS_ADD_EVENT_LISTENER);
			toWrite.append("(window, 'load', null, function(){");
			toWrite.append(JS_COMMUNICATION_CORE_NS);
			toWrite.append(".addFlashApp(");
			
			toWrite.append(getComponentInitValues(context, component));
			
			toWrite.append(");");
			toWrite.append("}, null, false);");
			
            toWrite.append(JS_ADD_EVENT_LISTENER);
            toWrite.append("(window, 'load', null, ");
            toWrite.append(JS_COMMUNICATION_CORE_PAGE_LOAD_NS);
            toWrite.append(", null, false);");
			writer.write(toWrite.toString());
			writer.endElement(MXMLAttributeConstants.SCRIPT_ELEM);
			
			String swfFile = getLocaleSwfFile(mxmlContext, context, appComponent, multiLingualSupportMap);
			
			writeHTMLSWF(writer, appComponent, swfFile);
			
		}
		
		private String getLocaleSwfFile(MxmlContext mxmlContext, FacesContext context, 
											com.googlecode.jsfFlex.component.ext.MXMLUIApplication appComponent, Map multiLingualSupportMap){
			
			String localeWebContextPath = mxmlContext.getLocaleWebContextPath();
			String swfFile = null;
			
			if(localeWebContextPath == null){
				swfFile = mxmlContext.getSwfWebPath() + appComponent.getMxmlPackageName() + MXMLConstants.SWF_FILE_EXT;
			}else{
				
				Locale preferredLocale = context.getExternalContext().getRequestLocale();
				
				String languageMatch = preferredLocale.getLanguage();
				languageMatch = languageMatch == null ? "" : languageMatch.toUpperCase().trim();
				
				String countryMatch = preferredLocale.getCountry();
				countryMatch = countryMatch == null ? "" : countryMatch.toUpperCase().trim();
				int countryMatchLength = countryMatch.length();
				
				String defaultLocale = context.getExternalContext().getInitParameter(MXMLConstants.DEFAULT_LOCALE);
				defaultLocale = defaultLocale != null ? defaultLocale : "";
				String defaultLocalePath = mxmlContext.getSwfWebPath() + appComponent.getMxmlPackageName() + 
											MXMLConstants.SWF_FILE_NAME_LOCALE_SEPARATOR + defaultLocale + MXMLConstants.SWF_FILE_EXT;
				String closestMatch = null;
				for(Iterator iterate = multiLingualSupportMap.keySet().iterator(); iterate.hasNext();){
					String currCountryLocale = (String) iterate.next();
					String currCountryLocaleMatch = currCountryLocale.toUpperCase().trim();
					
					if(currCountryLocaleMatch.indexOf(languageMatch) == 0){
						closestMatch = mxmlContext.getSwfWebPath() + appComponent.getMxmlPackageName() + 
											MXMLConstants.SWF_FILE_NAME_LOCALE_SEPARATOR + currCountryLocale + MXMLConstants.SWF_FILE_EXT;
						
						int matchIndex = currCountryLocaleMatch.indexOf(countryMatch);
						if((matchIndex > -1 && (matchIndex + countryMatchLength) == currCountryLocaleMatch.length())){
							swfFile = closestMatch;
							break;
						}
					}
					
				}
				
				if(swfFile == null){
					swfFile = closestMatch != null ? closestMatch : defaultLocalePath;
				}
			
			}
			
			return swfFile;
		}
		
		private void writeHTMLSWF(ResponseWriter writer, com.googlecode.jsfFlex.component.ext.MXMLUIApplication appComponent, 
									String swfFile) throws IOException{
			
			Object heightO = appComponent.getAttributes().get(MXMLAttributeConstants.HEIGHT_ATTR);
			Object widthO = appComponent.getAttributes().get(MXMLAttributeConstants.WIDTH_ATTR);
			
			String height = (heightO == null) ? "100%" : (String) heightO;
			String width = (widthO == null) ? "100%" : (String) widthO;
			
			writer.startElement("object", appComponent);
			
			writer.writeAttribute(MXMLAttributeConstants.ID_ATTR, appComponent.getMxmlPackageName(), null);
			writer.writeAttribute("classid", MXMLConstants.CLASS_ID, null);
			writer.writeAttribute("codebase", MXMLConstants.CODE_BASE, null);
			writer.writeAttribute(MXMLAttributeConstants.HEIGHT_ATTR, height, null);
			writer.writeAttribute(MXMLAttributeConstants.WIDTH_ATTR, width, null);
			
			writer.startElement("param", appComponent);
			writer.writeAttribute(MXMLAttributeConstants.NAME_ATTR, "src", null);
			writer.writeAttribute(MXMLAttributeConstants.VALUE_ATTR, swfFile, null);
			writer.endElement("param");
			
			writer.startElement("param", appComponent);
			writer.writeAttribute(MXMLAttributeConstants.NAME_ATTR, "allowScriptAccess", null);
			writer.writeAttribute(MXMLAttributeConstants.VALUE_ATTR, "sameDomain", null);
			writer.endElement("param");
			
			writer.startElement("embed", appComponent);
			writer.writeAttribute(MXMLAttributeConstants.NAME_ATTR, appComponent.getMxmlPackageName(), null);
			writer.writeAttribute("allowScriptAccess", "sameDomain", null);
			writer.writeAttribute("pluginspage", MXMLConstants.PLUGINS_PAGE, null);
			writer.writeAttribute(MXMLAttributeConstants.SRC_ATTR, swfFile, null);
			writer.writeAttribute(MXMLAttributeConstants.HEIGHT_ATTR, height, null);
			writer.writeAttribute(MXMLAttributeConstants.WIDTH_ATTR, width, null);
			writer.endElement("embed");
			
			writer.endElement("object");
			
		}
		
		private String getComponentInitValues(FacesContext context, UIComponent component) {
			
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			List applicationInitValueList = mxmlContext.getApplicationInitValueList();
			
			JSONObject flashAppObject = new JSONObject();
			
			try{
				flashAppObject.put(APP_ID, mxmlContext.getCurrMxml());
				flashAppObject.put(NAMING_CONTAINER_PREFIX, getNamingContainerPrefer( component.getClientId(context) ));
				
				if(applicationInitValueList.size() > 0){
					
					JSONArray initValueObjects = new JSONArray();
					flashAppObject.put(INIT_VALUE_OBJECTS, initValueObjects);
					
					for(Iterator iterate = applicationInitValueList.iterator(); iterate.hasNext();){
						JSONObject currComponentObject = (JSONObject) iterate.next();
						initValueObjects.put(currComponentObject);
					}
					
				}
				
			}catch(JSONException jsonException){
				_log.info("Error while generating JSON content", jsonException);
			}
			
			return flashAppObject.toString();
		}
		
	}
	
	private String getNamingContainerPrefer(String toRetrieveFrom){
		int endIndex = toRetrieveFrom.lastIndexOf(':');
		return endIndex < 0 ? toRetrieveFrom : toRetrieveFrom.substring(0, toRetrieveFrom.lastIndexOf(':'));
	}
	
}
