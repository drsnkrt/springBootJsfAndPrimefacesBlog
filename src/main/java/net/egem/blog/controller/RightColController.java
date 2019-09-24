package net.egem.blog.controller;


import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.mobile.component.page.Page;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ManagedBean(name = "rightColController")
@Scope(value = "session")
public class RightColController {


    public boolean getTitle() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        if (url.contains("detail.jsf")) {
            return true;
        } else {
            return false;
        }

    }

}
