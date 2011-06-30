package com.googlecode.jsfflexeclipseplugin.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;

/**
 * Class used to initialize default preference values.
 */
public final class PreferenceInitializer extends AbstractPreferenceInitializer {
	
	private static final String DEFAULT_LATEST_AS_APIS_URL = "http://help.adobe.com/en_US/FlashPlatform//reference/actionscript/3/class-summary.html";
	
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JsfFlexActivator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.ATTEMPT_FETCH_OF_LATEST_AS_APIS, false);
		store.setDefault(PreferenceConstants.LATEST_AS_APIS_URL, DEFAULT_LATEST_AS_APIS_URL);
	}

}
