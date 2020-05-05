package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.JQueryDualListBoxDemoPage;
import com.rmilan.seleniumtesting.tests.utils.SeleniumTestWatcher;
import com.rmilan.seleniumtesting.tests.utils.TestNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
class JQueryDualListBoxDemoPageTest {

    @Autowired
    JQueryDualListBoxDemoPage jQueryDualListBoxDemoPage;

    private final static Logger logger = LoggerFactory.getLogger(JQueryDualListBoxDemoPageTest.class);

    static Stream<Arguments> provideTestDataForSelectMultiplePicks() {
        return Stream.of(
                Arguments.of(Arrays.asList("Isis")),
                Arguments.of(Arrays.asList("Isis", "Sophia", "Alice")),
                Arguments.of(Arrays.asList("Manuela", "Giovanna", "Maria Luiza", "Julia"))
        );
    }

    static Stream<Arguments> provideTestDataForSelectMultiplePicksFromPickResultList() {
        return Stream.of(
                Arguments.of(Arrays.asList("Isis", "Alice", "Manuela", "Valentina", "Helena"),
                        Arrays.asList("Isis", "Manuela", "Helena"),
                        Arrays.asList("Alice", "Valentina")),
                Arguments.of(Arrays.asList("Isis", "Sophia", "Alice", "Isabella", "Manuela", "Laura", "Luiza"),
                        Arrays.asList("Isis", "Sophia", "Alice", "Isabella"),
                        Arrays.asList("Manuela", "Laura", "Luiza"))
        );
    }

    @DisplayName("TC-ST-JDLB-01 - Check pick result list after multiple picks added")
    @ParameterizedTest(name = "TC-ST-JDLB-01 - Check pick result list after {0} picks added")
    @MethodSource("provideTestDataForSelectMultiplePicks")
    void checkPickResultListAfterAddition(List<String> options) {
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickList(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        int noOfAddedPicks = jQueryDualListBoxDemoPage.getNumberOfPicksInPickResultList();
        List<String> addedPicks = jQueryDualListBoxDemoPage.getPickResultListOptions();
        assertEquals(options.size(), noOfAddedPicks);
        logger.info("{} : TC-ST-JDLB-01 - Number of added picks validated using {} values", this.getClass(), options);
        //System.out.println(String.format("TC-ST-JDLB-01 - Number of added picks validated using %s values", options));
        assertEquals(options, addedPicks);
        logger.info("{} : TC-ST-JDLB-01 - Added picks validated using {} values", this.getClass(), options);
        //System.out.println(String.format("TC-ST-JDLB-01 - Added picks validated using %s values", options));
    }

    @DisplayName("TC-ST-JDLB-02 - Check original pick list after multiple picks added to pick result list")
    @ParameterizedTest(name = "TC-ST-JDLB-02 - Check original pick list after {0} picks added to pick result list")
    @MethodSource("provideTestDataForSelectMultiplePicks")
    void checkOriginalPickListAfterAddition(List<String> options) {
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickList(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        int remainingPicks = jQueryDualListBoxDemoPage.getAllPicksBeforeSelectAny() - options.size();
        assertEquals(remainingPicks, jQueryDualListBoxDemoPage.getNumberOfSelectablePickOptions());
    }

    @DisplayName("TC-ST-JDLB-03 - Check number of picks in pick result list after added all picks")
    @Test
    void checkAddAllPicksToPickResultList() {
        jQueryDualListBoxDemoPage.addAllPicksToPickResultList();
        assertEquals(jQueryDualListBoxDemoPage.getNumberOfPicksInPickResultList(),
                jQueryDualListBoxDemoPage.getAllPicksBeforeSelectAny());
    }

    @DisplayName("TC-ST-JDLB-04 - Check the number of picks in original pick list after all picks added to pick result list")
    @Test
    void checkOriginalPickListAfterAllPicksAdd() {
        jQueryDualListBoxDemoPage.addAllPicksToPickResultList();
        assertEquals(0, jQueryDualListBoxDemoPage.getNumberOfSelectablePickOptions());
    }

    @DisplayName("TC-ST-JDLB-05 - Check the number of picks in pick result list after all picks removed")
    @Test
    void checkResultPickListAfterAllPicksRemoved() {
        jQueryDualListBoxDemoPage.addAllPicksToPickResultList();
        jQueryDualListBoxDemoPage.removeAllPicksFromPickResultList();
        assertEquals(0, jQueryDualListBoxDemoPage.getNumberOfPicksInPickResultList());
    }

    @DisplayName("TC-ST-JDLB-06 - Check the number of picks in original pick list after all picks removed from pick result list")
    @Test
    void checkOriginalPickListAfterAllPicksRemovedFromPickResultList() {
        jQueryDualListBoxDemoPage.addAllPicksToPickResultList();
        jQueryDualListBoxDemoPage.removeAllPicksFromPickResultList();
        assertEquals(jQueryDualListBoxDemoPage.getAllPicksBeforeSelectAny(),
                jQueryDualListBoxDemoPage.getNumberOfSelectablePickOptions());
    }

    @DisplayName("TC-ST-JDLB-07 - Check pick result list after remove some of the selected added items")
    @ParameterizedTest(name = "TC-ST-JDLB-07 - Check pick result list after remove {1} of {0} added items")
    @MethodSource("provideTestDataForSelectMultiplePicksFromPickResultList")
    void checkPickResultListAfterRemoveSelectedPicks(List<String> options, List<String> removablePicks, List<String> remainingPicks) {
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickList(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickResultList(removablePicks);
        jQueryDualListBoxDemoPage.removeSelectedPicksFromPickResultList();
        assertEquals(remainingPicks.size(), jQueryDualListBoxDemoPage.getNumberOfPicksInPickResultList());
        logger.info("{} : TC-ST-JDLB-07 - Number of remaining items in pick result list passed", this.getClass());
        assertEquals(remainingPicks, jQueryDualListBoxDemoPage.getPickResultListOptions());
        logger.info("{} : TC-ST-JDLB-07 - {} remaining picks passed", this.getClass(), remainingPicks);
    }

    @DisplayName("TC-ST-JDLB-08 - Check original pick list after remove some of the selected added items to pick result list")
    @ParameterizedTest(name = "TC-ST-JDLB-08 - Check original pick result list after remove {1} items from {0} added items to pick result list")
    @MethodSource("provideTestDataForSelectMultiplePicksFromPickResultList")
    void checkPickListAfterRemoveSelectedPicksFromPickResultList(List<String> options, List<String> removablePicks, List<String> remainingPicks) {
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickList(options);
        jQueryDualListBoxDemoPage.addSelectedItemsToResultList();
        jQueryDualListBoxDemoPage.selectMultipleItemsFromPickResultList(removablePicks);
        jQueryDualListBoxDemoPage.removeSelectedPicksFromPickResultList();
        int picksInPickList = jQueryDualListBoxDemoPage.getAllPicksBeforeSelectAny() - remainingPicks.size();
        assertEquals(picksInPickList, jQueryDualListBoxDemoPage.getNumberOfSelectablePickOptions());
    }
}