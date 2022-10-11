package com.common;

import com.initial.InitialClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WebDriverCommonActions extends InitialClass {

    public void waitForElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(property.getProperty("timeout"))))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

//        wait.until(ExpectedConditions.stalenessOf(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void write(WebElement element, String text) {
        waitForElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        waitForElement(element);
        element.click();
    }

    public boolean isDisplayed(WebElement element) {
        waitForElement(element);
        return element.isDisplayed();
    }

    public boolean isEnabled(WebElement element) {
        waitForElement(element);
        return element.isEnabled();
    }
}
