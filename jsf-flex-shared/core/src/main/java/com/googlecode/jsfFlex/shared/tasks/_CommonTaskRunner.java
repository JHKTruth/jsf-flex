package com.googlecode.jsfFlex.shared.tasks;

import java.io.File;
import java.io.InputStream;

public interface _CommonTaskRunner extends _TaskRunner {
	
	void unZipArchiveRelative(String file, String dest, String queueTaskId);
	
	void unZipArchiveAbsolute(File file, String dest, String queueTaskId);
	
	void unZipArchiveAbsolute(InputStream file, String dest, String queueTaskId);
	
}
