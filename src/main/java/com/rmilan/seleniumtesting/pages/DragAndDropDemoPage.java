package com.rmilan.seleniumtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DragAndDropDemoPage extends BasePage {

    @FindBy(xpath = "//div[@id='todrag']/child::span")
    List<WebElement> draggableItems;
    @FindBy(xpath = "//div[@id='todrag']/child::span[1]")
    WebElement firstdraggableItem;
    @FindBy(xpath = "//div[@id='mydropzone']")
    WebElement dropZone;
    @FindBy(xpath = "//div[@id='droppedlist']/child::span")
    List<WebElement> droppedItems;

    public DragAndDropDemoPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToDragAndDropPage() {
        navigateTo(baseUrl + "/drag-and-drop-demo.html");
        //driver.manage().window().maximize();
    }

    public int getDraggableItems() {
        return draggableItems.size();
    }

    public int getDroppedItems() {
        return droppedItems.size();
    }

    public boolean isDroppingPlaceVisible() {
        return dropZone.isDisplayed();
    }

    public void dragAndDrop() {
        Actions actions = new Actions(driver);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropZone);
        for (int i = 0; i < draggableItems.size(); i++) {
           actions.dragAndDrop(draggableItems.get(i), dropZone).build().perform();
            int finalI = i;
            wait.until((ExpectedCondition<Boolean>) driver -> droppedItems.size() == finalI + 1);
        }
    }
}

//TODO: no solution worked for selenium drag&drop, find some workaround which works with chromedriver
