import com.codeborne.selenide.*;
import org.example.models.TestListener;
import org.example.pages.BasePage;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
@Listeners(TestListener.class)
public class TestProblemUser {

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
    public void test3(){
        loginPage = new LoginPage();
        //Проверка на корректное отображение элементов на странице авторизации
        loginPage.checkURL();
        loginPage.checkLoginPage();

        //Авторизация
        loginPage.writeUsername("problem_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();

        //Создания экземпляра страницы с товарами
        productsPage = new ProductsPage();

        //Проверка успешной авторизации и корректного отображения элементов на странице с товарами
        productsPage.checkURL();
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

        loginPage.checkURL();
        loginPage.checkLoginPage();
    }
}
