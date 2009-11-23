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

.errorStyle{
    color:#ff6666;
    font-family:verdana;
    font-size:11px;
    font-weight:700;
}
</style>
</head>
<body>

<f:view renderKitId="MXML_BASIC">
	
	<h:form>
    	The button on the right is to test out the decode process and make sure that the information is mapped correctly
    	
    	<h:commandButton value="DecodeTester" action="success" />
        <br />
        <div class="descriptionStyle">
            Note that the client validation for the component with id textInputRef [namely mxmlNumberValidator] is not a child tag
            of the mxmlTextInput tag. However the regular validation tag [validateLongRange] is a child tag which performs the validation
            on the server side.
            <br />
            <h:message for="textAreaRef" errorClass="errorStyle" />
        </div>
        <br />
        
	    <jf:mxmlApplication mxmlPackageName="mxmlOverallExample" height="90%" width="90%" errorColor="#B80000" errorFontSize="13">
	    	<jf:mxmlLabel text="Following label contains korean text to demonstrate locale [if browser's language is set to korean]" color="#FFFFFF" fontWeight="bold"/>
	    	<jf:mxmlLabel text="@Resource(bundle='LocaleExample', key='greeting')" color="#FFFFFF" fontWeight="bold" fontSize="14"/>
	    	
	        <jf:mxmlScript>
	        	import flash.events.Event;
	        	
		    	import mx.collections.XMLListCollection;
		    	import mx.controls.Alert;
		    	import mx.controls.Menu;
		    	import mx.controls.PopUpButton;
		    	import mx.events.MenuEvent;
		    	
		    	private function alertMe():void {
		    		Alert.show("Uhm, I have been alerted");
		    	}
		    	
		    	private var myMenu:Menu;
		    	private var popUpButton:PopUpButton;
				
				private function initMenu(event:Event):void {
	                myMenu = new Menu();
	                var dp:Object = [{label: "New Folder"}, {label: "Sent Items"}, {label: "Inbox"}];        
	                myMenu.dataProvider = dp;
	                myMenu.selectedIndex = 0;
	                myMenu.addEventListener("itemClick", itemClickHandler);
	                popUpButton = event.target as PopUpButton;
	                popUpButton.popUp = myMenu;
	                popUpButton.label = "Put in: " + myMenu.dataProvider[myMenu.selectedIndex].label;
	            }
	            
	            private function itemClickHandler(event:MenuEvent):void {
	                var label:String = event.item.label;        
	                popUpButton.label = "Put in: " + label;
	                popUpButton.close();
	                myMenu.selectedIndex = event.index;
	            }
		    	
		    	[Bindable]
				public var reallySimpleArray:Array = ["First", "Second", "Third"];
				
				public var complexStruct:XMLList = <root>
														<menuitem label="Menu1">
									                        <menuitem label="MenuItem 1-A" data="1A"/>
									                        <menuitem label="MenuItem 1-B" data="1B"/>
									                    </menuitem>
									                    <menuitem label="Menu2">
									                        <menuitem label="MenuItem 2-A" type="check"  data="2A"/>
									                        <menuitem type="separator" />
									                        <menuitem label="MenuItem 2-B" >
									                            <menuitem label="SubMenuItem 3-A" type="radio"
									                                groupName="one" data="3A"/>
									                            <menuitem label="SubMenuItem 3-B" type="radio"
									                                groupName="one" data="3B"/>
									                        </menuitem>
									                    </menuitem>
													</root>.menuitem;
				[Bindable]
				public var complexStructCollection:XMLListCollection = new XMLListCollection(complexStruct);
				
				private function increaseProgressBar():void{
					progressBarRef.setProgress((progressBarRef.value + 10) % 110, 100);
				}
				
				private function decreaseProgressBar():void{
					progressBarRef.setProgress(progressBarRef.value == 0 ? 0 : progressBarRef.value - 10, 100);
				}
				
		    </jf:mxmlScript>
		    <!-- Validation on the client side -->
		    <jf:mxmlNumberValidator exceedsMaxError="The number provided exceeds max value [60]." property="text"
		    						domain="int" integerError="Must be an integer value."
			    					lowerThanMinError="The number provided is lower than min value [10]." maxValue="60" minValue="10" 
			    					required="true" source="{textInputRef}" />
			
			<jf:mxmlAccordion width="100%" height="100%" selectedIndex="#{mxmlOverallBean.accordionSelectedIndex}">
			    
				<jf:mxmlTabNavigator width="100%" height="100%" selectedIndex="#{mxmlOverallBean.tabNavigatorSelectedIndex}">
						
			    		<jf:mxmlPanel label="First Tab" width="100%" height="100%">
			    			<jf:mxmlDividedBox direction="horizontal" width="100%" height="100%">
			    			
				    			<jf:mxmlBox width="30%" height="100%">
					    			<jf:mxmlTextInput id="textInputRef" text="#{mxmlOverallBean.textInputText}" />
					    			<jf:mxmlRichTextEditor textBinding="htmlText" htmlText="#{mxmlOverallBean.richTextEditorHtmlText}" />
							    	<jf:mxmlTextArea id="textAreaRef" text="#{mxmlOverallBean.textAreaText}" >
							    		<f:validateLongRange minimum="10" maximum="60" />
							    	</jf:mxmlTextArea>
							    	<jf:mxmlDateField text="#{mxmlOverallBean.dateFieldText}" width="100" />
							    	<jf:mxmlCheckBox label="CheckBox am I [\^$.|?*+(){}" selected="#{mxmlOverallBean.checkBoxSelected}" />
						    	</jf:mxmlBox>
						    	
						    	<jf:mxmlBox width="70%" height="100%">
						    		<jf:mxmlDataGrid bindingBeanList="#{mxmlOverallBean.wisePeopleEntries}" width="100%" 
						    							rowCount="4" resizableColumns="true" editable="true" height="100%">
								    	<jf:mxmlColumns>
								    		<jf:mxmlDataGridColumn dataField="name" headerText="Name" />
								    		<jf:mxmlDataGridColumn wordWrap="true" dataField="quote" headerText="Quote" minWidth="170" />
								    		<jf:mxmlDataGridColumn dataField="email" headerText="Email" />
								    	</jf:mxmlColumns>
								    </jf:mxmlDataGrid>
							   </jf:mxmlBox>
					    	
					    	</jf:mxmlDividedBox>
					    </jf:mxmlPanel>
			    			
			    		<jf:mxmlPanel label="Second Tab" width="100%" height="100%">
					    	<jf:mxmlComboBox text="#{mxmlOverallBean.comboBoxText}" selectedIndex="#{mxmlOverallBean.comboBoxSelectedIndex}" 
					    							dataProviderCollection="#{mxmlOverallBean.comboBoxDisplayEntries}" />
					    	<jf:mxmlRadioButton groupName="radioTest" label="First" value="First" 
					    							selectedValue="#{mxmlOverallBean.radioButtonSelectedValue}" selected="#{mxmlOverallBean.radioButtonFirstSelected}"/>
					       	<jf:mxmlRadioButton groupName="radioTest" label="Second" value="Second" selected="#{mxmlOverallBean.radioButtonSecondSelected}"/>
					       	
					       	<jf:mxmlNumericStepper minimum="0" maximum="10" value="#{mxmlOverallBean.numericStepperValue}" />
					       	<jf:mxmlColorPicker labelField="ColorPicker am I" selectedColor="#{mxmlOverallBean.colorPickerSelectedColor}" />
					       	<jf:mxmlDateChooser selectedDate="#{mxmlFaceletOverallBean.selectedDate}" width="300" />
			    		</jf:mxmlPanel>
	
			    </jf:mxmlTabNavigator>
			            	
		       	<jf:mxmlPanel width="100%" height="100%">
			    		  
			    		  <jf:mxmlDividedBox direction="horizontal" width="100%" height="100%">
			    		  	
			    		  	<jf:mxmlBox width="50%" height="100%">
				    			<jf:mxmlLinkButton label="To Link to something" />
				        		<jf:mxmlButton label="Click Me for a nice message" buttonDown="alertMe();"/>
				        		<jf:mxmlButton action="#{mxmlOverallBean.buttonAction}" label="Will submit the form"/>
				        		<jf:mxmlLabel text="Simple List" />
				        		<jf:mxmlList width="100" dataProvider="{reallySimpleArray}" selectedIndex="#{mxmlOverallBean.listSelectedIndex}" />
				        		<jf:mxmlTree width="100" dataProvider="{complexStructCollection}" labelField="@label" selectedIndex="#{mxmlOverallBean.treeSelectedIndex}"/>
				        	</jf:mxmlBox>
				        	
			        		<jf:mxmlBox width="50%" height="100%">
				        		<jf:mxmlLabel text="Simple ButtonBar" />
				        		<jf:mxmlButtonBar>
				        			<jf:mxmlDataProvider>
	                                    <jf:mxmlObject>
	                                    	<jf:mxmlObjectStaticProperty staticPropertyName="label" staticPropertyValue="First Nesting" />
	                                    </jf:mxmlObject>
	                                    <jf:mxmlObject>
	                                    	<jf:mxmlObjectStaticProperty staticPropertyName="label" staticPropertyValue="Second Nesting" />
	                                    </jf:mxmlObject>
	                                </jf:mxmlDataProvider>
				        		</jf:mxmlButtonBar>
				        		<jf:mxmlLabel text="Simple ToggleButtonBar" />
				        		<jf:mxmlToggleButtonBar dataProvider="{reallySimpleArray}" />
				        		
				        		<jf:mxmlLabel text="Simple TabBar" />
				        		<jf:mxmlTabBar dataProvider="{reallySimpleArray}" />
				        		<jf:mxmlLabel text="Simple LinkBar" />
				        		<jf:mxmlLinkBar dataProvider="{reallySimpleArray}" />
				        	</jf:mxmlBox>
			        		
				          </jf:mxmlDividedBox>
				          
				</jf:mxmlPanel>
				
				<jf:mxmlBox width="100%" height="100%">
				
					<jf:mxmlBox width="100%" height="40%">
						
						<jf:mxmlLabel text="Simple VScrollBar" />
						<jf:mxmlVScrollBar />
						<jf:mxmlSpacer height="10" />
						
						<jf:mxmlLabel text="Simple HScrollBar" />
						<jf:mxmlHScrollBar />
						
						<jf:mxmlSpacer height="20" />
				        <jf:mxmlHRule width="100%" />
				        <jf:mxmlSpacer height="20" />
					    
						<jf:mxmlLabel text="Simple HSlider" />
						<jf:mxmlHSlider value="#{mxmlOverallBean.horizontalSliderValue}" />
						<jf:mxmlSpacer height="10" />
							
						<jf:mxmlLabel text="Simple VSlider" />
						<jf:mxmlVSlider value="#{mxmlOverallBean.verticalSliderValue}" />
						
					</jf:mxmlBox>
					
					<jf:mxmlTitleWindow title="Title Window" x="168" y="86" borderStyle="inset">
					    
					    <jf:mxmlTile>
					        <jf:mxmlButton label="Increase Progress Bar" buttonDown="increaseProgressBar();"/>
					        <jf:mxmlButton label="Decrease Progress Bar" buttonDown="decreaseProgressBar();" />
					    </jf:mxmlTile>
					    
					    <jf:mxmlProgressBar id="progressBarRef" mode="manual" minimum="0" maximum="100"	value="#{mxmlOverallBean.progressBarValue}" />
					    
					</jf:mxmlTitleWindow>
					
				</jf:mxmlBox>
				
			</jf:mxmlAccordion>
			
			<jf:mxmlApplicationControlBar dock="true">
	        	<jf:mxmlMenuBar dataProvider="{complexStructCollection}" labelField="@label" />
			    <jf:mxmlPopUpButton creationComplete="initMenu(event);" width="135" />
			    <jf:mxmlPopUpMenuButton dataProvider="{complexStructCollection}" labelField="@label" />
			</jf:mxmlApplicationControlBar>
			    		  
	    </jf:mxmlApplication>
    
    </h:form>
</f:view>

</body>

</html>
