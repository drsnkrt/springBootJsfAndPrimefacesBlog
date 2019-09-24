package net.egem.blog.controller;


import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "indexController")
@Scope(value = "session")
@Component(value = "indexController")
@Join(path = "/", to = "/pages/index.jsf")
public class IndexController {


}
