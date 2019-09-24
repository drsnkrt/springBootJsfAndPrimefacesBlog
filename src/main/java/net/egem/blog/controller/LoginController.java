package net.egem.blog.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginController")
@Scope(value = "session")
@Component(value = "loginController")
@Join(path = "/login", to = "/pages/login.jsf")
public class LoginController {

    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
