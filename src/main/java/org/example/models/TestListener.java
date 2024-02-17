package org.example.models;


import com.codeborne.selenide.WebDriverRunner;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestListener implements ITestListener, TestLifecycleListener {
    ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("Test started: %s\n", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("Test passed: %s\n", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf("Test failed: %s\n", result.getName());
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if(result.getStatus().name().equalsIgnoreCase("FAILED")) {
            takeScreenshot(drivers.get());
        }
    }
    private void takeScreenshot(WebDriver driver) {
        Allure.addAttachment("Screenshot of failed step", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(WebDriver driver) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
}
