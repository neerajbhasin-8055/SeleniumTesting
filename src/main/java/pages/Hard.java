package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class Hard {

    WebDriver driver;
    WaitUtils wait;

    public Hard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "datepicker-input")
    private WebElement dateInput;

    @FindBy(id = "month-year-display")
    private WebElement monthYear;

    @FindBy(id = "next-month")
    private WebElement nextMonthBtn;

    @FindBy(id = "days-grid")
    private WebElement daysGrid;

    @FindBy(id = "start-wait-btn")
    private WebElement startWaitBtn;

    @FindBy(id = "hidden-target-btn")
    private WebElement hiddenBtn;

    @FindBy(id = "shadow-host")
    private WebElement shadowHost;

    @FindBy(tagName = "a")
    private List<WebElement> links;

    public void selectDate(String targetMonth, String day) {
        wait.waitForElementToBeClickable(dateInput).click();

        while (true) {
            String current = wait.waitForElementToBeVisible(monthYear).getText();
            if (current.equals(targetMonth)) break;
            wait.waitForElementToBeClickable(nextMonthBtn).click();
        }

        daysGrid.findElement(By.xpath(".//div[text()='" + day + "']")).click();
    }

    public void handleWait() {
        wait.waitForElementToBeClickable(startWaitBtn).click();
        wait.waitForElementToBeClickable(hiddenBtn).click();
    }

    public void clickShadowDomButton() {
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.id("shadow-target-btn")).click();
    }

    public List<WebElement> getLinks() {
        return links;
    }
}