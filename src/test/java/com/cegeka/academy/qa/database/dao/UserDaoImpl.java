package com.cegeka.academy.qa.database.dao;

import com.cegeka.academy.qa.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate usersJdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        var sqlSelect = "select * from users";
        return usersJdbcTemplate.query(sqlSelect, (ResultSet rs, int rowNum) -> {
                    var user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setUserName(rs.getString("USER_NAME"));
                    user.setPassword(rs.getString("USER_PASSWORD"));
                    user.setUserId(rs.getString("USER_ID"));

                    return user;
                }
        );
    }
}
