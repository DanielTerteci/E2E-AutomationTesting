package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CucumberHooks {
    @Autowired(required = false)
    private WebDriver driver;

    @Value("${screenshot.path}")
    private String screenshotPath;

    @Before
    public void setupBeforeScenario() {
        System.out.println("Perform before scenario cucumber hook");
        System.out.println("Driver instance " + driver);
    }

    @After
    public void afterScenario(final Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
    }

    private void takeScreenshot(final Scenario scenario) {
        var testName = scenario.getName();
        var timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        var screenshotName = testName + "-" + timestamp + ".png";
        var destination = Paths.get(screenshotPath, screenshotName);
        try {
            Files.createDirectories(destination.getParent());
            var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), destination);
            System.out.println("Screenshot saved at: " + destination);
        } catch (IOException e) {
            System.out.println("Cannot take screenshot. " + e.getMessage());
        }
    }
}
