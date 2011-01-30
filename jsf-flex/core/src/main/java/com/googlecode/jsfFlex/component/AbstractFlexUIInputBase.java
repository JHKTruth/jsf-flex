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
package com.googlecode.jsfFlex.component;

import java.io.IOException;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.jsfFlex.renderkit.annotationDocletParser.AbstractAnnotationDocletParser;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.tasks.AbstractRunnerFactory;
import com.googlecode.jsfFlex.shared.util.FlexAttributeConstants;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * This class will process the needed actions of creating JSONObject and JSONArray needed<br>
 * by subclasses to preserve the state of beans during the post back phase.<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.FlexUIInputBase",
        family  =   "javax.faces.FlexInputBase",
        desc    =   "Base component for FlexInput components"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUIInputBase")
public abstract class AbstractFlexUIInputBase extends UIInput implements IFlexContract {
	
	private final static Log _log = LogFactory.getLog(AbstractFlexUIInputBase.class);
	
	protected static final String ATTRIBUTE = "attribute";
	protected static final String VALUE = "value";
	/*
     * Below parameter should be inserted into initValue JSONObject when 
     * specific ActionScript Object needs to be constructed for component's 
     * initial value [i.e. AbstractFlexUIDateChooser]
	 */
    protected static final String SPECIFIC_OBJECT_TYPE_INIT = "specificObjectTypeInit";
    
    private static final String INIT_VALUES = "initValues";
	
	protected JSONArray _initValues;
	
	private JSONObject _componentInitValueObject;
	
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
	
	public AbstractFlexUIInputBase(){
		super();
	}
	
	{
		try{
			_componentInitValueObject = new JSONObject();
			_initValues = new JSONArray();
			
			_componentInitValueObject.put(INIT_VALUES, _initValues);
			
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
	}
    
    public String getNameSpaceOverride(){
        return null;
    }
	
    public JSONObject getComponentInitValues(){
		return _componentInitValueObject;
    }
	
    @Override
	public void encodeBegin(FacesContext context) throws IOException {
		
		try{
			_componentInitValueObject.put(FlexAttributeConstants.ID_ATTR, getId());
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
		
		populateComponentInitValues();
		
		AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
		List<JSONObject> applicationInitValueList = flexContext.getApplicationInitValueList();
		if(getComponentInitValues() != null){
			applicationInitValueList.add(getComponentInitValues());
		}
		
    	if(flexContext.isProductionEnv()){
			//means no need to create preMxml files
			setRendered(false);
		}
    	
    	super.encodeBegin(context);
	}
	
    @Override
	public void processDecodes(FacesContext context) {
		String mode = context.getExternalContext().getInitParameter(FlexConstants.CONFIG_MODE_NAME);
		if(mode == null || mode.equals(FlexConstants.PRODUCTION_MODE)){
			//need to dataBind so set back to true
			setRendered(true);
		}
		
		super.processDecodes(context);
	}
	
	public synchronized AbstractAnnotationDocletParser getAnnotationDocletParserInstance(){
		
		if(_annotationDocletParserInstance == null){
			AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
			AbstractRunnerFactory runnerFactoryInstance = flexContext.getRunnerFactoryInstance();
			_annotationDocletParserInstance = runnerFactoryInstance.getAnnotationDocletParserImpl();
		}
		
		return _annotationDocletParserInstance;
	}
	
	protected abstract void populateComponentInitValues();
	
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
     * Id of the component.
     */
    @JSFProperty(
            inheritTag  =   true,
            rtexprvalue =   true,
            literalOnly =   true,
            desc        =   "Id of the component."
    )
    @Override
    public String getId(){
        return super.getId();
    }
    
	/**
	 * A boolean value that identifies the phase during which value change events should fire. During normal event processing, value change events are fired during the "invoke application" phase of request processing. If this attribute is set to true, these methods are fired instead at the end of the apply request values phase.
	 */
    @JSFProperty(
            inheritTag      =   true,
            desc            =   "A boolean value that identifies the phase during which value change events should fire. During normal event processing, value change events are fired during the 'invoke application' phase of request processing. If this attribute is set to true, these methods are fired instead at the end of the apply request values phase."
    )
    @Override
	public boolean isImmediate(){
		return super.isImmediate();
	}
	
	/**
     * A boolean value that indicates whether an input value is required.
     * If this value is true, and no input value is provided, the error
     * message javax.faces.component.UIInput.REQUIRED is posted.
     */
    @JSFProperty(
            inheritTag      =   true,
            desc            =   "A boolean value that indicates whether an input value is required. If this value is true, and no input value is provided, the error message javax.faces.component.UIInput.REQUIRED is posted."
    )
    @Override
	public boolean isRequired(){
		return super.isRequired();
	}
    
	/**
     * A method binding EL expression, accepting FacesContext, UIComponent,
     * and Object parameters, and returning void, that validates the
     * component's local value.
     */
    @JSFProperty(
            stateHolder     =   true,
            returnSignature =   "void",
            inheritTag      =   true,
            methodSignature =   "javax.faces.context.FacesContext,javax.faces.component.UIComponent,java.lang.Object"
    )
    @Override
	public MethodBinding getValidator(){
		return super.getValidator();
	}
    
    /**
     * A method binding EL expression, accepting a ValueChangeEvent parameter
     * and returning void. The specified method is invoked if this component
     * is modified. The phase that this handler is fired in can be controlled
     * via the immediate attribute.
     */
    @JSFProperty(
            stateHolder     =   true,
            returnSignature =   "void",
            inheritTag      =   true,
            methodSignature =   "javax.faces.event.ValueChangeEvent"
    )
    @Override
	public MethodBinding getValueChangeListener(){
		return super.getValueChangeListener();
	}
	
	/**
     * The value can either be a static value (ID) or an EL expression. When a static id is
     * specified, an instance of the converter type registered with that id is used. When this is an
     * EL expression, the result of evaluating the expression must be an object that implements the
     * Converter interface.
     */
    @JSFProperty(
            inheritTag      =   true,
            desc            =   "The value can either be a static value (ID) or an EL expression. When a static id is specified, an instance of the converter type registered with that id is used. When this is an EL expression, the result of evaluating the expression must be an object that implements the Converter interface."
    )
    @Override
    public Converter getConverter(){
    	return super.getConverter();
    }
	
}