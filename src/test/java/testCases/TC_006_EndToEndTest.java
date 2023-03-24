package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

public class TC_006_EndToEndTest extends BaseClass {

    @Test
    public void test_EndToEnd() {

        logger.info("*** Starting TC_06_EndToEndTest ***");

        try {
            //Home Page
            logger.info("---- Starting Home Page ----");
            HomePage hp = new HomePage(driver);
            hp.clickRegister();
            logger.info("---- Completed Home Page ----");

            logger.info("---- Starting Registration Page ----");
            //Registration Page
            RegistrationPage rp = new RegistrationPage(driver);
            Assert.assertEquals(rp.cnfRegPage(), "Register");
            rp.selectGender();
            rp.enterFirstName(getRandomNumber(4).toUpperCase());
            rp.enterLastName(getRandomString(4).toUpperCase());
            rp.selectDOBDate("10");
            rp.selectDOBMonth("August");
            rp.selectDOBYear("1981");
            String email = getAlphaNumeric(4) + "@email.com";
            rp.enterEmail(email);
            rp.enterCompanyName(rb.getString("company"));
            rp.enterPassword(rb.getString("password"));
            rp.reenterPassword(rb.getString("password"));
            rp.clickBtnRegister();
            Assert.assertEquals(rp.cnfRegMsg(), "Your registration completed");
            rp.clickBtnContinue();
//            rp.clickLogout();
            logger.info("---- Completed Registration Page ----");

            logger.info("---- Starting Login Page ----");

            hp.clickLogin();
            //Login Page
            LoginPage lp = new LoginPage(driver);

            Assert.assertEquals(lp.getSigninMsg(), "Welcome, Please Sign In!");
            lp.setTxtEmail(email);
            lp.setTxtPassword(rb.getString("password"));
            lp.setBtnLogin();
            Assert.assertEquals(lp.checkMyAccount(), "My account");
            logger.info("---- Completed Login Page ----");

            logger.info("---- Starting Search Page ----");
            hp.enterSearchProduct("books");
            hp.clickBtnSearch();
            //Search Page
            SearchPage sp = new SearchPage(driver);
            Assert.assertEquals(sp.getlblSearch(), "Search");

            if (sp.isProductAvailable("HP Spectre XT Pro UltraBook")) {
                sp.selectProduct("HP Spectre XT Pro UltraBook");
            }

            System.out.println(sp.getSelectedProductName());

            Assert.assertEquals(sp.getSelectedProductName(), "HP Spectre XT Pro UltraBook");
            logger.info("---- Completed Search Page ----");

            logger.info("---- Starting Add to Cart Page ----");
            //Add to Cart Page
            AddToCartPage acp = new AddToCartPage(driver);

            Assert.assertEquals(acp.getProductCost(), "$1,350.00");

            acp.enterQty("2");

            acp.clickBtnAddToCart();

            Assert.assertEquals(acp.getMsgAddedShoppingCart(), "The product has been added to your shopping cart");

            acp.clickBtnCancel();

            acp.perforMouseHoverAction();

            acp.clickBtnGoToCart();

            Assert.assertEquals(acp.getLblShoppingCart(), "Shopping cart");
            logger.info("---- Completed Add to Cart Page ----");

            logger.info("---- Starting Shopping Cart Page ----");
            //Shopping Cart Page
            ShoppingCartPage scp = new ShoppingCartPage(driver);

            System.out.println(scp.getShoppingCartValue());

            Assert.assertEquals(scp.getShoppingCartValue(), "$2,700.00");

            scp.clickChkTermCond();
            scp.clickbtnCheckOut();
            Assert.assertEquals(scp.getCheckoutMsg(), "Checkout");
            logger.info("---- Completed Shopping Cart Page ----");

            logger.info("---- Starting Checkout Page ----");
            //Checkout Page
            CheckOutPage cop = new CheckOutPage(driver);

            cop.selectCountry("India");
            cop.enterCity("Pune");
            cop.enterAddr1(getRandomString(10));
            cop.enterZipCode(getRandomNumber(6));
            cop.enterPhoneNum(getRandomNumber(10));
            cop.clickContinue1();
            cop.clickContinue3();
            cop.clickContinue4();
            cop.clickContinue5();
            Assert.assertEquals(cop.getTotalCartValue(), "$2,700.00");
            cop.clickBtnConfirm();
            Thread.sleep(10);
            Assert.assertEquals(cop.getMsgThankyou(), "Thank you");
            Assert.assertEquals(cop.msgOrderConfirm(), "Your order has been successfully processed!");
            cop.clickFinalButtonContinue();
            logger.info("---- Completed Checkout Page ----");

        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        logger.info("*** Finished TC_06_EndToEndTest Successfully***");

    }


}
