import com.initial.InitialClass;
import com.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestScenarios extends InitialClass {

    Page_Index page_index;
    Page_Authentication page_auth;
    Page_Account page_account;
    Page_ContactUs page_contact;
    Page_Category page_category;
    Page_CartSummary page_cartsummary;

    @Parameters ({ "browser" })
    @BeforeMethod (alwaysRun = true)
    public void setup(String browserName) { //Uncomment this method if TestNG needs to be used
        setDriver(browserName);
    }

//    @BeforeMethod
//    public void setup() { //Uncomment this method if single test needs to be executed
//        setDriver();
//    }

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

    @Test
    public void test_verify_cart_quantities_colors_sizes() {
        page_index = new Page_Index();
        page_category = new Page_Category();
        page_cartsummary = new Page_CartSummary();
        System.out.println("Verifying that we are able to add different quantities, colors and sizes to the cart");
        page_index.click_WomenMenu();
        page_category.add_random_items_in_this_category_to_cart();
        page_index.click_DressesMenu();
        page_category.add_random_items_in_this_category_to_cart();
        page_index.click_TshirtsMenu();
        page_category.add_random_items_in_this_category_to_cart();
        page_index.checkoutFloatingCart();

        int totalProducts = page_cartsummary.getNumberOfProducts();
        Assert.assertEquals(3, totalProducts);
        Integer[] old_quantities = page_cartsummary.getProductQuantities();
        for (int i = 0; i < totalProducts; i++) {
            page_cartsummary.click_QtyAdd(i);
            page_cartsummary.click_QtyAdd(i);
        }
        Integer[] new_quantities = page_cartsummary.getProductQuantities();

        for (int i = 0; i < new_quantities.length; i++) {
            Assert.assertEquals(new_quantities[i], old_quantities[i]+2);
        }
    }

    @Test
    public void test_verify_wishlist() {
        page_index = new Page_Index();
        page_category = new Page_Category();
        page_auth = new Page_Authentication();
        page_account = new Page_Account();
        String search_criteria1 = "faded short";
        String search_results1 = "Faded Short Sleeve T-shirts";
        String search_criteria2 = "printed chiffon dress";
        String search_results2 = "Printed Chiffon Dress";
        System.out.println("Verifying wishlist functionality");

        Assert.assertTrue(page_index.validateSearchExistence());
        Assert.assertTrue(page_index.validateSignInButton());
        page_index.click_SignIn();
        page_auth.log_in();
        Assert.assertTrue(page_account.validateAccountActions());
        page_account.click_MyWishlists();
        if (page_account.doesWishlistExist())
            page_account.click_WishlistDelete();

        page_index.performSearch(search_criteria1);
        Assert.assertTrue(page_index.lookForSearchresult(search_results1));
        page_category.add_random_items_in_this_category_to_wishlist();

        page_index.performSearch(search_criteria2);
        Assert.assertTrue(page_index.lookForSearchresult(search_results2));
        page_category.add_random_items_in_this_category_to_wishlist();

        page_index.click_CustomerAccount();
        page_account.click_MyWishlists();
        page_account.click_OpenWishlistDetails();
        Assert.assertEquals(2, page_account.getNumberOfProductsInWishlist());
        page_account.click_WishlistDelete();
    }
}
