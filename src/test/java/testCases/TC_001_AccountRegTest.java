package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegTest extends BaseClass {

    @Test
    public void test_AccountRegistration(){

        logger.debug("Application Logs....");

        logger.info("*** Starting TC_001_Account Registration Test ***");

        try {

            HomePage hp = new HomePage(driver);

            logger.info("*** On Home Page Clicked Register Link ***");

            hp.clickRegister();

            RegistrationPage rp = new RegistrationPage(driver);

            logger.info("*** On Register Page Confirm Register Title ***");

            Assert.assertEquals(rp.cnfRegPage(), "Register");

            logger.info("*** Entering Required Data for Account Registration ***");

            rp.selectGender();
            rp.enterFirstName(getRandomString(5));
            rp.enterLastName(getRandomString(5));
            rp.selectDOBDate("15");
            rp.selectDOBMonth("August");
            rp.selectDOBYear("1982");
            String email = getAlphaNumeric(3) + "@email.com";
            rp.enterEmail(email);
//            rp.enterEmail(rb.getString("email"));
            rp.enterCompanyName(rb.getString("company"));
            rp.enterPassword(rb.getString("password"));
            rp.reenterPassword(rb.getString("password"));
            rp.clickBtnRegister();

            Assert.assertEquals(rp.cnfRegMsg(), "Your registration completed");

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }

        logger.info("*** Completed TC_001_Account Registration Test ***");

    }
}
