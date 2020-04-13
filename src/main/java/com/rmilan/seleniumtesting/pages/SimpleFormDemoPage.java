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
    WebElement setValue1Field;
    @FindBy(xpath = "//input[@id='sum2']")
    WebElement setValue2Field;
    @FindBy(xpath = "//button[text()='Get Total']")
    WebElement getTotalButton;
    @FindBy(xpath = "//span[@id='displayvalue']")
    WebElement addedValues;

    public SimpleFormDemoPage(WebDriver driver) {
        super(driver);
    }

    public void setSingleInputField(String input) {
        navigateTo(baseUrl + "/basic-first-form-demo.html");
        setElementInput(userInput, input);
        clickOnWebElement(showMessageBtn);
    }

    public String getUserInputFromSingleInputField() {
        return getElementInnerText(showUserInput);
    }

    public void setDoubleInputField (String input1, String input2) {
        navigateTo(baseUrl + "/basic-first-form-demo.html");
        setElementInput(setValue1Field, input1);
        setElementInput(setValue2Field, input2);
        clickOnWebElement(getTotalButton);
    }

    public String getAddedValuesFromDoubleInputField() {
        return getElementInnerText(addedValues);
    }

}
