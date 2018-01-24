package io.srqsoftware;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int idx) throws SQLException {
        User u = new User();
        u.setUserName(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        return u;
    }
}
