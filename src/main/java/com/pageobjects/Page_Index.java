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
}