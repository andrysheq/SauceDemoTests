import com.codeborne.selenide.*;
import org.example.pages.BasePage;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class TestStandardUser {

    public static LoginPage loginPage;
    public static ProductsPage productsPage;

    public static CartPage cartPage;

    @BeforeClass
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        open("https://www.saucedemo.com/");
        WebDriverRunner.clearBrowserCache();
        clearBrowserLocalStorage();
        clearBrowserCookies();
        loginPage = new LoginPage();

    }

    @org.testng.annotations.Test
    public void test1(){
        //Проверка на корректное отображение элементов на странице авторизации
        BasePage.checkURL("https://www.saucedemo.com/");
        loginPage.checkLoginPage();

        //Авторизация
        loginPage.writeUsername("standard_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();

        //Проверка успешной авторизации
        BasePage.checkURL("https://www.saucedemo.com/inventory.html");

        //Создания экземпляра страницы с товарами
        productsPage = new ProductsPage();

        //Проверка успешной авторизации корректного отображения элементов на странице с товарами
        productsPage.checkHeadersAndButtons();
        productsPage.checkAllProducts(6);

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

        //Сортировка Z-A
        productsPage.openSortMenu();
        productsPage.choseSortFromMenuByIndex(1);

        //Проверка отсортированности
        productsPage.checkSortZA();
        productsPage.openLeftSideMenu();

        //Проверка корректного отображения левого бокового меню
        productsPage.checkLeftSideMenuContent();

        //Выход из учетной записи
        productsPage.logout();

        BasePage.checkURL("https://www.saucedemo.com/");
        loginPage.checkLoginPage();
    }

//    @org.testng.annotations.Test
//    public void test2(){
//        //Проверка на корректное отображение элементов на странице авторизации
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        //Авторизация
//        loginPage.writeUsername("locked_out_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//        //Проверка успешной аутентификации
//        BasePage.checkURL("https://www.saucedemo.com/inventory.html");
//
//        sleep(1000);
//
//        //Создания экземпляра страницы с товарами
//        productsPage = new ProductsPage();
//
//        //Проверка корректного отображения элементов на странице с товарами
//        productsPage.checkHeadersAndButtons();
//        productsPage.checkAllProducts(6);
//
//        //Добавление всех товаров в корзину
//        productsPage.addToCartAllProducts();
//
//        //Проверка счетчика у корзины
//        productsPage.checkAmountOfCart(6);
//
//        //Проверка корректного отображения всех кнопок REMOVE
//        productsPage.checkAllRemoveButtons();
//
//        sleep(1000);
//
//        //Удаление всех товаров из корзины
//        productsPage.removeFromCartAllProducts();
//
//        //Проверяем счетчик корзины (не должен отображаться)
//        productsPage.checkCartCounterVisibility(false);
//
//        sleep(1000);
//
//        //Сортировка Z-A
//        productsPage.openSortMenu();
//        productsPage.choseSortFromMenuByIndex(1);
//
//        //Проверка отсортированности
//        productsPage.checkSortZA();
//        productsPage.openLeftSideMenu();
//
//        sleep(1000);
//
//        //Проверка корректного отображения левого бокового меню
//        productsPage.checkLeftSideMenuContent();
//
//        //Выход из учетной записи
//        productsPage.logout();
//
//        sleep(1000);
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        sleep(1000);
//    }
//
//    @org.testng.annotations.Test
//    public void test3(){
//        //Проверка на корректное отображение элементов на странице авторизации
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        //Авторизация
//        loginPage.writeUsername("problem_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//        //Проверка успешной аутентификации
//        BasePage.checkURL("https://www.saucedemo.com/inventory.html");
//
//        sleep(1000);
//
//        //Создания экземпляра страницы с товарами
//        productsPage = new ProductsPage();
//
//        //Проверка корректного отображения элементов на странице с товарами
//        productsPage.checkHeadersAndButtons();
//        productsPage.checkAllProducts(6);
//
//        //Добавление всех товаров в корзину
//        productsPage.addToCartAllProducts();
//
//        //Проверка счетчика у корзины
//        productsPage.checkAmountOfCart(6);
//
//        //Проверка корректного отображения всех кнопок REMOVE
//        productsPage.checkAllRemoveButtons();
//
//        sleep(1000);
//
//        //Удаление всех товаров из корзины
//        productsPage.removeFromCartAllProducts();
//
//        //Проверяем счетчик корзины (не должен отображаться)
//        productsPage.checkCartCounterVisibility(false);
//
//        sleep(1000);
//
//        //Сортировка Z-A
//        productsPage.openSortMenu();
//        productsPage.choseSortFromMenuByIndex(1);
//
//        //Проверка отсортированности
//        productsPage.checkSortZA();
//
//        productsPage.openLeftSideMenu();
//
//        sleep(1000);
//
//        //Проверка корректного отображения левого бокового меню
//        productsPage.checkLeftSideMenuContent();
//
//        //Выход из учетной записи
//        productsPage.logout();
//
//        sleep(1000);
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        sleep(1000);
//    }
//
//    @org.testng.annotations.Test
//    public void test4(){
//        //Проверка на корректное отображение элементов на странице авторизации
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        //Авторизация
//        loginPage.writeUsername("performance_glitch_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//        //Проверка успешной аутентификации
//        BasePage.checkURL("https://www.saucedemo.com/inventory.html");
//
//        sleep(1000);
//
//        //Создания экземпляра страницы с товарами
//        productsPage = new ProductsPage();
//
//        //Проверка корректного отображения элементов на странице с товарами
//        productsPage.checkHeadersAndButtons();
//        productsPage.checkAllProducts(6);
//
//        //Добавление всех товаров в корзину
//        productsPage.addToCartAllProducts();
//
//        //Проверка счетчика у корзины
//        productsPage.checkAmountOfCart(6);
//
//        //Проверка корректного отображения всех кнопок REMOVE
//        productsPage.checkAllRemoveButtons();
//
//        sleep(1000);
//
//        //Удаление всех товаров из корзины
//        productsPage.removeFromCartAllProducts();
//
//        //Проверяем счетчик корзины (не должен отображаться)
//        productsPage.checkCartCounterVisibility(false);
//
//        sleep(1000);
//
//        //Сортировка Z-A
//        productsPage.openSortMenu();
//        productsPage.choseSortFromMenuByIndex(1);
//
//        //Проверка отсортированности
//        productsPage.checkSortZA();
//
//        productsPage.openLeftSideMenu();
//
//        sleep(1000);
//
//        //Проверка корректного отображения левого бокового меню
//        productsPage.checkLeftSideMenuContent();
//
//        //Выход из учетной записи
//        productsPage.logout();
//
//        sleep(1000);
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        sleep(1000);
//    }
//    @org.testng.annotations.Test
//    public void test5(){
//        //Проверка на корректное отображение элементов на странице авторизации
//        BasePage.checkURL("https://www.saucedemo.com/");
//        loginPage.checkLoginPage();
//
//        //Авторизация
//        loginPage.writeUsername("standard_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//
//        //Проверка успешной авторизации
//        BasePage.checkURL("https://www.saucedemo.com/inventory.html");
//
//        sleep(1000);
//
//        //Создания экземпляра страницы с товарами
//        productsPage = new ProductsPage();
//
//        //Проверка успешной авторизации корректного отображения элементов на странице с товарами
//        productsPage.checkHeadersAndButtons();
//        productsPage.checkAllProducts(6);
//
//        //Добавление всех товаров в корзину
//        productsPage.addToCartRandom();
//
//        //Проверка счетчика у корзины
//        productsPage.checkAmountOfCart(1);
//
//        productsPage.openCart();
//
//        BasePage.checkURL("https://www.saucedemo.com/cart.html");
//
//        cartPage = new CartPage();
//        cartPage.checkHeaders();
//
//        cartPage.deleteOneElementFromCart();
//
//        cartPage.openLeftSideMenu();
//
//        cartPage.logout();
//
//        loginPage = new LoginPage();
//
//        sleep(3000);
//    }
}
