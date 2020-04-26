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

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("TC-ST-FDD-03 - Check remaining character counter in textarea for entering data")
    void countRemainingCharacters() throws IOException {
        String input = this.textDataProvider();
        fileDownloadDemoPage.setInputToTextArea(input);
        //app's data provider textarea has 500 characters capacity
        int remainingCharacters = fileDownloadDemoPage.remainingCharacters(500);
        assertEquals(remainingCharacters+" characters remaining",
                fileDownloadDemoPage.getTextareaFeedback());
    }

    @Test
    @DisplayName("TC-ST-FDD-04 - Check if file is available for download after entering any data")
    void fileIsAvailableForDownload() throws IOException {
        String input = this.textDataProvider();
        fileDownloadDemoPage.setInputToTextArea(input);
        fileDownloadDemoPage.createFileForDownload();
        assertTrue(fileDownloadDemoPage.isAvailableForDownload());
    }

    String textDataProvider() throws IOException{
        Path filePath = FileSystems.getDefault().getPath("src/test/resources/textarea_message.txt");
        String input = new String(Files.readAllBytes(filePath));
        return input;
    }




}
