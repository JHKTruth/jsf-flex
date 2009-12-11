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

#container {
    font-family:Trebuchet MS, Tahoma, Verdana;
    font-size:13px;
    font-weight:500;
}

#warningMessage{
    border-style: solid;
    border-color: #CCCCCC;
    border-width: 0 0 1px;
    color:#006699;
}

#fieldSetLegend {
    color:#6495ED;
    font-size:18px;
}

#fieldSet > div {
    clear: both;
    position:relative;
}

#fieldSet div:first-child {
    color:#ff6666;
    float:left;
    width:250px;
}

#fieldSet > div div + div {
    color:#708090;
    float:left;
    overflow-x: auto;
    padding-left: 5px;
    width: 700px;
}

</style>
</head>

<body>

<f:view renderKitId="MXML_BASIC">
    <div id="container">
        <h4 id="warningMessage">
        Unless one is using a browser with compatible CSS 2.1 [i.e. IE >= 7.0] the below content will look fuzzy
        </h4>
        
        <fieldset id="fieldSet">
            <legend id="fieldSetLegend">Mapping of the fields</legend>
            <div>
                <div> Accordion Selected Index :</div>
                <div><h:outputText value="#{mxmlOverallBean.accordionSelectedIndex}"></h:outputText></div>
            </div>
            <div>
                <div>Tab Navigator Selected Index :</div>
                <div><h:outputText value="#{mxmlOverallBean.tabNavigatorSelectedIndex}"></h:outputText></div>
            </div>
            <div>
                <div>TextInput Text :</div>
                <div><h:outputText value="#{mxmlOverallBean.textInputText}"></h:outputText></div>
            </div>
            <div>
                <div>RichTextEditorHtmlText :</div>
                <div style="width:800px;"><h:outputText value="#{mxmlOverallBean.richTextEditorHtmlText}"></h:outputText></div>
            </div>
            <div>
                <div>TextArea Text :</div>
                <div><h:outputText value="#{mxmlOverallBean.textAreaText}"></h:outputText></div>
            </div>
            <div>
                <div>DateField Text :</div>
                <div><h:outputText value="#{mxmlOverallBean.dateFieldText}"></h:outputText></div>
            </div>
            <div>
                <div>CheckBox Selected :</div>
                <div><h:outputText value="#{mxmlOverallBean.checkBoxSelected}"></h:outputText></div>
            </div>
            <div>
                <div>ComboBox Text :</div>
                <div><h:outputText value="#{mxmlOverallBean.comboBoxText}"></h:outputText></div>
            </div>
            <div>
                <div>ComboBox Selected Index :</div>
                <div><h:outputText value="#{mxmlOverallBean.comboBoxSelectedIndex}"></h:outputText></div>
            </div>
            <div>
                <div>RadioButtonSelected Value :</div>
                <div><h:outputText value="#{mxmlOverallBean.radioButtonSelectedValue}"></h:outputText></div>
            </div>
            <div>
                <div>RadioButton First Selected :</div>
                <div><h:outputText value="#{mxmlOverallBean.radioButtonFirstSelected}"></h:outputText></div>
            </div>
            <div>
                <div>RadioButton Second Selected :</div>
                <div><h:outputText value="#{mxmlOverallBean.radioButtonSecondSelected}"></h:outputText></div>
            </div>
            <div>
                <div>NumericStepper Value :</div>
                <div><h:outputText value="#{mxmlOverallBean.numericStepperValue}"></h:outputText></div>
            </div>
            <div>
                <div>ColorPicker SelectedColor :</div>
                <div><h:outputText value="#{mxmlOverallBean.colorPickerSelectedColor}"></h:outputText></div>
            </div>
            <div>
                <div>List SelectedIndex :</div>
                <div><h:outputText value="#{mxmlOverallBean.listSelectedIndex}"></h:outputText></div>
            </div>
            <div>
                <div>Tree SelectedIndex :</div>
                <div><h:outputText value="#{mxmlOverallBean.treeSelectedIndex}"></h:outputText></div>
            </div>
            <div>
                <div>HorizontalSlider Value :</div>
                <div><h:outputText value="#{mxmlOverallBean.horizontalSliderValue}"></h:outputText></div>
            </div>
            <div>
                <div>VerticalSlider Value :</div>
                <div><h:outputText value="#{mxmlOverallBean.verticalSliderValue}"></h:outputText></div>
            </div>
            <div>
                <div>Selected Date :</div>
                <div style="overflow-x:scroll;width:800px;"><h:outputText value="#{mxmlOverallBean.selectedDate}"></h:outputText></div>
            </div>
        </fieldset>
        <h:form>
            <div style="color:#00CC33;padding-left:10px;">
                To navigate back to mxmlExample click the following button <h:commandButton value="Back to mxmlExample" action="back" style="margin-left: 10px;"/>
            </div>
        </h:form>
    </div>
    
</f:view>

</body>

</html>