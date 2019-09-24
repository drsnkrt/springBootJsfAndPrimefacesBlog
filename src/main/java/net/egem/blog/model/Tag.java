package net.egem.blog.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_tag")
@ManagedBean
@SessionScoped
public class Tag {

    @Id
    @GeneratedValue
    private int id;
    private String tags;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Tag(String tags, Article article) {
        this.tags = tags;
        this.article = article;
    }

    public Tag() {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "articleId", nullable = false)
    private Article article;



}
