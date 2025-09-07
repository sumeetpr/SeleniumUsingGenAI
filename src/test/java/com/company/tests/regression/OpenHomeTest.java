package com.company.tests.regression;

import com.company.framework.base.BaseTest;
import com.company.framework.drivers.DriverManager;
import com.company.framework.reporting.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenHomeTest extends BaseTest {

    @Test
    public void openUITAP() {
        ExtentTestManager.getTest().info("Navigating to UI Testing Playground");
        DriverManager.getDriver().get("http://uitestingplayground.com/");
        String title = DriverManager.getDriver().getTitle();
        ExtentTestManager.getTest().info("Title: " + title);
        Assert.assertTrue(title.contains("UI Test Automation"), "Title mismatch!");
    }
}
