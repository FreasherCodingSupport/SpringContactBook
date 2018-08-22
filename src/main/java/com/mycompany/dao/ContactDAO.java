package com.mycompany.dao;

import com.mycompany.domain.Contact;
import java.util.List;

public interface ContactDAO {
    
    public void save(Contact c);
    public void update(Contact c);
    public void delete(Contact c);
    public void delete(Integer contactId);
    public Contact findById(Integer contactId);
    public List<Contact> findAll();
    public List<Contact> findByProperty(String propName,Object propValue);

}
