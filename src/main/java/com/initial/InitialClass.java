package com.initial;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class InitialClass {

    public static Properties property;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void loadProperties() {
        try {
            property = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void setDriver(String browserName) { //Uncomment for TestNG purposes
      public static void setDriver() { //Uncomment to execute unit tests
        String browserName = property.getProperty("browser"); //Uncomment to execute unit tests

        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", property.getProperty("firefox_driver_location"));
            driver.set(new FirefoxDriver());
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", property.getProperty("edge_driver_location"));
            driver.set(new EdgeDriver());
        }
        else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", property.getProperty("chrome_driver_location"));
            driver.set(new ChromeDriver());
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(property.getProperty("timeout"))));
        getDriver().get(property.getProperty("app-url"));
        getDriver().manage().window().maximize();
//        getDriver().manage().window().setSize(new Dimension(Integer.parseInt(property.getProperty("window_width")), Integer.parseInt(property.getProperty("window_height"))));

    }

    public void sleep(int timeout) throws InterruptedException {
        Thread.sleep(timeout);
    }
}
