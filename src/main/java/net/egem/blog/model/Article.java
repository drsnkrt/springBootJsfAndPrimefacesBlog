package net.egem.blog.model;

import org.hibernate.annotations.Type;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_article")
@ManagedBean
@SessionScoped
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    @Size(max = 4000)
    private String article;
    private Date recordDate;
    int count;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Article() {
    }

    public Article(String title, String article, Date recordDate, byte[] image, int count) {
        this.title = title;
        this.article = article;
        this.recordDate = recordDate;
        this.image = image;
        this.count = count;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = new Date();
    }


}
