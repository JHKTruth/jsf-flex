package com.googlecode.jsfFlex.attributes;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

/**
 * @author Ji Hoon Kim
 */
@JSFComponent(configExcluded=true)
public interface IFlexUISelectedLabelAttribute {

	/**
	 * Selected Label.
	 */
    @JSFProperty(desc = "Selected Label.")
    String getSelectedLabel();
    
    void setSelectedLabel(String selectedLabel);
    
}
