package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    @FindBy(xpath = "//a[@id='example_next']")
    WebElement paginationButtonNext;
    @FindBy(xpath = "//a[@id='example_previous']")
    WebElement paginationButtonPrev;

    private List<String> paginationInfos = new ArrayList<>();

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

    public void tablePaginationUsingPageIndex(String pageNumber) {
        navigateTo(baseUrl + "/table-sort-search-demo.html");
        WebElement page = driver.findElement(By.xpath("//a[@data-dt-idx='" + pageNumber + "']"));
        clickOnWebElement(page);
    }

    public void tablePaginationUsingDirectionButtons() {
        navigateTo(baseUrl + "/table-sort-search-demo.html");
        while (!paginationButtonNext.getAttribute("class").equals("paginate_button next disabled")) {
            clickOnWebElement(paginationButtonNext);
            paginationInfos.add(gettableEntriesInfo());
        }
        while (!paginationButtonPrev.getAttribute("class").equals("paginate_button previous disabled")) {
            clickOnWebElement(paginationButtonPrev);
            paginationInfos.add(gettableEntriesInfo());
        }
    }

    public void tableSearch(String searchKey) {
        navigateTo(baseUrl + "/table-sort-search-demo.html");
        setElementInput(tableSearchBar, searchKey);
    }

    public void tableSort(String order, String header) {
        navigateTo(baseUrl + "/table-sort-search-demo.html");
        String th = header.substring(0, 1).toUpperCase() + header.substring(1);
        try {
            WebElement tableHeader = driver.findElement(By.xpath("//th[contains(text(),'" + th + "')]"));
            clickOnWebElement(tableHeader);
            while (!tableHeader.getAttribute("aria-sort").equals(order)) {
                clickOnWebElement(tableHeader);
            }
        } catch (NoSuchElementException e) {
            System.out.println("There is no such column to sort in the table");
            e.printStackTrace();
        }
    }

    public int getNumberOfTableRows() {
        return tableRows.size();
    }

    public String gettableEntriesInfo() {
        return tableEntryInfo.getText();
    }

    public List<String> getPaginationInfos() {
        return paginationInfos;
    }

    public String getFirstTableRowData() {
        return tableRows.get(0).getText();
    }
}
