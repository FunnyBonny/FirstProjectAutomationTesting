package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPom {

    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(xpath = "//*[@id='userName']")
    WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    WebElement password;

    @FindBy(xpath = "//*[@id='login']")
    WebElement login;

    @FindBy(xpath = "//*[@id='userName-value']")
    WebElement userNameLabel;

    public LoginPom(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public String clickLogin() {
        login.click();
        WebElement label = fluentWait(driver, By.id("userName-value"));
        return label.getText();
    }

    public void setPassword(String passParam) {
        password.clear();
        password.sendKeys(passParam);
    }

    public void setUserName(String userNameParam) {
        explicitWait(driver, ExpectedConditions.visibilityOf(userName), 10);
        userName.clear();
        userName.sendKeys(userNameParam);
    }

    // Metoda custom pentru Fluent Wait
    public WebElement fluentWait(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Poți păstra metoda explicitWait dacă există deja
    public void explicitWait(WebDriver driver, ExpectedCondition<?> condition, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(condition);
    }
}
