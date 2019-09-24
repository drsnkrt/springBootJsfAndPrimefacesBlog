package net.egem.blog.model;


import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@Entity
@Table(name = "t_contact")
@ManagedBean
@SessionScoped
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private boolean isRead;


    public Contact() {
    }

    public Contact(String name, String email, String subject, String message,boolean isRead) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.isRead=isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
