import org.example.models.BaseTest;

public class TestLockedOutUser extends BaseTest {
    @org.testng.annotations.Test
    public void test2(){
        login("locked_out_user","secret_sauce")
                .setupProductsPage()
                .addAndRemoveAllProducts()
                .sortZA()
                .openLeftSideMenuAndLogout();
    }
}
