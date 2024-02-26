import org.example.models.BaseTest;

public class TestStandardUser extends BaseTest {
    @org.testng.annotations.Test
    public void test1(){
        login("standard_user","secret_sauce")
                .setupProductsPage()
                .addAndRemoveAllProducts()
                .sortZA()
                .openLeftSideMenuAndLogout();
    }
}
