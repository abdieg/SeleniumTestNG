package com.pageobjects;

import com.common.WebDriverCommonActions;
import com.initial.InitialClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_Account extends InitialClass {

    WebDriverCommonActions action = new WebDriverCommonActions();

    @FindBy(xpath = "//span[contains(text(),'My account')]")
    WebElement my_account_text;

    @FindBy(xpath = "//ul/li/a/span[contains(text(),'Order history and details')]")
    WebElement account_order_history;

    @FindBy(xpath = "//ul/li/a/span[contains(text(),'My credit slips')]")
    WebElement account_credit_slips;

    @FindBy(xpath = "//ul/li/a/span[contains(text(),'My addresses')]")
    WebElement account_addresses;

    @FindBy(xpath = "//ul/li/a/span[contains(text(),'My personal information')]")
    WebElement account_personal_information;

    @FindBy(xpath = "//ul/li/a/span[contains(text(),'My wishlists')]")
    WebElement account_wishlists;

    @FindBy(xpath = "//a[@title='View my customer account']")
    WebElement account_customer_account;

    public Page_Account() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean validateAccountActions() {
        action.waitForElement(my_account_text);
        action.waitForElement(account_order_history);
        action.waitForElement(account_credit_slips);
        action.waitForElement(account_addresses);
        action.waitForElement(account_personal_information);
        action.waitForElement(account_wishlists);
        action.waitForElement(account_customer_account);

        return action.isDisplayed(my_account_text) &&
                action.isDisplayed(account_order_history) &&
                action.isDisplayed(account_credit_slips) &&
                action.isDisplayed(account_addresses) &&
                action.isDisplayed(account_personal_information) &&
                action.isDisplayed(account_wishlists) &&
                action.isDisplayed(account_customer_account);
    }

}

