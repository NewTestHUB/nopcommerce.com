package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_003_SearchTest extends BaseClass {

    @Test
    public void test_SearchTest() {

        logger.info("*** Starting TC_003_SearchTest ***");

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

            captureScreen("TC_003_SearchTest");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        logger.info("*** Starting TC_003_SearchTest ***");

    }

}
