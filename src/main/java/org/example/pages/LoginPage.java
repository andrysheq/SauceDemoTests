package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage{
    private final WebElement username = $x("//*[@id=\"user-name\"]");

    private final WebElement password = $x("//*[@id=\"password\"]");

    private final WebElement auth = $x("//*[@id=\"login-button\"]");


    public LoginPage() {
        super();
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

    @Step("Проверка на отображение кнопки 'LOGIN' и полей username и password")
    public void checkLoginPage(){
        Assert.assertTrue(username.isDisplayed(), "Поле username не отображается");
        Assert.assertTrue(password.isDisplayed(), "Поле password не отображается");
        Assert.assertTrue(auth.isEnabled() && auth.isDisplayed(), "Кнопка LOGIN не активна или не отображается");
    }
}
