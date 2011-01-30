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

<body>

<f:view renderKitId="FLEX_BASIC">
    
    <h:form>
    	<jf:flexApplication mxmlPackageName="flexProgressBar">
    		<jf:flexAttributeNode name="height" value="500"/>
    		<jf:flexAttributeNode name="width" value="800"/>
    		
    		<jf:flexScript>
	        	<![CDATA[
		    	private function increaseProgressBar():void{
					progressBarRef.setProgress((progressBarRef.value + 10) % 110, 100);
				}
				
				private function decreaseProgressBar():void{
					progressBarRef.setProgress(progressBarRef.value == 0 ? 0 : progressBarRef.value - 10, 100);
				}
				]]>
		    </jf:flexScript>
		    
    		<jf:flexButton>
    			<jf:flexAttributeNode name="label" value="Increase Progress Bar"/>
	    		<jf:flexAttributeNode name="buttonDown" value="increaseProgressBar();"/>
    		</jf:flexButton>
    		
	        <jf:flexButton>
	        	<jf:flexAttributeNode name="label" value="Decrease Progress Bar"/>
	    		<jf:flexAttributeNode name="buttonDown" value="decreaseProgressBar();"/>
	        </jf:flexButton>
	        
    		<jf:flexProgressBar id="progressBarRef"	value="#{flexOverallBean.progressBarValue}">
    			<jf:flexAttributeNode name="mode" value="manual"/>
	    		<jf:flexAttributeNode name="minimum" value="0"/>
	    		<jf:flexAttributeNode name="maximum" value="100"/>
    		</jf:flexProgressBar>
    	</jf:flexApplication>
    </h:form>
    
</f:view>

</body>

</html>
