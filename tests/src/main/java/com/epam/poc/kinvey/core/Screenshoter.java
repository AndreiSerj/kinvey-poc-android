package com.epam.poc.kinvey.core;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.io.Files;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshoter {

    private static final String SCREENSHOTS_FOLDER = "screenshots";
    private static final String DATE_FORMAT = "dd_MMM_yyyy__hh_mm_ssaa_SSS";
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(Screenshoter.class);

    public static String takeScreenshot(WebDriver driver, String additionalInfo) {
        LOGGER.debug("Taking page screenshot...");
        final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String scrFilePath = null;

        try {
            final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            final Date date = new Date();

            final String scrFolderPath = new File("").getCanonicalPath() + File.separator + SCREENSHOTS_FOLDER;
            scrFilePath = scrFolderPath + File.separator + dateFormat.format(date)
                    + ((additionalInfo == null) ? "" : "__" + additionalInfo) + ".png";

            // create screenshots folder if not exists
            final File scrFolder = new File(scrFolderPath);
            if (!scrFolder.exists()) {
                if (scrFolder.mkdir()) {
                    LOGGER.debug("Directory " + scrFolder + " was created.");
                } else {
                    LOGGER.error("Unable to create " + scrFolder + " folder.");
                }
            }

            // copy screenshot file from temp to project folder
            Files.copy(scrFile, new File(scrFilePath));
            LOGGER.debug("Screenshot captured: " + scrFilePath);

        } catch (final IOException e) {
            LOGGER.error("Failed to take screenshot", e);
        }

        return scrFilePath;
    }

    public static String takeScreenshot(WebDriver driver) {
        return takeScreenshot(driver, null);
    }
}
