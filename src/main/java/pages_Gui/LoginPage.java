package pages_Gui;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class LoginPage {
    SHAFT.GUI.WebDriver driver;

    /////// locators////////////
    By userNameLoc = By.id("user-name");
    By passwordLoc = By.id("password");
    By loginButtonLoc = By.id("login-button");
    By massageLoc = By.xpath("//*[@data-test=\"error\"]");

    public LoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    public void login(String userName, String Password){
        driver.element().type(userNameLoc,userName);
        driver.element().type(passwordLoc,Password);
        driver.element().click(loginButtonLoc);

    }

    public String getErrorMassage()
    {
        return driver.element().getText(massageLoc);
    }
}
//    public LoginPage(WebDriver driver){
//        this.driver = driver;
//    }
//
//    public void login(String userName, String Password){
//        driver.findElement(userNameLoc).sendKeys(userName);
//        driver.findElement(passwordLoc).sendKeys(Password);
//        driver.findElement(loginButtonLoc).click();
//        //return new HomePage(driver);
//    }
//
//    public String getErrorMassage()
//    {
//        return driver.findElement(massageLoc).getText();
//    }
