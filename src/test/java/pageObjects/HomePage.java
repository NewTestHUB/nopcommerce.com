package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(linkText = "Register")
    WebElement lnkRegister;

    @FindBy(xpath = "//a[text()='Log in']")
    WebElement lnkLogin;

    @FindBy(name = "q") //books
    WebElement boxSearch;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement btnSearch;

    //Actions
    public void clickRegister() {
        lnkRegister.click();
    }

    public void clickLogin() {

        lnkLogin.click();
    }

    public void enterSearchProduct(String productName) {

        boxSearch.sendKeys(productName);
    }

    public void clickBtnSearch() {
        btnSearch.click();
    }


}
