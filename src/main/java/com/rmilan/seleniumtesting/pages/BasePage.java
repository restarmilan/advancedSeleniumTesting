package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class BasePage {

    final int TIMEOUT = 10;
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.seleniumeasy.com/test";
    private final static Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, TIMEOUT);
        PageFactory.initElements(factory, this);
    }

    public void navigateTo() {
        driver.get(baseUrl);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickOnWebElement(WebElement element) {
        element.click();
    }

    public void setElementInput(WebElement element, String input) {
        element.sendKeys(input);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean elementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public String getElementInnerText(WebElement element) {
        return element.getText();
    }

    public void selectMultipleItemsFromList(List<String> options, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Actions action = new Actions(driver);
        for (String option : options) {
            try {
                WebElement select = driver.findElement(By.xpath("//option[text()='" + option + "']"));
                action.keyDown(Keys.CONTROL).click(select).build().perform();
            } catch (NoSuchElementException e) {
                logger.error("{} : There is no such option in the dropdown: {}", this.getClass(), option);
                e.printStackTrace();
            }
        }

    }
}
