package org.example.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final SelenideElement deleteFromCart = $x("//*[@id=\"remove-sauce-labs-backpack\"]");

    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");

    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");


    public CartPage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }

    public void deleteFromCart(){
        deleteFromCart.click();
    }

    public void openLeftSideMenu(){
        menu.click();
    }

    public void logout(){
        logout.click();
    }
}
