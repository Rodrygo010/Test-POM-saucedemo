package base;



import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import factory.DriverFactory;
import utils.ExtentManager;
import utils.ScreenshotUtils;


public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup(Method method) {

        extent = ExtentManager.getReport();
        test = extent.createTest(method.getName());

        driver = DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if(result.getStatus() == ITestResult.FAILURE){

            String path = ScreenshotUtils.takeScreenshot(driver, result.getName());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(path);
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test Passed");
        }

        driver.quit();
        extent.flush();
    }
}
