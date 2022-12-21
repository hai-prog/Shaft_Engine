package pages;

import com.shaft.driver.SHAFT;
import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage {

    SHAFT.GUI.WebDriver driver;

    ///// locators

    By title = By.className("title");

    By addToCartBTN = By.id("add-to-cart-sauce-labs-backpack");

    By removeBTN = By.id("remove-sauce-labs-backpack");

    By cart = By.className("shopping_cart_link");
    By productName = By.xpath("(//div[@class=\"inventory_item_name\"])[1]");
    By dropdown = By.className("product_sort_container");

    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle()
    {
        return driver.element().getText(title);
    }

    public String clickOnAddToCart()
    {
        driver.element().click(addToCartBTN);
        return driver.element().getText(removeBTN);
    }

    public void goToTheCart()
    {
        driver.element().click(cart);
    }

    public String getProductName()
    {
        return driver.element().getText(productName);
    }

    public void selectFromDropDown(String option){
       // Select  = new Select(driver.findElement(dropdown));
       driver.element().select(dropdown,option);
    }


}
