package net.egem.blog.controller;

import net.egem.blog.model.Contact;
import net.egem.blog.model.User;
import net.egem.blog.repository.service.ContactService;
import net.egem.blog.repository.service.UserService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Scope(value = "session")
@Component(value = "userListController")
@Join(path = "/userlist", to = "/pages/userList.jsf")
@ManagedBean(name = "userListController")
public class UserListContoller {


    @Inject
    private UserService service;

    private List<User> userList = new ArrayList<>();
    private User selectedUser;

    private String searchKeyword;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        userList = service.getAllUsers();
    }

    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String userEmail = context.getExternalContext().getRequestParameterMap().get("userEmail");
            User user = service.findByEmail(userEmail);
            return new DefaultStreamedContent(new ByteArrayInputStream(user.getImage()));
        }
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


}
