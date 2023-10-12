package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class BookStorePage extends BasePage {
    @FindBy(id = "login")
    private WebElement loginButton;
    @FindBy(css = ".books-wrapper")
    private WebElement bookStorePage;
    @FindBy(id = "see-book-Git Pocket Guide")
    private WebElement gitPocketGuideBook;
    @FindBy(id = "see-book-Learning JavaScript Design Patterns")
    private WebElement learningJsBook;
    @FindBy(css = ".fullButtonWrap .text-right")
    private WebElement addToCollectionButton;
    @FindBy(id = "addNewRecordButton")
    private WebElement backToStoreButton;

    public void clickLoginButton() {
        clickButton(loginButton);
    }

    public boolean isBookStorePageDisplayed() {
        try {
            return isDisplayed(bookStorePage);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickGitPocketGuideBook() {
        clickButton(gitPocketGuideBook);
    }

    public void addBookToCollection() {
        try {
            isDisplayed(addToCollectionButton);
            clickButton(addToCollectionButton);
        } catch (ElementClickInterceptedException e) {
            scrollIgnoreFooter(30, addToCollectionButton);
        }
    }

    public void backToStore() {
        clickButton(backToStoreButton);
    }

    public void clickLearningJsBook() {
        clickButton(learningJsBook);
    }

    public boolean isAddToCollectionButtonDisplayed() {
        try {
            return isDisplayed(addToCollectionButton);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void addAllBooksToCollection() {
        var selector = By.cssSelector(".rt-tr-group span.mr-2");
        getFluentWait().until(ExpectedConditions.elementToBeClickable(selector));
        var bookIdList = driver.findElements(selector).stream()
                .map(book -> book.getAttribute("id"))
                .toList();
        for (var bookId : bookIdList) {
            addBookById(bookId);
        }
    }

    private void addBookById(final String bookId) {
        try {
            driver.findElement(By.id(bookId)).click();
        } catch (ElementClickInterceptedException | NoSuchElementException e) {
            scrollIgnoreFooter(HEIGHT_DELTA, driver.findElement(By.id(bookId)));
        }
        addBookToCollection();
        handleAlert();
        backToStore();
    }
}
