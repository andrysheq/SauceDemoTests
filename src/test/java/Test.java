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
    public void test5(){

        loginPage.writeUsername("standard_user");
        loginPage.writePassword("secret_sauce");
        loginPage.auth();

        productsPage = new ProductsPage();

        productsPage.addToCart();

        productsPage.openCart();

        cartPage = new CartPage();

        cartPage.deleteFromCart();

        cartPage.openLeftSideMenu();

        cartPage.logout();

        sleep(3000);
    }
}
