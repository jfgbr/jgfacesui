package com.jgalante.jgfaces.component;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent("messageDialog")
public class MessageDialog extends UINamingContainer {
	
	enum PropertyKeys {
        autoUpdate,
        severity,
        header,
        visible,
        globalOnly,
        widgetVar,
        styleClass
    }
	
	enum MessageTypes {
		INFO,
		WARN,
		ERROR,
		FATAL
	}
	
	
	public boolean isAutoUpdate() {
        return (Boolean) getStateHelper().eval(PropertyKeys.autoUpdate, Boolean.TRUE);
    }

    public void setAutoUpdate(boolean autoUpdate) {
        getStateHelper().put(PropertyKeys.autoUpdate, autoUpdate);
    }
    
    public String getSeverity() {
    	return (String) getStateHelper().eval(PropertyKeys.severity, String.valueOf(getSeverityName("info").getOrdinal()));
    }
    
    public void setSeverity(String severity) {
        getStateHelper().put(PropertyKeys.severity, String.valueOf(getSeverityName(severity).getOrdinal()));
    }
    
    public String getHeader() {
    	return (String) getStateHelper().eval(PropertyKeys.header, "info");
    }
    
    public void setHeader(String header) {
        getStateHelper().put(PropertyKeys.header, header);
    }
    
    public boolean isVisible() {
        return (Boolean) getStateHelper().eval(PropertyKeys.visible, resolveVisible());
    }

    public void setVisible(boolean visible) {
        getStateHelper().put(PropertyKeys.visible, visible);
    }
    
    public boolean isGlobalOnly() {
        return (Boolean) getStateHelper().eval(PropertyKeys.globalOnly, Boolean.TRUE);
    }

    public void setGlobalOnly(boolean globalOnly) {
        getStateHelper().put(PropertyKeys.globalOnly, globalOnly);
    }
    
    public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, resolveWidgetVar(null));
	}
    
	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, resolveWidgetVar(_widgetVar));
	}
	
	public java.lang.String getStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, "");
    }

    public void setStyleClass(java.lang.String styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }
    
    protected Severity getSeverityName(String name) {
        if (name != null && name.indexOf("info") != -1) {
        	return FacesMessage.SEVERITY_INFO;
        } else if (name != null && name.indexOf("error") != -1) {
        	return FacesMessage.SEVERITY_ERROR;
        } else if (name != null && name.indexOf("warn") != -1) {
        	return FacesMessage.SEVERITY_WARN;
        } else if (name != null && name.indexOf("fatal") != -1) {
        	return FacesMessage.SEVERITY_FATAL;
        }
        
        return FacesMessage.SEVERITY_INFO;
    }
    
    public boolean resolveVisible() {
    	Severity severity = FacesContext.getCurrentInstance().getMaximumSeverity();
		
    	boolean globalMessage = false;
    	if (isGlobalOnly()) {
	    	Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages(null);
			while(messages.hasNext()) {
				globalMessage = true;
				break;
			}
    	} else {
    		globalMessage = true;
    	}
		
		return severity != null && severity.equals(getSeverityName(getSeverity())) && globalMessage;
    }
    
    public String resolveWidgetVar(String widgetVar) {
		FacesContext context = FacesContext.getCurrentInstance();
		String userWidgetVar = widgetVar;

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

}
