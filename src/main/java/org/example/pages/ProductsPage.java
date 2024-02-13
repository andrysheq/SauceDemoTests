package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$$x;
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
    @Step("Проверка количества продуктов в корзине")
    public void checkAmountOfCart(int expectedAmount){
        String cartProductsText = $x("//*[@id=\"shopping_cart_container\"]/a/span").getText();

        int actualAmount = Integer.parseInt(cartProductsText);

        if (actualAmount != expectedAmount) {
            // Фиксация ошибки с помощью Allure
            Allure.addAttachment("Ошибка проверки количества продуктов в корзине",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + actualAmount);

            // Вывести информацию об ошибке в лог
//            log.error("Ошибка проверки количества продуктов в корзине. " +
//                    "Ожидаемое количество: {}, Фактическое количество: {}", expectedAmount, actualAmount);

            // Выбросить исключение, чтобы пометить тест как неудачный
            throw new AssertionError("Количество продуктов в корзине не соответствует ожидаемому значению");
        }
    }

    @Step("Проверка отображения 6 товаров в алфавитном порядке")
    public void checkAllProducts(int expectedAmount){
        if(expectedAmount != $$x("//*[@id='inventory_container']//div[@class='inventory_item']").size()){
            Allure.addAttachment("Ошибка проверки количества продуктов на главной странице",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());
            System.out.println("Ошибка проверки количества продуктов на главной странице" +
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());
            // Выбросить исключение, чтобы пометить тест как неудачный
            throw new AssertionError("Количество продуктов на главной странице не соответствует ожидаемому значению");
        }
    }
}
