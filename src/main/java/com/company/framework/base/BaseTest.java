package com.company.framework.base;

import com.company.framework.drivers.BrowserType;
import com.company.framework.drivers.DriverManager;
import com.company.framework.drivers.WebDriverFactory;
import com.company.framework.reporting.ExtentListener;
import com.company.framework.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
@Listeners(ExtentListener.class)
public class BaseTest {
    private static ThreadLocal<BrowserType> threadBrowser = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("") String browserName) {
        BrowserType browser = browserName.isEmpty()
                ? BrowserType.valueOf(ConfigManager.getProperty("browser").toUpperCase())
                : BrowserType.valueOf(browserName.toUpperCase());

        threadBrowser.set(browser);
        WebDriver driver = WebDriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
        DriverManager.unload();
        threadBrowser.remove();
    }

    public static BrowserType getBrowser() {
        return threadBrowser.get();
    }
}
