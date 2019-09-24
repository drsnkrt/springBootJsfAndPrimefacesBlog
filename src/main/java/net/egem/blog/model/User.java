package net.egem.blog.model;


import org.hibernate.annotations.Type;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@Entity
@Table(name = "t_user")
@ManagedBean
@SessionScoped
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;
    private String email;
    private String password;


    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    public User(String name, String password, String surname, String email, byte[] image) {
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.email = email;
        this.image = image;
    }

    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
