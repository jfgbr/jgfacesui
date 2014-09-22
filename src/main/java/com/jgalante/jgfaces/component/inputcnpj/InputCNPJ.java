package com.jgalante.jgfaces.component.inputcnpj;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import org.primefaces.component.inputtext.InputText;
@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="jgfacesui", name="basico/jquery.basico.js"),
	@ResourceDependency(library="jgfacesui", name="cnpj/jquery.cnpj.js")
})
@FacesComponent("inputCNPJ")
public class InputCNPJ extends InputText {


	public static final String COMPONENT_TYPE = "com.jgalante.jgfaces.component.InputCNPJ";
	public static final String COMPONENT_FAMILY = "com.jgalante.jgfaces.component";
	private static final String DEFAULT_RENDERER = "com.jgalante.jgfaces.component.InputCNPJRenderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return DEFAULT_RENDERER;
	}

}
