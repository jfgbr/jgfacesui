package com.jgalante.jgfaces.component.enterpress;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;
@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="primefaces.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js")
})
@FacesComponent("enterPress")
public class EnterPress extends UIComponentBase implements Widget {

	public static final String COMPONENT_TYPE = "com.jgalante.jgfaces.component.EnterPress";
	public static final String COMPONENT_FAMILY = "com.jgalante.jgfaces.component";
	private static final String DEFAULT_RENDERER = "com.jgalante.jgfaces.component.EnterPressRenderer";

	protected enum PropertyKeys {
		target
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return DEFAULT_RENDERER;
	}
	
	public String getTarget() {
        return  (String) getStateHelper().eval(PropertyKeys.target, null);
    }
    
    public void setTarget(String target) {
    	getStateHelper().put(PropertyKeys.target, target);
    }

	public String resolveWidgetVar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}
}
