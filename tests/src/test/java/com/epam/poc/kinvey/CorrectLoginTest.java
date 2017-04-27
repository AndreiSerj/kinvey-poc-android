package com.epam.poc.kinvey;

import com.epam.poc.kinvey.core.BaseTest;
import com.epam.poc.kinvey.pages.CreateNewPasswordPage;
import com.epam.poc.kinvey.pages.LoginPage;
import com.epam.poc.kinvey.pages.SecretTokenPage;

import org.testng.annotations.Test;

public class CorrectLoginTest extends BaseTest {

    @Test(description = "Test correct login", groups = { "login" })
    public void testCorrectLogin() {
        new LoginPage()
                .verifyDisplayed()
                .typeUsername("testuser");
        new SecretTokenPage()
                .typeSecretToken("Testing123");
        new CreateNewPasswordPage()
                .verifyDisplayed()
                .createNewPassword("123");
    }
}
