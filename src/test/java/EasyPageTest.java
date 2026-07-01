import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Easy;

public class EasyPageTest extends BaseTest {

    Easy easyPage;

    @BeforeClass
    public void init() {
        easyPage = new Easy(driver);
    }

    // ✅ PASS
    @Test(priority = 1, description = "Enter valid username and click submit")
    public void checkValidUsername() {
        easyPage.fillForm("Neeraj");

        String result = easyPage.getFormResult();
        Assert.assertTrue(result.contains("Neeraj"), "Username not displayed");
    }

    // ✅ PASS
    @Test(priority = 2, description = "Select Male radio button")
    public void selectRadio() {
        easyPage.selectGender();

        String result = easyPage.getGenderHobbyResult();
        Assert.assertTrue(result.contains("Male"), "Male not selected");
    }

    // ✅ PASS
    @Test(priority = 3, description = "Select Coding checkbox")
    public void selectCoding() {
        easyPage.selectCoding();

        String result = easyPage.getGenderHobbyResult();
        Assert.assertTrue(result.contains("Coding"), "Coding not selected");
    }

    // ✅ PASS
    @Test(priority = 4, description = "Select Reading checkbox")
    public void selectReading() {
        easyPage.selectReading();

        String result = easyPage.getGenderHobbyResult();
        Assert.assertTrue(result.contains("Reading"), "Reading not selected");
    }

    // ✅ PASS
    @Test(priority = 5, description = "Select country India from dropdown")
    public void selectCountry() {
        easyPage.selectCountry("India");

        String result = easyPage.getDropdownResult();
        Assert.assertTrue(result.contains("India"), "India not selected");
    }

    // ❌ FAIL (intentional)
    @Test(priority = 6, description = "Submit with empty username")
    public void emptyUsername() {
        easyPage.fillForm("");

        String result = easyPage.getFormResult();
        Assert.assertTrue(result.contains("Neeraj"), "Should fail for empty username");
    }

    // ❌ FAIL (handled properly)
    @Test(priority = 7, description = "Select invalid country")
    public void wrongCountry() {
        easyPage.selectCountry("Pakistan");

        String result = easyPage.getDropdownResult();

        Assert.assertTrue(result.contains("India"), "Dropdown should remain unchanged");
    }
}