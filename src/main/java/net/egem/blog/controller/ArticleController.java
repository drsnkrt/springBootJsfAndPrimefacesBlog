package net.egem.blog.controller;


import net.egem.blog.model.Article;
import net.egem.blog.model.Comment;
import net.egem.blog.model.User;
import net.egem.blog.repository.service.ArticleService;
import net.egem.blog.repository.service.CommentService;
import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Scope(value = "session")
@Component(value = "articleController")
@Join(path = "/blog", to = "/pages/articles.jsf")
@ManagedBean(name = "articleController")
public class ArticleController {

    @Inject
    ArticleService articleService;
    @Inject
    CommentService commentService;

    private RepeatPaginator paginator;

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    @PostConstruct
    public void init() {
        articles = articleService.getAllArticles();
        paginator = new RepeatPaginator(this.articles);

    }

    public int getCommentCount(int id) {
        return commentService.getCommentsByAritcleId(id).size();
    }

    public int getArticleReadCount(int id) {
        return articleService.getArticleById(id).getCount();
    }

    public String convertTime(Date time) {
        Date date = new Date();
        Format format = new SimpleDateFormat("hh:mm:ss dd-MM-YYYY");
        return format.format(date);
    }

    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String articleId = context.getExternalContext().getRequestParameterMap().get("articleId");
            Article article = articleService.getArticleById(Integer.parseInt(articleId));
            return new DefaultStreamedContent(new ByteArrayInputStream(article.getImage()));
        }
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

}
