package com.jgalante.jgfaces.component.enterpress;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

public class EnterPressRenderer extends CoreRenderer {

	@Override
	public void encodeEnd(final FacesContext context,
			final UIComponent component) throws IOException {
		
		ResponseWriter writer = context.getResponseWriter();
		final EnterPress enterPress = (EnterPress) component;
		UIComponent parent = component.getParent();
		String parentId = component.getParent().getClientId(context);
//		final String clientId = enterPress.getClientId(context);
		
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady(parent.getClass().getName(), enterPress.resolveWidgetVar(), parentId);
        
//        startScript(writer, parentId);
//        wb.finish();
        wb.append("});");
//        endScript(writer);

//		startScript(writer, clientId);
//		writer.write("$(function() {");
		writer.append("$(\"[id='")
				.append(parentId)
				.append("']\").keypress(function(e) {if(e.which == 13) {")
				.append("$(\"[id='")
				.append(enterPress.getTarget())
				.append("']\").click();e.preventDefault();return false;")
				.append("}});");

		writer.write("});");
		endScript(writer);
	}

}
