package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;
import java.time.Duration;

public class LoginTest {

    WebDriver driver;


    public static String URL = "https://demoqa.com/login";
    public static String USER_NAME = "LilyRay";
    public static String PASSWORD = "Lily@1234";




    @BeforeClass
    public void setup() {
        driver = Driver.getDriver();
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get(URL);
        LoginPom loginPom = new LoginPom(driver);
        loginPom.setUserName(USER_NAME);
        loginPom.setPassword(PASSWORD);
        String userNameLabel = loginPom.clickLogin();
        Assert.assertEquals(userNameLabel, USER_NAME);
        System.out.println();
    }

    @AfterClass
    public void closeTest() {
        driver.quit();
    }
}