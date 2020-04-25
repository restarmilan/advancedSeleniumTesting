package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.JSAlertDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
public class JsAlertDemoPageTest {

    @Autowired
    JSAlertDemoPage jsAlertDemoPage;

    @Test
    @DisplayName("TC-ST-JSA-01 - Check alert confirmation")
    void acceptAlertTest() {
        jsAlertDemoPage.confirmAlert();
        assertEquals(jsAlertDemoPage.getAlertBoxStatus(), "You pressed OK!");
    }

    @Test
    @DisplayName("TC-ST-JSA-02 - Check alert dismiss")
    void dismissAlertTest() {
        jsAlertDemoPage.dismissAlert();
        assertEquals(jsAlertDemoPage.getAlertBoxStatus(), "You pressed Cancel!");
    }

    @Test
    @DisplayName("TC-ST-JSA-03 - Check prompt alert dismiss")
    void dismissPromptAlert() {
        jsAlertDemoPage.dismissPromptAlert();
        assertTrue(jsAlertDemoPage.alertMessageIsNotDisplayed());
    }


    @DisplayName("TC-ST-JSA-04 - Check accepted prompt alert message")
    @ParameterizedTest(name = "TC-ST-JSA-04 - Check accepted prompt alert message with {0} value")
    @CsvFileSource(resources = "/prompt_alert_test_data.csv", numLinesToSkip = 1)
    void checkMessageOfAcceptedPromptAlert(String input, String expected) {
        if (input == null) {
            input = " ";
        }
        jsAlertDemoPage.setInputToConfirmedPromptAlert(input);
        assertEquals(jsAlertDemoPage.getPromptAlertMessage(), expected);
    }
}
