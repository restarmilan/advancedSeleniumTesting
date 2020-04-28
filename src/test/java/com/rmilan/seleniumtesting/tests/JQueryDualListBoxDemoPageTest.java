package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.JQueryDualListBoxDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringConfig.class)
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(SeleniumTestWatcher.class)
class JQueryDualListBoxDemoPageTest {

    @Autowired
    JQueryDualListBoxDemoPage jQueryDualListBoxDemoPage;

    static Stream<Arguments> provideTestDataForSelectMultiplePicks() {
        return Stream.of(
                Arguments.of(Arrays.asList("Isis")),
                Arguments.of(Arrays.asList("Isis", "Sophia", "Alice")),
                Arguments.of(Arrays.asList("Manuela", "Giovanna", "Maria Luiza", "Julia"))
        );
    }

    @DisplayName("TC-ST-JDLB-01 - Check pick result list after multiple picks added")
    @ParameterizedTest(name = "TC-ST-JDLB-01 - Check pick result list after {0} picks added")
    @MethodSource("provideTestDataForSelectMultiplePicks")
    void checkPickResultListAfterAddition(List<String> options) {
        jQueryDualListBoxDemoPage.selectMultipleItems(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        int noOfAddedPicks = jQueryDualListBoxDemoPage.getNumberOfPickResultListOptions();
        List<String> addedPicks = jQueryDualListBoxDemoPage.getPickResultListOptions();
        assertEquals(options.size(), noOfAddedPicks);
        System.out.println(String.format("TC-ST-JDLB-01 - Number of added picks validated using %s values", options));
        assertEquals(options, addedPicks);
        System.out.println(String.format("TC-ST-JDLB-01 - Added picks validated using %s values", options));
    }

    @DisplayName("TC-ST-JDLB-02 - Check original pick list after multiple picks added to pick result list")
    @ParameterizedTest(name = "TC-ST-JDLB-02 - Check original pick list after {0} picks added to pick result list")
    @MethodSource("provideTestDataForSelectMultiplePicks")
    void checkOriginalPickListAfterAddition(List<String> options) {
        jQueryDualListBoxDemoPage.selectMultipleItems(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        int remainingPicks = jQueryDualListBoxDemoPage.getAllPicksBeforeSelectAny() - options.size();
        assertEquals(remainingPicks, jQueryDualListBoxDemoPage.getNumberOfSelectablePickOptions());
    }
}