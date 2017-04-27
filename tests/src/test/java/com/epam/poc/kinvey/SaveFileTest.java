package com.epam.poc.kinvey;

import com.epam.poc.kinvey.core.BaseTest;
import com.epam.poc.kinvey.pages.CreateNewPasswordPage;
import com.epam.poc.kinvey.pages.FileSystemPage;
import com.epam.poc.kinvey.pages.LoginPage;
import com.epam.poc.kinvey.pages.SecretTokenPage;
import com.epam.poc.kinvey.pages.TextFilePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.endsWith;

public class SaveFileTest extends BaseTest {

    @BeforeMethod(description = "Login and open file system root")
    public void loginAndOpenFSRoot() {
        new LoginPage()
                .verifyDisplayed()
                .typeUsername("testuser");
        new SecretTokenPage()
                .typeSecretToken("Testing123");
        new CreateNewPasswordPage()
                .createNewPassword("123");
    }

    @Test(description = "Test that file saved correctly")
    public void testSaveTxtFile() {
        new FileSystemPage()
                .navigateTo("Documents/Records/Poems")
                .openFile("poem3.txt");
        new TextFilePage()
                .verifyDisplayed()
                .saveFile()
                .verifyToastMessage(endsWith("saved in secure storage"));
    }
}
