package org.example.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.example.models.Product;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage {
    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");
    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");

    private final ElementsCollection cartList = $$x("//div[@class='cart_item']");
    private final SelenideElement cartHeader = $x("//*[@id=\"header_container\"]/div[2]/span");
    private final SelenideElement descriptionHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[2]");
    private final SelenideElement QTYHeader = $x("//*[@id=\"cart_contents_container\"]/div/div[1]/div[1]");

    private final SelenideElement cartCounter = $x("//*[@id=\"shopping_cart_container\"]/a/span");


    public CartPage() {
        super();
    }
    @Override
    @Step
    public void checkURL() {
        String currentURL = WebDriverRunner.url();
        String expectedURL = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(currentURL, expectedURL, "URL страницы не соответствует ожидаемому значению");
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
    public void checkProduct(Product product){
        //Перебираем всю корзину, так как это даст возможность расширить
        //функционал и проверять
        //каждый добавленный элемент, если в будущем захотим добавлять больше
        //одного случайного товара в корзину (далее можно будет добавить
        // проверку количества добавленных элементов, размера и счетчика корзины)
        for(SelenideElement cardProduct : cartList) {
            Product productFromCart = new Product(cardProduct
                    .$(".inventory_item_price")
                    .text(),
                    cardProduct
                            .$(".inventory_item_name")
                            .text(),
                    cardProduct
                            .$(".inventory_item_desc")
                            .text());
            if(product.equals(productFromCart))
                return;
        }
        throw new AssertionError("В корзине нет ни одного товара, который совпал с добавленным");
    }

    @Step("Проверка размера корзины")
    public void checkCartSize(int expectedSize){
        Assert.assertEquals(expectedSize, cartList.size(),
                "Размер корзины не соответствует ожидаемому значению");
    }

    @Step("Проверка отображения счетчика корзины, с передаваемым значением true и false")
    public void checkCartCounterVisibility(boolean value){
        Assert.assertEquals(value, cartCounter.isDisplayed(), "Ошибка отображения счетчика корзины");
    }
}
