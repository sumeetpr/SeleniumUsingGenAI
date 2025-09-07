package com.company.framework.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() { return driver.get(); }

    public static void setDriver(WebDriver d) { driver.set(d); }

    public static void unload() { driver.remove(); }
}
