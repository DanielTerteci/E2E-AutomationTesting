package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {
    @FindBy(css = ".login-wrapper")
    private WebElement loginContainer;
    @FindBy(id = "userName")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login")
    private WebElement loginButton;
    @FindBy(id = "newUser")
    private WebElement newUserButton;

    public void enterCredentials(final String username, final String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public void enterNewAccount(final String usernameNewAccount, final String passwordNewAccount) {
        usernameInput.sendKeys(usernameNewAccount);
        passwordInput.sendKeys(passwordNewAccount);
    }

    public void clickLoginButton() {
        clickButton(loginButton);
    }

    public boolean isLoginPageDisplayed() {
        try {
            return isDisplayed(loginContainer);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickNewUserButton() {
        clickButton(newUserButton);
    }
}
