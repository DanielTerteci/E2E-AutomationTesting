package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Component
public class BasePage {
    public static final int HEIGHT_DELTA = 180;
    @Value("${base.url:}")
    private String baseUrl;
    @Autowired(required = false)
    protected WebDriver driver;

    FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(WebDriverException.class);
    }

    public void waitInMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void handleAlert() {
        try {
            var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            var alert = wait.until(ExpectedConditions.alertIsPresent());
            var alertText = alert.getText();
            System.out.println("Alert is: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException ex) {
            System.out.println("The alert is not found");
        } catch (UnhandledAlertException ex2) {
            driver.switchTo().alert().accept();
        }
    }

    public void clickButton(final WebElement webElement) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public boolean isDisplayed(final WebElement webElement) {
        return getFluentWait().until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void scrollIgnoreFooter(final int heightDelta, final WebElement webElement) {
        var footer = By.tagName("footer");
        var footerHeight = driver.findElement(footer).getRect().getDimension().getHeight();
        new Actions(driver).scrollByAmount(0, footerHeight + heightDelta).perform();
        waitInMillis(500);
        webElement.click();
    }

    public void startPage() {
        driver.get(baseUrl);
    }

    @PostConstruct
    public void initialize() {
        PageFactory.initElements(driver, this);
    }

}
