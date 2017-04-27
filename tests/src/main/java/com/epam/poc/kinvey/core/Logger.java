package com.epam.poc.kinvey.core;

import org.openqa.selenium.WebDriver;

/**
 * Common test logger
 */
public class Logger {

    private static final String RP_MESSAGE = "RP_MESSAGE#FILE#%s#%s";

    /** Logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Logger.class);

    public static void info(Object msg, Throwable error) {
        LOGGER.info(msg, error);
    }

    public static void info(Object msg) {
        LOGGER.info(msg);
    }

    public static void debug(Object msg, Throwable error) {
        LOGGER.debug(msg, error);
    }

    public static void debug(Object msg) {
        LOGGER.debug(msg);
    }

    public static void warn(Object msg, Throwable error) {
        LOGGER.warn(msg, error);
    }

    public static void warn(Object message) {
        LOGGER.warn(message);
    }

    public static void fatal(Object msg, Throwable error) {
        LOGGER.fatal(msg, error);
    }

    public static void fatal(Object message) {
        LOGGER.fatal(message);
    }

    public static void error(Object msg, Throwable error) {
        LOGGER.error(msg, error);
    }

    public static void error(Object msg) {
        LOGGER.error(msg);
    }

    public static void trace(Object msg, Throwable error) {
        LOGGER.trace(msg, error);
    }

    public static void trace(Object msg) {
        LOGGER.trace(msg);
    }

    public static void screenshot(String msg, WebDriver driver) {
        LOGGER.info(String.format(RP_MESSAGE, Screenshoter.takeScreenshot(driver, msg), msg));
    }
}
