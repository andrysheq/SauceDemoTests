package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final WebElement username = $x("//*[@id=\"user-name\"]").shouldBe(Condition.visible);

    private final WebElement password = $x("//*[@id=\"password\"]").shouldBe(Condition.visible);

    private final WebElement auth = $x("//*[@id=\"login-button\"]").shouldBe(Condition.visible);


    public LoginPage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }

    public void writeUsername(String username) {
        this.username.sendKeys(username);
    }

    public void writePassword(String password) {
        this.password.sendKeys(password);
    }

    public void auth(){
        auth.click();
    }
}
