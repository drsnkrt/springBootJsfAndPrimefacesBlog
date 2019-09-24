package net.egem.blog.repository.dao;

import net.egem.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {

    public Tag getTagByArticle_Id(int id);

}
