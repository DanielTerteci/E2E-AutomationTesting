package com.cegeka.academy.qa.pageobjects;

import com.cegeka.academy.qa.models.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class ProfilePage extends BasePage {
    @FindBy(css = ".profile-wrapper")
    private WebElement profilePage;
    @FindBy(id = "gotoStore")
    private WebElement goToStoreButton;
    @FindBy(css = ".element-group:last-of-type li:nth-child(3)")
    private WebElement profileButton;
    @FindBy(id = "delete-record-undefined")
    private WebElement deleteBookButton;
    @FindBy(id = "closeSmallModal-ok")
    private WebElement confirmDeleteButton;
    @FindBy(css = ".buttonWrap .text-center")
    private WebElement deleteAccountButton;
    @FindBy(css = ".-next")
    private WebElement nextButton;
    @FindBy(css = ".-previous")
    private WebElement previousButton;
    @FindBy(css = ".buttonWrap .text-right")
    private WebElement deleteAllBooksButton;
    @FindBy(css = "#submit.btn.btn-primary")
    private WebElement logoutButton;


    public boolean isProfilePageDisplayed() {
        try {
            return isDisplayed(profilePage);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickGoToBookStore() {
        try {
            clickButton(goToStoreButton);
        } catch (ElementClickInterceptedException e) {
            scrollIgnoreFooter(0, goToStoreButton);
        }
    }

    public void clickProfileButton() {
        try {
            isDisplayed(profileButton);
            clickButton(profileButton);
        } catch (ElementClickInterceptedException e) {
            scrollIgnoreFooter(HEIGHT_DELTA, profileButton);
        }
    }

    public Book getBookDetails(final String bookTitle) {
        var bookWebElement = getBookElementByTitle(bookTitle);
        var titleElement = bookWebElement.getText().split("\n")[0];
        var authorElement = bookWebElement.getText().split("\n")[1];
        var publisherElement = bookWebElement.getText().split("\n")[2];
        var book = new Book(titleElement);
        book.setAuthor(authorElement);
        book.setPublisher(publisherElement);

        return book;
    }

    public WebElement getBookElementByTitle(final String bookTitle) {
        var bookWebElementList = driver.findElements(By.cssSelector(".rt-tr-group"));

        try {
            return bookWebElementList.stream().filter(book -> bookTitle.equals(book.getText().split("\n")[0])).findFirst().orElseThrow();
        } catch (NoSuchElementException e) {
            throw new TimeoutException(e);
        }
    }

    public void deleteButton() {
        clickButton(deleteBookButton);
    }

    public void confirmDeleteButton() {
        clickButton(confirmDeleteButton);
    }


    public void deleteAccountButton() {
        try {
            clickButton(deleteAccountButton);
        } catch (ElementClickInterceptedException e) {
            scrollIgnoreFooter(HEIGHT_DELTA, deleteAccountButton);
        }
    }

    public void clickNextButton() {
        try {
            clickButton(nextButton);
        } catch (ElementClickInterceptedException ex) {
            scrollIgnoreFooter(HEIGHT_DELTA, nextButton);
        }
    }

    public void clickPreviousButton() {
        try {
            clickButton(previousButton);
        } catch (ElementClickInterceptedException ex) {
            scrollIgnoreFooter(HEIGHT_DELTA, previousButton);
        }
    }

    public void deleteAllBooks() {
        try {
            clickButton(deleteAllBooksButton);
            handleAlert();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            scrollIgnoreFooter(HEIGHT_DELTA, deleteBookButton);
            handleAlert();
        }
    }

    public void logout() {
        try {
            clickButton(logoutButton);
        } catch (ElementClickInterceptedException e) {
            scrollIgnoreFooter(HEIGHT_DELTA, logoutButton);
        }
    }
}
