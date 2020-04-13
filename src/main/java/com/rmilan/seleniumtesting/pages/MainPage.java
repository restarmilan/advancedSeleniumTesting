package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class MainPage extends BasePage {

    @FindBy(xpath = "//*[(text()='All Examples')]")
    WebElement menuListTop;
    @FindBy(linkText = "Input Forms")
    WebElement inputForms;
    @FindBy(linkText = "Simple Form Demo")
    WebElement simpleFormDemoSelector;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void navigationUsingExampleSelector() {
        navigateTo();
        clickOnWebElement(menuListTop);
        clickOnWebElement(inputForms);
        clickOnWebElement(simpleFormDemoSelector);
    }
}
