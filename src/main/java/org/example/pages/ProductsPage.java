package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage {

    private final SelenideElement addToCart = $x("//*[@id=\"add-to-cart-sauce-labs-backpack\"]").shouldBe(Condition.visible);

    private final SelenideElement cart = $x("//*[@id=\"shopping_cart_container\"]/a").shouldBe(Condition.visible);

    private final SelenideElement productsHeader = $x("//*[@id=\"header_container\"]/div[2]/span").shouldBe(Condition.visible);

    private final SelenideElement sortButton = $x("//*[@id=\"header_container\"]/div[2]/div/span/select").shouldBe(Condition.visible);


    public ProductsPage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }

    public void addToCart(){
        addToCart.click();
    }

    public void openCart(){
        cart.click();
    }
}
