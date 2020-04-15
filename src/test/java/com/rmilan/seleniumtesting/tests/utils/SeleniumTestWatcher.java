package com.rmilan.seleniumtesting.tests.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SeleniumTestWatcher implements TestWatcher {

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
        //here comes a method calling for create screen shots from failed test cases
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        // do something
    }
}
