package net.egem.blog.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@Entity
@Table(name = "t_comment")
@ManagedBean
@SessionScoped
public class Comment {

    @Id
    @GeneratedValue
    private int id;
    private String comment;

    public Comment(String comment, Article article) {
        this.comment = comment;
        this.article = article;
    }

    public Comment() {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "articleId", nullable = false)
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
