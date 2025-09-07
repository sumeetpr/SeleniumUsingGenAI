package com.company.framework.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() { return test.get(); }

    public static void setTest(ExtentTest t) { test.set(t); }

    public static void unload() { test.remove(); }
}
