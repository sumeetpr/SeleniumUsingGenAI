package com.company.tests.regression;

import com.company.framework.base.BaseTest;
import com.company.framework.drivers.DriverManager;
import com.company.framework.pages.HomePage;
import com.company.framework.utils.ConfigManager;
import com.company.framework.utils.LinkKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenUrlTest extends BaseTest {

    @Test
    public void verifySiteIsOpened() {
        String url = ConfigManager.getProperty("base.url");
        DriverManager.getDriver().get(url);

        // Simple validation: check the page title
        String pageTitle =  DriverManager.getDriver().getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Validate the page title contains "UI Test Automation"
        Assert.assertTrue(pageTitle.contains("UI Test Automation"),
                "Expected title to contain 'UI Test Automation' but got: " + pageTitle);
    }

    @Test
    public void testDynamicIdLinkClick() {
        String url = ConfigManager.getProperty("base.url");
        DriverManager.getDriver().get(url);
        HomePage homePage = new HomePage( DriverManager.getDriver());

        // Click on the "Dynamic ID" link using LinkKeys
        homePage.clickLink(LinkKeys.DYNAMIC_ID.getKey());

        // Validate the page title or URL after clicking the link
        String expectedTitle = "Dynamic IDq";
        String actualTitle = homePage.getPageTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Expected title to contain '" + expectedTitle + "' but got: " + actualTitle);
    }



}
