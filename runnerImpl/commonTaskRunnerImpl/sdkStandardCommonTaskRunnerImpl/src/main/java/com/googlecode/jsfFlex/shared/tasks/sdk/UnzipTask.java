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
package com.googlecode.jsfFlex.shared.tasks.sdk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.jsfFlex.shared.exception.ComponentBuildException;
import com.googlecode.jsfFlex.shared.tasks.AbstractTask;

/**
 * @author Ji Hoon Kim
 */
public final class UnzipTask extends AbstractTask {
	
	private final static Log _log = LogFactory.getLog(UnzipTask.class);
	
	private static final int BUFFER_SIZE = 2048;
	
	private InputStream _file;
	private String _dest;
	
	public UnzipTask(){
		super();
	}
	
	public UnzipTask(InputStream file, String dest){
		super();
		_file = file;
		_dest = dest;
	}
	
	protected void performTask() {
		
		BufferedOutputStream bufferOutputStream = null;
		ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(_file));
		ZipEntry entry;
		
		try{
			
			while((entry = zipInputStream.getNextEntry()) != null){
				
				ensureDirectoryExists(entry.getName(), entry.isDirectory());
				
				bufferOutputStream = new BufferedOutputStream(new FileOutputStream(_dest + entry.getName()), BUFFER_SIZE);
				int currRead = 0;
				byte[] dataRead = new byte[BUFFER_SIZE];
				
				while((currRead = zipInputStream.read(dataRead, 0, BUFFER_SIZE)) != -1){
					bufferOutputStream.write(dataRead, 0, currRead);
				}
				bufferOutputStream.flush();
				bufferOutputStream.close();
			}
			
			_log.debug("UnzipTask performTask has been completed with " + toString());
		}catch(IOException ioExcept){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Error in Unzip's performTask with following fields \n");
			errorMessage.append(toString());
			throw new ComponentBuildException(errorMessage.toString(), ioExcept);
		}finally{
			try{
				zipInputStream.close();
				if(bufferOutputStream != null){
					bufferOutputStream.close();
				}
			}catch(IOException innerIOExcept){
				_log.info("Error while closing the streams within UnzipTask's finally block");
			}
		}
		
	}
	
	private void ensureDirectoryExists(String directoryToCheck, boolean isDirectory){
		String[] directorySplitted = directoryToCheck.split("/");
		int lengthToTraverse = isDirectory ? directorySplitted.length : directorySplitted.length - 1;
		
		String tempLocation = _dest;
		for(int i=0; i < lengthToTraverse; i++){
			tempLocation += directorySplitted[i] + File.separatorChar;
			File currDirCheck = new File(tempLocation);
			if(!currDirCheck.exists()){
				currDirCheck.mkdir();
			}
		}
		
	}
	
	public String toString(){
		StringBuilder content = new StringBuilder();
		content.append("file [ ");
		content.append(_file);
		content.append(" ] ");
		content.append("dest [ ");
		content.append(_dest);
		content.append(" ]");
		return content.toString();
	}
	
	public void dest(String dest) {
		_dest = dest;
	}
	public void file(InputStream file) {
		_file = file;
	}
	
}
