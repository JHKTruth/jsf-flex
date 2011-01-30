package com.googlecode.jsfFlex.shared.tasks;

import java.io.File;
import java.io.InputStream;

public interface ICommonTaskRunner extends ITaskRunner {
	
	void unZipArchiveRelative(String file, String dest, String queueTaskId);
	
	void unZipArchiveAbsolute(File file, String dest, String queueTaskId);
	
	void unZipArchiveAbsolute(InputStream file, String dest, String queueTaskId);
	
}
