package com.jgalante.jgfaces.component.dialogtermocompromisso;

import java.io.IOException;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtextarea.InputTextarea;

@FacesComponent("dialogTermoCompromisso")
public class DialogTermoCompromisso extends Dialog {

	protected enum PropertyKeys {

		value, viewOnly, processAceitar, updateAceitar, actionAceitar, actionCancelar;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public static final String COMPONENT_TYPE = "com.jgalante.jgfaces.component.DialogTermoCompromisso";
	public static final String COMPONENT_FAMILY = "com.jgalante.jgfaces.component";
	private static final String DEFAULT_RENDERER = "com.jgalante.jgfaces.component.DialogTermoCompromissoRenderer";

	
	public DialogTermoCompromisso() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		String id = getId();

		this.getChildren().clear();
		
		String hide = resolveWidgetVar().concat(".hide();");

		InputTextarea inputTextarea = new InputTextarea();
		inputTextarea.setValue(getValue());
		inputTextarea.setReadonly(true);
		inputTextarea.setAutoResize(false);
		inputTextarea.setCols(90);
		inputTextarea.setRows(20);

		getChildren().add(inputTextarea);

		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
		panelGroup.setLayout("block");
		panelGroup.setStyle("clear: both;padding-bottom: 30px;");
		panelGroup.setParent(this);
		
//		panelGroup.encodeAll(context);

		getChildren().add(panelGroup);

		if (isViewOnly()) {
			CommandButton buttonOk = new CommandButton();
			buttonOk.setId(id.concat("BtnOk"));
			buttonOk.setParent(this);
			buttonOk.setValue("Ok");
			buttonOk.setOncomplete(hide);
			buttonOk.setUpdate("@form");
			buttonOk.setProcess("@this");
//			buttonOk.encodeAll(context);
			if (!getChildren().contains(buttonOk)) {
				getChildren().add(buttonOk);
			}
		} else {
			CommandButton buttonAceitar = new CommandButton();
			buttonAceitar.setId(id.concat("BtnAceitar"));
			buttonAceitar.setParent(this);
			buttonAceitar.setValue("Aceitar");
			System.out.println("getActionAceitar(): " + getActionAceitar());
			buttonAceitar.setActionExpression(getActionAceitar());
			buttonAceitar.setUpdate(getUpdateAceitar());
			buttonAceitar.setProcess(getProcessAceitar());
			
//			buttonAceitar.encodeAll(context);

			if (!getChildren().contains(buttonAceitar)) {
				getChildren().add(buttonAceitar);
			}

			CommandButton buttonCancelar = new CommandButton();
			buttonCancelar.setId(id.concat("BtnCancelar"));
			buttonCancelar.setParent(this);
			buttonCancelar.setValue("Cancelar");
			buttonCancelar.setActionExpression(getActionCancelar());
			buttonCancelar.setOncomplete(hide);
			buttonCancelar.setUpdate("@form");
			buttonCancelar.setProcess("@this");

//			buttonCancelar.encodeAll(context);
			
			if (!getChildren().contains(buttonCancelar)) {
				getChildren().add(buttonCancelar);
			}
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return DEFAULT_RENDERER;
	}
	
//	@Override
//    public void queueEvent(FacesEvent event)
//    {
//        if (event != null && event instanceof ActionEvent)
//        {
//            if (getClientId()+"BtnOk").equals(event.getComponent().getClientId())) {
//            	
//            }
//        }
//        super.queueEvent(event);
//    }

	@Override
	public boolean isModal() {
		return (java.lang.Boolean) getStateHelper().eval(
				Dialog.PropertyKeys.modal, true);
	}

	@Override
	public boolean isResizable() {
		return (java.lang.Boolean) getStateHelper().eval(
				Dialog.PropertyKeys.resizable, false);
	}

	@Override
	public boolean isClosable() {
		return (java.lang.Boolean) getStateHelper().eval(
				Dialog.PropertyKeys.closable, false);
	}

	public boolean isViewOnly() {
		return (Boolean) getStateHelper().eval(PropertyKeys.viewOnly,
				Boolean.TRUE);
	}

	public void setViewOnly(boolean viewOnly) {
		getStateHelper().put(PropertyKeys.viewOnly, viewOnly);
	}

	public Object getValue() {
		return (String) getStateHelper().eval(PropertyKeys.value, null);
	}

	public void setValue(Object value) {
		getStateHelper().put(PropertyKeys.value, value);
	}

	public String getProcessAceitar() {
		return (String) getStateHelper().eval(PropertyKeys.processAceitar,
				"@this");
	}

	public void setProcessAceitar(String process) {
		getStateHelper().put(PropertyKeys.processAceitar, process);
	}

	public String getUpdateAceitar() {
		return (String) getStateHelper().eval(PropertyKeys.updateAceitar,
				"@form");
	}

	public void setUpdateAceitar(String update) {
		getStateHelper().put(PropertyKeys.updateAceitar, update);
	}
	
//	public MethodBinding getActionAceitar()
//    {
//        MethodExpression actionAceitarExpression = getActionAceitarExpression();
//        if (actionAceitarExpression instanceof MethodBindingToMethodExpression)
//        {
//            return ((MethodBindingToMethodExpression) actionAceitarExpression)
//                    .getMethodBinding();
//        }
//        if (actionAceitarExpression != null)
//        {
//            return new MethodExpressionToMethodBinding(actionAceitarExpression);
//        }
//        return null;
//    }
//
//	public void setActionAceitar(MethodBinding action) {
//		if (action != null) {
//			setActionAceitarExpression(new MethodBindingToMethodExpression(
//					action));
//		} else {
//			setActionAceitarExpression(null);
//		}
//	}

	public MethodExpression getActionAceitar() {
		return (MethodExpression) getStateHelper().get(PropertyKeys.actionAceitar);
	}

	public void setActionAceitar(MethodExpression actionAceitar) {
		getStateHelper().put(PropertyKeys.actionAceitar, actionAceitar);
	}

//	public MethodBinding getActionCancelar()
//    {
//        MethodExpression actionCancelarExpression = getActionCancelarExpression();
//        if (actionCancelarExpression instanceof MethodBindingToMethodExpression)
//        {
//            return ((MethodBindingToMethodExpression) actionCancelarExpression)
//                    .getMethodBinding();
//        }
//        if (actionCancelarExpression != null)
//        {
//            return new MethodExpressionToMethodBinding(actionCancelarExpression);
//        }
//        return null;
//    }
//
//	public void setActionCancelar(MethodBinding action) {
//		if (action != null) {
//			setActionCancelarExpression(new MethodBindingToMethodExpression(
//					action));
//		} else {
//			setActionCancelarExpression(null);
//		}
//	}
	
	public MethodExpression getActionCancelar() {
		return (MethodExpression) getStateHelper().get(
				PropertyKeys.actionCancelar);
	}

	public void setActionCancelar(MethodExpression actionCancelar) {
		getStateHelper().put(PropertyKeys.actionCancelar, actionCancelar);
	}

}
