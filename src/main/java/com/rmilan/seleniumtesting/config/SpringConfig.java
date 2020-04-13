package com.rmilan.seleniumtesting.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.rmilan.seleniumtesting.pages")
public class SpringConfig {

    @Bean(name = "webdriver", destroyMethod = "quit")
    public WebDriver getDriver() {
        String chromeDriverPath = "/home/rmilan/codecool/test_automation/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
