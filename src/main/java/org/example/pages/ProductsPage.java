package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.models.Product;
import org.testng.Assert;

import java.util.*;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage {
    //Хранение товаров
    SelenideElement products = $$x("(//*[@id=\"inventory_container\"])[1]/div").first();
    ElementsCollection productDetails = products.$$(".inventory_item");

    //Элементы, которые нужны для работы с корзиной
    private final SelenideElement cart = $x("//*[@id=\"shopping_cart_container\"]/a");
    private final SelenideElement cartCounter = $x("//*[@id=\"shopping_cart_container\"]/a/span");

    //Заголовок
    private final SelenideElement productsHeader = $x("//*[@id=\"header_container\"]/div[2]/span");

    //Меню сортировки списка товаров
    private final SelenideElement sortMenu = $x("//*[@id=\"header_container\"]/div[2]/div/span/select");

    //Левое боковое меню и кнопка выхода из аккаунта
    private final SelenideElement menu = $x("//*[@id=\"react-burger-menu-btn\"]");

    //ElementsCollection leftSideMenuContent =
    private final SelenideElement logout = $x("//*[@id=\"logout_sidebar_link\"]");
    public ProductsPage() {
        super();
    }
    @Override
    @Step("Проверка URL-адреса")
    public void checkURL() {
        String currentURL = WebDriverRunner.url();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(currentURL, expectedURL, "URL страницы не соответствует ожидаемому значению");
    }
    public void openLeftSideMenu(){
        menu.shouldBe(visible).click();
    }

    public void logout(){
        logout.shouldBe(visible).click();
    }

    public Product addToCartRandom(){
        Random random = new Random();
        int randomIndexOfProduct = random.nextInt(6);
        SelenideElement detail = productDetails.get(randomIndexOfProduct);
        SelenideElement addToCartButton = detail.$(".btn_inventory");
        addToCartButton.click();
        checkRemoveButton(randomIndexOfProduct);
        Product addedProduct = new Product(detail.$(".inventory_item_price").text(),
                detail.$(".inventory_item_name").text(),
                detail.$(".inventory_item_desc").text());
        return addedProduct;
    }

    public void openCart(){
        cart.click();
    }
    public void openSortMenu(){
        sortMenu.click();
    }

    public void choseSortFromMenuByIndex(int index){
        // Находим элемент select сортировки
        SelenideElement sortByElement = $(".product_sort_container");

        // Находим все опции внутри select
        ElementsCollection options = sortByElement.$$("option");

        // Проверяем, что индекс находится в пределах диапазона опций
        if (index >= 0 && index < options.size()) {
            // Кликаем на опцию с переданным индексом
            options.get(index).shouldBe(visible).click();
        } else {
            // Выводим сообщение об ошибке, если индекс выходит за пределы диапазона
            System.out.println("Индекс сортировки находится за пределами допустимого диапазона");
        }
    }

    public void addToCartAllProducts(){
        for(SelenideElement detail : productDetails){
            SelenideElement addToCartButton = detail.$(".btn_inventory");
            addToCartButton.click();
        }
    }

    public void removeFromCartAllProducts(){
        for(SelenideElement detail : productDetails){
            SelenideElement removeButton = detail.$(".btn_inventory");
            removeButton.click();
        }
    }

    @Step("Проверка корректного отображения кнопки REMOVE")
    public void checkAllRemoveButtons(){
        for(SelenideElement detail : productDetails){
            SelenideElement removeButton = detail.$(".btn_inventory");
            Assert.assertTrue(removeButton.isDisplayed() &&
                    removeButton.isEnabled() &&
                    removeButton.text().equalsIgnoreCase("REMOVE"));
        }
    }

    @Step("Проверка корректного отображения кнопки REMOVE")
    public void checkRemoveButton(int index){
        SelenideElement detail = productDetails.get(index);
            SelenideElement removeButton = detail.$(".btn_inventory");
            Assert.assertTrue(removeButton.isDisplayed() &&
                    removeButton.isEnabled() &&
                    removeButton.text().equalsIgnoreCase("REMOVE"));
    }

    @Step("Проверка количества продуктов в корзине")
    public void checkAmountOfCart(int expectedAmount){
        String cartProductsText = cartCounter.getText();

        int actualAmount = Integer.parseInt(cartProductsText);

        if (actualAmount != expectedAmount) {
            Allure.addAttachment("Ошибка проверки количества продуктов в корзине",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + actualAmount);

            throw new AssertionError("Количество продуктов в корзине не соответствует ожидаемому значению");
        }
    }

    @Step("Проверка отображения счетчика корзины, с передаваемым значением true и false")
    public void checkCartCounterVisibility(boolean value){
        Assert.assertEquals(value, cartCounter.isDisplayed(), "Ошибка отображения счетчика корзины");
    }

    @Step("Проверка отображения товаров в алфавитном порядке")
    public void checkAllProducts(int expectedAmount){
        if(expectedAmount != $$x("//*[@id='inventory_container']//div[@class='inventory_item']").size()){
            Allure.addAttachment("Ошибка проверки количества продуктов на главной странице",
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());
            System.out.println("Ошибка проверки количества продуктов на главной странице" +
                    "Ожидаемое количество: " + expectedAmount + ", Фактическое количество: " + $$x("//*[@id=\"inventory_container\"]/div").size());

            throw new AssertionError("Количество продуктов на главной странице не соответствует ожидаемому значению");
        }
        for(int i=0;i<Product.expectedProductsList.size();i++){
            checkProduct(i,Product.expectedProductsList.get(i));
        }
    }

    @Step("Проверка отображения заголовков и кнопок")
    public void checkHeadersAndButtons(){
        Assert.assertTrue(sortMenu.isDisplayed() && sortMenu.isEnabled(), "Кнопка сортировки не отображается или не активна");
        Assert.assertTrue(productsHeader.isDisplayed(), "Заголовок 'Products' не отображается");
        Assert.assertTrue(cart.isDisplayed() && cart.isEnabled(), "Не отображается или не активна кнопка корзины");
    }

    @Step("Проверка товара по его номеру")
    public void checkProduct(int index, Product product) {
        //Выбираем товар по индексу для проверки
        SelenideElement detail = productDetails.get(index);

        SelenideElement productNameElement = detail.$(".inventory_item_name");
        SelenideElement productPriceElement = detail.$(".inventory_item_price");
        SelenideElement addToCartButton = detail.$(".btn_inventory");
        SelenideElement productDescriptionElement = detail.$(".inventory_item_desc");

        //Проверка корректного отображения кнопки Add to cart и ее активности
        Assert.assertTrue(addToCartButton.text().equalsIgnoreCase("Add to cart"), "Не все кнопки являются - Add to cart");
        Assert.assertTrue(addToCartButton.isDisplayed() && addToCartButton.isEnabled(), "Кнопка Add to cart не отображается или не активна");

        Product productFromProductPage = new Product(productPriceElement.text(),productNameElement.text(),productDescriptionElement.text());
        Assert.assertEquals(product,productFromProductPage,"Информация о товаре некорретна");
    }

    @Step("Проверка отсортированности списка товаров")
    public void checkSortZA(){
        List<String> productsTitles = new ArrayList<>();
        for(SelenideElement detail : productDetails){
            String productNameElement = detail.$(".inventory_item_name").getText();
            productsTitles.add(productNameElement);
        }
        List<String> sortedZAProductsTitles = new ArrayList<>(productsTitles);
        sortedZAProductsTitles.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        Assert.assertEquals(sortedZAProductsTitles,productsTitles,"Список не отсортирован от Z до A");
    }

    @Step("Проверка отображения всех элементов левого бокового меню")
    public void checkLeftSideMenuContent() {
        ElementsCollection menuButtons = $$(".bm-item.menu-item");

        // Проверяем отображение и активность каждой кнопки
        for (SelenideElement button : menuButtons) {
            button.shouldBe(visible).shouldBe(enabled);
        }
    }
}
