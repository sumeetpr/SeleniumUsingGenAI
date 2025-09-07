package com.company.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    public synchronized static ExtentReports getInstance() {

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = "reports/ExtentReport_" + timestamp + ".html";

        if (extent == null) {
            spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentSparkReporter getSparkReporter() {
        return spark;
    }

    private static ExtentReports createInstance() {
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + ts + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("UI Automation Report");
        spark.config().setReportName("SeleniumUsingGenAI – Test Results");
        spark.config().setTimeStampFormat("dd-MMM-yyyy HH:mm:ss");

        ExtentReports ex = new ExtentReports();
        ex.attachReporter(spark);
        ex.setSystemInfo("OS", System.getProperty("os.name"));
        ex.setSystemInfo("Java", System.getProperty("java.version"));
        return ex;
    }
}

