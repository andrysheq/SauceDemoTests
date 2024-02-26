import org.example.models.BaseTest;
import org.example.models.TestListener;
import org.testng.annotations.Listeners;
@Listeners(TestListener.class)
public class TestProblemUser extends BaseTest {
    @org.testng.annotations.Test
    public void test3(){
        login("problem_user","secret_sauce")
                .setupProductsPage()
                .addAndRemoveAllProducts()
                .sortZA()
                .openLeftSideMenuAndLogout();
    }
}
