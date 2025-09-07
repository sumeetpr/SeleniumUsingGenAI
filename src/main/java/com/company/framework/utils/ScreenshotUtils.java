package com.company.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
        public static String capture(WebDriver driver, String name) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = name + "_" + ts + ".png";

                Path screenshotsDir = Path.of(System.getProperty("user.dir"), "reports", "screenshots");
                Files.createDirectories(screenshotsDir);

                Path dest = screenshotsDir.resolve(fileName);
                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

                // return relative path (important for Extent)
                return "screenshots/" + fileName;
            } catch (Exception e) {
                return null;
            }
        }
}
