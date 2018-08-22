package com.mycompany.service;

import com.mycompany.domain.Contact;
import java.util.List;

/**
 * The interface specifies all biz operation for contact entity.
 * @author  Suruchi
 */
public interface ContactService {
    
    public void save(Contact c);
    public void update(Contact c);
    public void delete(Integer contactId);
    public void delete(Integer[] contactIds);
    public Contact findById(Integer contactId);
    /**
     * This method returns all user contact (user who is logged in).
     * @param userId
     * @return 
     */
    public List<Contact> findUserContact(Integer userId);
    /**
     * The method search contact for user(userId) based on given free-text-criteria(txt).
     * @param userId User who is logged in
     * @param txt criteria used to search free-text search criteria
     * @return 
     */
    public List<Contact> findUserContact(Integer userId,String txt);
    
}
