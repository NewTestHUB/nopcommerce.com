package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddToCartPage extends BasePage{

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//span[@class='price-value-7']")
            WebElement productCost;

    @FindBy(id = "product_enteredQuantity_7")
            WebElement txtQty;

    @FindBy(id = "add-to-cart-button-7")
            WebElement btnAddToCart;

    @FindBy(xpath = "//p[@class='content']")
            WebElement msgAddedShoppingCart;

    @FindBy(xpath = "//span[@class='close']")
            WebElement btnCancel;

    @FindBy(xpath = "//span[@class='cart-label']")
            WebElement lnkShoppingCart;

    @FindBy(xpath = "//button[text()='Go to cart']")
            WebElement btnGoToCart;

    @FindBy(xpath = "//h1") // //Shopping cart
            WebElement lblShoppingCart;

    @FindBy(xpath = "//h1")
    WebElement lblCheckout;

    //Actions

    public String getProductCost() {
        String prodCost = productCost.getText();
        return prodCost;
    }

    public void enterQty(String qty) {

        txtQty.clear();
        txtQty.sendKeys(qty);
    }

    public void clickBtnAddToCart() {
        btnAddToCart.click();
    }

    public String getMsgAddedShoppingCart() {
        String successmsg = msgAddedShoppingCart.getText();
        return successmsg;
    }

    public void clickBtnCancel() {
        btnCancel.click();
    }

    public void perforMouseHoverAction() {
        Actions act = new Actions(driver);
        act.moveToElement(lnkShoppingCart).perform();
    }

    public void clickBtnGoToCart() {
        btnGoToCart.click();
    }

    public String getLblShoppingCart() {
        String lblcart = lblShoppingCart.getText();
        return lblcart;
    }

    public String getlblCheckout() {
        String chkout = lblCheckout.getText();
        return chkout;
    }

}
