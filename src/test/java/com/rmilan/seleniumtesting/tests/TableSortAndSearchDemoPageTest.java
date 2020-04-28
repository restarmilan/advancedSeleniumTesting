package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.TableSortAndSearchDemoPage;
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
class TableSortAndSearchDemoPageTest {

    @Autowired
    TableSortAndSearchDemoPage tableSortAndSearchDemoPage;

    @DisplayName("TC-ST-TSS-01 - Check table selected number of showing entries")
    @ParameterizedTest(name = "TC-ST-TSS-01 - Check table selected number of showing entries with {0} value.")
    @CsvFileSource(resources = "/table_entries.csv", numLinesToSkip = 1)
    void checkSelectedTableEntries(String option, String tableRows, String expected) {
        tableSortAndSearchDemoPage.selectNumberOfTableEntries(option);
        assertEquals(Integer.parseInt(tableRows), tableSortAndSearchDemoPage.getNumberOfTableRows());
        System.out.println(String.format("TC-ST-TSS-01 - Number of rows validated with %s value", option));
        assertEquals(expected, tableSortAndSearchDemoPage.gettableEntriesInfo());
        System.out.println(String.format("TC-ST-TSS-01 - Table entry info validated with %s value", option));
    }
}