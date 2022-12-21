import com.shaft.driver.SHAFT;
import org.python.modules._locale._locale;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    LoginPage loginPage;

    @BeforeMethod
    public void methodSetUp()
    {   driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://www.saucedemo.com/");
        driver.browser().maximizeWindow();
        testData = new SHAFT.TestData.JSON("testData.json");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessfully()
    {
       loginPage.login(testData.getTestData("credentials.standardUser"),testData.getTestData("credentials.validPassword"));
       driver.verifyThat().browser().url().isEqualTo("https://www.saucedemo.com/inventory.html").perform();
    }

    @Test
    public void testLockedUser()
    {
        loginPage.login(testData.getTestData("credentials.lockedOutUser"),testData.getTestData("credentials.validPassword"));

        SHAFT.Validations.assertThat().object(loginPage.getErrorMassage()).isEqualTo(testData.getTestData("messages.LockedOutUser"));
        //Assert.assertEquals(loginPage.getErrorMassage(),testData.getTestData("messages.LockedOutUser"));
    }

    @Test
    public void testFailToLoginWithoutUserName()
    {
        loginPage.login("",testData.getTestData("credentials.validPassword"));
        Assert.assertEquals(loginPage.getErrorMassage(),testData.getTestData("messages.requiredUserName"));
    }

    @Test
    public void testFailToLoginWithoutPassword()
    {
        loginPage.login(testData.getTestData("credentials.lockedOutUser"),"");
        Assert.assertEquals(loginPage.getErrorMassage(),testData.getTestData("messages.requiredPassword"));
    }

    @AfterMethod
    public void methodTearDown()
    {
        driver.quit();
    }

}
