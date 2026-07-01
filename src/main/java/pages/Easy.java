package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class Easy {

    WebDriver driver;
    WaitUtils wait;

    public Easy(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // ========= LOCATORS =========

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "submit-btn")
    private WebElement submitBtn;

    @FindBy(xpath = "//input[@value='Male']")
    private WebElement maleRadio;

    @FindBy(xpath = "//input[@value='Coding']")
    private WebElement codingCheckbox;

    @FindBy(xpath = "//input[@value='Reading']")
    private WebElement readingCheckbox;

    @FindBy(id = "country-select")
    private WebElement countryDropdown;

    @FindBy(id = "res-1")
    private WebElement resultBox1;

    @FindBy(id = "res-2")
    private WebElement resultBox2;

    @FindBy(id = "res-3")
    private WebElement resultBox3;


    // ========= ACTION METHODS =========

    public void enterUsername(String name) {
        wait.waitForElementToBeVisible(username).clear();
        username.sendKeys(name);
    }

    public void clickSubmit() {
        wait.waitForElementToBeClickable(submitBtn).click();
    }

    public void fillForm(String name) {
        enterUsername(name);
        clickSubmit();
    }

    public void selectGender() {
        wait.waitForElementToBeClickable(maleRadio).click();
    }

    public void selectCoding() {
        wait.waitForElementToBeClickable(codingCheckbox).click();
    }

    public void selectReading() {
        wait.waitForElementToBeClickable(readingCheckbox).click();
    }

    public void selectCountry(String country) {
        Select select = new Select(wait.waitForElementToBeVisible(countryDropdown));
        select.selectByVisibleText(country);
    }


    // ========= VALIDATION METHODS =========

    public String getFormResult() {
        return wait.waitForElementToBeVisible(resultBox1).getText();
    }

    public String getGenderHobbyResult() {
        return wait.waitForElementToBeVisible(resultBox2).getText();
    }

    public String getDropdownResult() {
        return wait.waitForElementToBeVisible(resultBox3).getText();
    }
}