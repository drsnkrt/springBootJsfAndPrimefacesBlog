package net.egem.blog.repository.service;

import net.egem.blog.model.Article;
import net.egem.blog.model.User;
import net.egem.blog.repository.dao.ArticleDao;
import net.egem.blog.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ArticleService {


    @Autowired
    private ArticleDao dao;

    @Transactional
    public Article save(Article article) {
        return dao.save(article);
    }

    public List<Article> getAllArticles() {
        return dao.findAll();
    }

    public Article getArticleById(int id) {


        return dao.findById(id);
    }


}
