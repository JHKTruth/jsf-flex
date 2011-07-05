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
.descriptionStyle{
    color:#708090;
    font-family:verdana;
    font-size:11px;
    font-weight:700;
}

</style>
</head>
<body>

<f:view renderKitId="MXML_BASIC">
	
	<h:form>
    	<div class="descriptionStyle">
            Currently if the data is large, it is broken into pieces of 50 with there being two batches, making
            the size of the cache 100 rows. When a limit is reached s.t. more information is required, one set will remain cached 
            with an another of batch of at most 50 being returned from the server.
            <br />
        </div>
        <br />
        
	    <jf:mxmlApplication mxmlPackageName="mxmlMultipleRequestDataGridExample" height="500" width="800" errorColor="#B80000" errorFontSize="13">
			
			<jf:mxmlDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeDataEntries}" width="100%" rowCount="10" 
								resizableColumns="true" editable="true">
				<jf:mxmlColumns>
					<jf:mxmlDataGridColumn dataField="firstColumnEntry" headerText="First Column Entry" />
					<jf:mxmlDataGridColumn dataField="secondColumnEntry" headerText="Second Column Entry">
						<f:convertNumber />
					</jf:mxmlDataGridColumn>
				</jf:mxmlColumns>
			</jf:mxmlDataGrid>
			
	    </jf:mxmlApplication>
    
    </h:form>
</f:view>

</body>

</html>
