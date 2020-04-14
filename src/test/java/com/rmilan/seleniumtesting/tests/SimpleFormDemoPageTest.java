package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.SimpleFormDemoPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringConfig.class)
class SimpleFormDemoPageTest {

    @Autowired
    private SimpleFormDemoPage simpleFormDemoPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/simple_input_message.csv", numLinesToSkip = 1)
    @DisplayName("TC-ST-SFD-01 - Check simple input field expected messages")
    void checkSimpleInputField(String input, String expected) {
        simpleFormDemoPage.setSingleInputField(input);
        assertEquals(expected, simpleFormDemoPage.getUserInputFromSingleInputField());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/double_input_field_test_data.csv", numLinesToSkip = 1)
    @DisplayName("TC-ST-SFD-02 - Check double input fields expected added values")
    void checkAddedUserInputs(String value1, String value2, String expected) {
        simpleFormDemoPage.setDoubleInputField(value1, value2);
        assertEquals(expected, simpleFormDemoPage.getAddedValuesFromDoubleInputField());
    }


}