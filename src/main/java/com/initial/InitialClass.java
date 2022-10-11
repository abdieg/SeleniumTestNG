package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {

    public static Properties property;
    public static WebDriver driver;

    public void loadProperties() {
        try {
            property = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDriver() {
        String browserName = property.getProperty("browser");

        if (browserName.contains("firefox")) {
            System.setProperty("webdriver.gecko.driver", property.getProperty("firefox_driver_location"));
            driver = new FirefoxDriver();
        }
    }
}
