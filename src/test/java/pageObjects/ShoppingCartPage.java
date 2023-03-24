package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//tr[@class='order-total']//span[@class='value-summary']")
    WebElement valueShoppingCart;

    @FindBy(id = "termsofservice")
    WebElement chkbtnTermsCond;

    @FindBy(id = "checkout")
    WebElement btnCheckout;

    @FindBy(xpath = "//h1")
    WebElement msgCheckOut;

    //Actions

    public String getShoppingCartValue() {

        String valueShoCart = valueShoppingCart.getText();
        return valueShoCart;
    }

    public void clickChkTermCond() {
        chkbtnTermsCond.click();
    }

    public void clickbtnCheckOut() {
        btnCheckout.click();
    }

    public String getCheckoutMsg() {
        String checkoutMsg = msgCheckOut.getText();
        return checkoutMsg;
    }


}
