package com.mycompany.service;

import com.mycompany.dao.BaseDAO;
import com.mycompany.dao.ContactDAO;
import com.mycompany.domain.Contact;
import com.mycompany.rm.ContactRowMapper;
import com.mycompany.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends BaseDAO implements ContactService{

    
   @Autowired   
   private ContactDAO contactDAO;
   
    @Override
    public void save(Contact c) {
        contactDAO.save(c);
        }

    @Override
    public void update(Contact c) {
        contactDAO.update(c);
        }

    @Override
    public void delete(Integer contactId) {
        contactDAO.delete(contactId);
       }

    @Override
    public void delete(Integer[] contactIds) {
        String ids = StringUtil.toCommaSeparationString(contactIds);
        String sql = "DELETE FROM contact WHERE ContactId IN ("+ids+")";
        getJdbcTemplate().update(sql);
        }

    @Override
    public List<Contact> findUserContact(Integer userId) {
       return contactDAO.findByProperty("userId",userId);
       }

    @Override
    public List<Contact> findUserContact(Integer userId, String txt) {
       String sql = "SELECT ContactId, UserId, Name, Phone, Email, Address, Remark FROM contact WHERE UserId=? AND (Name LIKE '%"+txt+"%' OR Email LIKE '%"+txt+"%' OR Address LIKE '%"+txt+"%' OR Remark LIKE '%"+txt+"%')";
       return getJdbcTemplate().query(sql,new ContactRowMapper(),userId);
       }

    @Override
    public Contact findById(Integer contactId) {
       return contactDAO.findById(contactId);
       }
    
}
