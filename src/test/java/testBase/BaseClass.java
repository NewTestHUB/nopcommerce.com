package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;


public class BaseClass {

    public static WebDriver driver;

    public ResourceBundle rb;

    public Logger logger;

    @BeforeClass
    @Parameters("browser")
    public void setup(String br) {

        rb = ResourceBundle.getBundle("config");
        logger = LogManager.getLogger(this.getClass());

        if(br.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (br.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(rb.getString("appURL"));
    }

    @AfterClass
    public void teardown() {

        driver.close();
    }

    public String getRandomString(Integer num) {
        return RandomStringUtils.randomAlphabetic(num);
    }

    public String getRandomNumber(Integer num) {
        return RandomStringUtils.randomNumeric(num);
    }

    public String getAlphaNumeric(Integer num) {
        String alpha = RandomStringUtils.randomAlphabetic(num);
        String number = RandomStringUtils.randomNumeric(num);

        return (alpha+number);
    }

    public String getEmail(Integer num) {
        String alpha = RandomStringUtils.randomAlphabetic(num);
        String number = RandomStringUtils.randomNumeric(num);

        return (alpha+number);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

        Screenshot source = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);
        String destination = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";

//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        String destination = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";

        try {
            ImageIO.write(source.getImage(), "png", new File(destination));
//            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }

}
