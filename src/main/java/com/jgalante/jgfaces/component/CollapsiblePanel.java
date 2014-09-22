package com.jgalante.jgfaces.component;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@FacesComponent("collapsiblePanel")
public class CollapsiblePanel extends UINamingContainer {

    enum PropertyKeys {
        collapsed,
        header        
    }

    public boolean isCollapsed() {
        return (Boolean) getStateHelper().eval(PropertyKeys.collapsed, Boolean.FALSE);
    }

    public void setCollapsed(boolean collapsed) {
        getStateHelper().put(PropertyKeys.collapsed, collapsed);
    }
    
    public String getHeader() {
        return  (String) getStateHelper().eval(PropertyKeys.header, "");
    }
    
    public void setHeader(String header) {
    	getStateHelper().put(PropertyKeys.header, header);
    }

    public void toggle(ActionEvent e) {
        setCollapsed(!isCollapsed());
        setCollapsedValueExpression();
    }

    private void setCollapsedValueExpression() {
        ELContext ctx = FacesContext.getCurrentInstance().getELContext();
        ValueExpression ve = getValueExpression(PropertyKeys.collapsed.name());
        if (ve != null) {
            ve.setValue(ctx, isCollapsed());
        }
    }

}
