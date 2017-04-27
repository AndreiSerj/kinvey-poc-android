package com.epam.poc.kinvey;

import com.epam.poc.kinvey.core.BaseTest;
import com.epam.poc.kinvey.pages.CreateNewPasswordPage;
import com.epam.poc.kinvey.pages.FileSystemPage;
import com.epam.poc.kinvey.pages.LoginPage;
import com.epam.poc.kinvey.pages.SecretTokenPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;

public class FoldersFilesListTest extends BaseTest {

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

    @Test(description = "Test list files and folders")
    public void testFoldersFilesList() {
        new FileSystemPage()
                .verifyDisplayed()
                .verifyFoldersList(hasItem("Documents"))
                .navigateToFolder("Documents")
                .verifyFilesList(contains(
                        "unknown1.sh",
                        "unknown2.bat",
                        "unknown3.diff",
                        "pexels-photo-1.png"));
    }
}
