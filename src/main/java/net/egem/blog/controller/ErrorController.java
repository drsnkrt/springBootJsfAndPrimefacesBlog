package net.egem.blog.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "errorController")
@Scope(value = "session")
@Component(value = "errorController")
@Join(path = "/error", to = "/pages/error.jsf")
public class ErrorController {
}
