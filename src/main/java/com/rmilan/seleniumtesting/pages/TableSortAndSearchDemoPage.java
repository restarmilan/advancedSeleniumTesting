package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableSortAndSearchDemoPage extends BasePage {

    @FindBy(xpath = "//select[@name='example_length']")
    WebElement tableEntrySelector;
    @FindBy(xpath = "//div[@id='example_info']")
    WebElement tableEntryInfo;
    @FindBy(xpath = "//table[@id='example']/tbody/tr")
    List<WebElement> tableRows;
    @FindBy(xpath = "//input[@type='search']")
    WebElement tableSearchBar;

    public TableSortAndSearchDemoPage(WebDriver driver) {
        super(driver);
    }

    public void selectNumberOfTableEntries(String option) {
        navigateTo(baseUrl + "/table-sort-search-demo.html");
        Select entriesSelect = new Select(tableEntrySelector);
        try {
            entriesSelect.selectByValue(option);
        } catch (NoSuchElementException e) {
            System.out.println("There is no such option in the table entry selector: " + option);
            e.printStackTrace();
        }
    }

    public int getNumberOfTableRows() {
        return tableRows.size();
    }

    public String gettableEntriesInfo() {
        return tableEntryInfo.getText();
    }
}
