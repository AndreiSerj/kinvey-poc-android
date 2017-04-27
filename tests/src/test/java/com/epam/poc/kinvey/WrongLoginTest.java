package com.epam.poc.kinvey;

import com.epam.poc.kinvey.core.BaseTest;
import com.epam.poc.kinvey.pages.AlertDialog;
import com.epam.poc.kinvey.pages.CreateNewPasswordPage;
import com.epam.poc.kinvey.pages.LoginPage;
import com.epam.poc.kinvey.pages.SecretTokenPage;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class WrongLoginTest extends BaseTest {

    @Test(description = "Test wrong login", groups = { "login" })
    public void testWrongLogin() {
        new LoginPage()
                .verifyDisplayed()
                .typeUsername("testuser");
        new SecretTokenPage()
                .typeSecretToken("WrongPass");
        new AlertDialog()
                .verifyDisplayed()
                .verifyTitle(equalTo("Incorrect Password"))
                .dismiss();
        new SecretTokenPage()
                .verifyDisplayed();
        new CreateNewPasswordPage()
                .verifyNotDisplayed();
    }
}
