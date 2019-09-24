package net.egem.blog.controller;

import net.egem.blog.model.Contact;
import net.egem.blog.repository.service.ContactService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Scope(value = "session")
@Component(value = "contactController")
@Join(path = "/contact", to = "/pages/contact.jsf")
@ManagedBean(name = "contactController")
public class ContactContoller {

    public Contact contact;

    @Inject
    private ContactService service;


    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        contact = new Contact();
    }

    public String save(Contact contact) {

        System.out.println("ContactContoller save method begin");

        if (service.getContactByEmail(contact.getEmail()) != null && service.getContactByEmail(contact.getEmail()).isRead() == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cevaplanmamış sorularınız mevcut", ""));
        } else if (null == contact.getEmail()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail alanı boş bırakılamaz", ""));
        } else if (null == contact.getName()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ad alanı boş bırakılamaz", ""));
        } else if (null == contact.getSubject()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Konu alanı boş bırakılamaz", ""));
        } else if (contact.getMessage().length() < 15) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesaj alanı 15 karakterden uzun olmalıdır", ""));
        } else if (null == contact.getMessage()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesaj alanı boş bırakılamaz", ""));
        } else {
            service.save(contact);
            return "/pages/success.xhtml?faces-redirect=true";
        }
        return "";
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
