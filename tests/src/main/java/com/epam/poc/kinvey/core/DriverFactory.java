package com.epam.poc.kinvey.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

    public static final int IMPLICIT_TIMEOUT = 10;
    private static ThreadLocal<AppiumDriver> driverPool = new ThreadLocal<>();

    public enum ConfigType {
        NATIVE_ANDROID_APP, NATIVE_IOS_APP
    }

    private static void setDriver(AppiumDriver driver) {
        driverPool.set(driver);
    }

    public static AppiumDriver getDriver() {
        return driverPool.get();
    }

    public static void createDriver(ConfigType config) throws MalformedURLException {
        File app;
        AppiumDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1_200);

        switch (config) {
            case NATIVE_ANDROID_APP:
                app = new File("../app/build/outputs/apk/app-debug.apk");
                caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                caps.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, 120_000);
                caps.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 120_000);
                caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.epam.synchronoss.orbitsync");
                caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.SyncpointActivity");
                caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".activity.CredentialsActivity");
                driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
                break;
            case NATIVE_IOS_APP:
                app = new File("app-debug.app.zip");
                caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Retina 3.5-inch");
                driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
                break;
        }

        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        setDriver(driver);
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            setDriver(null);
        }
    }
}
