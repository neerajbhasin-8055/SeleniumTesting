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

    public void fillForm(String name) {
        wait.waitForElementToBeVisible(username).sendKeys(name);
        wait.waitForElementToBeClickable(submitBtn).click();
    }

    public void selectGenderAndHobbies() {
        wait.waitForElementToBeClickable(maleRadio).click();
        wait.waitForElementToBeClickable(codingCheckbox).click();
        wait.waitForElementToBeClickable(readingCheckbox).click();
    }

    public void selectCountry(String country) {
        Select select = new Select(wait.waitForElementToBeVisible(countryDropdown));
        select.selectByVisibleText(country);
    }
}