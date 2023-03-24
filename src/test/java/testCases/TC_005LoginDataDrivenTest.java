package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_005LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void test_LoginDataDriven(String email, String password, String exp) {

        logger.info("*** Starting TC_007_LoginDataDriven Test ***");

        try {

            HomePage hp = new HomePage(driver);
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            Assert.assertEquals(lp.getSigninMsg(), "Welcome, Please Sign In!");

            lp.setTxtEmail(email);
            lp.setTxtPassword(password);
            lp.setBtnLogin();

            MyAccountPage map = new MyAccountPage(driver);
            boolean targetPage = map.cnfLblfMyAccount();

            if (exp.equals("Valid")) {
                if (targetPage == true) {
                    map.clickBtnLogout();
                    Assert.assertTrue(true);

                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equals("Invalid")) {
                if (targetPage == true) {
                    map.clickBtnLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        logger.info("*** Finished TC_007_LoginDataDriven Test ***");

    }
}
