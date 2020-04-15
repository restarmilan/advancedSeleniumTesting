package com.rmilan.seleniumtesting.tests.utils;

import com.rmilan.seleniumtesting.config.SpringConfig;
import com.rmilan.seleniumtesting.utils.ScreenshotCreator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

@SpringJUnitConfig(SpringConfig.class)
public class SeleniumTestWatcher implements TestWatcher {

    @Autowired
    private ScreenshotCreator sc;

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        // do something
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        // do something
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        System.out.println("Failed test case");
        System.out.println(sc.toString());
        sc.createScreenshot();
        //Displays the message at the failed test case, but throws a NullPointerException. Why??
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        // do something
    }
}
