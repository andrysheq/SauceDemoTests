package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final SelenideElement deleteFromCart = $x("//*[@id=\"remove-sauce-labs-backpack\"]");
    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");
    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");
    private final SelenideElement cartHeader = $x("//*[@id=\"header_container\"]/div[2]/span").shouldBe(Condition.visible);
    private final SelenideElement descriptionHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[2]").shouldBe(Condition.visible);
    private final SelenideElement QTYHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[1]").shouldBe(Condition.visible);


    public CartPage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }

    public void deleteFromCart(){
        deleteFromCart.shouldBe(Condition.visible).click();
    }

    public void openLeftSideMenu(){
        menu.shouldBe(Condition.visible).click();
    }

    public void logout(){
        logout.shouldBe(Condition.visible).click();
    }
}
