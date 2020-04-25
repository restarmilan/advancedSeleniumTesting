package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.FileDownloadDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
public class FileDownloadDemoPageTest {

    @Autowired
    FileDownloadDemoPage fileDownloadDemoPage;

    @Test
    @DisplayName("TC-ST-FDD-01 - Check if file generation feature is inactive without any entered data")
    void checkIfFileGenerationIsInactive() {
        fileDownloadDemoPage.setInputToTextArea("");
        assertFalse(fileDownloadDemoPage.isFileGeneratingButtonEnableToUse());
    }

    @Test
    @DisplayName("TC-ST-FDD-02 - Check if file generation feature is active with any entered data")
    void checkIfFileGenerationIsActive() {
        fileDownloadDemoPage.setInputToTextArea("a");
        assertTrue(fileDownloadDemoPage.isFileGeneratingButtonEnableToUse());
    }




}
