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

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.attributes._MXMLUIBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIChangeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICloseEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIColorFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIDisabledIconColorAttribute;
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
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOutAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIItemRollOverAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILabelFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUILeadingAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenDurationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIOpenEasingFunctionAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingBottomAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingLeftAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingRightAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIPaddingTopAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISelectedIndexAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIShowTextFieldAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISwatchBorderColorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISwatchBorderSizeAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUISwatchPanelStyleNameAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAlignAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextDecorationAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUITextIndentAttribute;
import com.googlecode.jsfFlex.component.MXMLUIInputBase;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlColorPicker",
        clazz               =   "com.googlecode.jsfFlex.component.ext.MXMLUIColorPicker",
        type                =   "com.googlecode.jsfFlex.MXMLUIColorPicker",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUIColorPickerTag",
        family              =   "javax.faces.MXMLInput",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLColorPicker",
        tagSuperclass       =   "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
)
public abstract class AbstractMXMLUIColorPicker 
						extends MXMLUIInputBase
                        implements _MXMLUIComboBaseAttributes, _MXMLUIColorFieldAttribute, _MXMLUILabelFieldAttribute, 
                        _MXMLUIShowTextFieldAttribute, _MXMLUIBorderColorAttribute, _MXMLUICloseDurationAttribute, 
                        _MXMLUICloseEasingFunctionAttribute, _MXMLUIColorAttribute, _MXMLUIDisabledIconColorAttribute, 
                        _MXMLUIFillAlphasAttribute, _MXMLUIFillColorsAttribute, _MXMLUIFocusAlphaAttribute, 
                        _MXMLUIFocusRoundedCornersAttribute, _MXMLUIFontAntiAliasTypeAttribute, _MXMLUIFontFamilyAttribute, 
                        _MXMLUIFontGridFitTypeAttribute, _MXMLUIFontSharpnessAttribute, _MXMLUIFontSizeAttribute, 
                        _MXMLUIFontStyleAttribute, _MXMLUIFontThicknessAttribute, _MXMLUIFontWeightAttribute, 
                        _MXMLUIHighlightAlphasAttribute, _MXMLUIIconColorAttribute, _MXMLUILeadingAttribute, 
                        _MXMLUIOpenDurationAttribute, _MXMLUIOpenEasingFunctionAttribute, _MXMLUIPaddingBottomAttribute, 
                        _MXMLUIPaddingLeftAttribute, _MXMLUIPaddingRightAttribute, _MXMLUIPaddingTopAttribute, 
                        _MXMLUISwatchBorderColorAttribute, _MXMLUISwatchBorderSizeAttribute, _MXMLUISwatchPanelStyleNameAttribute, 
                        _MXMLUITextAlignAttribute, _MXMLUITextDecorationAttribute, _MXMLUITextIndentAttribute, 
                        _MXMLUIChangeAttribute, _MXMLUICloseAttribute, _MXMLUIEnterAttribute, _MXMLUIItemRollOutAttribute,
                        _MXMLUIItemRollOverAttribute, _MXMLUIOpenAttribute, _MXMLUIDataProviderAttribute, 
                        _MXMLUIEditableAttribute, _MXMLUISelectedIndexAttribute, _MXMLUITextAttribute,
                        _MXMLUISelectedColorAttribute {
	
	private final static Log _log = LogFactory.getLog(AbstractMXMLUIColorPicker.class);
	
	private static final String SELECTED_COLOR_ATTR = "selectedColor";
	private static final String SELECTED_COLOR_ID_APPENDED = "_selectedColor";
	
	private JSONObject initValue;
	
	{
		try{
			initValue = new JSONObject();
			initValue.put(ATTRIBUTE, SELECTED_COLOR_ATTR);
			
			_initValues.put(initValue);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	protected void populateComponentInitValues(){
		try{
			if(getSelectedColor() != null){
				initValue.put(VALUE, getSelectedColor());
			}
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
	
	public void decode(FacesContext context) {
    	super.decode(context);
    	
    	Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String selectedColorId = getId() + SELECTED_COLOR_ID_APPENDED;
    	String selectedColorUpdateVal = requestMap.get(selectedColorId);
    	
    	if(selectedColorUpdateVal != null){
    		setSelectedColor(selectedColorUpdateVal);
    		setSubmittedValue(selectedColorUpdateVal);
    	}
    	
    }
	
	public void processUpdates(FacesContext context) {
    	super.processUpdates(context);
    	
    	if (!isRendered() || !isValid()){
    		return;
    	}
    	
    	ValueExpression ve = getValueExpression(SELECTED_COLOR_ATTR);
    	
    	if(ve != null && !ve.isReadOnly(context.getELContext())){
    		ve.setValue(context.getELContext(), getSelectedColor());
    		setSelectedColor(null);
    	}
    	
    }
	
}
