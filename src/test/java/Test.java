import com.codeborne.selenide.*;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class Test {

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
        loginPage = new LoginPage();

    }

    @org.testng.annotations.Test
    public void test1(){
        //Проверка на корректное отображение элементов на странице авторизации
        loginPage.checkLoginPage();

        //Авторизация
        loginPage.writeUsername("standard_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();

        //Создания экземпляра страницы с товарами
        productsPage = new ProductsPage();

        //Проверка корректного отображения элементов на странице с товарами
        productsPage.checkHeadersAndButtons();
        productsPage.checkAllProducts(6);

        //Добавление всех товаров в корзину
        //productsPage.addToCartAllProducts();

        //Проверка счетчика у корзины
        //productsPage.checkAmountOfCart(6);

        //Удаление всех товаров из корзины
        //productsPage.removeFromCartAllProducts();

        //Сортировка Z-A
        productsPage.openSortMenu();
        productsPage.choseSortFromMenuByIndex(1);

        //Выход из учетной записи
        productsPage.openLeftSideMenu();
        productsPage.logout();

        sleep(3000);
    }

//    @org.testng.annotations.Test
//    public void test2(){
//
//        loginPage.writeUsername("locked_out_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//
//        productsPage = new ProductsPage();
//
//        productsPage.addToCartAllProducts();
//
//        productsPage.checkAmountOfCart(6);
//
//        productsPage.removeFromCartAllProducts();
//
//        productsPage.choseSortFromMenuByIndex(1);
//        productsPage.openLeftSideMenu();
//        productsPage.logout();
//
//        sleep(3000);
//    }
//
//    @org.testng.annotations.Test
//    public void test3(){
//
//        loginPage.writeUsername("problem_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//
//        productsPage = new ProductsPage();
//
//        productsPage.addToCartAllProducts();
//
//        productsPage.checkAmountOfCart(6);
//
//        productsPage.removeFromCartAllProducts();
//
//        productsPage.choseSortFromMenuByIndex(1);
//        productsPage.openLeftSideMenu();
//        productsPage.logout();
//
//        sleep(3000);
//    }
//
//    @org.testng.annotations.Test
//    public void test4(){
//
//        loginPage.writeUsername("performance_glitch_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//
//        productsPage = new ProductsPage();
//
//        productsPage.addToCartAllProducts();
//
//        productsPage.checkAmountOfCart(6);
//
//        productsPage.removeFromCartAllProducts();
//
//        productsPage.choseSortFromMenuByIndex(1);
//        productsPage.openLeftSideMenu();
//        productsPage.logout();
//
//        sleep(3000);
//    }
//    @org.testng.annotations.Test
//    public void test5(){
//
//        loginPage.writeUsername("standard_user");
//        loginPage.writePassword("secret_sauce");
//        loginPage.auth();
//
//        productsPage = new ProductsPage();
//
//        productsPage.checkAllProducts(6);
//
//        productsPage.addToCart();
//
//        productsPage.checkAmountOfCart(1);
//
//        productsPage.openCart();
//
//        cartPage = new CartPage();
//
//        cartPage.deleteFromCart();
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
