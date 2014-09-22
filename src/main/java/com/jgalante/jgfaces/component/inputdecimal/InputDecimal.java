package com.jgalante.jgfaces.component.inputdecimal;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import org.primefaces.component.inputtext.InputText;
@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="jgfacesui", name="basico/jquery.basico.js"),
	@ResourceDependency(library="jgfacesui", name="decimal/jquery.decimal.js")
})
@FacesComponent("inputDecimal")
public class InputDecimal extends InputText {


	public static final String COMPONENT_TYPE = "com.jgalante.jgfaces.component.InputDecimal";
	public static final String COMPONENT_FAMILY = "com.jgalante.jgfaces.component";
	private static final String DEFAULT_RENDERER = "com.jgalante.jgfaces.component.InputDecimalRenderer";
	
	enum PropertyKeys {
        decimal,
        dezena,
        scale,
        showDefault
    }

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return DEFAULT_RENDERER;
	}

	public String getDecimal() {
        return  (String) getStateHelper().eval(PropertyKeys.decimal, ",");
    }
    
    public void setDecimal(String decimal) {
    	getStateHelper().put(PropertyKeys.decimal, decimal);
    }
    
    public String getDezena() {
        return  (String) getStateHelper().eval(PropertyKeys.dezena, ".");
    }
    
    public void setDezena(String dezena) {
    	getStateHelper().put(PropertyKeys.dezena, dezena);
    }
    
    public int getScale() {
        return  (Integer) getStateHelper().eval(PropertyKeys.scale, 2);
    }
    
    public void setScale(int scale) {
    	getStateHelper().put(PropertyKeys.scale, scale);
    }
    
    public boolean getShowDefault() {
        return  (Boolean) getStateHelper().eval(PropertyKeys.showDefault, true);
    }
    
    public void setShowDefault(boolean showDefault) {
    	getStateHelper().put(PropertyKeys.showDefault, showDefault);
    }
}
