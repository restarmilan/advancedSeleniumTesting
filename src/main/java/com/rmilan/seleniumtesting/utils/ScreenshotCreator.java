package com.rmilan.seleniumtesting.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScreenshotCreator {

    private static WebDriver driver;

    public ScreenshotCreator(WebDriver driver) {
        ScreenshotCreator.driver = driver;
    }

    public static void createScreenshot(String tcName) {
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm'.png'").format(new Date());
        try {
            new File("target/screenshots").mkdir();
            FileOutputStream out = new FileOutputStream("target/screenshots/" + tcName + "--" + fileName);
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
