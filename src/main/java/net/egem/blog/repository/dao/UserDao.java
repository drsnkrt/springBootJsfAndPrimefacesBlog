package net.egem.blog.repository.dao;

import net.egem.blog.model.Contact;
import net.egem.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
