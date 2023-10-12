package com.cegeka.academy.qa.configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.cegeka.academy.qa")
@PropertySource(value = {"classpath:framework.properties", "classpath:inputFields.properties", "classpath:screenshots.properties"})

public class FrameworkConfiguration {
    private WebDriver driver;
    @Value("${browser.type}")
    private String browser;

    @Bean
    public DataSource getUsersDataSource() {
        var url = "jdbc:mysql://localhost:3306/user_db";
        var username = "root";
        var password = "mysql123";

        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    public JdbcTemplate getUserJdbcTemplate(@Autowired DataSource usersDataSource) {
        return new JdbcTemplate(usersDataSource);
    }

    @Bean
    @Profile("UI")
    WebDriver getDriver() {
        switch (browser) {
            case "chrome" -> driver = openChrome();
            case "edge" -> driver = openEdge();
            case "firefox" -> {
                driver = openFirefox();
                driver.manage().window().maximize();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }

    private ChromeDriver openChrome() {
        System.setProperty("webdriver.chrome.driver", "D:\\Setup\\chromedrivers\\chromedriver113.exe");
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--start-maximized");
        return new ChromeDriver(chromeOptions);
    }

    private EdgeDriver openEdge() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Daniel\\msedgedriver.exe");
        var edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("--start-maximized");
        return new EdgeDriver(edgeOptions);
    }

    private FirefoxDriver openFirefox() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Daniel\\firefoxdriver.exe");
        var firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions);
    }

    @PreDestroy
    public void destroy() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit was called");
        } else {
            System.out.println("Cannot quit driver because it's already null");
        }
    }
}
