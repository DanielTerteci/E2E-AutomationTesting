package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegisterPage extends BasePage {
    @FindBy(id = "firstname")
    private WebElement firstNameField;
    @FindBy(id = "lastname")
    private WebElement lastNameField;
    @FindBy(id = "userName")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "register")
    private WebElement registerButton;
    @FindBy(id = "gotologin")
    private WebElement backToLoginPage;

    @Value("${firstname.field}")
    private String firstNameInput;
    @Value("${lastname.field}")
    private String lastNameInput;
    @Value("${username.field}")
    private String usernameInput;
    @Value("${password.field}")
    private String passwordInput;

    public void completeFields() {
        firstNameField.sendKeys(firstNameInput);
        lastNameField.sendKeys(lastNameInput);
        usernameField.sendKeys(usernameInput);
        passwordField.sendKeys(passwordInput);
    }

    public void clickRegisterButton() {
        clickButton(registerButton);
    }

    public void goBackToLoginPage() {
        clickButton(backToLoginPage);
    }

}
