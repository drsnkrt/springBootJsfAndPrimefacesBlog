package net.egem.blog.repository.dao;

import net.egem.blog.model.Article;
import net.egem.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    public Article findById(@Param("id") int id);
}
