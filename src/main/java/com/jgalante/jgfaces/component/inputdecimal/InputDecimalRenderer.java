package com.jgalante.jgfaces.component.inputdecimal;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtext.InputTextRenderer;
import org.primefaces.util.WidgetBuilder;

public class InputDecimalRenderer extends InputTextRenderer {

	@Override
	protected void encodeScript(FacesContext context, InputText inputText)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = inputText.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("InputText", inputText.resolveWidgetVar(), clientId);
        
//        encodeClientBehaviors(context, inputText);

//        startScript(writer, clientId);
//        writer.write(build(wb));
        wb.append("});");
		String decimal = ((InputDecimal)inputText).getDecimal();
        String dezena = ((InputDecimal)inputText).getDezena();
        int scale = ((InputDecimal)inputText).getScale();
        boolean showDefault = ((InputDecimal)inputText).getShowDefault(); 
		decimalScript(wb, clientId, decimal, dezena, scale, showDefault);
//		wb.finish();
        endScript(writer);
        
	}
	
	protected void decimalScript(WidgetBuilder wb, String clientId, String decimal, String dezena, int scale, boolean showDefault) throws IOException {
//		wb.append("$(function(){");
		wb.append("$(\"[id='").append(clientId)
				.append("']\").decimal({separadorDecimal : \"").append(decimal)
				.append("\",separadorDezenas :\"").append(dezena)
				.append("\",qtdDecimal :").append(String.valueOf(scale))
				.append(",exibeValorPadrao :").append(String.valueOf(showDefault))
				.append("});");
        wb.append("});");
	}
	
}
