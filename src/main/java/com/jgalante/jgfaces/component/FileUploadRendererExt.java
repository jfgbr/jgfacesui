package com.jgalante.jgfaces.component;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.component.fileupload.FileUploadRenderer;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.util.HTML;
import org.primefaces.util.WidgetBuilder;

public class FileUploadRendererExt extends FileUploadRenderer {
	@Override
	protected void encodeScript(FacesContext context, FileUpload fileUpload)
			throws IOException {
		String clientId = fileUpload.getClientId(context);
        String mode = fileUpload.getMode();
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("FileUpload", fileUpload.resolveWidgetVar(), clientId, "fileupload")
            .attr("mode", mode);
        
        if(!mode.equals("simple")) {
            String update = fileUpload.getUpdate();
            String process = fileUpload.getProcess();
            
            wb.attr("auto", fileUpload.isAuto(), false)
            .attr("dnd", fileUpload.isDragDropSupport(), true)
            .attr("update", SearchExpressionFacade.resolveComponentsForClient(context, fileUpload, update), null)
            .attr("process", SearchExpressionFacade.resolveComponentsForClient(context, fileUpload, process), null)
            .attr("maxFileSize", fileUpload.getSizeLimit(), Long.MAX_VALUE)
            .attr("fileLimit", fileUpload.getFileLimit(), Integer.MAX_VALUE)
            .attr("invalidFileMessage", fileUpload.getInvalidFileMessage(), null)
            .attr("invalidSizeMessage", fileUpload.getInvalidSizeMessage(), null)
            .attr("fileLimitMessage", fileUpload.getFileLimitMessage(), null)
            .attr("messageTemplate", fileUpload.getMessageTemplate(), null)
            .attr("previewWidth", fileUpload.getPreviewWidth(), 80)
            .attr("disabled", fileUpload.isDisabled(), false)
            .callback("onstart", "function()", fileUpload.getOnstart())
            .callback("onerror", "function()", fileUpload.getOnerror())
            .callback("oncomplete", "function()", fileUpload.getOncomplete());

	        if(fileUpload.getAllowTypes() != null) {
	            wb.append(",allowTypes:").append(fileUpload.getAllowTypes());
	        }
        }
        wb.finish();
	}
	
	@Override
	protected void encodeAdvancedMarkup(FacesContext context, FileUpload fileUpload) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
		String clientId = fileUpload.getClientId(context);
        String style = fileUpload.getStyle();
        String styleClass = fileUpload.getStyleClass();
        styleClass = styleClass == null ? FileUpload.CONTAINER_CLASS : FileUpload.CONTAINER_CLASS + " " + styleClass;
        boolean disabled = fileUpload.isDisabled();

		writer.startElement("div", fileUpload);
		writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, styleClass);
        if(style != null) {
            writer.writeAttribute("style", style, "style");
        }
        
        //buttonbar
        writer.startElement("div", fileUpload);
        writer.writeAttribute("class", FileUpload.BUTTON_BAR_CLASS, null);

        //choose button
        encodeChooseButton(context, fileUpload, disabled);
        
        if(!fileUpload.isAuto()) {
            encodeButton(context, fileUpload.getUploadLabel(), FileUpload.UPLOAD_BUTTON_CLASS, "ui-icon-arrowreturnthick-1-n");
            encodeButton(context, fileUpload.getCancelLabel(), FileUpload.CANCEL_BUTTON_CLASS, "ui-icon-cancel");
        }
        
        writer.endElement("div");
        
        //content
//        writer.startElement("div", null);
//        writer.writeAttribute("class", FileUpload.CONTENT_CLASS, null);
//        
//        writer.startElement("table", null);
//        writer.writeAttribute("class", FileUpload.FILES_CLASS, null);
//        writer.endElement("table");
        
//        writer.endElement("div");

		writer.endElement("div");
    }
	
	@Override
	protected void encodeChooseButton(FacesContext context, FileUpload fileUpload, boolean disabled) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = fileUpload.getClientId(context);
        
        // Changed this line
//        String cssClass = HTML.BUTTON_TEXT_ICON_LEFT_BUTTON_CLASS + " " + FileUpload.CHOOSE_BUTTON_CLASS;
        String cssClass = HTML.BUTTON_TEXT_ONLY_BUTTON_CLASS + " " + FileUpload.CHOOSE_BUTTON_CLASS;
        
        if(disabled) {
            cssClass += " ui-state-disabled";
        }
        
        writer.startElement("span", null);
        writer.writeAttribute("class", cssClass, null);
        
        // Added this line
        writer.writeAttribute("style", "text-align:center;margin:0;padding:0;width:auto;" , null);
        
        // Removes theses lines
        //button icon
//        writer.startElement("span", null);
//        writer.writeAttribute("class", HTML.BUTTON_LEFT_ICON_CLASS + " ui-icon-plusthick", null);
//        writer.endElement("span");
        
        //text
        writer.startElement("span", null);
        writer.writeAttribute("class", HTML.BUTTON_TEXT_CLASS, null);
        writer.writeText(fileUpload.getLabel(), "value");
        writer.endElement("span");

        if(!disabled) {
            encodeInputField(context, fileUpload, clientId + "_input", "advanced");
        }
        
		writer.endElement("span");
		
    }
}
