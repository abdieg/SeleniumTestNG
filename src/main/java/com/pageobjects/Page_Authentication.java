package com.pageobjects;

import com.common.WebDriverCommonActions;
import com.initial.InitialClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_Authentication extends InitialClass {

    WebDriverCommonActions action = new WebDriverCommonActions();

    @FindBy(xpath = "//input[@id='email']")
    WebElement signin_email;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement signin_password;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement signin_button;

    public Page_Authentication() {
        PageFactory.initElements(driver, this);
    }

    public Page_Account log_in() {
        action.write(signin_email, property.getProperty("sign_in_user"));
        action.write(signin_password, property.getProperty("sign_in_pass"));
        action.click(signin_button);
        return new Page_Account();
    }

}


//span_my_account = "//span[contains(text(),'My account')]"
//account_order_history = "//ul/li/a/span[contains(text(),'Order history and details')]"
//account_credit_slips = "//ul/li/a/span[contains(text(),'My credit slips')]"
//account_addresses = "//ul/li/a/span[contains(text(),'My addresses')]"
//account_personal_information = "//ul/li/a/span[contains(text(),'My personal information')]"
//account_wishlists = "//ul/li/a/span[contains(text(),'My wishlists')]"
//account_customer_account = "//a[@title='View my customer account']"