package com.cegeka.academy.qa.database.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setUserName(rs.getString("USER_NAME"));
        user.setPassword(rs.getString("USER_PASSWORD"));
        user.setUserId(rs.getString("USER_ID"));

        return user;
    }
}
