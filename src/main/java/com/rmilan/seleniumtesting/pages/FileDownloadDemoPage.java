package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class FileDownloadDemoPage extends BasePage {

    @FindBy(xpath = "//button[@id='create']")
    WebElement createFileBtn;

    @FindBy(xpath = "//textarea[@id='textbox']")
    WebElement dataTextArea;

    public FileDownloadDemoPage(WebDriver driver) {
        super(driver);
    }

    public void setInputToTextArea(String input) {
        navigateTo(baseUrl+"/generate-file-to-download-demo.html");
        setElementInput(dataTextArea, input);
    }

    public boolean isFileGeneratingButtonEnableToUse() {
        return createFileBtn.isEnabled();
    }


}
