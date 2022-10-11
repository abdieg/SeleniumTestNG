package com.pageobjects;

import com.common.WebDriverCommonActions;
import com.initial.InitialClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_Index extends InitialClass {

    WebDriverCommonActions action = new WebDriverCommonActions();

    @FindBy(xpath = "//img[contains(@class,'logo')]")
    WebElement main_logo;

    @FindBy(xpath = "//a[@class='login'][contains(text(),'Sign in')]")
    WebElement topbar_button_signin;

    @FindBy(xpath = "//a[@class='logout'][contains(text(),'Sign out')]")
    WebElement topbar_button_signout;

    public Page_Index() {
        PageFactory.initElements(driver, this);
    }

    public boolean validateLogo() {
        action.waitForElement(main_logo);
        return action.isDisplayed(main_logo);
    }

    public boolean validateSignInButton() {
        action.waitForElement(topbar_button_signin);
        if (action.isDisplayed(topbar_button_signin) && action.isEnabled(topbar_button_signin)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void click_SignOut() {
        action.click(topbar_button_signout);
    }

    public void click_SignIn() {
        action.click(topbar_button_signin);
    }
}


//span_my_account = "//span[contains(text(),'My account')]"
//account_order_history = "//ul/li/a/span[contains(text(),'Order history and details')]"
//account_credit_slips = "//ul/li/a/span[contains(text(),'My credit slips')]"
//account_addresses = "//ul/li/a/span[contains(text(),'My addresses')]"
//account_personal_information = "//ul/li/a/span[contains(text(),'My personal information')]"
//account_wishlists = "//ul/li/a/span[contains(text(),'My wishlists')]"
//account_customer_account = "//a[@title='View my customer account']"