package org.example.models;

import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import com.codeborne.selenide.*;

import java.util.Collections;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

public class BaseTest {
    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static CartPage cartPage;

    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("prefs", Collections.singletonMap("profile.password_manager_enabled", false));
        Configuration.browserCapabilities = chromeOptions;
        open("https://www.saucedemo.com/");
        WebDriverRunner.clearBrowserCache();
        clearBrowserLocalStorage();
        clearBrowserCookies();
        loginPage = new LoginPage();
        //Проверка на корректное отображение элементов на странице авторизации
        loginPage.checkURL();
        loginPage.checkLoginPage();
    }

    protected BaseTest login(String username, String password) {
        loginPage.writeUsername(username);
        loginPage.writePassword(password);
        loginPage.auth();
        return this;
    }

    public BaseTest addToCartAndDeleteRandomProduct(){
        //Добавление одного случайного товара в корзину, сохраняем его, для
        // того чтобы затем сравнить совпал ли он в корзине
        Product addedProduct = productsPage.addToCartRandom();

        //Проверка счетчика у корзины
        productsPage.checkAmountOfCart(1);

        productsPage.openCart();

        cartPage = new CartPage();

        //Проверка успешной авторизации и корректного отображения элементов на странице корзины
        cartPage.checkURL();
        cartPage.checkHeaders();
        cartPage.checkProduct(addedProduct);

        cartPage.deleteOneElementFromCart();
        cartPage.checkCartSize(0);
        //Проверяем счетчик корзины (не должен отображаться)
        cartPage.checkCartCounterVisibility(false);
        return this;
    }

    public BaseTest openLeftSideMenuAndLogout(){
        productsPage.openLeftSideMenu();

        //Проверка корректного отображения левого бокового меню
        productsPage.checkLeftSideMenuContent();

        //Выход из учетной записи
        productsPage.logout();

        loginPage.checkURL();
        loginPage.checkLoginPage();
        return this;
    }

    public BaseTest setupProductsPage() {
        productsPage = new ProductsPage();
        productsPage.checkURL();
        productsPage.checkHeadersAndButtons();
        productsPage.checkAllProducts(6);
        return this;
    }

    public BaseTest addAndRemoveAllProducts(){
        //Добавление всех товаров в корзину
        productsPage.addToCartAllProducts();

        //Проверка счетчика у корзины
        productsPage.checkAmountOfCart(6);

        //Проверка корректного отображения всех кнопок REMOVE
        productsPage.checkAllRemoveButtons();

        //Удаление всех товаров из корзины
        productsPage.removeFromCartAllProducts();

        //Проверяем счетчик корзины (не должен отображаться)
        productsPage.checkCartCounterVisibility(false);
        return this;
    }

    public BaseTest sortZA(){
        //Сортировка Z-A
        productsPage.openSortMenu();
        productsPage.choseSortFromMenuByIndex(1);

        //Проверка отсортированности
        productsPage.checkSortZA();
        return this;
    }
}