package net.egem.blog.repository.service;

import net.egem.blog.model.Contact;
import net.egem.blog.repository.dao.ContactDao;
import net.egem.blog.repository.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactService {


    @Autowired
    private ContactDao dao;

    public boolean save(Contact contact) {
        if (contact.getMessage().length() < 15 || contact.getMessage().length() > 255) {
            return false;

        } else {
            dao.save(contact);
            return true;
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = dao.findAll();
        List<Contact> unreadMessages = new ArrayList<>();

        for (Contact c : contactList) {
            if (c.isRead() == false) {
                unreadMessages.add(c);
            }

        }

        return unreadMessages;
    }

    public Contact getContact(int id) {

        return dao.getOne(id);
    }

    public Contact getContactByEmail(String email) {

        return dao.findByEmail(email);
    }

}
