import com.codeborne.selenide.*;
import org.example.pages.BasePage;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class TestStandardUserWorkWithCartPage {

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
    public void test5(){
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

        //Добавление одного случайного товара в корзину
        productsPage.addToCartRandom();

        //Проверка счетчика у корзины
        productsPage.checkAmountOfCart(1);

        productsPage.openCart();

        BasePage.checkURL("https://www.saucedemo.com/cart.html");

        cartPage = new CartPage();
        cartPage.checkHeaders();
        //cartPage.checkProduct()

        cartPage.deleteOneElementFromCart();

        cartPage.openLeftSideMenu();

        cartPage.logout();

        loginPage = new LoginPage();
    }
}
