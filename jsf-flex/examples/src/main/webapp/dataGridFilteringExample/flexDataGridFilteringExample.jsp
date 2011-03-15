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
    <link href="/jsf-flex-examples/css/example.css"></link>
</head>
<body>

<f:view renderKitId="FLEX_BASIC">
	
	<h:form>
    	<div class="descriptionStyle">
            Currently if the data is large, it is broken into pieces of 50 with there being two batches, making
            the size of the cache 100 rows. When a limit is reached s.t. more information is required, one set will remain cached 
            with an another of batch of at most 50 being returned from the server.
            <br />
        </div>
        <br />
        
	    <jf:flexApplication mxmlPackageName="flexMultipleRequestDataGridExample" errorColor="#B80000" errorFontSize="13">
			<jf:flexAttributeNode name="height" value="800"/>
    		<jf:flexAttributeNode name="width" value="800"/>
			
			<jf:flexVGroup>
				<jf:flexAttributeNode name="height" value="100%"/>
	    		<jf:flexAttributeNode name="width" value="100%"/>
	    		
	    		<jf:flexTextInput id="filteringComponent" text="#{flexOverallBean.textInputText}" />
	    		
				<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeDataEntries}" rowCount="10" editable="true"
					filterComponentId="filteringComponent" filterEventListener="keyUp">
					<jf:flexAttributeNode name="resizableColumns" value="true"/>
		    		<jf:flexAttributeNode name="width" value="100%"/>
					
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
