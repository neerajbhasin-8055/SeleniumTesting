package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.WaitUtils;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitUtils = new WaitUtils(driver);
        driver.get("https://neerajbhasin-8055.github.io/SeleniumWebpageForPractice/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}