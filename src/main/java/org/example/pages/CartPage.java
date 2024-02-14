package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage{
    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");
    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");

    private final ElementsCollection cartList = $$x("//div[@class='cart_item']");
    private final SelenideElement cartHeader = $x("//*[@id=\"header_container\"]/div[2]/span");
    private final SelenideElement descriptionHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[2]");
    private final SelenideElement QTYHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[1]");


    public CartPage() {
        super();
    }

    public void deleteAllFromCart(){
        cartList.forEach(item -> {
            // Найти кнопку "Remove" для каждого товара и нажать на неё
            item.$x(".//button[contains(@class, 'btn_secondary') and contains(@class, 'btn_small') and contains(@class, 'cart_button')]").click();
        });
    }

    public void deleteOneElementFromCart(){
        cartList.first().$x(".//button[contains(@class, 'btn_secondary') and contains(@class, 'btn_small') and contains(@class, 'cart_button')]").click();
    }

    public void openLeftSideMenu(){
        menu.shouldBe(Condition.visible).click();
    }

    public void logout(){
        logout.shouldBe(Condition.visible).click();
    }

    @Step("Проверка заголовков страницы корзины")
    public void checkHeaders(){
        Assert.assertTrue(cartHeader.isDisplayed() && QTYHeader.isDisplayed() && descriptionHeader.isDisplayed(), "Некорректное отображение заголовков");
    }

    @Step("Проверка на соответствие добавленного в корзину элемента")
    public void checkProduct(List<String> results){

    }
}
