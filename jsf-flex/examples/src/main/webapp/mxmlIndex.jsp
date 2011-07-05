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
<head>
<style type="text/css">
.sectionDescription{
	color:#708090;
	font-family:verdana;
	font-size:14px;
	font-weight:700;
	text-align:center;
	text-decoration:underline;
	width:50%;
}

.subSectionDescription{
	width:80%;
}

.subSectionDescription .descriptionHeading{
	color:FF9966;
	font-family:Helvetica;
	font-size:12px;
	font-weight:700;
	text-decoration:underline;
}

.subSectionDescription .descriptionText{
	font-family:Garamond;
	font-size: 15px;
}

</style>
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
				<li> mxmlSimple representing the bare skeleton of creating a mxmlApplication </li>
				<li> mxmlOverallExample representing overall example of the components </li>
			</ul>
		</span>
	</div>
	<h:panelGrid>
		<h:outputLink value="mxmlSimple/mxmlSimple.jsf">
			<f:verbatim>A page for testing a simple creation of mxmlApplication</f:verbatim>
		</h:outputLink>
		<h:outputLink value="overallExample/mxmlOverallExample.jsf">
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
				mxmlOverallExample page demonstrates preservation of values during postBack phase, these example 
				pages do not contain any success pages. Of course this may change when free time can be allocated 
				in creating these success pages.</li>
				<li> for creating static xml data and xml data through dataBinding, look to example such as mxmlTree.xhtml </li>
				<li> for complex Flex examples and how some of these components should be used, please refer to Adobe's page.</li>
			</ul>
		</span>
	</div>
	
	<h:panelGrid>
		<h:outputLink value="componentExamples/mxmlAccordion.jsf">
			<f:verbatim>An example for Accordion component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlApplicationControlBar.jsf">
			<f:verbatim>An example for AccordionControlBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlBox.jsf">
			<f:verbatim>An example for Box component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlButtonScript.jsf">
			<f:verbatim>An example for Button component that presents an alert with ActionScript</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlButtonBar.jsf">
			<f:verbatim>An example for ButtonBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlCanvas.jsf">
			<f:verbatim>An example for Canvas component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlCheckBox.jsf">
			<f:verbatim>An example for CheckBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlColorPicker.jsf">
			<f:verbatim>An example for ColorPicker component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlComboBox.jsf">
			<f:verbatim>An example for ComboBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlControlBar.jsf">
			<f:verbatim>An example for ControlBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink
			value="multipleRequestDataGridExample/mxmlMultipleRequestDataGridExample.jsf">
			<f:verbatim>An example demonstrating a data grid that performs multiple request for large data that is fetched asynchronously</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlDateField.jsf">
			<f:verbatim>An example for DataField component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlDividedBox.jsf">
			<f:verbatim>An example for DividedBox component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="dataGridDragDropExample/mxmlDataGridDragDropExample.jsf">
			<f:verbatim>An example for Drag + Drop of DataGrid component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlHorizontalList.jsf">
			<f:verbatim>An example for HorizontalList component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlHRule.jsf">
			<f:verbatim>An example for HRule component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlHScrollBar.jsf">
			<f:verbatim>An example for HScrollBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlHSlider.jsf">
			<f:verbatim>An example for HSlider component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlImage.jsf">
			<f:verbatim>An example for Image component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlLabel.jsf">
			<f:verbatim>An example for Label component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlLinkBar.jsf">
			<f:verbatim>An example for LinkBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlLinkButton.jsf">
			<f:verbatim>An example for LinkButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlList.jsf">
			<f:verbatim>An example for List component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlLocale.jsf">
			<f:verbatim>An example for Locale component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlMenuBar.jsf">
			<f:verbatim>An example for MenuBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlNumericStepper.jsf">
			<f:verbatim>An example for NumericStepper component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlPanel.jsf">
			<f:verbatim>An example for Panel component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlPopUpButton.jsf">
			<f:verbatim>An example for PopUpButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlPopUpMenuButton.jsf">
			<f:verbatim>An example for PopUpMenuButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlProgressBar.jsf">
			<f:verbatim>An example for ProgressBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlRadioButton.jsf">
			<f:verbatim>An example for RadioButton component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlRichTextEditor.jsf">
			<f:verbatim>An example for RichTextEditor component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlSpacer.jsf">
			<f:verbatim>An example for Spacer component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlSwfLoader.jsf">
			<f:verbatim>An example for SwfLoader component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTabBar.jsf">
			<f:verbatim>An example for TabBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTabNavigator.jsf">
			<f:verbatim>An example for TabNavigator component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlText.jsf">
			<f:verbatim>An example for Text component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTextArea.jsf">
			<f:verbatim>An example for TextArea component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTextInput.jsf">
			<f:verbatim>An example for TextInput component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTile.jsf">
			<f:verbatim>An example for Tile component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTileList.jsf">
			<f:verbatim>An example for TileList component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTitleWindow.jsf">
			<f:verbatim>An example for TitleWindow component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlToggleButtonBar.jsf">
			<f:verbatim>An example for ToggleButtonBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlTree.jsf">
			<f:verbatim>An example for Tree component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlVideoDisplay.jsf">
			<f:verbatim>An example for VideoDisplay component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlViewStack.jsf">
			<f:verbatim>An example for ViewStack component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlVRule.jsf">
			<f:verbatim>An example for VRule component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlVScrollBar.jsf">
			<f:verbatim>An example for VScrollBar component</f:verbatim>
		</h:outputLink>
		<h:outputLink value="componentExamples/mxmlVSlider.jsf">
			<f:verbatim>An example for VSlider component</f:verbatim>
		</h:outputLink>
	</h:panelGrid>
	
	</body>
</f:view>
