package com.googlecode.jsfflexeclipseplugin.processor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.googlecode.jsfflexeclipseplugin.processor.messages"; //$NON-NLS-1$
	public static String ATTRIBUTES_OF_AS_CLASS;
	public static String IO_EXCEPTION_WAS_THROWN;
	public static String MALFORMED_URL_EXCEPTION_WAS_THROWN;
	public static String PARSING;
	public static String PARSING_OF_TOP_AS_CLASS;
	public static String STARTING_THE_AS_PARSING_PROCESS;
	public static String STARTING_THE_PARSE_OF_AS_CLASS;
	public static String XPATHER_EXCEPTION_WAS_THROWN;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
