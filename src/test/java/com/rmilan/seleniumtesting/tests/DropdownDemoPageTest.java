package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.DropdownDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
public class DropdownDemoPageTest {

    @Autowired
    private DropdownDemoPage dropdownDemoPage;

    private final static Logger logger = LoggerFactory.getLogger(DropdownDemoPageTest.class);

    static Stream<Arguments> provideTestDataForMultiSelectorListAllSelectedValue() {
        return Stream.of(
                //first three options
                Arguments.of(Arrays.asList("California", "Florida", "New Jersey"),
                        "Options selected are : California,Florida,New Jersey"),
                //options aren't in following indexes
                Arguments.of(Arrays.asList("California", "New York", "Texas"),
                        "Options selected are : California,New York,Texas"),
                Arguments.of(Arrays.asList("California", "New York"),
                        "Options selected are : California,New York"),
                Arguments.of(Arrays.asList("California"),
                        "Options selected are : California")
        );
    }

    static Stream<Arguments> provideTestDataForMultiSelectorListFirstSelectedValue() {
        return Stream.of(
                Arguments.of(Arrays.asList("California", "New York", "Texas"),
                        "First selected option is : California"),
                Arguments.of(Arrays.asList("California", "New York"),
                        "First selected option is : California"),
                Arguments.of(Arrays.asList("California"),
                        "First selected option is : California")
        );
    }

    @DisplayName("TC-ST-DD-01 - Check selected value from single dropdown list")
    @ParameterizedTest(name = "TC-ST-DD-01 - Check single dropdown list with {0} value.")
    @CsvFileSource(resources = "/dayselect.csv", numLinesToSkip = 1)
    void checkSelectedDayFromASingleDropdown(String option, String expected) {
        dropdownDemoPage.selectADayFromSimpleDropdown(option);
        assertEquals(dropdownDemoPage.getSelectedDay(), expected);
    }

    @DisplayName("TC-ST-DD-02 - Check all selected values from multi selector list")
    @ParameterizedTest(name = "TC-ST-DD-02 - Check all selected values from multi selector list with {0} value")
    @MethodSource("provideTestDataForMultiSelectorListAllSelectedValue")
    void checkAllSelectedValues(List<String> options, String expected) {
        dropdownDemoPage.selectMultipleItems(options);
        assertEquals(dropdownDemoPage.getAllSelected(), expected);
    }

    @DisplayName("TC-ST-DD-03 - Check first selected value from multi selector list")
    @ParameterizedTest(name = "TC-ST-DD-03 - Check first selected value from multi selector list with {0} value")
    @MethodSource("provideTestDataForMultiSelectorListFirstSelectedValue")
    void checkFirstSelectedValue(List<String> options, String expected) {
        dropdownDemoPage.selectMultipleItems(options);
        assertEquals(dropdownDemoPage.getFirstSelectedOption(), expected);
    }

    @DisplayName("TC-ST-DD-04 - Check first and all selected values from multi selector list when one input is wrong")
    @Test
    void checkFirstAndAllSelectedValuesWhenOneOfTheInputsIsWrong() {
        List<String> options = Arrays.asList("California", "Newyrk", "Texas");
        dropdownDemoPage.selectMultipleItems(options);
        assertEquals(dropdownDemoPage.getFirstSelectedOption(), "First selected option is : California");
        logger.info("{} : TC-ST-DD-04 - First selected item passed with partially wrong input", this.getClass());
        assertEquals(dropdownDemoPage.getAllSelected(), "Options selected are : California,Texas");
        logger.info("{} : TC-ST-DD-04 - All selectable items passed with partially wrong input", this.getClass());
    }
}
