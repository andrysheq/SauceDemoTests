package org.example.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BasePage {
    public BasePage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }
    @Step
    public static void checkURL(String expectedURL) {
        String currentURL = WebDriverRunner.url();
        Assert.assertEquals(currentURL, expectedURL, "URL страницы не соответствует ожидаемому значению");
    }
}
