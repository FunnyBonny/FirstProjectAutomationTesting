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
                WebDriverManager.chromedriver().clearDriverCache().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");          // headless Chrome modern
                options.addArguments("--no-sandbox");            // necesar pe GitHub Actions
                options.addArguments("--disable-dev-shm-usage"); // rezolvă probleme cu memoria
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080"); // dimensiune fereastră

                driver = new ChromeDriver(options);

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
