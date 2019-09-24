package net.egem.blog.controller;

import net.egem.blog.model.Contact;
import net.egem.blog.repository.service.ContactService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Scope(value = "session")
@Component(value = "contactListController")
@Join(path = "/contactlist", to = "/pages/contactList.jsf")
@ManagedBean(name = "contactListController")
public class ContactListContoller {


    @Inject
    private ContactService service;

    private List<Contact> contactList = new ArrayList<>();

    private List<String> readList = new ArrayList<>();

    private String searchKeyword;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        contactList = service.getAllContacts();
        if (readList.size() == 0) {
            readList.add("true");
            readList.add("false");
        }

    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Contact myObject = (Contact) event.getObject();
            myObject.setRead(myObject.isRead());
            service.save(myObject);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/contactlist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<String> getReadList() {
        return readList;
    }

    public void setReadList(List<String> readList) {
        this.readList = readList;
    }


}
