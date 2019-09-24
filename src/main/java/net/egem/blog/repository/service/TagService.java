package net.egem.blog.repository.service;

import net.egem.blog.model.Comment;
import net.egem.blog.model.Tag;
import net.egem.blog.repository.dao.CommentDao;
import net.egem.blog.repository.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {


    @Autowired
    private TagDao dao;

    public void save(Tag tag) {
        dao.save(tag);
    }

    public List<Tag> getAllTags() {
        return dao.findAll();
    }

    public Tag getTagByArticleId(int id) {

        return dao.getTagByArticle_Id(id);
    }

}
