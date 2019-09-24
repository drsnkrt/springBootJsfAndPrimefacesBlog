package net.egem.blog.repository.dao;

import net.egem.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

    public List<Comment> getCommentsByArticle_Id(int id);

}
