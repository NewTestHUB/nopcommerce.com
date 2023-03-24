package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import testBase.BaseClass;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    //Elements
    @FindBy(xpath = "//h1")
//    @FindBy(className = "page-title")
//    @FindBy(xpath = "//h1[text()='Welcome, Please Sign In!']")
    WebElement lblSignin;

    @FindBy(name = "Email")
    WebElement txtEmail;

    @FindBy(name = "Password")
    WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement btnLogin;

//    @FindBy(className = "ico-account")
    @FindBy(xpath = "//a[@class='ico-account']")
    WebElement myAccount;

//    @FindBy(className = "ico-logout")
    @FindBy(linkText = "Log out")
//    @FindBy(xpath = "//a[@class='ico-logout']")
    WebElement lnkLogout;

    //Actions

    public String getSigninMsg() {
      String result = lblSignin.getText();
      return result;
    }

    public void setTxtEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void setBtnLogin () {
        btnLogin.click();
    }

    public String checkMyAccount () {
        String result = myAccount.getText();
        return result;
    }

}
