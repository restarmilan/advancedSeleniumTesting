package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.DragAndDropDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
class DragAndDropDemoPageTest {

    @Autowired
    DragAndDropDemoPage dragAndDropDemoPage;



    @Test
    @DisplayName("TC-ST-DAD-01 - Check initial number of draggable items")
    void checkInitialDraggableItems() {
        dragAndDropDemoPage.navigateToDragAndDropPage();
        assertEquals(4, dragAndDropDemoPage.getDraggableItems());
    }

    @Test
    @DisplayName("TC-ST-DAD-02 - Check initial number of dropped items")
    void checkInitialDroppedItems() {
        dragAndDropDemoPage.navigateToDragAndDropPage();
        assertEquals(0, dragAndDropDemoPage.getDroppedItems());
    }

    @Test
    void checkDroppingPlaceVisibility() {
        dragAndDropDemoPage.navigateToDragAndDropPage();
        assertTrue(dragAndDropDemoPage.isDroppingPlaceVisible());
    }

    @Test
    void dragAnItem() {
        dragAndDropDemoPage.navigateToDragAndDropPage();
        dragAndDropDemoPage.dragAndDrop();
        assertEquals(0, dragAndDropDemoPage.getDraggableItems());
        assertEquals(4, dragAndDropDemoPage.getDroppedItems());
    }

    //TODO: make this test case pass, find a proper drag&drop selenium solution which works with chromedriver

}