package gui.login_tests.product_tests;

import com.shaft.driver.SHAFT;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_Gui.CartPage;
import pages_Gui.LoginPage;
import pages_Gui.ProductsPage;

public class ProductsTests {
    SHAFT.GUI.WebDriver driver;

    SHAFT.TestData.JSON testData;
    LoginPage loginPage;
    ProductsPage productsPage ;
    CartPage cartPage;

    @BeforeMethod
    public void methodSetUp()
    {   driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://www.saucedemo.com/");
        driver.browser().maximizeWindow();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        testData = new SHAFT.TestData.JSON("testData.json");
        loginPage.login(testData.getTestData("credentials.standardUser"),testData.getTestData("credentials.validPassword"));
    }

    @Test
    public void testAddToCartFunctionality()
    {
        String productName = productsPage.getProductName();
        Assert.assertTrue( productsPage.clickOnAddToCart().equalsIgnoreCase("remove"));
        productsPage.goToTheCart();
        Assert.assertEquals(productName,cartPage.getProductName());
    }


    @AfterMethod
    public void methodTearDown()
    {
        driver.quit();
    }
}
