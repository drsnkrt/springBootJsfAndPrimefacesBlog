package net.egem.blog.controller;

import net.egem.blog.model.Contact;
import net.egem.blog.model.User;
import net.egem.blog.repository.service.UserService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Scope(value = "session")
@Component(value = "userController")
@Join(path = "/adduser", to = "/pages/addUser.jsf")
@ManagedBean(name = "userController")
public class UserController {

    public User user;
    @Inject
    private UserService service;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        user = new User();
    }

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String save(User user) {

        System.out.println("userController save method begin");
        if (service.findByEmail(user.getEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail Kullanılıyor", ""));
        } else if (null == user.getEmail()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail alanı boş bırakılamaz", ""));
        } else if (null == user.getName()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ad alanı boş bırakılamaz", ""));
        } else if (null == user.getSurname()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Soyad alanı boş bırakılamaz", ""));
        } else {
            user.setImage(file.getContents());
            service.save(user);
            return "/pages/success.xhtml?faces-redirect=true";
        }
        return "";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
