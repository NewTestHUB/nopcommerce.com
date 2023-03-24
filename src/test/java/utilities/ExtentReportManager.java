package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    String repName;

    @Override
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        repName = context.getName()+"_"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
        sparkReporter.config().setDocumentTitle("nopcommerce Automation Report");
        sparkReporter.config().setReportName("nopcommerce Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();

        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "nopcommerce.com");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub-Module", "Customers");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");
    }

    public void onTestSuccess(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS, "Test Case Passed is : "+result.getName());
    }

    public void onTestFailure(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL, "Test Case Failed is : "+result.getName());
        extentTest.log(Status.FAIL, "Test Case Failed Cause is : "+result.getThrowable().getMessage());

        try {
            String imgPath =new BaseClass().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, "Test Case Skipped is : "+result.getName());
        extentTest.log(Status.SKIP, "Test Case Skipped Cause is : "+result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {

        extentReports.flush();
    }
}
