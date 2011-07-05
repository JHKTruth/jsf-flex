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
import java.util.Iterator;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.component.MXMLUISelectedIndexBase;
import com.googlecode.jsfFlex.component.attributes._MXMLUIControlSkinAttributes;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIDataProviderCollectionAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIEditableAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIImeModeAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUIRestrictAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUISelectedItemAttribute;
import com.googlecode.jsfFlex.component.attributes._MXMLUITextAttribute;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIBaseAttributes;
import com.googlecode.jsfFlex.component.attributes.compBase._MXMLUIComboBaseAttributes;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.util.MXMLJsfUtil;

/**
 * @JSFComponent
 *   name     = "jf:mxmlComboBox"
 *   class    = "com.googlecode.jsfFlex.component.ext.MXMLUIComboBox"
 *   type     = "com.googlecode.jsfFlex.MXMLUIComboBox"
 *   tagClass = "com.googlecode.jsfFlex.taglib.ext.MXMLUIComboBoxTag"
 *   family   = "javax.faces.MXMLInput"
 *   defaultRendererType	= "com.googlecode.jsfFlex.MXMLComboBox"
 *   tagSuperclass 			= "com.googlecode.jsfFlex.taglib.MXMLUIInputTagBase"
 *   
 * @JSFJspProperties
 * 		properties	=		
 *   						@JSFJspProperty
 * 							 name		= "dropdownFactory"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The IFactory that creates a ListBase-derived instance to use as the drop-down."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dropdownWidth"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Width of the drop-down list, in pixels."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "prompt"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The prompt for the ComboBox control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "dropDownBorderColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The color of the border of the ComboBox."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dropDownStyleName"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The name of a CSSStyleDeclaration to be used by the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "rowCount"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Maximum number of rows visible in the control."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemRenderer"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "IFactory that generates the instances that displays the data for the drop-down list of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectionDuration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionDuration of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "labelField"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "The name of the field in the data provider items to display as the label."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "labelFunction"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "User-supplied function to run on each item to determine its label."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "alternatingItemColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The set of BackgroundColors for drop-down list rows in an alternating pattern."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "selectionEasingFunction"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionEasingFunction of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "arrowButtonWidth"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Width of the arrow button in pixels."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "borderColor"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of the border."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "borderThickness"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Bounding box thickness."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "backgroundGradientColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Specifies the colors used to tint the background gradient fill of the application."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "closeEasingFunction"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Easing function to control component tweening."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "closeDuration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Length of a close transition, in milliseconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "color"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "cornerRadius"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Radius of component corners."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "disabledColor"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Color of text in the component if it is disabled."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fillAlphas"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alphas used for the background fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fillColors"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Colors used to tint the background of the control."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusAlpha"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies the alpha transparency value of the focus skin."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "focusRoundedCorners"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Specifies which corners of the focus rectangle should be rounded."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontGridFitType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the gridFitType property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontSharpness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the sharpness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontAntiAliasType"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the antiAliasType property of internal TextFields."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontThickness"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Sets the thickness property of internal TextFields that represent text in Flex controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontStyle"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is italic font."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontWeight"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is boldface."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "fontSize"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Height of the text, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "fontFamily"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Name of the font to use."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "highlightAlphas"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Alphas used for the highlight fill of controls."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "leading"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Additional vertical space between lines of text."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "openDuration"
 *   						 returnType = "java.lang.String"
 *  						 longDesc	= "Length of an open or close transition, in milliseconds."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "openEasingFunction"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Easing function to control component tweening."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "paddingLeft"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Number of pixels between the container's left border and the left edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "paddingRight"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Number of pixels between the container's right border and the right edge of its content area."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "rollOverColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The rollOverColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "selectionColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "The selectionColor of the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textIndent"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Offset of first line of text from the left side of the container, in pixels."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textDecoration"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Determines whether the text is underlined."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textAlign"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Alignment of text within a container."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textSelectedColor"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Text color of the label as the user presses it."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "textRollOverColor"
 *   						 returnType	= "java.lang.String" 
 *   						 longDesc	= "Text color of the label as the user moves the mouse pointer over the button."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "change"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the selectedIndex or selectedItem property changes as a result of user interaction."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "close"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the drop-down list is dismissed for any reason."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "dataChange"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the data property changes."
 *  						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "enter"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched if the editable property is set to true and the user presses the Enter key while typing in the editable text field."
 *   						, 
 *   						
 *   						@JSFJspProperty
 *   						 name		= "itemRollOver"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "itemRollOut"
 *   						 returnType	= "java.lang.String"
 *   						 longDesc	= "Dispatched when the user rolls the mouse over a drop-down list item."
 *   						,
 *   						
 *   						@JSFJspProperty
 *   						 name		= "open"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user clicks the drop-down button to display the drop-down list."
 *   						,
 *   						
 *   						@JSFJspProperty
 * 							 name		= "scroll"
 *  						 returnType = "java.lang.String"
 *  						 longDesc	= "Dispatched when the user manually scrolls the container."
 * 
 * AbstractMXMLUIComboBox is a special case where the preserving of the state of the "text" field<br>
 * is held within the code. Main reason is because it extends MXMLUISelectedIndexBase and there exists<br>
 * no reason to create an another base class to preserve both "selectedIndex" + "text".<br>
 * @author Ji Hoon Kim
 */
public abstract class AbstractMXMLUIComboBox 
						extends MXMLUISelectedIndexBase
						implements _MXMLUIComboBaseAttributes, _MXMLUIBaseAttributes, _MXMLUITextAttribute, 
						_MXMLUIControlSkinAttributes, _MXMLUIDataProviderAttribute, _MXMLUIEditableAttribute, 
						_MXMLUIImeModeAttribute, _MXMLUISelectedItemAttribute, _MXMLUIRestrictAttribute,
						_MXMLUIDataProviderCollectionAttribute {
	
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
		
		Collection dataProviderCollection = getDataProviderCollection();
		if(dataProviderCollection != null && dataProviderCollection.size() > 0){
			//For AbstractMXMLUIComboBox, entries within the collection must be of type SelectItem
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			AdditionalApplicationScriptContent additionalApplicationScriptContent = mxmlContext.getAdditionalAppScriptContent();
			additionalApplicationScriptContent.addActionScriptImport(COMBO_BOX_UICOMPONENT_PACKAGE_IMPORT);
			
			JSONArray comboBoxContent = new JSONArray();
			for(Iterator iterate = dataProviderCollection.iterator(); iterate.hasNext();){
				SelectItem currSelectItem = (SelectItem) iterate.next();
				
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
    	
    	Map requestMap = context.getExternalContext().getRequestParameterMap();
    	
    	String textId = getId() + TEXT_ID_APPENDED;
    	String textUpdateVal = (String) requestMap.get(textId);
    	
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
