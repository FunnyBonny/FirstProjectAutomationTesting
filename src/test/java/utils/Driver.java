package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Driver {
    private static WebDriver driver;

    public static WebDriver getLocalDriver() {
        if (driver == null) {
            try {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // Pentru debugging, comentează headless temporar:
                // options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();

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

    // Metodă nouă pentru a reseta driver-ul
    public static void resetDriver() {
        quitDriver();
    }
}