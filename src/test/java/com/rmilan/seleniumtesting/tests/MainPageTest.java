package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringConfig.class)
class MainPageTest {

    @Autowired
    private MainPage mainPage;


    @Test
    @DisplayName("TC-ST-MP-01 - Check webdriver navigation")
    void checkNavigationToMainPage(){
        mainPage.navigateTo();
        assertEquals(mainPage.getPageUrl(),"https://www.seleniumeasy.com/test/");
    }

    @Test
    @DisplayName("TC-ST-MP-02 - Check example selector on main page")
    void checkExampleSelector() {
        mainPage.navigationUsingExampleSelector();
        assertEquals(mainPage.getPageUrl(),"https://www.seleniumeasy.com/test/basic-first-form-demo.html");

    }
}