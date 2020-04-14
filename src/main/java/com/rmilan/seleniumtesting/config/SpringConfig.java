package com.rmilan.seleniumtesting.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = "com.rmilan.seleniumtesting.pages")
public class SpringConfig {

    @Value("${chromeDriverPath}")
    private String chromeDriverPath;

    @Value("${logsDir}")
    private String logsDir;

    @Bean(name = "webdriver", destroyMethod = "quit")
    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //Create non-verbose chrome driver.log logfile after each run (rewrites previous one); it displays [INFO] level logs
        System.setProperty("webdriver.chrome.logfile", logsDir);
        //uncomment it for verbose logging
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
