import com.codeborne.selenide.*;
import org.example.pages.BasePage;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.*;

public class TestPerformGlitchUser {

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
    public void test4(){
        //Проверка на корректное отображение элементов на странице авторизации
        BasePage.checkURL("https://www.saucedemo.com/");
        loginPage.checkLoginPage();

        //Авторизация
        loginPage.writeUsername("performance_glitch_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();
        //Проверка успешной аутентификации
        BasePage.checkURL("https://www.saucedemo.com/inventory.html");

        sleep(1000);

        //Создания экземпляра страницы с товарами
        productsPage = new ProductsPage();

        //Проверка корректного отображения элементов на странице с товарами
        productsPage.checkHeadersAndButtons();
        productsPage.checkAllProducts(6);

        //Добавление всех товаров в корзину
        productsPage.addToCartAllProducts();

        //Проверка счетчика у корзины
        productsPage.checkAmountOfCart(6);

        //Проверка корректного отображения всех кнопок REMOVE
        productsPage.checkAllRemoveButtons();

        sleep(1000);

        //Удаление всех товаров из корзины
        productsPage.removeFromCartAllProducts();

        //Проверяем счетчик корзины (не должен отображаться)
        productsPage.checkCartCounterVisibility(false);

        sleep(1000);

        //Сортировка Z-A
        productsPage.openSortMenu();
        productsPage.choseSortFromMenuByIndex(1);

        //Проверка отсортированности
        productsPage.checkSortZA();

        productsPage.openLeftSideMenu();

        sleep(1000);

        //Проверка корректного отображения левого бокового меню
        productsPage.checkLeftSideMenuContent();

        //Выход из учетной записи
        productsPage.logout();

        sleep(1000);
        BasePage.checkURL("https://www.saucedemo.com/");
        loginPage.checkLoginPage();

        sleep(1000);
    }
}
