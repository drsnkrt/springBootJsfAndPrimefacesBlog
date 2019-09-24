package net.egem.blog.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "successController")
@Scope(value = "session")
@Component(value = "successController")
@Join(path = "/success", to = "/pages/success.jsf")
public class SuccessController {
}
