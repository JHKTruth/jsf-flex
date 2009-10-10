<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsf-flex.googlecode.com" prefix="jf"%>

<html>

<!--
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
//-->
<head>
<style type="text/css">
.fieldValue{
	color:#ff6666;
	font-family:verdana;
	font-size:13px;
	font-weight:700;
	text-align:center;
	width:50%;
}

.fieldDescription{
	color:#708090;
	font-family:verdana;
	font-size:13px;
	font-weight:700;
	text-align:center;
	width:50%;
}

#tableViewer{
	border-width:3;
	height:100%;
	width:100%;
}
</style>
</head>
<body>

<f:view>
	
	<table>
		<tr>
			<td style="width:70%">
				<table id="tableViewer" border="3;">
					<thead>
						<tr>
							<td colspan="2" style="color:#6495ED;font-family:Comic Sans MS;font-size:18px;font-weight:700;text-align:center;">
								Mapping of the fields
							</td>
						</tr>
					</thead>
					<tr>
			    		<td class="fieldDescription">
			    			Accordion Selected Index :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.accordionSelectedIndex}"></h:outputText>
			    		</td>
			    	</tr>
					<tr>
						<td class="fieldDescription">
							Tab Navigator Selected Index :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.tabNavigatorSelectedIndex}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
						    TextInput Text :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.textInputText}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
							RichTextEditorHtmlText :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.richTextEditorHtmlText}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
							TextArea Text :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.textAreaText}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
							DateField Text :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.dateFieldText}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
							CheckBox Selected :
						</td>
						<td class="fieldValue">
							<h:outputText value="#{mxmlOverallBean.checkBoxSelected}"></h:outputText>
						</td>
					</tr>
					<tr>
						<td class="fieldDescription">
				    		ComboBox Text :
				    	</td>
				    	<td class="fieldValue">
				    		<h:outputText value="#{mxmlOverallBean.comboBoxText}"></h:outputText>
				    	</td>
				    </tr>
				    <tr>
						<td class="fieldDescription">
				    		ComboBox Selected Index :
				    	</td>
				    	<td class="fieldValue">
				    		<h:outputText value="#{mxmlOverallBean.comboBoxSelectedIndex}"></h:outputText>
				    	</td>
				    </tr>
				    <tr>
				    	<td class="fieldDescription">
				    		RadioButtonSelected Value :
				    	</td>
				    	<td class="fieldValue">
				    		<h:outputText value="#{mxmlOverallBean.radioButtonSelectedValue}"></h:outputText>
				    	</td>
				    </tr>
				    <tr>
				    	<td class="fieldDescription">
				    		RadioButton First Selected :
				    	</td>
				    	<td class="fieldValue">
				    		<h:outputText value="#{mxmlOverallBean.radioButtonFirstSelected}"></h:outputText>
				    	</td>
				    </tr>
				    <tr>
				    	<td class="fieldDescription">
				    		RadioButton Second Selected :
				    	</td>
				    	<td class="fieldValue">
				    		<h:outputText value="#{mxmlOverallBean.radioButtonSecondSelected}"></h:outputText>
				    	</td>
				    </tr>
				    <tr>
				    	<td class="fieldDescription">
			    			NumericStepper Value :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.numericStepperValue}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
			    			ColorPicker SelectedColor :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.colorPickerSelectedColor}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
			    			List SelectedIndex :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.listSelectedIndex}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
					    	Tree SelectedIndex :
					    </td>
					    <td class="fieldValue">
					    	<h:outputText value="#{mxmlOverallBean.treeSelectedIndex}"></h:outputText>
					    </td>
					</tr>
					<tr>
						<td class="fieldDescription">
			    			HorizontalSlider Value :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.horizontalSliderValue}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
			    			VerticalSlider Value :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.verticalSliderValue}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
			    			ProgressBar Value :
			    		</td>
			    		<td class="fieldValue">
			    			<h:outputText value="#{mxmlOverallBean.progressBarValue}"></h:outputText>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="fieldDescription">
			    			Selected Date :
			    		</td>
			    		<td class="fieldValue">
			    			<div style="width:470px; overflow-x: scroll;">
			    				<h:outputText value="#{mxmlOverallBean.selectedDate}"></h:outputText>
			    			</div>
			    		</td>
			    	</tr>
			    </table>
    		</td>
    		<td style="width:1%">
				&nbsp;
			</td>
    		<td style="width:30%">
    			<h:form>
    			    <div style="color:00CC33;font-family:Comic Sans MS;font-weight:700;">
		    			To navigate back to mxmlExample click the below button<br /><br />
		    			<h:commandButton value="Back to mxmlExample" action="back" />
	    			</div>
    			</h:form>
    		</td>
    	</tr>
    </table>
	
</f:view>

</body>

</html>
