package com.mycompany.rm;

import com.mycompany.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
       User u = new User();
       u.setUserId(rs.getInt("userId"));
       u.setName(rs.getString("name"));
       u.setEmail(rs.getString("email"));
       u.setAddress(rs.getString("address"));
       u.setUsername(rs.getString("username"));
       u.setRole(rs.getInt("role"));
       u.setStatus(rs.getInt("status"));
       u.setPhone(rs.getString("phone"));
        return u;
    }
    
}
