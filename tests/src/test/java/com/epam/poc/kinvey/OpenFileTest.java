package com.epam.poc.kinvey;

import com.epam.poc.kinvey.core.BaseTest;
import com.epam.poc.kinvey.pages.CreateNewPasswordPage;
import com.epam.poc.kinvey.pages.FileSystemPage;
import com.epam.poc.kinvey.pages.ImageFilePage;
import com.epam.poc.kinvey.pages.LoginPage;
import com.epam.poc.kinvey.pages.SecretTokenPage;
import com.epam.poc.kinvey.pages.TextFilePage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class OpenFileTest extends BaseTest {

    @BeforeMethod(description = "Login and open file system root", alwaysRun = true)
    public void loginAndOpenFSRoot() {
        if (new LoginPage().isDisplayed()) {
            new LoginPage()
                    .typeUsername("testuser");
            new SecretTokenPage()
                    .typeSecretToken("Testing123");
            new CreateNewPasswordPage()
                    .createNewPassword("123");
        }
    }

    @Test(description = "Test that text file opens correctly")
    public void testOpenTxtFile() {
        new FileSystemPage()
                .navigateTo("Documents/Records/Poems")
                .openFile("poem1.txt");
        new TextFilePage()
                .verifyDisplayed()
                .verifyFileText(containsString("Camouflaged"))
                .navigateUp();
    }

    @Test(description = "Test that image file opens correctly")
    public void testOpenImgFile() {
        new FileSystemPage()
                .navigateTo("Documents/Images/PNG Images")
                .openFile("image1.png");
        new ImageFilePage()
                .verifyDisplayed()
                .navigateUp();
    }

    @AfterMethod(description = "Navigate up to file system root", alwaysRun = true)
    public void navigateToFSRoot() {
        new FileSystemPage()
                .navigateUpToRoot();
    }
}
