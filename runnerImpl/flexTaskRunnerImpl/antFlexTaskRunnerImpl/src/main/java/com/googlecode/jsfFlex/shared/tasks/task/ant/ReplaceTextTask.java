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
package com.googlecode.jsfFlex.shared.tasks.task.ant;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Replace;
import org.apache.tools.ant.taskdefs.Replace.NestedString;
import org.apache.tools.ant.taskdefs.Replace.Replacefilter;
import org.apache.tools.ant.taskdefs.optional.ReplaceRegExp;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;

/**
 * @author Ji Hoon Kim
 */
public final class ReplaceTextTask extends Ant_BaseTask {
	
	//make the below reg exp better later
	public static final String CLEAN_REG_EXP_MATCH = "\\s{2,}";
	public static final String CLEAN_REG_EXP_REPLACE_WITH = System.getProperty("line.separator");
	
	private static final String REPLACE_MULTI_LINE_TARGET = "replace_multi_line";
	private static final String REPLACE_TEXT_TARGET = "replace_text";
	private static final String REPLACE_REG_EXP_TARGET = "replace_reg_exp";
	
	private Target _replaceMultiLineTarget;
	private Target _replaceTextTarget;
	private Target _replaceRegExpTarget;
	
	private Replace _replaceMultiLineTask;
	private Replace _replaceTextTask;
	private ReplaceRegExp _replaceRegExpTask;
	
	private boolean _multiLineReplace;
	private boolean _replaceText;
	private boolean _replaceRegExp;
	
	private String _file;
	private String _regMatch;
	private String _regReplace;
	private String _flags;
	
	private Map _replaceList;
	
	public ReplaceTextTask(){
		super();
	}
	
	public ReplaceTextTask(String file){
		_file = file;
	}
	
	{
		//ReplaceMultiLineTarget
		_replaceMultiLineTarget = new Target();
		_replaceMultiLineTarget.setName(REPLACE_MULTI_LINE_TARGET);
		_replaceMultiLineTarget.setProject(_taskProject);
		_taskProject.addTarget(_replaceMultiLineTarget);
		
		_replaceMultiLineTask = new Replace();
		_replaceMultiLineTask.setOwningTarget(_replaceMultiLineTarget);
		_replaceMultiLineTask.setProject(_taskProject);
		
		_replaceMultiLineTarget.addTask(_replaceMultiLineTask);
		
		//ReplaceTextTarget 
		_replaceTextTarget = new Target();
		_replaceTextTarget.setName(REPLACE_TEXT_TARGET);
		_replaceTextTarget.setProject(_taskProject);
		_taskProject.addTarget(_replaceTextTarget);
		
		_replaceTextTask = new Replace();
		_replaceTextTask.setOwningTarget(_replaceTextTarget);
		_replaceTextTask.setProject(_taskProject);
		
		_replaceTextTarget.addTask(_replaceTextTask);
		
		//ReplaceRegExpTarget
		_replaceRegExpTarget = new Target();
		_replaceRegExpTarget.setName(REPLACE_REG_EXP_TARGET);
		_replaceRegExpTarget.setProject(_taskProject);
		_taskProject.addTarget(_replaceRegExpTarget);
		
		_replaceRegExpTask = new ReplaceRegExp();
		_replaceRegExpTask.setOwningTarget(_replaceRegExpTarget);
		_replaceRegExpTask.setProject(_taskProject);
		
		_replaceRegExpTarget.addTask(_replaceRegExpTask);
		
		_replaceList = new HashMap();
		_flags = "gis";
		_multiLineReplace = true;
	}
	
	public void addTokenValue(String token, String value){
		_replaceList.put(token, value);
	}
	
	protected void performTask() throws ComponentBuildException {
		
		try {
			
			java.util.Iterator iterate = _replaceList.keySet().iterator();
			String tokenVal;
			String targetToExecute = "";
			
			if(isMultiLineReplace()){
				
				_replaceMultiLineTask.setFile(new File(getFile()));
				Replacefilter replaceFilt;
				
				while(iterate.hasNext()){
					tokenVal = (String) iterate.next();
					
					replaceFilt = _replaceMultiLineTask.createReplacefilter();
					replaceFilt.setToken(tokenVal);
					replaceFilt.setValue((String) _replaceList.get(tokenVal));
				}
				_replaceMultiLineTask.maybeConfigure();
				targetToExecute = REPLACE_MULTI_LINE_TARGET;
			}else if(isReplaceText()){
				
				_replaceTextTask.setFile(new File(getFile()));
				NestedString nestedToken = null;
				NestedString nestedValue = null;
				
				while(iterate.hasNext()){
					tokenVal = (String) iterate.next();
					nestedToken = _replaceTextTask.createReplaceToken();
					nestedToken.addText(tokenVal);
					nestedValue = _replaceTextTask.createReplaceValue();
					nestedValue.addText((String) _replaceList.get(tokenVal));
				}
				
				_replaceTextTask.maybeConfigure();
				targetToExecute = REPLACE_TEXT_TARGET;
			}else if(isReplaceRegExp()){
				
				_replaceRegExpTask.setFile(new File(getFile()));
				_replaceRegExpTask.setMatch(getRegMatch());
				_replaceRegExpTask.setReplace(getRegReplace());
				_replaceRegExpTask.setFlags(getFlags());
				
				_replaceRegExpTask.maybeConfigure();
				targetToExecute = REPLACE_REG_EXP_TARGET;
			}
			
			buildProject(targetToExecute);
			
		} catch (BuildException buildException) {
			_taskProject.fireBuildFinished(buildException);
			StringBuffer errorMessage = new StringBuffer();
			errorMessage.append("Error in ReplaceText's performTask with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), buildException);
		}
		
	}
	
	public String toString() {
		StringBuffer content = new StringBuffer();
		content.append("multiLineReplace [ ");
		content.append(_multiLineReplace);
		content.append(" ] ");
		content.append("replaceText [ ");
		content.append(_replaceText);
		content.append(" ] ");
		content.append("replaceRegExp [ ");
		content.append(_replaceRegExp);
		content.append(" ] ");
		content.append("file [ ");
		content.append(_file);
		content.append(" ] ");
		content.append("regMatch [ ");
		content.append(_regMatch);
		content.append(" ] ");
		content.append("regReplace [ ");
		content.append(_regReplace);
		content.append(" ] ");
		content.append("flags [ ");
		content.append(_flags);
		content.append(" ] ");
		content.append("replaceList [");
		String currVal;
		for(Iterator iterate = _replaceList.keySet().iterator(); iterate.hasNext();){
			content.append(" ");
			content.append("key/value");
			currVal = (String) iterate.next();
			content.append(currVal);
			content.append("/");
			content.append(_replaceList.get(currVal));
		}
		content.append(" ] ");
		return content.toString();
	}

	public String getFile() {
		return _file;
	}
	public void setFile(String file) {
		_file = file;
	}
	public boolean isMultiLineReplace() {
		return _multiLineReplace;
	}
	public void setMultiLineReplace(boolean multiLineReplace) {
		_multiLineReplace = multiLineReplace;
		_replaceText = false;
		_replaceRegExp = false;
	}
	public boolean isReplaceRegExp() {
		return _replaceRegExp;
	}
	public void setReplaceRegExp(boolean replaceRegExp) {
		_replaceRegExp = replaceRegExp;
		_multiLineReplace = false;
		_replaceText = false;
	}
	public boolean isReplaceText() {
		return _replaceText;
	}
	public void setReplaceText(boolean replaceText) {
		_replaceText = replaceText;
		_multiLineReplace = false;
		_replaceRegExp = false;
	}
	public Map getReplaceList() {
		return _replaceList;
	}
	public void setReplaceList(Map replaceList) {
		_replaceList = replaceList;
	}
	public String getFlags() {
		return _flags;
	}
	public void setFlags(String flags) {
		_flags = flags;
	}
	public String getRegMatch() {
		return _regMatch;
	}
	public void setRegMatch(String regMatch) {
		_regMatch = regMatch;
	}
	public String getRegReplace() {
		return _regReplace;
	}
	public void setRegReplace(String regReplace) {
		_regReplace = regReplace;
	}
	
}