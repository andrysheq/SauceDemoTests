import org.example.models.BaseTest;
import org.example.pages.LoginPage;

public class TestStandardUserWorkWithCartPage extends BaseTest {
    @org.testng.annotations.Test
    public void test5(){
        login("standard_user","secret_sauce")
                .setupProductsPage()
                .addToCartAndDeleteRandomProduct()
                .openLeftSideMenuAndLogout();
    }
    @Override
    public BaseTest openLeftSideMenuAndLogout(){
        cartPage.openLeftSideMenu();

        cartPage.logout();

        loginPage = new LoginPage();
        loginPage.checkURL();
        loginPage.checkLoginPage();
        return this;
    }
}
