package com.initial;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

    public static void setDriver(String browserName) { //Uncomment for TestNG purposes
//      public static void setDriver() { //Uncomment to execute unit tests
//        String browserName = property.getProperty("browser"); //Uncomment to execute unit tests

//        if (browserName.equalsIgnoreCase("firefox")) {
//            System.setProperty("webdriver.gecko.driver", property.getProperty("firefox_driver_location"));
//            driver.set(new FirefoxDriver());
//        }
//        else if (browserName.equalsIgnoreCase("edge")) {
//            System.setProperty("webdriver.edge.driver", property.getProperty("edge_driver_location"));
//            driver.set(new EdgeDriver());
//        }
//        else if (browserName.equalsIgnoreCase("chrome")) {
//            System.setProperty("webdriver.chrome.driver", property.getProperty("chrome_driver_location"));
//            driver.set(new ChromeDriver());
//        }

        DesiredCapabilities capabilities = new DesiredCapabilities(); //Uncomment when executing through Selenium GRID
        if (browserName.equalsIgnoreCase("firefox")) {
            capabilities.setCapability("browserName", "firefox");
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            capabilities.setCapability("browserName", "MicrosoftEdge");
        }
        else if (browserName.equalsIgnoreCase("chrome")) {
            capabilities.setCapability("browserName", "chrome");
        }
        else if (browserName.equalsIgnoreCase("safari")) {
            capabilities.setCapability("browserName", "safari");
        }

        try {
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(property.getProperty("timeout"))));
        getDriver().get(property.getProperty("app-url"));
//        getDriver().manage().window().maximize();
        getDriver().manage().window().setSize(new Dimension(Integer.parseInt(property.getProperty("window_width")), Integer.parseInt(property.getProperty("window_height"))));

    }

}
