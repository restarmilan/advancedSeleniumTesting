package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.DropdownDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
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
public class DropdownDemoPageTest {

    @Autowired
    private DropdownDemoPage dropdownDemoPage;

    @DisplayName("TC-ST-DD-01 - Check selected value from single dropdown list")
    @ParameterizedTest(name = "TC-ST-DD-01 - Check single dropdown list with {0} value."  )
    @CsvFileSource(resources = "/dayselect.csv", numLinesToSkip = 1)
    void checkSelectedDayFromASingleDropdown(String option, String expected) {
        dropdownDemoPage.selectADayFromSimpleDropdown(option);
        assertEquals(dropdownDemoPage.getSelectedDay(), expected);
    }
}
