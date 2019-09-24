package net.egem.blog.controller;


import net.egem.blog.model.*;
import net.egem.blog.repository.service.ArticleService;
import net.egem.blog.repository.service.CommentService;
import net.egem.blog.repository.service.TagService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Scope(value = "session")
@Component(value = "detailController")
@Join(path = "/blog/detail", to = "/pages/detail.jsf")
@ManagedBean(name = "detailController")
public class DetailController {


    @Inject
    ArticleService articleService;
    @Inject
    CommentService commentService;
    @Inject
    TagService tagService;

    Comment comment;
    int id;

    int count;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        comment = new Comment();
        System.out.println(id);
    }

    public void save(Comment comment, int id) {

        System.out.println("CommentContoller save method begin");
        ExternalContext context = null;
        try {
            if (comment == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cevaplanmamış sorularınız mevcut", ""));
            } else {
                Article article = articleService.getArticleById(id);
                comment.setComment(comment.getComment());
                comment.setArticle(article);
                commentService.save(comment);
                context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/blog/detail?id=" + id);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                context.redirect(context.getRequestContextPath() + "/pages/error.xhtml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void increase(int id) {
        Article article = articleService.getArticleById(id);
        article.setCount(article.getCount() + 1);
        articleService.save(article);

    }

    public List<String> getTag(int id) {
        List<String> taglist = new ArrayList<>();
        try {
            String tag = tagService.getTagByArticleId(id).getTags();
            String[] tags = tag.split(",");
            for (int i = 0; i < tags.length; i++) {
                taglist.add(tags[i]);
            }
            return taglist;
        } catch (Exception e) {
            return null;
        }


    }

    public List<Comment> getComments(int id) {
        return commentService.getCommentsByAritcleId(id);

    }

    public String getTitle(int id) {
        return articleService.getArticleById(id).getTitle();
    }

    public String getArticle(int id) {
        return articleService.getArticleById(id).getArticle();
    }

    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String articleId = context.getExternalContext().getRequestParameterMap().get("articleDetailId");
            Article article = articleService.getArticleById(Integer.parseInt(articleId));
            return new DefaultStreamedContent(new ByteArrayInputStream(article.getImage()));
        }
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
