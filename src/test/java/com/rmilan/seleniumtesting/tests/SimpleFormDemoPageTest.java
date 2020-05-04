package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.SimpleFormDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
class SimpleFormDemoPageTest {

    @Autowired
    private SimpleFormDemoPage simpleFormDemoPage;

    @DisplayName("TC-ST-SFD-01 - Check simple input field expected messages")
    @ParameterizedRepeatedIfExceptionsTest(name = "TC-ST-SFD-01 - Check simple input field expected messages with {0} value.",
            repeatedName = " (Repeat {currentRepetition} of {totalRepetitions})",
            repeats = 3, minSuccess = 2)
    @CsvFileSource(resources = "/simple_input_message.csv", numLinesToSkip = 1)
    void checkSimpleInputField(String input, String expected) {
        simpleFormDemoPage.setSingleInputField(input);
        assertEquals(expected, simpleFormDemoPage.getUserInputFromSingleInputField());
    }

    @DisplayName("TC-ST-SFD-02 - Check double input fields expected added result")
    @ParameterizedTest(name = "TC-ST-SFD-02 - Check double input fields expected added result with {0} and {1} values.")
    @CsvFileSource(resources = "/double_input_field_test_data.csv", numLinesToSkip = 1)
    void checkAddedUserInputs(String value1, String value2, String expected) {
        simpleFormDemoPage.setDoubleInputField(value1, value2);
        assertEquals(expected, simpleFormDemoPage.getAddedValuesFromDoubleInputField());
    }
}