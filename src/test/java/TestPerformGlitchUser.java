import org.example.models.BaseTest;

public class TestPerformGlitchUser extends BaseTest {
    @org.testng.annotations.Test
    public void test4(){
        login("performance_glitch_user","secret_sauce")
                .setupProductsPage()
                .addAndRemoveAllProducts()
                .sortZA()
                .openLeftSideMenuAndLogout();
    }
}
