package com.pageobjects;

import com.common.WebDriverCommonActions;
import com.initial.InitialClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Page_Index extends InitialClass {

    WebDriverCommonActions action = new WebDriverCommonActions();

    @FindBy(xpath = "//img[contains(@class,'logo')]")
    WebElement main_logo;

    @FindBy(xpath = "//a[@class='login'][contains(text(),'Sign in')]")
    WebElement topbar_button_signin;

    @FindBy(xpath = "//a[@class='logout'][contains(text(),'Sign out')]")
    WebElement topbar_button_signout;

    @FindBy(xpath = "//a[@title='Contact Us']")
    WebElement topbar_contact_us;

    @FindBy(xpath = "//input[@name='search_query']")
    WebElement search_box;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement search_button;

    @FindBy(xpath = "//div[@class='product-container']//a[@class='product-name']")
    List<WebElement> search_results;

    @FindBy(xpath = "//p[@class='alert alert-warning'][contains(text(),'No results were found')]")
    WebElement search_alert_no_results;

    public Page_Index() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean validateLogo() {
        action.waitForElement(main_logo);
        return action.isDisplayed(main_logo);
    }

    public boolean validateSignInButton() {
        action.waitForElement(topbar_button_signin);
        return action.isDisplayed(topbar_button_signin) && action.isEnabled(topbar_button_signin);
    }

    public boolean validateSignOutButton() {
        action.waitForElement(topbar_button_signout);
        return action.isDisplayed(topbar_button_signout) && action.isEnabled(topbar_button_signout);
    }

    public void click_SignOut() {
        action.click(topbar_button_signout);
    }

    public void click_SignIn() {
        action.click(topbar_button_signin);
    }

    public void click_ContactUs() {
        action.click(topbar_contact_us);
    }

    public boolean validateSearchExistence() {
        action.waitForElement(search_box);
        action.waitForElement(search_button);
        return action.isDisplayed(search_box) && action.isEnabled(search_button);
    }
    public void performSearch(String text) {
        action.write(search_box, text);
        action.click(search_button);
    }

    public boolean lookForSearchresult(String text) {
        System.out.println("Number of elements displayed in search: " + search_results.size());
        for (WebElement search_result : search_results) {
            System.out.println("Text of each element: " + search_result.getText());
            if (search_result.getText().contains(text)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean validateNoSearchresults() {
        return action.isDisplayed(search_alert_no_results);
    }
}