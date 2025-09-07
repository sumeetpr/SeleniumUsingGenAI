
package com.company.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ---- Navigation ----
    @FindBy(linkText = "Home")
    private WebElement homeLink;

    @FindBy(linkText = "Resources")
    private WebElement resourcesLink;

    // ---- Section Links ----
    @FindBy(linkText = "Dynamic ID") private WebElement dynamicIdLink;
    @FindBy(linkText = "Class Attribute") private WebElement classAttrLink;
    @FindBy(linkText = "Hidden Layers") private WebElement hiddenLayersLink;
    @FindBy(linkText = "Load Delay") private WebElement loadDelayLink;
    @FindBy(linkText = "AJAX Data") private WebElement ajaxDataLink;
    @FindBy(linkText = "Client Side Delay") private WebElement clientDelayLink;
    @FindBy(linkText = "Click") private WebElement clickLink;
    @FindBy(linkText = "Text Input") private WebElement textInputLink;
    @FindBy(linkText = "Scrollbars") private WebElement scrollbarsLink;
    @FindBy(linkText = "Dynamic Table") private WebElement dynamicTableLink;
    @FindBy(linkText = "Verify Text") private WebElement verifyTextLink;
    @FindBy(linkText = "Progress Bar") private WebElement progressBarLink;
    @FindBy(linkText = "Visibility") private WebElement visibilityLink;
    @FindBy(linkText = "Sample App") private WebElement sampleAppLink;
    @FindBy(linkText = "Mouse Over") private WebElement mouseOverLink;
    @FindBy(linkText = "Non-Breaking Space") private WebElement nbspLink;
    @FindBy(linkText = "Overlapped Element") private WebElement overlappedElementLink;
    @FindBy(linkText = "Shadow DOM") private WebElement shadowDomLink;
    @FindBy(linkText = "Alerts") private WebElement alertsLink;
    @FindBy(linkText = "File Upload") private WebElement uploadLink;
    @FindBy(linkText = "Animated Button") private WebElement animationLink;
    @FindBy(linkText = "Disabled Input") private WebElement disabledInputLink;
    @FindBy(linkText = "Auto Wait") private WebElement autoWaitLink;


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        initLinkMap();
    }

    // ---- Optimized: Single Map for all links ----
    private final Map<String, WebElement> linkMap = new HashMap<>();

    private void initLinkMap() {
        linkMap.put("home", homeLink);
        linkMap.put("resources", resourcesLink);
        linkMap.put("dynamicId", dynamicIdLink);
        linkMap.put("classAttr", classAttrLink);
        linkMap.put("hiddenLayers", hiddenLayersLink);
        linkMap.put("loadDelay", loadDelayLink);
        linkMap.put("ajaxData", ajaxDataLink);
        linkMap.put("clientDelay", clientDelayLink);
        linkMap.put("click", clickLink);
        linkMap.put("textInput", textInputLink);
        linkMap.put("scrollbars", scrollbarsLink);
        linkMap.put("dynamicTable", dynamicTableLink);
        linkMap.put("verifyText", verifyTextLink);
        linkMap.put("progressBar", progressBarLink);
        linkMap.put("visibility", visibilityLink);
        linkMap.put("sampleApp", sampleAppLink);
        linkMap.put("mouseOver", mouseOverLink);
        linkMap.put("nbsp", nbspLink);
        linkMap.put("overlapped", overlappedElementLink);
        linkMap.put("shadowDom", shadowDomLink);
        linkMap.put("alerts", alertsLink);
        linkMap.put("upload", uploadLink);
        linkMap.put("animation", animationLink);
        linkMap.put("disabledInput", disabledInputLink);
        linkMap.put("autoWait", autoWaitLink);

    }

    // ---- Optimized Reusable Methods ----
    public HomePage clickLink(String key) {
        if (linkMap.containsKey(key)) {
            WebElement link = linkMap.get(key);
            wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        } else {
            throw new IllegalArgumentException("No link found for key: " + key);
        }
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
