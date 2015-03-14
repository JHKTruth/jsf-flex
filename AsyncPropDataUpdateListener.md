# Introduction #

This page provides a quick overview of the projects's `jf:flexAsynchronousPropertyUpdateEventListener`, note that `jf:flexAsynchronousDataUpdateEventListener` component is very similar so it will not be described within a Wiki page, component which allows listening to an event for a Flex component and retrieving the value for the component and returning data which will be set for the Flex component.

# Details #
  * Initial view of the `jf:flexNumericStepper` component which will be listened to and `jf:flexTextArea` component which will be used to set the value returned from the server side.
> > ![http://jsf-flex.googlecode.com/svn/wiki/initialAsynchronousPropertyUpdateView.jpg](http://jsf-flex.googlecode.com/svn/wiki/initialAsynchronousPropertyUpdateView.jpg)
  * After -
    1. a change has been made to the `jf:flexNumericStepper` component which triggered an asynchronous call to be made to the server side.
    1. A value is returned from the server side with the sent data and `jf:flexTextArea` component is updated with the value.
> > ![http://jsf-flex.googlecode.com/svn/wiki/afterAsynchronousPropertyUpdateView.jpg](http://jsf-flex.googlecode.com/svn/wiki/afterAsynchronousPropertyUpdateView.jpg)
  * The method for the `ManagedBean`that listens to the event for the `jf:flexNumericStepper` component and returns appropriate value for the `jf:flexTextArea` component
```
    public Object asyncPropertyUpdateListener(AsynchronousPropertyUpdateEvent event){
    	/*
         * Two possible values can be returned for the MethodExpression of flexAsynchronousPropertyUpdateEventListener
         *  AsynchronousPropertyUpdateEventBean
         *  An Object representing the value to update the target component to
         * 
         */
    	
    	return "Async Property Update: " + event.getCurrSourceValue();
    }
```
  * The XHTML content for the example:
```
<jf:flexHGroup>
    <jf:flexAttributeNode name="height" value="100%"/>
    <jf:flexAttributeNode name="width" value="100%"/>
    
    <jf:flexNumericStepper id="srcComp" value="#{flexFaceletOverallBean.numericStepperValue}">
        <jf:flexAttributeNode name="minimum" value="0"/>
	<jf:flexAttributeNode name="maximum" value="10"/>
    </jf:flexNumericStepper>
    		    	
    <jf:flexTextArea id="tgtComp" text="#{flexFaceletOverallBean.textInputText}" />
</jf:flexHGroup>

<jf:flexAsynchronousPropertyUpdateEventListener eventHandlerTgtId="tgtComp" eventHandlerSrcId="srcComp" 
sourcePropertyDelim="value" targetPropertyDelim="text" eventListener="change" 
asynchronousEventPropertyUpdateGlueHandler="#{flexFaceletOverallBean.asyncPropertyUpdateListener}"/>
```