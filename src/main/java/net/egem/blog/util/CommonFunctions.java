package net.egem.blog.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@ManagedBean(name = "commonFunctions")
@ViewScoped
public class CommonFunctions {

    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("internationalization.messages",
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(severity, bundle.getString(summary), bundle.getString(detail)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
