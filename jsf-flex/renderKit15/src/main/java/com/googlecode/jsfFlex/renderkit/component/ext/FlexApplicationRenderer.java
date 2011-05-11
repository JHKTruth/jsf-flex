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
import javax.faces.render.FacesRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttribute;
import com.googlecode.jsfFlex.renderkit.annotation.IJsfFlexAttributeProperties;
import com.googlecode.jsfFlex.renderkit.component.AbstractFlexComponentBaseRenderer;
import com.googlecode.jsfFlex.renderkit.flex.AbstractFlexResponseWriter;
import com.googlecode.jsfFlex.shared.adapter.IFlexApplicationContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.templates.TokenValue;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.util.FlexAttributeConstants;
import com.googlecode.jsfFlex.shared.util.FlexConstants;
import com.googlecode.jsfFlex.shared.util.FlexJsfUtil;

/**
 * Aside from its normal task of mapping the field to the Set and creating the preMxml file, FlexApplicationRenderer has<br>
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
        renderKitId="FLEX_BASIC",
        family="javax.faces.FlexApplication",
        type="com.googlecode.jsfFlex.FlexApplication"
)
@FacesRenderer(
		renderKitId="FLEX_BASIC",
		componentFamily="javax.faces.FlexApplication",
		rendererType="com.googlecode.jsfFlex.FlexApplication"
)
@IJsfFlexAttributeProperties(
		componentName="Application",
        componentNameSpace="s",

		jsfFlexAttributes=@IJsfFlexAttribute(attribute="initialize")
)
public final class FlexApplicationRenderer extends AbstractFlexComponentBaseRenderer {
	
	private final static Log _log = LogFactory.getLog(FlexApplicationRenderer.class);
	
	private static final String FLEX_APPLICATION_BODY_TEMPLATE  = "FlexApplicationBody.vm";
	
	private static final String TO_BE_CREATED_ADDITIONAL_APP_SCRIPT_CONTENT_TEMPLATE_SUFFIX = "AdditionalAppScriptContent.tmp";
	private static final String ADDITIONAL_APPLICATION_SCRIPT_CONTENT_TOKEN = "additionalApplicationScriptContent";
	
	private final FlexApplicationHTMLRenderer _flexApplicationHtmlRenderer;
	
	{
        _flexApplicationHtmlRenderer = new FlexApplicationHTMLRenderer();
	}
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent componentObj) throws IOException {
		super.encodeBegin(context, componentObj);
		
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		IFlexApplicationContract componentFlex = IFlexApplicationContract.class.cast( componentObj );
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        
        if(flexContext.isProductionEnv()){
            return;
        }
        
        writer.unZipFlexSDK(componentFlex);
        writer.mapFields(FlexApplicationRenderer.class, componentObj, null);
        
		/*
		 * Place in xmlns provided by the user + default for Flex application
		 */
        Set<TokenValue> tokenValueSet = componentFlex.getAnnotationDocletParserInstance().getTokenValueSet();
        TokenValue width = new TokenValue("width", null);
        TokenValue height = new TokenValue("height", null);
        for(TokenValue currTokenValue : tokenValueSet){
            if(currTokenValue.equals(width)){
                componentObj.getAttributes().put("width", currTokenValue.getValue());
            }
            if(currTokenValue.equals(height)){
                componentObj.getAttributes().put("height", currTokenValue.getValue());
            }
        }
        
        Map<String, String> xmlnsMap = componentFlex.getXmlnsMap();
        for(String currXmlnsKey : xmlnsMap.keySet()){
            String currXmlnsValue = xmlnsMap.get(currXmlnsKey);
            tokenValueSet.add(new TokenValue(currXmlnsKey, currXmlnsValue));
        }
        
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent componentObj) throws IOException {
		
		IFlexApplicationContract componentFlex = IFlexApplicationContract.class.cast( componentObj );
		AbstractFlexResponseWriter writer = AbstractFlexResponseWriter.class.cast( context.getResponseWriter() );
		
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
		String mxmlFile = flexContext.getMxmlPath() + flexContext.getCurrMxml() + FlexConstants.MXML_FILE_EXT;
		Map<String, String> multiLingualSupportMap = writer.getMultiLingualSupportMap();
		
		if(!flexContext.isProductionEnv()){
			//means it is of debugMode, so must create mxml and etcetera
			
			/* Beginning of creating application body content dynamically [since need to allow additional application script content] */
			AdditionalApplicationScriptContent additionalAppScriptContent = flexContext.getAdditionalAppScriptContent();
			String filePath = flexContext.getPreMxmlPath() + flexContext.getCurrMxml() + TO_BE_CREATED_ADDITIONAL_APP_SCRIPT_CONTENT_TEMPLATE_SUFFIX;
			Map<String, AdditionalApplicationScriptContent> tokenMap = new HashMap<String, AdditionalApplicationScriptContent>();
			tokenMap.put(ADDITIONAL_APPLICATION_SCRIPT_CONTENT_TOKEN, additionalAppScriptContent);
			writer.createFileContent(filePath, FLEX_APPLICATION_BODY_TEMPLATE, null, tokenMap);
			
			String bodyContent = writer.readFileContent(filePath);
			writer.createPreMxml(componentFlex, FlexApplicationRenderer.class.getAnnotation(IJsfFlexAttributeProperties.class), bodyContent);
			/* End of creating application body content dynamicall */
			
			Map<Integer, Set<IFlexContract>> preMxmlMap = flexContext.getPreMxmlCompMap();
			
			if(preMxmlMap.keySet().size() > 0){
				
				//Application must be a top component with others as children component
				for(Integer currMajor : preMxmlMap.keySet()){
					Set<IFlexContract> siblingSet = preMxmlMap.get(currMajor);
					
					for(IFlexContract currComp : siblingSet){
					    
						if(currComp.getMinorLevel() == 0){
							writer.replaceTokenWithValue(componentFlex.getAbsolutePathToPreMxmlFile(), writer.readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
									writer.childReplaceTokenWithPreMxmlIdentifier(currComp));
							
							_log.debug("Replacing token with value as a child for " + currComp.getAbsolutePathToPreMxmlFile());
						}else{
							writer.replaceTokenWithValue(componentFlex.getAbsolutePathToPreMxmlFile(), writer.readFileContent(currComp.getAbsolutePathToPreMxmlFile()), 
									writer.siblingReplaceTokenWithPreMxmlIdentifier(currComp));
							_log.debug("Replacing token with value as a sibling for " + currComp.getAbsolutePathToPreMxmlFile());
						}
						
					}
					
				}
				
				writer.processCreateSwf(mxmlFile, componentFlex, multiLingualSupportMap);
				
			}
			//finished with all tasks, so clear all future tasks if they have not been cleared yet
            writer.shutDownFutureTasks();
		}
		
		_flexApplicationHtmlRenderer.renderHtmlContent(context, componentObj, multiLingualSupportMap);
		
	}
	
	private final class FlexApplicationHTMLRenderer {
		
		private static final String APP_ID = "appId";
		private static final String NAMING_CONTAINER_PREFIX = "namingContainerPrefix";
		private static final String INIT_VALUE_OBJECTS = "initValueObjects";
		
        private static final String JS_ADD_EVENT_LISTENER = "com.googlecode.jsfFlex.communication.core.domHelpers.addEventListener";
		private static final String JS_COMMUNICATION_CORE_NS = "com.googlecode.jsfFlex.communication.core";
		private static final String JS_COMMUNICATION_CORE_PAGE_LOAD_NS = "com.googlecode.jsfFlex.communication.core.pageLoad";
		
		private void renderHtmlContent(FacesContext context, UIComponent component, Map<String, String> multiLingualSupportMap) throws IOException {
			
			AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
			com.googlecode.jsfFlex.component.ext.FlexUIApplication appComponent = 
                                        com.googlecode.jsfFlex.component.ext.FlexUIApplication.class.cast( component );
			
			Map<Integer, Set<IFlexContract>> preMxmlCompMap = flexContext.getPreMxmlCompMap();
			preMxmlCompMap.clear();
			
			ResponseWriter writer = context.getResponseWriter();
			
			writer.startElement(FlexAttributeConstants.SCRIPT_ELEM, component);
			writer.writeAttribute(FlexAttributeConstants.SCRIPT_TYPE_ATTR, FlexAttributeConstants.SCRIPT_TYPE_TEXT_JAVASCRIPT, null);
			
			//print out the JSON objects here
			StringBuilder toWrite = new StringBuilder();
			
            toWrite.append(JS_ADD_EVENT_LISTENER);
			toWrite.append("(window, 'load', null, function(){");
			toWrite.append(JS_COMMUNICATION_CORE_NS);
			toWrite.append(".addFlashApp(");
			
			toWrite.append(getComponentInitValues(context, component));
			
			toWrite.append(");");
			toWrite.append("}, null, false, true);");
			
            toWrite.append(JS_ADD_EVENT_LISTENER);
            toWrite.append("(window, 'load', null, ");
            toWrite.append(JS_COMMUNICATION_CORE_PAGE_LOAD_NS);
            toWrite.append(", null, false, true);");
			writer.write(toWrite.toString());
			writer.endElement(FlexAttributeConstants.SCRIPT_ELEM);
			
			String swfFile = getLocaleSwfFile(flexContext, context, appComponent, multiLingualSupportMap);
			
			writeHTMLSWF(writer, appComponent, swfFile);
			
		}
		
		private String getLocaleSwfFile(AbstractFlexContext flexContext, FacesContext context, 
											com.googlecode.jsfFlex.component.ext.FlexUIApplication appComponent, Map<String, String> multiLingualSupportMap){
			
			String localeWebContextPath = flexContext.getLocaleWebContextPath();
			String swfFile = null;
			
			if(localeWebContextPath == null){
				swfFile = flexContext.getApplicationSwfWebPath() + appComponent.getMxmlPackageName() + FlexConstants.SWF_FILE_EXT;
			}else{
				
				Locale preferredLocale = context.getExternalContext().getRequestLocale();
				
				String languageMatch = preferredLocale.getLanguage();
				languageMatch = languageMatch == null ? "" : languageMatch.toUpperCase().trim();
				
				String countryMatch = preferredLocale.getCountry();
				countryMatch = countryMatch == null ? "" : countryMatch.toUpperCase().trim();
				int countryMatchLength = countryMatch.length();
				
				String defaultLocale = context.getExternalContext().getInitParameter(FlexConstants.DEFAULT_LOCALE);
				defaultLocale = defaultLocale != null ? defaultLocale : "";
				String defaultLocalePath = flexContext.getApplicationSwfWebPath() + appComponent.getMxmlPackageName() + 
											FlexConstants.SWF_FILE_NAME_LOCALE_SEPARATOR + defaultLocale + FlexConstants.SWF_FILE_EXT;
				String closestMatch = null;
				for(String currCountryLocale : multiLingualSupportMap.keySet()){
					String currCountryLocaleMatch = currCountryLocale.toUpperCase().trim();
					
					if(currCountryLocaleMatch.indexOf(languageMatch) == 0){
						closestMatch = flexContext.getApplicationSwfWebPath() + appComponent.getMxmlPackageName() + 
											FlexConstants.SWF_FILE_NAME_LOCALE_SEPARATOR + currCountryLocale + FlexConstants.SWF_FILE_EXT;
						
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
        
        private JSONObject buildJSONObjectSwfHTMLWrapperContent(com.googlecode.jsfFlex.component.ext.FlexUIApplication appComponent, String swfFile) throws JSONException {
            
            JSONObject swfHTMLWrapperContent = appComponent.getSwfHTMLWrapperContent();
            if(swfHTMLWrapperContent == null) {
                swfHTMLWrapperContent = new JSONObject();
            }
            
            JSONObject objectElement = null;
            
            try{
                objectElement = swfHTMLWrapperContent.getJSONObject("object");
            }catch(JSONException jsonException) {
                objectElement = new JSONObject();
                swfHTMLWrapperContent.put("object", objectElement);
            }
            
            objectElement.put(FlexAttributeConstants.ID_ATTR, appComponent.getMxmlPackageName());
            
            Object heightO = appComponent.getAttributes().get(FlexAttributeConstants.HEIGHT_ATTR);
            Object widthO = appComponent.getAttributes().get(FlexAttributeConstants.WIDTH_ATTR);
            
            String height = (heightO == null) ? "100%" : String.class.cast( heightO );
            String width = (widthO == null) ? "100%" : String.class.cast( widthO );
            
            objectElement.put(FlexAttributeConstants.HEIGHT_ATTR, height);
            objectElement.put(FlexAttributeConstants.WIDTH_ATTR, width);
            
            /*
             * For below check if the property exists and if not, provide the system default value
             */
            try{
                objectElement.getString("classid");
            }catch(JSONException jsonException) {
                objectElement.put("classid", FlexConstants.CLASS_ID);
            }
            
            try{
                objectElement.getString("codebase");
            }catch(JSONException jsonException) {
                objectElement.put("codebase", FlexConstants.CODE_BASE);
            }
            
            JSONObject objectEmbed = null;
            try{
                objectEmbed = objectElement.getJSONObject("embed");
            }catch(JSONException jsonException) {
                objectEmbed = new JSONObject();
                objectElement.put("embed", objectEmbed);
            }
            
            objectEmbed.put(FlexAttributeConstants.NAME_ATTR, appComponent.getMxmlPackageName());
            objectEmbed.put(FlexAttributeConstants.SRC_ATTR, swfFile);
            objectEmbed.put(FlexAttributeConstants.HEIGHT_ATTR, height);
            objectEmbed.put(FlexAttributeConstants.WIDTH_ATTR, width);
            
            try{
                objectEmbed.getString("allowScriptAccess");
            }catch(JSONException jsonException) {
                objectEmbed.put("allowScriptAccess", "sameDomain");
            }
            
            try{
                objectEmbed.getString("pluginspage");
            }catch(JSONException jsonException) {
                objectEmbed.put("pluginspage", FlexConstants.PLUGINS_PAGE);
            }
            
            JSONArray paramList = null;
            try{
                paramList = objectElement.getJSONArray("param");
            }catch(JSONException jsonException) {
                paramList = new JSONArray();
                /*
                 * If one provides param, one should provide the value of allowScriptAccess
                 */
                
                JSONObject paramAllowScriptAccess = new JSONObject();
                paramAllowScriptAccess.put(FlexAttributeConstants.NAME_ATTR, "allowScriptAccess");
                paramAllowScriptAccess.put(FlexAttributeConstants.VALUE_ATTR, "sameDomain");
                paramList.put(paramAllowScriptAccess);
                
                objectElement.put("param", paramList);
            }
            
            JSONObject paramSrc = new JSONObject();
            paramSrc.put(FlexAttributeConstants.NAME_ATTR, "src");
            paramSrc.put(FlexAttributeConstants.VALUE_ATTR, swfFile);
            paramList.put(paramSrc);
            
            return swfHTMLWrapperContent;
        }
        
        private void parseJSONObjectPrintHTMLSWF(ResponseWriter writer, com.googlecode.jsfFlex.component.ext.FlexUIApplication appComponent, 
                                                    JSONObject object) throws JSONException, IOException {
            
            Map<String, JSONArray> jsonArrays = new HashMap<String, JSONArray>();
            Map<String, JSONObject> jsonObjects = new HashMap<String, JSONObject>();
            for(Iterator keysIterate = object.keys(); keysIterate.hasNext();) {
                String key = keysIterate.next().toString();
                Object value = object.get(key);

                if(value instanceof JSONObject) {
                    JSONObject objectEntry = JSONObject.class.cast( value );
                    jsonObjects.put(key, objectEntry);
                }else if(value instanceof JSONArray) {
                    JSONArray array = JSONArray.class.cast( value );
                    jsonArrays.put(key, array);
                }else {
                    writer.writeAttribute(key, value.toString(), null);
                }
            }
            
            /*
             * Since one has to set all the attributes of an element prior to starting 
             * a new element, below data structure are used. Personally would have liked it 
             * if JSF kept track of which element was still open, but whatever
             */
            for(String key : jsonArrays.keySet()) {
                JSONArray array = jsonArrays.get(key);
                for(int i=0; i < array.length(); i++) {
                    writer.startElement(key, appComponent);
                    parseJSONObjectPrintHTMLSWF(writer, appComponent, array.getJSONObject(i));
                    writer.endElement(key);
                }
            }
            
            for(String key : jsonObjects.keySet()) {
                JSONObject objectEntry = jsonObjects.get(key);
                writer.startElement(key, appComponent);
                parseJSONObjectPrintHTMLSWF(writer, appComponent, objectEntry);
                writer.endElement(key);
            }
            
        }
		
		private void writeHTMLSWF(ResponseWriter writer, com.googlecode.jsfFlex.component.ext.FlexUIApplication appComponent, 
									String swfFile) throws IOException{
			
            JSONObject swfHTMLWrapperContent = new JSONObject();
            
            try{
                swfHTMLWrapperContent = buildJSONObjectSwfHTMLWrapperContent(appComponent, swfFile);
                parseJSONObjectPrintHTMLSWF(writer, appComponent, swfHTMLWrapperContent);
            }catch(JSONException jsonException) {
                throw new IOException(jsonException);
            }
            
		}
		
		private String getComponentInitValues(FacesContext context, UIComponent component) {
			
			AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
			List<JSONObject> applicationInitValueList = flexContext.getApplicationInitValueList();
			
			JSONObject flashAppObject = new JSONObject();
			
			try{
				flashAppObject.put(APP_ID, flexContext.getCurrMxml());
				flashAppObject.put(NAMING_CONTAINER_PREFIX, FlexJsfUtil.retrieveFormId( component.getClientId(context) ));
				
				if(applicationInitValueList.size() > 0){
					
					JSONArray initValueObjects = new JSONArray();
					flashAppObject.put(INIT_VALUE_OBJECTS, initValueObjects);
					
					for(JSONObject currComponentObject : applicationInitValueList){
						initValueObjects.put(currComponentObject);
					}
					
				}
				
			}catch(JSONException jsonException){
				_log.info("Error while generating JSON content", jsonException);
			}
			
			return flashAppObject.toString();
		}
		
	}
	
}
