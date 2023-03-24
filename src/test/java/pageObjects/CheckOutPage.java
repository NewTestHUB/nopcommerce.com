package pageObjects;

import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.ExtentReportManager;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement drpdownCountries;

    @FindBy(id = "BillingNewAddress_City")
    WebElement txtCity;

    @FindBy(name = "BillingNewAddress.Address1")
    WebElement txtAddress1;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement txtZipCode;

    @FindBy(name = "BillingNewAddress.PhoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(name = "save")
    WebElement btnContinue1;

    @FindBy(xpath = "//button[@class='button-1 shipping-method-next-step-button']")
    WebElement btnContinue3;

    @FindBy(xpath = "//button[@class='button-1 payment-method-next-step-button']")
    WebElement btnContinue4;

    @FindBy(xpath = "//button[@class='button-1 payment-info-next-step-button']")
    WebElement btnContinue5;

    @FindBy(xpath = "//span[@class='value-summary']//strong[contains(text(),'$2,700.00')]")
    WebElement totalCartValue;

    @FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
    WebElement btnConfirm;

    @FindBy(xpath = "//h1[normalize-space()='Thank you']")
    WebElement msgThankyou;

    @FindBy(xpath = "//div[@class='title']//strong[text()='Your order has been successfully processed!']")
    WebElement msgOrderConfirm;

    @FindBy(xpath = "//button[text()='Continue']")
    WebElement btnContinue6;

    //Actions

    public void selectCountry(String Country) {

        Select country = new Select(drpdownCountries);
        country.selectByVisibleText(Country);
    }

    public void enterCity(String City) {
        txtCity.sendKeys(City);
    }

    public void enterAddr1(String Addr1) {

        txtAddress1.sendKeys(Addr1);
    }

    public void enterZipCode(String ZipCode) {
        txtZipCode.sendKeys(ZipCode);
    }

    public void enterPhoneNum(String PhoneNo) {
        txtPhoneNumber.sendKeys(PhoneNo);
    }

    public void clickContinue1() {
        btnContinue1.click();
    }

    public void clickContinue3() {
        btnContinue3.click();
    }

    public void clickContinue4() {
        btnContinue4.click();
    }
    public void clickContinue5() {
        btnContinue5.click();
    }
    public String getTotalCartValue() {
        String TotalValue = totalCartValue.getText();
        return TotalValue;
    }

    public void clickBtnConfirm() {
        btnConfirm.click();
    }
    public String getMsgThankyou() {
        String thankyoumsg = msgThankyou.getText();
        return thankyoumsg;
    }

    public String msgOrderConfirm() {
        String orderConfirmMsg = msgOrderConfirm.getText();
        return orderConfirmMsg;
    }

    public void clickFinalButtonContinue() {
        btnContinue6.click();
    }
}
