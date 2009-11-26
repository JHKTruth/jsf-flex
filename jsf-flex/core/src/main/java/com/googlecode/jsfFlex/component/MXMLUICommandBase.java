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
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;
import org.json.JSONObject;

import com.googlecode.jsfFlex.renderkit.annotationDocletParser._AnnotationDocletParser;
import com.googlecode.jsfFlex.renderkit.html.util.JsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.AdditionalApplicationScriptContent;
import com.googlecode.jsfFlex.shared.beans.additionalScriptContent.EventHandler.EVENT_HANDLER_TYPE;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.tasks._RunnerFactory;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * This component should be used as the base action of the component if the component<br>
 * does not require any preservation of values during the post-back phase and require functionality of<br>
 * UICommand<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.MXMLUICommandBase",
        family  =   "javax.faces.MXMLCommandBase",
        desc    =   "Base component for MXMLCommand components"
)
public abstract class MXMLUICommandBase extends UICommand implements _MXMLContract {
    
    private static final String JSF_FLEX_COMMUNICATOR_EVENT_JS = "jsfFlexCommunicatorEvent.js";
    private static final String ABSTRACT_EVENT_HANDLER_IMPORT = "com.googlecode.jsfFlex.communication.event.AbstractEventHandler";
    private static final String SUBMIT_FORM_EVENT_HANDLER_IMPORT = "com.googlecode.jsfFlex.communication.event.SubmitFormEventHandler";
    
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
    
    public MXMLUICommandBase(){
        super();
    }
    
    protected abstract String getEventHandlerSrcId();
    
    protected abstract String getEventHandlerTgtId();
    
    protected abstract EVENT_HANDLER_TYPE getEventHandlerType();
    
    protected abstract String getEventHandlerEventName();
    
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
    
    public void encodeBegin(FacesContext context) throws IOException {
        
        MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
        if(mxmlContext.isSimplySWF() || mxmlContext.isProductionEnv()){
            //means no need to create preMxml files
            setRendered(false);
        }else{
            //need to check whether to add content to AdditionalApplicationScriptContent for submission of form
            if(getAction() != null || getActionExpression() != null || getActionListener() != null){
                
                AdditionalApplicationScriptContent additionalApplicationScriptContent = mxmlContext.getAdditionalAppScriptContent();
                additionalApplicationScriptContent.addActionScriptImport(ABSTRACT_EVENT_HANDLER_IMPORT);
                additionalApplicationScriptContent.addActionScriptImport(SUBMIT_FORM_EVENT_HANDLER_IMPORT);
                additionalApplicationScriptContent.addEventHandler(getEventHandlerSrcId(), getEventHandlerTgtId(), 
                        getEventHandlerType(), getEventHandlerEventName());
                
                JsfFlexResource jsfFlexResource = JsfFlexResource.getInstance();
                jsfFlexResource.addResource(MXMLUICommandBase.class, JSF_FLEX_COMMUNICATOR_EVENT_JS);
            }
            
        }
        
        super.encodeBegin(context);
    }
    
    @Override
    public void decode(FacesContext context) {
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        String value = requestMap.get(getEventHandlerSrcId());
        if(value != null){
            //must have been triggered
            queueEvent(new ActionEvent(this));
        }
    }
    
    public void processDecodes(FacesContext context) {
        String mode = context.getExternalContext().getInitParameter(MXMLConstants.CONFIG_MODE_NAME);
        if(mode == null || mode.equals(MXMLConstants.SIMPLY_SWF_MODE) || mode.equals(MXMLConstants.PRODUCTION_MODE)){
            //need to dataBind so set back to true
            setRendered(true);
        }
        
        super.processDecodes(context);
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
     * Specifies the action to take when this command is invoked.
     *
     * If the value is an expression, it is expected to be a method 
     * binding EL expression that identifies an action method. An action method
     * accepts no parameters and has a String return value, called the action
     * outcome, that identifies the next view displayed. The phase that this
     * event is fired in can be controlled via the immediate attribute.
     *
     * If the value is a string literal, it is treated as a navigation outcome
     * for the current view.  This is functionally equivalent to a reference to
     * an action method that returns the string literal.
     * 
     */
    @JSFProperty(
            inheritTag      =   true,
            returnSignature =   "java.lang.String",
            desc            =   "Specifies the action to take when this command is invoked."
    )
    public MethodBinding getAction(){
        return super.getAction();
    }
    
    /**
     * A method binding EL expression that identifies an action listener method
     * to be invoked if this component is activated by the user. An action
     * listener method accepts a parameter of type javax.faces.event.ActionEvent
     * and returns void. The phase that this event is fired in can be controlled
     * via the immediate attribute.
     *  
     */
    @JSFProperty(
            inheritTag      =   true,
            stateHolder     =   true,
            returnSignature =   "void",
            methodSignature =   "javax.faces.event.ActionEvent",
            desc            =   "A method binding EL expression that identifies an action listener method to be invoked if this component is activated by the user."
    )
    public MethodBinding getActionListener(){
        return super.getActionListener();
    }
    
    /**
     * A boolean value that identifies the phase during which action events
     * should fire. During normal event processing, action methods and
     * action listener methods are fired during the "invoke application"
     * phase of request processing. If this attribute is set to "true",
     * these methods are fired instead at the end of the "apply request
     * values" phase.
     * 
     */
    @JSFProperty(
            inheritTag      =   true,
            defaultValue    =   "false",
            desc            =   "A boolean value that identifies the phase during which action events should fire."
    )
    public boolean isImmediate(){
        return super.isImmediate();
    }
    
}
