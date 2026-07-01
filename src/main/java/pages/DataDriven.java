package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class DataDriven {
    private WebDriver driver;
    private WaitUtils wait;

    public DataDriven(WebDriver driver, WaitUtils wait){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="ddt-username")
    private WebElement userName;

    @FindBy(id="ddt-password")
    private  WebElement password;

    @FindBy(id ="ddt-env")
    private WebElement selectElement;

    @FindBy(xpath = "//button[text() = 'Login']")
    private WebElement loginBtn;

    public void enterUsername(String user){
        wait.waitForElementToBeVisible(userName).sendKeys(user);
    }

    public void enterPassword(String pass){
        wait.waitForElementToBeVisible(password).sendKeys(pass);
    }

    public void selectDropdown(String env){
        Select sel = new Select(wait.waitForElementToBeVisible(selectElement));
        sel.selectByVisibleText(env);
    }
    public void clickLogin(){
        wait.waitForElementToBeClickable(loginBtn).click();
    }

    public void login(String user, String pass, String env){
        enterUsername(user);
        enterPassword(pass);
        selectDropdown(env);
        clickLogin();
    }

}
