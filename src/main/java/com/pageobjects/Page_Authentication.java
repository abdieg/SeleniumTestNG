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
        PageFactory.initElements(getDriver(), this);
    }

    public void log_in() {
        action.write(signin_email, property.getProperty("sign_in_user"));
        action.write(signin_password, property.getProperty("sign_in_pass"));
        action.click(signin_button);
    }

}