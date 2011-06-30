package com.googlecode.jsfflexeclipseplugin.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import com.googlecode.jsfflexeclipseplugin.JsfFlexActivator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public final class JsfFlexASAttributesClassPreferencePage 
										extends FieldEditorPreferencePage
										implements IWorkbenchPreferencePage {
	
	private BooleanFieldEditor _attemptFetchOfLatestASAPIs;
	private StringFieldEditor _latestASAPIsURL;
	
	public JsfFlexASAttributesClassPreferencePage() {
		super(GRID);
		setPreferenceStore(JsfFlexActivator.getDefault().getPreferenceStore());
		setDescription(Messages.DESCRIPTION);
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		
		_attemptFetchOfLatestASAPIs = new BooleanFieldEditor(PreferenceConstants.ATTEMPT_FETCH_OF_LATEST_AS_APIS, Messages.ATTEMPT_FETCH_LATEST_AS_APIS, getFieldEditorParent());
		addField(_attemptFetchOfLatestASAPIs);
		
		_latestASAPIsURL = new StringFieldEditor(PreferenceConstants.LATEST_AS_APIS_URL, Messages.LATEST_AS_APIS_URL, getFieldEditorParent());
		_latestASAPIsURL.setErrorMessage(Messages.LATEST_AS_APIS_URL_WARNING);
		addField(_latestASAPIsURL);
	}

	public void init(IWorkbench workbench) {
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		
		if(event.getSource() == _attemptFetchOfLatestASAPIs){
			checkState();
		}
	}
	
	@Override
	protected void checkState() {
		super.checkState();
		if(!isValid()){
			return;
		}
		
		boolean valid = true;
		if(_attemptFetchOfLatestASAPIs.getBooleanValue()){
			_latestASAPIsURL.setEnabled(true, getFieldEditorParent());
			if(_latestASAPIsURL.getStringValue().length() == 0){
				valid = false;
				_latestASAPIsURL.showErrorMessage();
			}
		}else{
			_latestASAPIsURL.setEnabled(false, getFieldEditorParent());
			
		}
		
		setValid(valid);
	}
	
}