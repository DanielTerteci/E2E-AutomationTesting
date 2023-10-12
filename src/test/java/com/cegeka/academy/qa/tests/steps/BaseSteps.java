package com.cegeka.academy.qa.tests.steps;

import com.cegeka.academy.qa.configurations.FrameworkConfiguration;
import com.cegeka.academy.qa.pageobjects.BookStorePage;
import com.cegeka.academy.qa.pageobjects.LoginPage;
import com.cegeka.academy.qa.pageobjects.ProfilePage;
import com.cegeka.academy.qa.pageobjects.RegisterPage;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = FrameworkConfiguration.class)
public class BaseSteps {
    @Autowired
    protected BookStorePage bookStorePage;
    @Autowired
    protected LoginPage loginPage;
    @Autowired
    protected ProfilePage profilePage;
    @Autowired
    protected RegisterPage registerPage;
}
