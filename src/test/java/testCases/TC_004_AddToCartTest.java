package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddToCartPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_AddToCartTest extends BaseClass {

    @Test
    public void test_AddToCartTest() {

        logger.info("*** Starting TC_004_AddToCartTest ***");

        try {
            HomePage hp = new HomePage(driver);
            hp.enterSearchProduct("books");
            hp.clickBtnSearch();

            SearchPage sp = new SearchPage(driver);
            Assert.assertEquals(sp.getlblSearch(), "Search");

            if (sp.isProductAvailable("HP Spectre XT Pro UltraBook")) {
                sp.selectProduct("HP Spectre XT Pro UltraBook");
            }

            System.out.println(sp.getSelectedProductName());

            Assert.assertEquals(sp.getSelectedProductName(), "HP Spectre XT Pro UltraBook");

            AddToCartPage acp = new AddToCartPage(driver);

            Assert.assertEquals(acp.getProductCost(),"$1,350.00");

            acp.enterQty("2");

            acp.clickBtnAddToCart();

            Assert.assertEquals(acp.getMsgAddedShoppingCart(),"The product has been added to your shopping cart");

            acp.clickBtnCancel();

            acp.perforMouseHoverAction();

            acp.clickBtnGoToCart();

            Assert.assertEquals(acp.getLblShoppingCart(),"Shopping cart");

            captureScreen("TC_004_AddToCartTest");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        logger.info("*** Starting TC_004_AddToTest ***");

    }
}
