package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class JSAlertDemoPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement alertConfirmBoxBtn;
    @FindBy(xpath = "//p[@id='confirm-demo']")
    WebElement alertStatus;

    public JSAlertDemoPage(WebDriver driver) {
        super(driver);
    }

    public void openAlert(WebElement button) {
        navigateTo(baseUrl+"/javascript-alert-box-demo.html");
        clickOnWebElement(button);
    }

    public void confirmAlert(){
        openAlert(alertConfirmBoxBtn);
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        openAlert(alertConfirmBoxBtn);
        driver.switchTo().alert().dismiss();
    }

    public String getAlertBoxStatus() {
        return alertStatus.getText();
    }


}
