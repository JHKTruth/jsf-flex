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
package com.googlecode.jsfFlex.examples.mxml.overallExample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * @author Ji Hoon Kim
 */
public class MXMLOverallExampleBean implements Serializable {
	
	private static final long serialVersionUID = 6694836447144771325L;
	
	private Integer _accordionSelectedIndex;
	private Integer _tabNavigatorSelectedIndex;
	private String _textInputText;
	private String _richTextEditorHtmlText;
	private String _textAreaText;
	private String _dateFieldText;
	private Boolean _checkBoxSelected;
	private List _comboBoxDisplayEntries;
	private Integer _comboBoxSelectedIndex;
	private String _comboBoxText;
	private Boolean _radioButtonFirstSelected;
	private Boolean _radioButtonSecondSelected;
	private String _radioButtonSelectedValue;
	private String _numericStepperValue;
	private String _colorPickerSelectedColor;
	private Integer _listSelectedIndex;
	private Integer _treeSelectedIndex;
	private String _horizontalSliderValue;
	private String _verticalSliderValue;
	private String _progressBarValue;
	
	private List _wisePeopleEntries;
	
	public MXMLOverallExampleBean(){
		super();
		_accordionSelectedIndex = new Integer(0);
		_tabNavigatorSelectedIndex = new Integer(0);
		_textInputText = "";
		_richTextEditorHtmlText = "";
		_textAreaText = "";
		_dateFieldText = "";
		_checkBoxSelected = Boolean.valueOf(false);
		_comboBoxDisplayEntries = new LinkedList();
		_comboBoxDisplayEntries.add(new SelectItem("FirstValue", "FirstLabel"));
		_comboBoxDisplayEntries.add(new SelectItem("SecondValue", "SecondLabel"));
		_comboBoxDisplayEntries.add(new SelectItem("ThirdValue", "ThirdLabel"));
		
		_comboBoxSelectedIndex = new Integer(0);
		_comboBoxText = "";
		_radioButtonFirstSelected = Boolean.valueOf(false);
		_radioButtonSecondSelected = Boolean.valueOf(false);
		_radioButtonSelectedValue = "";
		_numericStepperValue = "";
		_listSelectedIndex = new Integer(0);
		_treeSelectedIndex = new Integer(0);
		_horizontalSliderValue = "";
		_verticalSliderValue = "";
		_progressBarValue = "";
		
		_wisePeopleEntries = new ArrayList();
		_wisePeopleEntries.add(new WisePeopleEntry("Issac Newton", "This most beautiful system [The Universe] could only proceed from the dominion of an intelligent and powerful Being.", 
													"fatherOfPhysicsCalculusDude@wiseHumble.com"));
		_wisePeopleEntries.add(new WisePeopleEntry("James Clark Maxwell", "At quite uncertain times and places. The atoms left their heavenly path, And by fortuitous embraces, Engendered all that being hath.", 
													"electroMagneticDude@wiseHumble.com"));
		_wisePeopleEntries.add(new WisePeopleEntry("Blaise Pascal", "Belief is a wise wager. Granted that faith cannot be proved, what harm will come to you if you gamble on its truth and it proves false? If you gain, you gain all; if you lose, you lose nothing. Wager, then, without hesitation, that He exists.", 
													"probabilityTheoryDude@wiseHumble.com"));
		
	}
	
	public Integer getAccordionSelectedIndex() {
		return _accordionSelectedIndex;
	}
	public void setAccordionSelectedIndex(Integer accordionSelectedIndex) {
		_accordionSelectedIndex = accordionSelectedIndex;
	}
	public Boolean getCheckBoxSelected() {
		return _checkBoxSelected;
	}
	public void setCheckBoxSelected(Boolean checkBoxSelected) {
		_checkBoxSelected = checkBoxSelected;
	}
	public String getColorPickerSelectedColor() {
		return _colorPickerSelectedColor;
	}
	public void setColorPickerSelectedColor(String colorPickerSelectedColor) {
		_colorPickerSelectedColor = colorPickerSelectedColor;
	}
	public List getComboBoxDisplayEntries() {
		return _comboBoxDisplayEntries;
	}
	public void setComboBoxDisplayEntries(List comboBoxDisplayEntries) {
		_comboBoxDisplayEntries = comboBoxDisplayEntries;
	}
	public Integer getComboBoxSelectedIndex() {
		return _comboBoxSelectedIndex;
	}
	public void setComboBoxSelectedIndex(Integer comboBoxSelectedIndex) {
		_comboBoxSelectedIndex = comboBoxSelectedIndex;
	}
	public String getComboBoxText() {
		return _comboBoxText;
	}
	public void setComboBoxText(String comboBoxText) {
		_comboBoxText = comboBoxText;
	}
	public String getDateFieldText() {
		return _dateFieldText;
	}
	public void setDateFieldText(String dateFieldText) {
		_dateFieldText = dateFieldText;
	}
	public String getHorizontalSliderValue() {
		return _horizontalSliderValue;
	}
	public void setHorizontalSliderValue(String horizontalSliderValue) {
		_horizontalSliderValue = horizontalSliderValue;
	}
	public Integer getListSelectedIndex() {
		return _listSelectedIndex;
	}
	public void setListSelectedIndex(Integer listSelectedIndex) {
		_listSelectedIndex = listSelectedIndex;
	}
	public String getNumericStepperValue() {
		return _numericStepperValue;
	}
	public void setNumericStepperValue(String numericStepperValue) {
		_numericStepperValue = numericStepperValue;
	}
	public String getProgressBarValue() {
		return _progressBarValue;
	}
	public void setProgressBarValue(String progressBarValue) {
		_progressBarValue = progressBarValue;
	}
	public Boolean getRadioButtonFirstSelected() {
		return _radioButtonFirstSelected;
	}
	public void setRadioButtonFirstSelected(Boolean radioButtonFirstSelected) {
		_radioButtonFirstSelected = radioButtonFirstSelected;
	}
	public Boolean getRadioButtonSecondSelected() {
		return _radioButtonSecondSelected;
	}
	public void setRadioButtonSecondSelected(Boolean radioButtonSecondSelected) {
		_radioButtonSecondSelected = radioButtonSecondSelected;
	}
	public String getRadioButtonSelectedValue() {
		return _radioButtonSelectedValue;
	}
	public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
		_radioButtonSelectedValue = radioButtonSelectedValue;
	}
	public String getRichTextEditorHtmlText() {
		return _richTextEditorHtmlText;
	}
	public void setRichTextEditorHtmlText(String richTextEditorHtmlText) {
		_richTextEditorHtmlText = richTextEditorHtmlText;
	}
	public Integer getTabNavigatorSelectedIndex() {
		return _tabNavigatorSelectedIndex;
	}
	public void setTabNavigatorSelectedIndex(Integer tabNavigatorSelectedIndex) {
		_tabNavigatorSelectedIndex = tabNavigatorSelectedIndex;
	}
	public String getTextAreaText() {
		return _textAreaText;
	}
	public void setTextAreaText(String textAreaText) {
		_textAreaText = textAreaText;
	}
	public String getTextInputText() {
		return _textInputText;
	}
	public void setTextInputText(String textInputText) {
		_textInputText = textInputText;
	}
	public Integer getTreeSelectedIndex() {
		return _treeSelectedIndex;
	}
	public void setTreeSelectedIndex(Integer treeSelectedIndex) {
		_treeSelectedIndex = treeSelectedIndex;
	}
	public String getVerticalSliderValue() {
		return _verticalSliderValue;
	}
	public void setVerticalSliderValue(String verticalSliderValue) {
		_verticalSliderValue = verticalSliderValue;
	}
	public List getWisePeopleEntries() {
		return _wisePeopleEntries;
	}
	public void setWisePeopleEntries(List wisePeopleEntries) {
		_wisePeopleEntries = wisePeopleEntries;
	}
	
	public final static class WisePeopleEntry implements Serializable {
		
		private static final long serialVersionUID = -4974974584395025727L;
		
		private String _name;
		private String _quote;
		private String _email;
		
		private WisePeopleEntry(String name, String quote, String email){
			super();
			_name = name;
			_quote = quote;
			_email = email;
		}

		public String getEmail() {
			return _email;
		}
		public void setEmail(String email) {
			_email = email;
		}
		public String getName() {
			return _name;
		}
		public void setName(String name) {
			_name = name;
		}
		public String getQuote() {
			return _quote;
		}
		public void setQuote(String quote) {
			_quote = quote;
		}
		
		public boolean equals(Object instance) {
			if(!(instance instanceof WisePeopleEntry)){
				return false;
			}
			
			WisePeopleEntry wisePeopleEntryInstance = (WisePeopleEntry) instance;
			return _name.equals(wisePeopleEntryInstance._name) && _quote.equals(wisePeopleEntryInstance._quote)
						&& _email.equals(wisePeopleEntryInstance._email);
		}
		
		public int hashCode() {
			int hashCodeVal = MXMLConstants.HASH_CODE_INIT_VALUE;
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _name.hashCode();
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _quote.hashCode();
			hashCodeVal = MXMLConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _email.hashCode();
			return hashCodeVal;
		}
		
	}
	
}