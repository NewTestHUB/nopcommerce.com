package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {

        super(driver);
    }

    // Elements
    @FindBy(xpath = "//h1")
    WebElement lblRegister;

    @FindBy(id = "gender-male")
    WebElement chkGender;

    @FindBy(name = "FirstName")
    WebElement txtFirstName;

    @FindBy(name = "LastName")
    WebElement txtLastName;

    @FindBy(name = "DateOfBirthDay")
    WebElement drpdownDOBDate;

    @FindBy(name = "DateOfBirthMonth")
    WebElement drpdownDOBMonth;

    @FindBy(name = "DateOfBirthYear")
    WebElement drpdownDOBYear;

    @FindBy(id = "Email")
    WebElement txtEmail;

    @FindBy(name = "Company")
    WebElement txtCompanyName;

    @FindBy(name = "Password")
    WebElement txtPassword;

    @FindBy(id = "ConfirmPassword")
    WebElement txtCnfPassword;

    @FindBy(id = "register-button")
    WebElement btnRegister;

    @FindBy(xpath = "//div[text()='Your registration completed']")
    WebElement msgRegCompleted;

    @FindBy(xpath = "//a[text()='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//a[text()='Log out']")
    WebElement linkLogout;

    //Actions

    public String cnfRegPage(){
        String result = lblRegister.getText();
        return result;
    }

    public void selectGender() {

        chkGender.click();
    }

    public void enterFirstName(String Fname) {

        txtFirstName.sendKeys(Fname);
    }

    public void enterLastName(String Lname) {

        txtLastName.sendKeys(Lname);
    }

    public void selectDOBDate(String date) {
        Select dobDate = new Select(drpdownDOBDate);
        dobDate.selectByValue(date);
    }

    public void selectDOBMonth(String month) {
        Select dobMonth = new Select(drpdownDOBMonth);
        dobMonth.selectByVisibleText(month);
    }

    public void selectDOBYear(String year) {
        Select dobYear = new Select(drpdownDOBYear);
        dobYear.selectByVisibleText(year);
    }

    public void enterEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void enterCompanyName(String cname) {
        txtCompanyName.sendKeys(cname);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void reenterPassword(String password) {
        txtCnfPassword.sendKeys(password);
    }

    public void clickBtnRegister() {
        btnRegister.click();
    }

    public String cnfRegMsg () {
        String regsuccessmsg = msgRegCompleted.getText();
        return regsuccessmsg;
    }

    public void clickBtnContinue() {
        btnContinue.click();
    }

    public void clickLogout() {
        linkLogout.click();
    }

}
