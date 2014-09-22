package com.jgalante.jgfaces.component.dialogtermocompromisso;

import org.primefaces.component.dialog.DialogRenderer;

public class DialogTermoCompromissoRenderer extends DialogRenderer {
	
//	@Override
//	protected void encodeContent(FacesContext context, Dialog dialog)
//			throws IOException {
//		
//		DialogTermoCompromisso dialogTermoCompromisso = (DialogTermoCompromisso) dialog;
//		String id = dialogTermoCompromisso.getId();
//		
//		String hide = dialogTermoCompromisso.resolveWidgetVar().concat(".hide();");
//		
//		InputTextarea inputTextarea = new InputTextarea();
//		inputTextarea.setValue(dialogTermoCompromisso.getValue());
//		inputTextarea.setReadonly(true);
//		inputTextarea.setAutoResize(false);
//		inputTextarea.setCols(90);
//		inputTextarea.setRows(20);
//		
//		dialogTermoCompromisso.getChildren().add(inputTextarea);
//		
//		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
//		panelGroup.setLayout("block");
//		panelGroup.setStyle("clear: both;padding-bottom: 30px;");
//		
//		dialogTermoCompromisso.getChildren().add(panelGroup);
//		
//		if (dialogTermoCompromisso.isViewOnly()) {
//			CommandButton buttonOk = new CommandButton();
//			buttonOk.setId(id.concat("BtnOk"));
//			buttonOk.setValue("Ok");
//			buttonOk.setOncomplete(hide);
//			buttonOk.setUpdate("@form");
//			buttonOk.setProcess("@this");
//			dialogTermoCompromisso.getChildren().add(buttonOk);
//		} else {
//			CommandButton buttonAceitar = new CommandButton();
//			buttonAceitar.setId(id.concat("BtnAceitar"));
//			buttonAceitar.setValue("Aceitar");
//			buttonAceitar.setActionExpression(dialogTermoCompromisso.getActionAceitar());			
//			buttonAceitar.setUpdate(dialogTermoCompromisso.getUpdateAceitar());
//			buttonAceitar.setProcess(dialogTermoCompromisso.getProcessAceitar());
//			
//			dialogTermoCompromisso.getChildren().add(buttonAceitar);
//			
//			CommandButton buttonCancelar = new CommandButton();
//			buttonCancelar.setId(id.concat("BtnCancelar"));
//			buttonCancelar.setValue("Cancelar");
//			buttonCancelar.setActionExpression(dialogTermoCompromisso.getActionCancelar());
//			buttonCancelar.setOncomplete(hide);
//			buttonCancelar.setUpdate("@form");
//			buttonCancelar.setProcess("@this");
//			
//			dialogTermoCompromisso.getChildren().add(buttonCancelar);
//		}
//		
//		
//		super.encodeContent(context, dialog);
//	}

}
