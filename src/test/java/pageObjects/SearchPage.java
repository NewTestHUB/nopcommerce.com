package pageObjects;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//h1")
    WebElement lblSearch;

    @FindBy(xpath = "//div[@class='item-grid']")
    List<WebElement> searchProducts;

    @FindBy(xpath = "//a[text()='HP Spectre XT Pro UltraBook']")//Product Name
    WebElement Product;

    @FindBy(xpath = "//h1") // HP Spectre XT Pro UltraBook
    WebElement lblSelectedProductName;

    //Actions

    public String getlblSearch() {

        String Search = lblSearch.getText();
        return Search;
    }

    public boolean isProductAvailable(String productName) {

        boolean flag =false;
        for(WebElement product:searchProducts) {

            if(product.getText().contains(productName)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void selectProduct(String productName) {
        for(WebElement product:searchProducts) {
            if(product.getText().contains(productName))
                Product.click();
        }
    }
    public String getSelectedProductName() {
        String pName = lblSelectedProductName.getText();
        return pName;
    }


}
