import com.initial.InitialClass;
import com.pageobjects.Page_Account;
import com.pageobjects.Page_Authentication;
import com.pageobjects.Page_ContactUs;
import com.pageobjects.Page_Index;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestScenarios extends InitialClass {

    Page_Index page_index;
    Page_Authentication page_auth;
    Page_Account page_account;
    Page_ContactUs page_contact;

//    @Parameters({ "browser" })
//    @BeforeMethod
//    public void setup(String browserName) {
//        setDriver(browserName);
//    }

    @BeforeMethod
    public void setup() {
        setDriver();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
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
        Assert.assertFalse(page_index.validateSignOutButton());
    }

    @Test
    public void test_verify_log_in_and_out() {
        page_index = new Page_Index();
        page_auth = new Page_Authentication();
        page_account = new Page_Account();
        System.out.println("Verifying log in action");
        Assert.assertTrue(page_index.validateSignInButton());
        page_index.click_SignIn();
        page_auth.log_in();
        Assert.assertTrue(page_account.validateAccountActions());
        page_index.click_SignOut();
        Assert.assertTrue(page_index.validateSignInButton());
    }

    @Test
    public void test_verify_positive_search() {
        String search_criteria = "Blouse";
        String search_results = "Blouse";
        page_index = new Page_Index();
        System.out.println("Verifying positive search functionality");
        Assert.assertTrue(page_index.validateSearchExistence());
        page_index.performSearch(search_criteria);
        Assert.assertTrue(page_index.lookForSearchresult(search_results));
    }

    @Test
    public void test_verify_negative_search() {
        String search_criteria = "big machine";
        page_index = new Page_Index();
        System.out.println("Verifying negative search functionality");
        Assert.assertTrue(page_index.validateSearchExistence());
        page_index.performSearch(search_criteria);
        Assert.assertTrue(page_index.validateNoSearchresults());
    }

    @Test
    public void test_verify_customer_service() {
        page_index = new Page_Index();
        page_contact = new Page_ContactUs();
        System.out.println("Verifying customer service form");
        page_index.click_ContactUs();
        page_contact.fill_contact_form();
        Assert.assertTrue(page_contact.validateSuccessContactMessage());
    }
}
