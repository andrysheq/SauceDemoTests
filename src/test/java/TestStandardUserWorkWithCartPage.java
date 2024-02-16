import com.codeborne.selenide.*;
import org.example.models.Product;
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
    }
    @org.testng.annotations.Test
    public void test5(){

        loginPage = new LoginPage();
        //Проверка на корректное отображение элементов на странице авторизации
        loginPage.checkURL();
        loginPage.checkLoginPage();

        //Авторизация
        loginPage.writeUsername("standard_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();

        //Создания экземпляра страницы с товарами
        productsPage = new ProductsPage();

        //Проверка успешной авторизации и корректного отображения элементов на странице с товарами
        productsPage.checkURL();
        productsPage.checkHeadersAndButtons();
        productsPage.checkAllProducts(6);

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

        cartPage.openLeftSideMenu();

        cartPage.logout();

        loginPage = new LoginPage();
        loginPage.checkURL();
        loginPage.checkLoginPage();
    }
}
