package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class SimpleFormDemoPage extends BasePage {

    @FindBy(xpath = "//input[@id='user-message']")
    WebElement userInput;
    @FindBy(xpath = "//button[text()='Show Message']")
    WebElement showMessageBtn;
    @FindBy(xpath = "//span[@id='display']")
    WebElement showUserInput;
    @FindBy(xpath = "//input[@id='sum1']")
    WebElement value1;
    @FindBy(xpath = "//input[@id='sum2']")
    WebElement value2;
    @FindBy(xpath = "//button[text()='Get Total']")
    WebElement getTotalButton;
    @FindBy(xpath = "//span[@id='displayvalue']")
    WebElement addedValues;

    public SimpleFormDemoPage(WebDriver driver) {
        super(driver);
    }

    public void setSingleInputField(String input) {
        navigateTo(baseUrl+"/basic-first-form-demo.html");
        setElementInput(userInput, input);
        clickOnWebElement(showMessageBtn);
    }

    public String getUserInputFromSingleInputField() {
        return getElementInnerText(showUserInput);
    }
}
