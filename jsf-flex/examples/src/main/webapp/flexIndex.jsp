<%--
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
  --%>

<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<html>

<head>
    <link href="../css/example.css" rel="stylesheet"></link>
</head>

<f:view>
	<body>
	<div class="sectionDescription">
		Welcome to JSF Flex Project
	</div>
	<br />
	
	<div class="subSectionDescription">
		<span class="descriptionHeading">
			Quick bounding examples?
		</span>
		<span class="descriptionText">
			<ul>
				<li> flexSimple representing the bare skeleton of creating a flexApplication </li>
				<li> flexAsynchronousDataUpdateEventListener representing capability of listening to a specific event of a source Flex component, so that when it is triggered target Flex component's value is updated with the value returned from an asynchronous call that is bound by MethodExpression on the server side </li>
				<li> flexAdditionalComponent representing capability of creating a component that is outside of JSF Flex project [by specifying flex name + attributes as tag attributes] </li>
				<li> flexOverallExample representing overall example of the components </li>
			</ul>
		</span>
	</div>
	<h:panelGrid>
		<h:outputLink value="flexSimple/flexSimple.jsf">
			<f:verbatim>A page for testing a simple creation of flexApplication</f:verbatim>
		</h:outputLink>
		<h:outputLink value="eventGlue/flexAsynchronousDataUpdateEventListener.jsf">
			<f:verbatim>A page for testing an asynchronous data update event listener</f:verbatim>
		</h:outputLink>
		<h:outputLink value="eventGlue/flexAsynchronousPropertyUpdateEventListener.jsf">
            <f:verbatim>A page for testing an asynchronous property update event listener</f:verbatim>
        </h:outputLink>
		<h:outputLink value="additionalComponent/flexAdditionalComponent.jsf">
            <f:verbatim>A page showing usage of flexAdditionalComponent </f:verbatim>
        </h:outputLink>
        <h:outputLink
			value="dataGridFilteringExample/flexDataGridFilteringExample.jsf">
			<f:verbatim>An example demonstrating filtering a data grid's content with an another Flex component's value</f:verbatim>
		</h:outputLink>
		<h:outputLink value="overallExample/flexOverallExample.jsf">
			<f:verbatim>An overall example of the components</f:verbatim>
		</h:outputLink>
	</h:panelGrid>
	
	<br />
	<div class="subSectionDescription">
		<span class="descriptionHeading">
			Specific component examples
		</span>
		<span class="descriptionText">
			<ul>
				Note :
				<li> there exists much TODO for these components, such as allowing better dataBinding and etcetera, so
				please do not take them as finished components.</li>
				<li> after tweaking of creating the components/tags, one MUST change the com.googlecode.jsfFlex.MODE 
				field within web.xml to productionMode to avoid the cost of creating preMxml, Mxml, Swf, and etceteras.</li>
				<li> for components that allow dataBinding many use MXMLOverallExampleBean. However since 
				flexOverallExample page demonstrates preservation of values during postBack phase, these example 
				pages do not contain any success pages. Of course this may change when free time can be allocated 
				in creating these success pages.</li>
				<li> for creating static xml data and xml data through dataBinding, look to example such as flexTree.xhtml </li>
				<li> for complex Flex examples and how some of these components should be used, please refer to Adobe's page.</li>
			</ul>
		</span>
	</div>
	
	<h:panelGrid>
		<h:outputLink value="componentExamples/flexAccordion.jsf">
			<f:verbatim>An example for Accordion component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexApplicationControlBar.jsf">
			<f:verbatim>An example for AccordionControlBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexBox.jsf">
			<f:verbatim>An example for Box component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexButtonScript.jsf">
			<f:verbatim>An example for Button component that presents an alert with ActionScript</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexButtonBar.jsf">
			<f:verbatim>An example for ButtonBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexCanvas.jsf">
			<f:verbatim>An example for Canvas component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexCheckBox.jsf">
			<f:verbatim>An example for CheckBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexColorPicker.jsf">
			<f:verbatim>An example for ColorPicker component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexComboBox.jsf">
			<f:verbatim>An example for ComboBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexControlBar.jsf">
			<f:verbatim>An example for ControlBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink
			value="multipleRequestDataGridExample/flexMultipleRequestDataGridExample.jsf">
			<f:verbatim>An example demonstrating a data grid that performs multiple request for large data that is fetched asynchronously</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexDateField.jsf">
			<f:verbatim>An example for DataField component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexDividedBox.jsf">
			<f:verbatim>An example for DividedBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="dataGridDragDropExample/flexDataGridDragDropExample.jsf">
			<f:verbatim>An example for Drag + Drop of DataGrid component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="other/flexProvidedFlexSDK.jsf">
            <f:verbatim>An example for providing specific Flex SDK for JSF Flex</f:verbatim>
        </h:outputLink>
		<h:outputLink value="componentExamples/flexHorizontalList.jsf">
			<f:verbatim>An example for HorizontalList component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexHRule.jsf">
			<f:verbatim>An example for HRule component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexHScrollBar.jsf">
			<f:verbatim>An example for HScrollBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexHSlider.jsf">
			<f:verbatim>An example for HSlider component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexImage.jsf">
			<f:verbatim>An example for Image component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexLabel.jsf">
			<f:verbatim>An example for Label component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexLinkBar.jsf">
			<f:verbatim>An example for LinkBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexLinkButton.jsf">
			<f:verbatim>An example for LinkButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexList.jsf">
			<f:verbatim>An example for List component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexLocale.jsf">
			<f:verbatim>An example for Locale component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexMenuBar.jsf">
			<f:verbatim>An example for MenuBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexNumericStepper.jsf">
			<f:verbatim>An example for NumericStepper component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexPanel.jsf">
			<f:verbatim>An example for Panel component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexPopUpButton.jsf">
			<f:verbatim>An example for PopUpButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexPopUpMenuButton.jsf">
			<f:verbatim>An example for PopUpMenuButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexProgressBar.jsf">
			<f:verbatim>An example for ProgressBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexRadioButton.jsf">
			<f:verbatim>An example for RadioButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexRichTextEditor.jsf">
			<f:verbatim>An example for RichTextEditor component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexSpacer.jsf">
			<f:verbatim>An example for Spacer component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexSwfLoader.jsf">
			<f:verbatim>An example for SwfLoader component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTabBar.jsf">
			<f:verbatim>An example for TabBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTabNavigator.jsf">
			<f:verbatim>An example for TabNavigator component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexText.jsf">
			<f:verbatim>An example for Text component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTextArea.jsf">
			<f:verbatim>An example for TextArea component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTextInput.jsf">
			<f:verbatim>An example for TextInput component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTile.jsf">
			<f:verbatim>An example for Tile component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTileList.jsf">
			<f:verbatim>An example for TileList component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTitleWindow.jsf">
			<f:verbatim>An example for TitleWindow component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexToggleButtonBar.jsf">
			<f:verbatim>An example for ToggleButtonBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexTree.jsf">
			<f:verbatim>An example for Tree component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexVideoDisplay.jsf">
			<f:verbatim>An example for VideoDisplay component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexViewStack.jsf">
			<f:verbatim>An example for ViewStack component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexVRule.jsf">
			<f:verbatim>An example for VRule component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexVScrollBar.jsf">
			<f:verbatim>An example for VScrollBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/flexVSlider.jsf">
			<f:verbatim>An example for VSlider component</f:verbatim>
		</h:outputLink>
	</h:panelGrid>
	
	</body>
</f:view>

</html>