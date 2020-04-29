package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JQueryDualListBoxDemoPage extends BasePage {

    private final int ALL_PICKS = 15;
    @FindBy(xpath = "//select[@class='form-control pickListSelect pickData']")
    WebElement pickList;
    @FindBy(xpath = "//select[@class='form-control pickListSelect pickListResult']")
    WebElement pickResultList;
    @FindBy(xpath = "//select[@class='form-control pickListSelect pickListResult']/child::option")
    List<WebElement> selectedPicks;
    @FindBy(xpath = "//button[text()='Add']")
    WebElement addSelectedPicksButton;
    @FindBy(xpath = "//button[text()='Add All']")
    WebElement addAllPicksButton;
    @FindBy(xpath = "//button[text()='Remove']")
    WebElement removePicksFromPickResultListButton;
    @FindBy(xpath = "//button[text()='Remove All']")
    WebElement removeAllPicksFromPickResultListButton;

    public JQueryDualListBoxDemoPage(WebDriver driver) {
        super(driver);
    }

    public void selectMultipleItemsFromPickList(List<String> options) {
        navigateTo(baseUrl + "/jquery-dual-list-box-demo.html");
        selectMultipleItemsFromList(options, pickList);
    }

    public void selectMultipleItemsFromPickResultList(List<String> options) {
        selectMultipleItemsFromList(options, pickResultList);
    }

    public void addSelectedItemsToResultList() {
        clickOnWebElement(addSelectedPicksButton);
    }

    public void addAllPicksToPickResultList() {
        navigateTo(baseUrl + "/jquery-dual-list-box-demo.html");
        clickOnWebElement(addAllPicksButton);
    }

    public void removeSelectedPicksFromPickResultList() {
        clickOnWebElement(removePicksFromPickResultListButton);
    }

    public void removeAllPicksFromPickResultList() {
        clickOnWebElement(removeAllPicksFromPickResultListButton);
    }

    public int getNumberOfSelectablePickOptions() {
        Select selectablePicks = new Select(pickList);
        return selectablePicks.getOptions().size();
    }

    public int getNumberOfPicksInPickResultList() {
        Select resultList = new Select(pickResultList);
        return resultList.getOptions().size();
    }

    public List<String> getPickResultListOptions() {
        List<String> picks = new ArrayList<>();
        for (WebElement selectedPick : selectedPicks) {
            picks.add(selectedPick.getText());
        }
        return picks;
    }

    public int getAllPicksBeforeSelectAny() {
        return ALL_PICKS;
    }
}
