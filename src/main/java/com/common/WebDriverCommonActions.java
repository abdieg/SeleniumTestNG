package com.common;

import com.initial.InitialClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Random;

public class WebDriverCommonActions extends InitialClass {

    public void waitForElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
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

    public void hover(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
//        actions.moveToElement(element);
//        actions.click().build().perform();
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    public Integer getRandomNumber(int max) {
        Random rand = new Random();
        int n = rand.nextInt(max);
        return n;
    }

}
