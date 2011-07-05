package com.googlecode.jsfFlex.shared.tasks;

import java.io.File;
import java.io.InputStream;

public interface _CommonTaskRunner extends _TaskRunner{
	
	void unZipArchiveRelative(String file, String dest);
	
	void unZipArchiveAbsolute(File file, String dest);
	
	void unZipArchiveAbsolute(InputStream file, String dest);
	
}
