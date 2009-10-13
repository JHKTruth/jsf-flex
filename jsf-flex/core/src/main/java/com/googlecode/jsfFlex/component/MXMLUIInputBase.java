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

import com.googlecode.jsfFlex.renderkit.annotationDocletParser._AnnotationDocletParser;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.tasks._RunnerFactory;
import com.googlecode.jsfFlex.shared.util.MXMLAttributeConstants;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * This class will process the needed actions of creating JSONObject and JSONArray needed<br>
 * by subclasses to preserve the state of beans during the post back phase.<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.MXMLUIInputBase",
        family  =   "javax.faces.MXMLInputBase",
        desc    =   "Base component for MXMLInput components"
)
public abstract class MXMLUIInputBase extends UIInput implements _MXMLContract {
	
	private final static Log _log = LogFactory.getLog(MXMLUIInputBase.class);
	
	protected static final String ATTRIBUTE = "attribute";
	protected static final String VALUE = "value";
	/*
     * Below parameter should be inserted into initValue JSONObject when 
     * specific ActionScript Object needs to be constructed for component's 
     * initial value [i.e. AbstractMXMLUIDateChooser]
	 */
    protected static final String SPECIFIC_OBJECT_TYPE_INIT = "specificObjectTypeInit";
    
    private static final String INIT_VALUES = "initValues";
	
	protected JSONArray _initValues;
	
	private JSONObject _componentInitValueObject;
	
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
	
	public MXMLUIInputBase(){
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
	
	public JSONObject getComponentInitValues(){
		return _componentInitValueObject;
    }
	
	public void encodeBegin(FacesContext context) throws IOException {
		
		try{
			_componentInitValueObject.put(MXMLAttributeConstants.ID_ATTR, getId());
		}catch(JSONException jsonException){
			_log.info("Error while formatting to JSON content", jsonException);
		}
		
		populateComponentInitValues();
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		List<JSONObject> applicationInitValueList = mxmlContext.getApplicationInitValueList();
		if(getComponentInitValues() != null){
			applicationInitValueList.add(getComponentInitValues());
		}
		
    	if(mxmlContext.isSimplySWF() || mxmlContext.isProductionEnv()){
			//means no need to create preMxml files
			setRendered(false);
		}
    	
    	super.encodeBegin(context);
	}
	
	public void processDecodes(FacesContext context) {
		String mode = context.getExternalContext().getInitParameter(MXMLConstants.CONFIG_MODE_NAME);
		if(mode == null || mode.equals(MXMLConstants.SIMPLY_SWF_MODE) || mode.equals(MXMLConstants.PRODUCTION_MODE)){
			//need to dataBind so set back to true
			setRendered(true);
		}
		
		super.processDecodes(context);
	}
	
	public synchronized _AnnotationDocletParser getAnnotationDocletParserInstance(){
		
		if(_annotationDocletParserInstance == null){
			MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
			_RunnerFactory runnerFactoryInstance = mxmlContext.getRunnerFactoryInstance();
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
            desc        =   "Id of the component."
    )
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
    public Converter getConverter(){
    	return super.getConverter();
    }
	
}