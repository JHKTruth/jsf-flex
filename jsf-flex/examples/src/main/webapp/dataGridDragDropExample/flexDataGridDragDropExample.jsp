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
    <link href="../css/example.css" rel="stylesheet"></link>
</head>
<body>

<f:view renderKitId="FLEX_BASIC">
	
	<h:form>
    	<div class="descriptionStyle">
            Currently if the data is large, it is broken into pieces of 50 with there being two batches, making
            the size of the cache 100 rows. When a limit is reached s.t. more information is required, one set will remain cached 
            with an another of batch of at most 50 being returned from the server. Also, note that field of <b>bindingBeanClassName</b> 
            does not need to be present if bindingBeanList size is greater than 0.
            <br />
        </div>
        <br />
        
	    <jf:flexApplication mxmlPackageName="flexDataGridDragDropExample" errorColor="#B80000" errorFontSize="13">
	    	
			<jf:flexVGroup>
				<jf:flexAttributeNode name="height" value="100%"/>
	    		<jf:flexAttributeNode name="width" value="100%"/>
				
				<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeDataEntries}" rowCount="10">
	    			<jf:flexAttributeNode name="width" value="100%"/>
	    			<jf:flexAttributeNode name="resizableColumns" value="true"/>
		    		<jf:flexAttributeNode name="dragEnabled" value="true"/>
		    		<jf:flexAttributeNode name="dragMoveEnabled" value="true"/>
		    		<jf:flexAttributeNode name="allowMultipleSelection" value="true"/>
	    			
					<jf:flexColumns>
						<jf:flexDataGridColumn dataField="firstColumnEntry">
							<jf:flexAttributeNode name="headerText" value="First Column Entry"/>
						</jf:flexDataGridColumn>
						<jf:flexDataGridColumn dataField="secondColumnEntry">
							<jf:flexAttributeNode name="headerText" value="Second Column Entry"/>
							
							<f:convertNumber />
						</jf:flexDataGridColumn>
					</jf:flexColumns>
				</jf:flexDataGrid>
				
				<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeSecondDataEntries}" rowCount="10" editable="true" 
									bindingBeanClassName="com.googlecode.jsfFlex.examples.flex.multipleRequestDataGridExample.MXMLMultipleRequestDataGridExampleBean$LargeDataEntry">
					<jf:flexAttributeNode name="resizableColumns" value="true"/>
		    		<jf:flexAttributeNode name="width" value="100%"/>
		    		<jf:flexAttributeNode name="dropEnabled" value="true"/>
		    		<jf:flexAttributeNode name="allowMultipleSelection" value="true"/>
					
					<jf:flexColumns>
						<jf:flexDataGridColumn dataField="firstColumnEntry">
							<jf:flexAttributeNode name="headerText" value="First Column Entry"/>
						</jf:flexDataGridColumn>
						<jf:flexDataGridColumn dataField="secondColumnEntry">
							<jf:flexAttributeNode name="headerText" value="Second Column Entry"/>
							
							<f:convertNumber />
						</jf:flexDataGridColumn>
					</jf:flexColumns>
				</jf:flexDataGrid>
				
			</jf:flexVGroup>
	    </jf:flexApplication>
    
    </h:form>
</f:view>

</body>

</html>
