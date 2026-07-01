package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 1. Wait for visibility (Used before typing/clicking)
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 2. Wait for clickability (Used before clicking buttons/links)
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 3. Check for existence (Using a By locator is the standard way to check presence)
    public boolean isElementPresent(By locator) {
        try {
            // Using presenceOfElementLocated to check if it's in the DOM
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }
}