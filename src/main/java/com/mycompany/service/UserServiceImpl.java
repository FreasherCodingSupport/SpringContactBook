package com.mycompany.service;

import com.mycompany.dao.BaseDAO;
import com.mycompany.dao.UserDAO;
import com.mycompany.domain.User;
import com.mycompany.exception.UserBlockedException;
import com.mycompany.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends BaseDAO implements UserService {
   
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public void register(User u) {
        userDAO.save(u);
        }

    @Override
    public User login(String username, String password) throws UserBlockedException {
        String sql = "SELECT UserId, Name, Phone, Email, Address, Role, Status, userName FROM user WHERE userName=:un AND password=:pw";
        Map m = new HashMap();
        m.put("un",username);
        m.put("pw",password);
        try{
        User u = getNamedParameterJdbcTemplate().queryForObject(sql,m,new UserRowMapper());
        if(u.getStatus().equals(UserService.LOGIN_BLOCKED)){
            throw new UserBlockedException("Your account has been blocked! Contact to Admin");
        }else{
        return u;
        }
        }catch(EmptyResultDataAccessException ex){
          return null;  
        }
       }

    @Override
    public List<User> getUserList() {
        return userDAO.findByProperty("role",UserService.ROLE_USER);
     }

    @Override
    public void changeLoginStatus(Integer userId, Integer status) {
       String sql = "UPDATE user SET Status=:1st WHERE UserId=:uid"; 
       Map m = new HashMap();
       m.put("uid",userId);
       m.put("1st",status);
      getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public Boolean isUserNameExist(String username) {
        String sql = "SELECT count (userName) FROM user WHERE userName=?";
        Integer count = getJdbcTemplate().queryForObject(sql, new String[]{username},Integer.class);
        if(count==1){
            return true;
        }else{
            return false;  
        }
    }

    
}
