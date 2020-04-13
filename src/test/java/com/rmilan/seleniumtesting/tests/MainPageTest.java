package com.rmilan.seleniumtesting.tests;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringConfig.class)
class MainPageTest {

    @Autowired
    private MainPage mainPage;


    @Test
    @DisplayName("Check basic navigation method")
    void checkWebDriver(){
        mainPage.navigateTo();
    }

}