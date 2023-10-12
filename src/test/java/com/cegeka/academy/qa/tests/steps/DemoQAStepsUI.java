package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;

public class DemoQAStepsUI extends BaseSteps {
    @Value("${username.key}")
    private String username;
    @Value("${password.key}")
    private String password;
    @Value("${username.field}")
    private String usernameNewAccount;
    @Value("${password.field}")
    private String passwordNewAccount;

    @Given("User navigates to Book Store page")
    public void userNavigatesToBookStorePage() {
        bookStorePage.startPage();
    }

    @When("User clicks on login button")
    public void userClicksOnLoginButton() {
        bookStorePage.clickLoginButton();
    }

    @And("Login Page is displayed")
    public void loginPageIsDisplayed() {
        Assert.assertTrue("Login Page is not displayed", loginPage.isLoginPageDisplayed());
    }

    @Then("Username and password are entered")
    public void usernameAndPasswordAreEntered() {
        loginPage.enterCredentials(username, password);
    }

    @When("Login is attempted")
    public void loginIsAttempted() {
        loginPage.clickLoginButton();
    }

    @And("Book Store page is displayed")
    public void bookstorePageIsDisplayed() {
        Assert.assertTrue("BookStore Page isn't displayed", bookStorePage.isBookStorePageDisplayed());
    }

    @When("User selects the book")
    public void userSelectsTheBook() {
        bookStorePage.clickGitPocketGuideBook();
    }

    @Then("The book is added to collection and the alert is closed")
    public void isAddedToCollection() {
        bookStorePage.addBookToCollection();
        bookStorePage.handleAlert();
    }

    @Then("User go to store")
    public void userGoesBackToStore() {
        bookStorePage.backToStore();
    }

    @When("User navigates to profile")
    public void userNavigatesToProfile() {
        profilePage.clickProfileButton();
        Assert.assertTrue("Profile Page isn't displayed", profilePage.isProfilePageDisplayed());
    }

    @Then("Book {string} is present")
    public void bookIsPresent(String expectedBook) {
        var actualBook = profilePage.getBookElementByTitle(expectedBook);
        Assert.assertEquals(expectedBook, actualBook.getText().split("\n")[0]);
    }

    @Then("Check the title,author and publisher for book")
    public void checkTheTitleAuthorAndPublisherForBook() {
        var expectedBookTitle = "Git Pocket Guide";
        var expectedBookAuthor = "Richard E. Silverman";
        var expectedBookPublisher = "O'Reilly Media";
        var book = profilePage.getBookDetails(expectedBookTitle);
        Assert.assertEquals(expectedBookTitle, book.getTitle());
        Assert.assertEquals(expectedBookAuthor, book.getAuthor());
        Assert.assertEquals(expectedBookPublisher, book.getPublisher());
    }

    @When("User wants to delete the book")
    public void userWantsToDeleteTheBook() {
        profilePage.deleteButton();
    }

    @Then("The book is deleted and the alert is closed")
    public void theBookIsDeleted() {
        profilePage.confirmDeleteButton();
        profilePage.handleAlert();
    }

    @Then("User navigates back to store")
    public void userNavigatesBackToStore() {
        profilePage.clickGoToBookStore();
        Assert.assertTrue("Book Store Page isn't displayed", bookStorePage.isBookStorePageDisplayed());
    }

    @When("User clicks on the book")
    public void userClicksTheBook() {
        bookStorePage.clickLearningJsBook();
    }

    @And("The button to add a book in collection is not displayed")
    public void cannotBeAddedToCollection() {
        Assert.assertFalse("Button is present", bookStorePage.isAddToCollectionButtonDisplayed());
    }

    @Then("User navigates back to book store")
    public void userNavigatesBackToBookStore() {
        bookStorePage.backToStore();
    }

    @When("User adds all books to collection")
    public void userAddsAllBooksToCollection() {
        bookStorePage.addAllBooksToCollection();
    }

    @When("User clicks the next button")
    public void userClicksTheNextButton() {
        profilePage.clickNextButton();
    }

    @Then("User clicks on the previous page button")
    public void userClicksOnThePreviousPageButton() {
        profilePage.clickPreviousButton();
    }

    @When("User delete all books")
    public void userDeleteAllBooks() {
        profilePage.deleteAllBooks();
        profilePage.confirmDeleteButton();
    }

    @Then("User logs out")
    public void userLogsOut() {
        profilePage.logout();
        Assert.assertTrue("Login page isn't displayed", loginPage.isLoginPageDisplayed());
    }

    @Then("User log in with new account")
    public void userLogInWithNewAccount() {
        loginPage.enterNewAccount(usernameNewAccount, passwordNewAccount);
    }

    @Then("User clicks create a new user")
    public void userClicksCreateANewUser() {
        loginPage.clickNewUserButton();
    }

    @When("User check the fields and clicks register button")
    public void userCheckTheFields() {
        registerPage.completeFields();
        registerPage.clickRegisterButton();
        registerPage.handleAlert();
    }

    @Then("User go back to login page")
    public void userGoBackToLoginPage() {
        registerPage.goBackToLoginPage();
    }

    @Then("Profile page is displayed")
    public void profilePageIsDisplayed() {
        Assert.assertTrue("Profile Page isn't displayed", profilePage.isProfilePageDisplayed());
    }

    @And("User use delete account button")
    public void userUseDeleteAccountButton() {
        profilePage.deleteAccountButton();
        profilePage.confirmDeleteButton();
        profilePage.handleAlert();
    }

}

