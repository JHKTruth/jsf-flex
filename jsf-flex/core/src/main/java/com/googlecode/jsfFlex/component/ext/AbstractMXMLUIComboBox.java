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

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes._MXMLUIAlternatingItemColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIArrowButtonWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIBorderThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICornerRadiusAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderCollectionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropDownBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropDownStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropdownFactoryAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDropdownWidthAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIEnterAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFillColorsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusAlphaAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFocusRoundedCornersAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontAntiAliasTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontFamilyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontGridFitTypeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSharpnessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontStyleAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontThicknessAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIFontWeightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIHighlightAlphasAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIIconColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRendererAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOverAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPromptAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIRowCount;
import com.googlecode.jsfFlex.attributes._MXMLUIScrollAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectionEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextRollOverColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextSelectedColorAttribute;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.util.MXMLJsfUtil;

/**
 * AbstractMXMLUIComboBox is a special case where the preserving of the state of the "text" field<br>
 * is held within the code. Main reason is because it extends MXMLUISelectedIndexBase and there exists<br>
 * no reason to create an another base class to preserve both "selectedIndex" + "text".<br>
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlComboBox",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIComboBox",
        type                =   "com.googlecode.jsfFlex.MXMLUIComboBox",
        tagClass            =   "com.googlecode.jsfFlex.taglib.component.ext.MXMLUIComboBoxTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLComboBox",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIComboBox 
						extends com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase
						implements _MXMLUIComboBaseAttributes, _MXMLUIDropdownFactoryAttribute, _MXMLUIDropdownWidthAttribute, 
                        _MXMLUIItemRendererAttribute, _MXMLUILabelFieldAttribute, _MXMLUILabelFunctionAttribute, 
                        _MXMLUIPromptAttribute, _MXMLUIRowCount, _MXMLUIAlternatingItemColorsAttribute, _MXMLUIArrowButtonWidthAttribute, 
                        _MXMLUIBorderColorAttribute, _MXMLUIBorderThicknessAttribute, _MXMLUICloseDurationAttribute, 
                        _MXMLUICloseEasingFunctionAttribute, _MXMLUIColorAttribute, _MXMLUICornerRadiusAttribute, 
                        _MXMLUIDisabledColorAttribute, _MXMLUIDisabledIconColorAttribute, _MXMLUIDropDownBorderColorAttribute, 
                        _MXMLUIDropDownStyleNameAttribute, _MXMLUIFillAlphasAttribute, _MXMLUIFillColorsAttribute, _MXMLUIFocusAlphaAttribute, 
                        _MXMLUIFocusRoundedCornersAttribute, _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, 
                        _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, 
                        _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, 
                        _MXMLUIHighlightAlphasAttribute, _MXMLUIIconColorAttribute, _MXMLUILeadingAttribute, _MXMLUIOpenDurationAttribute, 
                        _MXMLUIOpenEasingFunctionAttribute, _MXMLUIPaddingTopAttribute, _MXMLUIPaddingBottomAttribute, 
                        _MXMLUIPaddingLeftAttribute, _MXMLUIPaddingRightAttribute, _MXMLUIRollOverColorAttribute, 
                        _MXMLUISelectionColorAttribute, _MXMLUISelectionDurationAttribute, _MXMLUISelectionEasingFunctionAttribute, 
                        _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
                        _MXMLUITextRollOverColorAttribute, _MXMLUITextSelectedColorAttribute, _MXMLUIChangeAttribute, 
                        _MXMLUICloseAttribute, _MXMLUIDataChangeAttribute, _MXMLUIEnterAttribute, _MXMLUIItemRollOutAttribute, 
                        _MXMLUIItemRollOverAttribute, _MXMLUIOpenAttribute, _MXMLUIScrollAttribute, _MXMLUIDataProviderCollectionAttribute, 
                        _MXMLUIDataProviderAttribute, _MXMLUIEditableAttribute, _MXMLUISelectedIndexAttribute, 
                        _MXMLUITextAttribute {
	
	private final static Log _log = LogFactory.getLog(AbstractMXMLUIComboBox.class);
	
	private static final String COMBO_BOX_UICOMPONENT_PACKAGE_IMPORT = "mx.controls.ComboBox";
	
	private static final String DATA_PROPERTY = "data";
	private static final String LABEL_PROPERTY = "label";
	
	private static final String TEXT_ATTR = "text";
	private static final String TEXT_ID_APPENDED = "_text";
	
	private JSONObject initValue;
	
	{
		try{
			initValue = new JSONObject();
			initValue.put(ATTRIBUTE, TEXT_ATTR);
			
			_initValues.put(initValue);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		super.populateComponentInitValues();
		
		try{
			if(getText() != null){
				initValue.put(VALUE, MXMLJsfUtil.escapeCharacters( getText() ));
			}
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		
		Collection<? extends Object> dataProviderCollection = getDataProviderCollection();
		if(dataProviderCollection != null && dataProviderCollection.size() > 0){
			//For AbstractMXMLUIComboBox, entries within the collection must be of type SelectItem
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			AdditionalApplicationScriptContent additionalApplicationScriptContent = mxmlContext.getAdditionalAppScriptContent();
			additionalApplicationScriptContent.addActionScriptImport(COMBO_BOX_UICOMPONENT_PACKAGE_IMPORT);
			
			JSONArray comboBoxContent = new JSONArray();
			for(Object currInstace : dataProviderCollection){
				SelectItem currSelectItem = SelectItem.class.cast( currInstace );
				
				JSONObject comboBoxEntry = new JSONObject();
				
				try{
					comboBoxEntry.put(DATA_PROPERTY, currSelectItem.getValue().toString());
					comboBoxEntry.put(LABEL_PROPERTY, currSelectItem.getLabel());
					comboBoxContent.put(comboBoxEntry);
				}catch(JSONException jsonException){
					_log.info("Error setting the following content for dataProviderCollection " + 
											currSelectItem.getValue() + currSelectItem.getLabel(), jsonException);
				}
				
			}
			
			additionalApplicationScriptContent.addSimpleDataProviderSetter(getId(), comboBoxContent.toString());
			
		}
		
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String textId = getId() + TEXT_ID_APPENDED;
    	String textUpdateVal = requestMap.get(textId);
    	
    	if(textUpdateVal != null){
    		setText(textUpdateVal);
    	}
    }
    
    public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueExpression ve = getValueExpression(TEXT_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getText());
    		setText(null);
    	}
    	
    }
	
}
