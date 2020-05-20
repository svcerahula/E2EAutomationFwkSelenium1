package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.UiNavigationUtils;
import utilities.ExtentReporterNG;
import java.io.IOException;

public class ListenersUtils implements ITestListener {
    public UiNavigationUtils uiObj = new UiNavigationUtils();
    public WebDriver driver;
    public ExtentReports reportObj = ExtentReporterNG.getReportObject();
    public ExtentTest test;
    ThreadLocal<ExtentTest> extentTestObj = new ThreadLocal();
    public void onTestStart(ITestResult iTestResult) {
        String testCaseName = iTestResult.getMethod().getMethodName();
        test = reportObj.createTest(testCaseName);
        extentTestObj.set(test);
    }

    public void onTestSuccess(ITestResult iTestResult) {
        extentTestObj.get().log(Status.PASS,iTestResult.getTestName() + " test has passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        extentTestObj.get().fail(iTestResult.getThrowable());
        String failedMethod = iTestResult.getMethod().getMethodName();
        Logger log = null;
        try {
            log = (Logger)iTestResult.getTestClass().getRealClass()
                    .getDeclaredField("log").get(iTestResult.getInstance());
            driver = (WebDriver)iTestResult.getTestClass().getRealClass()
                    .getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            String destFilePath = uiObj.getScreenshotPath(driver,failedMethod,log);
            extentTestObj.get().addScreenCaptureFromPath(destFilePath,failedMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        reportObj.flush(); // flush the listener
    }
}
