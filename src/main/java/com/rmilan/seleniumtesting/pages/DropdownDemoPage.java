package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DropdownDemoPage extends BasePage {

    @FindBy(xpath = "//select[@id='select-demo']")
    WebElement dropdown;
    @FindBy(xpath = "//p[@class='selected-value']")
    WebElement selectedDay;
    @FindBy(xpath = "//select[@id='multi-select']")
    WebElement multiSelector;
    @FindBy(xpath = "//button[@id='printMe']")
    WebElement firstSelected;
    @FindBy(xpath = "//button[@id='printAll']")
    WebElement allSelected;
    @FindBy(xpath = "//p[@class='getall-selected']")
    WebElement selectedOptions;


    public DropdownDemoPage(WebDriver driver) {
        super(driver);
    }

    public void selectADayFromSimpleDropdown(String option) {
        navigateTo(baseUrl + "/basic-select-dropdown-demo.html");
        Select daySelect = new Select(dropdown);
        daySelect.selectByValue(option);
    }

    public void selectMultipleItems(List<String> options) {
        navigateTo(baseUrl + "/basic-select-dropdown-demo.html");
        selectMultipleItemsFromList(options, multiSelector);
    }

    public String getSelectedDay() {
        return getElementInnerText(selectedDay);
    }

    public String getFirstSelectedOption() {
        clickOnWebElement(firstSelected);
        return selectedOptions.getText();
    }

    public String getAllSelected() {
        clickOnWebElement(allSelected);
        return getElementInnerText(selectedOptions);

    }
}
