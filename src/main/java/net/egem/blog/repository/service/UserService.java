package net.egem.blog.repository.service;

import net.egem.blog.model.User;
import net.egem.blog.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    @Autowired
    private UserDao dao;

    public User save(User user) {
        return dao.save(user);
    }

    public List<User> getAllUsers() {
        return dao.findAll();
    }

    public User getUser(int id) {

        return dao.getOne(id);

    }

    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

}
