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
    @FindBy(xpath = "//select[@class='form-control pickListSelect pickListResult']/child::option")
    List<WebElement> selectedPicks;
    @FindBy(xpath = "//button[text()='Add']")
    WebElement addSelectedPicksButton;

    public JQueryDualListBoxDemoPage(WebDriver driver) {
        super(driver);
    }

    public void selectMultipleItems(List<String> options) {
        navigateTo(baseUrl + "/jquery-dual-list-box-demo.html");
        selectMultipleItemsFromList(options, pickList);
    }

    public void addSelectedItemsToResultList() {
        clickOnWebElement(addSelectedPicksButton);
    }

    public int getNumberOfSelectablePickOptions() {
        Select selectablePicks = new Select(pickList);
        return selectablePicks.getOptions().size();
    }

    public int getNumberOfPickResultListOptions() {
        return selectedPicks.size();
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
