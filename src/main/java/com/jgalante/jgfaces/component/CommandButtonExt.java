package com.jgalante.jgfaces.component;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.expression.SearchExpressionFacade;

public class CommandButtonExt extends CommandButton {

	protected enum PropertyKeys {

		widgetVar
		,ajax
		,async
		,process
		,update
		,onstart
		,oncomplete
		,onerror
		,onsuccess
		,global
		,partialSubmit
		,icon
		,iconPos
		,inline
		,escape
		,messages;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
}
	}
	
	public java.lang.String getMessages() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.messages, ":grp-dlg-messages");
	}
	
	public void setMessages(java.lang.String _messages) {
		getStateHelper().put(PropertyKeys.messages, _messages);
//		handleAttribute("messages", _messages);
	}
	
	@Override
	public java.lang.String getUpdate() {
		java.lang.String update = super.getUpdate();
		try {
			//TODO: test this change
			//ComponentUtils.findClientIds(getFacesContext(), this, getMessages());
			SearchExpressionFacade.resolveComponentsForClient(getFacesContext(), this, getMessages());
		
			StringBuilder sb = new StringBuilder(getMessages());
	        if (update != null){
	        	if (!update.contains(sb.toString())) {
	        		update = update.replaceAll(",", " ");        		
		        	if (sb.length() > 0) {
			        	sb.append(" ");
		        	}
			        sb.append(update);
	        	}
	        }
	        
	        super.setUpdate(sb.toString());	        
			return super.getUpdate();
		} catch (Exception e){
			return update;
		}
	}
	
}
