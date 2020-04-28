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

    /*@Test
    @DisplayName("TC-ST-DAD-03 - Check drag and drop function")
    void dragAndDropAllItems() {
        dragAndDropDemoPage.navigateToDragAndDropPage();
        dragAndDropDemoPage.dragAndDrop();
        assertEquals(0, dragAndDropDemoPage.getDraggableItems());
        System.out.println("TC-ST-DAD-03 - All items has dragged");
        assertEquals(4, dragAndDropDemoPage.getDroppedItems());
        System.out.println("TC-ST-DAD-03 - All items has dropped");
    }*/

    //TODO: make this test case pass, find a proper drag&drop selenium solution which works with chromedriver

}