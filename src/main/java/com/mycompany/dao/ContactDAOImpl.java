package com.mycompany.dao;

import com.mycompany.domain.Contact;
import com.mycompany.rm.ContactRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Contact c) {
    String sql = "INSERT INTO contact(UserId, Name, Phone, Email, Address, Remark) VALUES (:userId, :name, :phone, :email, :address, :remark)"; 
    Map m = new HashMap();
    m.put("userId",c.getUserId());
    m.put("name",c.getName());
    m.put("email",c.getEmail());
    m.put("address",c.getAddress());
    m.put("phone",c.getPhone());
    m.put("remark",c.getRemark());
    SqlParameterSource ps = new MapSqlParameterSource(m);
    KeyHolder kh = new GeneratedKeyHolder();
    getNamedParameterJdbcTemplate().update(sql, ps, kh);
    c.setContactId(kh.getKey().intValue());
    }

    @Override
    public void update(Contact c) {
        String sql = "UPDATE contact SET Name=:name, Phone=:phone, Email=:email, Address=:address, Remark=:remark WHERE ContactId=:contactId"; 
    Map m = new HashMap();
    m.put("contactId",c.getContactId());
    m.put("name",c.getName());
    m.put("phone",c.getPhone());
    m.put("email",c.getEmail());
    m.put("address",c.getAddress());
    m.put("remark",c.getRemark());
    getNamedParameterJdbcTemplate().update(sql,m);
    }

    @Override
    public void delete(Contact c) {
        this.delete(c.getContactId());
    }

    @Override
    public void delete(Integer contactId) {
    String sql = "DELETE FROM contact WHERE ContactId=?"; 
    getJdbcTemplate().update(sql,contactId);
    }

    @Override
    public Contact findById(Integer contactId) {
     String sql = "SELECT ContactId, UserId, Name, Phone, Email, Address, Remark FROM contact WHERE ContactId=?";
     return getJdbcTemplate().queryForObject(sql,new ContactRowMapper(),contactId);
     }

    @Override
    public List<Contact> findAll() {
     String sql = "SELECT ContactId, UserId, Name, Phone, Email, Address, Remark FROM contact";
     return getJdbcTemplate().query(sql,new ContactRowMapper());
     }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {
     String sql = "SELECT ContactId, UserId, Name, Phone, Email, Address, Remark FROM contact WHERE "+propName+"=?";
     return getJdbcTemplate().query(sql,new ContactRowMapper(),propValue);
     }
    
}
