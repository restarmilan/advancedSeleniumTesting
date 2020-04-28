package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.TableSortAndSearchDemoPage;
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

import java.util.Arrays;

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

    @DisplayName("TC-ST-TSS-02 - Check case-insensitive table search feature")
    @ParameterizedTest(name = "TC-ST-TSS-02 - Check case-insensitive table search feature with {0} value")
    @CsvFileSource(resources = "/table_search.csv", numLinesToSkip = 1)
    void checkTableSearch(String searchKey, String result, String expectedInfo) {
        tableSortAndSearchDemoPage.tableSearch(searchKey);
        assertEquals(Integer.parseInt(result), tableSortAndSearchDemoPage.getNumberOfTableRows());
        System.out.println(String.format("TC-ST-TSS-02 - Number of results validated with %s value", searchKey));
        assertEquals(expectedInfo, tableSortAndSearchDemoPage.gettableEntriesInfo());
        System.out.println(String.format("TC-ST-TSS-02 - Number of results table info validated with %s value", searchKey));
    }

    @DisplayName("TC-ST-TSS-03 - Check table pagination using page indexes")
    @ParameterizedTest(name = "TC-ST-TSS-03 - Check table pagination using page index {0}")
    @CsvFileSource(resources = "/table_pagination_indexes.csv", numLinesToSkip = 1)
    void checkTablePaginationUsingIndexes(String pageNumber, String expectedInfo) {
        tableSortAndSearchDemoPage.tablePaginationUsingPageIndex(pageNumber);
        assertEquals(expectedInfo, tableSortAndSearchDemoPage.gettableEntriesInfo());
    }

    @DisplayName("TC-ST-TSS-04 - Check table pagination using direction buttons")
    @Test
    void checkTablePaginationUsingDirectionButtons() {
        tableSortAndSearchDemoPage.tablePaginationUsingDirectionButtons();
        assertEquals(tableSortAndSearchDemoPage.getPaginationInfos(), Arrays.asList("Showing 11 to 20 of 32 entries",
                "Showing 21 to 30 of 32 entries", "Showing 31 to 32 of 32 entries", "Showing 21 to 30 of 32 entries",
                "Showing 11 to 20 of 32 entries", "Showing 1 to 10 of 32 entries"));
    }
}