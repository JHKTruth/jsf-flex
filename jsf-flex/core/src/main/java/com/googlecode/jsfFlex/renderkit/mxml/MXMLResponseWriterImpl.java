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
package com.googlecode.jsfFlex.renderkit.mxml;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.apache.myfaces.shared_impl.renderkit.html.HtmlResponseWriterImpl;

import com.googlecode.jsfFlex.shared.adapter._MXMLApplicationContract;
import com.googlecode.jsfFlex.shared.adapter._MXMLContract;
import com.googlecode.jsfFlex.shared.context.MxmlContext;
import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks._CommonTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FileManipulatorTaskRunner;
import com.googlecode.jsfFlex.shared.tasks._FlexTaskRunner;
import com.googlecode.jsfFlex.shared.util.MXMLConstants;

/**
 * TODO : Implement it specifically for JSF Flex
 * @author Ji Hoon Kim
 */
public class MXMLResponseWriterImpl extends HtmlResponseWriterImpl {
	
	public MXMLResponseWriterImpl(Writer writer, String selectedContentType, String characterEncoding){
		super(writer, selectedContentType, characterEncoding);
	}
	
	public void execute() throws ComponentBuildException {
		getCommonTaskRunner().execute();
		getFlexTaskRunner().execute();
	}
	
	/**
	 * One can consider this method to be somewhat of a facade in creating application SWF file.<br>
	 * 
	 * @param componentMXML
	 * @param mxmlFile
	 * @throws ComponentBuildException
	 */
	public final void processCreateSwf(_MXMLApplicationContract componentMXML, String mxmlFile) throws ComponentBuildException {
		
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		String copyTo = mxmlContext.getMxmlPath() + mxmlContext.getCurrMxml() + MXMLConstants.MXML_FILE_EXT;
		//now create the MXML file
		createMXML(componentMXML, copyTo);
		
		if(!new File(mxmlContext.getFlexSDKPath()).exists()){
			makeDirectory(mxmlContext.getFlexSDKPath());
			unZipArchiveRelative(MXMLConstants.FLEX_SDK_ZIP, mxmlContext.getFlexSDKPath());
			
			//copy the necessary ActionScript files over for SWF generation 
			createSwcSourceFiles(mxmlContext.getSwcPath(), MXMLConstants.getSwcSourceFiles(), 
										MXMLConstants.JSF_FLEX_MAIN_SWC_CONFIG_FILE);
			
			//create the SWC file
			String _loadConfigAbsolutePath = mxmlContext.getSwcPath() + MXMLConstants.JSF_FLEX_MAIN_SWC_CONFIGURATIONFILE;
			String _swcFileLocationPath = mxmlContext.getSwcPath() + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWC_FILE_EXT;
			createSystemSWCFile(mxmlContext.getSwcPath(), _swcFileLocationPath, mxmlContext.getFlexSDKPath(), _loadConfigAbsolutePath);
			
			/*
			 * 	copy the necessary swf source files to swfBasePath
			 * 	these are files such as xml[s] which are used by the system's/above ActionScripts
			 */
			createSwfSourceFiles(mxmlContext.getSwfBasePath(), MXMLConstants.getSwfSourceFiles());
			
			/*
			 * unzip the swc's library.swf file and copy it to the swf file for linking with the swf file
			 */
			unZipArchiveAbsolute(new File(_swcFileLocationPath), mxmlContext.getSwcPath());
			
			//copy the library.swf file to swc directory
			copyFileSet(mxmlContext.getSwcPath(), "**/*.swf", null, mxmlContext.getSwfBasePath());
			
			//rename the file from library.swf to jsfFlexMainSwc.swf file
			String sourceFile = mxmlContext.getSwcPath() + MXMLConstants.DEFAULT_SWC_LIBRARY_SWF_NAME;
			String destFile = mxmlContext.getSwfBasePath() + MXMLConstants.JSF_FLEX_MAIN_SWC_ARCHIVE_NAME + MXMLConstants.SWF_FILE_EXT;
			
			renameFile(sourceFile, destFile, true);
			
			deleteResources(sourceFile, false);
		}
		
		//finally the SWF file
		createSWF(componentMXML, mxmlFile, mxmlContext.getSwfPath(), mxmlContext.getFlexSDKPath());
		
	}
	
	public final String generateMXMLObjectBeanContent() throws ComponentBuildException {
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		return getFileManipulatorTaskRunner().generateMXMLObjectBeanContent(mxmlContext.getMxmlObjectBeanWrapperSet(), mxmlContext.getPreMxmlPath());
	}
	
	/**
	 * This method will create a directory, which should be specified in absolute path.<br>
	 * 
	 * @param directoryToCreate
	 * @throws ComponentBuildException
	 */
	public final void makeDirectory(String directoryToCreate) throws ComponentBuildException {
		getFlexTaskRunner().makeDirectory(directoryToCreate);
	}
	
	/**
	 * This method will map the fields of javaDoc/annotation [depends on which JRE version was specified during<br>
	 * build time] from the MXMLUIComponent to a HashSet.<br>
	 * 
	 * @param mapClass
	 * @param componentObj
	 * @param mappingFile
	 * @throws ComponentBuildException
	 */
	public final void mapFields(Class mapClass, Object componentObj, String mappingFile) throws ComponentBuildException {
		_MXMLContract _comp = (_MXMLContract) componentObj;
		_comp.getAnnotationDocletParserInstance().mapComponentFields(mapClass, componentObj, mappingFile);
	}
	
	/**
	 * This method will create the preMxml file of the component.<br>
	 * 
	 * @param comp
	 * @param mxmlComponentName
	 * @param bodyContent
	 * @throws ComponentBuildException
	 */
	public final void createPreMxml(MXMLResponseWriterImpl writer, _MXMLContract comp, String mxmlComponentName, String bodyContent) throws ComponentBuildException {
		
		String fileDirectory = comp.getAbsolutePathToPreMxmlFile().substring(0, comp.getAbsolutePathToPreMxmlFile().lastIndexOf(File.separatorChar));
		writer.getFlexTaskRunner().makeDirectory(fileDirectory);
		
		writer.getFileManipulatorTaskRunner().createPreMxmlFile(comp.getAbsolutePathToPreMxmlFile(), null, comp.getAnnotationDocletParserInstance().getTokenValueSet(), mxmlComponentName, 
																bodyContent, writer.childPreMxmlComponentIdentifier(comp), writer.siblingPreMxmlComponentIdentifier(comp));
		
	}
	
	/**
	 * This method will replace a token with a value within a preMxml file.<br>
	 * 
	 * @param applicationInstance
	 * @param valueToReplaceWith
	 * @param tokenReplace
	 * @throws ComponentBuildException
	 */
	public final void replaceTokenWithValue(_MXMLContract applicationInstance, String valueToReplaceWith, String tokenReplace) throws ComponentBuildException {
		getFlexTaskRunner().replaceTokenWithValue(applicationInstance, valueToReplaceWith, tokenReplace);
	}
	
	/**
	 * This method will flatten the MXMLApplicationRenderer preMxml file and copy it as a MXML file to its correct directory,<br>
	 * which should be specified in absolute path.<br>
	 * 
	 * @param applicationInstance
	 * @param copyTo
	 * @throws ComponentBuildException
	 */
	public final void createMXML(_MXMLContract applicationInstance, String copyTo) throws ComponentBuildException {
		getFlexTaskRunner().createMXML(applicationInstance, copyTo);
	}
	
	/**
	 * This method will create the necessary SWC source files. Please refer to mxmlConstants.xml for the file listings.<br>
	 * 
	 * @param _swcPath
	 * @param _systemSourceFiles
	 * @param jsfFlexMainSwcConfigFile
	 * @throws ComponentBuildException
	 */
	public final void createSwcSourceFiles(String _swcPath, List _systemSourceFiles, String jsfFlexMainSwcConfigFile) throws ComponentBuildException {
		getFlexTaskRunner().createSwcSourceFiles(_swcPath, _systemSourceFiles, jsfFlexMainSwcConfigFile);
	}
	
	/**
	 * This method will create the SWC file, which will contain a library SWF file to be used by application SWF files.<br>
	 * 
	 * @param sourcePath
	 * @param outPut
	 * @param flexSDKRootPath
	 * @param loadConfigFilePath
	 * @throws ComponentBuildException
	 */
	public final void createSystemSWCFile(String sourcePath, String outPut, String flexSDKRootPath, String loadConfigFilePath) 
											throws ComponentBuildException {
		getFlexTaskRunner().createSystemSWCFile(sourcePath, outPut, flexSDKRootPath, loadConfigFilePath);
	}
	
	/**
	 * Thie method will create the application SWF file from its MXML file.<br>
	 * 
	 * @param componentMXML
	 * @param mxmlFile
	 * @param swfPath
	 * @param flexSDKRootPath
	 * @throws ComponentBuildException
	 */
	public final void createSWF(_MXMLApplicationContract componentMXML, String mxmlFile, 
									String swfPath, String flexSDKRootPath) throws ComponentBuildException {
		getFlexTaskRunner().createSWF(componentMXML, mxmlFile, swfPath, flexSDKRootPath);
	}
	
	/**
	 * This method will create the necessary source files for the application SWF. Please refer to mxmlConstants.xml for the file listings.<br>
	 * 
	 * @param _swfBasePath
	 * @param _systemSwfSourceFiles
	 * @throws ComponentBuildException
	 */
	public final void createSwfSourceFiles(String _swfBasePath, List _systemSwfSourceFiles) throws ComponentBuildException {
		getFlexTaskRunner().createSwfSourceFiles(_swfBasePath, _systemSwfSourceFiles);
	}
	
	/**
	 * This method will delete the resource, which should be specified in absolute path.<br>
	 * 
	 * @param deleteResource
	 * @param isDirectory
	 * @throws ComponentBuildException
	 */
	public final void deleteResources(String deleteResource, boolean isDirectory) throws ComponentBuildException {
		getFlexTaskRunner().deleteResources(deleteResource, isDirectory);
	}
	
	/**
	 * This method will copy one file to an another file. Note that these should be specified in absolute path.<br>
	 * 
	 * @param fileToCopy
	 * @param fileToCopyTo
	 * @throws ComponentBuildException
	 */
	public final void copyFile(String fileToCopy, String fileToCopyTo) throws ComponentBuildException {
		getFlexTaskRunner().copyFile(fileToCopy, fileToCopyTo);
	}
	
	/**
	 * This method will copy certain fileSet to the destination directory [i.e. if you wish to exclude or include only a specific set of<br>
	 * file extensions this method should be used]. Note that the copy source and copy target should be specified in absolute path.<br>
	 * 
	 * @param copyDir
	 * @param copyInclude
	 * @param copyExclude
	 * @param copyTo
	 * @throws ComponentBuildException
	 */
	public final void copyFileSet(String copyDir, String copyInclude, String copyExclude, String copyTo) throws ComponentBuildException {
		getFlexTaskRunner().copyFileSet(copyDir, copyInclude, copyExclude, copyTo);
	}
	
	/**
	 * This method will enable renaming of a file to an another file name. Note that the copy source and copy target should be specified<br>
	 * in absolute path.<br>
	 * 
	 * @param sourceFile
	 * @param destFile
	 * @param overWrite
	 * @throws ComponentBuildException
	 */
	public final void renameFile(String sourceFile, String destFile, boolean overWrite) throws ComponentBuildException {
		getFlexTaskRunner().renameFile(sourceFile, destFile, overWrite);
	}
	
	/**
	 * This method should be used for files that are relative to the UnzipTask.<br>
	 * 
	 * @param _unZipFile
	 * @param _unZipDest
	 * @throws ComponentBuildException
	 */
	public final void unZipArchiveRelative(String _unZipFile, String _unZipDest) throws ComponentBuildException {
		getCommonTaskRunner().unZipArchiveRelative(_unZipFile, _unZipDest);
	}
	
	/**
	 * This method should be used for files that are absolute.<br>
	 * 
	 * @param _unZipFile
	 * @param _unZipDest
	 * @throws ComponentBuildException
	 */
	public final void unZipArchiveAbsolute(File _unZipFile, String _unZipDest) throws ComponentBuildException {
		
		getCommonTaskRunner().unZipArchiveAbsolute(_unZipFile, _unZipDest);
	}
	
	/**
	 * This method should be used for files that are absolute.<br>
	 * 
	 * @param _unZipFile
	 * @param _unZipDest
	 * @throws ComponentBuildException
	 */
	public final void unZipArchiveAbsolute(InputStream _unZipFile, String _unZipDest) throws ComponentBuildException {
		
		getCommonTaskRunner().unZipArchiveAbsolute(_unZipFile, _unZipDest);
	}
	
	/**
	 * This method will load and read the template specified and return it as a String.<br>
	 * 
	 * @param template
	 * @return
	 * @throws ComponentBuildException
	 */
	public final String getComponentTemplate(ClassLoader loader, String template) throws ComponentBuildException {
		
		return _FileManipulatorTaskRunner.getComponentTemplate(loader, template);
	}
	
	/**
	 * This method will read the file specified and return it as a String. Note the fileName should be specified in absolute path.<br>
	 * 
	 * @param fileName
	 * @return
	 * @throws ComponentBuildException
	 */
	public final String readFileContent(String fileName) throws ComponentBuildException {
		
		return _FileManipulatorTaskRunner.readFileContent(fileName);
	}
	
	/**
	 * This method will return the child preMxml component identifier. In another words, when the preMxml file is created<br>
	 * the file requires this identifier to be placed within the file so that possible child can be added to the correct<br>
	 * component and have correct relationship.<br>
	 * 
	 * @param currInstance
	 * @return
	 */
	public final String childPreMxmlComponentIdentifier(_MXMLContract currInstance){
		StringBuffer toReturn = new StringBuffer();
		
		toReturn.append(currInstance.getPreMxmlIdentifier());
		toReturn.append(currInstance.getMajorLevel()+1);
		toReturn.append(0);
		
		return toReturn.toString();
	}
	
	/**
	 * This method will return the sibling preMxml component identifier. In another words, when the preMxml file is created<br>
	 * the file requires this identifier to be placed within the file so that possible sibling can be added to the correct<br>
	 * component and have correct relationship.<br>
	 * 
	 * @param currInstance
	 * @return
	 */
	public final String siblingPreMxmlComponentIdentifier(_MXMLContract currInstance){
		StringBuffer toReturn = new StringBuffer();
		
		toReturn.append(currInstance.getParentPreMxmlIdentifier());
		toReturn.append(currInstance.getMajorLevel());
		toReturn.append(currInstance.getMinorLevel()+1);
		
		return toReturn.toString();
	}
	
	/**
	 * This method will return the preMxml identifier of the component as a child component. Meaning this component has a minor level of 0,<br>
	 * so it is the first component of the parent component and should be considered as a child and NOT a sibling.<br>
	 * 
	 * @param currInstance
	 * @return
	 */
	public final String childReplaceTokenWithPreMxmlIdentifier(_MXMLContract currInstance){
		StringBuffer toReturn = new StringBuffer();
		
		toReturn.append(MXMLConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
		toReturn.append(currInstance.getPreMxmlIdentifier());
		toReturn.append(MXMLConstants.CHILD_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
		
		return toReturn.toString();
	}
	
	/**
	 * This method will return the preMxml identifier of the component as a sibling component. Meaning this component does NOT have a minor level of 0,<br>
	 * so it is NOT the first component of the parent component and should be NOT considered as a child but as a sibling.<br>
	 * 
	 * @param currInstance
	 * @return
	 */
	public final String siblingReplaceTokenWithPreMxmlIdentifier(_MXMLContract currInstance){
		StringBuffer toReturn = new StringBuffer();
		
		toReturn.append(MXMLConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_PRE);
		toReturn.append(currInstance.getPreMxmlIdentifier());
		toReturn.append(MXMLConstants.SIBLING_REPLACE_TOKEN_PREMXML_IDENTIFIER_SUFF);
		
		return toReturn.toString();
	}
	
	
	/**
	 * This method will return _CommonTaskRunner interface from MxmlContext.<br>
	 * 
	 * @return
	 */
	public final _CommonTaskRunner getCommonTaskRunner(){
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		return mxmlContext.getCommonRunner();
	}
		
	/**
	 * This method will return _FileManipulatorTaskRunner interface from MxmlContext.<br>
	 * 
	 * @return
	 */
	public final _FileManipulatorTaskRunner getFileManipulatorTaskRunner(){
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		return mxmlContext.getFileManipulatorRunner();
	}
	
	/**
	 * This method will return _FlexTaskRunner interface from MxmlContext.<br>
	 * 
	 * @return
	 */
	public final _FlexTaskRunner getFlexTaskRunner(){
		MxmlContext mxmlContext = MxmlContext.getCurrentInstance();
		return mxmlContext.getFlexRunner();
	}
	
}