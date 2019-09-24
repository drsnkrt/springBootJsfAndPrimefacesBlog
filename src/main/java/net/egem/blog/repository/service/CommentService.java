package net.egem.blog.repository.service;

import net.egem.blog.model.Comment;
import net.egem.blog.repository.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentDao dao;

    public void save(Comment comment) {
        dao.save(comment);
    }

    public List<Comment> getAllComments() {
        return dao.findAll();
    }

    public List<Comment> getCommentsByAritcleId(int id) {

        return dao.getCommentsByArticle_Id(id);
    }

}
