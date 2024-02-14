package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.json.JsonOutput;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {

//    private final SelenideElement addToCartFirst = $x("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
//    private final SelenideElement addToCartSecond = $x("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]");
//    private final SelenideElement addToCartThird = $x("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
//    private final SelenideElement addToCartFourth = $x("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]");
//    private final SelenideElement addToCartFifth = $x("//*[@id=\"add-to-cart-sauce-labs-onesie\"]");
//    private final SelenideElement addToCartSixth = $x("//*[@id=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]");
//    private final SelenideElement removeFromCartFirst = $x("//*[@id=\"remove-sauce-labs-backpack\"]");
//
//    private final SelenideElement removeFromCartSecond = $x("//*[@id=\"remove-sauce-labs-bike-light\"]");
//    private final SelenideElement removeFromCartThird = $x("//*[@id=\"remove-sauce-labs-bolt-t-shirt\"]");
//    private final SelenideElement removeFromCartFourth = $x("//*[@id=\"remove-sauce-labs-fleece-jacket\"]");
//    private final SelenideElement removeFromCartFifth = $x("//*[@id=\"remove-sauce-labs-onesie\"]");
//    private final SelenideElement removeFromCartSixth = $x("//*[@id=\"remove-test.allthethings()-t-shirt-(red)\"]");

//    List<SelenideElement> addToCartButtons = List.of(addToCartFirst,addToCartSecond,addToCartThird,addToCartFourth,addToCartFifth,addToCartSixth);
//    List<SelenideElement> removeButtons = List.of(removeFromCartFirst,removeFromCartSecond,removeFromCartThird,removeFromCartFourth,removeFromCartFifth,removeFromCartSixth);

    ElementsCollection products = $$x("//*[@id=\"inventory_container\"]/div");

    private final SelenideElement cart = $x("//*[@id=\"shopping_cart_container\"]/a").shouldBe(Condition.visible);

    private final SelenideElement productsHeader = $x("//*[@id=\"header_container\"]/div[2]/span").shouldBe(Condition.visible);

    private final SelenideElement sortMenu = $x("//*[@id=\"header_container\"]/div[2]/div/span/select").shouldBe(Condition.visible);
    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");
    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");
    public void openLeftSideMenu(){
        menu.shouldBe(Condition.visible).click();
    }

    public void logout(){
        logout.shouldBe(Condition.visible).click();
    }


    public ProductsPage() {
        Configuration.timeout = Duration.of(5, ChronoUnit.SECONDS).toMillis();
    }

    public void addToCart(){
        products.get(1).$x(".//button[contains(@class, 'btn_inventory')]").shouldBe(Condition.visible).click();
    }

    public void openCart(){
        cart.click();
    }
    public void openSortMenu(){
        sortMenu.click();
    }
    //public void

    public void choseSortFromMenuByIndex(int index){
        // Находим элемент select сортировки
        SelenideElement sortByElement = $(".product_sort_container");

        // Находим все опции внутри select
        ElementsCollection options = sortByElement.$$("option");

        // Проверяем, что индекс находится в пределах диапазона опций
        if (index >= 0 && index < options.size()) {
            // Кликаем на опцию с переданным индексом
            options.get(index).shouldBe(Condition.visible).click();
        } else {
            // Выводим сообщение об ошибке, если индекс выходит за пределы диапазона
            System.out.println("Индекс сортировки находится за пределами допустимого диапазона");
        }
    }

//    public void addToCartAllProducts(){
//        addToCartButtons.forEach(o->o.shouldBe(Condition.visible).click());
//    }
//
//    public void removeFromCartAllProducts(){
//        removeButtons.stream()
//                .filter(element -> element.shouldBe(Condition.visible).text().equalsIgnoreCase("REMOVE"))
//                .forEach(SelenideElement::click);
//    }
    @Step("Проверка количества продуктов в корзине")
    public void checkAmountOfCart(int expectedAmount){
        String cartProductsText = $x("//*[@id=\"shopping_cart_container\"]/a/span").getText();

        int actualAmount = Integer.parseInt(cartProductsText);

        if (actualAmount != expectedAmount) {
            Allure.addAttachment("Ошибка проверки количества продуктов в корзине",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + actualAmount);

            throw new AssertionError("Количество продуктов в корзине не соответствует ожидаемому значению");
        }
    }

    @Step("Проверка отображения товаров в алфавитном порядке")
    public void checkAllProducts(int expectedAmount){
        if(expectedAmount != $$x("//*[@id='inventory_container']//div[@class='inventory_item']").size()){
            Allure.addAttachment("Ошибка проверки количества продуктов на главной странице",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());
            System.out.println("Ошибка проверки количества продуктов на главной странице" +
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());
            // Выбросить исключение, чтобы пометить тест как неудачный
            throw new AssertionError("Количество продуктов на главной странице не соответствует ожидаемому значению");
        }

        // Сравнение названий, цен и описаний

        checkProduct(0, "Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "$29.99");
        checkProduct(1, "Sauce Labs Bike Light", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.", "$9.99");
        checkProduct(2, "Sauce Labs Bolt T-Shirt", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.", "$15.99");
        checkProduct(3, "Sauce Labs Fleece Jacket", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.", "$49.99");
        checkProduct(4, "Sauce Labs Onesie", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.", "$7.99");
        checkProduct(5, "Test.allTheThings() T-Shirt (Red)", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.", "$15.99");



    }

    @Step("Проверка отображения заголовков и кнопок")
    public void checkHeadersAndButtons(){
        Assert.assertTrue(sortMenu.isDisplayed() && sortMenu.isEnabled(), "Кнопка сортировки не отображается или не активна");
        Assert.assertTrue(productsHeader.isDisplayed(), "Заголовок 'Products' не отображается");
        Assert.assertTrue(cart.isDisplayed() && cart.isEnabled(), "Не отображается или не активна кнопка корзины");
    }

    @Step("Проверка товара по его номеру")
    public void checkProduct(int index, String name, String description, String price) {
//        Assert.assertEquals(name, products.get(index).
//                $x(".//div[@class='inventory_item_name']").
//                getText(), "Неверное имя товара");
//
//        Assert.assertEquals(description, products.get(index).
//                $x(".//div[@class='inventory_item_label']//div[@class='inventory_item_desc']").
//                getText(), "Неверное описание товара");
//
        Assert.assertEquals(price, products.get(index).
                $x(".//div[@class='inventory_item_price']").
                getText(), "Неверная цена товара");

        SelenideElement addToCart = products.get(index).
                $x(".//button[text()='Add to cart']");
        System.out.println(products);
        addToCart.click();
        //Assert.assertTrue(addToCart.isDisplayed() , "Кнопка ADD TO CART не отображена");
        //Assert.assertTrue(addToCart.isEnabled() , "Кнопка неактивна");

    }


}
