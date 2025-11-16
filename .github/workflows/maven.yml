package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getRemoteDriver() {
        if (driver == null) {
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");           // headless
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--remote-allow-origins=*");

                // URL-ul Selenoid
                String selenoidUrl = "http://localhost:4444/wd/hub";

                driver = new RemoteWebDriver(new URL(selenoidUrl), options);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void resetDriver() {
        quitDriver();
    }
}
