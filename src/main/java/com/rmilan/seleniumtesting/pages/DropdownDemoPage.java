package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

@Component
public class DropdownDemoPage extends BasePage {

    @FindBy(xpath = "//select[@id='select-demo']")
    WebElement dropdown;
    @FindBy(xpath = "//p[@class='selected-value']")
    WebElement selectedDay;

    public DropdownDemoPage(WebDriver driver) {
        super(driver);
    }

    public void selectADayFromSimpleDropdown(String option) {
        navigateTo(baseUrl+"/basic-select-dropdown-demo.html");
        Select daySelect = new Select(dropdown);
        daySelect.selectByValue(option);
    }

    public String getSelectedDay() {
        return getElementInnerText(selectedDay);
    }
}
