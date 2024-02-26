package org.example.pages;

import io.qameta.allure.Step;

public abstract class BasePage {
    public BasePage() {
    }
    @Step("Проверка URL-адреса")
    public abstract void checkURL();
}
