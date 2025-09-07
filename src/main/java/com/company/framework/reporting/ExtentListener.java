package com.company.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.company.framework.base.BaseTest;
import com.company.framework.drivers.BrowserType;
import com.company.framework.drivers.DriverManager;
import com.company.framework.utils.ScreenshotUtils;
import org.testng.*;

public class ExtentListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Get browser from BaseTest
        Object instance = result.getInstance();
        BrowserType browser = BrowserType.CHROME; // default
        if (instance instanceof BaseTest) {
            browser = ((BaseTest) instance).getBrowser();
        }

        ExtentTest test = extent.createTest(testName + " [" + browser + "]");
        ExtentTestManager.setTest(test);
        test.info("Test started on browser: " + browser);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        // Try to attach a screenshot if driver is available
        if (DriverManager.getDriver() != null) {
            String path = ScreenshotUtils.capture(DriverManager.getDriver(), result.getMethod().getMethodName());
            if (path != null) {
                try {
                    test.fail("Screenshot on failure",
                            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                } catch (Exception ignored) {}
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test skipped: " + result.getSkipCausedBy().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

//    @Override
//    public void onFinish(ITestContext context) {
//        // Optional: add quick pass% summary to System Info
//        int total = context.getAllTestMethods().length;
//        int passed = context.getPassedTests().size();
//        double passPct = total == 0 ? 0 : (passed * 100.0 / total);
//        extent.setSystemInfo("Suite: " + context.getName(), String.format("Pass %%: %.2f", passPct));
//        ExtentTestManager.unload();
//    }

    @Override
    public void onFinish(ITestContext context) {
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        double passPct = total == 0 ? 0 : (passed * 100.0 / total);

        // Update System Info
        extent.setSystemInfo("Suite Name", context.getName());
        extent.setSystemInfo("Total Tests", String.valueOf(total));
        extent.setSystemInfo("Passed", String.valueOf(passed));
        extent.setSystemInfo("Failed", String.valueOf(failed));
        extent.setSystemInfo("Skipped", String.valueOf(skipped));
        extent.setSystemInfo("Pass %", String.format("%.2f", passPct));

        // Update report banner
        ExtentManager.getSparkReporter().config().setReportName(
                "Execution Report - Pass %: " + String.format("%.2f", passPct)
        );


        ExtentTestManager.unload();
    }
}
