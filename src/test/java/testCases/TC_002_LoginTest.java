package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

@Test
    public void test_LoginTest() {

    logger.info("*** Starting TC_002_LoginTest ***");

    try {
        HomePage hp = new HomePage(driver);
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);

        Assert.assertEquals(lp.getSigninMsg(), "Welcome, Please Sign In!");

        lp.setTxtEmail(rb.getString("email"));
//        lp.setTxtEmail("test003@email.com");
        lp.setTxtPassword(rb.getString("password"));
        lp.setBtnLogin();

        System.out.println(lp.checkMyAccount());
        Assert.assertEquals(lp.checkMyAccount(), "My account");

        MyAccountPage map = new MyAccountPage(driver);
        System.out.println(map.cnfLblfMyAccount());
        map.clickBtnLogout();

        captureScreen("Homepage");

    } catch (Exception e) {
        Assert.fail(e.getMessage());
    }

    logger.info("*** Finished TC_002_LoginTest ***");

}
}
