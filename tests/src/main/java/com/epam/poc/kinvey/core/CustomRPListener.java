package com.epam.poc.kinvey.core;

import com.epam.ta.reportportal.listeners.testng.ReportPortalTestNGListener;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class CustomRPListener extends ReportPortalTestNGListener {

    private static final Logger LOGGER = Logger.getLogger(CustomRPListener.class);
    private static final String RP_MESSAGE = "RP_MESSAGE#FILE#%s#%s";

    @Override
    public void onStart(ISuite suite) {
        super.onStart(suite);
        LOGGER.info("Test suite started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("Test suite finished: " + suite.getName());
        super.onFinish(suite);
    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        LOGGER.info("Test class started: " + testContext.getName());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        LOGGER.info("Test class finished: " + testContext.getName());
        super.onFinish(testContext);
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        LOGGER.info("Test method started: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test method passed: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        super.onTestSuccess(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("Test method skipped: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        super.onTestSkipped(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("Test method failed: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        takeScreenshot(result);
        super.onTestFailure(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOGGER.error("Test method failed: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        takeScreenshot(result);
        super.onTestFailedButWithinSuccessPercentage(result);
    }

    private void takeScreenshot(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            LOGGER.debug(String.format(RP_MESSAGE,
                    Screenshoter.takeScreenshot(driver, result.getName()), "Screenshot"));
        } else {
            LOGGER.warn("Unable to take screenshot");
        }
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        LOGGER.error("Config method failed: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        takeScreenshot(result);
        super.onConfigurationFailure(result);
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        LOGGER.info("Config method passed: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        super.onConfigurationSuccess(result);
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {
        LOGGER.warn("Config method skipped: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
        super.onConfigurationSkip(result);
    }

    @Override
    public void beforeConfiguration(ITestResult result) {
        super.beforeConfiguration(result);
        LOGGER.info("Config method started: " + result.getName() + "() [" + result.getMethod().getDescription() + "]");
    }
}
