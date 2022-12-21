package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class CartPage {
    SHAFT.GUI.WebDriver driver;


    By productName = By.className("inventory_item_name");

    public CartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    public String getProductName()
    {
        return driver.element().getText(productName);
    }
}
