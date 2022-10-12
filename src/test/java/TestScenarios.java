import com.initial.InitialClass;
import com.pageobjects.Page_Account;
import com.pageobjects.Page_Authentication;
import com.pageobjects.Page_Index;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenarios extends InitialClass {

    Page_Index page_index;
    Page_Authentication page_auth;
    Page_Account page_account;

    @BeforeMethod
    public void setup() {
        setDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test_verify_logo() {
        page_index = new Page_Index();
        System.out.println("Verifying logo");
        Assert.assertTrue(page_index.validateLogo());
    }

    @Test
    public void test_verify_login_button() {
        page_index = new Page_Index();
        System.out.println("Verifying log in button");
        Assert.assertTrue(page_index.validateSignInButton());
    }

    @Test
    public void test_verify_log_in() {
        page_index = new Page_Index();
        page_auth = new Page_Authentication();
        page_account = new Page_Account();
        System.out.println("Verifying log in action");
        page_index.click_SignIn();
        page_auth.log_in();
        Assert.assertTrue(page_account.validateAccountActions());
    }
}
