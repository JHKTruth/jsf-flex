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
import java.util.EnumSet;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;
import org.json.JSONObject;

import com.googlecode.jsfFlex.renderkit.annotationDocletParser.AbstractAnnotationDocletParser;
import com.googlecode.jsfFlex.renderkit.html.util.AbstractJsfFlexResource;
import com.googlecode.jsfFlex.shared.adapter.IFlexContract;
import com.googlecode.jsfFlex.shared.adapter.IFlexEvent;
import com.googlecode.jsfFlex.shared.adapter.IFlexEvent.EVENT_HANDLER_TYPE.JAVA_SCRIPT_IMPORT;
import com.googlecode.jsfFlex.shared.context.AbstractFlexContext;
import com.googlecode.jsfFlex.shared.tasks.AbstractRunnerFactory;
import com.googlecode.jsfFlex.shared.util.FlexConstants;
import com.googlecode.jsfFlex.shared.util.FlexJsfUtil;

/**
 * This component should be used as the base action of the component if the component<br>
 * does not require any preservation of values during the post-back phase and require functionality of<br>
 * UICommand<br>
 * 
 * @author Ji Hoon Kim
 */
@JSFComponent(
        type    =   "com.googlecode.jsfFlex.FlexUICommandBase",
        family  =   "javax.faces.FlexCommandBase",
        desc    =   "Base component for FlexCommand components"
)
@FacesComponent("com.googlecode.jsfFlex.FlexUICommandBase")
public abstract class AbstractFlexUICommandBase 
                            extends UICommand 
                            implements IFlexContract, IFlexEvent {
    
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
    
    public AbstractFlexUICommandBase(){
        super();
    }
    
    /**
     * Usually one does not provide overriding of this method; however there are certain cases where one desires to 
     * provide additional parameters [i.e. AbstractFlexUIAsynchronousPropertyUpdateEventListener]
     * 
     * @return
     */
    public JSONObject getAddtionalArguments(){
        return null;
    }
    
    public JSONObject getComponentInitValues(){
        return null;
    }
    
    public String getEventHandlerSrcId() {
        return getId();
    }
    
    public String getEventHandlerTgtId() {
        FacesContext currInstance = FacesContext.getCurrentInstance();
        return FlexJsfUtil.retrieveFormId(getClientId(currInstance));
    }
    
    public String getNameSpaceOverride(){
        return null;
    }
    
    public synchronized AbstractAnnotationDocletParser getAnnotationDocletParserInstance(){
        
        if(_annotationDocletParserInstance == null){
            AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
            AbstractRunnerFactory runnerFactoryInstance = flexContext.getRunnerFactoryInstance();
            _annotationDocletParserInstance = runnerFactoryInstance.getAnnotationDocletParserImpl();
        }
        
        return _annotationDocletParserInstance;
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        
        AbstractFlexContext flexContext = AbstractFlexContext.getCurrentInstance();
        
        if(flexContext.isProductionEnv()){
            //means no need to create preMxml files
            setRendered(false);
        }
        
        //need to check whether to add content to AdditionalApplicationScriptContent for submission of form
        if(getAction() != null || getActionExpression() != null || getActionListener() != null){
            
            EVENT_HANDLER_TYPE eventHandlerType = getEventHandlerType();
            AbstractJsfFlexResource jsfFlexResource = AbstractJsfFlexResource.getInstance();
            EnumSet<JAVA_SCRIPT_IMPORT> javaScriptImports = eventHandlerType.getJavaScriptImports();
            
            for(JAVA_SCRIPT_IMPORT currJSImport : javaScriptImports){
                jsfFlexResource.addResource(AbstractFlexUICommandBase.class, currJSImport.getJavaScriptImport());
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
    
    @Override
    public void processDecodes(FacesContext context) {
        String mode = context.getExternalContext().getInitParameter(FlexConstants.CONFIG_MODE_NAME);
        if(mode == null || mode.equals(FlexConstants.PRODUCTION_MODE)){
            //need to dataBind so set back to true
            setRendered(true);
        }
        
        super.processDecodes(context);
    }
    
    public String getEventHandlerId(){
        return getId();
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
    		rtexprvalue =   true,
            literalOnly =   true,
            desc        =   "Id of the component."
    )
    @Override
    public String getId(){
        return super.getId();
    }
    
    /**
     * The action to take when this command is invoked.
     * <p>
     * If the value is an expression, it is expected to be a method binding EL expression that identifies
     * an action method. An action method accepts no parameters and has a String return value, called the
     * action outcome, that identifies the next view displayed. The phase that this event is fired in
     * can be controlled via the immediate attribute.
     * </p>
     * <p> 
     * If the value is a string literal, it is treated as a navigation outcome for the current view. This
     * is functionally equivalent to a reference to an action method that returns the string literal.
     * </p>
     */
    @JSFProperty(
            stateHolder     =   true,
            jspName         =   "action",
            returnSignature =   "java.lang.Object",
            desc            =   "Specifies the action to take when this command is invoked."
    )
    @Override
    public MethodExpression getActionExpression(){
        return super.getActionExpression();
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
    		stateHolder     =   true,
            returnSignature =   "void",
            methodSignature =   "javax.faces.event.ActionEvent",
            desc            =   "A method binding EL expression that identifies an action listener method to be invoked if this component is activated by the user."
    )
    @Override
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
    		defaultValue    =   "false",
            desc            =   "A boolean value that identifies the phase during which action events should fire."
    )
    @Override
    public boolean isImmediate(){
        return super.isImmediate();
    }
    
}
