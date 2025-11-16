package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    private static WebDriver driver;

    /**
     * Returnează o instanță de WebDriver local (Chrome headless pentru CI/CD)
     */
    public static WebDriver getLocalDriver() {
        if (driver == null) {
            try {
                // Pregătim driver-ul Chrome
                WebDriverManager.chromedriver().clearDriverCache().setup();

                ChromeOptions options = new ChromeOptions();

                // Opțiuni recomandate pentru rularea pe CI (GitHub Actions)
                options.addArguments("--headless=new");          // headless Chrome modern
                options.addArguments("--no-sandbox");            // necesar pe GitHub Actions
                options.addArguments("--disable-dev-shm-usage"); // rezolvă probleme cu memoria
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080"); // pentru testele care au nevoie de dimensiune

                driver = new ChromeDriver(options);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    /**
     * Închide driver-ul și resetează instanța
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Resetează driver-ul (închide și recreează la următoarea cerere)
     */
    public static void resetDriver() {
        quitDriver();
    }
}
