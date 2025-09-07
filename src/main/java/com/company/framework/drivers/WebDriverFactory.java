package com.company.framework.drivers;
import com.company.framework.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    public static WebDriver createDriver(BrowserType browser) {
        WebDriver driver = null;
        String executionMode = ConfigManager.getProperty("execution.mode").toUpperCase();

        try {
            if (executionMode.equals("GRID")) {
                String hubUrl = ConfigManager.getProperty("grid.url");
                switch (browser) {
                    case CHROME:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setAcceptInsecureCerts(true);
                        driver = new RemoteWebDriver(new URL(hubUrl), chromeOptions);
                        break;
                    case FIREFOX:
                        FirefoxOptions ffOptions = new FirefoxOptions();
                        ffOptions.setAcceptInsecureCerts(true);
                        driver = new RemoteWebDriver(new URL(hubUrl), ffOptions);
                        break;
                }
            } else { // LOCAL
                switch (browser) {
                    case CHROME:
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver");
        }

        driver.manage().window().maximize();
        return driver;
    }
}
