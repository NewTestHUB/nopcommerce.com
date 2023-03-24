import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EndToEndTest {

    public WebDriver driver;
    @Test
    public void test_EndToEnd() throws InterruptedException, IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.getCapabilityNames();

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.name("FirstName")).sendKeys("Test001");
        driver.findElement(By.name("LastName")).sendKeys("Customer");
        WebElement selectDate = driver.findElement(By.name("DateOfBirthDay"));
        Select dob = new Select(selectDate);
        dob.selectByVisibleText("15");

        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select dom = new Select(selectMonth);
        dom.selectByVisibleText("August");

        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select doy = new Select(selectYear);
        doy.selectByVisibleText("1983");

        String email = RandomStringUtils.randomAlphabetic(6)+"@email.com";
        driver.findElement(By.id("Email")).sendKeys(email);

        driver.findElement(By.name("Company")).sendKeys("XYZ Limited");

        driver.findElement(By.name("Password")).sendKeys("Test@123");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test@123");

        driver.findElement(By.id("register-button")).click();

        WebElement regCnfMsg = driver.findElement(By.xpath("//div[text()='Your registration completed']"));

        try {
            Assert.assertEquals(regCnfMsg.getText(), "Your registration completed");
        } catch (Exception e) {
            e.getMessage();
        }

//        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        driver.findElement(By.linkText("CONTINUE")).click();

        driver.findElement(By.xpath("//a[text()='Log in']")).click();

        driver.findElement(By.name("Email")).sendKeys(email);
        driver.findElement(By.name("Password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        driver.findElement(By.name("q")).sendKeys("books");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

       List<WebElement> searchProducts = driver.findElements(By.xpath("//div[@class='item-grid']"));

        System.out.println("Total No.of Products : "+searchProducts.size());

        for(WebElement product:searchProducts) {
            if(product.getText().contains("HP Spectre XT Pro UltraBook")) {
            driver.findElement(By.xpath("//a[text()='HP Spectre XT Pro UltraBook']")).click();
            }
        }

        driver.findElement(By.id("product_enteredQuantity_7")).clear();
        driver.findElement(By.id("product_enteredQuantity_7")).sendKeys("2");

        driver.findElement(By.id("add-to-cart-button-7")).click();

        WebElement msgAddedtocart = driver.findElement(By.xpath("//p[text()='The product has been added to your ']"));

        Assert.assertEquals(msgAddedtocart.getText(), "The product has been added to your shopping cart");

        driver.findElement(By.xpath("//span[@title='Close']")).click();

        WebElement sCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        Actions sCartMouseActions = new Actions(driver);
        sCartMouseActions.moveToElement(sCart).perform();

        driver.findElement(By.xpath("//button[text()='Go to cart']")).click();

        WebElement lblShopCart = driver.findElement(By.xpath("//h1"));
        System.out.println(lblShopCart.getText());
        Assert.assertEquals(lblShopCart.getText(),"Shopping cart");

        WebElement valueinSCart =  driver.findElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[6]/span"));

        System.out.println(valueinSCart.getText());

        Assert.assertEquals(valueinSCart.getText(), "$2,700.00");

        WebElement valueinChCart =  driver.findElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[2]/span/strong"));

        System.out.println(valueinChCart.getText());

        Assert.assertEquals(valueinChCart.getText(), "$2,700.00");

        driver.findElement(By.name("termsofservice")).click();

        driver.findElement(By.id("checkout")).click();

        //**** Checkout Page ****

        WebElement pageCheckout = driver.findElement(By.xpath("//h1"));

        Assert.assertEquals(pageCheckout.getText(), "Checkout");

        WebElement listCountry = driver.findElement(By.name("BillingNewAddress.CountryId"));

        Select selectCountry = new Select(listCountry);
        selectCountry.selectByVisibleText("India");

       driver.findElement(By.name("BillingNewAddress.City")).sendKeys("Banglore");

       driver.findElement(By.name("BillingNewAddress.Address1")).sendKeys("Main Road");

       driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("560001");

       driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("9999999999");

       driver.findElement(By.xpath("//button[@onclick='Billing.save()']")).click();

       driver.findElement(By.xpath("//button[@onclick='ShippingMethod.save()']")).click();

        driver.findElement(By.xpath("//button[@onclick='PaymentMethod.save()']")).click();

        driver.findElement(By.xpath("//button[@onclick='PaymentInfo.save()']")).click();

        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        WebElement ordMessage = driver.findElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));

        Assert.assertEquals(ordMessage.getText(), "Your order has been successfully processed!");

        driver.findElement(By.xpath("//a[text()='Click here for order details.']")).click();

        WebElement orderInfo = driver.findElement(By.xpath("//h1"));

        Assert.assertEquals(orderInfo.getText(), "Order information");

//        File ordInfo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(ordInfo, new File("./screenshots/orderinfo.png"));

        Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"png",new File("./screenshots/orderinfofullpage.png") );

        driver.close();
    }
}
