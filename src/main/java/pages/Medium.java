package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class Medium{

    WebDriver driver;
    WaitUtils wait;
    Actions action;

    public Medium(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Simple Alert']")
    private WebElement simpleAlertBtn;

    @FindBy(xpath = "//button[text()='Confirm Alert']")
    private WebElement confirmAlertBtn;

    @FindBy(xpath = "//button[text()='Prompt Alert']")
    private WebElement promptAlertBtn;

    @FindBy(className = "tooltip")
    private WebElement hoverElement;

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    @FindBy(id = "custom-drop-btn")
    private WebElement dropdownBtn;

    @FindBy(id = "myDropdown")
    private WebElement dropdownList;

    @FindBy(id = "new-tab-btn")
    private WebElement newTabBtn;

    @FindBy(id = "practice-iframe")
    private WebElement iframe;

    @FindBy(id = "iframe-button")
    private WebElement iframeButton;

    public void handleSimpleAlert() {
        wait.waitForElementToBeClickable(simpleAlertBtn).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void handleConfirmAlert() {
        wait.waitForElementToBeClickable(confirmAlertBtn).click();
        driver.switchTo().alert().accept();
    }

    public void handlePromptAlert(String text) {
        wait.waitForElementToBeClickable(promptAlertBtn).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    public void hover() {
        action.moveToElement(wait.waitForElementToBeVisible(hoverElement)).perform();
    }

    public void dragAndDrop() {
        action.dragAndDrop(
                wait.waitForElementToBeVisible(draggable),
                wait.waitForElementToBeVisible(droppable)
        ).perform();
    }

    public void selectCustomDropdown(String value) {
        wait.waitForElementToBeClickable(dropdownBtn).click();
        dropdownList.findElement(By.xpath(".//li[text()='" + value + "']")).click();
    }

    public void handleNewTab() {
        String parent = driver.getWindowHandle();

        wait.waitForElementToBeClickable(newTabBtn).click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    public void handleIframe() {
        driver.switchTo().frame(wait.waitForElementToBeVisible(iframe));
        wait.waitForElementToBeClickable(iframeButton).click();
        driver.switchTo().defaultContent();
    }
}