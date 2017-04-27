package com.epam.poc.kinvey.core;

import com.epam.poc.kinvey.core.DriverFactory.ConfigType;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private ConfigType config = ConfigType.NATIVE_ANDROID_APP;

    @BeforeClass(description = "Create driver", alwaysRun = true)
    public void setUp() throws Exception {
        Logger.debug("Creating driver using config " + config + "...");
        DriverFactory.createDriver(config);
        Logger.debug("Driver created successfully");
    }

    @AfterClass(description = "Quit driver", alwaysRun = true)
    public void tearDown() throws Exception {
        Logger.debug("Quitting driver...");
        DriverFactory.closeDriver();
        Logger.debug("Driver quited successfully");
    }
}
