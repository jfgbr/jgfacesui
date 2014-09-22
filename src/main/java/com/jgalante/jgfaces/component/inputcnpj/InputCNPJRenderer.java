package com.jgalante.jgfaces.component.inputcnpj;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtext.InputTextRenderer;
import org.primefaces.util.WidgetBuilder;

public class InputCNPJRenderer extends InputTextRenderer {

	@Override
	protected void encodeScript(FacesContext context, InputText inputText)
			throws IOException {
		
		ResponseWriter writer = context.getResponseWriter();
		String clientId = inputText.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("InputText", inputText.resolveWidgetVar(), clientId);
        
//        startScript(writer, clientId);
//        writer.write(wb.build());
        wb.append("});");
        cnpjScript(writer,clientId);
        endScript(writer);
        
	}
	
	protected void cnpjScript(ResponseWriter writer, String clientId) throws IOException {
//		writer.append("$(function(){");
        writer.append("$(\"[id='").append(clientId).append("']\").cnpj();");
        writer.append("});");
	}
	
}
