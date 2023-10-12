package com.cegeka.academy.qa.tests.steps;

import com.cegeka.academy.qa.database.dao.UserDao;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoQAStepsSQL {
    @Autowired
    private UserDao userDao;

    @Given("All users are retrieved from db")
    public void allUsersAreRetrievedFromDb() {
        var allUsers = userDao.getAllUsers();
        System.out.println(allUsers);
    }
}
