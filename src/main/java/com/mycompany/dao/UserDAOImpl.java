package com.mycompany.dao;

import com.mycompany.domain.User;
import com.mycompany.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO{

    @Override
    public void save(User u) {
    String sql = "INSERT INTO user (Name, Email, Address, userName, password, Status, Role, Phone)  VALUES (:name, :email, :address, :un, :password, :status, :role, :phone)";  
    Map m = new HashMap();
    m.put("name",u.getName());
    m.put("email",u.getEmail());
    m.put("address",u.getAddress());
    m.put("un",u.getUsername());
    m.put("password",u.getPassword());
    m.put("status",u.getStatus());
    m.put("role",u.getRole());
    m.put("phone",u.getPhone());
    KeyHolder kh = new GeneratedKeyHolder();
    SqlParameterSource ps = new MapSqlParameterSource(m);
    getNamedParameterJdbcTemplate().update(sql, ps, kh);
    Integer userId = kh.getKey().intValue();
    u.setUserId(userId);
    }

    @Override
    public void update(User u) {
        
     String sql ="UPDATE user SET"
    +"Name=: name"
    +"Email=: email"
    +"Address=: address"
    +"userName=: un"
    +"password=: password"
    +"Status=: satatus"
    +"Role=: role"
    +"Phone=: phone"
    +"WHERE UserId= userId";
   Map m = new HashMap();
   m.put("name",u.getName());
    m.put("email",u.getEmail());
    m.put("address",u.getAddress());
    m.put("un",u.getUsername());
    m.put("status",u.getStatus());
    m.put("role",u.getRole());
    m.put("phone",u.getPhone());
    m.put("userId",u.getUserId());
    getNamedParameterJdbcTemplate().update(sql,m);
    }

    @Override
    public void delete(User u) {
    this.delete(u.getUserId());
    }

    @Override
    public void delete(Integer userId) {
    String sql ="DELETE FROM user WHERE UserId=?";
    getJdbcTemplate().update(sql,userId);   
    }

    @Override
    public User findById(Integer userId) {
       String sql = "SELECT UserId, Name, Email, Address, userName, password,  Status,  Role, Phone FROM user WHERE UserId=?";
     User u = getJdbcTemplate().queryForObject(sql,new UserRowMapper());
       return u;
    }

    @Override
    public List<User> findAll() {
       String sql = "SELECT UserId, Name, Email, Address, userName, password, Status, Role, Phone FROM user";
        return getJdbcTemplate().query(sql,new UserRowMapper());   
    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
       String sql = "SELECT UserId, Name, Email, Address, userName, password, Status, Role, Phone FROM user WHERE "+propName+"=?";
        return getJdbcTemplate().query(sql,new UserRowMapper(),propValue); 
    }
    
}
