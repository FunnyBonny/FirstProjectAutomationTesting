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

public class FormTest {

    private WebDriver driver;
    private static final String URL = "https://demoqa.com/automation-practice-form";

    // Date de test
    private static final String FIRST_NAME = "Lia";
    private static final String LAST_NAME = "Railean";
    private static final String EMAIL = "ceiti.liarailean@gmail.com";
    private static final String GENDER = "Female";
    private static final String USER_NUMBER = "0123456789";
    private static final String DATE_OF_BIRTH = "31 October,2006";
    private static final String SUBJECT = "Maths";
    private static final String HOBBY = "Reading";
    private static final String ADDRESS = "Chisinau Buiucani";
    private static final String STATE = "Haryana";
    private static final String CITY = "Panipat";

    @BeforeClass
    public void setup() {
        driver = Driver.getDriver();
    }

    @Test
    public void fillFormTest() {
        driver.get(URL);

        // Ascunde eventualele reclame
        try {
            WebElement ad = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", ad);
        } catch (Exception ignored) {}

        // Folosim Page Object
        FormPom form = new FormPom(driver);

        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setEmail(EMAIL);
        form.setFemaleGender();
        form.setMobile(USER_NUMBER);
        form.setDateOfBirth(DATE_OF_BIRTH);
        form.setSubjects(SUBJECT);
        form.setHobbyReading();
        form.setAddress(ADDRESS);
        form.setState(STATE);
        form.setCity(CITY);
        form.clickSubmit();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        // Așteaptă modalul
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        // Verificări
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[1]/*[2]")).getText(), FIRST_NAME + " " + LAST_NAME);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[2]/*[2]")).getText(), EMAIL);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[3]/*[2]")).getText(), GENDER);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[4]/*[2]")).getText(), USER_NUMBER);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[5]/*[2]")).getText(), DATE_OF_BIRTH);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[6]/*[2]")).getText(), SUBJECT);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[7]/*[2]")).getText(), HOBBY);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody//tr[10]/*[2]")).getText(), STATE + " " + CITY);
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
