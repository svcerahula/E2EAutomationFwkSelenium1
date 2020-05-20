package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports reportObj;

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir")+"\\extent-reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("FB UI Browser Automation Results");
        reporter.config().setDocumentTitle("UI Test Results");
        reportObj  = new ExtentReports();
        reportObj.attachReporter(reporter);
        reportObj.setSystemInfo("Tester","CTS Emp");
        return reportObj;
    }
}
