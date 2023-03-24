package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//a[@class='ico-account']")
    WebElement lblMyAccount;

    @FindBy(xpath = "//a[@class='ico-logout']")
    WebElement btnLogout;

    //Actions

    public boolean cnfLblfMyAccount() {
        try {
            return lblMyAccount.isDisplayed();
        }catch (Exception e) {
            return (false);
        }
    }

    public void clickBtnLogout() {
        btnLogout.click();
    }
}
